package Herramientas;




public interface Union{
	
	public String tokenizar(String texto);
	public String chunk(String texto);
	public String parse(String texto);
	public String lang_ident(String texto);
	public String name_entity_recognizer(String texto);
	public String etiquetado_gramatical (String texto);//POS tagging
	public String sentence_detect (String texto);
	public String sentiment_analysis (String texto);
	public String coreference (String texto);
	
}
