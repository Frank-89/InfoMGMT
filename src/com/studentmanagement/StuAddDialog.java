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
public class StuAddDialog extends JDialog implements ActionListener {
	// define swing components
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1, jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JPanel jp1, jp2, jp3;

	public StuAddDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		jl1 = new JLabel("stuId");
		jl2 = new JLabel("stuName");
		jl3 = new JLabel("stuSex");
		jl4 = new JLabel("stuAge");
		jl5 = new JLabel("stuNationality");
		jl6 = new JLabel("stuMajor");

		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jtf3 = new JTextField();
		jtf4 = new JTextField();
		jtf5 = new JTextField();
		jtf6 = new JTextField();

		jb1 = new JButton("Add");
		jb2 = new JButton("Cancel");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// set layout
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		// add components
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
			StuModel temp = new StuModel();
			String sql = "insert into stu values(?, ?, ?, ?, ?, ?)";
			String[] paras = { jtf1.getText(), jtf2.getText(), jtf3.getText(), jtf4.getText(), jtf5.getText(),
					jtf6.getText() };
			if (!temp.updStu(sql, paras)) {
				JOptionPane.showMessageDialog(this, "Failed to add data", "Add data prompt", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Add data successfully", "Add data prompt", JOptionPane.INFORMATION_MESSAGE);
			}
			this.dispose();
		} else if (e.getSource() == jb2) {
			this.dispose();
		}
	}
}
