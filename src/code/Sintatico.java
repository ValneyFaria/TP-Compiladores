package code;

import java.util.ArrayList;

public class Sintatico {
	int i = 0;
	Token token = new Token();
	ArrayList<Token> tokenList = new ArrayList<Token>();

	public Sintatico(ArrayList<Token> tokenList) {
		this.tokenList = tokenList;
	}

	public void Programa() {
		match("INT");
		match("MAIN");
		match("LBRACKET");
		match("RBRACKET");
		match("LBRACE");

		match("RBRACE");
	}

	// Realiza um casamento e avanca na estrada
	private void match(String tok) {
		if (tokenList.get(i).getNomeToken().equals(tok)) {
			print("Token " + tokenList.get(i).getNomeToken() + " reconhecido!");
			i = i + 1;
			if (i < tokenList.size()) {
				token = tokenList.get(i);
			}
		} else {
			System.out.print("ERRO SINTÁTICO: Token ");
			System.out.print(tokenList.get(i).getNomeToken());
			print(" não esperado na linha " + tokenList.get(i).getnLinha());
			i = i - 1;
			token = tokenList.get(i);

		}
	}

	// Atalho para printar
	public void print(String string) {
		System.out.println(string);
	}
}
