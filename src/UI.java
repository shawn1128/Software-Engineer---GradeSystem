import java.util.Scanner;

public class UI {
	
	Scanner input;
	String userID;
	String command;
	GradeSystems aGradeSystem;
	
	public UI() throws NoSuchIDExceptions, NoSuchCommandExceptions {
		input = new Scanner(System.in);
		aGradeSystem = new GradeSystems();
		
		
		boolean ifexit, IDValid;
		while(true){
			ifexit = promptID();
			if (ifexit == false){
				showFinishMsg();
				break;
			}
			else if (ifexit == true){
				IDValid = checkID(userID);
				if(IDValid == true){
					WelcomeMsg();
					while(true){
						
						command = promptCommand(userID);
						if(command.equals("G")){
							aGradeSystem.showGrade(userID);
						}else if(command.equals("R")){
							aGradeSystem.showRank(userID);
						}else if(command.equals("A")){
							aGradeSystem.showAverage(userID);
						}else if(command.equals("W")){
							aGradeSystem.updateWeights(userID);
						}else if(command.equals("E")){
							break;
						}else{
							throw new NoSuchCommandExceptions(command);
						}
					}
					
				}
				
			}
		}
		
	}
	
	public boolean checkID(String ID) throws NoSuchIDExceptions {
		return aGradeSystem.contaionsID(ID);
		
	}
	
	public String promptCommand(String ID) throws NoSuchCommandExceptions {
		System.out.println("Please type your command:");
		System.out.println("\t1)G Show the grade.");
		System.out.println("\t2)R Show the rank.");
		System.out.println("\t3)A Show the average.");
		System.out.println("\t4)W Reset the grade.");
		System.out.println("\t5)E Exit the menu.");
		String tmp = input.nextLine();
		return tmp;
		
	}
	
	public boolean promptID() {
		System.out.println("Please type ID or Q (exit)");
		String tmp = input.nextLine();
		//System.out.println(tmp);
		if(tmp.equals("Q")){
			return false;
		}else{
			userID = tmp;
			return true;
		}
		
		
	}
	
	public void showFinishMsg() {
		System.out.println("See you next time!");
	}
	
	public void WelcomeMsg() {
		System.out.println("Welcome " + aGradeSystem.getName(userID));
	}
}
