package simulador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageReader;

import db.ConnectionLite;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import registros.Registros;

/**
 * Esta clase se encarga de crear y mostrar la interfaz gráfica de la aplicación
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

public class GUI extends Application implements Runnable {

	private static File file;
//	private FileReader fileReader;
//	private static BufferedReader br;
	public static boolean built=false;
	private final int layoutX = 600;
	private final int layoutY = 400;
	private static BorderPane window;
	private static TextArea console;
	private TableView<Registros> vistaRegistros;
	private ArrayList<Registros> Registros;
	


	public GUI() {
		
	}

	
	@Override
	public synchronized void run() {
		Application.launch();
	}

	
	
	/**
	 * Meodo que se encarga de dibujar y organizar los componentes gráficos del proyecto
	 */
	@Override
	public void start(Stage st) throws Exception {
		
		establecerConexión();
		
		//Ventana contenedora
		window = new BorderPane();
//		window.setMinSize(layoutY, layoutY);
		
		//Parte central
		TextArea editorTexto = new TextArea();
		editorTexto.setWrapText(true);
		editorTexto.setText(leerTextoArchivo());
		Tab editorDefecto = new Tab(this.file.getName(), editorTexto);
		TabPane editor = new TabPane(editorDefecto);
		
//		editorTexto.selectRange(0,editorTexto.);
		
		
		//Parte derecha
		getVistaRegistros();

		
		
		
		//Parte Inferior
		//Ver operación en curso en binario y hexa
		//TextArea
		
		
		//Barras parte superior y botones guardado
		BorderPane barrasSuperior = new BorderPane();
		MenuBar bar = new MenuBar();
		
		Menu menuFile = new Menu("Archivo");
		Menu menuEjecutar= new Menu("Ejecutar");
		Menu menuAyuda = new Menu("Ayuda");
		
		MenuItem run = new MenuItem("Run");
		run.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				Launcher.getS().print();
				Launcher.getS().ejecutar();
				
			}
		});
		menuEjecutar.getItems().add(run);
		
		bar.getMenus().addAll(menuFile,menuEjecutar,menuAyuda);
		barrasSuperior.setTop(bar);
		
		//Barra con botones para interactuar

		HBox botonera = new HBox();
		
		File archivoPlay = new File("src/res/play.png");
		Image imagenPlay = new Image(archivoPlay.toURI().toString());
		ImageView visorImagenPlay = new ImageView(imagenPlay);
		
		Button botonEjecutar = new Button("",visorImagenPlay);
		botonEjecutar.setFocusTraversable(false);
		
		File archivoStop = new File("src/res/stop.png");
		Image imagenStop = new Image(archivoStop.toURI().toString());
		ImageView visorImagenStop = new ImageView(imagenStop);
		
		Button botonParar = new Button("",visorImagenStop);
		botonParar.setFocusTraversable(false);
		
		
//		botonEjecutar.setShape(new Circle(1));
		
		botonEjecutar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				Launcher.getS().ejecutar();
				
			}
		});
		
		botonera.getChildren().addAll(botonEjecutar,botonParar);
		barrasSuperior.setCenter(botonera);
		
		//Abajo log
		vistaConsola();
		
		
		
		
		window.setTop(barrasSuperior);
		window.setCenter(editor);
		window.setRight(vistaRegistros);
		window.setLeft(null);
		window.setBottom(console);
		
		
		
		Scene sc = new Scene(window);
		st.setTitle("Staurn Risc-V");
//		st.setResizable(false);
//		st.setMaximized(true);
		st.setScene(sc);
		st.centerOnScreen();
		st.show();
		

	}

/**
 * Este método lee el archivo que contiene el código a ejecutar y lo carga a una variable Stgring
 * 
 * @return Un String con el texto del archivo
 * @throws IOException
 */
	
	private String leerTextoArchivo() throws IOException {
//		if(fileReader!=null) {
			String text = "";
			final String jump="\n";
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			
			String read = br.readLine();
			
			while (read != null ) { 
				try {
					text += read + jump;
					read=br.readLine();
					
				} catch (Exception e) {
					System.err.println("fin archivo");
				}
			}

			br.close();
			return text;
//		}
//		else {
//			System.err.println("No hay archivo cargado para mostrar");
//		}
//		return "";
	}

	/**
	 * Con este método se carga el archivo que contiene el código a ejecutar
	 * @param file
	 * @throws IOException
	 */
	
	public void loadFile(File file) throws IOException {
		
		if(file!=null) {
			this.file=file;
//			fileReader = new FileReader(file);
//			built=true;
//			br = new BufferedReader(fileReader);
//			System.out.println(br.readLine());
			
		}
		else
			System.err.println("Archivo nulo");

	}

	
	/**
	 * Este metodo construye la vista de los registros en un TableView de acuerdo al ArrayList
	 * de los registros.
	 * 
	 * @return TableView con los registros
	 */
	private TableView<Registros> getVistaRegistros() {
		
		
		vistaRegistros = new TableView<Registros>();
		vistaRegistros.setMinHeight(layoutY);
		vistaRegistros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		vistaRegistros.setEditable(true);
		
		TableColumn registro = new TableColumn("Registro");
		TableColumn nombreRegistro = new TableColumn("Nombre");
		TableColumn valorRegistro = new TableColumn("Valor");
		
		
		registro.setCellValueFactory(new PropertyValueFactory<Registros, String>("Register"));
		nombreRegistro.setCellValueFactory(new PropertyValueFactory<Registros, String>("ABI_Name"));
		valorRegistro.setCellValueFactory(new PropertyValueFactory<Registros, String>("val"));
		
		
		vistaRegistros.getColumns().addAll(registro,nombreRegistro, valorRegistro);
		
		
		ObservableList<Registros> listaRegistros = FXCollections.observableArrayList(Registros);
		
		
			
		vistaRegistros.setItems(listaRegistros);
		return vistaRegistros;
	}
	
	/**
	 * Esta funcion es llamada para construir y configurar la vista de la consola de eventos
	 */
	private void vistaConsola() {
		
		this.console = new TextArea();
		console.setEditable(false);
		console.setFont(Font.font(13));
		console.setMaxHeight(100);
		console.setWrapText(true);
		actualizarConsola("Esta es la consola");	
	}
	
	/**
	 * Esta funcion permite actualizar el texto de la consola
	 * 
	 * @param s
	 */
	void actualizarConsola(String s) {
		if(this.console.getText().equals(""))
			this.console.setText(this.console.getText() + s);
		else
			this.console.setText(this.console.getText() + "\n" + s);
	}
	
	
	/**
	 * Esta funcion permite actualizar el texto de la consola con un color en especifico
	 * 
	 * @param s
	 */
	private void actualizarConsola(String s,String color) {
		if(this.console.getText().equals(""))
			this.console.setText(this.console.getText() + s);
		else
			this.console.setText(this.console.getText() + "\n" + s);
	}
	
	/**
	 * Este metodo estabece la conexión a la base de datos para obtener los datos de los 
	 * registros, y asigna los valores al ArrayList.
	 */
	private void establecerConexión() {
		ConnectionLite con = new ConnectionLite();
		this.Registros = con.GetRegisters();
		
	}
	
	
	/**
	 * Esta funcion debe ser llamada por la clase de simulador (SIM) para actualizar los registros
	 * en la interfaz grpafica 
	 */
	public void actualizarRegistros(ArrayList<Registros> Registros) {
		this.Registros=Registros;
		getVistaRegistros();
		this.window.setRight(vistaRegistros);
		
	}
	
	
	
}
