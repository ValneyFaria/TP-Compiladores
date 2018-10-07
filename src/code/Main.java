package code;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "code.c";
		String source = null;

		ArrayList<Token> tokens = new ArrayList<Token>();

		System.out.println("Estou em Main.java\n");

		Lexico l = new Lexico();

		l.TabelaLexemas();
		l.ShowLexemasTable();

		ReadSource rs = new ReadSource();

		// String que contem todos os caracteres do arquivo de código
		source = rs.Reader(fileName);

		Compilador c = new Compilador();

		// Lista que armazena os tokens lidos da entrada
		tokens = c.findTokens(source);

		// findErrors(tokens);
		// makeTable(tokens); // Constroi um arquivo que armazena a tabela de
		// simbolos
	}
}
