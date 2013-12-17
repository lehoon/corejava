/**   
 * @Title: WorkFlowEntityRowMapper.java
 * @Package: com.coreworkflow.workflow.spi.mapper
 * @Description: WorkFlowEntityRowMapper.java
 * @author：lehoons@gmail.com
 * @date：2013年12月11日 下午5:40:27  
 */

package com.coreworkflow.workflow.spi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coreworkflow.workflow.entity.WorkFlowEntity;

/**
 * 工作流定义Mapper类，主要处理通过jdbcTemplate查询后回调自动序列化为实体类
 * @param <WorkFlowEntity>
 * @company：wxltsoft
 * @author：lehoons@gmail.com
 * @date：2013年12月11日 下午5:40:27
 */

public class WorkFlowEntityRowMapper implements RowMapper<WorkFlowEntity> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public WorkFlowEntity mapRow(ResultSet rs, int rownum) throws SQLException {
		WorkFlowEntity  workflowEntity = new WorkFlowEntity(); 
		
		workflowEntity.setWfid(rs.getLong("WFID"));
		workflowEntity.setWfname(rs.getString("WFNAME"));
		workflowEntity.setWfcode(rs.getString("WFCODE"));
		workflowEntity.setWfconfig(rs.getString("WFCONFIG"));
		workflowEntity.setWfsn(rs.getInt("WFSN"));
		workflowEntity.setCreatedate(rs.getString("CREATEDATE"));
		workflowEntity.setModifydate(rs.getString("MODIFYDATE"));
		workflowEntity.setWfstatus(rs.getInt("WFSTATUS"));
		return workflowEntity;
	}
}
