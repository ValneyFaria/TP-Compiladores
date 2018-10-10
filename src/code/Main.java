package code;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "code.c";
		String source = null;

		ArrayList<Token> tokens = new ArrayList<Token>();

		System.out.println("Estou em Main.java\n");

		Lexemas l = new Lexemas();

		l.TabelaLexemas();
		l.ShowLexemasTable();

		ReadSource rs = new ReadSource();

		// String que contem todos os caracteres do arquivo de código
		source = rs.Reader(fileName);

		Lexico c = new Lexico();

		// Lista que armazena os tokens lidos da entrada
		tokens = c.findTokens(source);
		
		c.printLexemasList(tokens);
		
		c.printTokensList(tokens);
		
		
		// findErrors(tokens);
		
		// Constroi um arquivo que armazena a tabela de simbolos
		// makeTable(tokens);
	}
}
