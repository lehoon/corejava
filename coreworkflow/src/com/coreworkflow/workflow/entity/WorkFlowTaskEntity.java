/**   
 * @Title: WorkFlowTaskEntity.java
 * @Package: com.coreworkflow.workflow.entity
 * @Description: WorkFlowTaskEntity.java
 * @author：lehoons@gmail.com
 * @date：2013年12月6日 下午2:45:36  
 */

package com.coreworkflow.workflow.entity;


/**
 * <p>流程任务节点定义实体类</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * <p>@date：2013年12月6日 下午2:45:36</p>
 */

public class WorkFlowTaskEntity implements java.io.Serializable{

	/**
	 * @Fields serialVersionUID : 序列化编号
	 */
	
	private static final long serialVersionUID = -4643856784819260402L;
	/** 流程任务编号 */
	private long		taskid;
	/** 流程任务所属流程实例编号 */
	private long		insid;
	/** 流程任务关联节点编号 */
	private	long		nodeid;
	/** 流程任务处理岗位编号 */
	private	long		postid;
	/** 流程任务处理组织编号 */
	private	long		orgid;
	/** 流程任务处理人编号，后续写入 */
	private	long		userid;
	/** 流程任务处理状态    0未处理   1已处理 */
	private	int			taskstatus;
	/** 流程任务创建时间 */
	private	String		createdate;
	/** 流程任务更新时间 */
	private	String		finishdate;
	/** 流程任务处理说明 */
	private	String		message;
	/** 流程任务处理结果 */
	private	int			status;
	/** 流程任务上个任务节点 */
	private	long		prenodeid;
	/** 流程任务下个任务节点 */
	private	long		nextnodeid;


	/**
	 *@filename WorkFlowTaskEntity.java
	 *@description 构造函数
	 *@date 2013年12月6日
	 */
	public WorkFlowTaskEntity() {
		super();
	}


	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param taskid
	 * @param insid
	 * @param nodeid
	 * @param postid
	 * @param orgid
	 * @param userid
	 * @param taskstatus
	 * @param createdate
	 * @param finishdate
	 * @param message
	 * @param status
	 * @param pretaskid
	 * @param nexttaskid
	 */
	public WorkFlowTaskEntity(long taskid, long insid, long nodeid,
			long postid, long orgid, long userid, int taskstatus,
			String createdate, String finishdate, String message, int status,
			long prenodeid, long nextnodeid) {
		super();
		this.taskid = taskid;
		this.insid = insid;
		this.nodeid = nodeid;
		this.postid = postid;
		this.orgid = orgid;
		this.userid = userid;
		this.taskstatus = taskstatus;
		this.createdate = createdate;
		this.finishdate = finishdate;
		this.message = message;
		this.status = status;
		this.prenodeid = prenodeid;
		this.nextnodeid = nextnodeid;
	}


	/**
	 * @return the taskid
	 */
	public long getTaskid() {
		return taskid;
	}


	/**
	 * @param taskid the taskid to set
	 */
	public void setTaskid(long taskid) {
		this.taskid = taskid;
	}


	/**
	 * @return the insid
	 */
	public long getInsid() {
		return insid;
	}


	/**
	 * @param insid the insid to set
	 */
	public void setInsid(long insid) {
		this.insid = insid;
	}


	/**
	 * @return the nodeid
	 */
	public long getNodeid() {
		return nodeid;
	}


	/**
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(long nodeid) {
		this.nodeid = nodeid;
	}


	/**
	 * @return the postid
	 */
	public long getPostid() {
		return postid;
	}


	/**
	 * @param postid the postid to set
	 */
	public void setPostid(long postid) {
		this.postid = postid;
	}


	/**
	 * @return the orgid
	 */
	public long getOrgid() {
		return orgid;
	}


	/**
	 * @param orgid the orgid to set
	 */
	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}


	/**
	 * @return the userid
	 */
	public long getUserid() {
		return userid;
	}


	/**
	 * @param userid the userid to set
	 */
	public void setUserid(long userid) {
		this.userid = userid;
	}


	/**
	 * @return the taskstatus
	 */
	public int getTaskstatus() {
		return taskstatus;
	}


	/**
	 * @param taskstatus the taskstatus to set
	 */
	public void setTaskstatus(int taskstatus) {
		this.taskstatus = taskstatus;
	}


	/**
	 * @return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}


	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}


	/**
	 * @return the finishdate
	 */
	public String getFinishdate() {
		return finishdate;
	}


	/**
	 * @param finishdate the finishdate to set
	 */
	public void setFinishdate(String finishdate) {
		this.finishdate = finishdate;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}


	/**
	 * @return the pretaskid
	 */
	public long getPrenodeid() {
		return prenodeid;
	}


	/**
	 * @param pretaskid the pretaskid to set
	 */
	public void setPrenodeid(long prenodeid) {
		this.prenodeid = prenodeid;
	}


	/**
	 * @return the nexttaskid
	 */
	public long getNextnodeid() {
		return nextnodeid;
	}


	/**
	 * @param nexttaskid the nexttaskid to set
	 */
	public void setNextnodeid(long nextnodeid) {
		this.nextnodeid = nextnodeid;
	}

	
	
	
	
	
}
