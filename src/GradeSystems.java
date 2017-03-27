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
	 * 
	 * Pseudo code
	 * 1.先宣告File file跟Scanner input
	 * 2.利用input.hasNexLine來parse txt檔的內容，利用split來切割裡面的資料
	 * 3.將學生資料用Grades存好，再將他們加進一個aGrades的list
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
	 * 
	 * Pseudo code
	 * 1.使用iterator go through aGrades
	 * 2.找到相同ID後print 出該學生的所有成績
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
	 * 
	 * Pseudo code
	 * 1.利用自己implement 的totalGradeComparator來sort原有的aGrades，這是按照加權後的分數在sort，複雜度是O(nlogn)
	 * 2.排序完之後花O(n)時間來找ID的位置
	 * 3.找到後print出該學生的排名
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
	 * Pseudo code
	 * 1.複製傳進來的tmp_weights到weights
	 * 2.print 成功訊息
	 * Time Estimated: O(n)
	 */
	public void updateWeights(float tmp_weights[]) {
		weights = Arrays.copyOf(tmp_weights, tmp_weights.length);
		System.out.println("更改成功!");
		
	}
	/*method containsID-----
	 * Use iterator to check if the student ID is stored in aGrades.
	 * @return boolean: if student ID is in aGrades then return true,
	 * 					else return false
	 * @param ID: student ID
	 * @throws NoSuchIDExceptions when ID is not in aGrades
	 * 
	 * Pseudo code
	 * 1.用iterator go through aGrades，去看有沒有相同ID的object
	 * 2.若有則回傳true
	 * 3.若沒有則回傳false
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
	 * 
	 * Pseudo code
	 * 1.用iterator go through aGrades，找到該學生的ID
	 * 2.若有找到，則print該學生的平均成績
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
	 * 
	 * Pseudo code
	 * 1.print 出傳進來的t_weights
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
	 * 
	 * Pseudo code
	 * 1.利用iterator來go through aGrades(資料庫)
	 * 2.若current.ID跟ID一樣則回傳該ID的名字
	 * 3.若都沒有則回傳NULL
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
