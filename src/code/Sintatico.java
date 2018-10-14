package code;

import java.util.Set;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;

class Sintatico {
	private int i = 0;
	private String tipo;
	private Token token = new Token();
	private ArrayList<Token> tokenList = new ArrayList<>();
	private HashMap<String, Simbolo> SymbolTable = new HashMap<>();

	public Sintatico(ArrayList<Token> tokenList) {
		this.tokenList = tokenList;
	}

	public Sintatico() {
	}

	private void imprimeErro() {
		print("Erro sintatico. Token nao esperado na entrada.");
		i = i - 1;
		// Continua a analise para verificar outros erros
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
			System.out.print("\nERRO SINTATICO: Token ");
			System.out.print(tokenList.get(i).getNomeToken());
			print(" nao esperado na linha " + tokenList.get(i).getnLinha());
			print("Token Esperado: " + tok);
			i = i - 1;
			token = tokenList.get(i);
		}
	}

	public void Programa() {
		print("INICIANDO ANALISE SEMANTICA:\n");
		match("INT");
		match("MAIN");
		match("LBRACKET");
		match("RBRACKET");
		match("LBRACE");
		// Corpo do Bloco
		Decl_Comando();
		match("RBRACE");
		print("\nFIM DA ANALISE SEMANTICA!");

		ImprimeListaSimbolos();
	}

	// Declaracao Decl_Comando | Comando Decl_Comando | Vazia
	private void Decl_Comando() {
		// print("Ativando Decl_Comando()");

		String Aux = tokenList.get(i).getNomeToken();

		// Declaracao -> Tipo -> INT | FLOAT

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
		// Declaracao
		// TODO: Tratar Vazias

		// TODO: Adiciona o Tipo e a linha ao simbolo
		case "INT":
			Declaracao();
			Decl_Comando();
			break;
		case "FLOAT":
			Declaracao();
			Decl_Comando();
			break;

		// Comando
		case "LBRACE":
			Comando();
			Decl_Comando();
			break;
		case "ID":
			Comando();
			Decl_Comando();
			break;
		case "IF":
			Comando();
			Decl_Comando();
			break;
		case "WHILE":
			Comando();
			Decl_Comando();
			break;
		case "READ":
			Comando();
			Decl_Comando();
			break;
		case "PRINT":
			Comando();
			Decl_Comando();
			break;
		case "FOR":
			Comando();
			Decl_Comando();
			break;
		default:
			break;
		}
	}

	// Tipo ID Decl2
	private void Declaracao() {
		// print("Ativando Declaracao()");

		Tipo();
		// Token Temporario
		Token tokAtual = tokenList.get(i);
		// Cria o Simbolo
		Simbolo simbolo = new Simbolo(tokAtual.getLexema(), tipo, i);

		// Verifica se o Simbolo ja existe na tabela de simbolos
		if (SymbolTable.containsKey(simbolo.getLexema())) {
			print("\nVariavel [" + simbolo.getLexema() + "] ja declarada!\n");
		} else {
			// Adiciona o Simbolo na Tabela de Simbolos
			SymbolTable.put(simbolo.getLexema(), simbolo);
		}

		match("ID");
		Decl2();
	}

	// COMMA ID Decl2 | PCOMMA | ATTR Expressao Decl2
	private void Decl2() {
		// print("Ativando Decl2()");
		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
		case "COMMA":
			match("COMMA");

			// Token Temporario
			Token tokAtual = tokenList.get(i);
			// Cria o Simbolo
			Simbolo simbolo = new Simbolo(tokAtual.getLexema(), tipo, i);
			// Adiciona o Simbolo na Tabela de Simbolos
			SymbolTable.put(simbolo.getLexema(), simbolo);

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
		String Aux = tokenList.get(i).getNomeToken();

		switch (Aux) {
		case "INT":
			match("INT");
			tipo = "INT";
			break;
		case "FLOAT":
			match("FLOAT");
			tipo = "FLOAT";
			break;
		}
	}

	private void Comando() {
		// print("Ativando Comando()");
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
		// print("Ativando Bloco()");
		match("LBRACE");
		Decl_Comando();
		match("RBRACE");
	}

	// ID ATTR Expressao PCOMMA
	private void Atribuicao() {
		// print("Ativando Atribuicao()");
		match("ID");
		match("ATTR");
		Expressao();
		match("PCOMMA");
	}

	// IF LBRACKET Expressao RBRACKET Comando ComandoSenao
	private void ComandoSe() {
		// print("Ativando ComandoSe()");
		match("IF");
		match("LBRACKET");
		Expressao();
		match("RBRACKET");
		Comando();
		if (tokenList.get(i).getNomeToken().equals("ELSE")) {
			match("ELSE");
			Comando();
		}
	}

	// Implementado ACIMA
	// ELSE Comando | Vazia
	private void ComandoSenao() {
		// print("Ativando ComandoSenao()");
		match("ELSE");
		Comando();
		// TODO: Tratar Vazias
	}

	// WHILE LBRACKET Expressao RBRACKET Comando
	private void ComandoEnquanto() {
		// print("Ativando ComandoEnquanto()");
		match("WHILE");
		match("LBRACKET");
		Expressao();
		match("RBRACKET");
		Comando();
	}

	// READ ID PCOMMA
	private void ComandoRead() {
		// print("Ativando ComandoRead()");
		match("READ");
		match("ID");
		match("PCOMMA");
	}

	// PRINT LBRACKET Expressao RBRACKET PCOMMA
	private void ComandoPrint() {
		// print("Ativando ComandoPrint()");
		match("PRINT");
		match("LBRACKET");
		Expressao();
		match("RBRACKET");
		match("PCOMMA");
	}

	// FOR LBRACKET AtribuicaoFor PCOMMA Expressao PCOMMA AtribuicaoFor RBRACKET
	// Comando

	private void ComandoFor() {
		// print("Ativando ComandoFor()");
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
		// print("Ativando AtribuicaoFor()");
		match("ID");
		match("ATTR");
		Expressao();
	}

	// Adicao RelacaoOpc
	private void Expressao() {
		// print("Ativando Expressao()");
		Adicao();
		RelacaoOpc();
	}

	// OpRel Adicao RelacaoOpc | Vazia
	private void RelacaoOpc() {
		// print("Ativando RelacaoOpc()");

		String Aux = tokenList.get(i).getNomeToken();

		if (Aux.equals("LT") || Aux.equals("LE") || Aux.equals("GT") || Aux.equals("GE")) {
			OpRel();
			Adicao();
			RelacaoOpc();
		}
		// TODO: Tratar Vazias
	}

	// LT | LE | GT | GE
	private void OpRel() {
		// print("Ativando OpRel()");
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
		// print("Ativando Adicao()");
		Termo();
		AdicaoOpc();
	}

	// OpAdicao Termo AdicaoOpc | Vazia
	private void AdicaoOpc() {
		// print("Ativando AdicaoOpc()");

		String Aux = tokenList.get(i).getNomeToken();

		if (Aux.equals("PLUS") || Aux.equals("MINUS")) {
			OpAdicao();
			Termo();
			AdicaoOpc();
		}
		// TODO: Tratar vazias
	}

	// PLUS | MINUS
	private void OpAdicao() {
		// print("Ativando OpAdicao()");
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
		// print("Ativando Termo()");
		Fator();
		TermoOpc();
	}

	// OpMult Fator TermoOpc | Vazia
	private void TermoOpc() {
		// print("Ativando TermoOpc()");

		String Aux = tokenList.get(i).getNomeToken();

		if (Aux.equals("MULT") || Aux.equals("DIV")) {
			OpMult();
			Fator();
			TermoOpc();
		}
		// TODO: Tratar Vazias
	}

	// MULT | DIV
	private void OpMult() {
		// print("Ativando OpMult()");

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
		// print("Ativando Fator()");

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

	// Atalho para printar
	private void print(String string) {
		System.out.println(string);
	}

	private void ImprimeListaSimbolos() {
		print("\nLISTA DE SIMBOLOS:\n");

		/* Exibe conteudo usando Iterator */
		Set<Entry<String, Simbolo>> set = SymbolTable.entrySet();
		for (Entry<String, Simbolo> mentry : set) {
			System.out.print("CHAVE (lex.): \"" + mentry.getKey() + "\"");
			printaSpaces(mentry.getKey().length());
			System.out.print(" VALOR (obj. lex.):");
			printaSpaces(2);

			System.out.println(mentry.getValue().getTipo());
		}
	}

	private void printaSpaces(int i) {
		while (i < 5) {
			System.out.print(" ");
			i++;
		}
	}
}
