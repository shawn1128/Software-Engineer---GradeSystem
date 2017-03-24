
public class NoSuchCommandExceptions extends Exception {
	/* class NoSuchCommandExceptions-----
	 * an exception class that will be trigger if command is invalid.
	 * @constructor:
	 * 		print the error message.
	 * 		@command: the command user typed. 
	 * 
	 * Pseudo code
	 * 1.�ϥΪ̪����O���X�k�Aprint command is not valid!
	 * Time Estimated: O(1)
	 */
	private static final long serialVersionUID = 1L;
	NoSuchCommandExceptions(String command){
		System.out.println(command + " is not valid!");
	}
}
