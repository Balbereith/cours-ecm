package fr.cmm.tags;

public class Functions {
    public static String newLineTransformnbr(String phrase){
        String returnedPhrase = phrase.replace("/n", "<br>");
        return returnedPhrase;
    }
}
