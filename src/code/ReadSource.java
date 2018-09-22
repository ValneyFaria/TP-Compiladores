package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadSource {

	String Reader(String fileName) throws FileNotFoundException {
		String content = null;

		content = new Scanner(new File(fileName)).useDelimiter("\\A").next();

		System.out.println(content);

		return content;
	}

}
