package code;

class Simbolo {
	private int nLinha;
	private String tipo;
	private String lexema;

	Simbolo(String lexema, String tipo, int nLinha) {
		this.lexema = lexema;
		this.tipo = tipo;
		this.nLinha = nLinha;
	}

	public int getnLinha() {
		return nLinha;
	}

	public void setnLinha(int nLinha) {
		this.nLinha = nLinha;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
