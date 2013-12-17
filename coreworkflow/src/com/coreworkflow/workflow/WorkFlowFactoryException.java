/**   
 * @Title: WorkFlowFactoryException.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: WorkFlowFactoryException.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午7:13:29  
 */

package com.coreworkflow.workflow;

/**
 * <p>@description：</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午7:13:29
 */

public class WorkFlowFactoryException extends WorkflowException {
    /**
	 * @Fields serialVersionUID : serialVersionUID
	 */
	
	private static final long serialVersionUID = 8530092747056794955L;

	public WorkFlowFactoryException() {
    }

    public WorkFlowFactoryException(String message) {
        super(message);
    }

    public WorkFlowFactoryException(Exception cause) {
        super(cause);
    }

    public WorkFlowFactoryException(String message, Exception cause) {
        super(message, cause);
    }
}
