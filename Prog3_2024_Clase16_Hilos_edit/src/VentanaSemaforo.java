import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaSemaforo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pCentro, pSur, pNorte;
	private JButton btnIniciar, btnPausar, btnDetener;
	private boolean enMarcha;
	private JLabel lblReloj;
	private JLabel etiquetas[];
	private int valoresIniciales [] = {10,3,10};
	private int valoresActuales [] = {10,3,10};
	private int posSem;
	private Thread t;
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	public VentanaSemaforo() {
		super();
		enMarcha = true;
		posSem = 2;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(400, 200, 300, 500);
		pSur = new JPanel();
		pCentro = new JPanel(new GridLayout(3, 1, 0, 5));
		pNorte = new JPanel();
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		btnIniciar = new JButton("Iniciar");
		btnPausar = new JButton("Pausar");
		btnDetener = new JButton("Detener");
		pSur.add(btnIniciar);
		pSur.add(btnPausar);
		pSur.add(btnDetener);
		btnPausar.setEnabled(false);
		btnDetener.setEnabled(false);

		lblReloj = new JLabel("");
		pNorte.add(lblReloj);
		iniciarReloj();
		
		etiquetas = new JLabel[3];
		inicializarEtiquetas();
		
		
		btnIniciar.addActionListener((e) -> {
			iniciar();
		});

		btnPausar.addActionListener((e) -> {
			pausar();
		});

		btnDetener.addActionListener((e) -> {
			detener();
		});

		setVisible(true);
	}
	
	// Método para inicializar reloj
	private void iniciarReloj() {
		Thread t = new Thread(()->{
			while(true) {
				Date fecha = new Date();
				String f = sdf.format(fecha);
				lblReloj.setText(f);
			}
		});
		t.start();
	}
	
	// Método para inicializar etiquetas
	private void inicializarEtiquetas() {
		for(int i=0;i<etiquetas.length;i++) {
			etiquetas[i] = new JLabel();
			etiquetas[i].setOpaque(true);
			etiquetas[i].setBackground(Color.BLACK);
			etiquetas[i].setHorizontalAlignment(JLabel.CENTER);
			etiquetas[i].setFont(new Font(Font.DIALOG, Font.BOLD, 30));
			pCentro.add(etiquetas[i]);
		}
		etiquetas[0].setForeground(Color.RED);
		etiquetas[1].setForeground(Color.YELLOW);
		etiquetas[2].setForeground(Color.GREEN);
	}
	
	// Método para botón "Iniciar"
	private void iniciar() {
		btnIniciar.setEnabled(false);
		btnPausar.setEnabled(true);
		btnDetener.setEnabled(true);
		/*Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});*/
		
		t = new Thread(()->{
			boolean continuar = true;
			while(continuar) {
				while(valoresActuales[posSem]>0) {
					//Convertimos el número (int) que haya en el array a String
					String valor = String.valueOf(valoresActuales[posSem]);
					etiquetas[posSem].setText(valor);
					valoresActuales[posSem]--;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						continuar = false;
					}
					if(continuar==false)
						break;
				}
				if(continuar == false)
					break;
				etiquetas[posSem].setText("");
				posSem--;
				if(posSem < 0) {
					posSem = 2;
					for(int i=0;i<valoresIniciales.length;i++) {
						valoresActuales[i] = valoresIniciales[i];
					}
				}
			}
		});
		t.start();
	}

	// Método para botón "Pausar"
	private void pausar() {
		if(enMarcha) {
			btnPausar.setText("Reanudar");
			enMarcha = false;
			t.interrupt();
		}else {
			btnPausar.setText("Pausar");
			enMarcha = true;
			iniciar();
		}
		
	}
	
	// Método para botón "Detener"
	private void detener() {
		btnDetener.setEnabled(false);
		btnPausar.setEnabled(false);
		btnIniciar.setEnabled(true);
		etiquetas[posSem].setText("");
		t.interrupt();
		posSem = 2;
		for(int i=0;i<valoresIniciales.length;i++) {
			valoresActuales[i] = valoresIniciales[i];
		}
	}
	
	public static void main(String[] args) {
		new VentanaSemaforo();
	}
}
