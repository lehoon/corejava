/**
 * <p>Title: WorkFlowInstanceNodeArgvInterface.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.wxltsoft.com</p>
 * @author lehoons@gmail.com
 * @date 2013年12月16日
 * @version 1.0
 */
package com.coreworkflow.workflow.basic;

/**
 * <p>Title: WorkFlowInstanceNodeArgvInterface</p>
 * <p>Description: 流程实例节点参数接口声明</p>
 * <p>Company: www.wxltsoft.com</p> 
 * @author	lehoons@gmail.com
 * @date	2013年12月16日
 */
public interface WorkFlowInstanceNodeArgvInterface {
	/**
	 * 
	 * <p>Title: getInsid</p>
	 * <p>Description: 获取工作流程定义节点参数值实例编号</p>
	 * @return  实例编号
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getInsid();
	/**
	 * 
	 * <p>Title: getNodeid</p>
	 * <p>Description: 获取工作流程定义节点参数值node编号</p>
	 * @return  node编号
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getNodeid();
	/**
	 * 
	 * <p>Title: getArgvtype</p>
	 * <p>Description: 获取工作流程定义节点参数类型</p>
	 * @return  参数类型
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public int getArgvtype();
	/**
	 * 
	 * <p>Title: getArgvvalue</p>
	 * <p>Description: 获取工作流程定义节点参数值</p>
	 * @return  参数值
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getArgvvalue();
}
