import java.util.Scanner;

public class Welcome{
	public static void main(String[]args) {
	
		Scanner scan = new Scanner(System.in);
		
		String fname;
		String lname;
		
		System.out.print("Enyter your First Name: ");
		fname = scan.nextLine();
		
		System.out.print("Enyter your Last Name: ");
		lname = scan.nextLine();
		
		System.out.println("Welcome to the Second Year " + fname + " " + lname);
		
		scan.close();
	}
}