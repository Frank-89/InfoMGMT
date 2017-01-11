package com.studentmanagement;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class StuModel extends AbstractTableModel {
	@SuppressWarnings("rawtypes")
	Vector rowData, columnNames;

	public boolean updStu(String sql, String[] paras) {
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.updExecute(sql, paras);
	}

	@SuppressWarnings("unchecked")
	public void queryStu(String sql, String[] paras) {
		SqlHelper sqlHelper = null;
		columnNames = new Vector<>();
		columnNames.add("stuId");
		columnNames.add("stuName");
		columnNames.add("stuSex");
		columnNames.add("stuAge");
		columnNames.add("stuNationality");
		columnNames.add("stuMajor");

		rowData = new Vector<>();
		try {
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.queryExectue(sql, paras);

			while (rs.next()) {
				@SuppressWarnings("rawtypes")
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				rowData.add(hang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlHelper.close();
		}
	}

	public int getColumnCount() {
		return this.columnNames.size();
	}

	@Override
	public String getColumnName(int column) {
		return (String) this.columnNames.get(column);
	}

	public int getRowCount() {
		return this.rowData.size();
	}

	@SuppressWarnings("rawtypes")
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}
}
