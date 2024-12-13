public class EjemploThread {

    public static void main(String[] args) {      
        // Vamos a crear un thread para contar desde 10 a 0.
        // Además, el hilo se va a dormir después de imprimir cada
        // valor durante 1000 ms.
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 10; i >= 0; i--) {
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //
                    }
                }

                System.out.println(Thread.currentThread().getName() + " finalizado");
            }
        });

        // Se debe iniciar el thread.
        t.start();

        try {
			t.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        // // Aquí el programa principal empieza a contar hasta 5
        // // lo que se realizará de forma concurrente al hilo creado.
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //
            }
        }

        // Hasta que no terminen el hilo principal (main) y el hilo que
        // hemos creado, el programa sigue en ejecución.

        System.out.println("Hilo main terminado");
    }
}
