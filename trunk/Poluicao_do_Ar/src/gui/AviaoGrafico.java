package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import controlador.ControladorJogo;

import ceu.Aeroporto;
import ceu.Aviao;

public class AviaoGrafico {

	private ControladorJogo controlador;
	private LinkedList<Aviao> avioes;

	public AviaoGrafico(ControladorJogo controlador) {
		this.avioes = controlador.getAvioes();
	}

	private void pinta(Graphics g, Aviao aviao) {
		Graphics2D g2 = (Graphics2D) g;

		desenhaImagemAviao(g2, aviao);

		//continuar metodo

	}

	private void desenhaImagemAviao(Graphics2D g2, Aviao aviao) {

		try {
			Point ponto = aviao.getPonto();
			double dimCelula = controlador.dimCelula;
			BufferedImage imagem;
			imagem = ImageIO.read(new File("imagens/aviao.png"));
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
