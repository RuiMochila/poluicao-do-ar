package gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import ceu.Aviao;

import controlador.ControladorJogo;

public class InterfaceGrafica extends JFrame { // o que e que tenho de fazer aqui pqausa desta aviso? nao percebo mt bem D

	protected ControladorJogo controlador;

	public InterfaceGrafica(final ControladorJogo controlador) {
		this.controlador = controlador;
		setTitle("Controlador Aereo");
		setLayout(new BorderLayout());
		getContentPane().add(new ComponenteJogo(), BorderLayout.CENTER);


		JButton start = new JButton("Iniciar");
		getContentPane().add(start, BorderLayout.SOUTH);
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton aux = (JButton)e.getSource();
				if(aux.getText() == "Iniciar"){
					controlador.initAeroportos();
					aux.setText("Fechar");
				}
				else{
					controlador.terminaJogo();
				}
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
	}

	private class ComponenteJogo extends JComponent{
		AviaoGrafico aviaoGrafico;
		AeroportoGrafico aeroportoGrafico;



		public ComponenteJogo() {


			final double dimCelula = controlador.dimCelula; 
			int numColunas = controlador.getNumColunas();
			int numLinhas = controlador.getNumLinhas();
			aviaoGrafico = new AviaoGrafico(controlador);
			aeroportoGrafico = new AeroportoGrafico(controlador);
			setPreferredSize(new Dimension((int)(numColunas*dimCelula), (int)(numLinhas*dimCelula)));



			addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent e) {
					int x = (int)Math.floor(e.getX()/dimCelula);
					int y = (int)Math.floor(e.getY()/dimCelula);

					controlador.click(new Point(x,y));

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
		}
		@Override
		protected void paintComponent(Graphics g) { 
			Graphics2D g2 = (Graphics2D) g; 

			g.setColor(new Color(135, 206, 235));
			g.fillRect(0, 0, getWidth(), getHeight());

			desenhaGrelha(g2);
			aeroportoGrafico.pintaAeroportos(g);
			aviaoGrafico.pintaAvioes(g);

		}
		private void desenhaGrelha(Graphics2D g2) {
			double dimCelula = controlador.dimCelula;
			int numColunas = controlador.getNumColunas();
			int numLinhas = controlador.getNumLinhas();

			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.BLACK);

			// linhas horizontais
			for(int i = 0; i < numLinhas; i++){
				g2.drawLine((int)(0.0), (int)(i*dimCelula), getWidth(), (int)(i*dimCelula));
			}

			// linhas verticais
			for(int i = 0; i < numColunas; i++){
				g2.drawLine((int)(i*dimCelula), (int)(0.0), (int)(i*dimCelula), getHeight());
			}
		}


	}


}
