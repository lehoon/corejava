/**   
 * @Title: WorkFlowJoinFunctionProvider.java
 * @Package: com.coreworkflow.workflow.function
 * @Description: WorkFlowJoinFunctionProvider.java
 * @author：lehoons@gmail.com
 * @date：2013年12月5日 下午4:34:49  
 */

package com.coreworkflow.workflow.function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.WorkflowException;
import com.coreworkflow.workflow.basic.WorkFlowFunctionInterface;

/**
 * @description：流程合并函数
 * @company：wxltsoft
 * @author：lehoons@gmail.com
 * @date：2013年12月5日 下午4:34:49
 */

public class WorkFlowJoinFunctionProvider implements WorkFlowFunctionInterface {

	private static final Log log = LogFactory.getLog(WorkFlowJoinFunctionProvider.class);
	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkFlowFunctionInterface#execute()
	 */
	@Override
	public void execute() throws WorkflowException {
		log.debug("");
	}

}
