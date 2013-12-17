/**   
 * @Title: WorkFlowStore.java
 * @Package: com.coreworkflow.workflow.spi
 * @Description: WorkFlowStore.java
 * @author：lehoons@gmail.com
 * @date：2013年12月5日 下午5:30:14  
 */

package com.coreworkflow.workflow.spi;

import java.util.List;
import java.util.Map;

import com.coreworkflow.workflow.StoreException;
import com.coreworkflow.workflow.entity.WorkFlowEntity;
import com.coreworkflow.workflow.entity.WorkFlowEventEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeArgvEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeEntity;
import com.coreworkflow.workflow.entity.WorkFlowNodeEntity;
import com.coreworkflow.workflow.entity.WorkFlowTaskEntity;

/**
 * <p>工作流程数据源定义</p>
 * <p>@company：wxltsoft</p>
 * @author：<a href="mailto:lehoons@hotmail.com">lehoon</a>
 * @date：2013年12月5日 下午5:30:14
 */

public interface WorkFlowStoreInterface {

	//根据流程定义编码获取工作流程定义SQL
	public final static String WORKFLOW_DEFINE_INFOFORMATION_WFCODE_SQL		= "SELECT WFID, WFCODE, WFNAME, WFCONFIG, WFSN, WFSTATUS,CREATEDATE, MODIFYDATE FROM GCS_WORKFLOW WHERE WFCODE = ?";
	//根据流程定义编号获取工作流程定义SQL
	public final static String WORKFLOW_DEFINE_INFOFORMATION_WFID_SQL		= "SELECT WFID, WFCODE, WFNAME, WFCONFIG, WFSN, WFSTATUS,CREATEDATE, MODIFYDATE FROM GCS_WORKFLOW WHERE WFID = ?";
	//获取工作流程定义节点列表
	public final static String WORKFLOW_DEFINE_NODELIST_SQL					= "SELECT NODEID, WFID, NODENAME, NODEDESC, POSTID, RULECONTEXT, PRENODEID, NEXTNODEID, STATUS, to_char(CREATED, 'yyyy-mm-dd HH24:MI:SS') AS CREATEDATE, TIMEOUT, AUTONODE, XPOINT, YPOINT  FROM GCS_WORKFLOW_NODE WHERE WFID=? AND STATUS = 1 ORDER BY NODEID ASC";
	//获取工作流程定义事件列表信息
	public final static String WORKFLOW_DEFINE_EVENTLIST_SQL				= "SELECT GWE.EVENTID, GWE.EVENTNAME, GWE.EVENTDESC, GWE.EVENTTYPE, GWE.EVENTHANDLE, GWE.EVENTFUNC, GWE.EVENTARGVS FROM GCS_WORKFLOW_EVENT GWE, GCS_WORKFLOW_NODEEVENT GWN WHERE GWE.EVENTID = GWN.EVENTID AND GWN.NODEID = ?  ORDER BY GWN.SEQUENCE ASC";
	
	//获取创建工作流实例编号
	public final static String WORKFLOW_INSTANCE_SEQINSID_SQL				= "SELECT SEQ_GCS_WORKFLOWINSTANCE_INSID.NEXTVAL INSID FROM DUAL";
	//创建工作流实例SQL
	public final static String WORKFLOW_INSTANCE_CREATE_SQL					= "INSERT INTO GCS_WORKFLOW_INSTANCE (INSID, INSNAME, WFID, WFNAME, USERID) VALUES (?, ?, ?, ?, ?)";
	//更新工作流程实例当前节点编号
	public final static String WORKFLOW_INSTANCE_UPDATE_CURRENTNOID_SQL		= "UPDATE GCS_WORKFLOW_INSTANCE SET CURRENTNODEID= ? WHERE INSID= ?";
	//更新工作流程实例状态
	public final static String WORKFLOW_INSTANCE_UPDATE_STATUS_SQL			= "UPDATE GCS_WORKFLOW_INSTANCE SET STATUS= ? WHERE INSID= ?";
	//查询工作流实例详情
	public final static String WORKFLOW_INSTANCE_QUERY_SQL					= "SELECT INSID, INSNAME, WFID, WFNAME, USERID, STATUS, CURRENTNODEID, CREATEDATE FROM GCS_WORKFLOW_INSTANCE WHERE INSID=?";
	
