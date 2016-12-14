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
import opennlp.tools.parser.AbstractBottomUpParser;
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
		
	public String tokenizar(String texto) {
		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-token.bin");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		TokenizerModel model = null;
		
		try {
			model = new TokenizerModel(is);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
			e.printStackTrace();
		}
		
		return string;
		
	}
	
	

	@Override
	public String chunk(String texto) {
		POSModel model = new POSModelLoader()
				.load(new File("data/OpenNLP/en-pos-maxent.bin"));
		POSTaggerME tagger = new POSTaggerME(model);
	 
		
		ObjectStream<String> lineStream = new PlainTextByLineStream(
				new StringReader(texto));
	 
		String line;
		String whitespaceTokenizerLine[] = null;
	 
		String[] tags = null;
		try {
			while ((line = lineStream.read()) != null) {
				whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
						.tokenize(line);
				tags = tagger.tag(whitespaceTokenizerLine);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	 
		// chunker
		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-chunker.bin");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ChunkerModel cModel = null;
		try {
			cModel = new ChunkerModel(is);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
			e.printStackTrace();
		}
		 
		ParserModel model = null;
		try {
			model = new ParserModel(is);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		Parser parser = ParserFactory.create(model);
	 
		Parse topParses[] = ParserTool.parseLine(texto, parser, 1);
		
		StringBuffer sb = new StringBuffer();
		for (Parse p : topParses){
			p.show(sb);
		}
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
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
			e.printStackTrace();
		}
		 
		TokenNameFinderModel model = null;
		try {
			model = new TokenNameFinderModel(is);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException e) {
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
			POSTaggerME tagger = new POSTaggerME(model);
		 
			ObjectStream<String> lineStream = new PlainTextByLineStream(
					new StringReader(texto));
			String line;
			String string = "";
			try {
				while ((line = lineStream.read()) != null) {
 
					String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
							.tokenize(line);
					String[] tags = tagger.tag(whitespaceTokenizerLine);
 
					POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
					string = string + sample.toString() + "\n";
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return string;
	}

	@Override
	public String sentence_detect(String texto) {
		 

		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-sent.bin");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		SentenceModel model = null;
		try {
			model = new SentenceModel(is);
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
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
			e.printStackTrace();
		}
		return string;
	}

	public String coreference (String texto){
		
		Linker linker = null;
		
		try {
			 linker = new DefaultLinker("data/OpenNLP",
					LinkerMode.TEST);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		InputStream is = null;
		try {
			is = new FileInputStream("data/OpenNLP/en-sent.bin");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		SentenceModel model = null;
		try {
			model = new SentenceModel(is);
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		String sentences[] = sdetector.sentDetect(texto);
		InputStream isT = null;
		try {
			isT = new FileInputStream("data/OpenNLP/en-token.bin");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		TokenizerModel modelT = null;
		try {
			modelT = new TokenizerModel(isT);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Tokenizer tokenizer = new TokenizerME(modelT);
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		Parser parser = ParserFactory.create(modelP);
		
		   final List<Mention> document = new ArrayList<Mention>();
		 
		   for (int i=0; i < sentences.length; i++) {
			   Parse p = new Parse(sentences[i],
				         new Span(0, sentences[i].length()),
				         AbstractBottomUpParser.INC_NODE,
				         1, 0);
			   Span[] spans = tokenizer.tokenizePos(sentences [i]);
				for (int idx=0; idx < spans.length; idx++) {
				      final Span span = spans[idx]; 
				      p.insert(new Parse(sentences [i],
				            span,
				            AbstractBottomUpParser.TOK_NODE, 
				            0,
				            idx));
				   }
				
				Parse parse = parser.parse(p);
		       Mention[] extents = linker.getMentionFinder().getMentions(new
		    		   DefaultParse(parse,i));
		       
		       /*for (int ei=0,en=extents.length;ei<en;ei++) {
		    	   if (extents[ei].getParse() == null) {
		    	   Parse snp = new Parse(parse.getText(),extents[ei].getSpan(),"NML",1.0,0);
		    	   parse.insert(snp);
		    	   extents[ei].setParse(new DefaultParse(snp, i));
		    	   }
		    	   } */
		    	   document.addAll(Arrays.asList(extents));
		   }
		   
		   String string="";
		   /*for (Mention m :document)
			   string = string + m.toString() + "\n";*/
		   
		   if (!document.isEmpty()) { 
			  DiscourseEntity [] de = linker.getEntities(document.toArray(new Mention[0]));
		      for (DiscourseEntity d : de){
		    	  string = string + d.toString() + "\n";
		    	  			
		   }
		   }
		
		return string;
	}

	@Override
	public String sentiment_analysis(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";
	}

	@Override
	public double promedio_sentiment_analysis(ArrayList<String> resultados) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
