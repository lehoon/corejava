/**
 * <p>Title: WorkFlowEvent.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.wxltsoft.com</p>
 * @author lehoons@gmail.com
 * @date 2013年12月12日
 * @version 1.0
 */
package com.coreworkflow.workflow;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkFlowEventInterface;
import com.coreworkflow.workflow.entity.WorkFlowEventEntity;

/**
 * <p>Title: WorkFlowEvent</p>
 * <p>Description: 流程引擎定义流程的节点事件描述包装类
 *    想要获取到事件的详细内容，应该在该包装类获取时间的详细定义再次进行处理
 * </p>
 * <p>Company: www.wxltsoft.com</p> 
 * @author	lehoons@gmail.com
 * @date	2013年12月12日
 */
public class WorkFlowEvent implements WorkFlowEventInterface{

	/**
	 * 流程节点帮顶的事件定义描述类
	 */
	private WorkFlowEventEntity workflowEventEntity;

	private static final Log log = LogFactory.getLog(WorkFlowEvent.class);
	/**
	 * <p>Title: </p>
	 * <p>Description: </p>
	 */
	public WorkFlowEvent() {
		super();
	}

	/**
	 * @return the workflowEventEntity
	 */
	public WorkFlowEventEntity getWorkflowEventEntity() {
		return workflowEventEntity;
	}

	/**
	 * @param workflowEventEntity the workflowEventEntity to set
	 */
	public void setWorkflowEventEntity(WorkFlowEventEntity workflowEventEntity) {
		
		log.debug("");
		this.workflowEventEntity = workflowEventEntity;
	}

	/**
	 * <p>Title: execute</p>
	 * <p>Description: </p>
	 * @param instid
	 * @param taskid
	 * @param inParams
	 * @throws WorkflowException
	 * @see com.coreworkflow.workflow.basic.WorkFlowEventInterface#execute(long, long, java.util.Map)
	 */
	@Override
	public void execute(long instid, long taskid, Map<String, Object> inParams)
			throws WorkflowException {
		log.debug("事件执行方法" + workflowEventEntity.getEventhandle() + "." + workflowEventEntity.getEventfunc());
	}

	/**
	 * <p>Title: getEventid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowEventInterface#getEventid()
	 */
	@Override
	public long getEventid() {
		return workflowEventEntity.getEventid();
	}

	/**
	 * <p>Title: getEventname</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowEventInterface#getEventname()
	 */
	@Override
	public String getEventname() {
		return workflowEventEntity.getEventname();
	}

	/**
	 * <p>Title: getEventdesc</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowEventInterface#getEventdesc()
	 */
	@Override
	public String getEventdesc() {
		return workflowEventEntity.getEventdesc();
	}

	/**
	 * <p>Title: getEventtype</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowEventInterface#getEventtype()
	 */
	@Override
	public String getEventtype() {
		return workflowEventEntity.getEventtype();
	}

	/**
	 * <p>Title: getEventhandle</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowEventInterface#getEventhandle()
	 */
	@Override
	public String getEventhandle() {
		return workflowEventEntity.getEventhandle();
	}

	/**
	 * <p>Title: getEventfunc</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowEventInterface#getEventfunc()
	 */
	@Override
	public String getEventfunc() {
		return workflowEventEntity.getEventfunc();
	}

	/**
	 * <p>Title: getEventargvs</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowEventInterface#getEventargvs()
	 */
	@Override
	public String getEventargvs() {
		return workflowEventEntity.getEventargvs();
	}
	
	
	
	
}
