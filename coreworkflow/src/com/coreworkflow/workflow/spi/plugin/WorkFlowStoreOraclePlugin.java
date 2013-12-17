/**   
 * @Title: WorkFlowStoreOraclePlugin.java
 * @Package: com.coreworkflow.workflow.spi.plugin
 * @Description: WorkFlowStoreOraclePlugin.java
 * @author：lehoons@gmail.com
 * @date：2013年12月6日 上午10:54:08  
 */

package com.coreworkflow.workflow.spi.plugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.coreworkflow.workflow.StoreException;
import com.coreworkflow.workflow.entity.WorkFlowEntity;
import com.coreworkflow.workflow.entity.WorkFlowEventEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeArgvEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeEntity;
import com.coreworkflow.workflow.entity.WorkFlowNodeEntity;
import com.coreworkflow.workflow.entity.WorkFlowTaskEntity;
import com.coreworkflow.workflow.spi.WorkFlowStoreInterface;
import com.coreworkflow.workflow.spi.mapper.WorkFlowEntityRowMapper;
import com.coreworkflow.workflow.spi.mapper.WorkFlowEventEntityRowMapper;
import com.coreworkflow.workflow.spi.mapper.WorkFlowInstanceEntityRowMapper;
import com.coreworkflow.workflow.spi.mapper.WorkFlowInstanceNodeArgvEntityRowMapper;
import com.coreworkflow.workflow.spi.mapper.WorkFlowInstanceNodeEntityRowMapper;
import com.coreworkflow.workflow.spi.mapper.WorkFlowNodeEntityRowMapper;
import com.coreworkflow.workflow.spi.mapper.WorkFlowTaskEntityRowMapper;

/**
 * <p>@description：流程存储Oracle实现方式</p>
 * <p>@company：wxltsoft</p>
 * @author：lehoons@gmail.com
 * @date：2013年12月6日 上午10:54:08
 */

public class WorkFlowStoreOraclePlugin implements WorkFlowStoreInterface {

	private static final Log log = LogFactory.getLog(WorkFlowStoreOraclePlugin.class);

	//数据操作对象
	private JdbcTemplate jdbcTemplate;
	//鉴权SQL配置
	private String	WORKFLOW_PERMISS_SQL;
	
	/* 工作流鉴权实现，如果创建流程的人不是创建流程节点的角色，则创建失败
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#checkPermissions(int)
	 */
	@Override
	public boolean CheckPermissions(long permisid) {
		String permisids = jdbcTemplate.queryForObject(WORKFLOW_PERMISS_SQL, String.class);
		log.debug("inpermisid=[" + permisid + "] permisids=" + permisids);
		
		String  permisidarr[] =  permisids.split(",");
		long	permisslong	= 0;
		
		for(String permiss : permisidarr)
		{
			permisslong	= Long.parseLong(permiss);
			
			if(permisslong == permisid)
				return true;
		}

		return false;
	}

