import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

public class MainClass {

	public static void main(String[] args) 
	{
		try
		{
			File songsFolder = new File("C:\\Users\\kshitij.solanki\\Downloads\\Songs");
			File[] files = songsFolder.listFiles();
			Stream<File> stream = Arrays.stream(files);
			stream.forEach(e -> System.out.println(e.getName()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