	//工作流程实例任务节点编号查询sql
	public final static String WORKFLOW_INSTANCETASK_TASKID_QUERY_SQL		= "SELECT SEQ_GCS_WORKFLOWTASK_TASKID.NEXTVAL FROM DUAL";
	//批量创建任务节点sql
	public final static String WORKFLOW_INSTANCETASK_CREATE_SINGLESQL		= "INSERT INTO GCS_WORKFLOW_TASK (TASKID, INSID, NODEID, POSTID, ORGID, PRENODEID, NEXTNODEID, USERID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	//批量创建任务节点sql
	public final static String WORKFLOW_INSTANCETASK_CREATE_BATCHSQL		= "INSERT INTO GCS_WORKFLOW_TASK (TASKID, INSID, NODEID, POSTID, ORGID, PRENODEID, NEXTNODEID) VALUES (SEQ_GCS_WORKFLOWTASK_TASKID.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	//根据流程实例编号获取流程实例节点信息sql
	public final static String WORKFLOW_INSTANCENODE_QUERY_SQL				= "SELECT INID, INSID, NODEID, ISSPLIT, STATUS, NODENAME, NODEDESC, POSTID FROM GCS_WORKFLOW_INSTANCE_NODE WHERE INSID=? ORDER BY INID";
	//根据流程实例编号获取任务节点sql
	public final static String WORKFLOW_INSTANCETASK_QUERY_SQL				= "SELECT TASKID, INSID, NODEID, POSTID, ORGID, TASKSTATUS, CREATEDATE, USERID, FINISHDATE, MESSAGE, STATUS, PRENODEID, NEXTNODEID FROM GCS_WORKFLOW_TASK WHERE INSID = ? ORDER BY TASKID ASC";
	
	//根据流程实例编号和节点编号获取任务sql
	public final static String WORKFLOW_INSTANCETASK_QUERY_BYINSNODEID_SQL	= "SELECT TASKID, INSID, NODEID, POSTID, ORGID, TASKSTATUS, CREATEDATE, USERID, FINISHDATE, MESSAGE, STATUS, PRENODEID, NEXTNODEID FROM GCS_WORKFLOW_TASK WHERE INSID = ? AND NODEID = ?ORDER BY TASKID ASC";
	
	//获取工作流实例节点序列编号sql
	public final static String WORKFLOW_INSTANCENODE_INID_SQL				= "SELECT SEQ_GCS_WORKFLOW_INST_NODEID.NEXTVAL FROM DUAL";
	//创建工作流实例节点sql
	public final static String WORKFLOW_INSTANCENODE_CREATE_SQL				= "INSERT INTO GCS_WORKFLOW_INSTANCE_NODE (INID, INSID, NODEID, ISSPLIT, STATUS, NODENAME, NODEDESC, POSTID) VALUES (?,?,?,?,?,?,?,?)";
	//根据工作流实例节点记录编号获取工作流实例节点信息sql
	public final static String WORKFLOW_INSTANCENODE_QUERYBYINID_SQL		= "SELECT INID, INSID, NODEID, ISSPLIT, STATUS, NODENAME, NODEDESC, POSTID FROM GCS_WORKFLOW_INSTANCE_NODE WHERE INID = ?";
	//创建工作流实例节点任务sql
	public final static String WORKFLOW_INSTANCENODETASK_CREATE_SQL			= "INSERT INTO GCS_WORKFLOW_TASK (TASKID, INSID, NODEID, POSTID, ORGID, PRENODEID ) VALUES (SEQ_GCS_WORKFLOWTASK_TASKID.NEXTVAL, ?, ?, ?, ?, ?)";
	//更新任务节点信息,供上层调用修改任务节点状态sql
	public final static String WORKFLOW_INSTANCETASK_UPDATEFINISH_SQL		= "UPDATE GCS_WORKFLOW_TASK SET USERID = ?, FINISHDATE = SYSDATE, MESSAGE=?, TASKSTATUS = 4, STATUS = ? WHERE TASKID = ? AND INSID = ?";
	//创建工作流程实例节点参数sql
	public final static String WORKFLOW_INSTANCENODEARGV_CREATE_SQL			= "INSERT INTO GCS_WORKFLOW_INSTANCE_NODEARGV (INSID, NODEID, ARGVTYPE, ARGVALUE ) VALUES (?, ?, ?, ?)";
	//查询工作流程实例节点参数sql 根据流程实例编号和节点编号
	public final static String WORKFLOW_INSTANCENODEARGV_QUERY_SQL			= "SELECT distinct ARGVALUE, INSID, NODEID, ARGVTYPE FROM GCS_WORKFLOW_INSTANCE_NODEARGV WHERE INSID = ? AND NODEID = ?";
	//根据流程实例和节点编号获取工作已流程实例任务信息sql
	public final static String WORKFLOW_INSTANCETASK_QUERYINFO_SQL			= "SELECT TASKID, INSID, NODEID, POSTID, ORGID, TASKSTATUS, CREATEDATE, USERID, FINISHDATE, MESSAGE, STATUS, PRENODEID, NEXTNODEID FROM GCS_WORKFLOW_TASK WHERE TASKID=?";	
	
