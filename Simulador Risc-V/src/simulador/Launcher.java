package simulador;

import java.io.File;
import java.io.IOException;

/**
	 * Esta clase lanzará los hilos de ejecución principales, la interfaz gráfica 
	 * y el proceso de fondo que gestionará la lógica.
	 * 
	 * 
	 * @author Sergio Ramirez
	 * 
	 * @version 1.0
	 * 
	 * 
	 * 
	 *
	 */

public class Launcher {
	private static File file;
	private static GUI g;
	private static Sim s;
	
	public synchronized static void main(String[] args) throws IOException {
		
		
		file = new File("/root/Escritorio/file");
		
		s = new Sim(file);
		Thread S = new Thread(s);
		S.start();
		
		g = new GUI();
		g.loadFile(file);

		
		Thread G = new Thread(g);
		G.start();
		
		
		
	}

	public File getFile() {
		return file;
	}

	public static GUI getG() {
		return g;
	}

	public static Sim getS() {
		return s;
	}
	
	
	
	

	
}
