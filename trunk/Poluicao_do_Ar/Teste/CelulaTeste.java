import java.awt.Point;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import controlador.ControladorJogo;
import ceu.Aeroporto;
import ceu.Aviao;
import ceu.Celula;
import ceu.EspacoAereo;


public class CelulaTeste {

	private static Aviao aviao;
	private static Aeroporto aeroporto;
	private static Point ponto;
	private static final int x=5;
	private static final int y=5;
	private static ControladorJogo controlador;
	private static EspacoAereo espaco;
	private static final int dimX = 10;
	private static final int dimY = 10;
	
	/**
	 * Executado antes de todos os testes
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		controlador = new ControladorJogo();
		espaco = new EspacoAereo(dimX, dimY);
		ponto = new Point(x, y);
		aeroporto = new Aeroporto(controlador, espaco, new Point(ponto));
		aviao = new Aviao(controlador, espaco, new Point(ponto));
	}
	
	/**
	 * Executado depois de todos os testes
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}
	
	/**
	 * Executado antes de cada teste
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		
	}
	
	/**
	 * Executado depois de cada teste
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}
	
	/**
	 * Testa o construtor
	 */
	@Test
	public final void testeCelula(){
		Celula celula = null;
		assertNull("Não está nula",celula);
		celula = new Celula(ponto);
		assertNotNull("Está nula", celula);
	}
	
	/**
	 * Testa o set e get aeroporto
	 */
	@Test
	public final void testeGetAndSetAeroporto(){
		Celula celula = new Celula(new Point(x,y));
		celula.setAeroporto(aeroporto);
		assertEquals("Disse que era diferente",aeroporto, celula.getAeroporto());
	}
	
	/**
	 * Testa o set e get ocupante avião
	 */
	@Test
	public final void testeGetAndSetOcupante(){
		Celula celula = new Celula(new Point(ponto));
		celula.obterAcessoCelula(aviao);
		assertTrue("Disse que não tinha acesso da célula", celula.celulaOcupada());
		assertEquals("Disse que era diferente", aviao, celula.getOcupanteCelula());
		celula.sairDaCelula();
		assertFalse("Disse que a celula estava ocupada", celula.celulaOcupada());
	}
	
	/**
	 * Testa o ponto da celula
	 */
	@Test
	public final void testeGetPonto(){
		Celula celula = new Celula(ponto);
		assertEquals("Disse que o ponto era diferente", ponto, celula.getPonto());
	}
	
	/**
	 * Testa o toString
	 */
	@Test
	public final void testeToString(){
		Celula celula = new Celula(ponto);
		String expected = "x= " + ponto.x + "y= " + ponto.y;
		assertEquals("Disse que o toString está mal", expected, celula.toString());
	}
}
