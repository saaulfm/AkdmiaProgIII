public class EjemploHilos {

    // Hilo que simula una tarea y luego notifica cuando termina
    static class HiloLento extends Thread {
        @Override
        public void run() {
            System.out.println("HiloLento: Empezando tarea...");
            try {
                Thread.sleep(5000); // Este hilo tarda 5 segundos en ejecutarse
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("HiloLento: Tarea completada.");
        }
    }

    // Hilo que espera a que HiloLento termine su tarea
    static class HiloEsperando extends Thread {
        private final HiloLento hiloLento;

        //El constructor del Hilo que espera recibe por parámetro el hilo al que tiene que esperar (HiloLento)
        public HiloEsperando(HiloLento hiloLento) {
            this.hiloLento = hiloLento;
        }

        @Override
        public void run() {
            System.out.println("HiloEsperando: Esperando que HiloLento termine...");

            // Espera a que HiloLento termine usando join()
            try {
                hiloLento.join(); // Espera a que HiloLento complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("HiloEsperando: HiloLento ha completado la tarea. Continuando...");
        }
    }

    public static void main(String[] args) {
        // Creación de los hilos
        HiloLento hiloLento = new HiloLento();
        HiloEsperando hiloEsperando = new HiloEsperando(hiloLento);

        // Iniciar ambos hilos
        hiloLento.start();
        hiloEsperando.start();
        
        /*Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});*/
        /*Thread t = new Thread(()->{
        	
        });*/
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}