package monitor;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread {
	
	private Queue<Student> colaEstudiantes;
	private Random aleatorio;
	private Semaphore sCola;
	private Semaphore sEstudiante;
	private Semaphore sMonitor;

	public Monitor(Queue<Student> colaEstudiantes, Semaphore sCola, long semilla, Semaphore sMonitor, Semaphore sEstudiante) {
		super();
		this.sCola = sCola;
		this.aleatorio = new Random(semilla);
		this.sMonitor = sMonitor;
		this.colaEstudiantes = colaEstudiantes;
		this.sEstudiante = sEstudiante;
	}
	
	public void run() {
		while (true) {
			try {
				if(colaEstudiantes.isEmpty()) {
					System.out.println("- [Monitor] Bueno, no hay nadie, as� que �A dormir!");
					sEstudiante.release();
					sleep(Math.abs(aleatorio.nextInt()) % 1000);
				}else {
					System.out.println("- [Monitor] Que pase el "+colaEstudiantes.peek().getNombre()+". Empieza la monitor�a");
					sleep(Math.abs(aleatorio.nextInt()) % 1000);
					sMonitor.acquire();
					System.out.println("- [Monitor] Termin� la monitor�a");
					sCola.release();
					colaEstudiantes.poll();
				}
				
			}catch(InterruptedException e) {
				
			}
		}
	}
}