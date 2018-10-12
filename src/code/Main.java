package code;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "code.c";
		String source = null;

		ArrayList<Token> tokenList = new ArrayList<Token>();

		System.out.println("Estou em Main.java\n");

		Lexemas l = new Lexemas();

		l.TabelaLexemas();
		l.ShowLexemasTable();

		ReadSource rs = new ReadSource();

		// String que contem todos os caracteres do arquivo de c�digo
		source = rs.Reader(fileName);

		Lexico c = new Lexico();

		// Lista que armazena os tokens lidos da entrada
		tokenList = c.findTokens(source);

		// Exibe a Lista de Lexemas
		c.printLexemasList(tokenList);

		// Exibe a Lista de Tokens
		c.printTokensList(tokenList);

		boolean A = c.findErrors(tokenList);

		if (A) {
			System.out.println("\nERRO LEXICO ENCONTRADO!");
		} else {
			System.out.println("\nNENHUM ERRO LEXICO ENCONTRADO!");
		}

		Sintatico s = new Sintatico(tokenList);

		// Inicia a an�lise sint�tica
		s.Programa();

		// Constroi um arquivo que armazena a tabela de simbolos
		// makeTable(tokens);
	}
}
