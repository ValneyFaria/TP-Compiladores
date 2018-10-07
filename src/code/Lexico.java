package code;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Lexico {
	// Tabela de Lexemas
	final HashMap<String, String> Lexemas = new HashMap<String, String>();

	// Retorna a Tabela de Lexemas
	HashMap<String, String> TabelaLexemas() {

		Lexemas.put("main", "MAIN");
		Lexemas.put("int", "INT");
		Lexemas.put("float", "FLOAT");
		Lexemas.put("if", "IF");
		Lexemas.put("else", "ELSE");
		Lexemas.put("while", "WHILE");
		Lexemas.put("for", "FOR");
		Lexemas.put("read", "READ");
		Lexemas.put("(", "LBRACKET");
		Lexemas.put(")", "RBRACKET");
		Lexemas.put("{", "LBRACE");
		Lexemas.put("}", "RBRACE");
		Lexemas.put(",", "COMMA");
		Lexemas.put(";", "PCOMMA");
		Lexemas.put("=", "ATTR");
		Lexemas.put("<", "LT");
		Lexemas.put("<=", "LE");
		Lexemas.put(">", "GT");
		Lexemas.put(">=", "GE");
		Lexemas.put("+", "PLUS");
		Lexemas.put("-", "MINUS");
		Lexemas.put("*", "MULT");
		Lexemas.put("/", "DIV");

		/*
		 * [A-Za-z] ([A-Za-z] | [0-9] | _ )* [0-9] ([0-9])* [0-9] ([0-9])*.[0-9]
		 * ([0-9])*
		 */
		System.out.println("Estou em Lexico.java\n");

		return Lexemas;
	}

	void ShowLexemasTable() {
		/* Exibe conteudo usando Iterator */
		Set<Entry<String, String>> set = Lexemas.entrySet();
		Iterator<Entry<String, String>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> mentry = iterator.next();
			System.out.print("CHAVE: " + mentry.getKey() + " VALOR: ");
			printaSpaces(mentry.getKey().length());

			System.out.println(mentry.getValue());
		}
	}

	private void printaSpaces(int i) {
		while (i < 4) {
			System.out.print(" ");
			i++;
		}
	}
}
