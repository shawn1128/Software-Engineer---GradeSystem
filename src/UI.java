import java.util.Scanner;

/*class UI------
 * To control all the procedures, including user's input, handle the id and command.
 * 
 *  @param input: object to scan user's input
 *  @param userID: store student ID from the user
 *  @param command: store the command from the user
 *  @Object aGradeSystem: store the information of all students, 
 *  					it will be used when we need student's information.
 *  					ex: checkID, showGrade...etc. 
 */
public class UI {
	
	Scanner input;
	String userID;
	String command;
	GradeSystems aGradeSystem;
	/* constructor UI-----
	 * To control all the procedures, from checking student's ID, checking commands
	 * and to show different results from different commands.
	 * @throws NoSuchIDExceptions: throw msg when student ID is not in database.
	 * @throws NoSuchCommandExceptions: throw msg when command is invalid.
	 * 
	 * Pseudo code
	 * 1.宣告一個Scanner input, GradeSystems aGradeSystem
	 * 2.開始無限迴圈
	 * 3.執行promtID()讓使用者輸入ID or Q
	 * 4.若輸入Q則結束程式
	 * 5.若輸入ID則呼叫checkID()確認ID存在，若不存在throw NoSuchIDExceptions，若存在則呼叫promptCommand()
	 * 讓使用者輸入相關指令
	 * 6.針對使用者輸入的指令，先確認是否合法，若不合法則throw NoSuchCommandExceptions，若合法則執行該指令，
	 * 接著持續promptCommand()直到使用者決定離開
	 */
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
							aGradeSystem.updateWeights();
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
	/* method checkID--------
	 * To check if student ID exists.
	 * @param ID: student ID
	 * @return a boolean to check if ID exists
	 * @method containsID: to check if student ID exists
	 * 
	 * Pseudo code
	 * 1.呼叫aGradeSystem.containsID(ID)，他會透過iterator去確認ID是否存在
	 * 2.若存在則回傳true, 不存在則回傳false
	 * Time Estimated: O(n)
	 */
	public boolean checkID(String ID) throws NoSuchIDExceptions {
		return aGradeSystem.containsID(ID);
	}
	
	/*method promptCommand------
	 * Constantly let user input commands
	 * @param ID: student ID
	 * return a String that represents user's command
	 * 
	 * Pseudo code
	 * 1.print出所有相關指令請使用者輸入
	 * 2.回傳使用者輸入的指令
	 * Time Estimated: O(1)
	 */
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
	/* method prompID-----
	 * To let user input student's ID or Quit
	 * @return a boolean: 
	 * if true means user input an ID, 
	 * if false means user decides to quit the program (type Q)
	 * 
	 * Pseudo code
	 * 1.讓使用者輸入ID或Q
	 * 2.若輸入Q則回傳false
	 * 3.若輸數ID則
	 * Time Estimated: O(1)
	 */
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
	/*method showFinishMsg-----
	 * show finish msg after user quit the program回傳true
	 * 
	 * Pseudo code
	 * 1.使用者輸入Q，因此print See you next time!
	 * Time Estimated: O(1)
	 */
	public void showFinishMsg() {
		System.out.println("See you next time!");
	}
	/*method WelcomeMsg-----
	 * show welcome msg if student ID is in database
	 * 
	 * Pseudo code
	 * 1.使用者輸入的ID合法，因此print Welcome+他的名字
	 * Time Estimated: O(1)
	 */
	public void WelcomeMsg() {
		System.out.println("Welcome " + aGradeSystem.getName(userID));
	}
}
