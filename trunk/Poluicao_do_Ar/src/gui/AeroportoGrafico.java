package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import controlador.ControladorJogo;

import ceu.Aeroporto;

public class AeroportoGrafico {
	
	private ControladorJogo controlador;
	private LinkedList<Aeroporto> aeroportos;

	public AeroportoGrafico(ControladorJogo controlador) {
		this.aeroportos = controlador.getAeroportos();
	}
	
	private void pinta(Graphics g, Aeroporto aeroporto) {
		Point ponto = aeroporto.getPonto();
		g.setColor(Color.GRAY);
		g.fillRect((int)(ponto.x*controlador.dimCelula), (int)(ponto.y*controlador.dimCelula), (int)(controlador.dimCelula), (int)(controlador.dimCelula));

	}
	
	public void pintaAeroportos(Graphics g) {
		for(Aeroporto aeroporto: aeroportos){
			pinta(g, aeroporto);
		}

	}
	
	

	

}
