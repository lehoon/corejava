/**   
 * @Title: WorkFlowInstanceEntity.java
 * @Package: com.coreworkflow.workflow.entity
 * @Description: WorkFlowInstanceEntity.java
 * @author：lehoons@gmail.com
 * @date：2013年12月6日 下午2:42:18  
 */

package com.coreworkflow.workflow.entity;

import java.util.List;

import com.coreworkflow.workflow.WorkFlowNode;
import com.coreworkflow.workflow.WorkFlowTask;

/**
 * 流程实例实体定义
 * @company：wxltsoft
 * @author：lehoons@gmail.com
 * @date：2013年12月6日 下午2:42:18
 */

public class WorkFlowInstanceEntity implements java.io.Serializable{

	/**
	 * @Fields serialVersionUID : 序列化编号
	 */
	
	private static final long serialVersionUID = -4919999646051659318L;
	/** 流程实例定义编号 */
	private long		insid;
	/** 流程实例定义名称 */
	private String		insname;
	/** 流程定义编号 */
	private long		wfid;
	/** 流程定义名称 */
	private String		wfname;
	/** 流程实例创建者编号 */
	private long		userid;
	/** 流程实例状态 */
	private int			status;
	/** 流程实例当前节点编号 */
	private long		currentnoidid;

	/**
	 * 流程任务节点列表
	 */
	private List<WorkFlowTask> workflowTaskList;
	
	/**
	 * 流程实例当前节点描述
	 */
	private WorkFlowNode		currentWorkFlowNode;

	/**
	 *@filename WorkFlowInstanceEntity.java
	 *@description 构造函数
	 *@date 2013年12月6日
	 */
	public WorkFlowInstanceEntity() {
		super();
	}
	
	/**
	 *@filename WorkFlowInstanceEntity.java
	 *@description 构造函数
	 *@date 2013年12月6日
	 *@param insid
	 *@param insname
	 *@param wfid
	 *@param wfname
	 *@param userid
	 */
	public WorkFlowInstanceEntity(long insid, String insname, long wfid,
			String wfname, long userid, int status, long currentnodeid) {
		super();
		this.insid = insid;
		this.insname = insname;
		this.wfid = wfid;
		this.wfname = wfname;
		this.userid = userid;
		this.status = status;
		this.currentnoidid = currentnodeid;
	}
	
	/**
	 *@description：获取实体属性
	 *@common：insid long WorkFlowInstanceEntity.java
	 *@return the insid
	 */
	public long getInsid() {
		return insid;
	}
	
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：insid long WorkFlowInstanceEntity.java
	 * @param insid the insid to set
	 */
	public void setInsid(long insid) {
		this.insid = insid;
	}
	
	/**
	 *@description：获取实体属性
	 *@common：insname String WorkFlowInstanceEntity.java
	 *@return the insname
	 */
	public String getInsname() {
		return insname;
	}
	
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：insname String WorkFlowInstanceEntity.java
	 * @param insname the insname to set
	 */
	public void setInsname(String insname) {
		this.insname = insname;
	}
	
	/**
	 *@description：获取实体属性
	 *@common：wfid long WorkFlowInstanceEntity.java
	 *@return the wfid
	 */
	public long getWfid() {
		return wfid;
	}
	
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：wfid long WorkFlowInstanceEntity.java
	 * @param wfid the wfid to set
	 */
	public void setWfid(long wfid) {
		this.wfid = wfid;
	}
	
	/**
	 *@description：获取实体属性
	 *@common：wfname String WorkFlowInstanceEntity.java
	 *@return the wfname
	 */
	public String getWfname() {
		return wfname;
	}
	
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：wfname String WorkFlowInstanceEntity.java
	 * @param wfname the wfname to set
	 */
	public void setWfname(String wfname) {
		this.wfname = wfname;
	}
	
	/**
	 *@description：获取实体属性
	 *@common：userid long WorkFlowInstanceEntity.java
	 *@return the userid
	 */
	public long getUserid() {
		return userid;
	}
	
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：userid long WorkFlowInstanceEntity.java
	 * @param userid the userid to set
	 */
	public void setUserid(long userid) {
		this.userid = userid;
	}

	/**
	 *@description：获取实体属性
	 *@common：status int WorkFlowInstanceEntity.java
	 *@return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：status int WorkFlowInstanceEntity.java
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 *@description：获取实体属性
	 *@common：currentnoidid long WorkFlowInstanceEntity.java
	 *@return the currentnoidid
	 */
	public long getCurrentnoidid() {
		return currentnoidid;
	}

	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：currentnoidid long WorkFlowInstanceEntity.java
	 * @param currentnoidid the currentnoidid to set
	 */
	public void setCurrentnoidid(long currentnoidid) {
		this.currentnoidid = currentnoidid;
	}

	/**
	 *@description：获取实体属性
	 *@common：workflowTaskList List<WorkFlowTask> WorkFlowInstanceEntity.java
	 *@return the workflowTaskList
	 */
	public List<WorkFlowTask> getWorkflowTaskList() {
		return workflowTaskList;
	}

	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：workflowTaskList List<WorkFlowTask> WorkFlowInstanceEntity.java
	 * @param workflowTaskList the workflowTaskList to set
	 */
	public void setWorkflowTaskList(List<WorkFlowTask> workflowTaskList) {
		this.workflowTaskList = workflowTaskList;
	}

	/**
	 *@description：获取实体属性
	 *@common：currentWorkFlowNode WorkFlowNode WorkFlowInstanceEntity.java
	 *@return the currentWorkFlowNode
	 */
	public WorkFlowNode getCurrentWorkFlowNode() {
		return currentWorkFlowNode;
	}

	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：currentWorkFlowNode WorkFlowNode WorkFlowInstanceEntity.java
	 * @param currentWorkFlowNode the currentWorkFlowNode to set
	 */
	public void setCurrentWorkFlowNode(WorkFlowNode currentWorkFlowNode) {
		this.currentWorkFlowNode = currentWorkFlowNode;
	}
	
	
}
