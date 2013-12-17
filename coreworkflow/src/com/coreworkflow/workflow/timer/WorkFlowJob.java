/**
 * <p>Title: WorkFlowJob.java</p>
 * <p>Description: 工作流定时任务调度模块</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.wxltsoft.com</p>
 * <p>@author lehoons@gmail.com</p>
 * <p>@date 2013年12月16日</p>
 * <p>@version 1.0</p>
 */
package com.coreworkflow.workflow.timer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.coreworkflow.workflow.basic.WorkFlowInterface;
import com.coreworkflow.workflow.basic.WorkFlowTaskInterface;
import com.coreworkflow.workflow.entity.WorkFlowInstanceEntity;
import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeArgvEntity;
import com.coreworkflow.workflow.entity.WorkFlowNodeEntity;
import com.coreworkflow.workflow.entity.WorkFlowTaskEntity;
import com.coreworkflow.workflow.spi.WorkFlowStoreInterface;

/**
 * <p>Title: WorkFlowJob</p>
 * <p>Description: 工作流定时任务调度模块</p>
 * <p>Company: www.wxltsoft.com</p> 
 * <p>@author	lehoons@gmail.com</p>
 * <p>@date	2013年12月16日</p>
 */
public class WorkFlowJob {

	/**
	 * 工作流处理类
	 */
	private WorkFlowStoreInterface workflowStroe;
	
	private static final Log log = LogFactory.getLog(WorkFlowJob.class);

