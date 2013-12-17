/**
 * <p>Title: WorkFlowInstanceNodeArgv.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.wxltsoft.com</p>
 * @author lehoons@gmail.com
 * @date 2013年12月13日
 * @version 1.0
 */
package com.coreworkflow.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkFlowInstanceNodeArgvInterface;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeArgvEntity;

/**
 * <p>Title: WorkFlowInstanceNodeArgv</p>
 * <p>Description: 工作流程实例节点参数包装类，主要封装了工作流程实例节点参数类，和提供一个帮助使用函数以及对
 * 					工作流实例节点参数类的一些处理手段</p>
 * <p>Company: www.wxltsoft.com</p> 
 * @author	lehoons@gmail.com
 * @date	2013年12月13日
 */
public class WorkFlowInstanceNodeArgv implements WorkFlowInstanceNodeArgvInterface{

	/**
	 * 工作流程实例节点参数类
	 */
	private WorkFlowInstanceNodeArgvEntity workflowInstanceNodeArgvEntity;
	
	private static final Log log = LogFactory.getLog(WorkFlowInstanceNodeArgv.class);

	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 */
	public WorkFlowInstanceNodeArgv() {
		super();
	}

	/**
	 * @return the workflowInstanceNodeArgvEntity
	 */
	public WorkFlowInstanceNodeArgvEntity getWorkflowInstanceNodeArgvEntity() {
		log.debug("返回参数实体类");
		return workflowInstanceNodeArgvEntity;
	}

	/**
	 * @param workflowInstanceNodeArgvEntity the workflowInstanceNodeArgvEntity to set
	 */
	public void setWorkflowInstanceNodeArgvEntity(
			WorkFlowInstanceNodeArgvEntity workflowInstanceNodeArgvEntity) {
		this.workflowInstanceNodeArgvEntity = workflowInstanceNodeArgvEntity;
	}

	/**
	 * <p>Title: getInsid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeArgvInterface#getInsid()
	 */
	@Override
	public long getInsid() {
		return workflowInstanceNodeArgvEntity.getInsid();
	}

	/**
	 * <p>Title: getNodeid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeArgvInterface#getNodeid()
	 */
	@Override
	public long getNodeid() {
		return workflowInstanceNodeArgvEntity.getNodeid();
	}

	/**
	 * <p>Title: getArgvtype</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeArgvInterface#getArgvtype()
	 */
	@Override
	public int getArgvtype() {
		return workflowInstanceNodeArgvEntity.getArgvtype();
	}

	/**
	 * <p>Title: getArgvvalue</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowInstanceNodeArgvInterface#getArgvvalue()
	 */
	@Override
	public long getArgvvalue() {
		return workflowInstanceNodeArgvEntity.getArgvvalue();
	}
	
	
	public String toString()
	{
		return workflowInstanceNodeArgvEntity.toString();
	}
}
