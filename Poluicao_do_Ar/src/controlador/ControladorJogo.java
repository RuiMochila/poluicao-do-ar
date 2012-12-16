package controlador;


import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import ceu.Aeroporto;
import ceu.Aviao;
import ceu.EspacoAereo;
import gui.InterfaceGrafica;

public class ControladorJogo {

	public static final double dimCelula = 30.0;
	private int numColunas;
	private int numLinhas;

	private ConcurrentLinkedQueue<Aeroporto> aeroportos;
	private ConcurrentLinkedQueue<Aviao> avioes;

	private EspacoAereo espacoAereo;
	private InterfaceGrafica guiGame;


	public void criarJogoPorFicheiro(){
		try {
			Scanner ficheiro = new Scanner(new FileReader("aeroportos.txt"));
			if(ficheiro.hasNext()){
				numColunas = ficheiro.nextInt();
				numLinhas = ficheiro.nextInt();
				espacoAereo = new EspacoAereo(numColunas, numLinhas);
			}

			aeroportos = new ConcurrentLinkedQueue<Aeroporto>();
			avioes = new ConcurrentLinkedQueue<Aviao>();

			while(ficheiro.hasNext()){
				int x = ficheiro.nextInt();
				int y = ficheiro.nextInt();
				Point ponto = new Point(x,y);
				Aeroporto aeroporto = new Aeroporto(this, espacoAereo, ponto);
				espacoAereo.getCelula(ponto).setAeroporto(aeroporto);
				aeroportos.add(aeroporto);

			}
			ficheiro.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public void click(Point ponto){

		Aviao aviao = espacoAereo.getCelula(ponto).getOcupanteCelula();

		if(aviao != null){

			// TODO
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

	public void initAeroportos() {
		for(Aeroporto aeroporto: aeroportos){
			aeroporto.start();
		}
	}

	public void updateGui(){
		guiGame.repaint();
	}

	public ConcurrentLinkedQueue<Aeroporto> getAeroportos() {
		return aeroportos;
	}

	public ConcurrentLinkedQueue<Aviao> getAvioes() {
		return avioes;
	}
	
	public void terminaJogo(){
		for(Aeroporto aeroporto: aeroportos){
			aeroporto.interrupt();
		}
		for(Aviao aviao: avioes){
			aviao.interrupt();
		}	
	}
}
