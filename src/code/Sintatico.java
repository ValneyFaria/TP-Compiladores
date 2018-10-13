package code;

import java.util.ArrayList;

public class Sintatico {
	int i = 0;
	Token token = new Token();
	ArrayList<Token> tokenList = new ArrayList<Token>();

	public Sintatico(ArrayList<Token> tokenList) {
		this.tokenList = tokenList;
	}

	private void imprimeErro() {
		print("Erro sint�tico. Token n�o esperado na entrada.");
		i = i - 1;
		// Continua a an�lise para verificar outros erros
		i = i + 1;
		token = tokenList.get(i);
	}

	// Realiza um casamento e avanca na estrada
	private void match(String tok) {
		// if(token.type == tok)
		if (tokenList.get(i).getNomeToken().equals(tok)) {
			print("Token " + tokenList.get(i).getNomeToken() + " reconhecido!");
			i = i + 1;
			if (i < tokenList.size()) {
				token = tokenList.get(i);
			}
		} else {
			System.out.print("\nERRO SINT�TICO: Token ");
			System.out.print(tokenList.get(i).getNomeToken());
			print(" n�o esperado na linha " + tokenList.get(i).getnLinha());
			print("Token Esperado: " + tok);
			i = i - 1;
			token = tokenList.get(i);
		}
	}

	public void Programa() {
		match("INT");
		match("MAIN");
		match("LBRACKET");
		match("RBRACKET");
		match("LBRACE");
		// Corpo do Bloco
		Decl_Comando();
		match("RBRACE");
	}

	// Declaracao Decl_Comando | Comando Decl_Comando | Vazia
	private void Decl_Comando() {
		print("Ativa��o de Decl_Comando()");

		String Aux = tokenList.get(i).getNomeToken();

		// Declaracao -> Tipo -> INT | FLOAT
		if (Aux.equals("INT") || Aux.equals("FLOAT")) {
			Tipo();
			match("ID");
			Decl2();
			Decl_Comando();
		}
		// Comando -> Bloco | Atribuicao | ComandoSe | ComandoEnquanto |
		// ComandoRead | ComandoPrint | ComandoFor

		// Bloco -> LBRACE
		// Atribuicao -> ID
		// ComandoSe -> IF
		// ComandoEnquanto -> WHILE
		// ComandoRead -> READ
		// ComandoPrint -> PRINT
		// ComandoFor -> FOR

		switch (Aux) {
		case "INT":
			Tipo();
			match("ID");
			Decl2();
			Decl_Comando();
			break;
		case "FLOAT":
			Tipo();
			match("ID");
			Decl2();
			Decl_Comando();
			break;
		case "LBRACE":
			Bloco();
			
			Decl_Comando();
			break;
		case "ID":

			Decl_Comando();
			break;
		case "IF":

			Decl_Comando();
			break;
		case "WHILE":

			Decl_Comando();
			break;
		case "READ":

			Decl_Comando();
			break;
		case "PRINT ":

			Decl_Comando();
			break;
		case "FOR":

			Decl_Comando();
			break;

		}

	}

	// Tipo ID Decl2
	private void Declaracao() {
		print("Ativa��o de Declaracao()");
		Tipo();
		// O erro ocorre aqui porque um ID era esperado e foi encontrado um
		// INTEGER_CONST na entrada
		match("ID");
		Decl2();
	}

