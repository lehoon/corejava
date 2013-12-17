/**   
 * WorkFlowInstance.java
 * @Package: com.coreworkflow.workflow
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月12日 上午10:23:11  
 */

package com.coreworkflow.workflow;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkFlowInstanceInterface;
import com.coreworkflow.workflow.entity.WorkFlowInstanceEntity;

/**
 * 流程实例包装类，包括流程实例的描述
 * 流程实例所有的节点和节点对应的任务都从该包装类中获取
 * <p>@author：lehoons@gmail.com
 * @date：2013年12月12日 上午10:23:11
 */

public class WorkFlowInstance implements WorkFlowInstanceInterface{

	/**
	 * 流程实例描述详情
	 */
	private WorkFlowInstanceEntity workflowInstanceEntity;
	
	/**
	 * 流程实例已经运行的所有节点列表信息
	 */
	private List<WorkFlowInstanceNode> workflowInstanceNodeList;
	
	/**
	 * 流程实例当前节点
	 */
	private WorkFlowInstanceNode  currentworkflowInstanceNode;
	
	/**
	 * 工作流程实例当前任务节点信息
	 */
	private WorkFlowTask 		 currentworkflowTask;
	
	private static final Log log = LogFactory.getLog(WorkFlowInstance.class);
	/**
	 *@filename WorkFlowInstance.java
	 *<p>@description 构造函数</p>
	 *@date 2013年12月12日
	 */
	public WorkFlowInstance() {
		super();
	}
	
	/**
	 * 
	 * <p>Title: GetWorkFlowInstanceId</p>
	 * <p>Description: 获取实例编号</p>
	 * @return  long
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public long GetWorkFlowInstanceId()
	{
		return workflowInstanceEntity.getInsid();
	}
	
	public long GetWorkFlowInstanceCurrentTaskId()
	{
		return currentworkflowTask.getTaskid();
	}
	
	
	/**
	 * 获取实体属性
	 *@common：workflowInstance WorkFlowInstanceEntity WorkFlowInstance.java
	 *@return the workflowInstance
	 */
	public WorkFlowInstanceEntity getWorkflowInstanceEntity() {
		return workflowInstanceEntity;
	}
	
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：workflowInstance WorkFlowInstanceEntity WorkFlowInstance.java
	 * @param workflowInstance the workflowInstance to set
	 */
	public void setWorkflowInstanceEntity(WorkFlowInstanceEntity workflowInstanceEntity) {
		log.debug("设置当前实例信息");
		this.workflowInstanceEntity = workflowInstanceEntity;
	}
	
	/**
	 * 获取实体属性
	 *@common：workflowInstanceNodeList List<WorkFlowInstanceNode> WorkFlowInstance.java
	 *@return the workflowInstanceNodeList
	 */
	public List<WorkFlowInstanceNode> getWorkflowInstanceNodeList() {
		return workflowInstanceNodeList;
	}
	
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：workflowInstanceNodeList List<WorkFlowInstanceNode> WorkFlowInstance.java
	 * @param workflowInstanceNodeList the workflowInstanceNodeList to set
	 */
	public void setWorkflowInstanceNodeList(
			List<WorkFlowInstanceNode> workflowInstanceNodeList) {
		log.debug("设置当前运行历史节点列表");
		this.workflowInstanceNodeList = workflowInstanceNodeList;
	}
	
	/**
	 * 获取实体属性
	 *@common：currentworkflowInstanceNode WorkFlowInstanceNode WorkFlowInstance.java
	 *@return the currentworkflowInstanceNode
	 */
	public WorkFlowInstanceNode getCurrentworkflowInstanceNode() {
		return currentworkflowInstanceNode;
	}
	
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：currentworkflowInstanceNode WorkFlowInstanceNode WorkFlowInstance.java
	 * @param currentworkflowInstanceNode the currentworkflowInstanceNode to set
	 */
	public void setCurrentworkflowInstanceNode(
			WorkFlowInstanceNode currentworkflowInstanceNode) {
		log.debug("设置当前运行节点");
		this.currentworkflowInstanceNode = currentworkflowInstanceNode;
	}

