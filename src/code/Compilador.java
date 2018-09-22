package code;

import java.util.ArrayList;

public class Compilador {
	public int contaLinhas = 1, contaColunas = 1, i = 0;
	
	
	
	/*self.listaTokens = {'separadores':[' ', '\n', '\t', '(', ')','{','}',',',';','\r'],
    'operadores':['+','-','*','/','=','<','<=','>','>=','==','!=','&&','||'],
    'reservadas':['main','int','float','if','else','while','read','print'],
'inteiro': [], 
	'float': [], 
	'identificadores':[],
	'identificadores1':[],
	    'ocorrencias':[],
	    'operadores1':[],
	    'separadores1':[],
	    'reservadas1':[],
	    'erros':[],
	    'tokens':[]}*/

	ArrayList<Token> findTokens(String source) {
		String auS = null;
		
		// Enquanto todos os caracteres não forem lidos
		while(i != source.length()) {
			auS = getChar(source, i);
			
			
			i++;
		}
		
		
		return null;
	}

	String getChar(String source, int i) {
		String s = Character.toString(source.charAt(i));
		System.out.println("CHAR LIDO: " + s);
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