	/* 创建一个新的工作流实例，根据流程编码，实例名称、调用者创建一个新的流程实例
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#createWorkFlowInstance(java.lang.String, java.lang.String, long)
	 */
	@Override
	public long CreateWorkFlowInstance(long wfid, String wfname, String instname, long caller) {
		log.debug("创建新的流程实例 流程编号=[" + wfid + "] 流程实例名称=[" + instname + "] 流程发起人=[" + caller + "]");

		if(instname == null || instname.equals(""))
		{
			instname = wfname;
		}
		
		/*
		 * 获取新的流程实例编号，从流程实例序列中获取到后，把获取的序列号作为新流程实例的编号写入到数据库中去，同时发挥给外围该流程实例编号
		 */
		long instid = jdbcTemplate.queryForLong(WORKFLOW_INSTANCE_SEQINSID_SQL);
		
		if(instid == 0)
		{
			return 0;
		}
		
		jdbcTemplate.update(WORKFLOW_INSTANCE_CREATE_SQL, new Object[] {instid, instname, wfid, wfname, caller}, new int[] {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER});
		
		log.debug("流程实例创建成功，返回的流程实例编码=[" + instid +"]");
		return instid;
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#ModifyWorkFlowInstanceStatus(long, int)
	 */
	@Override
	public boolean ModifyWorkFlowInstanceStatus(long instid, int status) {
		jdbcTemplate.update(WORKFLOW_INSTANCE_UPDATE_STATUS_SQL, new Object[] {status, instid}, new int[] {Types.INTEGER, Types.INTEGER});
		return true;
	}

	/* 批量创建流程实例的任务和节点信息
	 * instid  流程实例编号
	 * nodeid  流程节点编号
	 * preTaskId 上一个任务编号
	 * inParams  附加参数
	 *           caller 用户编号
	 *           orgid  部门编号
	 *           
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#createWorkFlowTask(long, long, long, java.util.Map)
	 */
	@Override
	public long CreateWorkFlowTaskBatch(final long instid, final long nodeid, final long preNodeId,
			final List<WorkFlowTaskEntity> inParams) {
		
		jdbcTemplate.batchUpdate(WORKFLOW_INSTANCETASK_CREATE_BATCHSQL,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						WorkFlowTaskEntity workflowTaskEntity = inParams.get(i);
						ps.setLong(1, instid);
						ps.setLong(2, nodeid);
						ps.setLong(3, workflowTaskEntity.getPostid());
						ps.setLong(4, workflowTaskEntity.getOrgid());
						ps.setLong(5, preNodeId);
						ps.setLong(6, 0);
					}

					@Override
					public int getBatchSize() {
						return inParams.size();
					}
				});

		return 0;
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#updateWorkFlowTask(long, java.util.Map)
	 */
	@Override
	public boolean UpdateWorkFlowTask(long taskid, Map<String, Object> inParams) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#getWorkFlowTasksByInstId(long)
	 */
	@Override
	public List<?> GetWorkFlowTasksByInstId(long instid) {
		return null;
	}

	/* 根据流程编号，查询流程对应的节点信息
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#getWorkFlowNodesByWfCode(long)
	 */
	@Override
	public List<WorkFlowNodeEntity> GetWorkFlowNodesByWfId(long wfid) throws StoreException {
		String checksql = "SELECT count(1) FROM GCS_WORKFLOW_NODE WHERE WFID=? AND STATUS = 1";
		int rownum = jdbcTemplate.queryForInt(checksql, new Object[] {wfid}, new int[]{Types.INTEGER});
		
		if(rownum > 0)
		{
			List<WorkFlowNodeEntity> workflowNodeEntityList = jdbcTemplate.query(WORKFLOW_DEFINE_NODELIST_SQL, new Object[] {wfid}, new int[] {Types.INTEGER}, new WorkFlowNodeEntityRowMapper());
			return workflowNodeEntityList;
		}
		
		throw new StoreException("根据工作流节点编号，获取该节点关联的事件定义列表失败，该流程没有配置流程节点信息");
	}

	/* 根据流程实例编号，获取该流程实例下所有的节点信息列表
	 * 注：在实际过程中，可能会存在工作流中中的一个节点已经删除掉，但是该流程实例可能还会有该节点的信息
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#getWrokFlowNodesByInstId(long)
	 */
	@Override
	public List<?> GetWrokFlowNodesByInstId(long instid) {
		return null;
	}

	/* 根据工作流节点编号，获取该节点关联的事件定义列表
	 * 一个节点可能帮顶多个事件
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#getWorkFlowNodeEventByNodeid(long)
	 */
	@Override
	public List<WorkFlowEventEntity> GetWorkFlowNodeEventByNodeid(long nodeid) {
		List<WorkFlowEventEntity> workflowEventEntityList = jdbcTemplate.query(WORKFLOW_DEFINE_EVENTLIST_SQL, new Object[] {nodeid}, new int[]{Types.INTEGER}, new WorkFlowEventEntityRowMapper());
		return workflowEventEntityList;
	}

