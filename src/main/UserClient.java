package main;

import frame.Login;
import frame.addCourse;
import frame.addSc;
import frame.addStudent;
import helper.mSql;
import helper.myActionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import util.Lesson;
import util.Sc;
import util.Student;

import java.awt.FlowLayout;

import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JTextArea;

public class UserClient extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel; // 表格模型对象
	private static JTable table;
	public static String currentOp;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton btnNewButton;
	private JTextField textField;
	private JFrame f = this;
	private JTextArea textArea;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnChangeone;
	private Boolean state = true;
	private Boolean state1 = true;
	private String key1;
	private String key2;
	private JButton btnNewButton_3;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserClient frame = new UserClient();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	// 更新table
	private void rewriteTable(List cl, List co) {
		Vector<String> colHeader = new Vector<String>();
		Iterator it1 = cl.iterator();
		while (it1.hasNext()) {
			String s = it1.next().toString();
			colHeader.add(s);
		}
		// colHeader.add("Operate");
		Vector<Vector<String>> dataVec = new Vector<Vector<String>>();
		updateVector uv = new updateVector();
		dataVec = uv.getData(co);

		((DefaultTableModel) table.getModel()).getDataVector().clear();
		tableModel = new DefaultTableModel(dataVec, colHeader);

		this.table.setModel(tableModel);

		Action action = new AbstractAction() {

			public void actionPerformed(ActionEvent e) {

				TableCellListener tcl = (TableCellListener) e.getSource();
				// System.out.printf("cell changed%n");
				// System.out.println("Row   : " + tcl.getRow());
				// System.out.println("Column: " + tcl.getColumn());
				// System.out.println("Old   : " + tcl.getOldValue());
				// System.out.println("New   : " + tcl.getNewValue());

				int selectedRow = UserClient.table.getSelectedRow();
				int changeCol = tcl.getColumn();
				if (changeCol == 0 && state == true) {
					state = false;
					key1 = (String) tcl.getOldValue();
				}
				if (state == true) {
					key1 = (String) UserClient.table.getValueAt(selectedRow, 0);
				}

				if (getCurrentTab() == 3 && changeCol == 1 && state1 == true) {
					state1 = false;
					key2 = (String) tcl.getOldValue();
				}
				if (state1 == true) {
					key2 = (String) UserClient.table.getValueAt(selectedRow, 1);
				}

				System.out.println("key1  :" + key1 + "   key2:" + key2);
			}
		};
		TableCellListener tcl = new TableCellListener(table, action);

	}

	/* 获取当前是哪张表 */
	private int getCurrentTab() {
		int res = 0;
		String value = comboBox.getSelectedItem().toString();
		if (value.equals("学生表"))
			res = 1;
		else if (value.equals("课程表"))
			res = 2;
		else
			res = 3;
		return res;
	}

	private class sabActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			List<List> res = new ArrayList();
			myActionListener ma = new myActionListener();
			int cu = getCurrentTab();
			res = ma.select(cu, 1, null, null); // 使用ma中的指定函数处理，获取数据库查询到的数据，返回res

			rewriteTable((List) res.get(0), (List) res.get(1));
		}
	}

	private class seActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			List res = new ArrayList();
			String value = textField.getText();
			if (value.equals("")) {
				JOptionPane.showMessageDialog(f, "查询条件不能为空 ", " 注意",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			myActionListener ma = new myActionListener();
			int cu = getCurrentTab();
			String value1 = comboBox_1.getSelectedItem().toString();
			res = ma.select(cu, 2, value, value1);
			List co = (List) res.get(1);
			if (co.size() == 0) {
				JOptionPane.showMessageDialog(f, "未查询到符合条件的数据 ", " 注意",
						JOptionPane.ERROR_MESSAGE);
				// return;
			}
			rewriteTable((List) res.get(0), (List) res.get(1));
		}
	}

	private class deleteActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String value = textField.getText();
			if (value.equals("")) {
				JOptionPane.showMessageDialog(f, "条件不能为空 ", " 注意",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			myActionListener ma = new myActionListener();
			int cu = getCurrentTab();
			String item = comboBox_1.getSelectedItem().toString();
			ma.delete(cu, item, value);
		}

	}

	private class deleteoneActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = UserClient.table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(f, "请选择要删除的项", " 注意",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			int res = JOptionPane.showConfirmDialog(f, "确定删除该项吗?",
					"choose one", JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {

				String no1 = (String) UserClient.table.getValueAt(selectedRow,
						0);
				String no2 = (String) UserClient.table.getValueAt(selectedRow,
						1);
				myActionListener ma = new myActionListener();
				int cu = getCurrentTab();
				ma.deleteOne(cu, no1, no2);
				if (DBoperate.opState.equals("success")) {
					JOptionPane.showMessageDialog(f, "删除成功 ", " 注意",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					DBoperate.opState = "success";
				}
			}

		}
	}

	private class changeoneActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int selectedRow = UserClient.table.getSelectedRow();
			if (selectedRow == -1) {
				JOptionPane.showMessageDialog(f, "请选择要修改的项", " 注意",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			int res = JOptionPane.showConfirmDialog(f, "确定修改该项吗?",
					"choose one", JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {

				int cu = getCurrentTab();

				myActionListener ma = new myActionListener();
				String no1 = "";
				String no2 = "";
				String no3 = "";
				String no4 = "";
				String no5 = "";
				if (cu == 1) {
					no1 = (String) UserClient.table.getValueAt(selectedRow, 0);
					no2 = (String) UserClient.table.getValueAt(selectedRow, 1);
					no3 = (String) UserClient.table.getValueAt(selectedRow, 2);
					no4 = (String) UserClient.table.getValueAt(selectedRow, 3);
					no5 = (String) UserClient.table.getValueAt(selectedRow, 4);
					System.out.println("no: " + no1 + "no2" + no4);

				} else if (cu == 2) {
					no1 = (String) UserClient.table.getValueAt(selectedRow, 0);
					no2 = (String) UserClient.table.getValueAt(selectedRow, 1);
					no3 = (String) UserClient.table.getValueAt(selectedRow, 2);
					no4 = (String) UserClient.table.getValueAt(selectedRow, 3);
				} else {
					no1 = (String) UserClient.table.getValueAt(selectedRow, 0);
					no2 = (String) UserClient.table.getValueAt(selectedRow, 1);
					no3 = (String) UserClient.table.getValueAt(selectedRow, 2);
				}
				ma.changeOne(cu, no1, no2, no3, no4, no5, key1, key2);
				if (DBoperate.opState.equals("success")) {
					JOptionPane.showMessageDialog(f, "更新成功 ", " 注意",
							JOptionPane.INFORMATION_MESSAGE);

				} else {
					DBoperate.opState = "success";
				}
				state = true;
				state1 = true;
			}
		}

	}

	private class addActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// String title = "Input Dialog";
			// int messageType = JOptionPane.QUESTION_MESSAGE;

			int c = getCurrentTab();
			myActionListener ma = new myActionListener();

			switch (c) {

			case 1:
				addStudent frame = new addStudent(c);
				frame.setVisible(true);
				break;
			case 2:
				addCourse frame1 = new addCourse(c);
				frame1.setVisible(true);
				break;
			case 3:
				addSc frame3 = new addSc(c);
				frame3.setVisible(true);
				break;
			}
		}

	}

	private class myItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if (e.getStateChange() == ItemEvent.SELECTED) {
				String itemSize = (String) e.getItem();
				try {
					// System.out.println("---ItemEvent performed:" +
					// e.paramString() + "---");
					String now = comboBox.getSelectedItem().toString();
					if (now.equals("学生表")) {
						comboBox_1.removeAllItems();
						comboBox_1.addItem("Sno");
						comboBox_1.addItem("Sname");
						comboBox_1.addItem("Sgender");
						comboBox_1.addItem("Sage");
						comboBox_1.addItem("Sdept");
					} else if (now.equals("课程表")) {
						comboBox_1.removeAllItems();
						comboBox_1.addItem("Cno");
						comboBox_1.addItem("Cname");
						comboBox_1.addItem("Cpno");
						comboBox_1.addItem("Ccredit");
					} else {
						comboBox_1.removeAllItems();
						comboBox_1.addItem("Sno");
						comboBox_1.addItem("Cno");
						comboBox_1.addItem("Grade");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	private class selectActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String mes = textArea.getText();
			List<List> res = new ArrayList();
			myActionListener ma = new myActionListener();
			int cu = getCurrentTab();
			res = ma.select(cu, 3, mes, null); // 使用ma中的指定函数处理，获取数据库查询到的数据，返回res

			rewriteTable((List) res.get(0), (List) res.get(1));
		}

	}

	/**
	 * Create the frame.
	 */
	public UserClient() {
		setTitle("Student-Class");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 950, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);

		comboBox = new JComboBox();
		comboBox.addItem("学生表");
		comboBox.addItem("课程表");
		comboBox.addItem("选课表");
		comboBox.addItemListener(new myItemListener());
		String[] columnNames = {};
		// 创建显示数据
		Object[][] data = {};
		tableModel = new DefaultTableModel(data, columnNames);

		btnNewButton = new JButton("SELECTALL");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new sabActionListener());

		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		table = new JTable(tableModel);
		table.setBackground(Color.white);
		JScrollPane scrollPane = new JScrollPane(table);

		JLabel label = new JLabel("\u64CD\u4F5C\u8868\uFF1A");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u67E5\u8BE2\u6761\u4EF6\uFF1A");

		JButton btnSelect = new JButton("SELECT");
		btnSelect.setBackground(UIManager.getColor("Button.background"));
		btnSelect.addActionListener(new seActionListener());

		comboBox_1 = new JComboBox();
		comboBox_1.addItem("Sno");
		comboBox_1.addItem("Sname");
		comboBox_1.addItem("Sgender");
		comboBox_1.addItem("Sage");
		comboBox_1.addItem("Sdept");

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new addActionListener());

		btnNewButton_1 = new JButton("DELETEALL");

		btnNewButton_1.addActionListener(new deleteActionListener());

		btnNewButton_2 = new JButton("DeleteOne");
		btnNewButton_2.addActionListener(new deleteoneActionListener());

		btnChangeone = new JButton("ChangeOne");
		btnChangeone.addActionListener(new changeoneActionListener());

		JLabel label_2 = new JLabel("\u9AD8\u7EA7\u67E5\u8BE2\uFF1A");

		textArea = new JTextArea();

		JButton btnS = new JButton("SELECT");
		btnS.addActionListener(new selectActionListener());

		btnNewButton_3 = new JButton("EXIT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(f, "确定退出?",
						"choose one", JOptionPane.YES_NO_OPTION);
				if (res == JOptionPane.YES_OPTION) {
					dispose();
					Login frame = new Login();
					frame.setVisible(true);
				}

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addComponent(label_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSelect)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_1, 0, 0, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBox_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnNewButton, 0, 0, Short.MAX_VALUE)
										.addComponent(textField))))
							.addGap(0, 50, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnChangeone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnS, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_3, Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE))
					.addGap(69))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(btnNewButton)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSelect)
								.addComponent(btnNewButton_1))
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnS)
							.addGap(26)
							.addComponent(btnNewButton_2)
							.addGap(18)
							.addComponent(btnChangeone)
							.addGap(18)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
							.addComponent(btnNewButton_3)
							.addGap(20)))
					.addGap(41))
		);
		contentPane.setLayout(gl_contentPane);

	}
}
