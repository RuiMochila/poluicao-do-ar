package ceu;

import java.awt.Point;

public class Aviao extends Thread implements ObjetoAereo{
	
	private static final TipoObjeto tipoObjeto = TipoObjeto.AVIAO;

	@Override
	public TipoObjeto getTipoObjeto() {
		return tipoObjeto;
	}
	
	private Point ponto;
	private boolean visivel = false;

	public Point getPonto() {
		return ponto;
	}
	
	public boolean estaVisivel(){
		return visivel;
	}

}
