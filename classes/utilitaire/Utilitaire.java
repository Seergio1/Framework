package utilitaire;
import java.lang.StringBuffer;

public class Utilitaire {
	
	// Constructor
	public Utilitaire(){}

	public String getContextUrl(StringBuffer url){
        String[] urlSplit = url.toString().split("/");
        String contextUrl ="";
        for (int i = 4;i<urlSplit.length ;i++ ) {
             contextUrl+="/"+urlSplit[i];

        }
        
        return contextUrl;
    }

	
}