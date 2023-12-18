package School_이준영Ver4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
	private Scanner scan;
	private final String CUR_PATH = System.getProperty("user.dir") + "\\src\\School_이준영\\";
	private static Util instance = new Util();

	public static Util getinstance() {
		return instance;
	}

	Util() {
		scan = new Scanner(System.in);

	}

	int getValue(int start, int end) {
		while (true) {
			try {
				System.out.printf("[%d]-[%d] 메뉴 입력 %n", start, end);
				int num = scan.nextInt();
				scan.nextLine();
				if (num < start || num > end) {
					System.out.println("잘못입력하셧습니다");
					continue;
				}
				return num;
			} catch (Exception e) {
				scan.nextLine();
				System.out.println("숫자만입력하세요");
			}
		}
	}

	String get(String msg) {
		System.out.println(msg);
		String id = scan.next();
		return id;
	}

	void scanclose() {
		scan.close();
	}

	private String loadData(String file) {
		String data = "";
		try (FileReader fr = new FileReader(CUR_PATH + file); BufferedReader br = new BufferedReader(fr)) {
			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}
				data += line + "\n";
			}
			data = data.substring(0, data.length() - 1);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("로드실패");
		}
		return data;
	}

	void loadFile(StudentDAO studao, SubjectDAO subdao) {
		String stuLoad = loadData("student.txt");
		String subLoad = loadData("subject.txt");
		studao.getData(stuLoad);
		subdao.getSubList(subLoad);
		studao.updataMaxStuNo();
	}

	void saveFileRun(StudentDAO studao, SubjectDAO subdao) {
		String stuData = studao.savedata();
		String subData = subdao.saveSubject();
		savedata("student.txt", stuData);
		savedata("subject.txt", subData);
	}

	private void savedata(String fileName, String data) {
		try (FileWriter fw = new FileWriter(CUR_PATH + fileName)) {
			fw.write(data);
			System.out.println("저장성공");
		} catch (IOException e) {
			System.out.println("저장실패");
			e.printStackTrace();
		}
	}

}
