/**   
 * @Title: WorkFlowInterface.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: WorkFlowInterface.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午5:00:16  
 */

package com.coreworkflow.workflow.basic;

import java.util.List;
import java.util.Map;

import com.coreworkflow.workflow.WorkFlowInstanceNode;
import com.coreworkflow.workflow.WorkFlowTask;

/**
 * <p>@description：</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午5:00:16
 */

public interface WorkFlowInterface {

	//活动状态
	public final static int		WORKFLOW_INSTANCE_STATUS_ACTIVE				=	1;
	//暂停状态
	public final static int		WORKFLOW_INSTANCE_STATUS_SUSPEND			=	2;
	//删除状态
	public final static int		WORKFLOW_INSTANCE_STATUS_STOP				=	3;
	//完成状态
	public final static int		WORKFLOW_INSTANCE_STATUS_FINISH				=	4;
	
	/**
	 * <p>@description: 返回指定实例的状态</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
	 * @argv：   
	 * @return int   
	 * @throws
	 */
	public int getInstanceState();
	
	/**
	 * <p>@description: 获取工作流的任务列表</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月6日
	 * @argv：   
	 * @return List<WorkFlowTask>   
	 * @throws
	 */
	public List<WorkFlowTask> getWorkFlowTask();
	
	/**
	 * 创建流程实例任务第一个节点
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月11日
	 * @param inParams  流程任务节点需要的参数
	 * @return long     返回创建的任务节点编号
	 * @throws
	 */
	public long CreateWorkFlowFirstTask(Map<String, Object> inParams);
	
	/**
	 * 创建工作流程实例下一个节点任务
	 * lehoons@gmail.com
	 * @date:2013年12月11日
	 * @param inParams
	 * @return  
	 * @throws
	 */
	//public boolean CreateWorkFlowNextTask(Map<String, Object> inParams);
	
	/**
	 * 获取指定的流程实例，同时获取当前任务节点
	 * lehoons@gmail.com
	 * @date:2013年12月11日
	 * @param instid  流程实例编号
	 * @param taskid  流程实例当前任务编号
	 * @return boolean   成功返回 true, 失败返回false
	 * @throws
	 */
	//public boolean GetWorkFlow(long instid, long taskid);
	
	/**
	 * 执行当前任务处理方法
	 * lehoons@gmail.com
	 * @date:2013年12月11日
	 * @param inParams 
	 * @return  boolean 成功返回 true  失败返回 false
	 * @throws
	 */
	public boolean Execute(Map<String, Object> inParams);
	
	/**
	 * 设置当前任务节点的处理状态
	 * lehoons@gmail.com
	 * @date:2013年12月11日
	 * @param taskstatus 状态
	 * @param inParams 参数
	 * @return  成功返回true  失败返回false
	 * @throws
	 */
	public boolean SetCurrentTaskStatus(int taskstatus, Map<String, Object> inParams);
	
	/**
	 * 返回流程实例所有的任务节点描述信息
	 * 根据节点去查询任务描述，处理一个节点拆分为多个任务的情况
	 * 1、如果一个节点拆分为多个任务，那么该节点的状态为该节点对应多个任务合并后的状态
	 * 2、在页面上去显示一个流程所有实例的历史记录的时候，应该从节点开始，不应该直接在任务上展示
	 * lehoons@gmail.com
	 * @date:2013年12月12日
	 * @return  
	 * @throws
	 */
	public List<WorkFlowInstanceNode> GetWorkFlowInstaceNodeList();
	
	/**
	 * <p>Title: SetWorkFlowInstanceNextNodeTaskArgv</p>
	 * <p>Description: 设置流程下一节点需要的参数，类型是Map<Integer, List<Long>>，key是参数类型，1为部门编号，其余保留带扩展
	 * 组织的时候，需要按照该格式组织好，然后工作流给创建下一个流程实例节点需要的参数，保存到数据库，然后工作流引起依次为依据创建下一个节点的任务</p>
	 * @param inParams
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public void SetWorkFlowNextNodeTaskArgv(Map<Integer, List<Long>> inParams);
	
	/**
	 * 
	 * <p>Title: GetWorkFlowId</p>
	 * <p>Description: 返回当前工作流程实例编号</p>
	 * @return  long 当前工作流程实例编号
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long GetWorkFlowInstanceId();
	/**
	 * 
	 * <p>Title: GetWorkFlowInstanceName</p>
	 * <p>Description: 返回当前工作流程实例名称</p>
	 * @return  String 当前工作流程实例
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String GetWorkFlowInstanceName();
	
	/**
	 * <p>Title: SuspendWorkFlow</p>
	 * <p>Description: 暂停该流程</p>
	 * @return  成功返回true  失败返回false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public boolean SuspendWorkFlow();
	/**
	 * 
	 * <p>Title: ResumeWorkFlow</p>
	 * <p>Description: 恢复该流程为激活状态</p>
	 * @return  成功返回true  失败返回false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public boolean ResumeWorkFlow();
	
	/**
	 * 
	 * <p>Title: StopWorkFlow</p>
	 * <p>Description: 停止工作流程</p>
	 * @return  成功返回true  失败返回false
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public boolean StopWorkFlow();
}
