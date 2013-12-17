/**   
 * WorkFlowEventEntityRowMapper.java
 * @Package: com.coreworkflow.workflow.entity
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午11:28:58  
 */

package com.coreworkflow.workflow.entity;

import java.io.Serializable;

/**
 * 工作流定义事件实体类
 * 在流程节点上关联事件定义类，通过该事件处理流程中每一个节点
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午11:28:58
 */

public class WorkFlowEventEntity implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列化编号
	 */
	
	private static final long serialVersionUID = -5588570988660449046L;

	/** 
	 *事件编号
	 */
	private long		eventid;
	/**
	 * 事件名称
	 */
	private String		eventname;
	/**
	 * 事件描述
	 */
	private String		eventdesc;
	/**
	 * 事件类型
	 */
	private String		eventtype;
	/**
	 * 事件处理句柄
	 */
	private String		eventhandle;
	/**
	 * 事件处理函数
	 */
	private String		eventfunc;
	/**
	 * 事件参数
	 */
	private String		eventargvs;
	/**
	 *@filename WorkFlowEventEntityRowMapper.java
	 *@description 构造函数
	 *@date 2013年12月12日
	 */
	public WorkFlowEventEntity() {
		super();
	}
	/**
	 *@filename WorkFlowEventEntityRowMapper.java
	 *@description 构造函数
	 *@date 2013年12月12日
	 *@param eventid
	 *@param eventname
	 *@param eventdesc
	 *@param eventtype
	 *@param eventhandle
	 *@param eventfunc
	 *@param eventargvs
	 */
	public WorkFlowEventEntity(long eventid, String eventname,
			String eventdesc, String eventtype, String eventhandle,
			String eventfunc, String eventargvs) {
		super();
		this.eventid = eventid;
		this.eventname = eventname;
		this.eventdesc = eventdesc;
		this.eventtype = eventtype;
		this.eventhandle = eventhandle;
		this.eventfunc = eventfunc;
		this.eventargvs = eventargvs;
	}
	/**
	 * 获取实体属性
	 *@common：eventid long WorkFlowEventEntityRowMapper.java
	 *@return the eventid
	 */
	public long getEventid() {
		return eventid;
	}
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：eventid long WorkFlowEventEntityRowMapper.java
	 * @param eventid the eventid to set
	 */
	public void setEventid(long eventid) {
		this.eventid = eventid;
	}
	/**
	 * 获取实体属性
	 *@common：eventname String WorkFlowEventEntityRowMapper.java
	 *@return the eventname
	 */
	public String getEventname() {
		return eventname;
	}
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：eventname String WorkFlowEventEntityRowMapper.java
	 * @param eventname the eventname to set
	 */
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	/**
	 * 获取实体属性
	 *@common：eventdesc String WorkFlowEventEntityRowMapper.java
	 *@return the eventdesc
	 */
	public String getEventdesc() {
		return eventdesc;
	}
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：eventdesc String WorkFlowEventEntityRowMapper.java
	 * @param eventdesc the eventdesc to set
	 */
	public void setEventdesc(String eventdesc) {
		this.eventdesc = eventdesc;
	}
	/**
	 * 获取实体属性
	 *@common：eventtype String WorkFlowEventEntityRowMapper.java
	 *@return the eventtype
	 */
	public String getEventtype() {
		return eventtype;
	}
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：eventtype String WorkFlowEventEntityRowMapper.java
	 * @param eventtype the eventtype to set
	 */
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	/**
	 * 获取实体属性
	 *@common：eventhandle String WorkFlowEventEntityRowMapper.java
	 *@return the eventhandle
	 */
	public String getEventhandle() {
		return eventhandle;
	}
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：eventhandle String WorkFlowEventEntityRowMapper.java
	 * @param eventhandle the eventhandle to set
	 */
	public void setEventhandle(String eventhandle) {
		this.eventhandle = eventhandle;
	}
	/**
	 * 获取实体属性
	 *@common：eventfunc String WorkFlowEventEntityRowMapper.java
	 *@return the eventfunc
	 */
	public String getEventfunc() {
		return eventfunc;
	}
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：eventfunc String WorkFlowEventEntityRowMapper.java
	 * @param eventfunc the eventfunc to set
	 */
	public void setEventfunc(String eventfunc) {
		this.eventfunc = eventfunc;
	}
	/**
	 * 获取实体属性
	 *@common：eventargvs String WorkFlowEventEntityRowMapper.java
	 *@return the eventargvs
	 */
	public String getEventargvs() {
		return eventargvs;
	}
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：eventargvs String WorkFlowEventEntityRowMapper.java
	 * @param eventargvs the eventargvs to set
	 */
	public void setEventargvs(String eventargvs) {
		this.eventargvs = eventargvs;
	}
	
	
	
}