	//修改工作流程任务状态sql
	public final static String WORKFLOW_INSTANCETASK_UPDATE_SQL				= "UPDATE GCS_WORKFLOW_TASK SET MESSAGE=?, STATUS = ? WHERE TASKID = ? ";
	//根据流程实例编号查询当前所有任务sql
	public final static String WORKFLOW_INSTANCETASK_QUERYBYINSID_SQL		= "SELECT GWT.TASKID, GWT.INSID, GWT.NODEID, GWT.POSTID, GWT.ORGID, GWT.TASKSTATUS, GWT.CREATEDATE, GWT.USERID, GWT.FINISHDATE, GWT.MESSAGE, GWT.STATUS, GWT.PRENODEID, GWT.NEXTNODEID FROM GCS_WORKFLOW_TASK GWT, GCS_WORKFLOW_INSTANCE_NODE GWIN WHERE GWT.NODEID = GWIN.NODEID AND GWIN.INSID = ?";
	/**
	 * 工作流任务处理类sql定义开始
	 */
	//查询待处理的工作流实例sql
	public final static String WORKFLOW_JOB_INSTANCE_QUERY_SQL				= "SELECT INSID, INSNAME, WFID, WFNAME, USERID, STATUS, CURRENTNODEID, CREATEDATE FROM GCS_WORKFLOW_INSTANCE WHERE STATUS = ?";
	//查询实例当前节点关联的任务列表
	public final static String WORKFLOW_JOB_INSTANCETASK_QUERY_SQL			= "SELECT TASKID, INSID, NODEID, POSTID, ORGID, TASKSTATUS, CREATEDATE, USERID, FINISHDATE, MESSAGE, STATUS, PRENODEID, NEXTNODEID FROM GCS_WORKFLOW_TASK WHERE INSID=? AND NODEID=?";
	//根据流程引擎定义编号和上一节点编号获取下一节点定义信息
	public final static String WORKFLOW_JOB_NODE_QUERY_SQL					= "SELECT NODEID, WFID, NODENAME, NODEDESC, POSTID, RULECONTEXT, PRENODEID, NEXTNODEID, STATUS, to_char(CREATED, 'yyyy-mm-dd HH24:MI:SS') AS CREATEDATE, TIMEOUT, AUTONODE, XPOINT, YPOINT  FROM GCS_WORKFLOW_NODE WHERE WFID=? AND STATUS = 1 AND PRENODEID=?";
	//设置工作流实例当前运行节点
	public final static String WORKFLOW_JOB_INSTANCE_CURRENTNODE_SQL		= "UPDATE GCS_WORKFLOW_INSTANCE SET CURRENTNODEID = ? WHERE INSID = ?";
	//修改任务节点状态
	public final static String WORKFLOW_JOB_INSTANCETASK_MODIFYSTATUS_SQL	= "UPDATE GCS_WORKFLOW_TASK SET FINISHDATE = SYSDATE, TASKSTATUS = ? WHERE TASKID = ? AND INSID = ?";
	//修改流程实例节点状态sql
	public final static String WORKFLOW_JOB_INSTANCENODE_MODIFYSTATUS_SQL	= "UPDATE GCS_WORKFLOW_INSTANCE_NODE SET STATUS = ? WHERE INSID = ? AND NODEID = ?";
	//修改工作流程任务表中下级节点内容sql
	public final static String WORKFLOW_JOB_INSTANCETASK_MODIFYNEXTNODE_SQL	= "UPDATE GCS_WORKFLOW_TASK SET NEXTNODEID=? WHERE INSID=? AND NODEID=?";
	