	/**
	 * <p>Title: execute</p>
	 * <p>Description: 工作流任务调度程序，通过quartz配置的时间去维护工作流任务计划安排</p>
	 */
	public void execute() {
		log.debug("开始执行工作流任务");
		//查询未处理完的工作流实例
		List<WorkFlowInstanceEntity> workflowInstanceEntityList = workflowStroe.GetWorkFlowInstanceEntityListJob(1);
		
		for(WorkFlowInstanceEntity workflowInstanceEntity : workflowInstanceEntityList)
		{
			log.debug("待处理工作流实例编号:" + workflowInstanceEntity.getInsid());
			//查询该工作流实例当前任务处理情况
			List<WorkFlowTaskEntity> workflowTaskEntityList;
				workflowTaskEntityList = workflowStroe.GetWorkFlowInstanceNodeEventByInstNodeid(workflowInstanceEntity.getInsid(), workflowInstanceEntity.getCurrentnoidid());
			
			boolean instanceTaskDealDone = false;
			boolean instanceStopStatus	 = false;

			//合并工作流程任务状态，如果有多个任务的话，则首先合并任务状态后设置实例的状态
			for(WorkFlowTaskEntity workflowTaskEntity : workflowTaskEntityList)
			{
				if(workflowTaskEntity.getTaskstatus() == WorkFlowTaskInterface.WORKFLOW_TASKSTATUS_STATUS_FINISH)
				{
					if(workflowTaskEntity.getStatus() == WorkFlowTaskInterface.WORKFLOW_TASKDEAL_STATUS_SUCESS)
					{
						instanceTaskDealDone = true;
					}
					else if(workflowTaskEntity.getStatus() == WorkFlowTaskInterface.WORKFLOW_TASKDEAL_STATUS_FAIL)
					{
						instanceTaskDealDone = true;
						instanceStopStatus   = true;
						break;
					}
				}
				else
				{
					instanceTaskDealDone = false;
				}
			}
			
			if(instanceStopStatus)
			{
				//流程处理失败，结束流程
				//修改流程实例状态为失败
				workflowStroe.ModifyWorkFlowInstanceStatus(workflowInstanceEntity.getInsid(), WorkFlowInterface.WORKFLOW_INSTANCE_STATUS_FINISH);
				log.debug("修改该流程实例的状态为已完成，实例编号=" + workflowInstanceEntity.getInsid());
				
				List<Long> taskList = new ArrayList<Long>();
				//修改任务节点状态为完成
				for(WorkFlowTaskEntity workflowTaskEntity : workflowTaskEntityList)
				{
					if(workflowTaskEntity.getTaskstatus() != WorkFlowTaskInterface.WORKFLOW_TASKSTATUS_STATUS_FINISH)
					{
						taskList.add(workflowTaskEntity.getTaskid());
					}
				}
				
				workflowStroe.JOBModifyWorkFlowTaskStatus(workflowInstanceEntity.getInsid(), WorkFlowTaskInterface.WORKFLOW_TASKSTATUS_STATUS_FINISH, taskList);
			}
			else if(instanceTaskDealDone)
			{
				//修改流程实例节点状态为已完成
				workflowStroe.JOBModifyWorkFlowInstanceNodeStatus(workflowInstanceEntity.getInsid(), workflowInstanceEntity.getCurrentnoidid(), 1);
				
				//如果该节点下任务都已经处理并且处理成功，则创建下一个任务节点
				//查询下一节点编号和岗位信息
				WorkFlowNodeEntity workflowNodeEntity = workflowStroe.GetWorkFlowNodeEntityByPreNodeid(workflowInstanceEntity.getWfid(), workflowInstanceEntity.getCurrentnoidid());
				//如果下一节点为null， 则说明流程已经结束
				if(workflowNodeEntity != null)
				{
					String postArr[] = workflowNodeEntity.getPostid().split(",");
					
					long nodeid 	= workflowNodeEntity.getNodeid();
					long instid		= workflowInstanceEntity.getInsid();
					int autoExecute = workflowNodeEntity.getAutonode();
					long postid = Long.parseLong(postArr[0]);
					List<WorkFlowInstanceNodeArgvEntity> workflowInstanceNodeArgvEntityList = null;
					
					if(autoExecute == 1)
					{
						//系统自动执行节点，设置默认部门编号为-99
						workflowInstanceNodeArgvEntityList = new ArrayList<WorkFlowInstanceNodeArgvEntity>();
						WorkFlowInstanceNodeArgvEntity workflowInstanceNodeArgvEntity = new WorkFlowInstanceNodeArgvEntity(instid, nodeid, 1, -99);
						workflowInstanceNodeArgvEntityList.add(workflowInstanceNodeArgvEntity);
					}
					else
					{
						//查询该节点关联的参数信息
						workflowInstanceNodeArgvEntityList = workflowStroe.GetWorkFlowInstanceNodeArgvEntity(workflowInstanceEntity.getInsid(), workflowNodeEntity.getNodeid());
					}

					if(null == workflowInstanceNodeArgvEntityList)
					{
						log.debug("该节点关联参数为空");
					}
					else
					{
						int split = workflowInstanceNodeArgvEntityList.size() > 1? 1: 0;
						//创建流程实例下一节点，首先获取节点参数是否存在，如果存在则根据流程节点参数创建任务，最后修改流程当前节点编号
						workflowStroe.CreateWorkFlowIntanceNode(workflowInstanceEntity.getInsid(), workflowNodeEntity.getNodeid(), split, workflowNodeEntity.getNodename(), workflowNodeEntity.getNodedcsc(), workflowNodeEntity.getPostid());
						
						List<WorkFlowTaskEntity> inParams = new ArrayList<WorkFlowTaskEntity> ();
						
						for(WorkFlowInstanceNodeArgvEntity workflowInstanceNodeArgvEntity : workflowInstanceNodeArgvEntityList)
						{
							if(workflowInstanceNodeArgvEntity.getArgvtype() == 1)
							{
								WorkFlowTaskEntity workflowTaskEntity = new WorkFlowTaskEntity();
								workflowTaskEntity.setOrgid(workflowInstanceNodeArgvEntity.getArgvvalue());
								workflowTaskEntity.setInsid(workflowInstanceEntity.getInsid());
								workflowTaskEntity.setNodeid(workflowNodeEntity.getNodeid());
								workflowTaskEntity.setPostid(postid);;
								workflowTaskEntity.setPrenodeid(workflowInstanceEntity.getCurrentnoidid());
								inParams.add(workflowTaskEntity);
							}
						}
						
						workflowStroe.CreateWorkFlowTaskBatch(instid, nodeid, workflowInstanceEntity.getCurrentnoidid(), inParams);
						workflowStroe.ModifyWorkFlowInstanceCurrentNode(instid, nodeid);
						//修改已经运行完的任务节点，更新下级节点编号
						workflowStroe.JOBModifyWorkFlowInstanceTaskNextNode(workflowInstanceEntity.getInsid(), workflowInstanceEntity.getCurrentnoidid(), nodeid);
					}
				}
				else
				{
					//修改流程当前任务下级节点编号
					workflowStroe.JOBModifyWorkFlowInstanceTaskNextNode(workflowInstanceEntity.getInsid(), workflowInstanceEntity.getCurrentnoidid(), -1);
					//修改流程实例状态为完成
					workflowStroe.ModifyWorkFlowInstanceStatus(workflowInstanceEntity.getInsid(), WorkFlowTaskInterface.WORKFLOW_TASKSTATUS_STATUS_FINISH);
					log.debug("修改该流程实例的状态为已完成，实例编号=" + workflowInstanceEntity.getInsid());
				}
			}
			else
			{
				//该节点是多任务节点，未处理全部
				//修改流程实例节点状态为已完成
				workflowStroe.JOBModifyWorkFlowInstanceNodeStatus(workflowInstanceEntity.getInsid(), workflowInstanceEntity.getCurrentnoidid(), 2);
			}
		}
		log.debug("结束执行工作流任务");
	}

	/**
	 * @return the workflowStroe
	 */
	public WorkFlowStoreInterface getWorkflowStroe() {
		return workflowStroe;
	}

	/**
	 * @param workflowStroe the workflowStroe to set
	 */
	public void setWorkflowStroe(WorkFlowStoreInterface workflowStroe) {
		this.workflowStroe = workflowStroe;
	}

}
