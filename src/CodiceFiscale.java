import java.util.*;

import codfisc.*;

public class CodiceFiscale
{
    private String codiceFiscale;
    
    
    public CodiceFiscale(String cognome, String nome, boolean f, Date data, String citta)
    {
        Cognome c = new Cognome(cognome);
        Nome n = new Nome(nome);
        Data d = new Data(data, f);
        Citta ct = new Citta(citta);
        codiceFiscale = doCodiceFiscale(c.getCognome()+n.getNome()+d.getData()+ct.getBelfiore());

    }
    
    public String getCodiceFiscale(){return codiceFiscale;}
    
    /*Metodo per il carattere di controllo (senza HashMap) */ 
    private String doCodiceFiscale(String cf){

        int som=0;

        String caratteri="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cardispari="BAKPLCQDREVOSFTGUHMINJWZYX";
        String num = "0123456789";
        String codiceFiscale = cf;

        for (int i = 0; i < num.length(); i++) 
        {
            cf = cf.replaceAll(String.valueOf(num.charAt(i)),String.valueOf(caratteri.charAt(i)));
        }

        for (int i = 0;i<cf.length();i++)
            if (i%2==0)
            {
                som+=cardispari.indexOf(cf.substring(i,i+1));
            }
            else
            {
                som+=caratteri.indexOf(cf.substring(i,i+1));
            }
        
        
        som = som%26;

        return codiceFiscale+caratteri.substring(som,som+1);

    }
}