
/* class Grades---
 * store name, ID and seven scores for individual student.
 * @param name: student's name
 * @param ID: student's ID
 * @param lab1, lab2, lab3, midTerm, finalExam: student's score from test.
 * @param average: (lab1+lab2+lab3+midTerm+finalExam)/5
 * @param totalGrade: add each score calculated by different weight, the weights sum is 1.
 * 
 */
public class Grades {
	String name, ID; 
	int lab1, lab2, lab3, midTerm, finalExam, totalGrade, average;
	
	public Grades () {
		
	} 
	/*constructor Grades----
	 * To save student's info passed by  aGradeSystem.
	 * @param tID: student's ID
	 * @param tname: student's name
	 * @param tlab1, tlab2, tlab3, tmidTerm, tfinalExam: student's score from test.
	 * @param weights[]: the weights to calculate totalGrade
	 * Time Estimated: O(1)
	 */
	public Grades(String tID, String tname, int tlab1, int tlab2, int tlab3, int tmidTerm, int tfinalExam, float weights[]){	
		ID = tID;
		name = tname;
		lab1 = tlab1;
		lab2 = tlab2;
		lab3 = tlab3;
		midTerm = tmidTerm;
		finalExam = tfinalExam;
		average = (lab1+lab2+lab3+midTerm+finalExam)/5;
		calculateTotalGrade(weights);
	}
	/*method calculateTotalGrade----
	 * Add each score calculated by different weight, the weights sum is 1.
	 * @param weights: the weight passed by aGradeSystem
	 * Time Estimated: O(1)
	 */
	public void calculateTotalGrade(float weights[]) {
		totalGrade = (int)(lab1*weights[0] + lab2*weights[1] + lab3*weights[2] + midTerm*weights[3] + finalExam*weights[4]);
	}
}
