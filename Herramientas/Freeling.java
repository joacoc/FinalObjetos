package Herramientas;

import java.util.ArrayList;

import edu.upc.freeling.ChartParser;
import edu.upc.freeling.HmmTagger;
import edu.upc.freeling.LangIdent;
import edu.upc.freeling.ListSentence;
import edu.upc.freeling.ListSentenceIterator;
import edu.upc.freeling.ListWord;
import edu.upc.freeling.ListWordIterator;
import edu.upc.freeling.Maco;
import edu.upc.freeling.MacoOptions;
import edu.upc.freeling.Nec;
import edu.upc.freeling.Ner;
import edu.upc.freeling.Sentence;
import edu.upc.freeling.Splitter;
import edu.upc.freeling.Tokenizer;
import edu.upc.freeling.TreeNode;
import edu.upc.freeling.Word;

public class Freeling extends HerramientaAbs{
	
	public Freeling (){
		System.loadLibrary("freeling_javaAPI");
	}
	

	
	@Override
	public String tokenizar(String texto) {
		String len = this.lang_ident(texto);
		if (!len.equals("es")&& !len.equals("en"))
			return "EL TEXTO DEBE ESTAR EN ESPAÑOL O INGLES PARA ANALIZARLO";
		Tokenizer tk = new Tokenizer("data/Freeling/" + len + "/tokenizer.dat" );
		ListWord l = tk.tokenize(texto);
		String string ="";
		Word w;
		
		ListWordIterator wIt = new ListWordIterator(l);
        while (wIt.hasNext()) {
        	w = wIt.next();
			string = string + w.getForm() + "\n";
		}
		return string;
	}


	 private static void printParseTree( int depth, TreeNode tr, StringBuilder string ) {
		    Word w;
		    TreeNode child;
		    long nch;
		   
		    string.append("\n"); 
		    nch = tr.numChildren();

		    if( nch == 0 ) {
		      w = tr.getInfo().getWord();
		      string.append("(" + w.getForm() + " " + w.getTag());
		      string.append(")");
		    }
		    else
		    {
		      string.append(tr.getInfo().getLabel() + "[");
		      for( int i = 0; i < nch; i++ ) {
		        child = tr.nthChildRef( i );

		        if( child != null ) {
		          printParseTree( depth + 1, child, string );
		        }
		        else {
		          System.err.println( "ERROR: Unexpected NULL child." );
		        }
		      }
		      string.append( "]");
		    }
		    
		    
		   
		  }

	public String chunk(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";
	}

	@Override
	public String parse(String texto) {
		
		String len = this.lang_ident(texto);
		if (!len.equals("es")&& !len.equals("en"))
			return "EL TEXTO DEBE ESTAR EN ESPAÑOL O INGLES PARA ANALIZARLO";
		HmmTagger tg = new HmmTagger("data/Freeling/"+ len+ "/tagger.dat", true, 2 );
		Tokenizer tk = new Tokenizer("data/Freeling/" + len + "/tokenizer.dat" );
		Splitter sp = new Splitter( "data/Freeling/"+ len + "/splitter.dat");
		Maco mf = crearMaco (len);
		ListWord l = tk.tokenize(texto);
		ListSentence ls = sp.split(l, false);
		mf.analyze(ls);
		tg.analyze(ls);
		Nec neclass = new Nec("data/Freeling/" + len+ "/nerc/nec/nec-ab-poor1.dat");
	    ChartParser parser = new ChartParser("data/Freeling/" + len +"/chunker/grammar-chunk.dat" );
		neclass.analyze(ls);
		StringBuilder sb = new StringBuilder();
		parser.analyze(ls);
		ListSentenceIterator sIt = new ListSentenceIterator(ls);
	      while (sIt.hasNext()) {
	    	Sentence s = sIt.next();
	        TreeNode tree = s.getParseTree();
	        printParseTree( 0, tree, sb );
	       
	      }
		return sb.toString();
	}

	@Override
	public String lang_ident(String texto) {
		LangIdent lgid = new LangIdent("data/Freeling/common/lang_ident/ident-few.dat");
		return lgid.identifyLanguage(texto);
		
	}

