package ceu;

import java.awt.Point;

import controlador.ControladorJogo;

public class Aeroporto extends Thread implements ObjetoAereo{
	
	private static final TipoObjeto tipoObjeto = TipoObjeto.AEROPORTO;
	
	private ControladorJogo controlador;
	private EspacoAereo espaco;
	private Point ponto;

	public Aeroporto(ControladorJogo controlador, EspacoAereo espaco,
			Point ponto) {
		super();
		this.controlador = controlador;
		this.espaco = espaco;
		this.ponto = ponto;
	}
	
	public Point getPonto() {
		return ponto;
	}

	@Override
	public void run() {
		
			
			
		Aviao a = new Aviao(controlador, espaco, new Point(ponto));
		controlador.getAvioes().add(a);

	}

	@Override
	public TipoObjeto getTipoObjeto() {
		return tipoObjeto;
	}

}
