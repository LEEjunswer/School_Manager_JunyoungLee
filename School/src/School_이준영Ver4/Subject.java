package School_이준영Ver4;

public class Subject {
	int stuNo;
	String subName;
	int score;
	
	Subject(int sub){
		this.stuNo=sub;
	}
	
	Subject(int sub,String subName,int score){
		this.stuNo=sub;
		this.subName=subName;
		this.score=score;
	}
	String showScore() {

		return "%s  %d점".formatted(subName,score);
	}

	@Override
	public String toString() {
		return subName+" "+score+"점 "; 
	}
	
	String subjectSave() {
		return "%d/%s/%d\n".formatted(stuNo,subName,score);
	}
}
