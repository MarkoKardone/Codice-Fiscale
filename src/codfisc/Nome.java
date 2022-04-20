package codfisc;
public class Nome 
{
    
    private String nome;

    public Nome(String nome) 
    {
        nome = nome.replaceAll("\\s","").replaceAll("\'", "").replaceAll("è","");
        this.nome = doNome(nome);   
    }
    
    private String doNome(String nome) {

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
    
    public String getNome() {return nome;}
}