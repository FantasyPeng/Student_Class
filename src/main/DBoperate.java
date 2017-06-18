package main;

import helper.getStackTrace;
import helper.mSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.Lesson;
import util.Sc;
import util.SnoAvg;
import util.Student;

public class DBoperate {
	
	public static String opState = "success";
	
	
	private List choseRes(ResultSet result, String op) {		
		switch (op) {
		case "getColumn":
			List al = new ArrayList();
			try {
				while (result.next()) { //meResultSet ά��ָ���䵱ǰ�����еĹ�ꡣÿ����һ�� next ��������������ƶ�һ�С������λ�ڵ�һ��֮ǰ����˵�һ�ε��� next
					                    //���ѹ�����ڵ�һ���ϣ�ʹ����Ϊ��ǰ�С�����ÿ�ε��� next ���¹�������ƶ�һ�У����մ������µĴ����ȡResultSet �С�
					// ���������Ϊ��ʱ
					//System.out.println("ѧ��:" + result.getString("column_name"));	
					String s =s = result.getString("column_name");
					al.add(s);
					//System.out.println(s);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return al;
		case "select_S":
			List<Student> as = new ArrayList<Student>();
			try {
				while (result.next()) {
					// ���������Ϊ��ʱ
					Student s = new Student();
					s.setSno(result.getString("sno"));
					s.setSname(result.getString("sname"));
					s.setSgender(result.getString("sgender"));					
					s.setSage(result.getInt("sage"));
					s.setSdept(result.getString("sdept"));
					as.add(s);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return as;
		case "select_C":
			List<Lesson> ac = new ArrayList<Lesson>();
			try {
				while (result.next()) {
					// ���������Ϊ��ʱ
					//System.out.println("ѧ��:" + result.getString("column_name"));
					Lesson l = new Lesson();
					l.setCno(result.getString("cno"));
					l.setCname(result.getString("cname"));
					l.setCpno(result.getString("cpno"));
					l.setCcredit(result.getInt("ccredit"));
					ac.add(l);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ac;
		case "select_SC":
			List<Sc> asc = new ArrayList<Sc>();
			try {
				while (result.next()) {
					// ���������Ϊ��ʱ
					//System.out.println("ѧ��:" + result.getString("column_name"));
					Sc l = new Sc();
					l.setSno(result.getString("sno"));
					l.setCno(result.getString("cno"));
					l.setGrade(result.getInt("grade"));
					asc.add(l);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return asc;
		case "login":
			List lo = new ArrayList();
			try {
				while (result.next()) {		
					lo.add(0);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lo;
		case "getAvg1":
			List<SnoAvg> ga = new ArrayList<SnoAvg>();
			try {
				while (result.next()) {		
					SnoAvg sa = new SnoAvg();
					sa.setSno(result.getString("Sno"));
					sa.setAvgGrade(result.getInt("Grade"));
					ga.add(sa);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ga;
		default:
			System.out.println("Something wrong in DBoperate");
			break;
		}
		return null;	

	}

    public List predo(mSql sql) {
		Connection con = null;// ����һ�����ݿ�����
		PreparedStatement pre = null;// ����Ԥ����������һ�㶼�������������Statement
		ResultSet result = null;// ����һ�����������
		List al = new ArrayList();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ����Oracle��������

			System.out.println("��ʼ�����������ݿ⣡");

			String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";// 127.0.0.1�Ǳ�����ַ��XE�Ǿ����Oracle��Ĭ�����ݿ���

			String user = "scott";// �û���,ϵͳĬ�ϵ��˻���

			String password = "tiger";// �㰲װʱѡ���õ�����

			con = DriverManager.getConnection(url, user, password);// ��ȡ����

			System.out.println("���ӳɹ���");
			// String sql = "select * from S";// Ԥ������䣬�������������
			System.out.println("sql:" + sql.getSql());
			pre = con.prepareStatement(sql.getSql());// ʵ����Ԥ�������

			// pre.setString(1, "peng0");// ���ò�����ǰ���1��ʾ�����������������Ǳ�������������

			result = pre.executeQuery();// ִ�в�ѯ��ע�������в���Ҫ�ټӲ���
			
			//System.out.println("result.next() " +result.next().getString("username"));
			if (sql.getOp().equals("getAvg1") ||sql.getOp().equals("getColumn") ||sql.getOp().equals("select_S") ||sql.getOp().equals("select_C")
					||sql.getOp().equals("select_SC") ||sql.getOp().equals("login"))
					al = choseRes(result, sql.getOp());
			
		} catch (Exception e) {
			// TODO: handle exception
			
			String error = getStackTrace.getStack(e);
			String[] errorArray = error.split("\n");
			
			String mes = errorArray[0];
			
			e.printStackTrace();
			opState = "error";
			JOptionPane.showMessageDialog(null, mes, " ע��",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				// ��һ������ļ�������رգ���Ϊ���رյĻ���Ӱ�����ܡ�����ռ����Դ
				// ע��رյ�˳�����ʹ�õ����ȹر�
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("���ݿ������ѹرգ�");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return al;
	}
}
