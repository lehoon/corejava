/**   
 * WorkFlowInstanceNode.java
 * @Package: com.coreworkflow.workflow
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月12日 上午10:19:10  
 */

package com.coreworkflow.workflow;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkFlowInstanceNodeInterface;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeEntity;

/**
 * <p>流程实例节点包装类，封装了流程实例的节点描述
 *    包括已经运行的所有节点和当前节点，以及所有节点的状态
 *    流程实例节点封装了节点对应的任务列表和详情
 *    获取流程实例任务节点详情，应该在该包装类中获取到</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月12日 上午10:19:10
 */

public class WorkFlowInstanceNode implements WorkFlowInstanceNodeInterface{

	/**
	 * 流程实例节点定义描述
	 */
	private WorkFlowInstanceNodeEntity		workflowInstanceNode;

	/**
	 * 工作流实例任务信息描述包装类
	 * 一个工作流实例中通过工作流节点定义关联一系列的工作流任务描述
	 */
	private List<WorkFlowTask>				workflowTaskList;
	
	/**
	 * 该实例节点下关联的参数信息
	 */
	private List<WorkFlowInstanceNodeArgv> workflowInstanceNodeArgvList;
	
	
	private static final Log log = LogFactory.getLog(WorkFlowInstanceNode.class);
	
	/**
	 *@filename WorkFlowInstanceNode.java
	 *<p>@description 构造函数</p>
	 *@date 2013年12月12日
	 */
	public WorkFlowInstanceNode() {
		super();
	}

	/**
	 * 获取实体属性
	 *@common：workflowInstanceNode WorkFlowInstanceNodeEntity WorkFlowInstanceNode.java
	 *@return the workflowInstanceNode
	 */
	public WorkFlowInstanceNodeEntity getWorkflowInstanceNode() {
		return workflowInstanceNode;
	}

	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：workflowInstanceNode WorkFlowInstanceNodeEntity WorkFlowInstanceNode.java
	 * @param workflowInstanceNode the workflowInstanceNode to set
	 */
	public void setWorkflowInstanceNode(
			WorkFlowInstanceNodeEntity workflowInstanceNode) {
		this.workflowInstanceNode = workflowInstanceNode;
	}

	/**
	 * 获取实体属性
	 *@common：workflowTaskList List<WorkFlowTask> WorkFlowInstanceNode.java
	 *@return the workflowTaskList
	 */
	public List<WorkFlowTask> getWorkflowTaskList() {
		return workflowTaskList;
	}

	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：workflowTaskList List<WorkFlowTask> WorkFlowInstanceNode.java
	 * @param workflowTaskList the workflowTaskList to set
	 */
	public void setWorkflowTaskList(List<WorkFlowTask> workflowTaskList) {
		if(workflowTaskList != null)
		{
			log.debug("workflowTaskList.size=" + workflowTaskList.size());
		}
		this.workflowTaskList = workflowTaskList;
	}

	/**
	 * @return the workflowInstanceNodeArgvList
	 */
	public List<WorkFlowInstanceNodeArgv> getWorkflowInstanceNodeArgvList() {
		return workflowInstanceNodeArgvList;
	}

	/**
	 * @param workflowInstanceNodeArgvList the workflowInstanceNodeArgvList to set
	 */
	public void setWorkflowInstanceNodeArgvList(
			List<WorkFlowInstanceNodeArgv> workflowInstanceNodeArgvList) {
		this.workflowInstanceNodeArgvList = workflowInstanceNodeArgvList;
	}

	/**
	 * <p>Title: getInid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeInterface#getInid()
	 */
	@Override
	public long getInid() {
		return workflowInstanceNode.getInid();
	}

	/**
	 * <p>Title: getInsid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeInterface#getInsid()
	 */
	@Override
	public long getInsid() {
		return workflowInstanceNode.getInsid();
	}

	/**
	 * <p>Title: getNodeid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeInterface#getNodeid()
	 */
	@Override
	public long getNodeid() {
		return workflowInstanceNode.getNodeid();
	}

	/**
	 * <p>Title: getIssplit</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeInterface#getIssplit()
	 */
	@Override
	public int getIssplit() {
		return workflowInstanceNode.getIssplit();
	}

	/**
	 * <p>Title: getStatus</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeInterface#getStatus()
	 */
	@Override
	public int getStatus() {
		return workflowInstanceNode.getStatus();
	}

	/**
	 * <p>Title: getNodename</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeInterface#getNodename()
	 */
	@Override
	public String getNodename() {
		return workflowInstanceNode.getNodename();
	}

	/**
	 * <p>Title: getNodedesc</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeInterface#getNodedesc()
	 */
	@Override
	public String getNodedesc() {
		return workflowInstanceNode.getNodedesc();
	}

	/**
	 * <p>Title: getPostid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeInterface#getPostid()
	 */
	@Override
	public String getPostid() {
		return workflowInstanceNode.getPostid();
	}
	
}
