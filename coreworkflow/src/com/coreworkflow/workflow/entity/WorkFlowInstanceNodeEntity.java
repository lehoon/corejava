/**   
 * @Title: WorkFlowInstanceNodeEntity.java
 * @Package: com.coreworkflow.workflow.entity
 * @Description: WorkFlowInstanceNodeEntity.java
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午9:48:29  
 */

package com.coreworkflow.workflow.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @company：wxltsoft
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午9:48:29
 */

public class WorkFlowInstanceNodeEntity implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化编号
	 */
	
	private static final long serialVersionUID = 5822718802086382906L;

	/** 记录编号 */
	private long		inid;
	/** 流程实例编号 */
	private long		insid;
	/** 流程实例节点编号 */
	private long		nodeid;
	/** 该节点是否拆分 */
	private int			issplit;
	/** 流程实例节点状态 */
	private int			status;
	/** 流程实例节点名称 */
	private String		nodename;
	/** 流程实例节点描述 */
	private String		nodedesc;
	/** 流程实例节点岗位 */
	private String		postid;

	/**
	 * 流程实例节点对应的任务描述
	 * 如果该流程节点只有一个任务，则该列表中有一条任务数据
	 * 如果该流程节点经过拆分过，那么该节点存在多条任务数据
	 */
	private List<WorkFlowTaskEntity> workflowTaskList;
	
	
	/**
	 *@filename WorkFlowInstanceNodeEntity.java
	 *@description 构造函数
	 *@date 2013年12月12日
	 */
	public WorkFlowInstanceNodeEntity() {
		super();
	}

	/**
	 *@filename WorkFlowInstanceNodeEntity.java
	 * 构造函数
	 *@date 2013年12月12日
	 *@param inid 记录编号
	 *@param insid 流程实例编号
	 *@param nodeid 流程实例节点编号
	 *@param issplit 是否拆分该节点
	 *@param status  流程节点状态
	 */
	public WorkFlowInstanceNodeEntity(long inid, long insid, long nodeid,
			int issplit, int status) {
		super();
		this.inid = inid;
		this.insid = insid;
		this.nodeid = nodeid;
		this.issplit = issplit;
		this.status = status;
	}

	/**
	 * 获取实体属性
	 *@common：inid long WorkFlowInstanceNodeEntity.java
	 *@return the inid
	 */
	public long getInid() {
		return inid;
	}

	/**
	 * 设置实体类属性值
	 * 提供方法注入手段
	 * @common：inid long WorkFlowInstanceNodeEntity.java
	 * @param inid the inid to set
	 */
	public void setInid(long inid) {
		this.inid = inid;
	}

	/**
	 * 获取实体属性
	 *@common：insid long WorkFlowInstanceNodeEntity.java
	 *@return the insid
	 */
	public long getInsid() {
		return insid;
	}

	/**
	 * 设置实体类属性值
	 * 提供方法注入手段
	 * @common：insid long WorkFlowInstanceNodeEntity.java
	 * @param insid the insid to set
	 */
	public void setInsid(long insid) {
		this.insid = insid;
	}

	/**
	 * 获取实体属性
	 *@common：nodeid long WorkFlowInstanceNodeEntity.java
	 *@return the nodeid
	 */
	public long getNodeid() {
		return nodeid;
	}

	/**
	 * 设置实体类属性值
	 * 提供方法注入手段
	 * @common：nodeid long WorkFlowInstanceNodeEntity.java
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(long nodeid) {
		this.nodeid = nodeid;
	}

	/**
	 * 获取实体属性
	 *@common：issplit int WorkFlowInstanceNodeEntity.java
	 *@return the issplit
	 */
	public int getIssplit() {
		return issplit;
	}

	/**
	 * 设置实体类属性值
	 * 提供方法注入手段
	 * @common：issplit int WorkFlowInstanceNodeEntity.java
	 * @param issplit the issplit to set
	 */
	public void setIssplit(int issplit) {
		this.issplit = issplit;
	}

	/**
	 * 获取实体属性
	 *@common：status int WorkFlowInstanceNodeEntity.java
	 *@return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置实体类属性值
	 * 提供方法注入手段
	 * @common：status int WorkFlowInstanceNodeEntity.java
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * 重写Object类的toString方法
	 * 返回流程实例节点的信息
	 */
	public String toString()
	{
		return "{inid:" + inid + ";insid:" + insid + ";nodeid:" + nodeid + ";issplit:" + ";status:" + status + ";nodename:" + nodename + ";nodedesc:" + nodedesc + ";postid:" + postid +"}";
	}

	/**
	 * 获取实体属性
	 *@common：workflowTaskList List<WorkFlowTaskEntity> WorkFlowInstanceNodeEntity.java
	 *@return the workflowTaskList
	 */
	public List<WorkFlowTaskEntity> getWorkflowTaskList() {
		return workflowTaskList;
	}

	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：workflowTaskList List<WorkFlowTaskEntity> WorkFlowInstanceNodeEntity.java
	 * @param workflowTaskList the workflowTaskList to set
	 */
	public void setWorkflowTaskList(List<WorkFlowTaskEntity> workflowTaskList) {
		this.workflowTaskList = workflowTaskList;
	}

	/**
	 * @return the nodename
	 */
	public String getNodename() {
		return nodename;
	}

	/**
	 * @param nodename the nodename to set
	 */
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	/**
	 * @return the nodedesc
	 */
	public String getNodedesc() {
		return nodedesc;
	}

	/**
	 * @param nodedesc the nodedesc to set
	 */
	public void setNodedesc(String nodedesc) {
		this.nodedesc = nodedesc;
	}

	/**
	 * @return the postid
	 */
	public String getPostid() {
		return postid;
	}

	/**
	 * @param postid the postid to set
	 */
	public void setPostid(String postid) {
		this.postid = postid;
	}
	
}
