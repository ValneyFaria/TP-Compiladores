package code;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "code.c";
		String source = null;
		
		System.out.println("Estou em Main.java\n");
		
		Lexico l = new Lexico();

		l.TabelaLexemas();
		l.ShowLexemasTable();
		
		ReadSource rs = new ReadSource();
		
		source = rs.Reader(fileName);
//		tokens = findTokens(source) // token é um vetor de Tokens
//		findErrors(tokens)
//		makeTable(tokens) // Constroi um arquivo que armazena a tabela de simbolos
	}
}
