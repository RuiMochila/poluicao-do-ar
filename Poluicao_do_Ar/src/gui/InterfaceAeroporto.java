package gui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import controlador.ControladorJogo;

import ceu.Aeroporto;

public class InterfaceAeroporto {

	private LinkedList<Aeroporto> aeroportos;

	public InterfaceAeroporto(ControladorJogo controlador) {
		this.aeroportos = controlador.getAeroportos();
	}
	
	private void paintAll(Graphics g) {
		// TODO Auto-generated method stub

	}

	

}
