/**
 * <p>Title: WorkFlowEventEntityRowMapper.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: www.wxltsoft.com</p>
 * @author lehoons@gmail.com
 * @date 2013年12月14日
 * @version 1.0
 */
package com.coreworkflow.workflow.spi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.coreworkflow.workflow.entity.WorkFlowEventEntity;

/**
 * <p>Title: WorkFlowEventEntityRowMapper</p>
 * <p>Description: </p>
 * <p>Company: www.wxltsoft.com</p> 
 * @author	lehoons@gmail.com
 * @date	2013年12月14日
 */
public class WorkFlowEventEntityRowMapper implements RowMapper<WorkFlowEventEntity> {

	/**
	 * <p>Title: mapRow</p>
	 * <p>Description: </p>
	 * @param rs
	 * @param rowNum
	 * @return
	 * @throws SQLException
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public WorkFlowEventEntity mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		WorkFlowEventEntity workflowEventEntity = new WorkFlowEventEntity();
		workflowEventEntity.setEventid(rs.getLong("EVENTID"));
		workflowEventEntity.setEventname(rs.getString("EVENTNAME"));
		workflowEventEntity.setEventdesc(rs.getString("EVENTDESC"));
		workflowEventEntity.setEventtype(rs.getString("EVENTTYPE"));
		workflowEventEntity.setEventhandle(rs.getString("EVENTHANDLE"));
		workflowEventEntity.setEventfunc(rs.getString("EVENTFUNC"));
		workflowEventEntity.setEventargvs(rs.getString("EVENTARGVS"));
		return workflowEventEntity;
	}

}
