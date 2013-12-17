/**   
 * @Title: WorkFlowSplitFunctionProvider.java
 * @Package: com.coreworkflow.workflow.function
 * @Description: WorkFlowSplitFunctionProvider.java
 * @author：lehoons@gmail.com
 * @date：2013年12月5日 下午4:33:28  
 */

package com.coreworkflow.workflow.function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.WorkflowException;
import com.coreworkflow.workflow.basic.WorkFlowFunctionInterface;

/**
 * @description：在event中配置的拆分函数
 * @company：wxltsoft
 * @author：lehoons@gmail.com
 * @date：2013年12月5日 下午4:33:28
 */

public class WorkFlowSplitFunctionProvider implements WorkFlowFunctionInterface {

	private static final Log log = LogFactory.getLog(WorkFlowSplitFunctionProvider.class);
	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkFlowFunctionInterface#execute()
	 */
	@Override
	public void execute() throws WorkflowException {
		log.debug("");
	}

}
