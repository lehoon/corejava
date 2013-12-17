/**   
 * @Title: WorkFlowNode.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: WorkFlowNode.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午4:24:59  
 */

package com.coreworkflow.workflow;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkFlowNodeInterface;
import com.coreworkflow.workflow.entity.WorkFlowNodeEntity;

/**
 * 工作流程引擎的节点实体定义
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午4:24:59
 */

public class WorkFlowNode implements WorkFlowNodeInterface{

	/**
	 * 流程节点描述信息
	 */
	private WorkFlowNodeEntity workflowNodeEntity;

	/**
	 * 节点绑定的事件列表
	 */
	private List<WorkFlowEvent> evnetList;

	private static final Log log = LogFactory.getLog(WorkFlowNode.class);
	
	
	/* 流程节点执行方法，如果有多个则循环调用事件的execute方法，参数是从上层传递过来的，透传给下层
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#execute()
	 */
	@Override
	public void execute(long instid, long taskid, Map<String, Object> inParams) throws WorkflowException {
		
		for(WorkFlowEvent workflowEvent: evnetList)
		{
			log.debug("指定节点的方法开始");
			workflowEvent.execute(instid, taskid, inParams);
			log.debug("指定节点的方法结束");
		}
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getEventList()
	 */
	@Override
	public List<WorkFlowEvent> getEventList() {
		return evnetList;
	}

	/**
	 *<p>@description：获取实体属性
	 *@common：workflowNodeEntity WorkFlowNodeEntity WorkFlowNode.java
	 *@return the workflowNodeEntity
	 */
	public WorkFlowNodeEntity getWorkflowNodeEntity() {
		return workflowNodeEntity;
	}

	/**
	 * <p>@description： 设置实体类属性值</p>
	 * @             提供方法注入手段
	 * @common：workflowNodeEntity WorkFlowNodeEntity WorkFlowNode.java
	 * @param workflowNodeEntity the workflowNodeEntity to set
	 */
	public void setWorkflowNodeEntity(WorkFlowNodeEntity workflowNodeEntity) {
		this.workflowNodeEntity = workflowNodeEntity;
	}

	/**
	 * @return the evnetList
	 */
	public List<WorkFlowEvent> getEvnetList() {
		return evnetList;
	}

	/**
	 * @param evnetList the evnetList to set
	 */
	public void setEvnetList(List<WorkFlowEvent> evnetList) {
		this.evnetList = evnetList;
	}

	/**
	 * <p>Title: getNodeid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getNodeid()
	 */
	@Override
	public long getNodeid() {
		return workflowNodeEntity.getNodeid();
	}

	/**
	 * <p>Title: getWfid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getWfid()
	 */
	@Override
	public long getWfid() {
		return workflowNodeEntity.getWfid();
	}

	/**
	 * <p>Title: getNodename</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getNodename()
	 */
	@Override
	public String getNodename() {
		return workflowNodeEntity.getNodename();
	}

	/**
	 * <p>Title: getNodedcsc</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getNodedcsc()
	 */
	@Override
	public String getNodedcsc() {
		return workflowNodeEntity.getNodedcsc();
	}

	/**
	 * <p>Title: getPostid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getPostid()
	 */
	@Override
	public String getPostid() {
		return workflowNodeEntity.getPostid();
	}

	/**
	 * <p>Title: getRulecontext</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getRulecontext()
	 */
	@Override
	public String getRulecontext() {
		return workflowNodeEntity.getRulecontext();
	}

	/**
	 * <p>Title: getPrenodeid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getPrenodeid()
	 */
	@Override
	public long getPrenodeid() {
		return workflowNodeEntity.getPrenodeid();
	}

	/**
	 * <p>Title: getNextnodeid</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getNextnodeid()
	 */
	@Override
	public long getNextnodeid() {
		return workflowNodeEntity.getNextnodeid();
	}

	/**
	 * <p>Title: getStatus</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getStatus()
	 */
	@Override
	public int getStatus() {
		return workflowNodeEntity.getStatus();
	}

	/**
	 * <p>Title: getCreated</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getCreated()
	 */
	@Override
	public String getCreated() {
		return workflowNodeEntity.getCreated();
	}

	/**
	 * <p>Title: getTimeout</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getTimeout()
	 */
	@Override
	public int getTimeout() {
		return workflowNodeEntity.getTimeout();
	}

	/**
	 * <p>Title: getAutonextnode</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getAutonextnode()
	 */
	@Override
	public int getAutonode() {
		return workflowNodeEntity.getAutonode();
	}

	/**
	 * <p>Title: getXpoint</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getXpoint()
	 */
	@Override
	public int getXpoint() {
		return workflowNodeEntity.getXpoint();
	}

	/**
	 * <p>Title: getYpoint</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.coreworkflow.workflow.basic.WorkFlowNodeInterface#getYpoint()
	 */
	@Override
	public int getYpoint() {
		return workflowNodeEntity.getYpoint();
	}
}
