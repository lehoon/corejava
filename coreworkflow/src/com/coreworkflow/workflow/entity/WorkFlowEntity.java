/**   
 * @Title: WorkFlowEntity.java
 * @Package: com.coreworkflow.workflow.entity
 * @Description: WorkFlowEntity.java
 * @author：lehoons@gmail.com
 * @date：2013年12月6日 下午2:16:56  
 */

package com.coreworkflow.workflow.entity;

import java.util.List;

import com.coreworkflow.workflow.WorkFlowNode;

/**
 * 工作流实体类
 * @company：wxltsoft
 * @author：lehoons@gmail.com
 * @date：2013年12月6日 下午2:16:56
 */

public class WorkFlowEntity implements java.io.Serializable{

	/**
	 * @Fields serialVersionUID : 序列化编号
	 */
	
	private static final long serialVersionUID = 2325890751062455931L;
	/** 流程定义编号 */
	private long   	wfid;
	/** 流程定义编码 */
	private String 	wfcode;
	/** 流程定义名称 */
	private String 	wfname;
	/** 流程定义配置 */
	private String 	wfconfig;
	/** 流程定义编号 */
	private int    	wfsn;
	/** 流程定义状态 */
	private int    	wfstatus;
	/** 流程定义创建时间 */
	private String  createdate;
	/** 流程定义修改时间 */
	private String  modifydate;

	/** 流程中节点列表*/
	private List<WorkFlowNode> workflowNodeList;
	
	/**
	 *@filename：WorkFlowEntity.java
	 *@description：构造函数
	 *@date：2013年12月6日
	 *@param：
	 */
	public WorkFlowEntity() {
		super();
	}
	/**
	 *@filename：WorkFlowEntity.java
	 *@description：构造函数
	 *@date：2013年12月6日
	 *@param wfid
	 *@param wfcode
	 *@param wfname
	 *@param wfconfig
	 *@param wfsn
	 *@param wfstatus
	 *@param createdate
	 *@param modifydate
	 */
	public WorkFlowEntity(long wfid, String wfcode, String wfname,
			String wfconfig, int wfsn, int wfstatus, String createdate,
			String modifydate) {
		super();
		this.wfid = wfid;
		this.wfcode = wfcode;
		this.wfname = wfname;
		this.wfconfig = wfconfig;
		this.wfsn = wfsn;
		this.wfstatus = wfstatus;
		this.createdate = createdate;
		this.modifydate = modifydate;
	}
	/**
	 *@description：获取实体属性
	 *@common：wfid long WorkFlowEntity.java
	 *@return the wfid
	 */
	public long getWfid() {
		return wfid;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：wfid long WorkFlowEntity.java
	 * @param wfid the wfid to set
	 */
	public void setWfid(long wfid) {
		this.wfid = wfid;
	}
	/**
	 *@description：获取实体属性
	 *@common：wfcode String WorkFlowEntity.java
	 *@return the wfcode
	 */
	public String getWfcode() {
		return wfcode;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：wfcode String WorkFlowEntity.java
	 * @param wfcode the wfcode to set
	 */
	public void setWfcode(String wfcode) {
		this.wfcode = wfcode;
	}
	/**
	 *@description：获取实体属性
	 *@common：wfname String WorkFlowEntity.java
	 *@return the wfname
	 */
	public String getWfname() {
		return wfname;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：wfname String WorkFlowEntity.java
	 * @param wfname the wfname to set
	 */
	public void setWfname(String wfname) {
		this.wfname = wfname;
	}
	/**
	 *@description：获取实体属性
	 *@common：wfconfig String WorkFlowEntity.java
	 *@return the wfconfig
	 */
	public String getWfconfig() {
		return wfconfig;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：wfconfig String WorkFlowEntity.java
	 * @param wfconfig the wfconfig to set
	 */
	public void setWfconfig(String wfconfig) {
		this.wfconfig = wfconfig;
	}
	/**
	 *@description：获取实体属性
	 *@common：wfsn int WorkFlowEntity.java
	 *@return the wfsn
	 */
	public int getWfsn() {
		return wfsn;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：wfsn int WorkFlowEntity.java
	 * @param wfsn the wfsn to set
	 */
	public void setWfsn(int wfsn) {
		this.wfsn = wfsn;
	}
	/**
	 *@description：获取实体属性
	 *@common：wfstatus int WorkFlowEntity.java
	 *@return the wfstatus
	 */
	public int getWfstatus() {
		return wfstatus;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：wfstatus int WorkFlowEntity.java
	 * @param wfstatus the wfstatus to set
	 */
	public void setWfstatus(int wfstatus) {
		this.wfstatus = wfstatus;
	}
	/**
	 *@description：获取实体属性
	 *@common：createdate Date WorkFlowEntity.java
	 *@return the createdate
	 */
	public String getCreatedate() {
		return createdate;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：createdate Date WorkFlowEntity.java
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	/**
	 *@description：获取实体属性
	 *@common：modifydate Date WorkFlowEntity.java
	 *@return the modifydate
	 */
	public String getModifydate() {
		return modifydate;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：modifydate Date WorkFlowEntity.java
	 * @param modifydate the modifydate to set
	 */
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	/**
	 * @return the workflowNodeList
	 */
	public List<WorkFlowNode> getWorkflowNodeList() {
		return workflowNodeList;
	}
	/**
	 * @param workflowNodeList the workflowNodeList to set
	 */
	public void setWorkflowNodeList(List<WorkFlowNode> workflowNodeList) {
		this.workflowNodeList = workflowNodeList;
	}


}
