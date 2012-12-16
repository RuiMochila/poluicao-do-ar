package controlador;


import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
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

	private Aviao aviaoParado;

	public void criarJogoPorFicheiro(){
		try {
			Scanner ficheiro = new Scanner(new FileReader("aeroportos.txt"));
			if(ficheiro.hasNext()){
				this.numColunas = ficheiro.nextInt();
				this.numLinhas = ficheiro.nextInt();
				this.espacoAereo = new EspacoAereo(numColunas, numLinhas);
			}

			aeroportos = new ConcurrentLinkedQueue<Aeroporto>();
			avioes = new ConcurrentLinkedQueue<Aviao>();

			while(ficheiro.hasNext()){
				int x = ficheiro.nextInt();
				int y = ficheiro.nextInt();
				Point novoPonto = new Point(x,y);
				Aeroporto aeroporto = new Aeroporto(this, espacoAereo, novoPonto);
				espacoAereo.getCelula(novoPonto).setAeroporto(aeroporto);
				aeroportos.add(aeroporto);

			}
			ficheiro.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		initGui();
	}


	public void criarJogoAleatorio(int numColunas, int numLinhas, int numAeroportos){
		//asserts e erros - tratar disso
		
		this.numColunas = numColunas;
		this.numLinhas = numLinhas;
		this.espacoAereo = new EspacoAereo(numColunas, numLinhas);
		
		aeroportos = new ConcurrentLinkedQueue<Aeroporto>();
		avioes = new ConcurrentLinkedQueue<Aviao>();

		for(int i = 0; i < numAeroportos; i++){
			Random r = new Random();
			int x = 0;
			int y = 0;
			boolean celulaOcupada = true;
			boolean celulasVizinhasOcupadas = true;//ver se pode ser inicializado aqui
			
			while(celulaOcupada || celulasVizinhasOcupadas){ // se A ou B acontecer eu quero ver de outro ponto
				x = r.nextInt(numColunas);
				y = r.nextInt(numLinhas); //no maximo ate o num Linhas
				for(Aeroporto aeroporto: aeroportos){
					if(!(aeroporto.getPonto().x == x && aeroporto.getPonto().y == y)){
						celulaOcupada = false;
					}
					
					ConcurrentLinkedQueue<Aeroporto> copiaAeroportos = new ConcurrentLinkedQueue<Aeroporto>(aeroportos); 
					for(Aeroporto aeroporto2: copiaAeroportos){
						if(!(aeroporto2.getPonto().x == x - 1 ||
								aeroporto2.getPonto().x == x + 1 ||
								aeroporto2.getPonto().x == y - 1 ||
								aeroporto2.getPonto().x == y + 1)){
							celulasVizinhasOcupadas = false;
						}
					}
				}	
			}
			Point novoPonto = new Point(x,y);
			Aeroporto aeroporto = new Aeroporto(this, espacoAereo, novoPonto);
			espacoAereo.getCelula(novoPonto).setAeroporto(aeroporto);
			aeroportos.add(aeroporto);	
		}
		initGui();
	}

	public void criarJogoTeste(){
		try {
			Scanner ficheiro = new Scanner(new FileReader("situacaoTeste.txt"));
			if(ficheiro.hasNext()){
				this.numColunas = ficheiro.nextInt();
				this.numLinhas = ficheiro.nextInt();
				this.espacoAereo = new EspacoAereo(numColunas, numLinhas);
			}
			aeroportos = new ConcurrentLinkedQueue<Aeroporto>();
			avioes = new ConcurrentLinkedQueue<Aviao>();
			
			while(ficheiro.hasNext()){
				int x = ficheiro.nextInt();
				int y = ficheiro.nextInt();
				Point novoPonto = new Point(x,y);
				Aeroporto aeroporto = new Aeroporto(this, espacoAereo, novoPonto);
				espacoAereo.getCelula(novoPonto).setAeroporto(aeroporto);
				aeroportos.add(aeroporto);
			}
			ficheiro.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		initGui();
	}
	
	public void click(Point ponto){

		Aviao aviao = espacoAereo.getCelula(ponto).getOcupanteCelula();

		if(aviao != null){ // se esta um aviao no ponto clicado
			if(aviao.estaEsperaComando()){ // no caso em que foi clicado no aviao errado
				aviao.acabaPausa();
				aviaoParado = null;
			}
			else{
				if(aviaoParado != null){ // tirar da pausa o aviao que estava (se houver)
					aviaoParado.acabaPausa();
					aviaoParado = null; //?
				}
				aviao.comecaPausa();
				aviaoParado = aviao;
			}
		}
		else{ // se clica num ponto vazio
			if(aviaoParado != null){ // e se ha algum aviao a espera
				aviaoParado.setDestinoIntermedio(ponto);
				aviaoParado.acabaPausa(); // depois de ter dito o destino intermedio deixa de estar parado e passa a ser null
				aviaoParado = null;
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
