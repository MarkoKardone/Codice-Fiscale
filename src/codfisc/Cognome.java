package codfisc;

public class Cognome 
{
    
    private String cognome;

    public Cognome(String cognome) 
    {
        cognome = cognome.replaceAll("\\s","").replaceAll("\'", "").replaceAll("è","");
        this.cognome = doCognome(cognome);
    }

    private String doCognome(String cognome) 
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
        
        car = car.substring(0,3);
        return car;
    }
    
    public String getCognome() {return cognome;}
}
