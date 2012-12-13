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

import controlador.ControladorJogo;
import ceu.Aviao;

public class AviaoGrafico {

	private ControladorJogo controlador;
	private ConcurrentLinkedQueue<Aviao> avioes;

	public AviaoGrafico(ControladorJogo controlador) {
		avioes = controlador.getAvioes();
		this.controlador = controlador;
	}

	private void pinta(Graphics g, Aviao aviao) {
		Graphics2D g2 = (Graphics2D) g;

		desenhaImagemAviao(g2, aviao);

		//continuar metodo

	}

	private void desenhaImagemAviao(Graphics2D g2, Aviao aviao) {

		try {
			Point ponto = aviao.getPonto();
			double dimCelula = ControladorJogo.dimCelula; 
			BufferedImage imagem;
			imagem = ImageIO.read(new File("imagens/aviao.png"));
			
			double centroImagemX = imagem.getWidth() / 2; 
			double centroImagemY = imagem.getHeight() / 2;
			AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(aviao.getRotacao()), centroImagemX, centroImagemY);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			
			g2.drawImage(op.filter(imagem, null), (int)(ponto.x * dimCelula), (int)(ponto.y*dimCelula), null);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void pintaAvioes(Graphics g) {
		for(Aviao aviao: avioes){
			if(aviao.estaVisivel()){
				pinta(g, aviao);	
			}
		}
	}

}