	// COMMA ID Decl2 | PCOMMA | ATTR Expressao Decl2
	private void Decl2() {
		print("Ativa��o de Decl2()");
		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
		case "COMMA":
			match("COMMA");
			match("ID");
			Decl2();
			break;
		case "PCOMMA":
			match("PCOMMA");
			break;
		case "ATTR":
			match("ATTR");
			Expressao();
			Decl2();
			break;
		}
	}

	// INT | FLOAT
	private void Tipo() {
		print("Ativa��o de Tipo()");
		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
		case "INT":
			match("INT");
			break;
		case "FLOAT":
			match("FLOAT");
			break;
		}
	}

	private void Comando() {
		print("Ativa��o de Comando()");
		/*
		 * Bloco | Atribuicao | ComandoSe | ComandoEnquanto | ComandoRead |
		 * ComandoPrint | ComandoFor
		 */
		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
		case "LBRACE":
			Bloco();
			Comando();
			if (i == tokenList.size()) {
				return;
			}
			break;
		case "ID":
			Atribuicao();
			Comando();
			if (i == tokenList.size()) {
				return;
			}
			break;
		case "IF":
			ComandoSe();
			Comando();
			if (i == tokenList.size()) {
				return;
			}
			break;
		case "WHILE":
			ComandoEnquanto();
			Comando();
			if (i == tokenList.size()) {
				return;
			}
			break;
		case "READ":
			ComandoRead();
			Comando();
			if (i == tokenList.size()) {
				return;
			}
			break;
		case "PRINT":
			ComandoPrint();
			Comando();
			if (i == tokenList.size()) {
				return;
			}
			break;
		case "FOR":
			ComandoFor();
			Comando();
			if (i == tokenList.size()) {
				return;
			}
			break;
		}
	}

	// LBRACE Decl_Comando RBRACE
	private void Bloco() {
		print("Ativa��o de Bloco()");
		match("LBRACE");
		Decl_Comando();
		match("RBRACE");
	}

	// ID ATTR Expressao PCOMMA
	private void Atribuicao() {
		print("Ativa��o de Atribuicao()");
		match("ID");
		match("ATTR");
		E();
		match("PCOMMA");
	}

	// IF LBRACKET Expressao RBRACKET Comando ComandoSenao
	private void ComandoSe() {
		print("Ativa��o de ComandoSe()");
		match("IF");
		match("LBRACKET");
		E();
		match("RBRACKET");
		Comando();
		if (tokenList.get(i).getNomeToken().equals("ELSE")) {
			match("ELSE");
			Comando();
		}
	}

	// ELSE Comando | Vazia
	private void ComandoSenao() {
		print("Ativa��o de ComandoSenao()");
		match("ELSE");
		Comando();
		// TODO: Tratar Vazia
	}

	// WHILE LBRACKET Expressao RBRACKET Comando
	private void ComandoEnquanto() {
		print("Ativa��o de ComandoEnquanto()");
		match("WHILE");
		match("LBRACKET");
		Expressao();
		match("RBRACKET");
		Comando();
	}

	// READ ID PCOMMA
	private void ComandoRead() {
		print("Ativa��o de ComandoRead()");
		match("READ");
		match("ID");
		match("PCOMMA");
	}

	// PRINT LBRACKET Expressao RBRACKET PCOMMA
	private void ComandoPrint() {
		print("Ativa��o de ComandoPrint()");
		match("PRINT");
		match("LBRACKET");
		Expressao();
		match("RBRACKET");
		match("PCOMMA");
	}

	// FOR LBRACKET AtribuicaoFor PCOMMA Expressao PCOMMA AtribuicaoFor RBRACKET
	// Comando

	private void ComandoFor() {
		print("Ativa��o de ComandoFor()");
		match("FOR");
		match("LBRACKET");
		AtribuicaoFor();
		match("PCOMMA");
		Expressao();
		match("PCOMMA");
		AtribuicaoFor();
		match("RBRACKET");
		Comando();
	}

	// ID ATTR Expressao
	private void AtribuicaoFor() {
		print("Ativa��o de AtribuicaoFor()");
		match("ID");
		match("ATTR");
		Expressao();
	}

	// Adicao RelacaoOpc
	private void Expressao() {
		print("Ativa��o de Expressao()");
		Adicao();
		RelacaoOpc();
	}

	// OpRel Adicao RelacaoOpc | Vazia
	private void RelacaoOpc() {
		print("Ativa��o de RelacaoOpc()");
		OpRel();
		Adicao();
		RelacaoOpc();

		// TODO: Tratar Vazia
	}

	// LT | LE | GT | GE
	private void OpRel() {
		print("Ativa��o de OpRel()");
		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
		case "LT":
			match("LT");
			break;
		case "LE":
			match("LE");
			break;
		case "GT":
			match("GT");
			break;
		case "GE":
			match("GE");
			break;
		}
	}

	// Termo AdicaoOpc
	private void Adicao() {
		print("Ativa��o de Adicao()");
		Termo();
		AdicaoOpc();
	}

	// OpAdicao Termo AdicaoOpc | Vazia
	private void AdicaoOpc() {
		print("Ativa��o de AdicaoOpc()");
		OpAdicao();
		Termo();
		AdicaoOpc();

		// TODO: Tratar vazias
	}

	// PLUS | MINUS
	private void OpAdicao() {
		print("Ativa��o de OpAdicao()");
		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
		case "PLUS":
			match("PLUS");
			break;
		case "MINUS":
			match("MINUS");
			break;
		}
	}

	// Fator TermoOpc
	private void Termo() {
		print("Ativa��o de Termo()");
		Fator();
		TermoOpc();
	}

	// OpMult Fator TermoOpc | Vazia
	private void TermoOpc() {
		print("Ativa��o de TermoOpc()");
		OpMult();
		Fator();
		TermoOpc();
		// TODO: Tratar Vazias
	}

	// MULT | DIV
	private void OpMult() {
		print("Ativa��o de OpMult()");

		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
		case "MULT":
			match("MULT");
			break;
		case "DIV":
			match("DIV");
			break;
		}
	}

	// ID | INTEGER_CONST | FLOAT_CONST | LBRACKET Expressao RBRACKET
	private void Fator() {
		print("Ativa��o de Fator()");

		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
		case "ID":
			match("ID");
			break;
		case "INTEGER_CONST":
			match("INTEGER_CONST");
			break;
		case "FLOAT_CONST":
			match("FLOAT_CONST");
			break;
		case "LBRACKET":
			match("LBRACKET");
			Expressao();
			match("RBRACKET");
			break;
		}
	}

	///////////////////////////// AUXILIARES - N�O SEI SE EU VOU USAR

	private void E() {
		print("Ativa��o de E()");

		String aux = tokenList.get(i).getNomeToken();

		if (aux.equals("ID") || aux.equals("NUM") || aux.equals("LBRACKET")) {
			T();
			E_();
			if (i == tokenList.size()) {
				print("Fim da an�lise sint�tica.");
			}
		} else {
			imprimeErro();
		}

	}

	private void E_() {
		print("Ativa��o de E_()");

		if (tokenList.get(i).getNomeToken().equals("PLUS")) {
			match("PLUS");
			T();
			E_();
		} else if (tokenList.get(i).getNomeToken().equals("MINUS")) {
			match("MINUS");
			T();
			E_();
		}
	}

	private void T() {
		print("Ativa��o de T()");
		F();
		T_();
	}

	private void T_() {
		print("Ativa��o de T_()");
		if (tokenList.get(i).getNomeToken().equals("MULT")) {
			match("MULT");
			F();
			T_();
		} else if (tokenList.get(i).getNomeToken().equals("DIV")) {
			match("DIV");
			F();
			T_();
		}
	}

	private void F() {
		print("Ativa��o de F()");
		if (tokenList.get(i).getNomeToken().equals("LBRACKET")) {
			match("LBRACKET");
			E();
			match("RBRACKET");
		} else if (tokenList.get(i).getNomeToken().equals("ID")) {
			match("ID");
		} else if (tokenList.get(i).getNomeToken().equals("NUM")) {
			match("NUM");
		} else {
			imprimeErro();
		}
	}

	// Atalho para printar
	public void print(String string) {
		System.out.println(string);
	}
}
