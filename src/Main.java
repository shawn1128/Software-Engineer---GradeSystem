
public class Main extends Object {
	public static void main(String[] args) throws NoSuchIDExceptions, NoSuchCommandExceptions {
		try {
			new UI();
		} catch (NoSuchIDExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchCommandExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public class NoSuchIDExceptions extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	} 
	
	public class NoSuchCommandExceptions extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
}
