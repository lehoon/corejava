/**   
 * @Title: WorkFlowContext.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: WorkFlowContext.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午4:52:54  
 */

package com.coreworkflow.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkflowContextInterface;

/**
 * <p>@description：工作流上下文对象</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午4:52:54
 */

public class WorkFlowContext implements WorkflowContextInterface {

	/**
	 * @Fields serialVersionUID : serialVersionUID
	 */

	/**
	 * the workflow current caller
	 */
	private long caller = 0;
	
	
	
	private static final long serialVersionUID = 762060499650207390L;
	private static final Log log = LogFactory.getLog(WorkFlowContext.class);

	/**
	 *@filename：WorkFlowContext.java
	 *<p>@description：构造函数</p>
	 *@date：2013年12月5日
	 *@tags：@param caller
	 */
	public WorkFlowContext(long caller) {
		super();
		this.caller = caller;
		log.debug("流程上下文调用对象" + caller);
	}

	/**
	 *@filename：WorkFlowContext.java
	 *<p>@description：构造函数</p>
	 *@date：2013年12月5日
	 *@tags：
	 */
	public WorkFlowContext() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkflowContextInterface#getCaller()
	 */
	@Override
	public long getCaller() {
		return caller;
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkflowContextInterface#getCurrentWorkFlowNode()
	 */
	@Override
	public WorkFlowNode getCurrentWorkFlowNode() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkflowContextInterface#setRollbackOnly()
	 */
	@Override
	public void setRollbackOnly() {
	}

}
