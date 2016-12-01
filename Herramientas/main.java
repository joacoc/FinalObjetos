package Herramientas;
import InterfazGrafica.InterfazUsuario;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.setProperty("java.library.path", "C:/Users/franc/Dropbox/Workspace/TPFinalObjetos");
		String s = "Last night, Adly Mansour, the interim leader of Egypt, announced plans to "
				+ "reform Egypt's constitution and hold a new round of parliamentary and "
				+ "presidential elections. The interim president also announced a judicial "
				+ "investigation into yesterday's shooting of at least 51 supporters of deposed "
				+ "president Mohamed Morsi. "
				+ "Mansour plans to form a panel within fifteen days to review and suggest changes"
				+ "to the now-suspended constitution. Those amendments would be voted on in a "
				+ "referendum within four months. Parliamentary elections would then be held, "
				+ "perhaps in early 2014, followed by presidential elections upon the forming "
				+ "of a new parliament.";
		


//		String s = "Hey dude, that was a great film by the way.\n"
//				+ "I love you.\n"
//				+ "This SUX.\n"
//				+ "Cheers!";
		// String s = "No, no juegues con eso.\n"
				// + "Sos un idiota.\n"
				// + "Dedicate a otra cosa.\n"
				// + "Pasame una carta de Esconder.";
		//----------------NLTK------------------------------
		

		//Si paso el string con '\n' se rompe en NLTK.
		//Para el sentiment analysis si el idioma no es en castellano 
		//hay que traducirlo. 
		//Para traducirlo se utiliza la libreria de python 'goslate'
		//que utiliza los servicios de google para traducir el texto.
		//ATENCION: Puede ser que el servicio se caiga. Esto puede resultar un problema en la salida.
		
		s = s.replaceAll("\n", " ");
		NLTK nltk = new NLTK();
		nltk.sentiment_analysis(s);
	
		//----------------Chat bot------------------------------
		/*
        ChatterBotFactory factory = new ChatterBotFactory();

        ChatterBot bot1 = factory.create(ChatterBotType.CLEVERBOT);
        ChatterBotSession bot1session = bot1.createSession();

        Scanner keyboard = new Scanner(System.in);
        String respuesta;
        String sentencia = "Hola";
        
        while (true) {
            
            respuesta = bot1session.think(sentencia);
            
            System.out.println("\nbot1> " + respuesta);
            
            System.out.println("\nIngrese una sentencia:\n");
            sentencia = keyboard.nextLine();
        }
        */

		//----------------OpenNLP------------------------------
		
		/*System.out.println("OPENNLP: ");
		
		
		OpenNLP open = new OpenNLP ();
		System.out.println("TOKENIZAR: ");
		System.out.println();
		open.tokenizar(s);
		System.out.println();
		System.out.println("ETIQUETADO GRAMATICAL: ");
		System.out.println();
		
		open.etiquetado_gramatical(s);
		System.out.println();
		System.out.println("CHUNK: ");
		System.out.println();
		open.chunk(s);
		System.out.println();
		System.out.println("PARSE: ");
		System.out.println();
		open.parse(s);
		System.out.println();
		//String op= "Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29. Mr. Vinken is chairman of Elsevier N.V., the Dutch publishing group. Rudolph Agnew, 55 years old and former chairman of Consolidated Gold Fields PLC, was named a director of this British industrial conglomerate.";
		System.out.println("NAME FINDER ");
		System.out.println();
		open.name_entity_recognizer(s);
		System.out.println();
		System.out.println("SENTENCE DETECT ");
		System.out.println();
		open.sentence_detect(s);
		//open.coreference(op);
		
		
		System.out.println();
		System.out.println();
	*/
		
		
		//------------------StanfordCoreNLP---------------------
		
		//System.out.println("STANFORD: ");
		
		
		//StCoreNLP core = new StCoreNLP ();
		//String st="Donald Trump is a new president";
		/*core.tokenizar(s);
		core.parse(s);
		core.etiquetado_gramatical(s);
		core.name_entity_recognizer(s);*/
		//core.sentiment_analysis(st);
		//core.coreference(s);
		
		
		
		
		InterfazUsuario interfaz = new InterfazUsuario();

	}

}