	/**
	 *@description：获取实体属性
	 *@common：jdbcTemplate JdbcTemplate WorkFlowStoreOraclePlugin.java
	 *@return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：jdbcTemplate JdbcTemplate WorkFlowStoreOraclePlugin.java
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 *@description：获取实体属性
	 *@common：WORKFLOW_PERMISS_SQL String WorkFlowStoreOraclePlugin.java
	 *@return the wORKFLOW_PERMISS_SQL
	 */
	public String getWORKFLOW_PERMISS_SQL() {
		return WORKFLOW_PERMISS_SQL;
	}

	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：WORKFLOW_PERMISS_SQL String WorkFlowStoreOraclePlugin.java
	 * @param wORKFLOW_PERMISS_SQL the wORKFLOW_PERMISS_SQL to set
	 */
	public void setWORKFLOW_PERMISS_SQL(String wORKFLOW_PERMISS_SQL) {
		WORKFLOW_PERMISS_SQL = wORKFLOW_PERMISS_SQL;
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#getWorkFlowResource(long)
	 */
	@Override
	public List<?> GetWorkFlowResource(long nodeid) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowEntity(long, java.lang.String)
	 */
	@Override
	public WorkFlowEntity GetWorkFlowEntityByWfCode(String wfcode) throws StoreException {
		
		log.debug("获取流程定义描述信息，参数  wfcode=" + wfcode);
	
		if(wfcode == null || wfcode.length() == 0)
		{
			throw new StoreException("获取流程引擎定义发生异常，传入的参数有误 wfcode=null");
		}
		
		String checksql = "SELECT count(1) FROM GCS_WORKFLOW WHERE WFCODE = ?";
		int rownum = jdbcTemplate.queryForInt(checksql, new Object[] {wfcode}, new int[]{Types.VARCHAR});
		
		if(rownum > 0)
		{
		
			WorkFlowEntity workflowEntity = (WorkFlowEntity) jdbcTemplate.queryForObject(WORKFLOW_DEFINE_INFOFORMATION_WFCODE_SQL, new Object[] {wfcode}, new int[]{Types.VARCHAR}, new WorkFlowEntityRowMapper());
			return workflowEntity;
		}
		
		throw new StoreException("获取流程引擎定义发生异常，传入的参数有误 wfcode=null");
	}

	/**
	 * <p>Title: GetWorkFlowInstanceEntity</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowInstanceEntity()
	 */
	@Override
	public WorkFlowInstanceEntity GetWorkFlowInstanceEntity(long instid) throws StoreException{
		String checksql = "SELECT count(1) FROM GCS_WORKFLOW_INSTANCE WHERE INSID=?";
		int rownum = jdbcTemplate.queryForInt(checksql, new Object[] {instid}, new int[]{Types.INTEGER});
		
		if(rownum > 0)
		{
			WorkFlowInstanceEntity workflowInstanceEntity = (WorkFlowInstanceEntity) jdbcTemplate.queryForObject(WORKFLOW_INSTANCE_QUERY_SQL, new Object[] {instid}, new int[]{Types.INTEGER}, new WorkFlowInstanceEntityRowMapper());
			return workflowInstanceEntity;
		}
		
		throw new StoreException("获取流程实例定义发生异常，传入的参数有误 instid=" + instid);
	}

	/**
	 * <p>Title: CreateWorkFlowIntanceNode</p>
	 * <p>Description: 创建流程的实例节点，根据实例节点创建下一个工作流实例任务
	 * 在创建完工作流程实例节点后，先把该节点需要参数创建好，然后根据节点参数创建任务</p>
	 * @param insid      流程实例编号
	 * @param nodeid     流程实例节点编号
	 * @param issplit    流程实例节点是否拆分
	 * @param nodename   流程实例节点名称
	 * @param nodedesc   流程实例节点描述
	 * @param postid     流程实例节点岗位编号
	 * @return 返回创建的流程实例节点记录编号
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#CreateWorkFlowIntanceNode(long, long, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public long CreateWorkFlowIntanceNode(long instid, long nodeid, int issplit,
			String nodename, String nodedesc, String postid) {

		long inid = jdbcTemplate.queryForLong(WORKFLOW_INSTANCENODE_INID_SQL);
		
		if(inid == 0)
		{
			return 0;
		}

		jdbcTemplate.update(WORKFLOW_INSTANCENODE_CREATE_SQL, new Object[] {inid, instid, nodeid, issplit, 0, nodename, nodedesc, postid}, 
				new int[] {Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
		return inid;
	}

	/**
	 * <p>Title: CreateWorkFlowInstanceNodeArgv</p>
	 * <p>Description: 批量添加流程实例任务节点参数，主要使用场景是在工作流自动拆分任务场景中，
	 * 工作流查询该表如果只有一条数据，则不拆分任务，如果有多条数据，则把该节点拆分为多个任务</p>
	 * @param insid    流程实例编号
	 * @param nodeid   流程实例节点编号
	 * @param inparams 流程实例节点参数列表
	 * @return  成功返回 true  失败返回 false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月13日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#CreateWorkFlowInstanceNodeArgv(long, long, java.util.List)
	 */
	@Override
	public boolean CreateWorkFlowInstanceNodeArgv(final List<WorkFlowInstanceNodeArgvEntity> inparams) {
		jdbcTemplate.batchUpdate(WORKFLOW_INSTANCENODEARGV_CREATE_SQL,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						WorkFlowInstanceNodeArgvEntity workflowInstanceNodeArgvEntity = inparams
								.get(i);
						ps.setLong(1, workflowInstanceNodeArgvEntity.getInsid());
						ps.setLong(2, workflowInstanceNodeArgvEntity.getNodeid());
						ps.setLong(3, workflowInstanceNodeArgvEntity.getArgvtype());
						ps.setLong(4, workflowInstanceNodeArgvEntity.getArgvvalue());
					}

					@Override
					public int getBatchSize() {
						return inparams.size();
					}
				});

