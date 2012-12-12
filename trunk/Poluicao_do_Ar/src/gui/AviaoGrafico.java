package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import controlador.ControladorJogo;

import ceu.Aeroporto;
import ceu.Aviao;

public class AviaoGrafico {
	
	private ControladorJogo controlador;
	private LinkedList<Aviao> avioes;
	
	public AviaoGrafico(ControladorJogo controlador) {
		this.avioes = controlador.getAvioes();
	}
	
	//refazer estes dois metodos
	
	private void pinta(Graphics g, Aviao aviao) {
		Point ponto = aviao.getPonto();
		g.setColor(Color.GRAY);
		g.fillRect((int)(ponto.x*controlador.dimCelula), (int)(ponto.y*controlador.dimCelula), (int)(controlador.dimCelula), (int)(controlador.dimCelula));

	}
	
	public void pintaAvioes(Graphics g) {
		for(Aviao aviao: avioes){
			pinta(g, aviao);
		}

	}

}
