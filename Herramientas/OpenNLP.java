package Herramientas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

public class OpenNLP extends HerramientaAbs {
		
	private Tokenizer tokenizer;
	private ChunkerME chunkerME;
	private SentenceDetectorME sdetector;
	private Parser parser;
	private NameFinderME nameFinder;
	private POSTaggerME tagger;
	
	
	public OpenNLP (){
		//-----------TOKENIZER--------------
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
		this.tokenizer = new TokenizerME(model);
		
		//-----------------CHUNKER-------------------
		
		InputStream isC = null;
		try {
			isC = new FileInputStream("data/OpenNLP/en-chunker.bin");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ChunkerModel cModel = null;
		try {
			cModel = new ChunkerModel(isC);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.chunkerME = new ChunkerME(cModel);
		
		//-------------POSTAGGER---------------------
		POSModel modelP = new POSModelLoader()
				.load(new File("data/OpenNLP/en-pos-maxent.bin"));
		this.tagger = new POSTaggerME(modelP);
		
		
		//--------------PARSER-----------------------
		InputStream isPA = null;
		try {
			isPA = new FileInputStream("data/OpenNLP/en-parser-chunking.bin");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 
		ParserModel modelPA = null;
		try {
			modelPA = new ParserModel(isPA);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.parser = ParserFactory.create(modelPA);
		
		//----------------SENTENCE DETECTOR------------------------
		
		InputStream isS = null;
		try {
			isS = new FileInputStream("data/OpenNLP/en-sent.bin");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		SentenceModel modelS = null;
		try {
			modelS = new SentenceModel(isS);
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.sdetector = new SentenceDetectorME(modelS);
		
		//--------------NAMEFINDER------------------------
		
		InputStream isN = null;
		try {
			isN = new FileInputStream("data/OpenNLP/en-ner-person.bin");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 
		TokenNameFinderModel modelN = null;
		try {
			modelN = new TokenNameFinderModel(isN);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.nameFinder = new NameFinderME(modelN);
		
	}
	
	
 	public String tokenizar(String texto) {
		
		String tokens[] = tokenizer.tokenize(texto);
		String string ="";
		for (String a : tokens)
			string = string + a + "\n";
		return string;
		
	}
	
	

	@Override
	public String chunk(String texto) {
		
		String tokens[] = tokenizer.tokenize(texto);
		String[] tags = tagger.tag (tokens);
		String result[] = chunkerME.chunk(tokens, tags);
		String string ="";
		for (int i=0; i < result.length; i++)
			string = string +  tokens[i] + ":  "+ result[i] +"\n";
		Span[] span = chunkerME.chunkAsSpans(tokens, tags);
		string = string + "\n";
		string = string + "\n";
		for (Span s : span)
			string = string + s.toString() + "\n";
		return string;
	}

	@Override
	public String parse(String texto) {
		
		String sentences[] = sdetector.sentDetect(texto);
		Parse topParses[] = null;
		StringBuffer sb = new StringBuffer();
		for (String s : sentences){
			topParses = ParserTool.parseLine(s, parser, 1);
		for (Parse p : topParses){
			p.show(sb);
			sb.append("\n");
			}
		}		
		return sb.toString();
		}

	
	@Override
	public String lang_ident(String texto) {
		return "LA HERRAMIENTA NO SOPORTA LA ACCIÓN";
		
	}

	@Override
	public String name_entity_recognizer (String texto) {
		
		String sentence[] = sdetector.sentDetect(texto);
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

		String sentences[] = sdetector.sentDetect(texto);
		String string = ""; 
		for (String s: sentences)
			string = string + s + "\n";
		return string;
	}

	public String coreference (String texto){
		return "LA HERRAMIENTA NO SOPORTA LA ACCION";
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
