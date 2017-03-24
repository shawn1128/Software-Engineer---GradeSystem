import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/*class GradeSystems------
 * To read the student's info's file, use a list to store the file by individual.
 * @param weights[]: the weights to calculate totalGrade in class Grades
 * @param aGrades: a list to store every student's info
 */
public class GradeSystems {
	float weights[] = {0.1f, 0.1f, 0.1f, 0.3f, 0.4f};
	Scanner w = new Scanner(System.in);
	List<Grades> aGrades = new ArrayList<Grades>();
	/*constructor GradeSystems-----
	 * To read the file, parse the content and store it to aGrades.
	 *  Time Estimated: O(n)
	 */
	public GradeSystems() {
        try {
            File file = new File("gradeinput.txt");
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] parts = line.split(" ");
                //System.out.println(line);
                Grades tmp = new Grades(parts[0], parts[1], Integer.valueOf(parts[2]), Integer.valueOf(parts[3]),Integer.valueOf(parts[4]),Integer.valueOf(parts[5]), Integer.valueOf(parts[6]), weights);
                aGrades.add(tmp);
            }
            input.close();
        } catch (Exception ex) {
        	
            ex.printStackTrace();
        }
	}
	/*method showGrade----
	 * Use iterator to find the student's info and print the student's scores.
	 * @param ID: student ID
	 * Time Estimated: O(n)
	 */
	public void showGrade(String ID){
		for (Iterator<Grades> i = aGrades.iterator(); i.hasNext();) {
			Grades current=((Grades)i.next());
		      if(current.ID.equals(ID)){
		    	  System.out.println(current.name+"成績:");
		    	  System.out.format("lab1:       " + "%4d"+((current.lab1<60)?"*":"")+"\n", current.lab1);
		    	  System.out.format("lab2:       " + "%4d"+((current.lab2<60)?"*":"")+"\n", current.lab2);
		    	  System.out.format("lab3:       " + "%4d"+((current.lab3<60)?"*":"")+"\n", current.lab3);
		    	  System.out.format("mid-term:   " + "%4d"+((current.midTerm<60)?"*":"")+"\n", current.midTerm);
		    	  System.out.format("final exam: " + "%4d"+((current.finalExam<60)?"*":"")+"\n", current.finalExam);
		    	  System.out.format("total grade:" + "%4d"+((current.totalGrade<60)?"*":"")+"\n", current.totalGrade);
		      }
		}
	}
	/*method showRank-----
	 * To calculate the rank by sorting aGrades, and print the result.
	 * To sort aGrades, interface Comparator<Grades> has be used.
	 * @param ID: student ID
	 * Time Estimated: O(nlogn)
	 */
	public void showRank(String ID){
		int rank=0, preGrade=110, add = 1;
		Collections.sort(aGrades, new totalGradeComparator());
		for (Iterator<Grades> i = aGrades.iterator(); i.hasNext();) {
			Grades current=((Grades)i.next());
			//System.out.format(current.name+"成績是:  "+"%d\n", current.totalGrade);
		      if(current.ID.equals(ID)){
		    	  System.out.format(current.name+"排名是:  "+"%d\n", (rank==0)?1:rank);
		    	  break;
		      }else{
		    	  if(current.totalGrade < preGrade){
		    		  preGrade = current.totalGrade;
		    		  rank = rank + add;
		    		  add = 1;
		    	  }else{
		    		  add = add + 1;
		    	  }
		      }
		}
	}
	/*class totalGradeComparator------
	 * To implement the interface Comparator, for sort function.
	 * compare the totalGrade in Grades objects, sort the object from high score
	 * to low score.
	 */
	class totalGradeComparator implements Comparator<Grades>{
		@Override
		public int compare(Grades grade1, Grades grade2){
			return grade1.totalGrade < grade2.totalGrade ? 1: -1;
		}
	}
	/*method updateWeights----
	 * To update the weights to calculate the totalscore.
	 * After updating the the weights, user should type Y to finish,
	 * or the modification will fail.
	 * Time Estimated: O(1)
	 */
	public void updateWeights() {
		String tmp;
		float tmp_weights[] = new float[5];
		System.out.println("舊配分:");
		printWeights(weights);
		System.out.println("輸入新配分:");
		System.out.format("lab1:       "); tmp_weights[0] = w.nextFloat()/100;
		System.out.format("lab2:       "); tmp_weights[1] = w.nextFloat()/100;
		System.out.format("lab3:       "); tmp_weights[2] = w.nextFloat()/100;
		System.out.format("mid-term:   "); tmp_weights[3] = w.nextFloat()/100;
		System.out.format("final exam: "); tmp_weights[4] = w.nextFloat()/100;
		System.out.println("請確認新配分");
		printWeights(tmp_weights);
		while(true){
			tmp = w.nextLine();
			if(tmp.equals("Y") || tmp.equals("N")) break;
			System.out.println("以上正確嗎? Y (Yes) 或 N (No)");
		}
		if(tmp.equals("Y")){
			if(weights[0]+weights[1]+weights[2]+weights[3]+weights[4]!=1){
				System.out.println("配分總和需為1!");
			}else{
				weights = Arrays.copyOf(tmp_weights, tmp_weights.length);
				System.out.println("更改成功!");
			}
		}else if(tmp.equals("N")){
			System.out.println("回到選單!");
		}
	}
	/*method containsID-----
	 * Use iterator to check if the student ID is stored in aGrades.
	 * @return boolean: if student ID is in aGrades then return true,
	 * 					else return false
	 * @param ID: student ID
	 * @throws NoSuchIDExceptions when ID is not in aGrades
	 * Time Estimated: O(n)
	 */
	public boolean containsID(String ID) throws NoSuchIDExceptions{
		boolean flag=false;
		for (Iterator<Grades> i = aGrades.iterator(); i.hasNext();) {
			Grades current=((Grades)i.next());
		      if(current.ID.equals(ID)){
		    	  flag=true;
		    	  break;
		      }
		}
		if(flag==false)throw new NoSuchIDExceptions(ID);
		return flag;

	}
	/*method showAverage------
	 * Use iterator to find the student's info and print the average score.
	 * @param ID: student ID
	 * Time Estimated: O(n)
	 */
	public void showAverage(String ID){
		for (Iterator<Grades> i = aGrades.iterator(); i.hasNext();) {
			Grades current=((Grades)i.next());
		      if(current.ID.equals(ID)){
		    	  System.out.format(current.name+"的平均是:  "+"%d\n", current.average);
		      }
		}
	}
	/*method printWeights---
	 * To print all the weights.
	 * @param t_weights[]: the weights passed by updateWeights
	 * Time Estimated: O(1)
	 */
	public void printWeights(float t_weights[]){
		System.out.format("lab1:       "+"%4d%%\n", (int)(t_weights[0]*100));
		System.out.format("lab2:       "+"%4d%%\n", (int)(t_weights[1]*100));
		System.out.format("lab3:       "+"%4d%%\n", (int)(t_weights[2]*100));
		System.out.format("mid-term:   "+"%4d%%\n", (int)(t_weights[3]*100));
		System.out.format("final exam: "+"%4d%%\n", (int)(t_weights[4]*100));
	}
	/*method getName----
	 * Use the student ID to find student's name from aGradeSystem.
	 * Use iterator to run through aGrades to find student's name.
	 * @return a string: student's name
	 * @param ID: student's ID
	 * Time Estimated: O(n)
	 */
	public String getName(String ID){
		for (Iterator<Grades> i = aGrades.iterator(); i.hasNext();) {
			Grades current=((Grades)i.next());
		      if(current.ID.equals(ID)){
		    	  return current.name;
		      }
		}
		return null;
	}
	
}
