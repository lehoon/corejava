/**
 * <p>Title: WorkFlowInstanceNodeArgvEntityRowMapper.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.wxltsoft.com</p>
 * @author lehoons@gmail.com
 * @date 2013年12月13日
 * @version 1.0
 */
package com.coreworkflow.workflow.spi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coreworkflow.workflow.entity.WorkFlowInstanceNodeArgvEntity;

/**
 * <p>Title: WorkFlowInstanceNodeArgvEntityRowMapper</p>
 * <p>Description: </p>
 * <p>Company: www.wxltsoft.com</p> 
 * @author	lehoons@gmail.com
 * @date	2013年12月13日
 */
public class WorkFlowInstanceNodeArgvEntityRowMapper implements RowMapper<WorkFlowInstanceNodeArgvEntity> {

	/**
	 * <p>Title: mapRow</p>
	 * <p>Description: </p>
	 * @param rs
	 * @param rownum
	 * @return WorkFlowInstanceNodeArgvEntity
	 * @throws SQLException
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public WorkFlowInstanceNodeArgvEntity mapRow(ResultSet rs, int rownum)
			throws SQLException {
		WorkFlowInstanceNodeArgvEntity workflowInstanceNodeArgvEntity = new WorkFlowInstanceNodeArgvEntity();
		workflowInstanceNodeArgvEntity.setInsid(rs.getLong("INSID"));
		workflowInstanceNodeArgvEntity.setNodeid(rs.getLong("NODEID"));
		workflowInstanceNodeArgvEntity.setArgvtype(rs.getInt("ARGVTYPE"));
		workflowInstanceNodeArgvEntity.setArgvvalue(rs.getLong("ARGVALUE"));
		return workflowInstanceNodeArgvEntity;
	}

}
