package monitor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import monitor.Student;
import monitor.Monitor;

public class Main {
	
	private static Queue<Student> colaEstudiantes;
	private long semilla = Long.parseLong("1010");
	private static Semaphore sEstudiante;
	private static Semaphore sMonitor;
	private static Student e1, e2, e3, e4, e5, e6;
	private static Monitor monitor;
	private static Semaphore sCola;
	
	public Main() {
		sCola = new Semaphore(1,true);
		sEstudiante = new Semaphore(1,true);
		sMonitor = new Semaphore(1, true);
		colaEstudiantes = new LinkedList<Student>();
		
		monitor = new Monitor(colaEstudiantes, sCola, semilla, sMonitor, sEstudiante);
		e1 = new Student("Estudiante 1", sCola, sMonitor, sEstudiante, colaEstudiantes, semilla);
		e2 = new Student("Estudiante 2", sCola, sMonitor, sEstudiante, colaEstudiantes, semilla);
		e3 = new Student("Estudiante 3", sCola, sMonitor, sEstudiante, colaEstudiantes, semilla);
		e4 = new Student("Estudiante 4", sCola, sMonitor, sEstudiante, colaEstudiantes, semilla);
		e5 = new Student("Estudiante 5", sCola, sMonitor, sEstudiante, colaEstudiantes, semilla);
		e6 = new Student("Estudiante 6", sCola, sMonitor, sEstudiante, colaEstudiantes, semilla);
		
		sMonitor.drainPermits();
		sEstudiante.drainPermits();

		monitor.start();
		e1.start();
		e2.start();
		e3.start();
		e4.start();
		e5.start();
		e6.start();
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Main main = new Main();		
	}

}