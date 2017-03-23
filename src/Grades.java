
public class Grades {
	String name, ID; 
	int lab1, lab2, lab3, midTerm, finalExam, totalGrade, average;
	
	public Grades () {
		
	} 
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
	
	public void calculateTotalGrade(float weights[]) {
		totalGrade = (int)(lab1*weights[0] + lab2*weights[1] + lab3*weights[2] + midTerm*weights[3] + finalExam*weights[4]);
	}
}
