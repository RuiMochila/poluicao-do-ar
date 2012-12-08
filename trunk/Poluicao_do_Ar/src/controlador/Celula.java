package controlador;

import java.awt.Point;
import java.util.concurrent.locks.ReentrantLock;

public class Celula {
	
	private Point ponto;
	private boolean ocupada;
	private final ReentrantLock lock = new ReentrantLock();
	

	public boolean obterAcessoCelula(){
		if(lock.tryLock()==true){
			//moverAviao();
			ocupada = true;
			return true;
		}
		else{
			//rodarAviao();
			return false; //o aviao sabe que esta la outro aviao e comeca a rodar
		}
	}
	
	@Override
	public String toString() {
		return "x= " + ponto.x + "y= " + ponto.y;
	}
}
