package perceptron;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Canvas;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class perceptronWindow extends JFrame {

	private JPanel contentPane;
	main cerebro;
	private JTextField quantity;
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					perceptronWindow frame = new perceptronWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
	

	/**
	 * Create the frame.
	 */
	public perceptronWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton autoBtn = new JButton("AUTOMATICO");
		autoBtn.setBounds(259, 11, 115, 23);
		contentPane.add(autoBtn);
		
		quantity = new JTextField();
		quantity.setToolTipText("Cantidad de puntos");
		quantity.setBounds(10, 12, 86, 20);
		contentPane.add(quantity);
		quantity.setColumns(10);
		
		Random rand= new Random();
		double x = rand.nextDouble();
		double y = rand.nextDouble();
		
		JButton manualBtn = new JButton("HACER CICLO");
		manualBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		manualBtn.setBounds(134, 11, 115, 23);
		contentPane.add(manualBtn);
		
		
	}
	
}
