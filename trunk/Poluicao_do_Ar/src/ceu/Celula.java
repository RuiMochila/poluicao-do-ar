package ceu;

import java.awt.Point;
import java.util.concurrent.locks.ReentrantLock;

public class Celula {
	
	private Point ponto;
	private Aviao ocupanteCelula;
	private boolean ocupada;
	private final ReentrantLock lock;
	private Aeroporto aeroporto;

	public Celula(Point ponto) {
		super();
		this.ponto = ponto;
		this.ocupada=false;
		lock = new ReentrantLock();
	}


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
	 * 
	 */
	public synchronized void sairDaCelula(){
		if(lock.isHeldByCurrentThread()){
			ocupada = false;
			lock.unlock();
			ocupanteCelula = null;
		}
	}
	
	public boolean celulaOcupada(){
		return ocupada;	
	}
	
	@Override
	public String toString() {
		return "x= " + ponto.x + "y= " + ponto.y;
	}
	

	public Aeroporto getAeroporto() { 
		return aeroporto;
	}


	public void setAeroporto(Aeroporto aeroporto) {
		this.aeroporto = aeroporto;
	}
	
	public Aviao getOcupanteCelula() {
		return ocupanteCelula;
	}
	
	public Point getPonto() {
		return ponto;
	}

}
