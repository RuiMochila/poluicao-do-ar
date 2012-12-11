package ceu;

import java.awt.Point;

public class EspacoAereo {

	private Celula [][] matriz;
	
	public EspacoAereo(int numCelX, int numCelY) {
		matriz = new Celula[numCelX][numCelY];
		for(int i = 0; i<numCelX; i++){
			for(int j = 0; j<numCelY; j++){
				Point ponto = new Point(i,j);
				matriz[i][j] = new Celula(ponto);
				
			}
		}
	}
	
	public Celula getCelula(Point ponto){
		int x = ponto.x;
		int y = ponto.y;
		return matriz[x][y];
	}
	
}
