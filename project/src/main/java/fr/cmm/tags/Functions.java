package fr.cmm.tags;

public class Functions {
    public static String newLineTransformnbr(String phrase){
        String[] returnedPhrase = phrase.split("/n");
        int length = returnedPhrase.length;
        if (phrase.length() >1){
            if (phrase.substring(phrase.length() - 2, phrase.length()) =="/n") {
                returnedPhrase[length-1] += "<br>";
            }
        }
        for(int x = 0; x < length; x = x+1) {
            returnedPhrase[x]="fn:escapeXml(" + returnedPhrase[x] + ")";
        }
        String returnedPhraseConcate = returnedPhrase[0];
        if (length==1) {
            for (int x = 1; x < length; x = x + 1) {
                returnedPhraseConcate += "<br>" + returnedPhrase[x];
            }
        }
        return returnedPhraseConcate;
    }
}
