package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Adiciona todos os caracteteres do arquivo em uma String e a retorna
public class ReadSource {
	String Reader(String fileName) throws FileNotFoundException {
		String content = null;

		Scanner scanner = new Scanner(new File(fileName));
		content = scanner.useDelimiter("\\A").next();
		
		scanner.close();
		return content;
	}
}
