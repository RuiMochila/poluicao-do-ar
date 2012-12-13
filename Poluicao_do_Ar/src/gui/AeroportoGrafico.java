package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import controlador.ControladorJogo;

import ceu.Aeroporto;

public class AeroportoGrafico {
	
	private ControladorJogo controlador;
	private ConcurrentLinkedQueue<Aeroporto> aeroportos;

	public AeroportoGrafico(ControladorJogo controlador) {
		this.aeroportos = controlador.getAeroportos();
	}
	
	private void pinta(Graphics g, Aeroporto aeroporto) {
		Point ponto = aeroporto.getPonto();
		g.setColor(new Color(255, 20, 147));
		g.fillRect((int)(ponto.x*controlador.dimCelula)+1, (int)(ponto.y*controlador.dimCelula)+1, (int)(controlador.dimCelula)-1, (int)(controlador.dimCelula)-1);

	}
	
	public void pintaAeroportos(Graphics g) {
		for(Aeroporto aeroporto: aeroportos){
			pinta(g, aeroporto);
		}

	}
	
	

	

}
