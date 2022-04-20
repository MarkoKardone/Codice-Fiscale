
import java.io.*;
import java.util.*;
import java.text.*;
public class CodiceFiscale
{
    private String codiceFiscale;
    public static final int USE_SPECIAL=0; 
    
    public CodiceFiscale(String cognome, String nome, boolean sexF, Date data, String citta)
    {
        String cognomeT =cognome(cognome);
        String nomeT =nome(nome);
        String dataT =born(data,sexF);
        String belfT =belfiore(citta);

        
        String codiceFiscale15  = cognomeT+nomeT+dataT+belfT;
        String codiceFiscale=  codiceFiscale15+control_internal_number(codiceFiscale15);

        if (crtl_control_internal_number(codiceFiscale)) 
        {
            this.codiceFiscale = codiceFiscale;
        }
        
    }
    public CodiceFiscale(String cognome, String nome, boolean sexF, Date data, String citta,int special)
    {
        
        String codiceFiscale15  =   cognome(cognome)
                                    +nome(nome)
                                    +born(data, sexF)
                                    +belfiore_2(citta);
        String codiceFiscale=  codiceFiscale15
                        +control_internal_number_2(codiceFiscale15);

        if (crtl_control_internal_number(codiceFiscale)) 
        {
            this.codiceFiscale = codiceFiscale;
        }

        
    }
    
    /*Metodo per ottenere il codice fiscale*/
    public String getCodiceFiscale()
    {
        return codiceFiscale;
    }
    
    /*Metodo per il cognome*/
    private String cognome(String cognome)
    {

        String con = new String();
        String voc = new String();
        String car = new String();

        cognome=cognome.toUpperCase().replaceAll("\\s","").replaceAll("\'","");
         
        for (int i=0;i<cognome.length();i++)
            switch(cognome.charAt(i))
            {
                case'A':
                case'E':
                case'I':
                case'O':
                case'U':voc += cognome.charAt(i);break;
                default:con += cognome.charAt(i); 
            }

        car = con + voc + "XXX";
        
        return car.substring(0,3);
    }
    
    /*Metodo per il nome*/
    private String nome(String nome)
    {

        String con = new String();
        String voc = new String();
        String car = new String();

        nome=nome.toUpperCase().replaceAll("\\s","").replaceAll("\'","");
         
        for (int i=0;i<nome.length();i++)
            switch(nome.charAt(i))
            {
                case'A':
                case'E':
                case'I':
                case'O':
                case'U':voc += nome.charAt(i);break;
                default:con += nome.charAt(i); 
            }
        
        if (con.length() > 3) con = con.substring(0,1) + con.substring(2);

        car = con + voc + "XXX";

        return car.substring(0,3);
    }
    
    /*Metodo per la data*/
    private String born(Date data,boolean sexF)
    {
        
        String code = new String();
        String char_mese ="ABCDEHLMPRST";
        
        SimpleDateFormat data_format = new SimpleDateFormat("yy");
        Calendar calendar = new GregorianCalendar();
        int mese;
        
        calendar.setTime(data);
        mese = calendar.get(Calendar.MONTH);
        int day = (calendar.get(Calendar.DAY_OF_MONTH)+((sexF) ?40:0));

        code  = data_format.format(data);
        code += char_mese.substring(mese,mese+1);
        code += String.format("%02d",day);
        
        return code;
    }

    /*Metodo per il codice Belefiore (senza HashMap) */ 
    private String belfiore(String citta)
    {

        FileReader f;       
        BufferedReader fin; 
        StringTokenizer st;
        String line;     
        
        try
        {
            f = new FileReader("src\\dati.txt");   
            fin = new  BufferedReader(f);
            do
            {
                line = fin.readLine(); 
                st = new StringTokenizer(line,"\t");
                if(st.nextToken().toLowerCase().replaceAll("\\s+","").equals(citta.toLowerCase().replaceAll("\\s+","")))
                { 
                    fin.close();
                    return st.nextToken();
                }
            }
            while(line != null);   
        }
        catch(IOException e){}
        
        return "XXXX";
    }
    
    /*Metodo per il codice Belefiore (con HashMap) */ 
    private String belfiore_2(String citta)
    {

        FileReader f;       
        BufferedReader fin; 
        StringTokenizer st; 
        Map<String,String> city_for_belfiore_code = new HashMap<String,String>(); 
        String line;

        try 
        {
            f = new FileReader("dati.txt");   
            fin = new  BufferedReader(f);
            do
            {
                line = fin.readLine();                  
                st = new StringTokenizer(line,"\t");
                city_for_belfiore_code.put(st.nextToken().toLowerCase().replaceAll("\\s+",""), st.nextToken());
            }
            while(line != null);

            fin.close();
        } 
        catch (Exception e) {}

        return city_for_belfiore_code.get(citta.toLowerCase().replaceAll("\\s+",""));

    }
    
    /*Metodo per il carattere di controllo (senza HashMap) */ 
    private String control_internal_number(String codicefiscale){

        int som=0;
        String caratteri="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String cardispari="BAKPLCQDREVOSFTGUHMINJWZYX";

        codicefiscale=codicefiscale.replace("0", "A");
        codicefiscale=codicefiscale.replace("1", "B");
        codicefiscale=codicefiscale.replace("2", "C");
        codicefiscale=codicefiscale.replace("3", "D");
        codicefiscale=codicefiscale.replace("4", "E");
        codicefiscale=codicefiscale.replace("5", "F");
        codicefiscale=codicefiscale.replace("6", "G");
        codicefiscale=codicefiscale.replace("7", "H");
        codicefiscale=codicefiscale.replace("8", "I");
        codicefiscale=codicefiscale.replace("9", "J");
                
        for (int i = 0;i<codicefiscale.length();i++)
            if (i%2==0)
            {
                som+=cardispari.indexOf(codicefiscale.substring(i,i+1));
            }
            else
            {
                som+=caratteri.indexOf(codicefiscale.substring(i,i+1));
            }
        
        
        som = som%26;

        return caratteri.substring(som,som+1);

    }

    /*Metodo per il carattere di controllo (con HashMap)*/
    private String control_internal_number_2(String codicefiscale){

        
        String caratteri="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";             
        Map<Character,Integer> pari = new HashMap<Character,Integer>();     
        Map<Character,Integer> dispari = new HashMap<Character,Integer>();  
        int[] valori_pari={0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        int[] valori_dispari={1,0,5,7,9,13,15,17,19,21,1,0,5,7,9,13,15,17,19,21,2,4,18,20,11,3,6,8,12,14,16,10,22,25,24,23};
        int som=0;
        
        for (int i = 0; i < caratteri.length(); i++) 
        {
            pari.put(caratteri.charAt(i),valori_pari[i]);
            dispari.put(caratteri.charAt(i),valori_dispari[i]);            
        }
         
        for (int i = 0;i<codicefiscale.length();i++)
            som+=(i%2==0)? dispari.get(codicefiscale.charAt(i)):pari.get(codicefiscale.charAt(i));
        
        som = som%26;

        som += 10;
        return caratteri.substring(som,som+1);
    }

    /*Metodo per il controllo del carattere di controllo*/
    private boolean crtl_control_internal_number(String codiceFiscale) {
        String ctrl_codiceFiscale = codiceFiscale.substring(0,codiceFiscale.length()-1);
        String ctrl_cin = control_internal_number(ctrl_codiceFiscale);
        return ctrl_cin.equals(codiceFiscale.substring(codiceFiscale.length()-1, codiceFiscale.length()));
    }


}