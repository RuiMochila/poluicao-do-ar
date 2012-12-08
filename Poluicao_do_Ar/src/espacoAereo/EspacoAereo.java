package espacoAereo;

import java.awt.Point;

public class EspacoAereo {

	private int numCelX;
	private int numCelY;
	private Celula [][] matriz;
	
	public EspacoAereo(int numCelX, int numCelY) {
		super();
		this.numCelX = numCelX;
		this.numCelY = numCelY;
		matriz = new Celula[numCelX][numCelY];
		for(int i = 0; i<numCelX; i++){
			for(int j = 0; j<numCelY; j++){
				Point ponto = new Point(i,j);
				matriz[i][j] = new Celula(ponto);
				
			}
		}
	}
	
}
