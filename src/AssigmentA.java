import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.IOException;
public class AssigmentA{
	public static void main(String[] args){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Lessons file:\n");
		String lessonstxt=scanner.nextLine();
		System.out.println("Teachers file:\n");
		String teacherstxt=scanner.nextLine();
		System.out.println("IQ:");
		String IQ=scanner.nextLine();
		Utilities.GO(lessonstxt,teacherstxt,IQ);
	}

}
