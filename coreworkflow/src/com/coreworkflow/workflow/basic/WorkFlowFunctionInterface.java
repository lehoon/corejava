/**   
 * <p>@Title: WorkFlowFunctionInterface.java</p>
 * <p>@Package: com.coreworkflow.workflow.basic</p>
 * <p>@Description: WorkFlowFunctionInterface.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * <p>@date：2013年12月5日 下午4:27:00  </p>
 */

package com.coreworkflow.workflow.basic;

import com.coreworkflow.workflow.WorkflowException;


/**
 * <p>@description：工作流函数接口定义</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * <p>@date：2013年12月5日 下午4:27:00</p>
 */

public interface WorkFlowFunctionInterface {

	/**
	 * <p>@description: Execute this function</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @argv： @throws WorkflowException    
	 * @return void   
	 * @throws
	 */
	public void execute() throws WorkflowException;
}
