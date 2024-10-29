import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaBarcelona extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Declarar componentes de la ventana
	private JPanel pCentro, pNorte, pSur;
	private JButton btnMensaje;
	private JLabel lblHora, lblMensaje, lblImagen;
	
	private Date fecha; // Para guardar la fecha en el sistema y visualizar la hora en el JLabel
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");
	
	public VentanaBarcelona() {
		// Primera secuencia
		super();
		// Tamaño y posición inicial de la ventana
		setBounds(100, 100, 700, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// Título de la ventana´
		setTitle("VENTANA LAMINE YAMAL");
		// Inicializar paneles
		pNorte = new JPanel();
		pCentro = new JPanel();
		pSur = new JPanel();
		// Inicializar componentes
		btnMensaje = new JButton("GOLDEN BOY !!!");
		btnMensaje.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 26));
		btnMensaje.setBackground(Color.WHITE);
		btnMensaje.setForeground(Color.BLACK);
		
		lblHora = new JLabel();
		lblHora.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		
        lblImagen = new JLabel(new ImageIcon("img/lamine.JPG"));
		
		lblMensaje = new JLabel("Visca el Barça !!!");
		lblMensaje.setFont(new Font(Font.DIALOG, Font.BOLD, 26));
		lblMensaje.setBackground(Color.RED);
		lblMensaje.setForeground(Color.BLUE);
		// Añadir los componentes a la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		pNorte.add(lblHora);
		pCentro.add(lblMensaje);
		pCentro.add(lblImagen);
		pSur.add(btnMensaje);
		
		// Crear un Thread para que el botón cambie de color todo el rato en segundo plano
		Runnable r4 = new Runnable() {

			@Override
			public void run() {
				while(true) {
				btnMensaje.setForeground(Color.RED);
				btnMensaje.setBackground(Color.BLUE);
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				btnMensaje.setForeground(Color.BLUE);
				btnMensaje.setBackground(Color.RED);
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
		
		Thread t4 = new Thread(r4);
		t4.start();
		
		// Crear un Thread que desplace todo el rato la etiqueta lblMensaje
		Runnable r3 = new Runnable() {

			@Override
			public void run() {
				int xInicial = lblMensaje.getX();
				while(true) {
					int x = lblMensaje.getX();
					x = x + 10;
					lblMensaje.setBounds(x, lblMensaje.getY(), lblMensaje.getWidth(), lblMensaje.getHeight());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(x/*+lblMensaje.getWidth()*/ >= pCentro.getWidth()) {
						x = xInicial-lblMensaje.getWidth();
						lblMensaje.setBounds(x, lblMensaje.getY(), lblMensaje.getWidth(), lblMensaje.getHeight());
					}
				}	
			}	
		};
		Thread t3 = new Thread(r3);
		t3.start();
		
		// Vamos a crear la etiqueta que muestra la hora dentro de un hilo
		Runnable r2 = new Runnable() {
					
		@Override
		public void run() {
			while(true) {
				// Primero obtener la fecha del sistema y la vamos a guardar en la variable fecha
				fecha = new Date(System.currentTimeMillis());
				lblHora.setText(sdf.format(fecha)); //Convierte la fecha de tipo Date en un String con el formato HH:mm:ss:SSS			
			}
		}
	};
		Thread t2 = new Thread(r2);
		//t2.start(); 
		
		// Para crear un thread, primero, crear un objeto ejecutableS
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				// Dentro de este método tenemos que escribir el código que queremos que ejecute
				// el thread el segundo plano
				while(true) {
					System.out.println("Soy una ventana");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		// Añadir listeners a los componentes	
		btnMensaje.addActionListener((e)->{
			System.out.println("Para bien o para mal, Lamine Yamal");
		});
		
		// Última secuencia
		setVisible(true);
	}
	
	public static void main(String[] args) {
		VentanaBarcelona vb = new VentanaBarcelona();
		
	}

}
