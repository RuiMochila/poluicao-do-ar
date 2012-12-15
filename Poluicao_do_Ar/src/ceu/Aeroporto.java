package ceu;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import controlador.ControladorJogo;

public class Aeroporto extends Thread{
	
	private ControladorJogo controlador;
	private EspacoAereo espaco;
	private Point pontoAeroporto;
	private AtomicInteger avioesPorLancar; 

	public Aeroporto(ControladorJogo controlador, EspacoAereo espaco,
			Point ponto) {
		super();
		this.controlador = controlador;
		this.espaco = espaco;
		this.pontoAeroporto = ponto;
		
		avioesPorLancar = new AtomicInteger(1);
		Random r = new Random(); // tem de ser lancado sempre pelo menos um aviao
		int numAvioes = r.nextInt(5); // 0 a 4
		avioesPorLancar.addAndGet(numAvioes);
	}
	
	public Point getPonto() {
		return pontoAeroporto;
	}

	@Override
	public void run() {
		while(true){ // !interrupted() 
			if(avioesPorLancar.get() > 0){
				lancaAviao();
				avioesPorLancar.decrementAndGet();	
			}
			
		}
		
		
	}

	private void lancaAviao() { // cria 1aviao e lanca o
		//criar aviao e dar destino e gasolina
		// meter na lista e inicia lo
		
		//tenho de criar uma copia da lista dos avioes e remover me de la para criar outro como destino para o aviao que eu vou lancar
		//a copia e feita pq nao se quer estragar a original
		
		LinkedList<Aeroporto> copiaAeroportos= new LinkedList<Aeroporto>(controlador.getAeroportos());
		copiaAeroportos.remove(this);
		if(copiaAeroportos.size() > 0){ 
			Random r = new Random();
			int i = r.nextInt(copiaAeroportos.size()); 
			Point pontoDestino = copiaAeroportos.get(i).getPonto(); //ponto do aeroporto escolhido aleatoriamente da copia da lista

			Aviao aviao = new Aviao(controlador, espaco, new Point(pontoAeroporto));
			aviao.setDestino(pontoDestino);
			//abastece e saltos ate destino
			controlador.getAvioes().add(aviao);
			aviao.start();
		
		}
		
		
	}

	public void aterraAviao() {
		this.avioesPorLancar.incrementAndGet(); // se um aviao aterra volta a ser lancado e tem combustivel novamente
		
	}


}
