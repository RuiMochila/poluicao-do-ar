package ceu;

import java.awt.Point;
import java.util.concurrent.locks.ReentrantLock;

public class Celula {
	
	private Point ponto;
	private Aviao ocupanteCelula;
	private boolean ocupada;
	private final ReentrantLock lock;
	private Aeroporto aeroporto;

	/**
	 * Cria uma celula nova. Inicializa-a com um Point
	 * @param ponto
	 */
	public Celula(Point ponto) {
		super();
		this.ponto = ponto;
		this.ocupada=false;
		lock = new ReentrantLock();
	}

	/**
	 * M�todo sincronizado que permite a um avi�o ocupar uma celula.
	 * O acesso � celula � gerido recorrendo a um lock. 
	 * Se devolver true significa que bloquiei a celula em minha posse, 
	 * e que sou agora o ocupante. Se devolver false significa que nao consegui
	 * ter acesso e que outro avi�o a detem.
	 * 
	 * @param ocupanteCelula
	 * @return
	 */
	public synchronized boolean obterAcessoCelula(Aviao ocupanteCelula){
		if(lock.tryLock()==true){
			this.ocupanteCelula = ocupanteCelula;
			ocupada = true;
			return true;
		}
		else{
			return false; 
		}
	}
	
	/**
	 * M�todo sincronizado para libertar a celula.
	 * Se o evocador deste m�todo detinha controlo da celula liberta-a,
	 * e a celula deixa de ter um ocupante, podendo ser ocupada por outro aviao.
	 *  
	 */
	public synchronized void sairDaCelula(){
		if(lock.isHeldByCurrentThread()){
			ocupada = false;
			lock.unlock();
			ocupanteCelula = null;
		}
	}
	
	/**
	 * M�todo que devolve true se a celula estiver ocupada, false se estiver livre.
	 * @return
	 */
	public boolean celulaOcupada(){
		return ocupada;	
	}
	
	/**
	 * Imprime uma celula no formato:
	 * "x= " + ponto.x + "y= " + ponto.y
	 */
	@Override
	public String toString() {
		return "x= " + ponto.x + "y= " + ponto.y;
	}
	
	/**
	 * Devolve o aeroporto se existir um nesta celula, caso contr�rio devolve null
	 * @return
	 */
	public Aeroporto getAeroporto() { 
		return aeroporto;
	}

	/**
	 * Atribui um aeroporto a esta celula
	 * @param aeroporto
	 */
	public void setAeroporto(Aeroporto aeroporto) {
		this.aeroporto = aeroporto;
	}
	
	/**
	 * Devolve o aviao ocupante desta celula se existir algum, caso contr�rio devolve null
	 * @return
	 */
	public Aviao getOcupanteCelula() {
		return ocupanteCelula;
	}
	
	/**
	 * Devolve o Point onde esta celula se encontra
	 * @return
	 */
	public Point getPonto() {
		return ponto;
	}

}
