package frame;

import helper.myActionListener;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;

import main.DBoperate;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addStudent extends JFrame {

	private int currentTab;
	
	private JPanel contentPane;
	private JTextField textField_Sno;
	private JTextField textField_Sname;
	private JTextField textField_Sgender;
	private JTextField textField_Sage;
	private JTextField textField_Sdept;
	
	private JFrame f = this;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					addStudent frame = new addStudent();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	private class confirmActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String Sno = textField_Sno.getText();
			String Sname = textField_Sname.getText();
			String Sgender = textField_Sgender.getText();
			String Sage = textField_Sage.getText();
			String Sdept = textField_Sdept.getText();
			myActionListener ma = new myActionListener();
			ma.insert(currentTab, Sno, Sname, Sgender, Sage, Sdept);
			if (DBoperate.opState.equals("success")) {
				dispose();
				JOptionPane.showMessageDialog(f, "添加成功 ", " 注意",
						JOptionPane.DEFAULT_OPTION);		
			} else {
				DBoperate.opState = "success";
			}
			
		}
		
	}
	/**
	 * Create the frame.
	 */
	public addStudent(int c) {
		currentTab = c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u6DFB\u52A0\u4FE1\u606F");
       // this.setSize(300, 200);
		setBounds(600, 200, 247, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		
		JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
		
		JLabel lblNewLabel_1 = new JLabel("\u6027\u522B\uFF1A");
		
		JLabel lblNewLabel_2 = new JLabel("\u5E74\u9F84\uFF1A");
		
		JLabel lblNewLabel_3 = new JLabel("\u7CFB\u522B\uFF1A");
		
		textField_Sno = new JTextField();
		textField_Sno.setColumns(10);
		
		textField_Sname = new JTextField();
		textField_Sname.setColumns(10);
		
		textField_Sgender = new JTextField();
		textField_Sgender.setColumns(10);
		
		textField_Sage = new JTextField();
		textField_Sage.setColumns(10);
		
		textField_Sdept = new JTextField();
		textField_Sdept.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new confirmActionListener());
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Sno, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Sname, 127, 127, 127))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Sdept, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_Sage))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_Sgender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(44))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField_Sno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField_Sname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_Sgender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_Sage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textField_Sdept, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
