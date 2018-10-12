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
			System.out.print("ERRO SINT�TICO: Token ");
			System.out.print(tokenList.get(i).getNomeToken());
			print(" n�o esperado na linha " + tokenList.get(i).getnLinha());
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

		match("RBRACE");
	}

	private void Decl_Comando() {
		print("Ativa��o de Decl_Comando()");

	}

	private void Declaracao() {
		print("Ativa��o de Declaracao()");

	}

	private void Decl2() {
		print("Ativa��o de Decl2()");

	}

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
		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
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
		}
	}

	private void Bloco() {
		print("Ativa��o de Bloco()");

	}

	private void Atribuicao() {
		print("Ativa��o de Atribuicao()");
		match("ID");
		E();
		match("PCOMMA");
	}

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

	private void ComandoSenao() {
		print("Ativa��o de ComandoSenao()");

	}

	private void ComandoEnquanto() {

	}

	private void ComandoRead() {

		print("Ativa��o de ComandoRead()");
	}

	private void ComandoPrint() {

		print("Ativa��o de ComandoPrint()");
	}

	private void ComandoFor() {

		print("Ativa��o de ComandoFor()");
	}

	private void AtribuicaoFor() {

		print("Ativa��o de AtribuicaoFor()");
	}

	private void Expressao() {

		print("Ativa��o de Expressao()");
	}

	private void RelacaoOpc() {

		print("Ativa��o de RelacaoOpc()");
	}

	private void OpRel() {

		print("Ativa��o de OpRel()");
	}

	private void Adicao() {
		print("Ativa��o de Adicao()");

	}

	private void AdicaoOpc() {
		print("Ativa��o de AdicaoOpc()");

	}

	private void OpAdicao() {
		print("Ativa��o de OpAdicao()");

	}

	private void Termo() {
		print("Ativa��o de Termo()");

	}

	private void TermoOpc() {
		print("Ativa��o de TermoOpc()");

	}

	private void OpMult() {
		print("Ativa��o de OpMult()");

	}

	private void Fator() {
		print("Ativa��o de Fator()");

	}

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
