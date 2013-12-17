/**   
 * @Title: WorkFlowInstanceEntityRowMapper.java
 * @Package: com.coreworkflow.workflow.spi.mapper
 * @Description: WorkFlowInstanceEntityRowMapper.java
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午9:03:35  
 */

package com.coreworkflow.workflow.spi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coreworkflow.workflow.entity.WorkFlowInstanceEntity;

/**
 * 工作流实例Mapper类，主要处理通过jdbcTemplate查询后回调自动序列化为实体类
 * @company：wxltsoft
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午9:03:35
 */

public class WorkFlowInstanceEntityRowMapper implements RowMapper<WorkFlowInstanceEntity> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public WorkFlowInstanceEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		WorkFlowInstanceEntity workflowInstance = new WorkFlowInstanceEntity();
		
		workflowInstance.setInsid(rs.getLong("INSID"));
		workflowInstance.setWfid(rs.getLong("WFID"));
		workflowInstance.setWfname(rs.getString("WFNAME"));
		workflowInstance.setInsname(rs.getString("INSNAME"));
		workflowInstance.setUserid(rs.getLong("USERID"));
		workflowInstance.setCurrentnoidid(rs.getLong("CURRENTNODEID"));
		workflowInstance.setStatus(rs.getInt("STATUS"));
		return workflowInstance;
	}

}
