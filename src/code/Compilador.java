package code;

import java.util.Arrays;
import java.util.ArrayList;

// TODO: Tratar os "\n" 				DONE
// TODO: Tratar aspas 					DONE
// TODO: Tratar Comentários				DONE
// TODO: Tratar operadores binários

public class Compilador {
	public int contaLinhas = 1, i = 0;

	final ArrayList<String> separadores = new ArrayList<>(
			Arrays.asList(" ", "\n", "\t", "(", "*", ")", "{", "}", ",", ";", "\r", "\""));

	final ArrayList<String> operadores = new ArrayList<>(
			Arrays.asList("+", "-", "*", "/", "=", "<", "<=", ">", ">=", "==", "!=", "&&", "||"));

	final ArrayList<String> reservadas = new ArrayList<>(
			Arrays.asList("main", "int", "float", "if", "else", "while", "read", "print"));

	ArrayList<Token> findTokens(String source) {
		String sLida = "", auX = "";
		ArrayList<Token> tokenList = new ArrayList<Token>();

		// Remove os caracteres de quebra de linha
		// print("\nLimpando Caracteres de quebra de Linha:\n");
		// print("ANTES: " + source);
		// source = source.replaceAll("\\r\\n|\\r|\\n", " ");
		// print("DEPOIS: " + source);

		// TODO: Buscando tokens
		print("\n\nBUSCANDO TOKENS");
		// Enquanto todos os caracteres não forem lidos
		while (i < source.length()) {
			// Le o caractere na posicao
			sLida = getChar(source, i);

			System.out.printf("\nCHAR LIDO: [%s]\n", sLida);

			// Incrementa o numero de Linhas se for um "\n"
			if (sLida.equals("\n")) {
				contaLinhas++;
			}

			// Se o caractere não é Separador nem operador
			if (!isSeparator(sLida) && !isOperador(sLida)) {
				print("Nem Operador, nem Separador.\nConcatenando...");
				// Concatena o caractere ao final da string auxiliar
				auX = auX + sLida;
				System.out.printf("auX: [%s]\n", auX);
			}

			// TODO: Tratando Comentarios
			if (sLida.equals("/") && (getChar(source, i + 1).equals("/") || getChar(source, i + 1).equals("*"))) {

				// Se for possivel obter o proximo caractere
				if (i + 1 < source.length()) {
					// String sAfter1 recebe o caractere Posterior

					// TODO: Comentario de Linha
					if (getChar(source, i + 1).equals("/")) {
						print("COMENTARIO DE LINHA!\n");
						String sAfter1 = getChar(source, i);

						// Percorre a string em busca do fim do comentário
						while ((sAfter1.equals("\n") == false || sAfter1.equals("\0")) && (i < source.length())) {
							// Obtem o caractere na posicao i

							sAfter1 = getChar(source, i);
							if (sAfter1.equals("\n"))
								print("AAWFAW");
							System.out.print("I: " + i);
							print(" CHAR: " + sAfter1);
							i++;
						}
						// Para resolver um problema
						i--;

						// TODO: Comentario de Bloco
					} else if (getChar(source, i + 1).equals("*")) {
						print("COMENTARIO DE BLOCO!\n");
						// Tratar comentarios de bloco vazios
						if (getChar(source, i + 2).equals("*") && getChar(source, i + 3).equals("/")) {
							i = i + 4;
						} else {
							// Ja ignorados os caracteres '/' e '*'
							i = i + 2;
							// Obtem o proximo caractere da sequencia
							String blz = getChar(source, i);

							System.out.println(blz.equals("/"));
							System.out.println(blz.equals("*"));

							// Enquanto nao encontrar um '*' ou final da string
							while (blz.equals("*") == false || blz.equals("\0") == false && (i < source.length())) {
								System.out.printf("i: %2d - %s\n", i, blz);

								// Le um caractere da String
								blz = getChar(source, i);

								// Se encontrar uma '*' seguido por uma '/'
								if (blz.equals("*") && getChar(source, i + 1).equals("/")) {
									print("Fim do Comentario de Bloco");
									// Incrementa na sequencia
									i = i + 2;
									break;
								} else {
									print("TO NO ELSE");
									// print("i: " + i + " tam: " +
									// source.length());
									i++;
								}
							}
						}
					}
				}
			}

			// TODO: Tratando Separadores
			// Verifica se o caractere lido é um separador
			else if (isSeparator(sLida)) {
				print("É Separador!");
				System.out.printf("auX: [%s]\n", auX);

				// Verifica se é BarraN
				if (sLida.equals("\n")) {
					print("BarraN");
				}

				// String t recebe o caractere anterior
				String sBefore = getChar(source, i - 1);
				// Verifica se o caractere anterior é um operador
				if (isOperador(sBefore)) {
					print("Caractere Anterior é Operador!");
					System.out.printf("sBefore: [%s]\n", sBefore);
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Separador (%s) Adicionado\n", sLida);
				}
				// Verifica se o caractere anterior é um separador
				else if (isSeparator(sBefore)) {
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Separador (%s) Adicionado\n", sLida);
				} else {
					// Adiciona um token contendo o nome do Token e a linha
					// adiciona token
					tokenList.add(new Token(auX, contaLinhas));
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Token (%s) Adicionado, Separador (%s) Adicionado\n", auX, sLida);
				}

				// Reseta a String
				print("Limpando AUX...");
				auX = "";
			}

			// TODO: Tratando Operadores
			// Verifica se o caractere lido é um operador
			else if (isOperador(sLida)) {
				print("É Operador!");
				System.out.printf("auX: [%s]\n", auX);

				// Tratamento para Operadores Binarios
				// Se o proximo caractere da string de origem é operador
				if (isOperador(getChar(source, i + 1))) {
					// Adiciona Aux
					System.out.printf("BUB: Operador (%s) Adicionado\n", auX);
					tokenList.add(new Token(auX, contaLinhas));
					// Concatena as duas Strings
					sLida = sLida + getChar(source, i + 1);
					i++;
				}

				// String sBefore recebe o caractere anterior
				String sBefore = getChar(source, i - 1);
				// Verifica se o caractere anterior é um operador
				if (isOperador(sBefore)) {
					// adiciona o operador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("XOW: Operador (%s) Adicionado\n", sLida);
				}
				// Verifica se o caractere anterior é um separador
				else if (isSeparator(sBefore)) {
					// Verificação para operadores binários
					// String t recebe o caractere Posterior
					String sAfter = getChar(source, i + 1);
					// Verifica se o simbolo anterior é um operador
					if (isOperador(sAfter)) {
						// Concatena as duas Strings
						sLida = sLida + sAfter;
						// adiciona o Operador
						tokenList.add(new Token(sLida, contaLinhas));
						System.out.printf("Operador (%s) Adicionado\n", sLida);
						i++;
					} else {
						// adiciona o separador
						tokenList.add(new Token(sLida, contaLinhas));
						System.out.printf(" AKI Separador (%s) Adicionado\n", sLida);
					}

				} else {
					// Adiciona um token contendo o nome do Token e a linha
					tokenList.add(new Token(auX, contaLinhas));
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Token (%s) Adicionado, Separador (%s) Adicionado\n", auX, sLida);
				}

				// Reseta a String
				auX = "";
			}

			// Se i é igual ao tamanho da palavra e o concanetado não é
			// nem operador nem separador
			else if (i == source.length()) {
				// adiciona token
				tokenList.add(new Token(auX, contaLinhas));
				print("\n\nAnalise Final!\n");
				System.out.printf("Token (%s) Adicionado\n", auX);
				// Reseta a String
				auX = "";
			}
			i++;
		}

		// Chamada do procedimento para limpeza dos espaços na lista de tokens
		tokenList = removeSpaces(tokenList);

		printTokenList(tokenList);

		return tokenList;
	}

	// Atalho para printar
	private void print(String string) {
		System.out.println(string);
	}

	// Exibe os Tokens na Lista de Tokens
	private void printTokenList(ArrayList<Token> tokenList) {
		System.out.println("\nLISTA DE TOKENS:\n");
		for (int i = 0; i < tokenList.size(); i++) {
			System.out.printf("%02d - Nome: %s\n", i, tokenList.get(i).getNomeToken());
			// System.out.println(" nLinha: " + tokenList.get(i).getnLinha());
		}
	}

	// Remove os Espaços na Lista de Tokens
	private ArrayList<Token> removeSpaces(ArrayList<Token> tokenList) {
		print("\nREMOVENDO ESPACOS VAZIOS...");
		for (int i = 0; i < tokenList.size(); i++) {
			// Remove Espacos
			if (tokenList.get(i).getNomeToken().equals(" ")) {
				tokenList.remove(i);
			}
			// Remove Strings Vazias
			if(tokenList.get(i).getNomeToken().length() == 0) {
				tokenList.remove(i);
			}
		}
		return tokenList;
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