	/**
	 * @return the currentworkflowTask
	 */
	public WorkFlowTask getCurrentworkflowTask() {
		return currentworkflowTask;
	}

	/**
	 * @param currentworkflowTask the currentworkflowTask to set
	 */
	public void setCurrentworkflowTask(WorkFlowTask currentworkflowTask) {
		this.currentworkflowTask = currentworkflowTask;
	}

	/**
	 * <p>Title: getInstanceState</p>
	 * <p>Description: </p>
	 * @param instid
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#getInstanceState(long)
	 */
	@Override
	public int getInstanceState() {
		return workflowInstanceEntity.getStatus();
	}

	/**
	 * <p>Title: getWorkFlowTask</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#getWorkFlowTask()
	 */
	@Override
	public List<WorkFlowTask> getWorkFlowTask() {
		return workflowInstanceEntity.getWorkflowTaskList();
	}

	/**
	 * <p>Title: CreateWorkFlow</p>
	 * <p>Description: </p>
	 * @param wfcode
	 * @param instname
	 * @param caller
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#CreateWorkFlow(java.lang.String, java.lang.String, long)
	 */
	@Override
	public long CreateWorkFlow(String wfcode, String instname, long caller) {
		return 0;
	}

	/**
	 * <p>Title: CreateWorkFlowFirstTask</p>
	 * <p>Description: </p>
	 * @param inParams
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#CreateWorkFlowFirstTask(java.util.Map)
	 */
	@Override
	public long CreateWorkFlowFirstTask(Map<String, Object> inParams) {
		return 0;
	}

	/**
	 * <p>Title: CreateWorkFlowNextTask</p>
	 * <p>Description: </p>
	 * @param inParams
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#CreateWorkFlowNextTask(java.util.Map)
	 */
	@Override
	public boolean CreateWorkFlowNextTask(Map<String, Object> inParams) {
		return false;
	}

	/**
	 * <p>Title: GetWorkFlow</p>
	 * <p>Description: </p>
	 * @param instid
	 * @param taskid
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#GetWorkFlow(long, long)
	 */
	@Override
	public boolean GetWorkFlow(long instid, long taskid) {
		return false;
	}

	/**
	 * <p>Title: Execute</p>
	 * <p>Description: </p>
	 * @param inParams
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#Execute(java.util.Map)
	 */
	@Override
	public boolean Execute(Map<String, Object> inParams) {
		return false;
	}

	/**
	 * <p>Title: SetCurrentTaskStatus</p>
	 * <p>Description: </p>
	 * @param taskstatus
	 * @param inParams
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#SetCurrentTaskStatus(int, java.util.Map)
	 */
	@Override
	public boolean SetCurrentTaskStatus(int taskstatus,
			Map<String, Object> inParams) {
		return false;
	}

	/**
	 * <p>Title: getInsid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#getInsid()
	 */
	@Override
	public long getInsid() {
		return workflowInstanceEntity.getInsid();
	}

	/**
	 * <p>Title: getInsname</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#getInsname()
	 */
	@Override
	public String getInsname() {
		return workflowInstanceEntity.getInsname();
	}

	/**
	 * <p>Title: getWfid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#getWfid()
	 */
	@Override
	public long getWfid() {
		return workflowInstanceEntity.getWfid();
	}

	/**
	 * <p>Title: getWfname</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#getWfname()
	 */
	@Override
	public String getWfname() {
		return workflowInstanceEntity.getWfname();
	}

	/**
	 * <p>Title: getUserid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#getUserid()
	 */
	@Override
	public long getUserid() {
		return workflowInstanceEntity.getUserid();
	}

	/**
	 * <p>Title: getStatus</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#getStatus()
	 */
	@Override
	public int getStatus() {
		return workflowInstanceEntity.getStatus();
	}

	/**
	 * <p>Title: getCurrentnoidid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceInterface#getCurrentnoidid()
	 */
	@Override
	public long getCurrentnoidid() {
		return workflowInstanceEntity.getCurrentnoidid();
	}
	
}
