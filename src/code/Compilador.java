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
		String auS = null;

		// Enquanto todos os caracteres não forem lidos
		while (i != source.length()) {
			auS = getChar(source, i);

			i++;
		}

		return null;
	}

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
		}
		else {
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
