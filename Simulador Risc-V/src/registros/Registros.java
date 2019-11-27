package registros;


public class Registros {

/**
 * Esta clase es una abstracción de un registro de la arquitectura de RISC-V
 * 
 */
	
	private String Register;
	private String ABI_Name;
	private String Description;
	private String Saver;
	private int val;
	
	/**
	 * 
	 * @param Register Referencia del registro
	 * @param ABI_Name Nombre del registro
	 * @param Description Descripción del registro
	 * @param Saver 
	 *
	 */
	public Registros(String Register, String ABI_Name, String Description, String Saver) {

		this.Register = Register;
		this.ABI_Name = ABI_Name;
		this.Description=Description;
		this.Saver= Saver;
		this.val=0;
			
		
	}
	
	
	
	public  String getRegister() {
		return Register;
	}
	public  String getABI_Name() {
		return ABI_Name;
		
	}
	public  String getDescription() {
		return Description;
	}
	public  String getSaver() {
		return Saver;
	}
	
	
	
	public int getVal() {
		return val;
	}



	public  void setRegister(String register) {
		
		Register=register;
	}
	public void setABI_Name(String aBI_Name) {
		ABI_Name = aBI_Name;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public void setSaver(String saver) {
		Saver = saver;
	}
	
	
	
	public void setVal(int val) {
		this.val = val;
	}



	@Override
	public String toString() {

		return this.Register + " " + this.ABI_Name + " " + this.Description +" "+ this.Saver; 
		
	}
	
}
