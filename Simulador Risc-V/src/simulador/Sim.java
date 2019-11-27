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
				if(!read.trim().equals(""))
					if(read.charAt(0)!='#')
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
		
		String binary = "";
		StringTokenizer stAux;
		String r1;
		String r2;
		String r3;
		String imm;
		int sum1=0;
		int sum2=0;
		int ir1=-1;
		int ir2=-1;
		int ir3=-1;
		int auxC;
		
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
			
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2= stAux.nextToken();
			imm = stAux.nextToken();
			sum1 = 0;
			sum2 = Integer.parseInt(imm);
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1.trim())) {
					ir1=i;
					sum1=registros.get(i).getVal();
					}
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2.trim())) {
					ir2=i;
					registros.get(i).setVal(sum1+sum2);
					}
			
			//Tipo I
			imm=Integer.toBinaryString(sum2).toString();
			if(imm.length()>12)
				binary += imm.substring(imm.length()-12, imm.length()) + " ";
			else
				binary += imm + " ";
			
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+= " 000 ";
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			
			binary+=" 0010011";
			
			
			Launcher.getG().actualizarConsola(linea +" --> " + binary);
			
			
			

			break;
		case "SLTI":

			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2= stAux.nextToken();
			imm = stAux.nextToken();
			sum1 = 0;
			sum2 = Integer.parseInt(imm);
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1.trim())) {
					ir1=i;
					
					}
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2.trim())) {
					ir2=i;
					
					}
			
			//Tipo I
			imm=Integer.toBinaryString(sum2).toString();
			if(imm.length()>12)
				binary += imm.substring(imm.length()-12, imm.length()) + " ";
			else
				binary += imm + " ";
			
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+= " 010 ";
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			
			binary+=" 0010011";
			
			
			Launcher.getG().actualizarConsola(linea +" --> " + binary);
			

			break;
		case "SLTIU":

			
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2= stAux.nextToken();
			imm = stAux.nextToken();
			sum1 = 0;
			sum2 = Integer.parseInt(imm);
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1.trim())) {
					ir1=i;
					
					}
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2.trim())) {
					ir2=i;
					
					}
			
			//Tipo I
			imm=Integer.toBinaryString(sum2).toString();
			if(imm.length()>12)
				binary += imm.substring(imm.length()-12, imm.length()) + " ";
			else
				binary += imm + " ";
			
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+= " 011 ";
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			
			binary+=" 0010011";
			
			
			Launcher.getG().actualizarConsola(linea +" --> " + binary);
			

			break;
		case "XORI":
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2= stAux.nextToken();
			imm = stAux.nextToken();
			sum1 = 0;
			sum2 = Integer.parseInt(imm);
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1.trim())) {
					ir1=i;
					
					}
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2.trim())) {
					ir2=i;
					
					}
			
			//Tipo I
			imm=Integer.toBinaryString(sum2).toString();
			if(imm.length()>12)
				binary += imm.substring(imm.length()-12, imm.length()) + " ";
			else
				binary += imm + " ";
			
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+= " 100 ";
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			
			binary+=" 0010011";
			
			
			Launcher.getG().actualizarConsola(linea +" --> " + binary);
			

			break;
		case "ORI":

			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2= stAux.nextToken();
			imm = stAux.nextToken();
			sum1 = 0;
			sum2 = Integer.parseInt(imm);
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1.trim())) {
					ir1=i;
					
					}
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2.trim())) {
					ir2=i;
					
					}
			
			//Tipo I
			imm=Integer.toBinaryString(sum2).toString();
			if(imm.length()>12)
				binary += imm.substring(imm.length()-12, imm.length()) + " ";
			else
				binary += imm + " ";
			
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+= " 110 ";
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			
			binary+=" 0010011";
			
			
			Launcher.getG().actualizarConsola(linea +" --> " + binary);
			

			break;
		case "ANDI":
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2= stAux.nextToken();
			imm = stAux.nextToken();
			sum1 = 0;
			sum2 = Integer.parseInt(imm);
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1.trim())) {
					ir1=i;
					
					}
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2.trim())) {
					ir2=i;
					
					}
			
			//Tipo I
			imm=Integer.toBinaryString(sum2).toString();
			if(imm.length()>12)
				binary += imm.substring(imm.length()-12, imm.length()) + " ";
			else
				binary += imm + " ";
			
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+= " 111 ";
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			
			binary+=" 0010011";
			
			
			Launcher.getG().actualizarConsola(linea +" --> " + binary);
			

			break;
		case "SLLI":
			//shamt?

			break;
		case "SRLI":
			//shamt?


			break;
		case "SRAI":
			//shamt?


			break;
		case "ADD":
			
			
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2 = stAux.nextToken();
			r3 = stAux.nextToken();
			sum1 = 0;
			sum2 = 0;
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
				}
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
					registros.get(i).setVal(sum1+sum2);
					}
			
			//Tipo R
			binary+= "0000000 "; //Function

			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 000 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);

			break;
			
			
		case "SUB":
			
			//Tipo R
			binary+= "0100000 "; //Function
			
			
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2 = stAux.nextToken();
			r3 = stAux.nextToken();
			sum1 = 0;
			sum2 = 0;
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
				}
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
					registros.get(i).setVal(sum1-sum2);
					}
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 000 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);

			
			break;
		case "AUB":


			break;
		case "SLL":

			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2 = stAux.nextToken();
			r3 = stAux.nextToken();
			sum1 = 0;
			sum2 = 0;
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
				}
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
					}
			
			//Tipo R
			binary+= "0000000 "; //Function
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 001 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);



			break;
		case "SLT":
			
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2 = stAux.nextToken();
			r3 = stAux.nextToken();
			sum1 = 0;
			sum2 = 0;
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
				}
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
					}

			
			//Tipo R
			binary+= "0000000 "; //Function
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 010 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);



			break;
		case "SLTU":
			stAux = new StringTokenizer(complemento,",");
			
			r1= stAux.nextToken();
			r2= stAux.nextToken();
			r3= stAux.nextToken();
			
			
			
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
					registros.get(i).setVal(sum1>sum2?1:0);
					}
					
			
			
			//Tipo R
			binary+= "0000000 "; //Function
			
					
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 011 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);

			
			
			break;
		case "XOR":

			
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2 = stAux.nextToken();
			r3 = stAux.nextToken();
			sum1 = 0;
			sum2 = 0;
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
				}
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
					registros.get(i).setVal(Integer.parseInt(Integer.toBinaryString(sum1))^Integer.parseInt(Integer.toBinaryString(sum2)));
					}

			//Tipo R
			binary+= "0000000 "; //Function
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 100 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);


			break;
		case "SRL":
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2 = stAux.nextToken();
			r3 = stAux.nextToken();
			sum1 = 0;
			sum2 = 0;
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
				}
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
				
					}

			//Tipo R
			binary+= "0000000 "; //Function
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 101 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);



			break;
		case "SRA":
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2 = stAux.nextToken();
			r3 = stAux.nextToken();
			sum1 = 0;
			sum2 = 0;
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
				}
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
					}

			//Tipo R
			binary+= "0100000 "; //Function
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 101 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);


			break;
		case "OR":
			
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2 = stAux.nextToken();
			r3 = stAux.nextToken();
			sum1 = 0;
			sum2 = 0;
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
				}
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
					registros.get(i).setVal(Integer.parseInt(Integer.toBinaryString(sum1))|Integer.parseInt(Integer.toBinaryString(sum2)));
					}
			Launcher.getG().actualizarRegistros(registros);
			
			//Tipo R
			binary+= "0000000 "; //Function
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 110 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);


			break;
		case "AND":
			
			stAux = new StringTokenizer(complemento,",");
			r1 = stAux.nextToken();
			r2 = stAux.nextToken();
			r3 = stAux.nextToken();
			sum1 = 0;
			sum2 = 0;
		
			
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r2)) {
					ir2=i;
					sum1=registros.get(i).getVal();
				}
			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r3)) {
					ir3=i;
					sum2=registros.get(i).getVal();
					}

			for (int i = 0; i < registros.size(); i++) 
				if(registros.get(i).getABI_Name().equals(r1)) {
					ir1=i;
					registros.get(i).setVal(Integer.parseInt(Integer.toBinaryString(sum1))&Integer.parseInt(Integer.toBinaryString(sum2)));
					}

			
			//Tipo R
			binary+= "0000000 "; //Function
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";

			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir3).getRegister().substring(1)));
			binary+= " "; 
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir2).getRegister().substring(1)));
			
			binary+=" 111 ";
			
			auxC = Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1))).length();	
			while(auxC++<5)
				binary+="0";
			binary+= Integer.toBinaryString(Integer.parseInt(registros.get(ir1).getRegister().substring(1)));
			binary+=" 0110011";
			Launcher.getG().actualizarConsola(linea +" --> " + binary);


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
		Launcher.getG().actualizarRegistros(registros);
		return "";

	}
	




}
