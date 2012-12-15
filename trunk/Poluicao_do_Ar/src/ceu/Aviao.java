package ceu;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;

import controlador.ControladorJogo;

public class Aviao extends Thread {

	private static final int CONSUMO_COMBUSTIVEL = 10;
	private static final long SLEEP_TIME = 1000;
	private static final double RESERVA = 0.20;
	private ControladorJogo controlador;
	private EspacoAereo espaco;
	private Point ponto;
	private Point proxPonto;
	private Celula proximaCelula;
	private int rotacao;
	private boolean visivel = false;

	private int combustivelInicial;
	private int combustivelActual;
	private boolean aviaoEmPausa = false;

	private boolean destinoIntermedio = false;
	private boolean chegouDestino = false;
	private Point destinoFinal;
	private LinkedList<Celula> trajecto;

	public Aviao(ControladorJogo controlador, EspacoAereo espaco, Point ponto) {
		super();
		this.controlador = controlador;
		this.espaco = espaco;
		this.ponto = ponto;
		trajecto = new LinkedList<Celula>();    
	}

	@Override
	public void run() {
		try {
			esperaTempoAleatorio();
			tentaDescolar();
			this.visivel = true;

			while(!chegouDestino  && combustivelActual > 0){
				controlador.updateGui();

				if(!aviaoEmPausa ){
					if(trajecto.size() > 0){
						veProximaCelula();
						tentaIrProximaCelula(); 
					}
					else{ //entao Ž porque chegou a um destino
						if(destinoIntermedio){
							destinoIntermedio = false;
							setDestino(destinoFinal);
						}
						else{
							chegouAoDestino();
						}
					}
				}
				if(!chegouDestino){
					combustivelActual -= CONSUMO_COMBUSTIVEL;
					sleep(SLEEP_TIME);
				}
			}
			termina();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setDestino(Point pontoDestino) {
		if(!destinoIntermedio){
			destinoFinal = pontoDestino;
		}
		Point proximaCelulaTrajecto = (Point)ponto.clone(); //tem de ser um clone senao o ponto estava sempre a alterar neste processo de escolher o trajecto
		trajecto.clear(); 
		while(espaco.getCelula(pontoDestino) != espaco.getCelula(proximaCelulaTrajecto)){ //ou ao contrario
			int dx = pontoDestino.x - proximaCelulaTrajecto.x;
			int dy = pontoDestino.y - proximaCelulaTrajecto.y;
			if(Math.abs(dx) > Math.abs(dy)){
				if(dx > 0){
					proximaCelulaTrajecto.x ++;
				}
				else{
					proximaCelulaTrajecto.x --;
				}
			}
			else{
				if(dy > 0){
					proximaCelulaTrajecto.y ++;
				}
				else{
					proximaCelulaTrajecto.y --;
				}
			}
			trajecto.addLast(espaco.getCelula(proximaCelulaTrajecto));
		}
	}

	private void tentaIrProximaCelula() {
		// se conseguir o acesso para a proxima celula, movo me para la 
		if(proximaCelula.obterAcessoCelula(this)){
			Celula celulaAnterior = espaco.getCelula(ponto);
			this.ponto = proximaCelula.getPonto();
			celulaAnterior.sairDaCelula();
		}
		else{
			moveEmCirculos();
		}
	}

	private void moveEmCirculos() {
		rotacao += 90; //vira se para a direita
	}

	private void veProximaCelula() {
		// se estamos na celula onde queremos estar e queremos ir para a proxima
		// ou se ainda nao estamos onde queriamos estar mas a celula para onde queriamos ir esta ocupada e temos um destino intermedio
		// senao tivessemos o destino intermedio passariamos por cima do aviao e iriamos para a celula a seguir
		if(ponto.equals(proxPonto) || (!ponto.equals(proxPonto) && proximaCelula.celulaOcupada() && destinoIntermedio)){
			proximaCelula = trajecto.pollFirst();
			// se for preciso por if para ver se a proximaCelula esta a null
			proxPonto = proximaCelula.getPonto();
			direcaoRotacao();
		}
	}

	private void termina() {
		// ou chegou ao destino ou se despenhou
		if(!chegouDestino){
			// ver pontuacao
		}
		Celula celula = this.espaco.getCelula(ponto);
		celula.sairDaCelula();

		//tenho de remover este aviao e fazer update pq ja nao volta a entrar naquele while
		controlador.getAvioes().remove(this);
		controlador.updateGui();


	}

	private void chegouAoDestino() {
		chegouDestino = true;
		Aeroporto aeroporto = espaco.getCelula(ponto).getAeroporto(); // se for preciso mete se um if para nao dar erro
		aeroporto.aterraAviao();

		//falta tratar da pontuacao

	}

	private void tentaDescolar() throws InterruptedException {
		if(trajecto != null){
			Celula primeiraCelulaTrajecto = trajecto.getFirst();
			while(primeiraCelulaTrajecto.celulaOcupada()){
				sleep(SLEEP_TIME);
			}
		}
	}

	private void esperaTempoAleatorio() throws InterruptedException { // perceber melhor pq e que isto vem para aqui e nao e try/cath -> rever!
		Random r = new Random();
		int tempo = r.nextInt(3000); //ate 3 segundos
		sleep(tempo);
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
	
	public void abastece(int combustivelNecessarioParaViajar) {
		combustivelInicial = (int) (combustivelNecessarioParaViajar * (1 + RESERVA) * CONSUMO_COMBUSTIVEL);
		combustivelActual = combustivelInicial; 
	}
	
	public int obtemCombustivelParaDestino(){ // ve quantas celulas tem de viajar ate o destino
		if(trajecto != null){
			return trajecto.size();
		}
		return 0;
	}

}
