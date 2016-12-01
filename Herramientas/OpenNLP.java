package Herramientas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.coref.DefaultLinker;
import opennlp.tools.coref.DiscourseEntity;
import opennlp.tools.coref.Linker;
import opennlp.tools.coref.LinkerMode;
import opennlp.tools.coref.TreebankLinker;
import opennlp.tools.coref.mention.DefaultParse;
import opennlp.tools.coref.mention.Mention;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

public class OpenNLP extends HerramientaAbs {
	// always start with a model, a model is learned from training data !!!!!
		
	@Override
	public String tokenizar(String texto) {
		//ver tema de las excepciones porque me pide tantas
		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-token.bin");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HAY QUE VER ESE ARCHIVO, CARGA EL MODELO A SEGUIR PARA TOKENIZAR 
		TokenizerModel model = null;
		
		try {
			model = new TokenizerModel(is);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		Tokenizer tokenizer = new TokenizerME(model);
	 
		
		String tokens[] = tokenizer.tokenize(texto);
	 
		String string ="";
		for (String a : tokens)
			string = string + a + "\n";
	 
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return string;
		
	}

	@Override
	public String chunk(String texto) {
		POSModel model = new POSModelLoader()
				.load(new File("data/OpenNLP/en-pos-maxent.bin"));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);
	 
		
		ObjectStream<String> lineStream = new PlainTextByLineStream(
				new StringReader(texto));
	 
		perfMon.start();
		String line;
		String whitespaceTokenizerLine[] = null;
	 
		String[] tags = null;
		try {
			while ((line = lineStream.read()) != null) {
				whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
						.tokenize(line);
				tags = tagger.tag(whitespaceTokenizerLine);
 
				POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
				System.out.println(sample.toString());
					perfMon.incrementCounter();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		perfMon.stopAndPrintFinalResult();
	 
		// chunker
		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-chunker.bin");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChunkerModel cModel = null;
		try {
			cModel = new ChunkerModel(is);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		ChunkerME chunkerME = new ChunkerME(cModel);
		String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
	 
		String string ="";
		for (int i=0; i < result.length; i++)
		
			string = string +  whitespaceTokenizerLine[i] + ":  "+ result[i] +"\n";
	  
		Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
		string = string + "\n";
		string = string + "\n";
		for (Span s : span)
			
			string = string + s.toString() + "\n";
			
		return string;
	}

	@Override
	public String parse(String texto) {
		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-parser-chunking.bin");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		ParserModel model = null;
		try {
			model = new ParserModel(is);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		Parser parser = ParserFactory.create(model);
	 
		Parse topParses[] = ParserTool.parseLine(texto, parser, 1);
		
		String string ="";
		
		for (Parse p : topParses){
			string = string + p.getCoveredText() + "\n";
			p.show();
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return string;
	}

	
	@Override
	public String lang_ident(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCIÓN";
		
	}

	@Override
	public String name_entity_recognizer (String texto) {
		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-ner-person.bin");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		TokenNameFinderModel model = null;
		try {
			model = new TokenNameFinderModel(is);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SentenceDetector sentenceDetector = null;
	    InputStream modelS = null;
	     
	    try {
	       modelS = new FileInputStream ("data/OpenNLP/en-sent.bin");
	       SentenceModel sentenceModel = new SentenceModel(modelS);
	       modelS.close();
	       sentenceDetector = new SentenceDetectorME(sentenceModel);
	    }
	    catch (final IOException ioe) {
	           ioe.printStackTrace();
	        }
		
		NameFinderME nameFinder = new NameFinderME(model);
		String sentence[] = sentenceDetector.sentDetect(texto);
		Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
		String name ="";
	    for (int si = 0; si < sentence.length; si++) {
	        Span[] tokenSpans = tokenizer.tokenizePos(sentence[si]);
	        String[] tokens = Span.spansToStrings(tokenSpans, sentence[si]);
	        Span[] names = nameFinder.find(tokens);
	        for (int ni = 0; ni < names.length; ni++) {
	            Span startSpan = tokenSpans[names[ni].getStart()];
	            int nameStart = startSpan.getStart();
	            Span endSpan = tokenSpans[names[ni].getEnd() - 1];
	            int nameEnd = endSpan.getEnd();
	            name = name + sentence[si].substring(nameStart, nameEnd) + "\n";
	            
	        }
	    }
	    return name;
	}

	@Override
	public String etiquetado_gramatical(String texto) {
		
		POSModel model = new POSModelLoader()	
				.load(new File("data/OpenNLP/en-pos-maxent.bin"));
			PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
			POSTaggerME tagger = new POSTaggerME(model);
		 
			ObjectStream<String> lineStream = new PlainTextByLineStream(
					new StringReader(texto));
		 
			perfMon.start();
			String line;
			String string = "";
			try {
				while ((line = lineStream.read()) != null) {
 
					String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
							.tokenize(line);
					String[] tags = tagger.tag(whitespaceTokenizerLine);
 
					POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
					string = string + sample.toString() + "\n";
 
					perfMon.incrementCounter();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			perfMon.stopAndPrintFinalResult();
			return string;
	}

	@Override
	public String sentence_detect(String texto) {
		 

		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-sent.bin");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SentenceModel model = null;
		try {
			model = new SentenceModel(is);
		} catch (InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
	 
		String sentences[] = sdetector.sentDetect(texto);
		String string = ""; 
		for (String s: sentences)
			string = string + s + "\n";
			
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}

	
	
	
	public String coreference (String texto){
		/*
		//-DWNSEARCHDIR= "";
		
		Linker _linker = null;
		 
		try {
		   // coreference resolution linker
		   _linker = new DefaultLinker(
		         // LinkerMode should be TEST
		         //Note: I tried LinkerMode.EVAL for a long time
		         // before realizing that this was the problem
		         "data/OpenNLP", LinkerMode.TEST);
		    
		} catch (final IOException ioe) {
		   ioe.printStackTrace();
		}
		
		Linker treebankLinker = null;
		
		try {
			 treebankLinker = new TreebankLinker("data/OpenNLP",
					LinkerMode.TEST);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//VER DE USAR LOS METODOS DE LA CLASE, NO REPETIR CODIGO
		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-sent.bin");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SentenceModel model = null;
		try {
			model = new SentenceModel(is);
		} catch (InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
	 
		String sentences[] = sdetector.sentDetect(texto);
		
		
		
		
		InputStream isT = null;
		try {
			isT = new FileInputStream("data/OpenNLP/en-token.bin");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HAY QUE VER ESE ARCHIVO, CARGA EL MODELO A SEGUIR PARA TOKENIZAR 
		TokenizerModel modelT = null;
		
		try {
			modelT = new TokenizerModel(isT);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		Tokenizer tokenizer = new TokenizerME(modelT);
	 
		
		String tokens[] = tokenizer.tokenize(texto);
		
		
		InputStream isP = null;
		try {
			isP = new FileInputStream("data/OpenNLP/en-parser-chunking.bin");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		ParserModel modelP = null;
		try {
			modelP = new ParserModel(isP);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		Parser parser = ParserFactory.create(modelP);
	 
		Parse topParses[] = ParserTool.parseLine(texto, parser, 1);
	 
		
		//----------------------------------------------------------------
		
		// tokens should correspond to sentences
		   assert(sentences.length == tokens.length);
		    
		   // list of document mentions
		   final List<Mention> document = new ArrayList<Mention>();
		 
		   for (int i=0; i < topParses.length; i++) {
		      // generate the sentence parse tree
			   
			   Parse parse = topParses [i];
			   //ACA TAMBIEN PONER EL METODO QUE USAMOS ACA, ES PARA PROBAR
		      
		       DefaultParse parseWrapper = new DefaultParse(parse, i);
		       //Mention[] extents = _linker.getMentionFinder().getMentions(parseWrapper);
		       Mention[] extents = treebankLinker.getMentionFinder().getMentions(new
		    		   DefaultParse(parse,i));
		       
		       
		       for (int ei=0,en=extents.length;ei<en;ei++) {
		    	   if (extents[ei].getParse() == null) {
		    	   Parse snp = new Parse(parse.getText(),extents[ei].getSpan(),"NML",1.0,0);
		    	   parse.insert(snp);
		    	   extents[ei].setParse(new DefaultParse(snp, i));
		    	   }
		    	   }
		    	   
		    	   document.addAll(Arrays.asList(extents));
		    	 //i++;
		   }
		      //Note: taken from TreebankParser source...
		     /* for (int ei=0, en=extents.length; ei<en; ei++) {
		         // construct parses for mentions which don't have constituents
		         if (extents[ei].getParse() == null) {
		            // not sure how to get head index, but it doesn't seem to be used at this point
		            final Parse snp = new Parse(parse.getText(), 
		                  extents[ei].getSpan(), "NML", 1.0, 0);
		            parse.insert(snp);
		            // setting a new Parse for the current extent
		            extents[ei].setParse(new DefaultParse(snp, i));
		         }
		      }
		      document.addAll(Arrays.asList(extents));
		   }
		 
		   
		   for (Mention m :document)
			   System.out.println(m.toString());
		   
		   
		   if (!document.isEmpty()) { 
			   DiscourseEntity[] entities =
					   
			   
					   treebankLinker.getEntities(document.toArray(new Mention[document.size()]));
			  //DiscourseEntity [] de = _linker.getEntities(document.toArray(new Mention[0]));    
		      for (DiscourseEntity d : entities){
		    	  			System.out.println(d.toString());
		   }
		   }
		*/
		return "LA HERRAMIENTA NO SOPORTA LA ACCION (VER SI PODEMOS HACERLO ANDAR)";
	}

	@Override
	public String sentiment_analysis(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";
	}
	
	

}
