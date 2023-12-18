package School_이준영Ver4;

public class Student {
	 int stuNo; // 같을경우 이름출력
	 String stuId;
	 String stuName;
	
	Student(int num) {
		this.stuNo=num;
	
	}

	@Override
	public String toString() {
		return "%d \t %s \t %s \n".formatted(stuNo,stuName,stuId) ;
	}
	
	String savedatas() {
		return "%d/%s/%s\n".formatted(stuNo,stuName,stuId);
	}
	
}
