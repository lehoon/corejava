/**   
 * @Title: WorkFlow.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: WorkFlow.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月6日 下午4:27:47  
 */

package com.coreworkflow.workflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkFlowInterface;
import com.coreworkflow.workflow.basic.WorkFlowTaskInterface;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeArgvEntity;
import com.coreworkflow.workflow.spi.WorkFlowStoreInterface;

/**
 * 具体一个工作流
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月6日 下午4:27:47
 */

public class WorkFlow extends AbstractWorkFlow implements WorkFlowInterface{

	/**
	 * 流程定义对应的节点列表
	 */
	private List<WorkFlowNode> workflowNodeList;
	
	/**
	 * 流程实例描述
	 */
	private WorkFlowInstance workflowInstance;
	
	/**
	 * 流程持久化存储实现
	 */
	protected WorkFlowStoreInterface workflowStroe;
	
	private static final Log log = LogFactory.getLog(WorkFlow.class);

	/**
	 *@filename WorkFlow.java
	 *<p>@description 构造函数</p>
	 *@date 2013年12月11日
	 */
	public WorkFlow() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#getInstanceState(long)
	 */
	@Override
	public int getInstanceState() {
		log.debug("");
		return workflowInstance.getStatus();
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#getWorkFlowTask()
	 */
	@Override
	public List<WorkFlowTask> getWorkFlowTask() {
		return workflowInstance.getWorkFlowTask();
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#CreateWorkFlowFirstTask(java.util.Map)
	 */
	@Override
	public long CreateWorkFlowFirstTask(Map<String, Object> inParams) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#Execute(java.util.Map)
	 */
	@Override
	public boolean Execute(Map<String, Object> inParams) {
		return false;
	}

	/* 设置当前任务状态
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#SetCurrentTaskStatus(int, java.util.Map)
	 */
	@Override
	public boolean SetCurrentTaskStatus(int status,
			Map<String, Object> inParams) {
		
		if(workflowInstance.getStatus() != WorkFlowTaskInterface.WORKFLOW_TASKSTATUS_STATUS_ACTIVE)
		{
			log.error("流程处于非激活状态，不允许进行操作，流程状态：" + workflowInstance.getStatus());
			return false;
		}
		return workflowStroe.ModifyWorkFlowTaskFinishStatus(workflowInstance.GetWorkFlowInstanceId(), workflowInstance.GetWorkFlowInstanceCurrentTaskId(), status, inParams);
	}

	
	/**
	 *<p>@description：获取实体属性</p>
	 *@common：workflowInstace WorkFlowInstanceEntity WorkFlow.java
	 *@return the workflowInstace
	 */
	public WorkFlowInstance getWorkflowInstance() {
		return workflowInstance;
	}

	/**
	 * <p>@description： 设置实体类属性值</p>
	 * @             提供方法注入手段
	 * @common：workflowInstace WorkFlowInstanceEntity WorkFlow.java
	 * @param workflowInstace the workflowInstace to set
	 */
	public void setWorkflowInstance(WorkFlowInstance workflowInstance) {
		this.workflowInstance = workflowInstance;
	}

	/**
	 * 获取实体属性
	 *@common：workflowStroe WorkFlowStoreInterface WorkFlow.java
	 *@return the workflowStroe
	 */
	public WorkFlowStoreInterface getWorkflowStroe() {
		return workflowStroe;
	}

	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：workflowStroe WorkFlowStoreInterface WorkFlow.java
	 * @param workflowStroe the workflowStroe to set
	 */
	public void setWorkflowStroe(WorkFlowStoreInterface workflowStroe) {
		this.workflowStroe = workflowStroe;
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#GetWorkFlowInstaceNodeList()
	 */
	@Override
	public List<WorkFlowInstanceNode> GetWorkFlowInstaceNodeList() {
		return null;
	}

	/**
	 * @return the workflowNode
	 */
	public List<WorkFlowNode> getWorkflowNodeList() {
		return workflowNodeList;
	}

	/**
	 * @param workflowNode the workflowNode to set
	 */
	public void setWorkflowNodeList(List<WorkFlowNode> workflowNodeList) {
		this.workflowNodeList = workflowNodeList;
	}

	/**
	 * <p>Title: SetWorkFlowInstanceNextNodeTaskArgv</p>
	 * <p>Description: 设置流程下一节点需要的参数，类型是Map<Integer, List<Long>>，key是参数类型，1为部门编号，其余保留带扩展
	 * 组织的时候，需要按照该格式组织好，然后工作流给创建下一个流程实例节点需要的参数，保存到数据库，然后工作流引起依次为依据创建下一个节点的任务</p>
	 * @param inParams
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#SetWorkFlowInstanceNextNodeTaskArgv(java.util.Map)
	 */
	@Override
	public void SetWorkFlowNextNodeTaskArgv(
			Map<Integer, List<Long>> inParams) {
		
		if(inParams.isEmpty())
		{
			log.error("传递参数为空，直接返回");
			return;
		}

		//当前实例编号
		long  instid = workflowInstance.getInsid();
		//当前实例下一节点编号
		long nextnodeid = FindWorkFlowNextNodeId();
		
		if(nextnodeid == 0)
		{
			log.error("查找流程下一节点失败");
			return;
		}
		
		//组织下一节点参数信息
		List<WorkFlowInstanceNodeArgvEntity> workflowInstNodeArgvList = new ArrayList<WorkFlowInstanceNodeArgvEntity>();
		
		for (int key : inParams.keySet())
		{
			List<Long> valueList = inParams.get(key);
			
			for(long inParamValue : valueList)
			{
				WorkFlowInstanceNodeArgvEntity workflowInstanceNodeArgvEntity = new WorkFlowInstanceNodeArgvEntity(instid, nextnodeid, key, inParamValue);
				workflowInstNodeArgvList.add(workflowInstanceNodeArgvEntity);
			}
		}
		
		log.debug("添加流程实例下一节点参数开始");
		workflowStroe.CreateWorkFlowInstanceNodeArgv(workflowInstNodeArgvList);
		log.debug("添加流程实例下一节点参数结束");
		return ;
	}


	/**
	 * <p>Title: FindWorkFlowNextNodeId</p>
	 * <p>Description: 查找当前流程实例下一节点编号</p>
	 * @return  查找当前流程实例下一节点编号
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	private long FindWorkFlowNextNodeId()
	{
		long  currentnodeid = workflowInstance.getCurrentnoidid();
		
		for(WorkFlowNode workflowNode : workflowNodeList)
		{
			if(workflowNode.getPrenodeid() == currentnodeid)
			{
				return workflowNode.getNodeid();
			}
		}
		
		return 0;
	}

	/**
	 * <p>Title: GetWorkFlowId</p>
	 * <p>Description: 返回当前工作流程实例编号</p>
	 * @return  long 
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#GetWorkFlowInstanceId()
	 */
	@Override
	public long GetWorkFlowInstanceId() {
		return workflowInstance.getInsid();
	}

	/**
	 * <p>Title: GetWorkFlowInstanceName</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#GetWorkFlowInstanceName()
	 */
	@Override
	public String GetWorkFlowInstanceName() {
		return workflowInstance.getInsname();
	}

	/**
	 * <p>Title: SuspendWorkFlow</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#SuspendWorkFlow()
	 */
	@Override
	public boolean SuspendWorkFlow() {
		//修改工作流程状态为暂停
		boolean suspend = workflowStroe.ModifyWorkFlowInstanceStatus(workflowInstance.getInsid(), WORKFLOW_INSTANCE_STATUS_SUSPEND);
		
		if(suspend)
		{
			//修改关联的任务状态为暂停
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("message", "暂停流程任务");
			suspend = workflowStroe.ModifyWorkFlowTaskStatus(workflowInstance.getInsid(), WORKFLOW_INSTANCE_STATUS_SUSPEND, inParams);
		}
		return suspend;
	}

	/**
	 * <p>Title: ResumeWorkFlow</p>
	 * <p>Description: 恢复该流程为激活状态</p>
	 * @return  成功返回true  失败返回false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#ResumeWorkFlow()
	 */
	@Override
	public boolean ResumeWorkFlow() {
		
		if(workflowInstance.getStatus() == WorkFlowTaskInterface.WORKFLOW_TASKSTATUS_STATUS_STOP)
		{
			log.error("流程处于停止状态，不允许进行恢复操作");
			return false;
		}
		
		//修改工作流程状态为恢复
		boolean suspend = workflowStroe.ModifyWorkFlowInstanceStatus(workflowInstance.getInsid(), WORKFLOW_INSTANCE_STATUS_ACTIVE);
		
		if(suspend)
		{
			//修改关联的任务状态为恢复
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("message", "恢复流程任务");
			suspend = workflowStroe.ModifyWorkFlowTaskStatus(workflowInstance.getInsid(), WORKFLOW_INSTANCE_STATUS_ACTIVE, inParams);
		}
		return suspend;
	}

	/**
	 * <p>Title: StopWorkFlow</p>
	 * <p>Description: 停止工作流程</p>
	 * @return  成功返回true  失败返回false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 * @see com.coreworkflow.workflow.basic.WorkFlowInterface#StopWorkFlow()
	 */
	@Override
	public boolean StopWorkFlow() {
		//修改工作流程状态为停止
		boolean suspend = workflowStroe.ModifyWorkFlowInstanceStatus(workflowInstance.getInsid(), WORKFLOW_INSTANCE_STATUS_STOP);
		
		if(suspend)
		{
			//修改关联的任务状态为停止
			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put("message", "停止流程任务");
			suspend = workflowStroe.ModifyWorkFlowTaskStatus(workflowInstance.getInsid(), WORKFLOW_INSTANCE_STATUS_STOP, inParams);
		}
		return suspend;
	}
	


}
