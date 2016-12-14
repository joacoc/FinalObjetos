package Herramientas;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class StCoreNLP extends HerramientaAbs {
	
	
		public String tokenizar(String texto) {
		
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		List<CoreLabel> lista = tokenizerFactory.getTokenizer(new StringReader(texto)).tokenize();
		String string ="";	  
			  for (CoreLabel cl : lista){
				  string = string + cl.originalText() + "\n";
			  }
			  
		return string;
	}


	public String chunk(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";
		
	}


	
	public String parse(String texto) {
		LexicalizedParser lp = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
		TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
		List<CoreLabel> lista = tokenizerFactory.getTokenizer(new StringReader(texto)).tokenize();
		Tree parse = lp.apply(lista);		
		return parse.pennString();
	      
	}



	public String lang_ident(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";		
	}

	
	public static LinkedHashMap<String, LinkedHashSet<String>> identifyNER(String text, String model) {
	    LinkedHashMap<String, LinkedHashSet<String>> map = new<String, LinkedHashSet<String>> LinkedHashMap();
	    String serializedClassifier = model;
	    CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
	    List<List<CoreLabel>> classify = classifier.classify(text);
	    for (List<CoreLabel> coreLabels : classify) {
	        for (CoreLabel coreLabel : coreLabels) {

	            String word = coreLabel.word();
	            String category = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
	            if (!"O".equals(category)) {
	                if (map.containsKey(category)) {
	                    map.get(category).add(word);
	                } else {
	                    LinkedHashSet<String> temp = new LinkedHashSet<String>();
	                    temp.add(word);
	                    map.put(category, temp);
	                }
	                System.out.println(word + ":" + category);
	            }

	        }

	    }
	    return map;
	}
	
	
	public String name_entity_recognizer (String texto){
		return identifyNER(texto, "edu/stanford/nlp/models/ner/english.conll.4class.distsim.crf.ser.gz").toString();
	}


	public String etiquetado_gramatical(String texto) {
			
		MaxentTagger tagger = new MaxentTagger ("data/StanfordCore/english-bidirectional-distsim.tagger");	
		return tagger.tagString(texto) ;
	
	}


	
	public String sentence_detect(String texto) {
		
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		Annotation document = new Annotation(texto);

		pipeline.annotate(document);

		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		String string ="";
		for(CoreMap sentence: sentences) 
			string = string + sentence.get(TextAnnotation.class);
		
		    
		return string;
	}

	
	public String sentiment_analysis (String texto){
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		Annotation annotation = pipeline.process(texto);
		String[] sentimentText = { "Very Negative","Negative", "Neutral", "Positive", "Very Positive"};
		String string="";
		int i=1;
		for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
		 Tree tree = sentence.get(SentimentAnnotatedTree.class);
		 int score = RNNCoreAnnotations.getPredictedClass(tree);
		 string = string + "SENTENCIA " + i + ": " + sentimentText[score] + "\n";
		 i++;
		}
		return string;
	}
	
	
	
	public String coreference (String texto){
		
		Annotation document = new Annotation(texto);
	    Properties props = new Properties();
	    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,mention,coref");
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    pipeline.annotate(document);
	    String string="";
	    
	    //TODO: A que libreria pertenece? 
	    /*
	    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
	    	string = string + cc + "\n";
	    }
	    */
	    
	    return string;
	  }


	@Override
	public double promedio_sentiment_analysis(ArrayList<String> resultados) {
		double aux = 0;
		double cant = resultados.size();
		

		//Los resultados son de la forma: 
		//
		//		I'm so happy !. 
		//		SENTENCIA 1 : Very Positive
		//		
		//Por lo tanto solo me importa el valor despues de ':', y darle un valor numerico para sacar el promedio.
		
		
		for(String s : resultados){
			cant++;
			if(s.contains("Very Negative")){
				aux--;
			}else
				if(s.contains("Negative")){
					aux = aux - 0.5;
				}else
					if(s.contains("Positive"))
						aux = aux + 0.5;
					else
						if(s.contains("Very Positive"))
							aux++;
		}
		return aux/cant;
	}
		
}
