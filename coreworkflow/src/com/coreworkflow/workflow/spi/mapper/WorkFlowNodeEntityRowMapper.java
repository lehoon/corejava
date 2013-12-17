/**   
 * WorkFlowNodeEntityRowMapper.java
 * @Package: com.coreworkflow.workflow.spi.mapper
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午11:25:31  
 */

package com.coreworkflow.workflow.spi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coreworkflow.workflow.entity.WorkFlowNodeEntity;

/**
 * 工作流定义节点Mapper类，主要处理通过jdbcTemplate查询后回调自动序列化为实体类
 * @author：lehoons@gmail.com
 * @date：2013年12月12日 上午11:25:31
 */

public class WorkFlowNodeEntityRowMapper implements RowMapper<WorkFlowNodeEntity> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public WorkFlowNodeEntity mapRow(ResultSet rs, int rownum)
			throws SQLException {
		WorkFlowNodeEntity workflowNodeEntity = new WorkFlowNodeEntity();
		workflowNodeEntity.setNodeid(rs.getLong("NODEID"));
		workflowNodeEntity.setWfid(rs.getLong("WFID"));
		workflowNodeEntity.setNodename(rs.getString("NODENAME"));
		workflowNodeEntity.setNodedcsc(rs.getString("NODEDESC"));
		workflowNodeEntity.setPostid(rs.getString("POSTID"));
		workflowNodeEntity.setRulecontext(rs.getString("RULECONTEXT"));
		workflowNodeEntity.setPrenodeid(rs.getLong("PRENODEID"));
		workflowNodeEntity.setNextnodeid(rs.getLong("NEXTNODEID"));
		workflowNodeEntity.setStatus(rs.getInt("STATUS"));
		workflowNodeEntity.setCreated(rs.getString("CREATEDATE"));
		workflowNodeEntity.setTimeout(rs.getInt("TIMEOUT"));
		workflowNodeEntity.setAutonode(rs.getInt("AUTONODE"));
		return workflowNodeEntity;
	}

}
