public class EjemploHilosV {

    // Objeto de sincronización
    private static final Object monitor = new Object(); //Variable compartida para usar notifyAll

    // Hilo que simula una tarea y luego notifica cuando termina
    static class HiloLento extends Thread {
        @Override
        public void run() {
            System.out.println("HiloLento: Empezando tarea...");
            try {
                Thread.sleep(5000); // Simula que la tarea tarda 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("HiloLento: Tarea completada.");

            // Notificar a los hilos esperando en 'monitor'
            synchronized (monitor) {
            	System.out.println("Hilo Lento envía la señal");
                monitor.notifyAll(); // Notifica que HiloLento ha terminado
            }
        }
    }

    // Hilo que espera a que HiloLento termine su tarea
    static class HiloEsperando extends Thread {
        @Override
        public void run() {
            System.out.println("HiloEsperando: Esperando que HiloLento termine...");

            // Espera la notificación de HiloLento
            synchronized (monitor) {
                try {
                    System.out.println("HiloEsperando: Esperando notificación de HiloLento...");
                    monitor.wait(); // Espera hasta recibir una notificación
                    System.out.println("Hilo esperando recibe una señal");
                    for(int i=0;i<10;i++) {
                    	System.out.println(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("HiloEsperando: HiloLento ha completado la tarea. Continuando...");
        }
    }

    public static void main(String[] args) {
        // Creación de los hilos
        HiloLento hiloLento = new HiloLento();
        HiloEsperando hiloEsperando = new HiloEsperando();

        // Iniciar ambos hilos
        hiloEsperando.start();
        hiloLento.start();
    }
}