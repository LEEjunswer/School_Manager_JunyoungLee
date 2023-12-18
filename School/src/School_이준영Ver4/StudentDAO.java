package School_이준영Ver4;

import java.util.ArrayList;
import java.util.Random;

public class StudentDAO {
	private ArrayList<Student> stuList;
	private ArrayList<Subject> subject;
	private Util scan;
	private int cnt;
	private int maxNo;
	private int size;
	private Random ran;

	 StudentDAO() {
		stuList = new ArrayList<Student>();
		subject = new ArrayList<Subject>();
		scan = new Util();
		size = stuList.size();
		ran = new Random();
		maxNo = 1001;

	}

	void getUser() {
		String input = scan.get("회원가입하실 아이디를 입력해주세요");
		if (check(input)) {
			System.out.println("중복ID존재");
			return;
		}
		String name = scan.get("회원가입하실 이름을 입력하세요");
		System.out.println("회원가입완료");
		add(input, name, maxNo);
		maxNo++;
	
	}

	private boolean check(String id) {
		if (size == 0) {
			return false;
		}
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				if (stuList.get(i).stuId.equals(id)) {
					return true;
				}
			}
		}
		return false;
	}

	void add(String name, String id, int num) {
		if (size == 0) {
			stuList.add(new Student(1));
		} else {
			ArrayList<Student> temp = new ArrayList<Student>();
			temp = stuList;
			stuList.add(new Student(size + 1));
			stuList.addAll(temp);
		}
		stuList.get(size).stuId = name;
		stuList.get(size).stuName = id;
		stuList.get(size).stuNo = maxNo;
		size++;
	}

	void DelUser(SubjectDAO subdao) {
		String id = scan.get("삭제하실 아이디를 입력하세요");
		int idx = delId(id);
		if (idx == -1) {
			System.out.println("아이디가 존재하지 않습니다");
			return;
		} else {
			int stu= stuList.get(idx).stuNo;
			System.out.println(stu);
			subdao.SubDel(stu);
			check(idx);
			System.out.println("삭제완료");
			

		}
	}

	private int delId(String id) {
		for (int i = 0; i < size; i++) {
			if (stuList.get(i).stuId.equals(id)) {
				return i;
			}
		}
		return -1;
	}

	private String check(int delIdx) {
		if (size == 0)
			return "삭제할아디가 없습니다";
		stuList.remove(delIdx);
//		maxNo--;
		size--;
		return "삭제완료";
	}
	
	private boolean hasData() {
		if(stuList.size()==0) {
			System.out.println("No data");
		return false;
		}
		return true;
	}

	void addSubject(SubjectDAO subdao) {
		System.out.println("학번을 입력하세요");
		int input = scan.getValue(1001, maxNo - 1);
		if (hack(input) == -1) {
			System.out.println("학번이 존재하지않습니다");
			return;
		}
		subdao.inputSubject(input);
	}

	private int hack(int stuNo) {
		for (int i = 0; i < size; i++) {
			if (stuList.get(i).stuNo == stuNo) {
				return i;
			}
		}
		return -1;
	}

	void delSubject(SubjectDAO subdao) {
		System.out.println("학번을 입력하세요");
		int input = scan.getValue(1001, maxNo - 1);
		if (hack(input) == -1) {
			System.out.println("학번이 존재하지않습니다");
			return;
		}
		for (Subject s : subject) {
			if (s.stuNo == input) {
				System.out.printf("%d \t %s\t %d \t \n", s.stuNo, s.subName, s.score);
			}
		}
		subdao.delSubject(input);
		

	}
	void updataMaxStuNo(){
		if(cnt==0) return;
		int maxNo=0;
		for(Student s : stuList) {
			if(maxNo<s.stuNo) {
				maxNo=s.stuNo;
			}
		}
		this.maxNo=maxNo;
	}
	void printAllScore(SubjectDAO subDAO) {
		if(!hasData()) return;
		ArrayList<Student> temp = new ArrayList<Student>();
	for(Student s : stuList) {
		temp.add(s);
	}
	ArrayList<Double> scores =new ArrayList<Double>();
		for(Student s : stuList) {
			scores.add(subDAO.getAvgSub(s));
		}
		for (int i = 0; i < temp.size(); i += 1) {
			double max = scores.get(i);
			for (int k = i; k < temp.size(); k += 1) {
				if (max < scores.get(k)) {
					max = scores.get(k);
					
					Student s = temp.get(i);
					temp.set(i, temp.get(k));
					temp.set(k, s);

					double score = scores.get(i);
					scores.set(i, scores.get(k));
					scores.set(k, score);

			}
			}
		}
		for(int i=0; i<temp.size(); i++) {
			System.out.println(temp.get(i));
			subDAO.pirntStudentSubjects(temp.get(i));
			if(scores.get(i)!=0) {
				System.out.println(scores.get(i)+ " 점");
		}else if(scores.get(i)==0) {
			System.out.println("No data");
		}
			System.out.println("------------------");
		}
			}
	void getData(String data) {
		String[] datas = data.split("\n");
		size = datas.length;
		for (int i = 0; i < size; i++) {
			stuList.add(new Student(i));
		}
		for (int i = 0; i < size; i++) {
			String[] info = datas[i].split("/");
			stuList.get(i).stuNo = Integer.parseInt(info[0]);
			stuList.get(i).stuId = info[2];
			stuList.get(i).stuName = info[1];
		}
	}
	void getStudentsBySub(ArrayList<Integer> stuNoList) {
		if(!hasData())return;
		ArrayList<Student> list = new ArrayList<Student>();
		for(int num : stuNoList) {
			for(Student s : stuList) {
				if(num ==s.stuNo) {
					list.add(s);
					break;
				}
			}
		}
		for(int i=0; i<list.size(); i++) {
			String name=list.get(i).stuName;
			for(int j=i; j<list.size(); j++) {
				if(list.get(j).stuName.compareTo(name)<0) {
					name =list.get(j).stuName;
					Student temp =list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		for(Student s : list) {
			System.out.println(s);
		}
	}
	String savedata() { // 데이터 저장
		if (size == 0)
			return "";
		String data = "";
		for (int i = 0; i < size; i++) {
			data += stuList.get(i).savedatas();
		}

		return data;
	}

}
