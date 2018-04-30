import java.io.IOException;
/**
 *
 */

public class W04Project {
	public static void main(String[] args) {
		try {
			runGUI design = new runGUI(args[0], args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: java combine <text_file> <The quantity of presented words>");
		}
	}
		
}