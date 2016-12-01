package Herramientas;



public abstract class HerramientaAbs implements Union {

	
	public abstract String tokenizar(String texto);


	public abstract String chunk(String texto);

	
	public abstract String parse (String texto);
	
		


	public abstract String lang_ident(String texto);

	
	public abstract String name_entity_recognizer (String texto);


	public abstract String etiquetado_gramatical(String texto);


	@Override
	public abstract String sentence_detect(String texto);

	@Override
	public abstract String sentiment_analysis(String texto);


	@Override
	public abstract String coreference(String texto);
}
