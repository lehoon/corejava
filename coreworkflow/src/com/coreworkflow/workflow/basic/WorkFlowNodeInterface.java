/**   
 * @Title: WorkFlowNodeInterface.java
 * @Package: com.coreworkflow.workflow.basic
 * <p>@description: WorkFlowNodeInterface.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午7:47:49  
 */

package com.coreworkflow.workflow.basic;

import java.util.List;
import java.util.Map;

import com.coreworkflow.workflow.WorkFlowEvent;
import com.coreworkflow.workflow.WorkflowException;

/**
 * <p>@description：工作流节点包装类接口定义声明</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午7:47:49
 */

public interface WorkFlowNodeInterface {

	/**
	 * <p>@description: 节点执行方法</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @param：   instid    实例编号 
	 * @param:  taskid    任务编号
	 * @param:  inParams  参数
	 * @return void   
	 * @throws
	 */
	public void execute(long instid, long taskid, Map<String, Object> inParams) throws WorkflowException;
	
	/**
	 * <p>@description: 返回当前节点的事件列表</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return List<WorkFlowEvent>   
	 * @throws
	 */
	public List<WorkFlowEvent> getEventList();
	
	/**
	 * <p>Title: getNodeid</p>
	 * <p>Description: 获取节点店编号</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getNodeid();
	
	/**
	 * <p>Title: getWfid</p>
	 * <p>Description: 获取节点流程编号</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getWfid();
	/**
	 * 
	 * <p>Title: getNodename</p>
	 * <p>Description: 获取节点名称</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getNodename();
	/**
	 * 
	 * <p>Title: getNodedcsc</p>
	 * <p>Description: 获取节点描述</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getNodedcsc();
	/**
	 * 
	 * <p>Title: getPostid</p>
	 * <p>Description: 获取节点岗位编号</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getPostid();
	/**
	 * 
	 * <p>Title: getRulecontext</p>
	 * <p>Description: 获取节点流程规则定义</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getRulecontext();
	/**
	 * 
	 * <p>Title: getPrenodeid</p>
	 * <p>Description: 获取节点上一节点编号</p>
	 * @return  long
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getPrenodeid();
	/**
	 * 
	 * <p>Title: getNextnodeid</p>
	 * <p>Description: 获取节点下一节点编号</p>
	 * @return  long
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getNextnodeid();
	/**
	 * 
	 * <p>Title: getStatus</p>
	 * <p>Description: 获取节点状态</p>
	 * @return  int
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public int getStatus();
	/**
	 * 
	 * <p>Title: getCreated</p>
	 * <p>Description: 获取节点创建时间</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getCreated();
	/**
	 * 
	 * <p>Title: getTimeout</p>
	 * <p>Description:获取节点超时时间 </p>
	 * @return  int
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public int getTimeout();
	/**
	 * 
	 * <p>Title: getAutonextnode</p>
	 * <p>Description: 获取节点是否执行下一个节点</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public int getAutonode();
	/**
	 * 
	 * <p>Title: getXpoint</p>
	 * <p>Description: 获取节点位置坐标X</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public int getXpoint();
	
	/**
	 * 
	 * <p>Title: getYpoint</p>
	 * <p>Description: 获取节点位置坐标Y</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public int getYpoint();
}
