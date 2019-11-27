/**
 * 
 */
package simulador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
	private File file;
	public Sim(File file) throws FileNotFoundException {

		ConnectionLite con = new ConnectionLite();
		this.registros= con.GetRegisters();
		this.file= file;
//		BufferedReader br = new BufferedReader(fileReader);

		System.out.println("Simulador construido.");




	}



	@Override
	public synchronized void run() {

		
		

	}

	public void print() {

		Launcher.getG().actualizarRegistros(this.registros);
	}
	
	
	public void ejecutar(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String read = br.readLine();
			while (read != null){
				ejecutarInstruccion(read);
				read= br.readLine();
				
			}
			br.close();
		} catch (IOException e) {
			
		}

		
	} 

	private String ejecutarInstruccion(String linea) {

		if(!linea.trim().equals("")) {
		StringTokenizer st = new StringTokenizer(linea, " ");

		
		String instruccion = st.nextToken();
		String complemento = st.nextToken();

		instruccion = instruccion.toUpperCase();
		System.out.println(instruccion);
		switch (instruccion) {
		case "LUI":
			System.out.println("Instruccion " + instruccion + " ejecutada");
			Launcher.getG().actualizarConsola("Instruccion " + instruccion + " ejecutada");
			break;

		case "AUIPC":


			break;	

		case "JAL":


			break;	

		case "HALR":


			break;
		case "BEQ":


			break;
		case "BNE":


			break;
		case "BLT":


			break;
		case "BGE":


			break;
		case "BLTU":


			break;
		case "BGEU":


			break;
		case "LB":


			break;
		case "LH":


			break;
		case "LW":


			break;
		case "LBU":


			break;
		case "LHU":


			break;
		case "SB":


			break;
		case "SH":


			break;
		case "SW":


			break;
		case "ADDI":
			StringTokenizer staux = new StringTokenizer(complemento,",");
			String r1 = staux.nextToken();
			String r2 = staux.nextToken();
			String imm = staux.nextToken();
			int sum1 = 0;
			int sum2 = Integer.parseInt(imm);
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1))
					sum1=registros.get(i).getVal();
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2))
					registros.get(i).setVal(sum1+sum2);
			
			Launcher.getG().actualizarRegistros(registros);
			
			


			break;
		case "SLTI":


			break;
		case "SLTIU":


			break;
		case "XORI":


			break;
		case "ORI":


			break;
		case "ANDI":


			break;
		case "SLLI":


			break;
		case "SRLI":


			break;
		case "SRAI":


			break;
		case "ADD":


			break;
		case "AUB":


			break;
		case "SLL":


			break;
		case "SLT":


			break;
		case "SLTU":


			break;
		case "XOR":


			break;
		case "SRL":


			break;
		case "SRA":


			break;
		case "OR":


			break;
		case "AND":


			break;
		case "FENCE":


			break;
		case "FENCE.I":



		default:
			System.err.println("La instruccion " + instruccion + " no es una instruccion de RISC-V o no ha sido implementada aun");
			Launcher.getG().actualizarConsola("La instruccion " + instruccion + " no es una instruccion de RISC-V o no ha sido implementada aun");
			break;
		}

	}
		return "";

	}
	




}
