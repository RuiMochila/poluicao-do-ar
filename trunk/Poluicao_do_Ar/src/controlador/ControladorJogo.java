package controlador;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import ceu.EspacoAereo;
import gui.InterfaceJogo;

public class ControladorJogo {
	
	private static final double DIM_CELULAS = 3.0;
	private int numColunas;
	private int numLinhas;
	
	private EspacoAereo espacoAereo;
	private InterfaceJogo guiGame;
	
	public ControladorJogo(int numColunas, int numLinhas) {
		super();
		this.numColunas = numColunas;
		this.numLinhas = numLinhas;

	}
	
	public void criarJogoPorFicheiro(){
		try {
			Scanner ficheiro = new Scanner(new FileReader("aeroportos.txt"));
			if(ficheiro.hasNext()){
				numColunas = ficheiro.nextInt();
				numLinhas = ficheiro.nextInt();
				espacoAereo = new EspacoAereo(numColunas, numLinhas);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initGui(){
		guiGame = new InterfaceJogo(this);
	}
	
	public void updateGui(){
		guiGame.repaint();
	}
	
}
