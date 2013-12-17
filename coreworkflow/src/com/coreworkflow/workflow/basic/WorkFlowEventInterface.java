/**   
 * <p>@Title: WorkFlowEventInterface.java</p>
 * <p>@Package: com.coreworkflow.workflow.basic</p>
 * <p>@Description: WorkFlowEventInterface.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * <p>@date：2013年12月5日 下午4:31:01  </p>
 */

package com.coreworkflow.workflow.basic;

import java.util.Map;

import com.coreworkflow.workflow.WorkflowException;


/**
 * <p>工作流程事件定义接口，可扩展</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * <p>@date：2013年12月5日 下午4:31:01</p>
 */

public interface WorkFlowEventInterface {

	/**
	 * <p>Execute this Event</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @throws WorkflowException    
	 * @return void   
	 */
	public void execute(long instid, long taskid, Map<String, Object> inParams) throws WorkflowException;
	
	/**
	 * <p>Title: getEventid</p>
	 * <p>Description: 获取事件定义编号</p>
	 * @return  long
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getEventid();
	/**
	 * 
	 * <p>Title: getEventname</p>
	 * <p>Description: 获取事件定义名称</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getEventname();
	/**
	 * 
	 * <p>Title: getEventdesc</p>
	 * <p>Description: 获取事件定义描述</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getEventdesc();
	/**
	 * 
	 * <p>Title: getEventtype</p>
	 * <p>Description: 获取事件定义类型</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getEventtype();
	/**
	 * 
	 * <p>Title: getEventhandle</p>
	 * <p>Description: 获取事件定义处理句柄</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getEventhandle();
	/**
	 * 
	 * <p>Title: getEventfunc</p>
	 * <p>Description: 获取事件定义处理函数定义名称</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getEventfunc();
	
	/**
	 * 
	 * <p>Title: getEventargvs</p>
	 * <p>Description: 获取事件定义处理函数参数</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getEventargvs();
	
	
	
	
	
	
	
	
	
	
	
	
}
