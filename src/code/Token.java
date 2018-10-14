package code;

// Classe que representa um Token
class Token {
	private int nLinha;
	private String lexema;
	private String nomeToken;

	Token() {
	}

	Token(String lexema, int nLinha) {
		this.setLexema(lexema);
		this.setnLinha(nLinha);
	}

	public int getnLinha() {
		return nLinha;
	}

	private void setnLinha(int nLinha) {
		this.nLinha = nLinha;
	}

	public String getLexema() {
		return lexema;
	}

	private void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public String getNomeToken() {
		return nomeToken;
	}

	public void setNomeToken(String nomeToken) {
		this.nomeToken = nomeToken;
	}
}
