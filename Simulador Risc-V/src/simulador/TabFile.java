package simulador;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.Tab;


public class TabFile {

	private ArrayList<Tab> tabs;
	private ArrayList<File> files;
	private int size;
	
	public TabFile() {
		this.tabs= new ArrayList<Tab>();
		this.files = new ArrayList<File>();
		this.size=0;
	}
	
	public boolean add(Tab tab, File file) {
		this.size++;
		return tabs.add(tab) && files.add(file);
	} 
	
	public boolean remove(Tab tab, File file) {
		this.size--;
		return tabs.remove(tab) && files.remove(file); 
	}
	
	public File getFileFromTab(Tab tab) {
		
		for(int i=0; i < this.size; i++)
			if(tabs.get(i).equals(tab))
				return files.get(i);
		return null;
	}
	
	public Tab getTabFromFile(File file) {
		
		for(int i=0; i < this.size; i++)
			if(files.get(i).equals(file))
				return tabs.get(i);
		return null;
	}
	
}
