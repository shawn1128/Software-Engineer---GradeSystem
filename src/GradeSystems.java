import java.io.File;
import java.util.Scanner;

public class GradeSystems {
	float weights[] = {0.1f, 0.1f, 0.1f, 0.3f, 0.4f};
	public GradeSystems() {
        try {

            File file = new File("gradeinput.txt");

            Scanner input = new Scanner(file);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
            }
            
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void showGrade(){
		
	}
	
	public void showRank(){
		
	}
	
	public void updateWeights() {
		
	}
	
	public boolean contaionsID(){
		return false;
	}
	
}
