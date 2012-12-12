package ceu;

import java.awt.Point;

public class Aviao extends Thread implements ObjetoAereo{
	
	private static final TipoObjeto tipoObjeto = TipoObjeto.AVIAO;

	@Override
	public TipoObjeto getTipoObjeto() {
		return tipoObjeto;
	}
	
	private Point ponto;

	public Point getPonto() {
		return ponto;
	}

}
