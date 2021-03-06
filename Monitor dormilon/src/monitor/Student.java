package monitor;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Student extends Thread {

	private String nombre;
	private Semaphore sCola;
	private Semaphore sMonitor;
	private Semaphore sEstudiante;
	private Queue<Student> cola;
	private Random aleatorio;
	
	public Student(String nombre, Semaphore sCola, Semaphore sMonitor, Semaphore sEstudiante, Queue<Student> cola, long semilla) {
		super();
		this.sCola = sCola;
		this.sEstudiante = sEstudiante;
		this.nombre = nombre;
		this.cola = cola;
		this.sMonitor = sMonitor;
		aleatorio = new Random(semilla);
	}
	
	public void run() {
		while (true) {
			try {
				System.out.println("- ["+nombre+"] ¡Necesito ayuda!");
				sCola.acquire();
				if(cola.size() == 4) {
					System.out.println("- ["+nombre+"] ¡Rayos! La sala de espera está llena, me iré a la sala de cómputo a programar :(");
					sleep(Math.abs(aleatorio.nextInt()) % 1000);	
					sCola.release();
				} else {
					if(cola.isEmpty()) {
						cola.add(this);
						System.out.println("- ["+nombre+"] Despierte monitor, necesito de su ayuda");
						sEstudiante.acquire();
						sMonitor.release();
						System.out.println("- ["+nombre+"] ¡Terminé!");
					}else {
						System.out.println("- ["+nombre+"] El monitor está ocupado y hay sillas disponibles, me sentaré a esperar");
						cola.add(this);
						sleep(Math.abs(aleatorio.nextInt()) % 1000);
				}
			}
				
			} catch (InterruptedException e) {
			}
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
}