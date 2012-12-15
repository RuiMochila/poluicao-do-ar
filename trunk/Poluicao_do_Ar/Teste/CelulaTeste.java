import java.awt.Point;

import org.junit.Test;
import static org.junit.Assert.*;

import controlador.ControladorJogo;
import ceu.Aeroporto;
import ceu.Celula;
import ceu.EspacoAereo;


public class CelulaTeste {

	
	@Test
	public final void testeGetAndSetAeroporto(){
		Aeroporto aeroporto = new Aeroporto(new ControladorJogo(), new EspacoAereo(10, 10), new Point(5, 5));
		Celula celula = new Celula(new Point(5,5));
		celula.setAeroporto(aeroporto);
		assertEquals(aeroporto, celula.getAeroporto());
	}
}
