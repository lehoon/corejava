/**   
 * @文件名称: TestJdbc.java
 * @类路径: coreworkflow
 * @描述: 
 * @作者：lehoons@gmail.com
 * @时间：2013年12月4日 下午6:09:38  
 */



package coreworkflow;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coreworkflow.workflow.WorkFlow;
import com.coreworkflow.workflow.WorkFlowFactory;
import com.coreworkflow.workflow.WorkFlowFactoryException;
import com.coreworkflow.workflow.WorkFlowInstance;
import com.coreworkflow.workflow.entity.WorkFlowEntity;
import com.coreworkflow.workflow.spi.plugin.WorkFlowStoreOraclePlugin;
import com.coreworkflow.workflow.timer.WorkFlowJob;

/**
 * @类功能说明：
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：wxltsoft
 * @作者：lehoons@gmail.com
 * @创建时间：2013年12月4日 下午6:09:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestJdbc extends AbstractJUnit4SpringContextTests{

	@Resource
	JdbcTemplate jdbcTemplate;
	//工作流工厂
	WorkFlowFactory workflowFactory;
	@Resource
	WorkFlowJob workflowjob;
	
	@Test
	public void testJob()
	{
		workflowjob.execute();
	}
	
	
	//@Test
	public void testGetWorkflow()
	{
		try {
			WorkFlow workflow =   workflowFactory.getWorkflow(1127, 2214);
			
			System.out.println(workflow.GetWorkFlowName());
			System.out.println(workflow.getWorkflowNodeList().size());
			System.out.println(workflow.getWorkflowInstance().getCurrentworkflowTask().getTaskid());
			System.out.println(workflow.getWorkflowInstance().getCurrentworkflowTask().getNodeId());
			System.out.println(workflow.getWorkflowInstance().getCurrentworkflowTask().getPostid());
			System.out.println(workflow.getWorkflowInstance().getCurrentworkflowTask().getStartDate());
			
			Map<String, Object> inParams = new HashMap<String, Object> ();
			inParams.put("userid", 3);
			inParams.put("message", "处理任务成功");
			
			boolean dealflag = workflow.SetCurrentTaskStatus(4, inParams);
			/*
			Map<Integer, List<Long>> nextNodeParams = new HashMap<Integer, List<Long>> ();
			List<Long> orgList = new ArrayList<Long> ();
			orgList.add((long) 2);
			orgList.add((long) 3);
			orgList.add((long) 4);
			nextNodeParams.put(1, orgList);
			workflow.SetWorkFlowNextNodeTaskArgv(nextNodeParams);
			*/
			System.out.println("修改任务节点状态：" + dealflag);
			
		} catch (WorkFlowFactoryException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//@Test
	public void test1() throws WorkFlowFactoryException
	{
		System.out.println("其他操作");
		
		WorkFlow workflow = (WorkFlow) workflowFactory.createWorkflow("workflow_upload", "图纸上传流程", 3, 2, 1);
		
		WorkFlowEntity workflowEntity = workflow.getWorkflowEntity();
		System.out.println(workflowEntity.getWfname());
		WorkFlowInstance workflowInstance = workflow.getWorkflowInstance();
		System.out.println(workflowInstance.getWorkflowInstanceEntity().getInsid());
		System.out.println(workflowInstance.getWorkflowInstanceEntity().getCurrentnoidid());
		System.out.println(workflowInstance.getWorkflowInstanceEntity().getInsname());
		System.out.println(workflowInstance.getWorkflowInstanceEntity().getUserid());
	}
	
	@Before
	public void connect() throws WorkFlowFactoryException
	{
		workflowFactory = new WorkFlowFactory();
		WorkFlowStoreOraclePlugin workflowStroe = new WorkFlowStoreOraclePlugin();
		workflowFactory.setWorkflowStroe(workflowStroe);
		workflowStroe.setJdbcTemplate(jdbcTemplate);
		System.out.println("连接数据库");
	}
	
	@After
	public void close()
	{
		System.out.println("关闭数据库");
		
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	/**
	 * @return the workflowFactory
	 */
	public WorkFlowFactory getWorkflowFactory() {
		return workflowFactory;
	}


	/**
	 * @param workflowFactory the workflowFactory to set
	 */
	public void setWorkflowFactory(WorkFlowFactory workflowFactory) {
		this.workflowFactory = workflowFactory;
	}


	/**
	 * @return the workflowjob
	 */
	public WorkFlowJob getWorkflowjob() {
		return workflowjob;
	}


	/**
	 * @param workflowjob the workflowjob to set
	 */
	public void setWorkflowjob(WorkFlowJob workflowjob) {
		this.workflowjob = workflowjob;
	}
	
	
	
}