	@Override
	public String name_entity_recognizer(String texto) {
		
		String len = this.lang_ident(texto);
		if (!len.equals("es")&& !len.equals("en"))
			return "EL TEXTO DEBE ESTAR EN ESPAÑOL O INGLES PARA ANALIZARLO";
		if (len.equals("en"))
			return "LA FUNCIONALIDAD SOLO ESTA DISPONIBLE EN ESPAÑOL";
		Tokenizer tk = new Tokenizer("data/Freeling/" + len + "/tokenizer.dat" );
		Splitter sp = new Splitter( "data/Freeling/"+ len + "/splitter.dat");
		Maco mf = crearMaco (len);
		ListWord l = tk.tokenize(texto);
		ListSentence ls = sp.split(l, false);
		mf.analyze(ls);
		Nec nec = new Nec("data/Freeling/" + len+ "/nerc/nec/nec-ab-rich.dat");
		Ner ner = new Ner ("data/Freeling/" + len+ "/nerc/ner/ner-ab-rich.dat");
		ner.analyze(ls);
		nec.analyze(ls);
		String string ="";
		ListSentenceIterator sIt = new ListSentenceIterator(ls);
	      while (sIt.hasNext()) {
	        Sentence s = sIt.next();
	        ListWordIterator wIt = new ListWordIterator(s);
	        while (wIt.hasNext()) {
	          Word w = wIt.next();
	          if (w.getTag().charAt(0) == 'N' && w.getTag().charAt(1) == 'P')
	          string = string + w.getForm() + " " + w.getLemma() + " " + w.getTag() + "\n"; 
	          
	        }
	      }
		
		return string;
	}

	@Override
	public String etiquetado_gramatical(String texto) {
		
		String len = this.lang_ident(texto);
		if (!len.equals("es")&& !len.equals("en"))
			return "EL TEXTO DEBE ESTAR EN ESPAÑOL O INGLES PARA ANALIZARLO";
		HmmTagger tg = new HmmTagger("data/Freeling/"+ len+ "/tagger.dat", true, 2 );
		Tokenizer tk = new Tokenizer("data/Freeling/" + len + "/tokenizer.dat" );
		Splitter sp = new Splitter( "data/Freeling/"+ len + "/splitter.dat");
		Maco mf = crearMaco (len);
		ListWord l = tk.tokenize(texto);
		ListSentence ls = sp.split(l, false);
		mf.analyze(ls);
		tg.analyze(ls);
		String string ="";
		ListSentenceIterator sIt = new ListSentenceIterator(ls);
	      while (sIt.hasNext()) {
	        Sentence s = sIt.next();
	         ListWordIterator wIt = new ListWordIterator(s);
	        while (wIt.hasNext()) {
	          Word w = wIt.next();
	          string = string + w.getForm() + " " + w.getLemma() + " " + w.getTag() + "\n";
	      }
	   }
		return string;
	      
	      
	}

	@Override
	public String sentence_detect(String texto) {
		
		String len = this.lang_ident(texto);
		if (!len.equals("es") && !len.equals("en"))
			return "EL TEXTO DEBE ESTAR EN ESPAÑOL O INGLES PARA ANALIZARLO";
		Tokenizer tk = new Tokenizer("data/Freeling/" + len + "/tokenizer.dat" );
		Splitter sp = new Splitter( "data/Freeling/"+ len + "/splitter.dat");
		
		ListWord l = tk.tokenize(texto);
		ListSentence ls = sp.split(l, false);
		String string = "";
		ListSentenceIterator sIt = new ListSentenceIterator(ls);
		int i = 1;
	      while (sIt.hasNext()) {
	        Sentence s = sIt.next();
	        ListWordIterator wIt = new ListWordIterator(s);
	        string = string + "\n"+"SENTENCIA " + i + ": ";
	        while (wIt.hasNext()) {
	          Word w = wIt.next();
	          string = string + w.getForm()+ " ";
	      }
	        i++;
	      }
		return string;
	}

	@Override
	public String sentiment_analysis(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";
	}

	@Override
	public String coreference(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";
	}


	
	private Maco crearMaco (String len){
		MacoOptions op = new MacoOptions(len);
		op.setActiveModules(
				 false, op.getAffixAnalysis(),op.getMultiwordsDetection(), op.getNumbersDetection(), true,
				 op.getDatesDetection(), op.getQuantitiesDetection(), true, true, op.getNERecognition(), false );


	    op.setDataFiles(
	      "", 
	      "data/Freeling/"+ len+  "/locucions.dat", 
	      "data/Freeling/"+ len+  "/quantities.dat",
	      "data/Freeling/"+ len+  "/afixos.dat",
	      "data/Freeling/"+ len+ "/probabilitats.dat",
	      "data/Freeling/"+ len+ "/dicc.src",
	      "data/Freeling/"+ len+ "/np.dat",
	      "data/Freeling/common/punct.dat");
	       return new Maco( op );
	}
	
	
	

	
	public double promedio_sentiment_analysis(ArrayList<String> resultados) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