	/**
	 * <p>对该用户是否有权限创建流程进行鉴权，根据流程配置的第一个节点角色进行判断</p>
	 * <p>鉴权需要配置鉴权sql，在配置文件中进行配置</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param permisid 需要鉴权的标示
	 * @return boolean 成功返回true 失败返回false
	 */
	public boolean CheckPermissions(long permisid);
	/**
	 * <p>创建流程，创建一个流程实例，初始化实例信息，创建一个任务</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param：   wfcode   流程编号
	 * @param：  instname 实例名称
	 * @param：  caller   发起人
	 * @return long     创建的流程实例编号
	 */
	public long CreateWorkFlowInstance(long wfid, String wfname, String instname, long caller);

	/**
	 * <p>修改流程实例的状态</p> 
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param instid
	 * @param status {@link com.coreworkflow.workflow.basic.WorkFlowInterface#WORKFLOW_INSTANCE_STATUS_ACTIVE}
	 * 				{@link com.coreworkflow.workflow.basic.WorkFlowInterface#WORKFLOW_INSTANCE_STATUS_SUSPEND}
	 * 				{@link com.coreworkflow.workflow.basic.WorkFlowInterface#WORKFLOW_INSTANCE_STATUS_DELETE}
	 * 				{@link com.coreworkflow.workflow.basic.WorkFlowInterface#WORKFLOW_INSTANCE_STATUS_FINISH}
	 * @return boolean   成功返回true  失败返回false
	 */
	public boolean ModifyWorkFlowInstanceStatus(long instid, int status);
	
	/**
	 * <p>Title: ModifyWorkFlowInstanceCurrentNode</p>
	 * <p>Description: 设置当前流程运行节点编号</p>
	 * @param instid 		流程实例编号
	 * @param currentnodeid 当前节点编号
	 * @return  成功 true  失败 false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public boolean ModifyWorkFlowInstanceCurrentNode(long instid, long currentnodeid);
	
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
	 */
	public boolean ModifyWorkFlowTaskFinishStatus(long instid, long taskid, int status, Map<String, Object> inParams);
	
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
	 */
	public boolean ModifyWorkFlowTaskStatus(long instid, int status, Map<String, Object> inParams);
	
	/**
	 * 
	 * <p>Title: JOBModifyWorkFlowTaskStatus</p>
	 * <p>Description: 供工作流任务调度模块使用修改工作流任务状态</p>
	 * @param instid   实例编号
	 * @param status   状态
	 * @param inParams  任务编号参数
	 * @return  成功返回true  失败返回false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月17日
	 */
	public boolean JOBModifyWorkFlowTaskStatus(long instid, int taskstatus, List<Long> inParams);
	
	/**
	 * 
	 * <p>Title: JOBModifyWorkFlowInstanceNodeStatus</p>
	 * <p>Description: 修改工作流程实例节点运行状态为成功或者失败</p>
	 * @param instid 实例编号
	 * @param nodeid 节点编号
	 * @param status 状态
	 * @return  成功返回true  失败返回false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月17日
	 */
	public boolean JOBModifyWorkFlowInstanceNodeStatus(long instid, long nodeid, int status);
	
	/**
	 * 
	 * <p>Title: JOBModifyWorkFlowInstanceTaskNextNode</p>
	 * <p>Description: 修改工作流实例当前任务节点的下一节点信息</p>
	 * @param instid   流程实例编号
	 * @param nodeid  当前运行节点编号
	 * @param nextnodeid  下级节点编号
	 * @return true 成功， false  失败
	 * @author	lehoons@gmail.com
	 * @date	2013年12月17日
	 */
	public boolean JOBModifyWorkFlowInstanceTaskNextNode(long instid, long nodeid, long nextnodeid);
	
	
	/**
	 * <p>Title: SetWorkFlowInstanceCurrentNode</p>
	 * <p>Description: 设置流程实例当前节点编号</p>
	 * @param instid   流程实例编号
	 * @param nodeid   当前运行节点编号
	 * @return  true 成功， false  失败
	 * @author	lehoons@gmail.com
	 * @date	2013年12月13日
	 */
	public boolean SetWorkFlowInstanceCurrentNode(long instid, long nodeid);
	
	/**
	 * <p>Title: CreateWorkFlowIntanceNode</p>
	 * <p>Description: 创建一个工作流实例节点</p>
	 * @param insid    工作流实例编号
	 * @param nodeid   工作流实例节点编号
	 * @param issplit  是否拆分
	 * @param nodename 节点名称
	 * @param nodedesc 节点描述
	 * @param postid   岗位编号
	 * @return     新创建的节点记录编号
	 * @author	lehoons@gmail.com
	 * @date	2013年12月12日
	 */
	public long	CreateWorkFlowIntanceNode(long insid, long nodeid, int issplit, String nodename, String nodedesc, String postid);

