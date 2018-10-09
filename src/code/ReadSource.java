package code;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Realiza a Leitura do Arquivo de Codigo e retorna os caracteres numa string
public class ReadSource {
	String Reader(String fileName) throws FileNotFoundException {
		String content = null;

		try {
			Scanner scanner = new Scanner(new File(fileName));
			content = scanner.useDelimiter("\\A").next();
			scanner.close();
		} catch (FileNotFoundException o) {
			System.out.println("\nARQUIVO NAO ENCONTRADO!\n");
			System.exit(0);
		} catch (NoSuchElementException e) {
			System.out.println("\nARQUIVO VAZIO!\n");
			System.exit(0);
		}
		return content;
	}
}
