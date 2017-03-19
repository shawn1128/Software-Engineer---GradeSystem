
public class Main extends Object {
	public static void main(String[] args) {
		try{
			new UI();
		}catch(NoSuchIDExceptions e1){
			
		}catch (NoSuchCommandExceptions e2){
			
		}
	}
	
	public class NoSuchIDExceptions extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	} 
	
	public class NoSuchCommandExceptions extends Exception {
		
	}
}
