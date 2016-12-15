package Herramientas;

public interface Interseccion {
	public String tokenizar(String texto);
	public String parse(String texto);
	public String etiquetado_gramatical (String texto);//POS tagging
	public String sentence_detect (String texto);

}
