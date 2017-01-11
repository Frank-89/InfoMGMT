package com.studentmanagement;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class StuUpdDialog extends JDialog implements ActionListener {
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1, jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JPanel jp1, jp2, jp3;

	public StuUpdDialog(Frame owner, String title, boolean modal, StuModel sm, int rowNum) {
		super(owner, title, modal);
		jl1 = new JLabel("stuId");
		jl2 = new JLabel("stuName");
		jl3 = new JLabel("stuSex");
		jl4 = new JLabel("stuAge");
		jl5 = new JLabel("stuNationality");
		jl6 = new JLabel("stuMajor");

		jtf1 = new JTextField();
		jtf1.setText((String) sm.getValueAt(rowNum, 0));
		jtf1.setEditable(false);
		jtf2 = new JTextField();
		jtf2.setText((String) sm.getValueAt(rowNum, 1));
		jtf3 = new JTextField();
		jtf3.setText((String) sm.getValueAt(rowNum, 2));
		jtf4 = new JTextField();
		jtf4.setText(sm.getValueAt(rowNum, 3).toString());
		jtf5 = new JTextField();
		jtf5.setText((String) sm.getValueAt(rowNum, 4));
		jtf6 = new JTextField();
		jtf6.setText((String) sm.getValueAt(rowNum, 5));

		jb1 = new JButton("Edit");
		jb2 = new JButton("Cancel");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);

		jp3.add(jb1);
		jp3.add(jb2);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		jb1.addActionListener(this);
		jb2.addActionListener(this);

		this.setSize(400, 300);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			String sql = "update stu set stuName = ?,stuSex = ?,stuAge = ?,stuNationality = ?,stuMajor = ? where stuId = ?";
			String[] paras = { jtf2.getText(), jtf3.getText(), jtf4.getText(), jtf5.getText(), jtf6.getText(),
					jtf1.getText() };
			StuModel temp = new StuModel();
			if (temp.updStu(sql, paras)) {
				JOptionPane.showMessageDialog(this, "Edit data successfully", "Edit data prompt", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Failed to Edit data", "Edit data prompt", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		} else if (e.getSource() == jb2) {
			this.dispose();
		}
	}
}
