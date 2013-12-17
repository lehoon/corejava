/**   
 * @Title: WorkFlowInstanceInterface.java
 * @Package: com.coreworkflow.workflow.basic
 * <p>@description: WorkFlowInstanceInterface.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月11日 上午11:50:40  
 */

package com.coreworkflow.workflow.basic;

import java.util.List;
import java.util.Map;

import com.coreworkflow.workflow.WorkFlowTask;

/**
 * <p>@description：</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com
 * @date：2013年12月11日 上午11:50:40
 */

public interface WorkFlowInstanceInterface {
	
	/**
	 * <p>@description: 返回指定实例的状态</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月5日
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
	 * <p>实例化一个工作流实例，返回工作流实例编号 ,同时创建开始任务节点</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月11日
	 * @param wofcode   流程定义模板
	 * @param instname  实例名称
	 * @param caller    调用者编号
	 * @return long   
	 * @throws
	 */
	public long CreateWorkFlow(String wfcode, String instname, long caller);
	
	/**
	 * <p>创建流程实例任务第一个节点</p>
	 * <p>@author: lehoons@gmail.com</p>
	 * @date:2013年12月11日
	 * @param inParams  流程任务节点需要的参数
	 * @return long     返回创建的任务节点编号
	 * @throws
	 */
	public long CreateWorkFlowFirstTask(Map<String, Object> inParams);
	
	/**
	 * <p>创建工作流程实例下一个节点任务</p>
	 * <p>lehoons@gmail.com</p>
	 * @date:2013年12月11日
	 * @param inParams
	 * @return  
	 * @throws
	 */
	public boolean CreateWorkFlowNextTask(Map<String, Object> inParams);
	
	/**
	 * <p>获取指定的流程实例，同时获取当前任务节点</p>
	 * <p>lehoons@gmail.com</p>
	 * @date:2013年12月11日
	 * @param instid  流程实例编号
	 * @param taskid  流程实例当前任务编号
	 * @return boolean   成功返回 true, 失败返回false
	 * @throws
	 */
	public boolean GetWorkFlow(long instid, long taskid);
	
	/**
	 * <p>执行当前任务处理方法</p>
	 * <p>lehoons@gmail.com</p>
	 * @date:2013年12月11日
	 * @param inParams 
	 * @return  boolean 成功返回 true  失败返回 false
	 * @throws
	 */
	public boolean Execute(Map<String, Object> inParams);
	
	/**
	 * <p>设置当前任务节点的处理状态</p>
	 * <p>lehoons@gmail.com</p>
	 * @date:2013年12月11日
	 * @param taskstatus 状态
	 * @param inParams 参数
	 * @return  成功返回true  失败返回false
	 * @throws
	 */
	public boolean SetCurrentTaskStatus(int taskstatus, Map<String, Object> inParams);
	
	/**
	 * 
	 * <p>Title: getInsid</p>
	 * <p>Description: </p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getInsid();
	/**
	 * 
	 * <p>Title: getInsname</p>
	 * <p>Description: 获取工作流程实例名称</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getInsname();
	/**
	 * 
	 * <p>Title: getWfid</p>
	 * <p>Description: 获取工作流程引擎定义编号</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getWfid();
	/**
	 * 
	 * <p>Title: getWfname</p>
	 * <p>Description: 获取工作流名称</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public String getWfname();
	
	/**
	 * 
	 * <p>Title: getUserid</p>
	 * <p>Description: 获取流程实例创建者编号</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getUserid();
	/**
	 * 
	 * <p>Title: getStatus</p>
	 * <p>Description: 获取当前流程实例状态</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public int getStatus();
	
	/**
	 * <p>Title: getCurrentnoidid</p>
	 * <p>Description: 获取当前运行节点</p>
	 * @return
	 * @author	lehoons@gmail.com
	 * @date	2013年12月16日
	 */
	public long getCurrentnoidid();
	
	
	
	
	
	
	
	
	
}
