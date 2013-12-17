/**   
 * @Title: WorkFlowFactory.java
 * @Package: com.coreworkflow.workflow
 * <p>@description: WorkFlowFactory.java</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月11日 上午11:10:03  
 */

package com.coreworkflow.workflow;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkFlowTaskInterface;
import com.coreworkflow.workflow.entity.WorkFlowEntity;
import com.coreworkflow.workflow.entity.WorkFlowEventEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeArgvEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeEntity;
import com.coreworkflow.workflow.entity.WorkFlowNodeEntity;
import com.coreworkflow.workflow.entity.WorkFlowTaskEntity;
import com.coreworkflow.workflow.loader.WorkflowFactoryInterface;
import com.coreworkflow.workflow.spi.WorkFlowStoreInterface;

/**
 * 工作流工厂
 * <p>@company：wxltsoft</p>
 * <p>@author：lehoons@gmail.com</p>
 * @date：2013年12月11日 上午11:10:03
 */

public class WorkFlowFactory implements WorkflowFactoryInterface {

	/**
	 * 流程持久化存储实现
	 */
	private WorkFlowStoreInterface workflowStroe;

	private static final Log log = LogFactory.getLog(WorkFlowFactory.class);
	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.loader.WorkflowFactoryInterface#getName()
	 */
	@Override
	public String getName() {
		log.debug("工作流工厂");
		return "工作流工厂";
	}

	/* <p>创建新的工作流，返回工作流对象</p>
	 * @see com.coreworkflow.workflow.loader.WorkflowFactoryInterface#createWorkflow(java.lang.String, java.lang.String, long)
	 */
	@Override
	public WorkFlow createWorkflow(String workflowCode,
			String instname, long caller, long orgid, long callpostid) throws WorkFlowFactoryException {
		// 创建一个信的工作流对象
		WorkFlow workflow = new WorkFlow();

		try {
			WorkFlowEntity workflowEntity = workflowStroe.GetWorkFlowEntityByWfCode(workflowCode);
			workflow.setWorkflowEntity(workflowEntity);

			// 获取工作流所有的节点定义
			List<WorkFlowNodeEntity> workflowNodeEntityList = workflowStroe.GetWorkFlowNodesByWfId(workflowEntity.getWfid());
			List<WorkFlowNode> workflowNodeList = new ArrayList<WorkFlowNode>();

			// 组织工作流定义的node和event
			for (WorkFlowNodeEntity workflowNodeEntiry : workflowNodeEntityList) {
				WorkFlowNode workflowNode = new WorkFlowNode();
				workflowNode.setWorkflowNodeEntity(workflowNodeEntiry);
				List<WorkFlowEventEntity> workflowEventEntityList = workflowStroe.GetWorkFlowNodeEventByNodeid(workflowNodeEntiry.getNodeid());

				List<WorkFlowEvent> workflowEventList = new ArrayList<WorkFlowEvent>();

				for (WorkFlowEventEntity workflowEventEntity : workflowEventEntityList) {
					WorkFlowEvent workflowEvent = new WorkFlowEvent();
					workflowEvent.setWorkflowEventEntity(workflowEventEntity);
					workflowEventList.add(workflowEvent);
				}

				workflowNode.setEvnetList(workflowEventList);
				workflowNodeList.add(workflowNode);
			}

			// 组装工作流程定义节点列表
			workflow.setWorkflowNodeList(workflowNodeList);

			// 返回的流程实例编号
			long insid = workflowStroe.CreateWorkFlowInstance(workflowEntity.getWfid(), workflowEntity.getWfcode(), instname, caller);
			// 根据流程实例编号查询流程实例相关内容
			// 创建一个工作流实例对象
			WorkFlowInstance workflowInstance = new WorkFlowInstance();
			// 创建工作流程实例第一个任务节点
			// 创建工作流实例第一个任务，以发起流程人信息创建任务
			long nodeid = workflow.getWorkflowNodeList().get(0).getWorkflowNodeEntity().getNodeid();
			String nodename = workflow.getWorkflowNodeList().get(0).getWorkflowNodeEntity().getNodename();
			String nodedesc = workflow.getWorkflowNodeList().get(0).getWorkflowNodeEntity().getNodedcsc();
			String postid = workflow.getWorkflowNodeList().get(0).getWorkflowNodeEntity().getPostid();

			/**
			 * 创建流程实例第一个节点，返回节点编号
			 */
			long inid = workflowStroe.CreateWorkFlowIntanceNode(insid, nodeid, 0, nodename, nodedesc, postid);

			if (inid > 0) {
				// 创建该流程实例节点需要的参数
				WorkFlowInstanceNodeArgvEntity workflowInstanceNodeArgv = new WorkFlowInstanceNodeArgvEntity();
				workflowInstanceNodeArgv.setInsid(insid);
				workflowInstanceNodeArgv.setNodeid(nodeid);
				workflowInstanceNodeArgv.setArgvtype(1);
				workflowInstanceNodeArgv.setArgvvalue(orgid);

				List<WorkFlowInstanceNodeArgvEntity> params = new ArrayList<WorkFlowInstanceNodeArgvEntity>();
				params.add(workflowInstanceNodeArgv);

				workflowStroe.CreateWorkFlowInstanceNodeArgv(params);
				// 创建第一个任务节点
				long currentTaskid = workflowStroe.CreateWorkFlowTask(insid, nodeid, 0, orgid, callpostid, caller);
				// 获取第一个任务信息
				WorkFlowTaskEntity currentTask = workflowStroe.GetWorkFlowTaskEntity(currentTaskid);

				WorkFlowTask currentworkflowTask = new WorkFlowTask();
				currentworkflowTask.setWorkflowTaskEntity(currentTask);
				currentworkflowTask.setTaskEntryCaller(caller);
				// 设置当前为未完成状态
				currentworkflowTask.setTaskEntryStatus(WorkFlowTaskInterface.WORKFLOW_TASKSTATUS_STATUS_ACTIVE);

				// 获取流程实例节点信息
				WorkFlowInstanceNodeEntity workflowInstanceNodeEntity = workflowStroe.GetWorkFlowInstanceNodeEntityByInId(inid);
				WorkFlowInstanceNode workflowInstanceNode = new WorkFlowInstanceNode();
				workflowInstanceNode.setWorkflowInstanceNode(workflowInstanceNodeEntity);
				// 设置流程实例当前运行节点
				workflowInstance.setCurrentworkflowInstanceNode(workflowInstanceNode);
				workflowInstance.setCurrentworkflowTask(currentworkflowTask);

				// 更新工作流程实例当前运行节点信息
				workflowStroe.SetWorkFlowInstanceCurrentNode(insid, nodeid);
				log.debug("currentTaskid = " + currentTaskid);
				// 设置工作流实例描述类
				workflowInstance.setWorkflowInstanceEntity(workflowStroe.GetWorkFlowInstanceEntity(insid));
				workflow.setWorkflowInstance(workflowInstance);
			}
			log.debug("创建工作流实例成功， 工作流名称：" + workflowEntity.getWfname());
			
			workflow.setWorkflowStroe(workflowStroe);
			return workflow;
		} catch (StoreException e) {
			throw new WorkFlowFactoryException("创建流程失败");
		}
	}

