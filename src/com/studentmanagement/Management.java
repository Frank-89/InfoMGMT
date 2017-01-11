package com.studentmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Management extends JFrame implements ActionListener {
	// define components
	JPanel jp1, jp2;
	JLabel jl1;
	JButton jb1, jb2, jb3, jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new Management();
	}

	// constructor
	public Management() {
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("Query");
		jb1.addActionListener(this);
		jl1 = new JLabel("Please enter name: ");

		// add components to jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jp2 = new JPanel();
		jb2 = new JButton("Add");
		jb2.addActionListener(this);
		jb3 = new JButton("Edit");
		jb3.addActionListener(this);
		jb4 = new JButton("Delete");
		jb4.addActionListener(this);
		
		// add components to jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);

		// create a data model object
		sm = new StuModel();
		String[] paras = { "1" };
		sm.queryStu("select * from stu where 1 = ?", paras);

		// initialization JTable
		jt = new JTable(sm);

		// initialization JScrollPane
		jsp = new JScrollPane(jt);

		// add jsp to jframe
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");

		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			String name = this.jtf.getText();
			String sql = "select * from stu where stuName = ?";
			String[] paras = { name };
			// refresh data model
			sm = new StuModel();
			sm.queryStu(sql, paras);
			// refresh JTable
			jt.setModel(sm);
		} else if (e.getSource() == jb2) {
			@SuppressWarnings("unused")
			StuAddDialog sa = new StuAddDialog(this, "Add student", true);
			sm = new StuModel();
			String[] paras2 = { "1" };
			sm.queryStu("select * from stu where 1 = ?", paras2);
			jt.setModel(sm);
		} else if (e.getSource() == jb3) {
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please select one row", "Prompt", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			new StuUpdDialog(this, "Edit student's information", true, sm, rowNum);
			sm = new StuModel();
			String[] paras2 = { "1" };
			sm.queryStu("select * from stu where 1=?", paras2);
			jt.setModel(sm);
		} else if (e.getSource() == jb4) {
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "Please select one row", "Prompt", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			String stuId = (String) sm.getValueAt(rowNum, 0);
			String sql = "delete from stu where stuid = ?";
			String[] paras = { stuId };
			StuModel temp = new StuModel();
			if (temp.updStu(sql, paras)) {
				JOptionPane.showMessageDialog(this, "Delete data successfully", "Delete data prompt", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Failed to delete data", "Delete data prompt", JOptionPane.ERROR_MESSAGE);
			}
			sm = new StuModel();
			String[] paras2 = { "1" };
			sm.queryStu("select * from stu where 1 = ?", paras2);
			jt.setModel(sm);
		}
	}
}
