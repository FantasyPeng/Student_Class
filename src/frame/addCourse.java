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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import main.DBoperate;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addCourse extends JFrame {

	private JFrame f = this;
	private int currentTab;
	
	private JPanel contentPane;
	private JTextField textField_Cno;
	private JTextField textField_Cname;
	private JTextField textField_Cpno;
	private JTextField textField_Ccredit;




	/**
	 * Create the frame.
	 */
	public addCourse(int c) {
		currentTab = c;
		setTitle("\u6DFB\u52A0\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 200, 247, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		
		JLabel lblNewLabel_1 = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		
		JLabel lblNewLabel_2 = new JLabel("\u5148\u4FEE\u8BFE\uFF1A");
		
		JLabel lblNewLabel_3 = new JLabel("\u5B66\u5206\uFF1A");
		
		textField_Cno = new JTextField();
		textField_Cno.setColumns(10);
		
		textField_Cname = new JTextField();
		textField_Cname.setColumns(10);
		
		textField_Cpno = new JTextField();
		textField_Cpno.setColumns(10);
		
		textField_Ccredit = new JTextField();
		textField_Ccredit.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Cno = textField_Cno.getText();
				String Cname = textField_Cname.getText();
				String Cpno = textField_Cpno.getText();
				String Ccredit = textField_Ccredit.getText();
				myActionListener ma = new myActionListener();
				
				ma.insert(c, Cno, Cname, Cpno, Ccredit, null);
				if (DBoperate.opState.equals("success")) {
					dispose();
					JOptionPane.showMessageDialog(f, "添加成功 ", " 注意",
							JOptionPane.DEFAULT_OPTION);		
				} else {
					DBoperate.opState = "success";
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Cno, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Cname, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Cpno))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_Ccredit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addGap(48))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField_Cno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_Cname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_Cpno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_Ccredit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addGap(18)
					.addComponent(btnNewButton)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
