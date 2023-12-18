package School_이준영Ver4;

import java.util.ArrayList;
import java.util.Random;

public class SubjectDAO {
	private ArrayList<Subject> subList;
	private Util scan;
	private int cnt;
	private Random ran;

	public SubjectDAO() {
		scan = new Util();
		subList = new ArrayList<Subject>();
		ran = new Random();
		cnt = subList.size();
	}

	private void printAll() {
		for (Subject s : subList) {
			System.out.println(s.toString());
		}
	}
	ArrayList<Subject> getSubjectsByAStudent(Student stu){
		if(subList.size()==0)return new ArrayList<Subject>();
		ArrayList<Subject> list =new ArrayList<Subject>();
		for(Subject s : subList) {
			if(stu.stuNo==s.stuNo) {
				list.add(s);
			}
		}
		return list;
		}
	private boolean hasData(ArrayList<Subject> list) {
		if (list.size() == 0) {
			System.out.println("[no subject data ]");
			return false;
		}
		return true;
	}
	void pirntStudentSubjects(Student stu) {
		ArrayList<Subject> list = getSubjectsByAStudent(stu);
		if(list.size()==0) return;
		for(Subject s: list) {
			System.out.print(s + " ");
		}
		System.out.println();
	}

	void inputSubject(int stu) {
		String input = scan.get("추가하실 과목을 입력하세요");
		if (check(input, stu)) {
			System.out.println("해당과목이 존재합니다");
			return;
		}
		insertSubject(stu, input);
		System.out.println("추가완료");
	}

	void insertSubject(int stuNo, String subName) {
		int score = ran.nextInt(51) + 50;
		if (cnt == 0) {
			subList.add(new Subject(1));
		} else {
			ArrayList<Subject> temp = new ArrayList<Subject>();
			temp=subList;
			for(int i=0; i<cnt+1; i++) {
				subList.add(new Subject(i));
			}
			for(int i=0; i<temp.size(); i++) {
				subList.get(i).score=subList.get(i).score;
				subList.get(i).stuNo=subList.get(i).stuNo;
				subList.get(i).subName=subList.get(i).subName;
			}
		}
		subList.get(cnt).stuNo = stuNo;
		subList.get(cnt).subName = subName;
		subList.get(cnt).score = score;
		System.out.println(cnt);
		cnt++;
	}

	void delSubject(int stu) {
		if (cnt == 0)
			return;
		String input = scan.get("삭제하실 과목을 선택하세요");
		if (checkSub(input, stu) == -1) {
			System.out.println("삭제하실 과목이 없습니다");
			return;
		}
		deleteCheck(stu, input);
		System.out.println("삭제완료");
	}

	void SubDel(int delNo) {
		if (cnt != 0) {
			for (int i = 0; i < cnt; i++) {
				if (subList.get(i).stuNo == delNo) {
					System.out.println(subList.get(i));
					subList.remove(i);
					cnt--;
				}
			}

		}
	}

	private void deleteCheck(int stu, String input) {
		for (int i = 0; i < cnt; i++) {
			if (stu == subList.get(i).stuNo && subList.get(i).subName.equals(input)) {
				subList.remove(i);
			}
		}
	}

	private boolean check(String Name, int st) {
		if (subList == null)
			return false;
		else {
			for (int i = 0; i < cnt; i++) {
				if (subList.get(i).subName.equals(Name) && subList.get(i).stuNo == st) {
					return true;

				}
			}
		}

		return false;
	}
	void getStuListNyAsubject(StudentDAO studao) {
	if(!hasData(subList))return;
		String name =scan.get("찾을 과목 입력");
		ArrayList<Integer> stuNoList = new ArrayList<Integer>();
		for(Subject sub : subList) {
			if(sub.subName.equals(name)) {
				stuNoList.add(sub.stuNo);
			}
		}
		if(stuNoList.size()==0) {
			System.out.println("해당 과목은 학생 데이터가 없습니다");
			return;
		}
		studao.getStudentsBySub(stuNoList);
	}

	private int checkSub(String subName, int stuNo) {
		if (subList == null)
			return -1;
		else if (subList != null) {
			for (Subject s : subList) {
				if (s.subName.equals(subName) && s.stuNo == stuNo) {
					return 1;
				}
			}
		}
		return -1;
	}
	double getAvgSub(Student stu) {
		ArrayList<Subject> list=getSubjectsByAStudent(stu);
		if(list.size()==0) return 0;
		double score =0;
		int cnt=0;
		for(Subject s : list) {
			if(stu.stuNo==s.stuNo) {
				score+=s.score;
				cnt++;
			}
		}
		return score/list.size();
	}

	void getSubList(String val) {
		String[] datas = val.split("\n");
		int size = datas.length;
		for (int i = 0; i < datas.length; i++) {
			subList.add(new Subject(i));
		}
		for (int i = 0; i < size; i++) {
			String[] info = datas[i].split("/");
			subList.get(i).stuNo = Integer.parseInt(info[0]);
			subList.get(i).subName = info[1];
			subList.get(i).score = Integer.parseInt(info[2]);
		}
	}

	String saveSubject() {
		if (subList.size() == 0)
			return "";
		String data = "";
		for (int i = 0; i < cnt; i++) {
			data += subList.get(i).subjectSave();
		}

		return data;
	}

}
