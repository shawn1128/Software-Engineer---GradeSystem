
public class NoSuchIDExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	NoSuchIDExceptions(String ID){
		System.out.println(ID + " is not our student!");
	}

}
