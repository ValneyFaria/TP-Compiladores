package code;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Lexico {

	public static void main(String[] args) {

		// Tabela de Lexemas
		HashMap<String, String> Lexemas = new HashMap<String, String>();

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
		Lexemas.put("int", "INT");
		Lexemas.put("int", "INT");
		Lexemas.put("int", "INT");
		Lexemas.put("int", "INT");

		/*
		 * [A-Za-z] ([A-Za-z] | [0-9] | _ )* [0-9] ([0-9])* [0-9] ([0-9])*.[0-9]
		 * ([0-9])*
		 * 
		 * 
		 */

		/* Exibe conteudo usando Iterator */
		Set set = Lexemas.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry) iterator.next();
			System.out.print("CHAVE: " + mentry.getKey() + " - VALOR: ");
			System.out.println(mentry.getValue());
		}

		System.out.println("oi");

	}
}