		return true;
	}

	/**
	 * <p>Title: CreateWorkFlowTask</p>
	 * <p>Description: 创建一个任务节点，</p>
	 * @param instid     流程实例编号
	 * @param nodeid     流程实例当前节点编号
	 * @param preTaskId  上级任务节点编号
	 * @param orgid      调用者组织编号
	 * @param postid     调用者岗位编号
	 * @return
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#CreateWorkFlowTask(long, long, long, long, long)
	 */
	@Override
	public long CreateWorkFlowTask(long instid, long nodeid, long preTaskId,
			long orgid, long postid, long caller) {
		long taskidid = jdbcTemplate.queryForLong(WORKFLOW_INSTANCETASK_TASKID_QUERY_SQL);
		
		if(taskidid == 0)
		{
			return 0;
		}

		jdbcTemplate.update(WORKFLOW_INSTANCETASK_CREATE_SINGLESQL, new Object[] {taskidid, instid, nodeid, postid, orgid, preTaskId, 0, caller}, 
				new int[] {Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER});
		return taskidid;
	}

	/**
	 * <p>Title: GetWorkFlowTaskEntity</p>
	 * <p>Description: 根据任务编号获取任务内容</p>
	 * @param instid   任务编号
	 * @return  WorkFlowTaskEntity 当前对象
	 * @author	lehoons@gmail.com
	 * @throws StoreException 
	 * @date	2013年12月13日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowTaskEntity(long, long)
	 */
	@Override
	public WorkFlowTaskEntity GetWorkFlowTaskEntity(long taskid) throws StoreException {
		log.debug("获取流程实例任务定义信息，参数  wfcode=" + taskid);
		
		if(taskid == 0)
		{
			throw new StoreException("获取流程实例任务定义发生异常，传入的参数有误 taskid=0");
		}
		
		String checksql = "SELECT count(1) FROM GCS_WORKFLOW_TASK WHERE TASKID=?";
		int rownum = jdbcTemplate.queryForInt(checksql, new Object[] {taskid}, new int[]{Types.INTEGER});
		
		if(rownum > 0)
		{
			WorkFlowTaskEntity workflowTaskEntity = (WorkFlowTaskEntity) jdbcTemplate.queryForObject(WORKFLOW_INSTANCETASK_QUERYINFO_SQL, new Object[] {taskid}, new int[]{Types.INTEGER}, new WorkFlowTaskEntityRowMapper());
			return workflowTaskEntity;
		}
		
		throw new StoreException("获取流程实例任务定义发生异常，传入的参数有误 taskid=" + taskid);
	}

	/**
	 * <p>Title: GetWorkFlowInstanceNodeEntityByInId</p>
	 * <p>Description: 根据流程实例节点编号获取该流程实例节点信息</p>
	 * @param inid     流程实例节点记录编号
	 * @return workflowInstanceNodeEntity  流程实例节点信息
	 * @author	lehoons@gmail.com
	 * @date	2013年12月13日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowInstanceNodeEntityByInId(long)
	 */
	@Override
	public WorkFlowInstanceNodeEntity GetWorkFlowInstanceNodeEntityByInId(
			long inid)  throws StoreException {
		log.debug("获取流程实例节点定义信息，参数  inid=" + inid);
		
		String checksql = "SELECT count(1) FROM GCS_WORKFLOW_INSTANCE_NODE WHERE INID = ?";
		int rownum = jdbcTemplate.queryForInt(checksql, new Object[] {inid}, new int[]{Types.INTEGER});
		
		if(rownum > 0)
		{
			WorkFlowInstanceNodeEntity workflowInstanceNodeEntity = jdbcTemplate.queryForObject(WORKFLOW_INSTANCENODE_QUERYBYINID_SQL, new Object[] {inid}, new int[]{Types.INTEGER},  new WorkFlowInstanceNodeEntityRowMapper());
			return workflowInstanceNodeEntity;
		}
		
		throw new StoreException("获取流程实例节点定义信息出错，inid=" + inid);
	}

	/**
	 * <p>Title: GetWorkFlowInstanceNodeArgvEntity</p>
	 * <p>Description: 根据工作流程实例编号和节点编号获取该节点所有的参数</p>
	 * @param instid 流程实例编号
	 * @param nodeid 流程实例节点编号
	 * @return  List<WorkFlowInstanceNodeArgvEntity>
	 * @author	lehoons@gmail.com
	 * @date	2013年12月13日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowInstanceNodeArgvEntity(long, long)
	 */
	@Override
	public List<WorkFlowInstanceNodeArgvEntity> GetWorkFlowInstanceNodeArgvEntity(
			long instid, long nodeid) {
		log.debug("根据工作流程实例编号和节点编号获取该节点所有的参数,instid=" + instid + " nodeid=" + nodeid);
		
		List<WorkFlowInstanceNodeArgvEntity> workflowInstanceNodeArgvEntityList = jdbcTemplate.query(WORKFLOW_INSTANCENODEARGV_QUERY_SQL, new Object[] {instid, nodeid}, new int[]{Types.INTEGER, Types.INTEGER}, new WorkFlowInstanceNodeArgvEntityRowMapper());
		return workflowInstanceNodeArgvEntityList;

	}

	/**
	 * <p>Title: SetWorkFlowInstanceCurrentNode</p>
	 * <p>Description: 设置流程实例当前节点编号</p>
	 * @param instid   流程实例编号
	 * @param nodeid   当前运行节点编号
	 * @return  true 成功， false  失败
	 * @author	lehoons@gmail.com
	 * @date	2013年12月13日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#SetWorkFlowInstanceCurrentNode(long, long)
	 */
	@Override
	public boolean SetWorkFlowInstanceCurrentNode(long instid, long nodeid) {
		int affaceid = jdbcTemplate.update(WORKFLOW_INSTANCE_UPDATE_CURRENTNOID_SQL, new Object[] {nodeid, instid}, new int[] {Types.INTEGER, Types.INTEGER});
		return affaceid == 1;
	}

	/**
	 * <p>Title: GetWorkFlowEntityByWfId</p>
	 * <p>Description: 根据工作流程编号获取流程描述定义</p>
	 * @param  wfid 工作流定义编号
	 * @return WorkFlowEntity
	 * @throws StoreException
	 * @author	lehoons@gmail.com
	 * @date	2013年12月14日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowEntityByWfId(long)
	 */
	@Override
	public WorkFlowEntity GetWorkFlowEntityByWfId(long wfid)
			throws StoreException {
		String checksql = "SELECT count(1) FROM GCS_WORKFLOW WHERE WFID = ?";
		int rownum = jdbcTemplate.queryForInt(checksql, new Object[] {wfid}, new int[] {Types.INTEGER});
		
		if(rownum > 0)
		{
			WorkFlowEntity workflowEntity = jdbcTemplate.queryForObject(WORKFLOW_DEFINE_INFOFORMATION_WFID_SQL, new Object[] {wfid}, new int[] {Types.INTEGER}, new WorkFlowEntityRowMapper());
			return workflowEntity;
		}
		else
		{
			throw new StoreException("获取工作流定义发生异常，工作流定义编号不存在或者数据库错误");
		}		
	}

	/**
	 * <p>Title: GetWorkFlowInstanceNodeEntityListByInstId</p>
	 * <p>Description: </p>
	 * @param instid
	 * @return
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowInstanceNodeEntityListByInstId(long)
	 */
	@Override
	public List<WorkFlowInstanceNodeEntity> GetWorkFlowInstanceNodeEntityListByInstId(
			long instid)  throws StoreException {
		List<WorkFlowInstanceNodeEntity> workflowInstanceNodeEntityList = jdbcTemplate.query(WORKFLOW_INSTANCENODE_QUERY_SQL, new Object[] {instid}, new int[] {Types.INTEGER}, new WorkFlowInstanceNodeEntityRowMapper());
		return workflowInstanceNodeEntityList;
	}

	/**
	 * <p>Title: GetWorkFlowInstanceNodeEventByInstNodeid</p>
	 * <p>Description: 根据流程实例编号和节点编号获取该节点下所有的任务信息</p>
	 * @param instid   流程实例编号
	 * @param nodeid   流程实例节点编号
	 * @return List<WorkFlowTaskEntity>        该流程实例节点下关联的任务列表 
	 * @author	lehoons@gmail.com
	 * @date	2013年12月14日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowInstanceNodeEventByInstNodeid(long, long)
	 */
	@Override
	public List<WorkFlowTaskEntity> GetWorkFlowInstanceNodeEventByInstNodeid(
			long instid, long nodeid) {
		List<WorkFlowTaskEntity> workflowTaskEntityList = jdbcTemplate.query(WORKFLOW_INSTANCETASK_QUERY_BYINSNODEID_SQL, new Object[] {instid, nodeid}, new int[] {Types.INTEGER, Types.INTEGER}, new WorkFlowTaskEntityRowMapper());
		return workflowTaskEntityList;
	}

	/**
	 * <p>Title: ModifyWorkFlowTaskStatus</p>
	 * <p>Description: 修改任务状态</p>
	 * @param instid   流程实例编号
	 * @param taskid   流程任务编号
	 * @param status   流程任务处理结果
	 * @param inParams 流程任务更新参数
	 *                 userid   用户编号
	 *                 message  用户处理信息
	 * @return  成功 true  失败 false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月14日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#ModifyWorkFlowTaskStatus(long, long, int, java.util.Map)
	 */
	@Override
	public boolean ModifyWorkFlowTaskFinishStatus(long instid, long taskid,
			int status, Map<String, Object> inParams) {
		String message = (String) inParams.get("message");
		long	userid =  (long) inParams.get("userid");
		if(message == null)
		{
			message = "任务处理完成";
		}

		int affaceid = jdbcTemplate.update(WORKFLOW_INSTANCETASK_UPDATEFINISH_SQL, new Object[] {userid, message, status, taskid, instid}, new int[] {Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.INTEGER});
		return affaceid == 1;
	}

	/**
	 * <p>Title: GetWorkFlowInstanceEntityJob</p>
	 * <p>Description: 工作流任务处理类查询带处理的工作流实例，查询状态为激活状态的工作流实例</p>
	 * @param   status 工作流实例状态
	 * @return  List<WorkFlowInstanceEntity> 返回工作流实例
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowInstanceEntityJob(int)
	 */
	@Override
	public List<WorkFlowInstanceEntity> GetWorkFlowInstanceEntityListJob(int status) {
			List<WorkFlowInstanceEntity> workflowInstanceEntityList = jdbcTemplate.query(WORKFLOW_JOB_INSTANCE_QUERY_SQL, new Object[] {status}, new int[]{Types.INTEGER}, new WorkFlowInstanceEntityRowMapper());
			return workflowInstanceEntityList;
	}

	/**
	 * <p>Title: GetWorkFlowTaskEntityListByInsNodeId</p>
	 * <p>Description: </p>
	 * @param instid
	 * @param nodeid
	 * @return
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowTaskEntityListByInsNodeId(long, long)
	 */
	@Override
	public List<WorkFlowTaskEntity> GetWorkFlowTaskEntityListByInsNodeId(
			long instid, long nodeid) {
			List<WorkFlowTaskEntity> workflowTaskEntityList = jdbcTemplate.query(WORKFLOW_JOB_INSTANCETASK_QUERY_SQL, new Object[] {instid, nodeid}, new int[]{Types.INTEGER, Types.INTEGER}, new WorkFlowTaskEntityRowMapper());
			return workflowTaskEntityList;
	}

	/**
	 * <p>Title: GetWorkFlowNodeEntityByPreNodeid</p>
	 * <p>Description: 根据流程编号和上一任务节点编号获取下一任务节点信息</p>
	 * @param wfid     工作流程引擎定义斌啊哈哦
	 * @param prenodeid 上一工作流程引擎定义节点编号
	 * @return  下一流程引擎定义节点编号
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#GetWorkFlowNodeEntityByPreNodeid(long, long)
	 */
	@Override
	public WorkFlowNodeEntity GetWorkFlowNodeEntityByPreNodeid(long wfid,
			long prenodeid) {
		String checksql = "SELECT count(1) FROM GCS_WORKFLOW_NODE WHERE WFID=? AND STATUS = 1 AND PRENODEID=? ";
		int rownum = jdbcTemplate.queryForInt(checksql, new Object[] {wfid, prenodeid}, new int[] {Types.INTEGER, Types.INTEGER});
		
		if(rownum == 1)
		{
			WorkFlowNodeEntity workflowNodeEntity = jdbcTemplate.queryForObject(WORKFLOW_JOB_NODE_QUERY_SQL, new Object[] {wfid, prenodeid}, new int[] {Types.INTEGER, Types.INTEGER}, new WorkFlowNodeEntityRowMapper());
			return workflowNodeEntity;
		}
		else
		{
			return null;
		}
	}

	/**
	 * <p>Title: ModifyWorkFlowTaskStatus111</p>
	 * <p>Description: 修改工作流任务状态，主要是提供暂停、恢复、停止状态</p>
	 * @param instid   流程实例编号
	 * @param taskid   流程任务编号
	 * @param status   流程任务处理结果
	 * @param inParams 流程任务更新参数
	 *                 message  用户处理信息
	 * @return  成功 true  失败 false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#ModifyWorkFlowTaskStatus(long, long, int, java.util.Map)
	 */
	@Override
	public boolean ModifyWorkFlowTaskStatus(long instid, final int status, Map<String, Object> inParams) {
		final String message = (String) inParams.get("message");

		//根据流程实例编号查询当前所有的任务
		final List<WorkFlowTaskEntity> workflowTaskEntityList = jdbcTemplate.query(WORKFLOW_INSTANCETASK_QUERYBYINSID_SQL, new Object[] {instid}, new int [] {Types.INTEGER}, new WorkFlowTaskEntityRowMapper());
		
		int affaceid [] = jdbcTemplate.batchUpdate(WORKFLOW_INSTANCETASK_UPDATE_SQL, new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				WorkFlowTaskEntity  workflowTaskEntity = workflowTaskEntityList.get(i);
				ps.setString(1, message);
				ps.setInt(2, status);
				ps.setLong(3, workflowTaskEntity.getTaskid());
			}

			@Override
			public int getBatchSize() {
				return workflowTaskEntityList.size();
			}
			
		});

		return affaceid.length > 1;
	}

	/**
	 * <p>Title: ModifyWorkFlowInstanceCurrentNode</p>
	 * <p>Description: 设置当前流程运行节点编号</p>
	 * @param instid 		流程实例编号
	 * @param currentnodeid 当前节点编号
	 * @return  成功 true  失败 false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#ModifyWorkFlowInstanceCurrentNode(long, long)
	 */
	@Override
	public boolean ModifyWorkFlowInstanceCurrentNode(long instid,
			long currentnodeid) {
		int affactid = jdbcTemplate.update(WORKFLOW_JOB_INSTANCE_CURRENTNODE_SQL, new Object[] {currentnodeid, instid}, new int [] {Types.INTEGER, Types.INTEGER});
		return affactid == 1;
	}

	/**
	 * <p>Title: JOBModifyWorkFlowTaskStatus</p>
	 * <p>Description: 供工作流任务调度模块使用修改工作流任务状态</p>
	 * @param instid   实例编号
	 * @param taskstatus   状态
	 * @param inParams  任务编号参数
	 * @return  成功返回true  失败返回false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月17日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#JOBModifyWorkFlowTaskStatus(long, int, java.util.List)
	 */
	@Override
	public boolean JOBModifyWorkFlowTaskStatus(final long instid, final int taskstatus,
			final List<Long> inParams) {
		
		int affaceid [] = jdbcTemplate.batchUpdate(WORKFLOW_JOB_INSTANCETASK_MODIFYSTATUS_SQL, new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setInt(1, taskstatus);
				ps.setLong(2, inParams.get(i));
				ps.setLong(3, instid);
			}

			@Override
			public int getBatchSize() {
				return inParams.size();
			}
			
		});

		return affaceid.length > 1;
	}

	/**
	 * <p>Title: JOBModifyWorkFlowInstanceNodeStatus</p>
	 * <p>Description: 修改工作流程实例节点运行状态为成功或者失败</p>
	 * @param instid 实例编号
	 * @param nodeid 节点编号
	 * @param status 状态
	 * @return  成功返回true  失败返回false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月17日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#JOBModifyWorkFlowInstanceNodeStatus(long, long, int)
	 */
	@Override
	public boolean JOBModifyWorkFlowInstanceNodeStatus(long instid,
			long nodeid, int status) {
		int affactid = jdbcTemplate.update(WORKFLOW_JOB_INSTANCENODE_MODIFYSTATUS_SQL, new Object[] {status, instid, nodeid}, new int [] {Types.INTEGER, Types.INTEGER, Types.INTEGER});
		return affactid == 1;
	}

	/**
	 * <p>Title: JOBModifyWorkFlowInstanceTaskNextNode</p>
	 * <p>Description: 修改工作流实例当前任务节点的下一节点信息</p>
	 * @param instid   流程实例编号
	 * @param nodeid  当前运行节点编号
	 * @param nextnodeid  下级节点编号
	 * @return true 成功， false  失败
	 * @author	lehoons@gmail.com
	 * @date	2013年12月17日
	 * @see com.coreworkflow.workflow.spi.WorkFlowStoreInterface#JOBModifyWorkFlowInstanceTaskNextNode(long, long, long)
	 */
	@Override
	public boolean JOBModifyWorkFlowInstanceTaskNextNode(long instid,
			long nodeid, long nextnodeid) {
		int affactid = jdbcTemplate.update(WORKFLOW_JOB_INSTANCETASK_MODIFYNEXTNODE_SQL, new Object[] {nextnodeid, instid, nodeid}, new int [] {Types.INTEGER, Types.INTEGER, Types.INTEGER});
		return affactid >= 1;
	}



}
