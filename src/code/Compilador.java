package code;

import java.util.Arrays;
import java.util.ArrayList;

// TODO: OI
public class Compilador {
	public int contaLinhas = 1, contaColunas = 1, i = 0;

	final ArrayList<String> separadores = new ArrayList<>(
			Arrays.asList(" ", "\n", "\t", "(", "*", ")", "{", "}", ",", ";", "\r"));

	final ArrayList<String> operadores = new ArrayList<>(
			Arrays.asList("+", "-", "*", "/", "=", "<", "<=", ">", ">=", "==", "!=", "&&", "||"));

	final ArrayList<String> reservadas = new ArrayList<>(
			Arrays.asList("main", "int", "float", "if", "else", "while", "read", "print"));

	ArrayList<Token> findTokens(String source) {
		String sLida = "", auX = "";
		ArrayList<Token> tokenList = new ArrayList<Token>();

		System.out.println("\n\nBUSCANDO TOKENS");
		// Enquanto todos os caracteres não forem lidos
		while (i != source.length()) {
			// Le o caractere na posicao
			sLida = getChar(source, i);

			System.out.printf("\nCHAR LIDO: [%s]\n", sLida);

			// Incrementa o numero de Linhas se for um "\n"
			if (sLida.equals("\n")) {
				contaLinhas++;
			}

			// Se o caractere não É Separador nem operador
			if (!isSeparator(sLida) && !isOperador(sLida)) {
				System.out.println("Nem Operador, nem Separador.\nConcatenando...");
				// Concatena o caractere ao final da string
				auX = auX + sLida;
				System.out.printf("auX: [%s]\n", auX);
			}

			// Verifica se o caractere ï¿½ um separador
			else if (isSeparator(sLida)) {
				System.out.println("É Separador!");
				System.out.printf("auX: [%s]\n", auX);

				// Verifica se ï¿½ BarraN
				if (sLida.equals("\n")) {
					System.out.println("BarraN");
				}

				// String t recebe o caractere anterior
				String sBefore = getChar(source, i - 1);
				// Verifica se o simbolo anterior ï¿½ um operador
				if (isOperador(sBefore)) {
					System.out.println("Caractere Anterior é Separador!");
					System.out.printf("sBefore: [%s]\n", sBefore);
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Separador (%s) Adicionado\n", sLida);
				}
				// Verifica se o simbolo anterior ï¿½ um separador
				else if (isSeparator(sBefore)) {
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Separador (%s) Adicionado\n", sLida);
				} else {
					// Adiciona um token contendo o nome do Token, a linha e a
					// coluna
					// adiciona token
					tokenList.add(new Token(auX, contaLinhas));
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Token (%s) Adicionado, Separador (%s) Adicionado\n", auX, sLida);
				}

				// Reseta a String
				auX = "";
			}

			// Verifica se o caractere ï¿½ um operador
			else if (isOperador(sLida)) {
				System.out.println("É Operador!");
				System.out.printf("auX: [%s]\n", auX);

				// String t recebe o caractere anterior
				String sBefore = getChar(source, i - 1);
				// Verifica se o simbolo anterior ï¿½ um operador
				if (isOperador(sBefore)) {
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Separador (%s) Adicionado\n", sLida);
				}
				// Verifica se o simbolo anterior ï¿½ um separador
				else if (isSeparator(sBefore)) {
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Separador (%s) Adicionado\n", sLida);
				} else {
					// Adiciona um token contendo o nome do Token, a linha e a
					// coluna
					// adiciona token
					tokenList.add(new Token(auX, contaLinhas));
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Token (%s) Adicionado, Separador (%s) Adicionado\n", auX, sLida);
				}
				// Reseta a String
				auX = "";
			}

			// Se i ï¿½ igual ao tamanho da palavra e o concanetado não ï¿½
			// nem
			// operador nem separador
			else if (i == source.length()) {
				// adiciona token
				tokenList.add(new Token(auX, contaLinhas));
				System.out.printf("Token (%s) Adicionado\n", auX);
				// Reseta a String
				auX = "";
			}

			// Incrementa o numero de colunas
			contaColunas++;
			i++;
		}

		// Chamada do procedimento para limpeza dos espaços na lista de tokens
		tokenList = removeSpaces(tokenList);

		System.out.println("\nTOKENS LIST:\n");
		printTokenList(tokenList);

		return tokenList;
	}

	// Exibe os Tokens na Lista de Tokens
	private void printTokenList(ArrayList<Token> tokenList) {
		for (int i = 0; i < tokenList.size(); i++) {
			System.out.println(i + " - nome: " + tokenList.get(i).getNomeToken());
			//System.out.println("	nLinha: " + tokenList.get(i).getnLinha());
		}
	}

	// Remove os Espaï¿½os na Lista de Tokens
	private ArrayList<Token> removeSpaces(ArrayList<Token> tokenList) {
		System.out.println("Removendo Espacos...");
		for (int i = 0; i < tokenList.size(); i++) {
			if (tokenList.get(i).getNomeToken().equals(" ")) {
				tokenList.remove(i);
			}
		}
		return tokenList;
	}

	// Retorna se uma dada string ï¿½ um separador
	boolean isSeparator(String s) {
		for (String string : separadores) {
			if (s.equals(string)) {
				return true;
			}
		}
		return false;
	}

	// Retorna se uma dada string ï¿½ um separador
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
		return Character.toString(source.charAt(i));
	}

	public int getContaLinhas() {
		return contaLinhas;
	}

	public void setContaLinhas(int contaLinhas) {
		this.contaLinhas = contaLinhas;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