	/**
	 * <p>Title: GetWorkFlowInstanceNodeEntityByInId</p>
	 * <p>Description: 根据流程实例节点编号获取该流程实例节点信息</p>
	 * @param inid     流程实例节点记录编号
	 * @return WorkFlowInstanceNodeEntity  流程实例节点信息
	 * @author	lehoons@gmail.com
	 * @date	2013年12月13日
	 */
	public WorkFlowInstanceNodeEntity GetWorkFlowInstanceNodeEntityByInId(long inid)  throws StoreException;
	
	/**
	 * <p>Title: GetWorkFlowInstanceNodeEntityByInId</p>
	 * <p>Description: 根据流程实例节点编号获取该流程实例节点信息</p>
	 * @param inid     流程实例节点记录编号
	 * @return List<WorkFlowInstanceNodeEntity>  流程实例节点信息
	 * @author	lehoons@gmail.com
	 * @date	2013年12月13日
	 */
	public List<WorkFlowInstanceNodeEntity> GetWorkFlowInstanceNodeEntityListByInstId(long instid)  throws StoreException;
	
	
	/**
	 * <p>创建一个任务节点</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param instid  流程实例编号
	 * @param nodeid  流程当前节点编号
	 * @param preTaskId 上个任务节点编号，如果没有，则创建流程的第一个节点
	 * @param inParams  任务其他参数，便于扩展
	 * 			orgid   组织编号
	 * 			context 任务描述信息  node节点继承过来
	 * 			caller  任务执行者编号
	 * 			postid	岗位编号
	 * @return long   返回创建的任务节点编号
	 */
	public long CreateWorkFlowTask(long instid, long nodeid, long preTaskId, long orgid, long postid, long caller);
	
	/**
	 * <p>批量创建一个任务节点</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param instid  流程实例编号
	 * @param nodeid  流程当前节点编号
	 * @param preTaskId 上个任务节点编号，如果没有，则创建流程的第一个节点
	 * @param inParams  任务其他参数，便于扩展
	 * 			orgid   组织编号
	 * 			context 任务描述信息  node节点继承过来
	 * 			caller  任务执行者编号
	 * 			postid	岗位编号
	 * @return long   返回创建的任务节点编号
	 */
	public long CreateWorkFlowTaskBatch(long instid, long nodeid, long preTaskId, List<WorkFlowTaskEntity> inParams);

	/**
	 * <p>Title: GetWorkFlowTaskEntity</p>
	 * <p>Description: 根据任务编号获取任务内容</p>
	 * @param taskid   流程实例编号
	 * @return  WorkFlowTaskEntity 当前对象
	 * @author	lehoons@gmail.com
	 * @date	2013年12月13日
	 */
	public WorkFlowTaskEntity GetWorkFlowTaskEntity(long taskid)  throws StoreException;
	
	
	/**
	 * <p>更新任务信息</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param taskid  任务编号
	 * @param inParams 任务信息参数
	 * @return boolean   成功true 失败false
	 */
	public boolean UpdateWorkFlowTask(long taskid, Map<String, Object> inParams) ;
	
	/**
	 * <p>根据流程实例编号获取该流程实例关联的所有任务，在任务表中查询该实例的任务</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param instid
	 * @return List<?>   
	 */
	public List<?> GetWorkFlowTasksByInstId(long instid)  throws StoreException ;
	
	/**
	 * <p>根据流程编码获取流程所有节点信息</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param wfcode
	 * @return List<WorkFlowNodeEntity>   
	 */
	public List<WorkFlowNodeEntity> GetWorkFlowNodesByWfId(long wfid)  throws StoreException ;
	
	/**
	 * <p>根据流程实例编号获取该流程实例所有的节点描述</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param instid 
	 * @return List<?>   
	 */
	public List<?> GetWrokFlowNodesByInstId(long instid)  throws StoreException ;
	
	/**
	 * <p>根据节点编号获取事件信息， {@link com.coreworkflow.workflow.basic.WorkFlowInterface#WORKFLOW_INSTANCE_STATUS_ACTIVE}</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @param nodeid
	 * @return List<?>   
	 */
	public List<WorkFlowEventEntity> GetWorkFlowNodeEventByNodeid(long nodeid)  throws StoreException ;
	
