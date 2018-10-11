package code;

import java.util.ArrayList;

public class Sintatico {
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
	private void match(String string) {
		
		
	}
	
	// Funcao Match(String s);
	
}
