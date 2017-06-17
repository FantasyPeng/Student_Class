package helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.DBoperate;
import main.UserClient;
import util.Student;

public class myActionListener {
	
	public int login(String name,String pw){
		String sql = "select * from users where username = '" + name + "' and password = '" + pw +"'";
		DBoperate db1 = new DBoperate();
		mSql ms = new mSql();
		ms.setOp("login");
		ms.setSql(sql);
		List al = new ArrayList();
		al = db1.predo(ms);
		if (al.size() == 0)
			return 0;		
		else {
			return 1;
		}
	}
	
	public void changeOne(int cu, String no1,String no2,String no3,String no4,String no5,String key1,String key2) {
		String sql = "update ";
		
		DBoperate db1 = new DBoperate();
		mSql ms = new mSql();
		if (cu == 1) {
			int age;
			if (no4.equals("")) {
				sql += "S Set Sno = '" + no1 + "',Sname = '" + no2 +"',Sgender = '" + no3 + "',Sage = " + null +",Sdept = '"
						+ no5 +"' where Sno = '" + key1 +"'" ;
			} else {
				 age = Integer.parseInt(no4);
				 sql += "S Set Sno = '" + no1 + "',Sname = '" + no2 +"',Sgender = '" + no3 + "',Sage = " + age +",Sdept = '"
							+ no5 +"' where Sno = '" + key1 +"'" ;
			}
			ms.setOp("update_s");
			ms.setSql(sql);
		} else if (cu == 2){
			if (no3 == null) {
				no3 ="";
			}
			int credit = Integer.parseInt(no4);
			sql += "C Set Cno = '" + no1 + "',Cname = '" + no2 +"',Cpno = '" + no3 + "',Ccredit = " + credit  +" where Cno = '" + key1 +"'" ;
			ms.setOp("update_c");
			ms.setSql(sql);
		} else {
			int grade = Integer.parseInt(no3);
			sql += "SC Set Sno = '" + no1 + "',Cno = '" + no2 +"',Grade = " + grade  +" where Sno = '" + key1 +"' and Cno = '" + key2 +"'";
			ms.setOp("update_sc");
			ms.setSql(sql);
		}
		db1.predo(ms);
	}
	public void deleteOne(int cu, String no1,String no2) {
		String sql = "delete from ";
		DBoperate db1 = new DBoperate();
		mSql ms = new mSql();
		if (cu == 1) {
			sql += "S where Sno = '" + no1 + "'";
			ms.setOp("delete_S");
			ms.setSql(sql);
		} else if (cu == 2) {
			sql += "C where Cno = '" + no1 + "'";
			ms.setOp("delete_C");
			ms.setSql(sql);
		} else {
			sql += "SC where Sno = '" + no1 + "' and Cno = '" + no2 + "'";
			ms.setOp("delete_SC");
			ms.setSql(sql);
		}
		db1.predo(ms);
	}
	public void delete(int cu, String item,String value) {
		String sql = "delete from ";
		DBoperate db1 = new DBoperate();
		mSql ms = new mSql();
		if (cu == 1) {
			sql += "S where " + item + " = '" + value + "'";
			ms.setOp("delete_S");
			ms.setSql(sql);
		} else if (cu == 2) {
			sql += "C where " + item + " = '" + value + "'";
			ms.setOp("delete_S");
			ms.setSql(sql);
		} else {
			sql += "SC where " + item + " = '" + value + "'";
			ms.setOp("delete_S");
			ms.setSql(sql);
		}
		db1.predo(ms);
	}
	public void insert(int cu,String a,String b,String c,String d,String e) {
		String sql = "insert into ";
		DBoperate db1 = new DBoperate();
		mSql ms = new mSql();
		if (cu == 1) {
			int age = Integer.parseInt(d);
			sql += "S values('" + a +"','" + b +"','" + c +"'," + d +",'" + e + "')";
			ms.setOp("insert_S");
			ms.setSql(sql);
		} else if (cu == 2) {
			int credit = Integer.parseInt(d);
			sql += "C values('" + a +"','" + b +"','" + c +"'," + credit + ")";
			ms.setOp("insert_C");
			ms.setSql(sql);
		} else {
			int grade = Integer.parseInt(c);
			sql += "SC values('" + a +"','" + b +"'," + c + ")";
			ms.setOp("insert_SC");
			ms.setSql(sql);
		}
		db1.predo(ms);
	}
	public List<List> select(int cu, int op, String s,String s1) {
		List<List> res = new ArrayList();
		List al = new ArrayList();
		List al2 = new ArrayList<Student>();
		DBoperate db1 = new DBoperate();
		mSql ms = new mSql();
		mSql ms1 = new mSql();
		String sql1 = "select column_name from user_tab_columns where table_name = ";
		String sql2 = "select * from ";
		if (cu == 1) {
			sql1 += "'S'";
			sql2 += "S";
			ms1.setOp("select_S");
			UserClient.currentOp = "select_S";
		} else if (cu == 2) {
			sql1 += "'C'";
			sql2 += "C";
			ms1.setOp("select_C");
			UserClient.currentOp = "select_C";
		} else {
			sql1 += "'SC'";
			sql2 += "SC";
			ms1.setOp("select_SC");
			UserClient.currentOp = "select_SC";
		}
		switch (op) {
		case 1:
			break;
		case 2:
			if (cu == 1)
				sql2 += " where " + s1 +" = '" + s +"'";
			else if (cu == 2)
				sql2 += " where " + s1 +" = '" + s +"'";
			else
				sql2 += " where " + s1 +" = '" + s +"'";
			break;
		case 3:
			sql2 = s;
			break;
		default:
			System.out.println("ERROR IN myActionListener");
		}
		ms.setOp("getColumn");
		ms.setSql(sql1);
		al = db1.predo(ms); // 真正对数据库进行查询
		ms1.setSql(sql2);
		al2 = db1.predo(ms1);
		
//		Iterator it = al2.iterator();
//		while(it.hasNext()) {
//			Student s1 = (Student) it.next();
//			System.out.println("S:" + s1.getSname());
//		}
		res.add(al);
		res.add(al2);
		return res;
	}
}
