package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle.Control;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.ControladorJogo;

public class InterfaceInicial extends JFrame{
	
	
	public InterfaceInicial(){
		setTitle("Controlador Aereo");

		setLayout(new BorderLayout());
		
		JButton botaoTeste = new JButton("Situação de teste");
		botaoTeste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ControladorJogo controlador = new ControladorJogo(); //nao pode estar definido la em cima?
				controlador.criarJogoTeste();
			}
		});
		
		JButton botaoAleatorio = new JButton("Jogo aleatorio");
		botaoAleatorio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt(JOptionPane.showInputDialog("Inserir numero de colunas")); //pq parseInt?
				int y = Integer.parseInt(JOptionPane.showInputDialog("Inserir numero de linhas"));
				int aeroportos = Integer.parseInt(JOptionPane.showInputDialog("Inserir numero de aeroportos"));
				
				ControladorJogo controlador = new ControladorJogo();
				controlador.criarJogoAleatorio(x, y, aeroportos);
				
			}
		});
		
		
		JButton botaoFicheiro = new JButton("Jogo por ficheiro");
			botaoFicheiro.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ControladorJogo controlador = new ControladorJogo();
					controlador.criarJogoPorFicheiro();
				}
			});
		
		JPanel painelBotoes = new JPanel();
		painelBotoes.add(botaoTeste);
		painelBotoes.add(botaoAleatorio);
		painelBotoes.add(botaoFicheiro);
		getContentPane().add(painelBotoes, BorderLayout.NORTH);

		JPanel painelAluno = new JPanel(new GridLayout(3,1));
		JLabel nome = new JLabel("Débora Gonçalves");
		JLabel numero = new JLabel("33957");
		JLabel curso = new JLabel("Informática e Gestão de Empresas");
		getContentPane().add(painelAluno, BorderLayout.CENTER);
		
		getContentPane().add(nome);
		getContentPane().add(numero);
		getContentPane().add(curso);
		
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setResizable(false);
		setVisible(true);
		
		
		
	}

}
