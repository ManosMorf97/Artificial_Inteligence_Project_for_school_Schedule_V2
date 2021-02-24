import java.util.Scanner;
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