	/**
	 * <p>Title: GetWorkFlowInstanceNodeEventByInstNodeid</p>
	 * <p>Description: 根据流程实例编号和节点编号获取该节点下所有的任务信息</p>
	 * @param instid   流程实例编号
	 * @param nodeid   流程实例节点编号
	 * @return List<WorkFlowTaskEntity>        该流程实例节点下关联的任务列表 
	 * @author	lehoons@gmail.com
	 * @date	2013年12月14日
	 */
	public List<WorkFlowTaskEntity> GetWorkFlowInstanceNodeEventByInstNodeid(long instid, long nodeid);
	
	
	/**
	 * <p>根据任务关联的节点编号获取该节点下所有的资源信息</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月11日
	 * @return
	 * @return List<?>   
	 */
	public List<?> GetWorkFlowResource(long nodeid)  throws StoreException ;
	
	/**
	 * <p>根据工作流程编码获取流程描述定义</p>
	 * <p>lehoons@gmail.com</p>
	 * @date:2013年12月11日
	 * @param wfcode  流程定义编码
	 * @return  WorkFlowEntity
	 */
	public WorkFlowEntity GetWorkFlowEntityByWfCode(String wfcode) throws StoreException;
	
	/**
	 * <p>Title: GetWorkFlowEntityByWfId</p>
	 * <p>Description: 根据工作流程编号获取流程描述定义</p>
	 * @param  wfid 工作流定义编号
	 * @return WorkFlowEntity
	 * @throws StoreException
	 * @author	lehoons@gmail.com
	 * @date	2013年12月14日
	 */
	public WorkFlowEntity GetWorkFlowEntityByWfId(long wfid) throws StoreException;
	
	
	/**
	 * <p>Title: CreateWorkFlowInstance</p>
	 * <p>Description: 获取一个工作流实例详情</p>
	 * @param   long instid  流程实例编号
	 * @return  WorkFlowInstanceEntity
	 * @author	lehoons@gmail.com
	 * @date	2013年12月12日
	 */
	public WorkFlowInstanceEntity GetWorkFlowInstanceEntity(long instid)  throws StoreException ;
	
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
	 */
	public boolean	CreateWorkFlowInstanceNodeArgv(final List<WorkFlowInstanceNodeArgvEntity> inparams) ;
	
	/**
	 * <p>Title: GetWorkFlowInstanceNodeArgvEntity</p>
	 * <p>Description: 根据工作流程实例编号和节点编号获取该节点所有的参数</p>
	 * @param instid 流程实例编号
	 * @param nodeid 流程实例节点编号
	 * @return  List<WorkFlowInstanceNodeArgvEntity>
	 * @author	lehoons@gmail.com
	 * @date	2013年12月13日
	 */
	public List<WorkFlowInstanceNodeArgvEntity> GetWorkFlowInstanceNodeArgvEntity(long instid, long nodeid);
	
	/**
	 * <p>Title: GetWorkFlowInstanceEntityJob</p>
	 * <p>Description: 工作流任务处理类查询带处理的工作流实例，查询状态为激活状态的工作流实例</p>
	 * @param   status 工作流实例状态
	 * @return  List<WorkFlowInstanceEntity> 返回工作流实例
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public List<WorkFlowInstanceEntity> GetWorkFlowInstanceEntityListJob(int status);
	
	/**
	 * <p>Title: GetWorkFlowTaskEntityListByInsNodeId</p>
	 * <p>Description: 根据实例编号和节点编号获取该实例当前任务列表</p>
	 * @param instid   任务编号
	 * @param taskid   实例编号
	 * @return  任务列表
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public List<WorkFlowTaskEntity> GetWorkFlowTaskEntityListByInsNodeId(long instid, long taskid);
	
	/**
	 * <p>Title: GetWorkFlowNodeEntityByPreNodeid</p>
	 * <p>Description: 根据流程编号和上一任务节点编号获取下一任务节点信息</p>
	 * @param wfid     工作流程引擎定义斌啊哈哦
	 * @param prenodeid 上一工作流程引擎定义节点编号
	 * @return  下一流程引擎定义节点编号
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public WorkFlowNodeEntity	GetWorkFlowNodeEntityByPreNodeid(long wfid, long prenodeid);
	
	
}
