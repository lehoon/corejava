/**   
 * @Title: WorkFlowTask.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: WorkFlowTask.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午7:33:52  
 */

package com.coreworkflow.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkFlowTaskInterface;
import com.coreworkflow.workflow.entity.WorkFlowTaskEntity;

/**
 * <p>@description：</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午7:33:52
 */

public class WorkFlowTask implements WorkFlowTaskInterface{
	
	/**
	 * 工作流程任务节点定义
	 */
	private WorkFlowTaskEntity workflowTaskEntity;

	private static final Log log = LogFactory.getLog(WorkFlowTask.class);

	/**
	 * <p>Title: getTaskEntryCaller</p>
	 * <p>Description: 获取任务调用者</p>
	 * @return      long
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public long getTaskEntryCaller() {
		log.debug("获取任务调用者");
		return workflowTaskEntity.getUserid();
	}

	/**
	 * <p>Title: setTaskEntryCaller</p>
	 * <p>Description: </p>
	 * @param caller
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public void setTaskEntryCaller(long caller)
	{
		workflowTaskEntity.setUserid(caller);
	}

	/**
	 * <p>Title: setTaskEntryStatus</p>
	 * <p>Description: </p>
	 * @param status
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public void setTaskEntryStatus(int status)
	{
		workflowTaskEntity.setStatus(status);
	}
	
	/**
	 * <p>Title: getWorkFlowInstanceId</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public long getWorkFlowInstanceId() {
		return workflowTaskEntity.getInsid();
	}


	/**
	 * <p>Title: getStartDate</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public String getStartDate() {
		return workflowTaskEntity.getCreatedate();
	}

	/**
	 * <p>Title: getFinishDate</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public String getFinishDate() {
		return workflowTaskEntity.getFinishdate();
	}

	/**
	 * 
	 * <p>Title: getTaskid</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public long getTaskid() {
		return workflowTaskEntity.getTaskid();
	}

	/**
	 * 
	 * <p>Title: getPostid</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public long getPostid() {
		return workflowTaskEntity.getPostid();
	}

	/**
	 * 
	 * <p>Title: getOrgId</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public long getOrgId() {
		return workflowTaskEntity.getOrgid();
	}

	/**
	 * 
	 * <p>Title: getPreviousTaskId</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public long getPreviousNodeId() {
		return workflowTaskEntity.getPrenodeid();
	}

	/**
	 * 
	 * <p>Title: getNextTaskId</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public long getNextNodeId() {
		return workflowTaskEntity.getNextnodeid();
	}

	/**
	 * 
	 * <p>Title: getTaskStatus</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public int getTaskStatus() {
		return workflowTaskEntity.getTaskstatus();
	}

	/**
	 * 
	 * <p>Title: getNodeId</p>
	 * <p>Description: </p>
	 * @return
	 * <p>@author	lehoons@gmail.com</p>
	 * @date	2013年12月14日
	 */
	public long getNodeId() {
		return workflowTaskEntity.getNodeid();
	}

	/**
	 *<p>@description：获取实体属性</p>
	 *@common：workflowTask WorkFlowTaskEntity WorkFlowTask.java
	 *@return the workflowTask
	 */
	public WorkFlowTaskEntity getWorkflowTaskEntity() {
		return workflowTaskEntity;
	}

	/**
	 * <p>@description： 设置实体类属性值</p>
	 * @             提供方法注入手段
	 * @common：workflowTask WorkFlowTaskEntity WorkFlowTask.java
	 * @param workflowTask the workflowTask to set
	 */
	public void setWorkflowTaskEntity(WorkFlowTaskEntity workflowTaskEntity) {
		this.workflowTaskEntity = workflowTaskEntity;
	}

	/**
	 * <p>Title: getWorkFlowNode</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowTaskInterface#getWorkFlowNode()
	 */
	@Override
	public WorkFlowNode getWorkFlowNode() {
		return null;
	}

	/**
	 * <p>Title: getId</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowTaskInterface#getId()
	 */
	@Override
	public long getId() {
		return 0;
	}

	/**
	 * <p>Title: getMessage</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowTaskInterface#getMessage()
	 */
	@Override
	public String getMessage() {
		return workflowTaskEntity.getMessage();
	}

	/**
	 * <p>Title: getInsid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowTaskInterface#getInsid()
	 */
	@Override
	public long getInsid() {
		return workflowTaskEntity.getInsid();
	}

}
