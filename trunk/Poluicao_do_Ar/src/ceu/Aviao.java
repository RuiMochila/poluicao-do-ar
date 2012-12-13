package ceu;

import java.awt.Point;

import controlador.ControladorJogo;

public class Aviao extends Thread implements ObjetoAereo{
	
	private static final TipoObjeto tipoObjeto = TipoObjeto.AVIAO;

	@Override
	public TipoObjeto getTipoObjeto() {
		return tipoObjeto;
	}
	private ControladorJogo controlador;
	private EspacoAereo espaco;
	private Point ponto;
	private Point proxPonto;
	private int rotacao;
	private boolean visivel = true;

	public Aviao(ControladorJogo controlador, EspacoAereo espaco, Point ponto) {
		super();
		this.controlador = controlador;
		this.espaco = espaco;
		this.ponto = ponto;
	}
	
	@Override
	public void run() {
		for(int i=0; i<7; i++){	
			controlador.updateGui();

			ponto.y--;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}

	public Point getPonto() {
		return ponto;
	}
	
	public boolean estaVisivel(){
		return visivel;
	}
	
	public void direcaoRotacao(){
		if(proxPonto.x > ponto.x){
			rotacao = 90;
		}
		if(ponto.x > proxPonto.x){
			rotacao = 270;
		}
		if(proxPonto.y > ponto.y){
			rotacao = 180;
		}
		if(ponto.y > proxPonto.y){
			rotacao = 0;
		}
	}

	public int getRotacao() {
		return rotacao;
	}
	
	

}
