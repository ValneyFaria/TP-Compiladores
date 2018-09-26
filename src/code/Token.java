package code;

public class Token {
	int nLinha;
	int nColuna;
	String lexema;
	String nomeToken;

	Token() {
	}

	Token(String nomeToken, int nLinha, int nColuna) {
		this.setNomeToken(nomeToken);
		this.setnLinha(nLinha);
		this.setnColuna(nColuna);
	}

	public int getnLinha() {
		return nLinha;
	}

	public void setnLinha(int nLinha) {
		this.nLinha = nLinha;
	}

	public int getnColuna() {
		return nColuna;
	}

	public void setnColuna(int nColuna) {
		this.nColuna = nColuna;
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
