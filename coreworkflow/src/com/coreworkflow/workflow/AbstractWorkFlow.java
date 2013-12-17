/**   
 * @Title: AbstractWorkFlow.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: AbstractWorkFlow.java</p>
 * @author：lehoons@gmail.com
 * @date：2013年12月5日 下午7:24:31  
 */

package com.coreworkflow.workflow;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.entity.WorkFlowEntity;

/**
 * <p>工作流抽象类，定义工作流的操作流程和暴露给外围的接口</p>
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月5日 下午7:24:31
 */

public class AbstractWorkFlow {

	/**
	 * 工作流程描述定义
	 */
	protected WorkFlowEntity workflowEntity; 

	private static final Log log = LogFactory.getLog(AbstractWorkFlow.class);
	
	
	public String GetWorkFlowName()
	{
		log.debug("");
		return workflowEntity.getWfname();
	}


	/**
	 *@filename AbstractWorkFlow.java
	 *<p>@description 构造函数</p>
	 *@date 2013年12月12日
	 */
	public AbstractWorkFlow() {
		super();
	}


	/**
	 *<p>@description：获取实体属性</p>
	 *@common：workflowEntity WorkFlowEntity AbstractWorkFlow.java
	 *@return the workflowEntity
	 */
	public WorkFlowEntity getWorkflowEntity() {
		return workflowEntity;
	}


	/**
	 * <p>@description： 设置实体类属性值</p>
	 * <p>提供方法注入手段</p>
	 * <p>@common：workflowEntity WorkFlowEntity AbstractWorkFlow.java</p>
	 * @param workflowEntity the workflowEntity to set
	 */
	public void setWorkflowEntity(WorkFlowEntity workflowEntity) {
		this.workflowEntity = workflowEntity;
	}
	
	
	
	
	
	
	
	
	
	
}
