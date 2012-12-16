package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import controlador.ControladorJogo;
import ceu.Aviao;

public class AviaoGrafico {

	private ControladorJogo controlador;
	private ConcurrentLinkedQueue<Aviao> avioes;
	private BufferedImage bandeira;
	private BufferedImage aviaoPreto;
	private BufferedImage aviaoVermelho;

	public AviaoGrafico(ControladorJogo controlador) {
		avioes = controlador.getAvioes();
		this.controlador = controlador;
		carregaImagens();
	}

	private void carregaImagens() {
		//Cerregamento com base em getResource() para funcionar dentro do jar.
		
		//carrega imagem aviao preto
		ImageIcon iconAviao;
		iconAviao = new ImageIcon(getClass().getResource("/aviao.png"));
		aviaoPreto = new BufferedImage(iconAviao.getIconWidth(),
				iconAviao.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		aviaoPreto.getGraphics().drawImage(iconAviao.getImage(), 0, 0, null);
		
		// carrega imagem aviao vermelho
		ImageIcon iconAviao2;
		iconAviao2 = new ImageIcon(getClass().getResource("/aviao_vermelho.png"));
		aviaoVermelho = new BufferedImage(iconAviao2.getIconWidth(),
				iconAviao2.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		aviaoVermelho.getGraphics().drawImage(iconAviao2.getImage(), 0, 0, null);

		//carrega imagem flag
		ImageIcon iconFlag;
		iconFlag = new ImageIcon(getClass().getResource("/flag.png"));
		bandeira = new BufferedImage(iconFlag.getIconWidth(), iconFlag.getIconHeight(),
				BufferedImage.TYPE_INT_ARGB);
		bandeira.getGraphics().drawImage(iconFlag.getImage(), 0, 0, null);

	}
	
	private void pinta(Graphics g, Aviao aviao) {
		Graphics2D g2 = (Graphics2D) g;

		desenhaImagemAviao(g2, aviao);
		
		if(aviao.estaEsperaDestinoIntermedio()){
			desenhaBandeira(g2, aviao);
		}

	}

	private void desenhaBandeira(Graphics2D g2, Aviao aviao) {
		double dimCelula = controlador.dimCelula;
//		BufferedImage bandeira;
//		try {
//			bandeira = ImageIO.read(new File("imagens/flag.png"));
			g2.drawImage(bandeira, (int)(aviao.getPontoIntermedio().x * dimCelula + 1),(int)( aviao.getPontoIntermedio().y * dimCelula +1), (int)(dimCelula -1), (int)(dimCelula -1), null);
			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	private void desenhaImagemAviao(Graphics2D g2, Aviao aviao) {

//		try {
			Point ponto = aviao.getPonto();
			double dimCelula = ControladorJogo.dimCelula; 
			BufferedImage imagem;
			if(aviao.getCombustivelActual() < aviao.getCombustivelInicial()*aviao.RESERVA){
//				imagem = ImageIO.read(new File("imagens/aviao_vermelho.png"));
				imagem = aviaoVermelho;
			}
			else{
//				imagem = ImageIO.read(new File("imagens/aviao.png"));
				imagem = aviaoPreto;
			}
			
			
			double centroImagemX = imagem.getWidth() / 2; 
			double centroImagemY = imagem.getHeight() / 2;
			AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(aviao.getRotacao()), centroImagemX, centroImagemY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			
			g2.drawImage(op.filter(imagem, null), (int)(ponto.x * dimCelula)+2, (int)(ponto.y*dimCelula)+2, null);
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}

	}

	public void pintaAvioes(Graphics g) {
		for(Aviao aviao: avioes){
			if(aviao.estaVisivel()){
				pinta(g, aviao);	
			}
		}
	}

}
