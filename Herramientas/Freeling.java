package Herramientas;

import edu.upc.freeling.ChartParser;
import edu.upc.freeling.Depnode;
import edu.upc.freeling.HmmTagger;
import edu.upc.freeling.LangIdent;
import edu.upc.freeling.ListSentence;
import edu.upc.freeling.ListSentenceIterator;
import edu.upc.freeling.ListWord;
import edu.upc.freeling.ListWordIterator;
import edu.upc.freeling.Maco;
import edu.upc.freeling.MacoOptions;
import edu.upc.freeling.Nec;
import edu.upc.freeling.Senses;
import edu.upc.freeling.Sentence;
import edu.upc.freeling.Splitter;
import edu.upc.freeling.Tokenizer;
import edu.upc.freeling.TreeDepnode;
import edu.upc.freeling.TreeNode;
import edu.upc.freeling.Ukb;
import edu.upc.freeling.VectorWord;
import edu.upc.freeling.Word;

public class Freeling extends HerramientaAbs{
//PREGUNTAR SI CREO TODOS LOS OBJETOS DE UNA O A MEDIDA QUE SE LLAMAN LOS METODOS (CREAR DE MAS O REPETIR CODIGO ?), ACLARAR QUE TAMBIEN PUEDEN PONER FRASES EN DISTINTOS IDIOMAS
	public Freeling (){
		System.loadLibrary("freeling_javaAPI");
	}
	
