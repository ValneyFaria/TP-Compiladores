package code;

import java.util.Arrays;
import java.util.ArrayList;

// TODO: Tratar os "\n" 				DONE
// TODO: Tratar aspas 					DONE
// TODO: Tratar Comentarios			DONE
// TODO: Tratar operadores binarios

// Realiza a Leitura dos caracteres da string a fim de identificar os lexemas 
class Lexico extends Lexemas {
	private int contaLinhas = 1;
	private int i = 0;

	private final ArrayList<String> separadores = new ArrayList<>(
			Arrays.asList(" ", "\n", "\t", "(", "*", ")", "{", "}", ",", ";", "\r", "\""));

	private final ArrayList<String> operadores = new ArrayList<>(
			Arrays.asList("+", "++", "-", "*", "/", "=", "<", "<=", ">", ">=", "==", "!=", "&&", "||"));

	final ArrayList<String> reservadas = new ArrayList<>(
			Arrays.asList("main", "int", "float", "if", "else", "while", "read", "print"));

	ArrayList<Token> findTokens(String source) {
		String sLida, auX = "";
		ArrayList<Token> tokenList = new ArrayList<>();

		// Remove os caracteres de quebra de linha
		// print("\nLimpando Caracteres de quebra de Linha:\n");
		// print("ANTES: " + source);
		// source = source.replaceAll("\\r\\n|\\r|\\n", " ");
		// print("DEPOIS: " + source);

		// TODO: Buscando tokens
		print("\n\nBUSCANDO TOKENS");
		// Enquanto todos os caracteres nao forem lidos
		while (i < source.length()) {
			// Le o caractere na posicao
			sLida = getChar(source, i);

			System.out.printf("\nCHAR LIDO: [%s]\n", sLida);

			// Incrementa o numero de Linhas se for um "\n"
			if (sLida.equals("\n")) {
				contaLinhas++;
			}

			// Se o caractere nao eh Separador nem operador
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

						// Percorre a string em busca do fim do comentario
						while ((!sAfter1.equals("\n") || sAfter1.equals("\0")) && (i < source.length())) {
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
							while (!blz.equals("*") || !blz.equals("\0") && (i < source.length())) {
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
			// Verifica se o caractere lido eh um separador
			else if (isSeparator(sLida)) {
				print("� Separador!");
				System.out.printf("auX: [%s]\n", auX);

				// Verifica se eh BarraN
				if (sLida.equals("\n")) {
					print("BarraN");
				}

				// String t recebe o caractere anterior
				String sBefore = getChar(source, i - 1);
				// Verifica se o caractere anterior eh um operador
				if (isOperador(sBefore)) {
					print("Caractere Anterior eh Operador!");
					System.out.printf("sBefore: [%s]\n", sBefore);
					// adiciona o separador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("Separador (%s) Adicionado\n", sLida);
				}
				// Verifica se o caractere anterior eh um separador
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
			// Verifica se o caractere lido eh um operador
			else if (isOperador(sLida)) {
				print("Eh Operador!");
				System.out.printf("auX: [%s]\n", auX);

				// Tratamento para Operadores Binarios
				// Se o proximo caractere da string de origem � operador
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
				// Verifica se o caractere anterior � um operador
				if (isOperador(sBefore)) {
					// adiciona o operador
					tokenList.add(new Token(sLida, contaLinhas));
					System.out.printf("XOW: Operador (%s) Adicionado\n", sLida);
				}
				// Verifica se o caractere anterior � um separador
				else if (isSeparator(sBefore)) {
					// Verifica��o para operadores bin�rios
					// String t recebe o caractere Posterior
					String sAfter = getChar(source, i + 1);
					// Verifica se o simbolo anterior � um operador
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

			// Se i � igual ao tamanho da palavra e o concanetado n�o �
			// nem operador nem separador
			// TODO: Testar possibilidade de ignorar
			// TODO: Consultar a lista de lexemas: Se nao eh palavra reservada,
			// � ID
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

		// Chamada do procedimento para limpeza dos espa�os na lista de tokens
		removeSpaces(tokenList);

		// Exibe a Lista de Tokens
		// printTokenList(tokenList);

		tokenizer(tokenList);

		return tokenList;
	}

	// Atalho para printar
	private void print(String string) {
		System.out.println(string);
	}

	// Exibe os Lexemas na Lista de Tokens
	void printLexemasList(ArrayList<Token> tokenList) {
		System.out.println("\nLISTA DE LEXEMAS:\n");
		for (int i = 0; i < tokenList.size(); i++) {
			System.out.printf("%02d - Lex: %s\n", i, tokenList.get(i).getLexema());
			// System.out.println(" nLinha: " + tokenList.get(i).getnLinha());
		}
	}

	private void printaSpaces(int i) {
		while (i < 18) {
			System.out.print(" ");
			i++;
		}
	}

	void printTokensList(ArrayList<Token> tokenList) {
		System.out.println("\nLISTA DE TOKENS:\n");
		print("Token    |   Num. da Linha   | Lexema");
		print("------------------------------------");
		for (Token aTokenList : tokenList) {
			System.out.printf("%s", aTokenList.getNomeToken());
			printaSpaces(aTokenList.getNomeToken().length());
			System.out.printf("%3d", aTokenList.getnLinha());
			printaSpaces(7);
			System.out.printf("%s\n", aTokenList.getLexema());
		}
	}

	// Realiza a identificacao dos tokens
	private void tokenizer(ArrayList<Token> tokenList) {
		Lexemas lexemas = new Lexemas();
		String nomeTok;

		for (Token aTokenList : tokenList) {

			// Se o Hash contem a chave
			if (lexemas.TabelaLexemas().containsKey(aTokenList.getLexema())) {
				// Realiza o casamento com o Token
				nomeTok = lexemas.TabelaLexemas().get(aTokenList.getLexema());
				aTokenList.setNomeToken(nomeTok);
				System.out.println("CASOU COM " + aTokenList.getNomeToken());
			}
			// Senao
			else {
				nomeTok = aTokenList.getLexema();
				// Realiza o casamento com inteiro
				if (nomeTok.matches("[0-9]+")) {
					print("CASOU COM INTEIRO: " + nomeTok);
					aTokenList.setNomeToken("INTEGER_CONST");
				}
				// Realiza o casamento com float
				else if (nomeTok.matches("[0-9]([0-9])*.[0-9]([0-9])*")) {
					print("CASOU COM FLOAT: " + nomeTok);
					aTokenList.setNomeToken("FLOAT_CONST");
				}
				// Considera como um ID
				else {
					print("CASOU COM ID");
					aTokenList.setNomeToken("ID");
				}
			}
		}
	}

	// Remove os Espacos na Lista de Tokens
	private ArrayList<Token> removeSpaces(ArrayList<Token> tokenList) {
		print("\nREMOVENDO ESPACOS VAZIOS...\n");

		/*
		 * for (Token token : tokenList) { String lexema =
		 * tokenList.get(i).getLexema();
		 * 
		 * char character = lexema.charAt(0); // This gives the character 'a'
		 * int ascii = (int) character; // ascii is now 97.
		 * System.out.println(ascii);
		 * 
		 * switch (lexema) { // Remove Espacos case " ": tokenList.remove(i); i
		 * = i + 1; break; // Remove Tabula��es case "\t":
		 * tokenList.remove(i); i = i + 1; break; // Remove Quebras de Linha
		 * case "\n": tokenList.remove(i); i = i + 1; default: break; } }
		 */

		for (int i = 0; i < tokenList.size(); i++) {
			String lexema = tokenList.get(i).getLexema();

			// char character = lexema.charAt(0); // Retorna um Char
			// int ascii = (int) character; // Valor inteiro do Char
			// System.out.printf("%c: %d\n", character, ascii);

			switch (lexema) {
			case "":
				tokenList.remove(i);
				i = i - 1;
				break;
			// Remove Espacos
			case " ":
				tokenList.remove(i);
				i = i - 1;
				break;
			// Remove Tabula��es
			case "\t":
				tokenList.remove(i);
				i = i - 1;
				break;
			// Remove Quebras de Linha
			case "\n":
				tokenList.remove(i);
				i = i - 1; // DEIXAR SEM O BREAK
				break;
			// Remove Caractere Estranho
			case "\r":
				tokenList.remove(i);
				i = i - 1;
				break;
			default:
				break;
			}
		}
		return tokenList;
	}

	// Retorna se uma dada string � um separador
	private boolean isSeparator(String s) {
		for (String string : separadores) {
			if (s.equals(string)) {
				return true;
			}
		}
		return false;
	}

	// Retorna se uma dada string � um separador
	private boolean isOperador(String s) {
		for (String string : operadores) {
			if (s.equals(string)) {
				return true;
			}
		}
		return false;
	}

	// Retorna o caractere de uma dada posicao numa string
	private String getChar(String source, int i) {
		return Character.toString(source.charAt(i));
	}

	// Retorna false caso exista erro l�xico
	public boolean findErrors(ArrayList<Token> tokenList) {
		boolean retorno = false;

		print("\nBUSCANDO ERROS LEXICOS:");

		for (Token aTokenList : tokenList) {
			String tk = aTokenList.getNomeToken();
			String lexema = aTokenList.getLexema();

			// Verifica se o nome de um ID eh valido
			if (tk.equals("ID")) {
				if (lexema.matches("[A-Za-z_]([A-Za-z]|[0-9]|_)*")) {
					// print("ACEITA!");
				} else {
					System.out.print("Token [" + lexema + "] nao reconhecido na linha ");
					System.out.println(aTokenList.getnLinha() + ".");
					retorno = true;
				}
			}
		}

		// Retorne false
		return retorno;
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
