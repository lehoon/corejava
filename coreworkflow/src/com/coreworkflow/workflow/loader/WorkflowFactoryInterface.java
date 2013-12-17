/**   
 * @Title: WorkflowFactoryInterface.java
 * @Package: com.coreworkflow.workflow.loader
 * @Description: WorkflowFactoryInterface.java
 * @author：lehoons@gmail.com
 * @date：2013年12月5日 下午7:06:06  
 */

package com.coreworkflow.workflow.loader;

import com.coreworkflow.workflow.WorkFlow;
import com.coreworkflow.workflow.WorkFlowFactoryException;


/**
 * @description：
 * @company：wxltsoft
 * @author：lehoons@gmail.com
 * @date：2013年12月5日 下午7:06:06
 */

public interface WorkflowFactoryInterface {

	/**
	 * <p>@description: 返回流程实例名称</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @argv：   
	 * @return String   
	 * @throws
	 */
	public String getName();

	/**
	 * <p>@description:创建一个工作流实例 </p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @param   workflowCode 工作流定义编码
	 * @param	instname     实例名称
	 * @param	caller		 工作流调用者编号
	 * @param	orgid		 工作流调用者岗位编号
	 * @param	callpostid	 工作流调用者岗位编号
	 * @return AbstractWorkFlow   
	 * @throws WorkFlowFactoryException
	 */
	public WorkFlow createWorkflow(String workflowCode, String instname, long caller, long orgid, long callpostid) throws WorkFlowFactoryException;

	/**
	 * <p>@description: 重命名工作流实例</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @argv：   
	 * @return void   
	 * @throws
	 */
	public void renameWorkflow(String oldName, String newName);

	/**
	 * <p>@description: 根据指定的工作流编号获取工作流对象</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @param instanceid 工作流实例编号
	 * @param taskid     工作流任务编号
	 * @return AbstractWorkFlow   
	 * @throws WorkFlowFactoryException
	 */
	public WorkFlow getWorkflow(long instanceid, long taskid) throws WorkFlowFactoryException;
}
