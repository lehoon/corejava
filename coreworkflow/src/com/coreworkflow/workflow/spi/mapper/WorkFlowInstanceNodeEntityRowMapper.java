/**   
 * WorkFlowInstanceNodeEntityRowMapper.java
 * @Package: com.coreworkflow.workflow.spi.mapper
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午11:24:43  
 */

package com.coreworkflow.workflow.spi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeEntity;

/**
 * 工作流实例节点Mapper类，主要处理通过jdbcTemplate查询后回调自动序列化为实体类
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午11:24:43
 */

public class WorkFlowInstanceNodeEntityRowMapper implements RowMapper<WorkFlowInstanceNodeEntity> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public WorkFlowInstanceNodeEntity mapRow(ResultSet rs, int rownum)
			throws SQLException {
		WorkFlowInstanceNodeEntity workflowInstanceNodeEntity = new WorkFlowInstanceNodeEntity();
		workflowInstanceNodeEntity.setInid(rs.getLong("INID"));
		workflowInstanceNodeEntity.setInsid(rs.getLong("INSID"));
		workflowInstanceNodeEntity.setIssplit(rs.getInt("ISSPLIT"));
		workflowInstanceNodeEntity.setNodedesc(rs.getString("NODEDESC"));
		workflowInstanceNodeEntity.setNodeid(rs.getLong("NODEID"));
		workflowInstanceNodeEntity.setNodename(rs.getString("NODENAME"));
		workflowInstanceNodeEntity.setPostid(rs.getString("POSTID"));
		workflowInstanceNodeEntity.setStatus(rs.getInt("STATUS"));
		return workflowInstanceNodeEntity;
	}

}
