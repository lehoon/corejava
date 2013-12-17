/**   
 * WorkFlowTaskEntityRowMapper.java
 * @Package: com.coreworkflow.workflow.spi.mapper
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午11:26:03  
 */

package com.coreworkflow.workflow.spi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coreworkflow.workflow.entity.WorkFlowTaskEntity;

/**
 * 工作流定义任务Mapper类，主要处理通过jdbcTemplate查询后回调自动序列化为实体类
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午11:26:03
 */

public class WorkFlowTaskEntityRowMapper implements RowMapper<WorkFlowTaskEntity> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public WorkFlowTaskEntity mapRow(ResultSet rs, int rownum)
			throws SQLException {
		WorkFlowTaskEntity workflowTaskEntity = new WorkFlowTaskEntity();
		workflowTaskEntity.setTaskid(rs.getLong("TASKID"));
		workflowTaskEntity.setNodeid(rs.getLong("NODEID"));
		workflowTaskEntity.setInsid(rs.getLong("INSID"));
		workflowTaskEntity.setPostid(rs.getLong("POSTID"));
		workflowTaskEntity.setOrgid(rs.getLong("ORGID"));
		workflowTaskEntity.setTaskstatus(rs.getInt("TASKSTATUS"));
		workflowTaskEntity.setStatus(rs.getInt("STATUS"));
		workflowTaskEntity.setCreatedate(rs.getString("CREATEDATE"));
		workflowTaskEntity.setUserid(rs.getLong("USERID"));
		workflowTaskEntity.setFinishdate(rs.getString("FINISHDATE"));
		workflowTaskEntity.setMessage(rs.getString("MESSAGE"));
		workflowTaskEntity.setPrenodeid(rs.getLong("PRENODEID"));
		workflowTaskEntity.setNextnodeid(rs.getLong("NEXTNODEID"));
		return workflowTaskEntity;
	}

}
