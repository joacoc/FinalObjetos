package Herramientas;




import java.io.IOException;


import java.io.IOException;

import InterfazGrafica.InterfazChatBot;

import InterfazGrafica.InterfazUsuario;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//System.setProperty("java.library.path", "C:/Users/franc/Dropbox/Workspace/TPFinalObjetos");
/*
		String z = "Proclaiming that “the main, most dangerous part of the war is already in the past,” President Petro O. Poroshenko of Ukraine said on Thursday that his office was in constant communication with President Vladimir V. Putin of Russia to sustain a truce in eastern Ukraine, and that Ukraine was now looking ahead to pursue membership in the European Union.";


		String st="Donald Trump is a new president.";

		String s = "Hola mi nombre es Franco. Yo soy de Argentina y juego al basquet.";
		String q = "Hi. My name is Frank. I'm from Argentina. I feel bad. Fuck you. I'm so happy !.";


		//----------------NLTK------------------------------
		
	NLTK nltk = new NLTK();
//		nltk.parse(s);

		System.out.println(nltk.tokenizar("The Stanford NLP Group makes some of our Natural Language Processing software available to everyone! We provide statistical NLP, deep learning NLP, and rule-based NLP tools for major computational linguistics problems, which can be incorporated into applications with human language technology needs. These packages are widely used in industry, academia, and government."
				+ "This code is actively being developed, and we try to answer questions and fix bugs on a best-effort basis."));


//		System.out.println(nltk.sentiment_analysis(q));
		
		//----------------OpenNLP------------------------------


//		System.out.println("OPENNLP: ");
		
		
//		OpenNLP open = new OpenNLP ();
		
//		System.out.println("PARSE: ");
//		System.out.println();
//		open.parse("The Apache OpenNLP library is a machine learning based toolkit for the processing of natural language text. It supports the most common NLP tasks, such as tokenization, sentence segmentation, part-of-speech tagging, named entity extraction, chunking, parsing, and coreference resolution. These tasks are usually required to build more advanced text processing services. OpenNLP also includes maximum entropy and perceptron based machine learning.");
		/*System.out.println();
		System.out.println("ETIQUETADO GRAMATICAL: ");
		System.out.println();
		
		
		System.out.println(open.etiquetado_gramatical(s));
		System.out.println("CHUNK: ");
		System.out.println();
		
		
		System.out.println(open.chunk(s));
		System.out.println("PARSE: ");
		System.out.println();
		//open.parse("The quick brown fox jumps over the lazy dog.");
		System.out.println();
		String op= "Pierre Vinken, 61 years old, will join the board as a nonexecutive director Nov. 29. Mr. Vinken is chairman of Elsevier N.V., the Dutch publishing group. Rudolph Agnew, 55 years old and former chairman of Consolidated Gold Fields PLC, was named a director of this British industrial conglomerate.";
		String o = "John is strong. He's play rugby.";
		System.out.println("NAME FINDER ");
		System.out.println();
		
		System.out.println(open.name_entity_recognizer(s));
		System.out.println("SENTENCE DETECT ");
		System.out.println(open.sentence_detect(s));*/
		

		
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
		core.sentiment_analysis(s);
		//core.coreference(s);
<<<<<<< HEAD
<<<<<<< HEAD
		*/
		/*Freeling freeling = new Freeling();
=======
	*/	
		/*Freeling freeling = new Freeling();

=======
		 
		 
//		Freeling freeling = new Freeling();
>>>>>>> bc108f561e3a0db821e8ad2acdfc9f1f212f9a69
		//System.out.println(freeling.tokenizar(s));
		System.out.println(freeling.lang_ident(s));
		//freeling.chunk(s);
		System.out.println("-----------------Etiquetado gramatical-----------------");
		//System.out.println(freeling.etiquetado_gramatical("askadisn"));
		System.out.println("-----------------NER-----------------");
		System.out.println(freeling.name_entity_recognizer(s));
		System.out.println("-----------------Parser-----------------");
		System.out.println(freeling.parse(s));
		System.out.println("-----------------Sentence detector-----------------");

<<<<<<< HEAD
		//System.out.println(freeling.sentence_detect(s));
=======
		System.out.println(freeling.sentence_detect(s));
>>>>>>> bc108f561e3a0db821e8ad2acdfc9f1f212f9a69
		*/
		
		InterfazUsuario interfaz = new InterfazUsuario();



		//System.out.println(freeling.sentence_detect(s));
//		InterfazUsuario interfaz = new InterfazUsuario();


//		System.out.println(freeling.sentence_detect(s));*/
		
		

	}

}
