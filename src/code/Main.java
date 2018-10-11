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

		// Exibe a Lista de Lexemas
		c.printLexemasList(tokens);

		// Exibe a Lista de Tokens
		c.printTokensList(tokens);

		boolean A = c.findErrors(tokens);

		if (A) {
			System.out.println("\nERRO LEXICO ENCONTRADO!");
		} else {
			System.out.println("\nNENHUM ERRO LEXICO ENCONTRADO!");
		}
		
		Sintatico s = new Sintatico(tokens);
		// Constroi um arquivo que armazena a tabela de simbolos
		// makeTable(tokens);
	}
}
