/**   
 * <p>@Title: WorkFlowTaskInterface.java</p>
 * <p>@Package: com.coreworkflow.workflow.basic</p>
 * <p>@description: WorkFlowTaskInterface.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * <p>@date：2013年12月5日 下午5:41:07</p>
 */

package com.coreworkflow.workflow.basic;

import com.coreworkflow.workflow.WorkFlowNode;

/**
 * <p>@description：任务接口定义</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午5:41:07
 */

public interface WorkFlowTaskInterface {

	/**
	 * 任务状态
	 */
	//活动状态
	public final static int		WORKFLOW_TASKSTATUS_STATUS_ACTIVE				=	1;
	//暂停状态
	public final static int		WORKFLOW_TASKSTATUS_STATUS_SUSPEND				=	2;
	//删除状态
	public final static int		WORKFLOW_TASKSTATUS_STATUS_STOP					=	3;
	//完成状态
	public final static int		WORKFLOW_TASKSTATUS_STATUS_FINISH				=	4;
	
	/**
	 * 任务处理状态
	 */
	//代办
	public final static int 	WORKFLOW_TASKDEAL_STATUS_WAIT					= 	0;
	//成功
	public final static int 	WORKFLOW_TASKDEAL_STATUS_SUCESS					= 	1;
	//失败
	public final static int 	WORKFLOW_TASKDEAL_STATUS_FAIL					= 	2;

	/**
	 * <p>@description: 获取任务节点描述</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return WorkFlowNode   
	 */
	public WorkFlowNode getWorkFlowNode();
	
	/**
	 * <p>@description:设置当前任务处理状态 </p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @argv：   status 
	 * @return void   
	 */
	public void setTaskEntryStatus(int status);
	
	/**
	 * <p>@description: 返回任务处理人</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return long   
	 */
	public long getTaskEntryCaller();
	
	/**
	 * <p>@description: 设置当前任务处理人</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @argv：   caller 当前处理人
	 * @return void   
	 */
	public void setTaskEntryCaller(long caller);
	
	/**
	 * <p>@description: 获取流程实例号</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return long   
	 */
	public long getWorkFlowInstanceId();
	
	/**
	 * <p>@description: 返回开始时间</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return String   
	 */
	public String getStartDate();
	
	/**
	 * <p>@description: 返回完成时间</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return String   
	 */
	public String getFinishDate();
	
	/**
	 * <p>@description: 返回任务编号</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return long   
	 */
	public long getId();
	
	/**
	 * <p>@description: 返回该任务处理人的岗位编号</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return long   
	 */
	public long getPostid();
	
	/**
	 * <p>@description: 返回处理人的部门编号</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return long   
	 */
	public long getOrgId();
	
	/**
	 * <p>@description: 返回上个任务编号</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return long   
	 */
	public long getPreviousNodeId();
	
	/**
	 * <p>@description: 返回下个任务编号</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return long   0 is the last
	 */
	public long getNextNodeId();
	
	/**
	 * <p>@description: 返回任务状态</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return int   
	 */
	public int  getTaskStatus();
	
	/**
	 * <p>@description: 返回任务对应的节点编号</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @return long   
	 */
	public long getNodeId();
	
	/**
	 * <p>Title: getMessage</p>
	 * <p>Description: 获取任务处理反馈消息</p>
	 * @return  String
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getMessage();
	/**
	 * 
	 * <p>Title: getInsid</p>
	 * <p>Description: 获取流程实例编号</p>
	 * @return  long
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getInsid();
}
