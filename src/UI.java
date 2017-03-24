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
	 * Time Estimated: O(n)
	 */
	public boolean checkID(String ID) throws NoSuchIDExceptions {
		return aGradeSystem.containsID(ID);
	}
	
	/*method promptCommand------
	 * Constantly let user input commands
	 * @param ID: student ID
	 * return a String that represents user's command
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
	 * show finish msg after user quit the program
	 * Time Estimated: O(1)
	 */
	public void showFinishMsg() {
		System.out.println("See you next time!");
	}
	/*method WelcomeMsg-----
	 * show welcome msg if student ID is in database
	 * Time Estimated: O(1)
	 */
	public void WelcomeMsg() {
		System.out.println("Welcome " + aGradeSystem.getName(userID));
	}
}
