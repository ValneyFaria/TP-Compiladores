package code;

// Classe que representa um Token
public class Token {
	int nLinha;
	String lexema;
	String nomeToken;

	Token() {
	}

	Token(String lexema, int nLinha) {
		this.setLexema(lexema);
		this.setnLinha(nLinha);
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

	public String getNomeToken() {
		return nomeToken;
	}

	public void setNomeToken(String nomeToken) {
		this.nomeToken = nomeToken;
	}
}
