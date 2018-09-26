package code;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Compilador {
	public int contaLinhas = 1, contaColunas = 1, i = 0;

	ArrayList<String> separadores = new ArrayList<>(
			Arrays.asList(" ", "\n", "\t", "(", "*", ")", "{", "}", ",", ";", "\r"));

	ArrayList<String> operadores = new ArrayList<>(
			Arrays.asList("+", "-", "*", "/", "=", "<", "<=", ">", ">=", "==", "!=", "&&", "||"));

	ArrayList<String> reservadas = new ArrayList<>(
			Arrays.asList("main", "int", "float", "if", "else", "while", "read", "print"));

	/*
	 * self.listaTokens = {'separadores':[' ', '\n', '\t', '(',
	 * ')','{','}',',',';','\r'],
	 * 'operadores':['+','-','*','/','=','<','<=','>','>=','==','!=','&&','||'],
	 * 'reservadas':['main','int','float','if','else','while','read','print'],
	 * 'inteiro': [], 'float': [], 'identificadores':[], 'identificadores1':[],
	 * 'ocorrencias':[], 'operadores1':[], 'separadores1':[], 'reservadas1':[],
	 * 'erros':[], 'tokens':[]}
	 */

	ArrayList<Token> findTokens(String source) {
		String sLida = "", auX = "", tempS = "";
		ArrayList<Token> tokenList = new ArrayList<Token>();
		ArrayList<String> tempList = new ArrayList<String>();

		// Enquanto todos os caracteres não forem lidos
		while (i != source.length()) {
			// Le o caractere na posicao
			sLida = getChar(source, i);
			System.out.println("sLida: " + sLida);

			// Incrementa o numero de Linhas se for um "\n"
			if (sLida.equals("\n")) {
				contaLinhas++;
			}

			// Concatena o caractere ao final da string
			auX = auX + sLida;
			System.out.println("auX: " + auX);

			// Verifica se o caracter é um separador
			if (isSeparator(sLida)) {
				System.out.println("É separador!");
				System.out.println("auX: " + auX);
				// Adiciona um token contendo o nome do Token e a linha
				tokenList.add(new Token(auX, contaLinhas));
				// Reseta a String
				auX = "";
			}

			// Incrementa o numero de colunas
			contaColunas++;
			i++;
		}

		System.out.println("\nTOKENS LIST:\n");
		printTokenList(tokenList);

		return tokenList;
	}

	private void printTokenList(ArrayList<Token> tokenList) {
		for (int i = 0; i < tokenList.size(); i++) {
			System.out.println("nome: " + tokenList.get(i).getNomeToken());
			System.out.println("nLinha: " + tokenList.get(i).getnLinha());
		}

	}

	// Retorna se uma string é um separador
	boolean isSeparator(String s) {
		for (String string : separadores) {
			if (s.equals(string)) {
				return true;
			}
		}
		return false;
	}

	// Retorna o caractere de uma dada posicao numa string
	String getChar(String source, int i) {
		String s = Character.toString(source.charAt(i));

		if (s.equals("\n")) {
			System.out.println("CHAR LIDO: BarraN");
		}
		if (Character.isSpaceChar((s.charAt(0)))) {
			System.out.println("CHAR LIDO: SPACE");
		}
		if (s.equals("\t")) {
			System.out.println("CHAR LIDO: TAB");
		} else {
			System.out.println("CHAR LIDO: " + s);
		}

		return s;
	}

	public int getContaLinhas() {
		return contaLinhas;
	}

	public void setContaLinhas(int contaLinhas) {
		this.contaLinhas = contaLinhas;
	}

	public int getContaColunas() {
		return contaColunas;
	}

	public void setContaColunas(int contaColunas) {
		this.contaColunas = contaColunas;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

}
