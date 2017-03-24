
public class NoSuchIDExceptions extends Exception {

	/* class NoSuchIDExceptions-----
	 * an exception class that will be trigger if student ID is not in database.
	 * @constructor:
	 * 		print the error message.
	 * 		@ID: the student ID user typed. 
	 */
	private static final long serialVersionUID = 1L;
	
	NoSuchIDExceptions(String ID){
		System.out.println(ID + " is not our student!");
	}

}
