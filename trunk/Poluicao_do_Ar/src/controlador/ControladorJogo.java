package controlador;


import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

import ceu.Aeroporto;
import ceu.Aviao;
import ceu.EspacoAereo;
import ceu.ObjetoAereo;
import ceu.TipoObjeto;
import gui.InterfaceGrafica;

public class ControladorJogo {
	
	public static final double dimCelula = 30.0;
	private int numColunas;
	private int numLinhas;
	
	private LinkedList<Aeroporto> aeroportos;
	private LinkedList<Aviao> avioes;

	private EspacoAereo espacoAereo;
	private InterfaceGrafica guiGame;
	
	
//	public ControladorJogo(int numColunas, int numLinhas) {
//		super();
//		this.numColunas = numColunas;
//		this.numLinhas = numLinhas;
//
//	}
	
	public void criarJogoPorFicheiro(){
		try {
			Scanner ficheiro = new Scanner(new FileReader("aeroportos.txt"));
			if(ficheiro.hasNext()){
				numColunas = ficheiro.nextInt();
				numLinhas = ficheiro.nextInt();
				espacoAereo = new EspacoAereo(numColunas, numLinhas);
			}
			while(ficheiro.hasNext()){
				int x = ficheiro.nextInt();
				int y = ficheiro.nextInt();
				Aeroporto aeroporto = new Aeroporto(this, espacoAereo, new Point(x,y));
				
			}
			ficheiro.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public void click(Point ponto){
		
		ObjetoAereo objAereo = espacoAereo.getCelula(ponto).getOcupanteCelula();
		
		if(objAereo != null){
			if(objAereo.getTipoObjeto() == TipoObjeto.AVIAO){
				Aviao aviao = (Aviao) objAereo;
				// TODO
			}
		}
		
	}
	
	
	public int getNumColunas() {
		return numColunas;
	}

	public int getNumLinhas() {
		return numLinhas;
	}

	public void initGui(){
		guiGame = new InterfaceGrafica(this);
	}
	
	public void updateGui(){
		guiGame.repaint();
	}
	
	public LinkedList<Aeroporto> getAeroportos() {
		return aeroportos;
	}

	public LinkedList<Aviao> getAvioes() {
		return avioes;
	}
}
