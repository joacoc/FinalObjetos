package Herramientas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NLTK extends HerramientaAbs {

	//NLTK esta compilado en python, por lo tanto 
	//	se tiene que ejecutar utilizando un interprete de python
	//	y correr el codigo necesario utilizando el interprete.
	
	public StringBuilder correrCodigo(String codigo){
		StringBuilder stringBuilder = null;
		
        try{
        	BufferedWriter out = new BufferedWriter(new FileWriter("nltkCode.py"));
        	out.write(codigo);
        	out.close();
        	
        	Process p = Runtime.getRuntime().exec("python nltkCode.py");
        	BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        	
        	stringBuilder = new StringBuilder();
        	String linea = in.readLine();    
        	
        	while(linea != null){
        		stringBuilder.append(linea+"\n");
        		linea = in.readLine();
        	}
        	
        	
    	}catch(Exception e){
        		e.printStackTrace();
    	}
        
		return stringBuilder;
	}
	
	@Override
	public String tokenizar(String texto) {
		
		//Remuevo todos los saltos de linea
		//Sino cuando se ejecuta en python se corre el codigo y el interprete no lo detecta.
		texto = texto.replace("\n", "").replace("\r", "");
    	
    	String codigo = "import nltk\n"
				+"from nltk.tokenize import word_tokenize\n"
				+"tokens = word_tokenize( \" " +texto +" \" ) \n"
				+"print(tokens) \n";
    	
    	return correrCodigo(codigo).toString();
   	}

	@Override
	public String chunk(String texto) {
		
		texto = texto.replace("\n", "").replace("\r", "");
    	
    	String codigo = "import nltk\n"
				+"from nltk.tokenize import word_tokenize\n"
				+"tokens = word_tokenize( \" " +texto+" \" ) \n"
    			+"grammar = r\"\"\" \n"
    			+"NP: \n"
    			+"\t{<.*>+} \n"
    			+"\t\"\"\" \n"
    			+"cp = nltk.RegexpParser(grammar)\n"
    			+"tagged_tokens = nltk.pos_tag(tokens)\n"
    			+"print (cp.parse(tagged_tokens))";
    	
    	return correrCodigo(codigo).toString();
	}

	@Override
	public String parse(String texto) {
		
		//TODO: Para parsear en nltk hay que darle una configuracion de la gramatica 
		//		http://www.nltk.org/howto/parse.html
		//  	Esta configuracion se puede cargar por archivo o por texto 
		// 		Metodos: nltk.CFG.fromstring(""") o nltk.data.load('file:mygrammar.cfg')
		// 		http://www.nltk.org/book/ch08.html
		//		Hay algunas disponibles por nltk. 
		texto = texto.replace("\n", "").replace("\r", "");
    	
    	String codigo = "import nltk\n"
    			+"from nltk.tokenize import word_tokenize\n"
				+"from nltk.parse import RecursiveDescentParser\n"
				+"tokens = word_tokenize( \" " +texto +" \" ) \n"
				+"sentence = "+"\"" +texto +"\".split() \n" 
    			+"grammar = r\"\"\" \n"
    			+"NP: \n"
    			+"\t{<.*>+} \n"
    			+"\t\"\"\" \n"
    			+"cp = nltk.RegexpParser(grammar)\n"
				+"rd = RecursiveDescentParser(grammar)\n"
    			+"for t in rd.parse(sentence)\n"
				+"\t print(t)\n";
    	
    	System.out.println(correrCodigo(codigo));
    	
    	return "Actualmente no disponible.";
//    	return correrCodigo(codigo).toString();
	}

	@Override
	public String lang_ident(String texto) {
		
		//Link:
		//http://blog.alejandronolla.com/2013/05/15/detecting-text-language-with-python-and-nltk/
		texto = texto.replace("\n", "").replace("\r", "");
    	
    	String codigo = "import nltk\n"
    			+"from nltk.tokenize import word_tokenize \n"
				+"from nltk.corpus import stopwords \n"
    			+"languages_ratios = {} \n"
				+"tokens = word_tokenize( \" " +texto +" \" ) \n"
    			+"words = [word.lower() for word in tokens] \n"
    			+"for language in stopwords.fileids(): \n"
    			+"\t stopwords_set = set(stopwords.words(language)) \n"
    			+"\t words_set = set(words) \n"
    			+"\t common_elements = words_set.intersection(stopwords_set) \n"
    			+"\t languages_ratios[language] = len(common_elements) # language \"score\" \n"
    			+"most_rated_language = max(languages_ratios, key=languages_ratios.get)\n"
    			+"print(most_rated_language)";
    	
    	return correrCodigo(codigo).toString();
		
	}

	@Override
	public String name_entity_recognizer (String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";
	}

	@Override
	public String etiquetado_gramatical(String texto) {
		//Primero se tokeniza 
		// y despues se obtiene los tag de cada token.
		texto = texto.replace("\n", "").replace("\r", "");
		
    	String codigo = "import nltk\n"
    			+"from nltk.tokenize import word_tokenize\n"
				+"from nltk.parse import RecursiveDescentParser\n"
				+"tokens = word_tokenize( \" " +texto +" \" ) \n"
    			+"print(nltk.pos_tag(tokens))";
		
    	return correrCodigo(codigo).toString();
	}

	@Override
	public String sentence_detect(String texto) {
		texto = texto.replace("\n", "").replace("\r", "");
    	
    	String codigo = "import nltk\n"
    			+"sent_tokenizer = nltk.tokenize.PunktSentenceTokenizer()\n"	
				+"sent_tokenizer = sent_tokenizer.tokenize( \" " +texto +" \" )\n"
				+"print(sent_tokenizer)" ;
    	
    	return correrCodigo(codigo).toString();
	}
	
	public String sentiment_analysis(String texto){
		//	Obtiene el positivismo de cada una de las sentencias dentro de un texto
		//	http://www.nltk.org/howto/sentiment.html
		//	es necesario bajarse el VaderLexicon desde nltk.download()
		
		//TODO:
		//	Primero hay que chequear el idioma del texto, si esta en espanol hay que traducirlo al ingles
		
//		String codigo_traduccion = "import goslate\n"
//							+"gs = goslate.Goslate()\n"
//							+"print(gs.translate('"+texto+"','en'))";
		
//		StringBuilder traduccion = correrCodigo(codigo_traduccion);
		
//		if(traduccion.toString().length()>0){
		texto = texto.replace("\n", "").replace("\r", "");
    	
		if(texto.length()>0){
			String codigo = "import nltk\n"
					+"from nltk.sentiment.vader import SentimentIntensityAnalyzer\n"
					+"from nltk import tokenize\n"
//					+"sentences = tokenize.sent_tokenize( \" " +traduccion.toString() +" \" ) \n"
					+"sentences = tokenize.sent_tokenize( \" " +texto +" \" ) \n"
					+"sid = SentimentIntensityAnalyzer()\n"
					+"for sentence in sentences:\n"
					+"\tprint(sentence)\n"
					+"\tss = sid.polarity_scores(sentence)\n"
					+"\tfor k in sorted(ss):\n"
					+"\t\tprint('{0}: {1}, '.format(k, ss[k]), end='')\n"
					+"\tprint()";
			
		
			return correrCodigo(codigo).toString();	
		}else
			return "0";
	}
	
	@Override
	public String coreference(String texto){
		return "LA HERRAMIENTA NO SOPORTA LA ACCION"; 
	}

	public double promedio_sentiment_analysis(ArrayList<String> resultados) {
		double aux = 0;
		double cant = resultados.size();
		
		//Los resultados son de la forma: 
		//
		//		I'm so happy !. 
		//		compound: 0.6468, neg: 0.0, neu: 0.412, pos: 0.588.
		//		
		//Por lo tanto solo me importa el compound, que es el "promedio" de los resultados.
		
		for(String s : resultados){
			
			//Remuevo la primer oracion.
			s = s.substring(s.indexOf('\n')+1);
			
			//Remuevo la palabra"compbound".
			s = s.substring(9,s.length()-1);
			
			StringBuilder s_aux = new StringBuilder();
			
			for(int i = 0; i < s.length(); i++){
				char c = s.charAt(i);
				//Termine de leer el valor
				if(s.charAt(i)==',')
					break;
				else{
					s_aux.append(String.valueOf(c));
				}
			}
			//Obtengo el valor del compbound limpio
			aux = Double.valueOf(s_aux.toString());
		}
		System.out.println(aux+"Cant:"+cant);
		return aux/cant;
	}
	
}
