/**
 * <p>Title: HandoutTest.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.wxltsoft.com</p>
 * @author lehoons@gmail.com
 * @date 2013年12月17日
 * @version 1.0
 */
package coreworkflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coreworkflow.workflow.WorkFlow;
import com.coreworkflow.workflow.WorkFlowFactory;
import com.coreworkflow.workflow.WorkFlowFactoryException;
import com.coreworkflow.workflow.WorkFlowInstance;
import com.coreworkflow.workflow.entity.WorkFlowEntity;
import com.coreworkflow.workflow.spi.plugin.WorkFlowStoreOraclePlugin;
import com.coreworkflow.workflow.timer.WorkFlowJob;

/**
 * <p>Title: HandoutTest</p>
 * <p>Description: </p>
 * <p>Company: www.wxltsoft.com</p> 
 * @author	lehoons@gmail.com
 * @date	2013年12月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class HandoutTest {

	@Resource
	JdbcTemplate jdbcTemplate;
	//工作流工厂
	WorkFlowFactory workflowFactory;
	@Resource
	WorkFlowJob workflowjob;
	
	@Test
	public void TestMain() throws WorkFlowFactoryException
	{
		//testCreateWorkFlow();
		TestJob();
		//ModifyTaskStatus(1146, 2230);
	}
	
	public void TestJob()
	{
		workflowjob.execute();
	}
	
	public void ModifyTaskStatus(long instid, long taskid) throws WorkFlowFactoryException
	{
		WorkFlow workflow =   workflowFactory.getWorkflow(instid, taskid);
		Map<String, Object> inParams = new HashMap<String, Object> ();
		inParams.put("userid", 303);
		inParams.put("message", "发送审核成功");
		
		boolean dealflag = workflow.SetCurrentTaskStatus(1, inParams);

		Map<Integer, List<Long>> nextNodeParams = new HashMap<Integer, List<Long>> ();
		List<Long> orgList = new ArrayList<Long> ();
		orgList.add((long) 202);
		orgList.add((long) 303);
		orgList.add((long) 404);
		nextNodeParams.put(1, orgList);
		workflow.SetWorkFlowNextNodeTaskArgv(nextNodeParams);
		System.out.println("修改任务节点状态：" + dealflag);
	}
	
	/**
	 * 
	 * <p>Title: TestHandOutAudit</p>
	 * <p>Description: 审核通过</p>
	 * @throws WorkFlowFactoryException
	 * @author	lehoons@gmail.com
	 * @date	2013年12月17日
	 */
	public void TestHandOutAudit() throws WorkFlowFactoryException
	{
		WorkFlow workflow =   workflowFactory.getWorkflow(1127, 2214);
		workflow.getInstanceState();
	}
	
	
	/**
	 * 
	 * <p>Title: testCreateWorkFlow</p>
	 * <p>Description: 图纸发放申请</p>
	 * @throws WorkFlowFactoryException
	 * @author	lehoons@gmail.com
	 * @date	2013年12月17日
	 */
	public void testCreateWorkFlow() throws WorkFlowFactoryException
	{
		System.out.println("其他操作");
		
		WorkFlow workflow = (WorkFlow) workflowFactory.createWorkflow("workflow_handout", "总体设计图专业图纸发放流程", 248, 2, 101);
		
		WorkFlowEntity workflowEntity = workflow.getWorkflowEntity();
		System.out.println(workflowEntity.getWfname());
		WorkFlowInstance workflowInstance = workflow.getWorkflowInstance();
		System.out.println(workflowInstance.getWorkflowInstanceEntity().getInsid());
		System.out.println(workflowInstance.getWorkflowInstanceEntity().getCurrentnoidid());
		System.out.println(workflowInstance.getWorkflowInstanceEntity().getInsname());
		System.out.println(workflowInstance.getWorkflowInstanceEntity().getUserid());

		Map<String, Object> inParams = new HashMap<String, Object> ();
		inParams.put("userid", 3);
		inParams.put("message", "处理任务成功");
		workflow.SetCurrentTaskStatus(4, inParams);
		/**
		 * 设置下步审核的部门列表
		 */
		Map<Integer, List<Long>> nextNodeParams = new HashMap<Integer, List<Long>> ();
		List<Long> orgList = new ArrayList<Long> ();
		orgList.add((long) 888);
		orgList.add((long) 666);
		orgList.add((long) 777);
		nextNodeParams.put(1, orgList);
		workflow.SetWorkFlowNextNodeTaskArgv(nextNodeParams);
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
}
