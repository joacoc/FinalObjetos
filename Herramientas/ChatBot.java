package Herramientas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import InterfazGrafica.InterfazChatBot;

public class ChatBot extends Thread {

	public static final String appData_Dir = System.getenv("LOCALAPPDATA")+"\\hangupsbot\\hangupsbot";
	InterfazChatBot interfaz;
	boolean inicializo = false;
	BufferedReader in;
	BufferedWriter out;
	
	public ChatBot(String key, InterfazChatBot interfaz) throws IOException{
		StringBuilder stringBuilder;
		this.interfaz = interfaz;
	}
	
	@Override
	public void run() {
		StringBuilder stringBuilder; 
		
		//Se corre en un thread aparte sino la GUI nunca se actualiza.
		Process p;
		try {
			p = Runtime.getRuntime().exec("python .\\hangoutsbot-master\\hangupsbot\\hangupsbot.py");
	    	in = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    	out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
	    	
	    	stringBuilder = new StringBuilder();
	    	String linea = in.readLine();    
	    	
	    	while(linea != null){
	    		stringBuilder.append(linea);
	    		if(linea.contains("bot initialised")){
	    			inicializo = true;
	    		}
	    		
	    		if(linea.contains("Sign in with your Google account")){
	    			interfaz.addTexto_Output( "\n\nSe necesita configurar el bot! \n"
	    					+ "Es necesario que para logear antes tenga configurado el bot.\n"
	    					+ "Para eso, dentro de la consola de comandos vaya a la carpeta donde se encuentra el hangouts bot y ejecute el siguiente comando y siga los pasos.\n"
	    					+ "Comando: python hangupsbot");
	    		}else
	    			interfaz.addTexto_Output(linea);
	    		
	    		linea = in.readLine();
	    	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarConfig() throws IOException{
		
		File f = new File(appData_Dir +"\\cookies.json");
		f.delete();
	}
	
	public void escribirConsola(String texto) throws IOException{
		out.flush();
		out.write(texto+"/r/n");
	}
	
}
