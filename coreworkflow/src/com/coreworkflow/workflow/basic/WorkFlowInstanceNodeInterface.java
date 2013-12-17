/**   
 * WorkFlowInstanceNodeInterface.java
 * @Package: com.coreworkflow.workflow.basic
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月12日 下午2:03:17  
 */

package com.coreworkflow.workflow.basic;

import java.util.List;

import com.coreworkflow.workflow.WorkFlowTask;

/**
 * <p>流程实例节点接口定义;主要有创建任务操作执行当前任务操作</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月12日 下午2:03:17
 */

public interface WorkFlowInstanceNodeInterface {

	/**
	 * 
	 * <p>Title: getInid</p>
	 * <p>Description: 获取流程实例节点编号</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getInid();

	/**
	 * 
	 * <p>Title: getInsid</p>
	 * <p>Description: 获取流程实例编号</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getInsid();

	/**
	 * 
	 * <p>Title: getNodeid</p>
	 * <p>Description: 获取流程实例节点编号</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getNodeid();

	/**
	 * 
	 * <p>Title: getIssplit</p>
	 * <p>Description: 获取流程实例节点是否拆分标志</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public int getIssplit();
	/**
	 * 
	 * <p>Title: getStatus</p>
	 * <p>Description: 获取流程实例节点状态</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public int getStatus();

	/**
	 * 
	 * <p>Title: getWorkflowTaskList</p>
	 * <p>Description: 获取流程实例节点关联的任务列表</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public List<WorkFlowTask> getWorkflowTaskList();
	/**
	 * 
	 * <p>Title: getNodename</p>
	 * <p>Description: 获取流程实例节点名称</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getNodename();
	/**
	 * 
	 * <p>Title: getNodedesc</p>
	 * <p>Description: 获取流程实例节点描述</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getNodedesc();
	/**
	 * 
	 * <p>Title: getPostid</p>
	 * <p>Description: </p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getPostid();
	
}
