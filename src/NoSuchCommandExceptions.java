
public class NoSuchCommandExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NoSuchCommandExceptions(String command){
		System.out.println(command + " is not valid!");
	}
}