	/* (non-Javadoc)
	 * @see com.coreworkflow.workflow.loader.WorkflowFactoryInterface#renameWorkflow(java.lang.String, java.lang.String)
	 */
	@Override
	public void renameWorkflow(String oldName, String newName) {
	}

	/* <p>获取指定的的工作流，返回工作流对象</p>
	 * @see com.coreworkflow.workflow.loader.WorkflowFactoryInterface#getWorkflow(long)
	 */
	@Override
	public WorkFlow getWorkflow(long instanceid, long taskid)
			throws WorkFlowFactoryException {
		//获取指定的工作流实例信息
		try
		{
			WorkFlowInstanceEntity workflowInstanceEntity = workflowStroe.GetWorkFlowInstanceEntity(instanceid);
			
			if(workflowInstanceEntity == null)
			{
				throw new WorkFlowFactoryException("没有找到该工作流，请检查工作流实例是否存在");
			}
			
			WorkFlowInstance workflowInstance = new WorkFlowInstance();
			workflowInstance.setWorkflowInstanceEntity(workflowInstanceEntity);
			
			//获取工作流引起流程编号
			long wfid = workflowInstanceEntity.getWfid();
			
			WorkFlowEntity workflowEntity = null;
			
			workflowEntity = workflowStroe.GetWorkFlowEntityByWfId(wfid);
			
			//创建工作流
			WorkFlow workflow = new WorkFlow();
			//设置工作流描述
			workflow.setWorkflowEntity(workflowEntity);
			//设置工作流实例信息
			workflow.setWorkflowInstance(workflowInstance);
	
			/**
			 * 获取工作流描述信息
			 */
			List<WorkFlowNodeEntity> workflowNodeEntityList = workflowStroe.GetWorkFlowNodesByWfId(wfid);
			List<WorkFlowNode> workflowNodeList = new ArrayList<WorkFlowNode>();
			for(WorkFlowNodeEntity workflowNodeEntity : workflowNodeEntityList)
			{
				WorkFlowNode workflowNode = new WorkFlowNode();
				workflowNode.setWorkflowNodeEntity(workflowNodeEntity);
				
				List<WorkFlowEventEntity> workflowEventEntityList = workflowStroe.GetWorkFlowNodeEventByNodeid(workflowNodeEntity.getNodeid());
				List<WorkFlowEvent> workflowEventList = new ArrayList<WorkFlowEvent>();
	
				for (WorkFlowEventEntity workflowEventEntity : workflowEventEntityList) {
					WorkFlowEvent workflowEvent = new WorkFlowEvent();
					workflowEvent.setWorkflowEventEntity(workflowEventEntity);
					workflowEventList.add(workflowEvent);
				}
	
				workflowNode.setEvnetList(workflowEventList);			
				workflowNodeList.add(workflowNode);
			}
			
			workflow.setWorkflowNodeList(workflowNodeList);
			
			/**
			 * 获取工作流实例信息
			 */
			List<WorkFlowInstanceNode> workflowInstanceNodeList = new ArrayList<WorkFlowInstanceNode>();
			//根据流程实例编号获取该流程实例下所有的节点信息
			List<WorkFlowInstanceNodeEntity> workflowInstanceNodeEntityList = workflowStroe.GetWorkFlowInstanceNodeEntityListByInstId(instanceid);
			
			for(WorkFlowInstanceNodeEntity workflowInstanceNodeEntity : workflowInstanceNodeEntityList)
			{
				WorkFlowInstanceNode workflowInstanceNode = new WorkFlowInstanceNode();
				workflowInstanceNode.setWorkflowInstanceNode(workflowInstanceNodeEntity);
	
				//查询该节点下的参数信息
				
				List<WorkFlowInstanceNodeArgvEntity> workflowInstanceNodeArgvEntityList = workflowStroe.GetWorkFlowInstanceNodeArgvEntity(workflowInstanceNodeEntity.getInsid(), workflowInstanceNodeEntity.getNodeid());
				
				List<WorkFlowInstanceNodeArgv> workflowInstanceNodeArgvList = new ArrayList<WorkFlowInstanceNodeArgv> ();
				
				for(WorkFlowInstanceNodeArgvEntity workflowInstanceNodeArgvEntity : workflowInstanceNodeArgvEntityList)
				{
					WorkFlowInstanceNodeArgv workflowInstanceNodeArgv = new WorkFlowInstanceNodeArgv();
					workflowInstanceNodeArgv.setWorkflowInstanceNodeArgvEntity(workflowInstanceNodeArgvEntity);
					workflowInstanceNodeArgvList.add(workflowInstanceNodeArgv);
				}
	
				workflowInstanceNode.setWorkflowInstanceNodeArgvList(workflowInstanceNodeArgvList);
				//查询该节点下的任务信息
				List<WorkFlowTaskEntity> worflowTaskEntityList = workflowStroe.GetWorkFlowInstanceNodeEventByInstNodeid(workflowInstanceNodeEntity.getInsid(), workflowInstanceNodeEntity.getNodeid());
				
				List<WorkFlowTask> workflowTaskList = new ArrayList<WorkFlowTask> ();
				
				for(WorkFlowTaskEntity worflowTaskEntity : worflowTaskEntityList)
				{
					WorkFlowTask workflowTask = new WorkFlowTask();
					workflowTask.setWorkflowTaskEntity(worflowTaskEntity);
					workflowTaskList.add(workflowTask);
				}
				
				workflowInstanceNode.setWorkflowTaskList(workflowTaskList);			
				workflowInstanceNodeList.add(workflowInstanceNode);
			}
			
			workflowInstance.setWorkflowInstanceNodeList(workflowInstanceNodeList);
			
			/**
			 * 获取当前任务信息
			 * 根据taskid获取
			 */
			
			WorkFlowTask currentworkflowTask = new WorkFlowTask();
			WorkFlowTaskEntity workflowTaskEntity = workflowStroe.GetWorkFlowTaskEntity(taskid);
			currentworkflowTask.setWorkflowTaskEntity(workflowTaskEntity);
	
			//设置当前任务节点信息
			workflowInstance.setCurrentworkflowTask(currentworkflowTask);
			workflow.setWorkflowStroe(workflowStroe);
			return workflow;
		
		}
		catch(StoreException e)
		{
			throw new WorkFlowFactoryException(e.getMessage());
		}
	}


	/**
	 * <p>获取实体属性</p>
	 *<p>@common：workflowStroe WorkFlowStoreInterface AbstractWorkFlow.java</p>
	 *@return the workflowStroe
	 */
	public WorkFlowStoreInterface getWorkflowStroe() {
		return workflowStroe;
	}

	/**
	 *  <p>设置实体类属性值</p>
	 *  <p>提供方法注入手段</p>
	 * <p>@common：workflowStroe WorkFlowStoreInterface AbstractWorkFlow.java</p>
	 * @param workflowStroe the workflowStroe to set
	 */
	public void setWorkflowStroe(WorkFlowStoreInterface workflowStroe) {
		log.debug("开始注入流程实例持久化存储对象");
		this.workflowStroe = workflowStroe;
	}
}
