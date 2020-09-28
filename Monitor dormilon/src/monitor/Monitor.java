package monitor;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Monitor extends Thread {
	
	private Queue<Student> queueStudents;
	private Semaphore semQueue;
	private Semaphore semStudent;
	private Semaphore semMonitor;
	
	public Monitor(Queue<Student> queueStudents, Semaphore semQueue, Semaphore semStudent, Semaphore semMonitor) {
		super();
		this.queueStudents = queueStudents;
		this.semQueue = semQueue;
		this.semStudent = semStudent;
		this.semMonitor = semMonitor;
	}
	
	public void run() {
		while (true) {
			try {
				if(queueStudents.isEmpty()) {
					System.out.println("- [Monitor] Bueno, no hay nadie, así que ¡A dormir!");
					semStudent.release();
					sleep((long) ((Math.random() + 1) *100));
				}else {
					System.out.println("- [Monitor] Que pase el "+queueStudents.peek().getName()+". Empieza la monitoría");
					sleep((long) ((Math.random() + 1) *100));
					semMonitor.acquire();
					System.out.println("- [Monitor] Terminé la monitoría");
					semQueue.release();
					queueStudents.poll();
				}
				
			}catch(InterruptedException e) {
				
			}
		}
	}
	
}
