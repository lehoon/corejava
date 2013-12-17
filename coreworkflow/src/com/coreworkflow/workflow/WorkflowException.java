/**   
 * @Title: WorkflowException.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: WorkflowException.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午4:22:37  
 */

package com.coreworkflow.workflow;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * <p>@description：</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午4:22:37
 */

public class WorkflowException extends Exception {

	/**
	 * @Fields serialVersionUID : 序列化版本号
	 */
	
	private static final long serialVersionUID = 4201273195954363691L;
    private Throwable rootCause;

    //~ Constructors ///////////////////////////////////////////////////////////

    public WorkflowException() {
    }

    public WorkflowException(String s) {
        super(s);
    }

    public WorkflowException(String s, Throwable rootCause) {
        super(s);
        this.rootCause = rootCause;
    }

    public WorkflowException(Throwable rootCause) {
        this.rootCause = rootCause;
    }

    //~ Methods ////////////////////////////////////////////////////////////////

    public Throwable getCause() {
        return rootCause;
    }

    public String getMessage() {
        StringBuffer sb = new StringBuffer();
        String msg = super.getMessage();

        if (msg != null) {
            sb.append(msg);

            if (rootCause != null) {
                sb.append(": ");
            }
        }

        if (rootCause != null) {
            sb.append("root cause: " + ((rootCause.getMessage() == null) ? rootCause.toString() : rootCause.getMessage()));
        }

        return sb.toString();
    }

    public Throwable getRootCause() {
        return rootCause;
    }

    public void printStackTrace() {
        super.printStackTrace();

        if (rootCause != null) {
            synchronized (System.err) {
                System.err.println("\nRoot cause:");
                rootCause.printStackTrace();
            }
        }
    }

    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);

        if (rootCause != null) {
            synchronized (s) {
                s.println("\nRoot cause:");
                rootCause.printStackTrace(s);
            }
        }
    }

    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);

        if (rootCause != null) {
            synchronized (s) {
                s.println("\nRoot cause:");
                rootCause.printStackTrace(s);
            }
        }
    }
}
