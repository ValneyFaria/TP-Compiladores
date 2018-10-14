package code;

import java.util.Set;
import java.util.HashMap;
import java.util.Map.Entry;

// Contem a Tabela de Lexemas e Tokens
class Lexemas {
	// Tabela de Lexemas
	private final HashMap<String, String> Lexemas = new HashMap<>();

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
		Lexemas.put("print", "PRINT");
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
		Lexemas.put("==", "EQ");
		Lexemas.put("+", "PLUS");
		Lexemas.put("-", "MINUS");
		Lexemas.put("*", "MULT");
		Lexemas.put("/", "DIV");
		Lexemas.put("++", "PLUSPLUS");

		return Lexemas;
	}

	void ShowLexemasTable() {
		/* Exibe conteudo usando Iterator */
		Set<Entry<String, String>> set = Lexemas.entrySet();
		for (Entry<String, String> mentry : set) {
			System.out.print("CHAVE: \"" + mentry.getKey() + "\"");
			printaSpaces(mentry.getKey().length());
			System.out.print(" VALOR:");
			printaSpaces(2);

			System.out.println(mentry.getValue());
		}
	}

	private void printaSpaces(int i) {
		while (i < 5) {
			System.out.print(" ");
			i++;
		}
	}
}
