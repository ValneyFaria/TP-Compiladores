package code;

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
		String sLida = "", auX = "";
		ArrayList<Token> tokenList = new ArrayList<Token>();

		System.out.println("\n\nBUSCANDO TOKENS");
		// Enquanto todos os caracteres não forem lidos
		while (i != source.length()) {
			// Le o caractere na posicao
			sLida = getChar(source, i);

			System.out.printf("\nCHAR LIDO: %s\n", sLida);

			// Incrementa o numero de Linhas se for um "\n"
			if (sLida.equals("\n")) {
				contaLinhas++;
			}

			// Se o caractere não é separador nem operador
			if (!isSeparator(sLida) && !isOperador(sLida)) {
				System.out.println("Nem Operador, nem Separador.\nConcatenando...");
				// Concatena o caractere ao final da string
				auX = auX + sLida;
				System.out.println("auX: " + auX);
			}

			// Verifica se o caractere é um separador
			else if (isSeparator(sLida)) {
				System.out.println("É separador!");
				System.out.println("auX: " + auX);
				
				// String t recebe o caractere anterior
				String t = getChar(source, i - 1);
				// Verifica se o simbolo anterior é um operador
				if(isOperador(t)) {
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas, i - sLida.length()));
					System.out.printf("Separador (%s) Adicionado\n", sLida);
				}
				// Verifica se o simbolo anterior é um separador
				else if (isSeparator(t)) {
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas, i - sLida.length()));
					System.out.printf("Separador (%s) Adicionado\n", sLida);
				}
				else{
					// Adiciona um token contendo o nome do Token, a linha e a
					// coluna
					// adiciona token
					tokenList.add(new Token(auX, contaLinhas, i - auX.length()));
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas, i - sLida.length()));
					System.out.printf("Token (%s) Adicionado, Separador (%s) Adicionado\n", auX, sLida);
				}

				
				// Reseta a String
				auX = "";
			}

			// Verifica se o caractere é um operador
			else if (isOperador(sLida)) {
				System.out.println("É Operador!");
				auX = sLida;
				System.out.println("auX: " + auX);

				// Adiciona um token contendo o nome do Token, a linha e a
				// coluna
				// adiciona token separador
				tokenList.add(new Token(auX, contaLinhas, i - auX.length()));
				System.out.printf("Token (%s) Adicionado\n", auX);
				// Reseta a String
				auX = "";
			}

			// Incrementa o numero de colunas
			contaColunas++;
			i++;
		}

		System.out.println("TOKENS LIST:\n");
		printTokenList(tokenList);

		return tokenList;
	}

	private void printTokenList(ArrayList<Token> tokenList) {
		for (int i = 0; i < tokenList.size(); i++) {
			System.out.println(i + " - nome: " + tokenList.get(i).getNomeToken());
			System.out.println("	nLinha: " + tokenList.get(i).getnLinha());
			System.out.println("	nColuna: " + tokenList.get(i).getnColuna());
		}

	}

	// Retorna se uma dada string é um separador
	boolean isSeparator(String s) {
		for (String string : separadores) {
			if (s.equals(string)) {
				return true;
			}
		}
		return false;
	}

	// Retorna se uma dada string é um separador
	boolean isOperador(String s) {
		for (String string : operadores) {
			if (s.equals(string)) {
				return true;
			}
		}

		return false;
	}

	// Retorna o caractere de uma dada posicao numa string
	String getChar(String source, int i) {
		String s = Character.toString(source.charAt(i));
		//
		// if (s.equals("\n")) {
		// System.out.println("CHAR LIDO: BarraN");
		// }
		// if (Character.isSpaceChar((s.charAt(0)))) {
		// System.out.println("CHAR LIDO: SPACE");
		// }
		// if (s.equals("\t")) {
		// System.out.println("CHAR LIDO: TAB");
		// } else {
		// System.out.println("CHAR LIDO: " + s);
		// }

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
