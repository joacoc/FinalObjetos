package Herramientas;

import edu.upc.freeling.ListWord;
import edu.upc.freeling.ListWordIterator;
import edu.upc.freeling.Tokenizer;
import edu.upc.freeling.Word;

public class Freeling extends HerramientaAbs{

	public Freeling (){
		System.loadLibrary("freeling_javaAPI");
	}
	
	@Override
	public String tokenizar(String texto) {
		Tokenizer tk = new Tokenizer("data/Freeling/tokenizer.dat" );
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

	@Override
	public String chunk(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String parse(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String lang_ident(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name_entity_recognizer(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String etiquetado_gramatical(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sentence_detect(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sentiment_analysis(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String coreference(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	
	
}
