package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

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
	}
	
	

}
