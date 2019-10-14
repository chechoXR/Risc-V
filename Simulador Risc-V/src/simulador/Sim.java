/**
 * 
 */
package simulador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import db.ConnectionLite;
import registros.Registros;

/**
 * Esta clase simula la arquitectura de RISC-V haciendo uso del conjunto de instrucciones RV32I
 * Controla los registros, transforma las instucciones de código ensamblador a código binario y ejecuta las instrucciones.
 * 
 * @author Sergio Ramirez
 *
 */
public class Sim implements Runnable{

	private ArrayList<Registros> registros;
	
	public Sim(File file) throws FileNotFoundException {
		
		ConnectionLite con = new ConnectionLite();
		this.registros= con.GetRegisters();
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		
		System.out.println(registros.toString());
		
		
		
		
	}
	
	
	
	@Override
	public synchronized void run() {

		for(int i=0; i<200;i++) {
			System.out.println(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
