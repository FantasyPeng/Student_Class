package main;

import java.util.List;
import java.util.Vector;

import util.Lesson;
import util.Sc;
import util.Student;

public class updateVector {
	public Vector<Vector<String>> getData(List co) {
		Vector<Vector<String>> dataVec = new Vector<Vector<String>>();
		//System.out.println("Something wrong!" + co.size());
		for (int i = 0; i < co.size(); i++) {
			Vector<String> row = new Vector<String>();
			switch (UserClient.currentOp) {
			case "select_S":
				Student s = (Student) co.get(i);
				row.add(s.getSno());
				row.add(s.getSname());
				row.add(s.getSgender());
				row.add(Integer.toString(s.getSage()));
				row.add(s.getSdept());
			//	row.add("É¾³ý");
				dataVec.add(row);
				break;
			case "select_C":
				Lesson l = (Lesson) co.get(i);
				row.add(l.getCno());
				row.add(l.getCname());
				row.add(l.getCpno());
				row.add(Integer.toString(l.getCcredit()));
			//	row.add("É¾³ý");
				dataVec.add(row);
				break;
			case "select_SC":
				Sc sc = (Sc) co.get(i);
				row.add(sc.getSno());
				row.add(sc.getCno());
				row.add(Integer.toString(sc.getGrade()));
			//	row.add("É¾³ý");
				dataVec.add(row);
				break;
			default:
				System.out.println("Something wrong!" + UserClient.currentOp);
				break;
			}

		}
		return dataVec;
	}
}