	@Override
	public String tokenizar(String texto) {
		LangIdent lgid = new LangIdent("data/Freeling/common/lang_ident/ident-few.dat");
		String len = lgid.identifyLanguage(texto);
		if (len != "es" || len != "en")
			return "NO SE PUDO RECONOCER EL LENGUAJE, INTENTE NUEVAMENTE";
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

	

	  private static void printSenses( Word w ) {
	    String ss = w.getSensesString();

	    // The senses for a FreeLing word are a list of
	    // pair<string,double> (sense and page rank). From java, we
	    // have to get them as a string with format
	    // sense:rank/sense:rank/sense:rank
	    // which will have to be splitted to obtain the info.
	    //
	    // Here, we just output it:
	    System.out.print( " " + ss );
	  }

	 private static void printParseTree( int depth, TreeNode tr, StringBuilder string ) {
		    Word w;
		    TreeNode child;
		    long nch;
		   
		    string.append("\n");
		    
		    for( int i = 0; i < depth; i++ ) {
		    		string.append(" "); 
		    }

		    nch = tr.numChildren();

		    if( nch == 0 ) {
		      w = tr.getInfo().getWord();
		      string.append("(" + w.getForm() + " " + /*w.getLemma() + " " +*/ w.getTag());
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
		      for( int i = 0; i < depth; i++ ) {
		        string.append("  ");
		      }
		      string.append( "]");
		    }
		   
		  }

		  private static void printDepTree( int depth, TreeDepnode tr ) {
		    TreeDepnode child = null;
		    TreeDepnode fchild = null;
		    Depnode childnode;
		    long nch;
		    int last, min;
		    Boolean trob;

		    for( int i = 0; i < depth; i++ ) {
		      System.out.print( "  " );
		    }

		    System.out.print(
		      tr.getInfo().getLinkRef().getInfo().getLabel() + "/" +
		      tr.getInfo().getLabel() + "/" );

		    Word w = tr.getInfo().getWord();

		    System.out.print(
		      "(" + w.getForm() + " " + w.getLemma() + " " + w.getTag() );
		    printSenses( w );
		    System.out.print( ")" );

		    nch = tr.numChildren();

		    if( nch > 0 ) {
		      System.out.println( " [" );

		      for( int i = 0; i < nch; i++ ) {
		        child = tr.nthChildRef( i );

		        if( child != null ) {
		          if( !child.getInfo().isChunk() ) {
		            printDepTree( depth + 1, child );
		          }
		        }
		        else {
		          System.err.println( "ERROR: Unexpected NULL child." );
		        }
		      }

		      // Print chunks (in order)
		      last = 0;
		      trob = true;

		      // While an unprinted chunk is found, look for the one with lower
		      // chunk_ord value.
		      while( trob ) {
		        trob = false;
		        min = 9999;

		        for( int i = 0; i < nch; i++ ) {
		          child = tr.nthChildRef( i );
		          childnode = child.getInfo();

		          if( childnode.isChunk() ) {
		            if( (childnode.getChunkOrd() > last) &&
		                (childnode.getChunkOrd() < min) ) {
		              min = childnode.getChunkOrd();
		              fchild = child;
		              trob = true;
		            }
		          }
		        }
		        if( trob && (child != null) ) {
		          printDepTree( depth + 1, fchild );
		        }

		        last = min;
		      }

		      for( int i = 0; i < depth; i++ ) {
		        System.out.print( "  " );
		      }

		      System.out.print( "]" );
		    }

		    System.out.println( "" );
		  }
		
	public String chunk(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";
	}

	@Override
	public String parse(String texto) {
		LangIdent lgid = new LangIdent("data/Freeling/common/lang_ident/ident-few.dat");
		String len = lgid.identifyLanguage(texto);
		if (len != "es" || len != "en")
			return "NO SE PUDO RECONOCER EL LENGUAJE, INTENTE NUEVAMENTE";
		HmmTagger tg = new HmmTagger("data/Freeling/"+ len+ "/tagger.dat", true, 2 );
		Tokenizer tk = new Tokenizer("data/Freeling/" + len + "/tokenizer.dat" );
		Splitter sp = new Splitter( "data/Freeling/"+ len + "/splitter.dat");
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
	    Maco mf = new Maco( op );
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
		switch (lgid.identifyLanguage(texto)) {
   	 
        case "de":
        	return "Alemán";
        
        case "en":
        	return "Inglés";
        	
       
        case "es":
        	return "Español";
        
        case "fr":
        	return "Francés";
       
        
        case "pt":
        	return "Portugués";
 
		}
		return "No se pudo detectar el idioma";
		
	}

	@Override
	public String name_entity_recognizer(String texto) {
		LangIdent lgid = new LangIdent("data/Freeling/common/lang_ident/ident-few.dat");
		String len = lgid.identifyLanguage(texto);
		if (len != "es" || len != "en")
			return "NO SE PUDO RECONOCER EL LENGUAJE, INTENTE NUEVAMENTE";
		HmmTagger tg = new HmmTagger("data/Freeling/"+ len+ "/tagger.dat", true, 2 );
		Tokenizer tk = new Tokenizer("data/Freeling/" + len + "/tokenizer.dat" );
		Splitter sp = new Splitter( "data/Freeling/"+ len + "/splitter.dat");
		MacoOptions op = new MacoOptions(len);

	    /*op.setActiveModules(false, true, true, true, 
	                               true, true, true, 
	                               true, true, true);*/
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
	    Maco mf = new Maco( op );
		ListWord l = tk.tokenize(texto);
		ListSentence ls = sp.split(l, false);
		mf.analyze(ls);
		tg.analyze(ls);
		Nec neclass = new Nec("data/Freeling/" + len+ "/nerc/nec/nec-ab-poor1.dat");
		Senses sen = new Senses("data/Freeling/" + len+"/senses.dat" ); // sense dictionary
	    Ukb dis = new Ukb("data/Freeling/" + len+ "/ukb.dat" ); // sense disambiguator
		neclass.analyze(ls);
		sen.analyze( ls );
	    dis.analyze( ls );
		String string ="";
		ListSentenceIterator sIt = new ListSentenceIterator(ls);
	      while (sIt.hasNext()) {
	        Sentence s = sIt.next();
	        ListWordIterator wIt = new ListWordIterator(s);
	        while (wIt.hasNext()) {
	          Word w = wIt.next();
	          string = string + w.getForm() + " " + w.getLemma() + " " + w.getTag() + "\n"; 
	          /*System.out.print(
	            w.getForm() + " " + w.getLemma() + " " + w.getTag() );
	          printSenses( w );
	          System.out.println();*/
	        }

	       // System.out.println();
	      }
		
		return string;
	}

	@Override
	public String etiquetado_gramatical(String texto) {
		LangIdent lgid = new LangIdent("data/Freeling/common/lang_ident/ident-few.dat");
		String len = lgid.identifyLanguage(texto);
		if (len != "es" || len != "en")
			return "NO SE PUDO RECONOCER EL LENGUAJE, INTENTE NUEVAMENTE";
		HmmTagger tg = new HmmTagger("data/Freeling/"+ len+ "/tagger.dat", true, 2 );
		Tokenizer tk = new Tokenizer("data/Freeling/" + len + "/tokenizer.dat" );
		Splitter sp = new Splitter( "data/Freeling/"+ len + "/splitter.dat");
		MacoOptions op = new MacoOptions(len);

	    /*op.setActiveModules(false, true, true, true, 
	                               true, true, true, 
	                               true, true, true);*/
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
	    Maco mf = new Maco( op );
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
		
		LangIdent lgid = new LangIdent("data/Freeling/common/lang_ident/ident-few.dat");
		String len = lgid.identifyLanguage(texto);
		if (len != "es" || len != "en")
			return "NO SE PUDO RECONOCER EL LENGUAJE, INTENTE NUEVAMENTE";
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
		return null;
	}

	
	
	
	
	
	
}
