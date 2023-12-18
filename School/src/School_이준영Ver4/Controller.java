package School_이준영Ver4;
/* 무조건 파일 업로드 먼저
 처음부터 우리 데이터가 연결된 상태
*/
public class Controller {
	private StudentDAO stuDao;
	private SubjectDAO subDao;
	private 	Util util;
	 Controller(){
		stuDao = new StudentDAO();
		subDao = new SubjectDAO();
		util = new Util();
	}
	private void mainMenu() {
		System.out.println("[1]학생추가"); // 학번(1001) 자동증가 : 학생id 중복 불가  
		System.out.println("[2]학생삭제"); // 학생 id 입력후 삭제 과목도 같이 삭제 
		System.out.println("[3]과목추가"); //학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
		System.out.println("[4]과목삭제"); // 학번 입력후 과목삭제 
		System.out.println("[5]전체학생목록"); // 점수로 출력
		System.out.println("[6]과목별학생목록"); // 과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력 (오름차순 이름순) 
		System.out.println("[7]파일저장");
		System.out.println("[8]파일로드");
		System.out.println("[0] 종료");
	}
	void run() {
	while(true) {
			mainMenu();
		int sel=util.getValue(0, 8);
	if(sel==1) {
		stuDao.getUser();
	}else if(sel==2) {
		stuDao.DelUser(subDao);
	}else if(sel==3) {
		stuDao.addSubject(subDao);
	}else if(sel==4) {
		stuDao.delSubject(subDao);
	}else if(sel==5) {
		stuDao.printAllScore(subDao);
	}else if(sel==6) {
		subDao.getStuListNyAsubject(stuDao);
	}else if(sel==7) {
		util.saveFileRun(stuDao, subDao);
	}else if(sel==8) {
		util.loadFile(stuDao, subDao);
	}else {
		System.out.println("종료");
		break;
	}
	}
	}
}
