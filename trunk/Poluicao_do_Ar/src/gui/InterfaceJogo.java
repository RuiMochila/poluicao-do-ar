package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import ceu.Aviao;

import controlador.ControladorJogo;

public class InterfaceJogo extends JFrame {
	
	protected ControladorJogo controlador;
	
	public InterfaceJogo(ControladorJogo controlador) {
		setTitle("Controlador Aereo");
		setLayout(new BorderLayout());
		
		JButton start = new JButton("Iniciar");
		getContentPane().add(start, BorderLayout.SOUTH);
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton aux = (JButton)e.getSource();
				if(aux.getText() == "Iniciar"){
					//iniciar o jogo
					aux.setText("Fechar");
				}
				else{
					//fechar o jogo
					aux.setText("Iniciar");
					
				}
				
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	private class ComponenteJogo extends JComponent{
		InterfaceAviao aviaoGrafico;
		InterfaceAeroporto aeroportoGrafico;
		
	
		
		public ComponenteJogo() {
			
			
			final double dimCelula = controlador.dimCelula; 
			int numColunas = controlador.getNumColunas();
			int numLinhas = controlador.getNumLinhas();
			aviaoGrafico = new InterfaceAviao(controlador);
			aeroportoGrafico = new InterfaceAeroporto(controlador);
			setPreferredSize(new Dimension((int)(numColunas*dimCelula), (int)(numLinhas*dimCelula)));
			
			
			
			addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					int x = (int)Math.floor(e.getX()/dimCelula);
					int y = (int)Math.floor(e.getX()/dimCelula);
					
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
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g; 
			
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			desenhaGrelha(g2);
			
			//aeroportoGrafico.paint??
			
			
		
		}
		private void desenhaGrelha(Graphics2D g2) {
			
			
		}
		
		
	}
	

}
