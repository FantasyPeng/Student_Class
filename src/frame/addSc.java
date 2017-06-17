package frame;

import helper.myActionListener;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import main.DBoperate;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addSc extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Sno;
	private JTextField textField_Cno;
	private JTextField textField_Grade;
	private JFrame f = this;
	private int currentTab;
	

	/**
	 * Create the frame.
	 */
	public addSc(int c) {
		currentTab = c;
		setTitle("\u6DFB\u52A0\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 200, 248, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u53F7\uFF1A");
		
		JLabel lblNewLabel_1 = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		
		JLabel lblNewLabel_2 = new JLabel("\u5206\u6570\uFF1A");
		
		textField_Sno = new JTextField();
		textField_Sno.setColumns(10);
		
		textField_Cno = new JTextField();
		textField_Cno.setColumns(10);
		
		textField_Grade = new JTextField();
		textField_Grade.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Sno = textField_Sno.getText();
				String Cno = textField_Cno.getText();
				String Grade = textField_Grade.getText();
	
				myActionListener ma = new myActionListener();
				ma.insert(c, Sno, Cno, Grade, null, null);
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
							.addGap(8)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_Sno, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Cno, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_Grade, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
					.addGap(30))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_Sno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textField_Cno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_Grade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(22))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
