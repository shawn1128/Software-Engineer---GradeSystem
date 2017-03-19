import java.util.Scanner;

public class UI {
	
	Scanner input;
	String userID;
	GradeSystems aGradeSystem;
	
	public UI() throws NoSuchIDExceptions, NoSuchCommandExceptions {
		input = new Scanner(System.in);
		aGradeSystem = new GradeSystems();
		while(true){
			
		}
		
	}
	
	public boolean checkID(String ID) throws NoSuchIDExceptions {
		return false;
		
	}
	
	public String promptCommand(String ID) throws NoSuchCommandExceptions {
		return null;
		
	}
	
	public boolean promptID() {
		System.out.println("輸入ID或 Q (結束使用)？");
		String tmp = input.nextLine();
		if(tmp == "Q"){
			return false;
		}else{
			userID = tmp;
			return true;
		}
		
		
	}
	
	public void showFinishMsg() {
		System.out.println("結束了");
	}
	
	public void WelcomeMsg() {
		
	}
}
