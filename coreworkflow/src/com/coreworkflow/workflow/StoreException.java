/**   
 * @Title: StoreException.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: StoreException.java</p>
 * <p>@author:：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午5:37:18  
 */

package com.coreworkflow.workflow;

/**
 * <p>@description：工作流持久化存储异常定义</p>
 * <p>@company：wxltsoft</p>
 * <p>@author:：lehoons@gmail.com</p>
 * <p>@date：2013年12月5日 下午5:37:18</p>
 */

public class StoreException extends WorkflowException{

	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = 7431960712637688439L;

    public StoreException(String s) {
        super(s);
    }

    public StoreException(String s, Throwable ex) {
        super(s, ex);
    }

    public StoreException(Throwable ex) {
        super(ex);
    }
}
