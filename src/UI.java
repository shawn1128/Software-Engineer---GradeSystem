
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
	 * 1.�ŧi�@��Scanner input, GradeSystems aGradeSystem
	 * 2.�}�l�L���j��
	 * 3.����promtID()���ϥΪ̿�JID or Q
	 * 4.�Y��JQ�h�����{��
	 * 5.�Y��JID�h�I�scheckID()�T�{ID�s�b�A�Y���s�bthrow NoSuchIDExceptions�A�Y�s�b�h�I�spromptCommand()
	 * ���ϥΪ̿�J�������O
	 * 6.�w��ϥΪ̿�J�����O�A���T�{�O�_�X�k�A�Y���X�k�hthrow NoSuchCommandExceptions�A�Y�X�k�h����ӫ��O�A
	 * ���۫���promptCommand()����ϥΪ̨M�w���}
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
							updateWeights();
							
							
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
	 * 1.�I�saGradeSystem.containsID(ID)�A�L�|�z�Literator�h�T�{ID�O�_�s�b
	 * 2.�Y�s�b�h�^��true, ���s�b�h�^��false
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
	 * 1.print�X�Ҧ��������O�ШϥΪ̿�J
	 * 2.�^�ǨϥΪ̿�J�����O
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
	 * 1.���ϥΪ̿�JID��Q
	 * 2.�Y��JQ�h�^��false
	 * 3.�Y���ID�h
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
	 * show finish msg after user quit the program�^��true
	 * 
	 * Pseudo code
	 * 1.�ϥΪ̿�JQ�A�]��print See you next time!
	 * Time Estimated: O(1)
	 */
	public void showFinishMsg() {
		System.out.println("See you next time!");
	}
	/*method WelcomeMsg-----
	 * show welcome msg if student ID is in database
	 * 
	 * Pseudo code
	 * 1.�ϥΪ̿�J��ID�X�k�A�]��print Welcome+�L���W�r
	 * Time Estimated: O(1)
	 */
	public void WelcomeMsg() {
		System.out.println("Welcome " + aGradeSystem.getName(userID));
	}
	/*method updateWeights----
	 * To update the weights to calculate the totalscore.
	 * After updating the the weights, user should type Y to finish,
	 * or the modification will fail.
	 * 
	 * Pseudo code
	 * 1.����printWeights(weights)�Aprint �X�쥻��weights
	 * 2.�ШϥΪ̿�J��s�᪺weights
	 * 3.����printWeights(tmp_weights)�Aprint �ϥΪ̧�s��weights
	 * 4.�Y�ϥΪ̿�JY�A�h�b�T�{tmp_weights�`�X��1�����s��aGradeSystem
	 * 5.�Y�ϥΪ̿�JN�A�h�^��D���
	 * Time Estimated: O(1)
	 */
	public void updateWeights(){
		System.out.println("�°t��:");
		aGradeSystem.printWeights(aGradeSystem.weights);
		float tmp_weights[] = new float[5];
		String tmp;
		System.out.println("��J�s�t��:");
		System.out.format("lab1:       "); tmp_weights[0] = input.nextFloat()/100;
		System.out.format("lab2:       "); tmp_weights[1] = input.nextFloat()/100;
		System.out.format("lab3:       "); tmp_weights[2] = input.nextFloat()/100;
		System.out.format("mid-term:   "); tmp_weights[3] = input.nextFloat()/100;
		System.out.format("final exam: "); tmp_weights[4] = input.nextFloat()/100;
		System.out.println("�нT�{�s�t��");
		aGradeSystem.printWeights(tmp_weights);
		while(true){
			tmp = input.nextLine();
			if(tmp.equals("Y") || tmp.equals("N")) break;
			System.out.println("�H�W���T��? Y (Yes) �� N (No)");
		}
		if(tmp.equals("Y")){
			if(tmp_weights[0]+tmp_weights[1]+tmp_weights[2]+tmp_weights[3]+tmp_weights[4]!=1){
				System.out.println("�t���`�M�ݬ�1!");
			}else{
				aGradeSystem.updateWeights(tmp_weights);
			}
		}else if(tmp.equals("N")){
			System.out.println("�^����!");
		}
	}
}
