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
		
		System.out.println("Simulador construido.");
		
		
		
		
	}
	
	
	
	@Override
	public synchronized void run() {
		
		
		
		
	}
	
	public void print() {
		ArrayList<Registros> Registros = new ArrayList<Registros>();
		Registros.add(new Registros("asd", "fgh", "jkl", "ñzx"));
		Launcher.getG().actualizarRegistros(Registros);
	}
	
	private String ejecutar(String instruccion) {
		
		
		
		
		return "";
		
	}
	
	
	
	

}
