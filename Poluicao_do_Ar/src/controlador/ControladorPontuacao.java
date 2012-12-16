package controlador;

public class ControladorPontuacao {
	
	private int pontos = 0;
	
	public synchronized void adicionaPontos(int pontos) { 
		this.pontos += pontos;
	}
	
	public synchronized void retiraPontos(int pontos) { 
		this.pontos -= pontos;
	}

	public int getPontos() {
		return pontos;
	}
}
