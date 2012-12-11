package gui;

import java.util.LinkedList;

import controlador.ControladorJogo;

import ceu.Aviao;

public class InterfaceAviao {
	
	private LinkedList<Aviao> avioes;
	
	public InterfaceAviao(ControladorJogo controlador) {
		avioes = controlador.getAvioes();
	}

}
