package Herramientas;


import InterfazGrafica.InterfazUsuario;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.setProperty("java.library.path", "C:/Users/franc/Dropbox/Workspace/TPFinalObjetos");
//		String z = "Proclaiming that “the main, most dangerous part of the war is already in the past,” President Petro O. Poroshenko of Ukraine said on Thursday that his office was in constant communication with President Vladimir V. Putin of Russia to sustain a truce in eastern Ukraine, and that Ukraine was now looking ahead to pursue membership in the European Union.";
//		String s= "Hola mi nombre es Franco. Yo soy de Argentina y juego al basquet.";
		
		//----------------NLTK------------------------------
		
		/*NLTK nltk = new NLTK();
		nltk.parse(s);*/
		
		//----------------OpenNLP------------------------------
		/*
		System.out.println("OPENNLP: ");
		
		
		OpenNLP open = new OpenNLP ();
		
		System.out.println();
		System.out.println("ETIQUETADO GRAMATICAL: ");
		System.out.println();
		
		
		System.out.println(open.etiquetado_gramatical(s));
		System.out.println("CHUNK: ");
		System.out.println();
		
		
		System.out.println(open.chunk(s));
		System.out.println("PARSE: ");
		System.out.println();
		open.parse(s);
		System.out.println();
		//String op= "Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29. Mr. Vinken is chairman of Elsevier N.V., the Dutch publishing group. Rudolph Agnew, 55 years old and former chairman of Consolidated Gold Fields PLC, was named a director of this British industrial conglomerate.";
		System.out.println("NAME FINDER ");
		System.out.println();
		
		System.out.println(open.name_entity_recognizer(s));
		System.out.println("SENTENCE DETECT ");
		System.out.println(open.sentence_detect(s));
		
		//open.coreference(op);
		
		
		System.out.println();
		System.out.println();
	
		*/
		
		//------------------StanfordCoreNLP---------------------
		/*
		System.out.println("STANFORD: ");
		
		
		StCoreNLP core = new StCoreNLP ();
		//String st="Donald Trump is a new president";
		//core.tokenizar(s);
		
		System.out.println("-----------------Parser-----------------");
		core.parse(s);
		
		System.out.println("-----------------Etiquetado gramatical-----------------");
		core.etiquetado_gramatical(s);
		core.name_entity_recognizer(s);
		//core.sentiment_analysis(st);
		//core.coreference(s);
		*/
//		Freeling freeling = new Freeling();
		//System.out.println(freeling.tokenizar(s));
		//System.out.println(freeling.lang_ident(s));
		//freeling.chunk(s);
		/*System.out.println("-----------------Etiquetado gramatical-----------------");
		System.out.println(freeling.etiquetado_gramatical("askadisn"));*/
		/*System.out.println("-----------------NER-----------------");
		freeling.name_entity_recognizer(s);
		System.out.println("-----------------Parser-----------------");
		System.out.println(freeling.parse(s));
		System.out.println("-----------------Sentence detector-----------------");
		System.out.println(freeling.sentence_detect(s));*/
		InterfazUsuario interfaz = new InterfazUsuario();

	}

}
