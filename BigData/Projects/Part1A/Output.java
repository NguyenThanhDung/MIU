import java.io.FileWriter;
import java.io.IOException;

public class Output {
	static String text = "";
	
	public static void add(String s) {
		text += s;
	}
	
	public static void writeToFile() {
		try {
		      FileWriter myWriter = new FileWriter("/workspace/WordCount/Output.txt");
		      myWriter.write(text);
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
