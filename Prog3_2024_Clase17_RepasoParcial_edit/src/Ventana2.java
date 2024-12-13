import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/*TABLA CON MODELO CREADO POR NOSOTROS*/
public class Ventana2 extends JFrame{
	private ModeloTabla modeloTabla;
	private JTable tabla;
	private JScrollPane scrollTabla;
	private JCheckBox b;
	private JRadioButton br;
	
	public Ventana2() {
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300, 200, 600, 400);
		Contenedora.cargarLista();
		
		b = new JCheckBox("Selecciona: ");
		getContentPane().add(b,BorderLayout.NORTH);
		br = new JRadioButton("Selecciona: ");
		getContentPane().add(br,BorderLayout.SOUTH);
		
		b.addActionListener((e)->{
			System.out.println("Has seleccionado el JCheckBox");
		});
		
		br.addActionListener((e)->{
			System.out.println("Has seleccionado el JRadioButton");
		});
		
		modeloTabla = new ModeloTabla(Contenedora.getLista());
		tabla = new JTable(modeloTabla);
		scrollTabla = new JScrollPane(tabla);
		getContentPane().add(scrollTabla, BorderLayout.CENTER);
		
		/*tabla.setDefaultRenderer(Object.class, new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// TODO Auto-generated method stub
				return null;
			}
		});*/
		/*
		 * 
		 * En la columna 0 que aparezca la foto aprobar.jpg*/
		int ancho = tabla.getTableHeader().getColumnModel().getColumn(0).getWidth();
		System.out.println(ancho);
		tabla.setRowHeight(ancho);

		/*La columna 0 tiene ancho 75 y el resto de columnas 50*/
		tabla.getColumnModel().getColumn(0).setPreferredWidth(200);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		tabla.setDefaultRenderer(Object.class, (table,value,isSelected,hasFocus,row,column)->{
			if(column == 0) {
				ImageIcon im = new ImageIcon("img/aprobado.jpg");
				ImageIcon imConDimensiones = new ImageIcon(im.getImage().getScaledInstance(ancho, ancho, Image.SCALE_DEFAULT));
				JLabel l = new JLabel(imConDimensiones);
				return l;
			}else if(column==1){
				JLabel l = new JLabel(value.toString());
				l.setHorizontalAlignment(JLabel.CENTER);
				l.setBackground(Color.YELLOW);
				l.setOpaque(true);
				return l;
			}else if(column==4) {
		
				JProgressBar pb = new JProgressBar(0, 10);
				pb.setValue((int)Float.parseFloat(value.toString()));
				pb.setStringPainted(true);
				pb.setToolTipText("Vamos a APROBAR TODOS :-) ");
				return pb;
			}
			else {
				JLabel l = new JLabel(value.toString());
				return l;
			}
		});
		
		/*tabla.getTableHeader().setDefaultRenderer(new TableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				// TODO Auto-generated method stub
				return null;
			}
		});*/
		
		tabla.getTableHeader().setDefaultRenderer((table,value,isSelected,hasFocus,row,column)->{
			if(column==0) {
				JLabel l = new JLabel(value.toString());
				l.setHorizontalAlignment(JLabel.CENTER);
				l.setFont(new Font("Arial", Font.BOLD, 30));
		
				l.setOpaque(true);
				l.setBackground(Color.LIGHT_GRAY);
				return l;
			}else {
				JLabel l = new JLabel(value.toString());
				l.setHorizontalAlignment(JLabel.CENTER);
				l.setFont(new Font("Arial", Font.PLAIN, 30));
				return l;
			}
		});
		setVisible(true);
	}
	public static void main(String[] args) {
		new Ventana2();
		
		Thread t1 = new Thread(()->{
			System.out.println("Soy el hilo 1");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread(()->{
			System.out.println("Soy el hilo 2");
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
		Thread t3 = new Thread(()->{
			System.out.println("Soy el hilo 3");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t1.start();
		try {
			t1.join();
			t2.start();
			
			t2.join();
			t3.start();
			
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Todos los hilos han finalizado");
	}

}










