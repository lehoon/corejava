/**
 * <p>Title: WorkFlowInstanceNodeArgvEntity.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.wxltsoft.com</p>
 * @author lehoons@gmail.com
 * @date 2013年12月13日
 * @version 1.0
 */
package com.coreworkflow.workflow.entity;

import java.io.Serializable;

/**
 * <p>Title: WorkFlowInstanceNodeArgvEntity</p>
 * <p>Description: 流程实例节点参数表,流程实例每一个节点需要参数的时候，需要先设置该流程实例节点的参数
 * 		           主要使用场景是工作流自动拆分任务的时候，作为该节点拆分任务的一个依据，便于工作流自动进行拆分任务
 * </p>
 * <p>Company: www.wxltsoft.com</p> 
 * @author	lehoons@gmail.com
 * @date	2013年12月13日
 */
public class WorkFlowInstanceNodeArgvEntity implements Serializable {

	/** serialVersionUID*/
	private static final long serialVersionUID = -143452128017452623L;

	/**
	 * 工作流程实例编号
	 */
	private long		insid;
	/**
	 * 工作流程实例节点编号
	 */
	private long		nodeid;
	/**
	 * 工作流程实例节点参数类型
	 */
	private	int			argvtype;
	
	/**
	 * 工作流程实例节点参数值
	 */
	private long		argvvalue;

	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 */
	public WorkFlowInstanceNodeArgvEntity() {
		super();
	}

	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param insid
	 * @param nodeid
	 * @param argvtype
	 * @param argvvalue
	 */
	public WorkFlowInstanceNodeArgvEntity(long insid, long nodeid, int argvtype,
			long argvvalue) {
		super();
		this.insid = insid;
		this.nodeid = nodeid;
		this.argvtype = argvtype;
		this.argvvalue = argvvalue;
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
	 * @return the argvtype
	 */
	public int getArgvtype() {
		return argvtype;
	}

	/**
	 * @param argvtype the argvtype to set
	 */
	public void setArgvtype(int argvtype) {
		this.argvtype = argvtype;
	}

	/**
	 * @return the argvvalue
	 */
	public long getArgvvalue() {
		return argvvalue;
	}

	/**
	 * @param argvvalue the argvvalue to set
	 */
	public void setArgvvalue(long argvvalue) {
		this.argvvalue = argvvalue;
	}
	
	
}
