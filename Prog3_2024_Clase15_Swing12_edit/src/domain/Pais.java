package domain;

public enum Pais {
	ESPAÑA, ARGENTINA, BRASIL
}

/*
 * CONVERSIONES DE UNA ENUMERACIÓN
 * 
 * Convertir un String a enum
 * --------------------------
 * 
 * "ESPAÑA" -> A enum ESPAÑA
 * 
 * Pais p = Pais.valueOf("ESPAÑA");
 * 
 * 
 * Convertir de enumeración a String
 * ---------------------------------
 * 
 * Enum ESPAÑA -> "ESPAÑA"
 * 
 * Pais p = Pais.ESPAÑA;
 * String s = p.toString();
 * 
 * */