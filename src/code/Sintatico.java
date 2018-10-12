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
		print("Erro sintático. Token não esperado na entrada.");
		i = i - 1;
		// Continua a análise para verificar outros erros
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
			System.out.print("ERRO SINTÁTICO: Token ");
			System.out.print(tokenList.get(i).getNomeToken());
			print(" não esperado na linha " + tokenList.get(i).getnLinha());
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
		print("Ativação de Decl_Comando()");

	}

	private void Declaracao() {
		print("Ativação de Declaracao()");

	}

	private void Decl2() {
		print("Ativação de Decl2()");

	}

	private void Tipo() {
		print("Ativação de Tipo()");
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
		print("Ativação de Comando()");
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
		print("Ativação de Bloco()");

	}

	private void Atribuicao() {
		print("Ativação de Atribuicao()");
		match("ID");
		E();
		match("PCOMMA");
	}

	private void ComandoSe() {
		print("Ativação de ComandoSe()");
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
		print("Ativação de ComandoSenao()");

	}

	private void ComandoEnquanto() {

	}

	private void ComandoRead() {

		print("Ativação de ComandoRead()");
	}

	private void ComandoPrint() {

		print("Ativação de ComandoPrint()");
	}

	private void ComandoFor() {

		print("Ativação de ComandoFor()");
	}

	private void AtribuicaoFor() {

		print("Ativação de AtribuicaoFor()");
	}

	private void Expressao() {

		print("Ativação de Expressao()");
	}

	private void RelacaoOpc() {

		print("Ativação de RelacaoOpc()");
	}

	private void OpRel() {

		print("Ativação de OpRel()");
	}

	private void Adicao() {
		print("Ativação de Adicao()");

	}

	private void AdicaoOpc() {
		print("Ativação de AdicaoOpc()");

	}

	private void OpAdicao() {
		print("Ativação de OpAdicao()");

	}

	private void Termo() {
		print("Ativação de Termo()");

	}

	private void TermoOpc() {
		print("Ativação de TermoOpc()");

	}

	private void OpMult() {
		print("Ativação de OpMult()");

	}

	private void Fator() {
		print("Ativação de Fator()");

	}

	private void E() {
		print("Ativação de E()");

		String aux = tokenList.get(i).getNomeToken();

		if (aux.equals("ID") || aux.equals("NUM") || aux.equals("LBRACKET")) {
			T();
			E_();
			if (i == tokenList.size()) {
				print("Fim da análise sintática.");
			}
		} else {
			imprimeErro();
		}

	}

	private void E_() {
		print("Ativação de E_()");

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
		print("Ativação de T()");
		F();
		T_();
	}

	private void T_() {
		print("Ativação de T_()");
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
		print("Ativação de F()");
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
