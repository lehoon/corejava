/**   
 * <p>@Title: WorkflowContextInterface.java</p>
 * <p>@Package: com.coreworkflow.workflow</p>
 * <p>@description: WorkflowContextInterface.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * <p>@date：2013年12月5日 下午4:44:28  </p>
 */

package com.coreworkflow.workflow.basic;

import java.io.Serializable;

import com.coreworkflow.workflow.WorkFlowNode;

/**
 * <p>@description：工作流上下文接口</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * <p>@date：2013年12月5日 下午4:44:28</p>
 */

public interface WorkflowContextInterface extends Serializable {

	/**
	 * <p>@description: 返回当前调用流程的用户编号 </p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @argv：     
	 * @return long   
	 * @throws
	 */
	public long getCaller(); 

	/**
	 * <p>@description:获取当前节点 </p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @argv：   
	 * @return WorkFlowBaseNode   
	 * @throws
	 */
	public WorkFlowNode getCurrentWorkFlowNode();

	/**
	 * <p>@description:  Sets the current transaction to be rolled back.</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @argv：     
	 * @return void   
	 * @throws
	 */
    public void setRollbackOnly();
}
