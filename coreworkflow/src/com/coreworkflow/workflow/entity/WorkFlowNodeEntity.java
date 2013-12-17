/**   
 * @Title: WorkFlowNodeEntity.java
 * @Package: com.coreworkflow.workflow.entity
 * @Description: WorkFlowNodeEntity.java
 * @author：lehoons@gmail.com
 * @date：2013年12月6日 下午2:45:53  
 */

package com.coreworkflow.workflow.entity;


/**
 * @description：流程节点定义实体类
 * @company：wxltsoft
 * @author：lehoons@gmail.com
 * @date：2013年12月6日 下午2:45:53
 */

public class WorkFlowNodeEntity implements java.io.Serializable{

	/**
	 * @Fields serialVersionUID : 序列化编号
	 */
	
	private static final long serialVersionUID = 6435234928815318357L;
	/** 流程节点定义编号 */
	private long		nodeid;
	/** 流程定义编号 */
	private long		wfid;
	/** 流程节点定义名称 */
	private String		nodename;
	/** 流程节点定义描述 */
	private String		nodedcsc;
	/** 流程节点定义岗位 */
	private String		postid;
	/** 流程节点规则定义 */
	private String		rulecontext;
	/** 流程节点上一节点编号 */
	private long		prenodeid;
	/** 流程下一节点编号 */
	private long		nextnodeid;
	/** 流程节点定义状态 */
	private int			status;
	/** 流程节点创建时间 */
	private String		created;
	/** 流程节点定义超时时间 */
	private int			timeout;
	/** 流程节点是否自动执行下一个节点 */
	private	int			autonode;
	/** 流程节点定义坐标X */
	private	int			xpoint;
	/** 流程节点定义坐标Y */
	private int			ypoint;
	/**
	 *@filename WorkFlowNodeEntity.java
	 *@description 构造函数
	 *@date 2013年12月6日
	 */
	public WorkFlowNodeEntity() {
		super();
	}
	/**
	 *@filename WorkFlowNodeEntity.java
	 * 构造函数
	 *@date 2013年12月6日
	 *@param nodeid
	 *@param wfid
	 *@param nodename
	 *@param nodedcsc
	 *@param postid
	 *@param rulecontext
	 *@param prenodeid
	 *@param nextnodeid
	 *@param status
	 *@param created
	 *@param timeout
	 *@param autonode
	 *@param xpoint
	 *@param ypoint
	 */
	public WorkFlowNodeEntity(long nodeid, long wfid, String nodename,
			String nodedcsc, String postid, String rulecontext, long prenodeid,
			long nextnodeid, int status, String created, int timeout,
			int autonode, int xpoint, int ypoint) {
		super();
		this.nodeid = nodeid;
		this.wfid = wfid;
		this.nodename = nodename;
		this.nodedcsc = nodedcsc;
		this.postid = postid;
		this.rulecontext = rulecontext;
		this.prenodeid = prenodeid;
		this.nextnodeid = nextnodeid;
		this.status = status;
		this.created = created;
		this.timeout = timeout;
		this.autonode = autonode;
		this.xpoint = xpoint;
		this.ypoint = ypoint;
	}
	/**
	 * 获取实体属性
	 *@common：nodeid long WorkFlowNodeEntity.java
	 *@return the nodeid
	 */
	public long getNodeid() {
		return nodeid;
	}
	/**
	 *   设置实体类属性值
	 *   提供方法注入手段
	 * @common：nodeid long WorkFlowNodeEntity.java
	 * @param nodeid the nodeid to set
	 */
	public void setNodeid(long nodeid) {
		this.nodeid = nodeid;
	}
	/**
	 * 获取实体属性
	 *@common：wfid long WorkFlowNodeEntity.java
	 *@return the wfid
	 */
	public long getWfid() {
		return wfid;
	}
	/**
	 *   设置实体类属性值
	 *   提供方法注入手段
	 * @common：wfid long WorkFlowNodeEntity.java
	 * @param wfid the wfid to set
	 */
	public void setWfid(long wfid) {
		this.wfid = wfid;
	}
	/**
	 * 获取实体属性
	 *@common：nodename String WorkFlowNodeEntity.java
	 *@return the nodename
	 */
	public String getNodename() {
		return nodename;
	}
	/**
	 * 设置实体类属性值
	 * 提供方法注入手段
	 * @common：nodename String WorkFlowNodeEntity.java
	 * @param nodename the nodename to set
	 */
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	/**
	 *@description：获取实体属性
	 *@common：nodedcsc String WorkFlowNodeEntity.java
	 *@return the nodedcsc
	 */
	public String getNodedcsc() {
		return nodedcsc;
	}
	/**
	 * 设置实体类属性值
	 * 提供方法注入手段
	 * @common：nodedcsc String WorkFlowNodeEntity.java
	 * @param nodedcsc the nodedcsc to set
	 */
	public void setNodedcsc(String nodedcsc) {
		this.nodedcsc = nodedcsc;
	}
	/**
	 * 获取实体属性
	 *@common：postid long WorkFlowNodeEntity.java
	 *@return the postid
	 */
	public String getPostid() {
		return postid;
	}
	/**
	 * 设置实体类属性值
	 * 提供方法注入手段
	 * @common：postid long WorkFlowNodeEntity.java
	 * @param postid the postid to set
	 */
	public void setPostid(String postid) {
		this.postid = postid;
	}
	/**
	 * 获取实体属性
	 *@common：rulecontext String WorkFlowNodeEntity.java
	 *@return the rulecontext
	 */
	public String getRulecontext() {
		return rulecontext;
	}
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：rulecontext String WorkFlowNodeEntity.java
	 * @param rulecontext the rulecontext to set
	 */
	public void setRulecontext(String rulecontext) {
		this.rulecontext = rulecontext;
	}
	/**
	 * 获取实体属性
	 *@common：prenodeid long WorkFlowNodeEntity.java
	 *@return the prenodeid
	 */
	public long getPrenodeid() {
		return prenodeid;
	}
	/**
	 *  设置实体类属性值
	 *  提供方法注入手段
	 * @common：prenodeid long WorkFlowNodeEntity.java
	 * @param prenodeid the prenodeid to set
	 */
	public void setPrenodeid(long prenodeid) {
		this.prenodeid = prenodeid;
	}
	/**
	 *@description：获取实体属性
	 *@common：nextnodeid long WorkFlowNodeEntity.java
	 *@return the nextnodeid
	 */
	public long getNextnodeid() {
		return nextnodeid;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：nextnodeid long WorkFlowNodeEntity.java
	 * @param nextnodeid the nextnodeid to set
	 */
	public void setNextnodeid(long nextnodeid) {
		this.nextnodeid = nextnodeid;
	}
	/**
	 *@description：获取实体属性
	 *@common：status int WorkFlowNodeEntity.java
	 *@return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：status int WorkFlowNodeEntity.java
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 *@description：获取实体属性
	 *@common：created Date WorkFlowNodeEntity.java
	 *@return the created
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：created Date WorkFlowNodeEntity.java
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	/**
	 *@description：获取实体属性
	 *@common：timeout int WorkFlowNodeEntity.java
	 *@return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：timeout int WorkFlowNodeEntity.java
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	/**
	 *@description：获取实体属性
	 *@common：autonextnode int WorkFlowNodeEntity.java
	 *@return the autonextnode
	 */
	public int getAutonode() {
		return autonode;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：autonextnode int WorkFlowNodeEntity.java
	 * @param autonextnode the autonextnode to set
	 */
	public void setAutonode(int autonode) {
		this.autonode = autonode;
	}
	/**
	 *@description：获取实体属性
	 *@common：xpoint int WorkFlowNodeEntity.java
	 *@return the xpoint
	 */
	public int getXpoint() {
		return xpoint;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：xpoint int WorkFlowNodeEntity.java
	 * @param xpoint the xpoint to set
	 */
	public void setXpoint(int xpoint) {
		this.xpoint = xpoint;
	}
	/**
	 *@description：获取实体属性
	 *@common：ypoint int WorkFlowNodeEntity.java
	 *@return the ypoint
	 */
	public int getYpoint() {
		return ypoint;
	}
	/**
	 * @description： 设置实体类属性值
	 * @             提供方法注入手段
	 * @common：ypoint int WorkFlowNodeEntity.java
	 * @param ypoint the ypoint to set
	 */
	public void setYpoint(int ypoint) {
		this.ypoint = ypoint;
	}
	
	
	
}
