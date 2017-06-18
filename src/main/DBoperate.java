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
				while (result.next()) { //meResultSet 维护指向其当前数据行的光标。每调用一次 next 方法，光标向下移动一行。最初它位于第一行之前，因此第一次调用 next
					                    //将把光标置于第一行上，使它成为当前行。随着每次调用 next 导致光标向下移动一行，按照从上至下的次序获取ResultSet 行。
					// 当结果集不为空时
					//System.out.println("学号:" + result.getString("column_name"));	
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
					// 当结果集不为空时
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
					// 当结果集不为空时
					//System.out.println("学号:" + result.getString("column_name"));
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
					// 当结果集不为空时
					//System.out.println("学号:" + result.getString("column_name"));
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
		Connection con = null;// 创建一个数据库连接
		PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		ResultSet result = null;// 创建一个结果集对象
		List al = new ArrayList();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 加载Oracle驱动程序

			System.out.println("开始尝试连接数据库！");

			String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名

			String user = "scott";// 用户名,系统默认的账户名

			String password = "tiger";// 你安装时选设置的密码

			con = DriverManager.getConnection(url, user, password);// 获取连接

			System.out.println("连接成功！");
			// String sql = "select * from S";// 预编译语句，“？”代表参数
			System.out.println("sql:" + sql.getSql());
			pre = con.prepareStatement(sql.getSql());// 实例化预编译语句

			// pre.setString(1, "peng0");// 设置参数，前面的1表示参数的索引，而不是表中列名的索引

			result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数
			
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
			JOptionPane.showMessageDialog(null, mes, " 注意",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if (result != null)
					result.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
				System.out.println("数据库连接已关闭！");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return al;
	}
}
