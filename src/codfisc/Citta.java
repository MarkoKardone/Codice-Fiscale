package codfisc;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;

public class Citta
{

    private JSONParser parser = new JSONParser();
    private JSONObject element,singleELement;
    private String citta;

    private String instat,cap,prefisso,provincia,regione,belfiore,abitanti;

    public Citta(String citta) 
    {
        this.citta = citta.toLowerCase().replaceAll("\\s","");
        instat = doIstat();
        cap = doCap();
        prefisso = doPrefisso();
        provincia = doProvincia();
        regione = doRegione();
        belfiore = doBelfiore();
        abitanti = doAbitanti(); 
    }
    
    private String doIstat() 
    {
        try (Reader reader = new FileReader("src\\dati.json"))
        {
            element = (JSONObject) parser.parse(reader);
            singleELement = (JSONObject) element.get(citta);
            return (String) singleELement.get("istat");
        }
        catch (Exception e) 
        {
            System.err.println(e.getLocalizedMessage());
        }
        return "";       
    }
    private String doCap() 
    {
        try (Reader reader = new FileReader("src\\dati.json"))
        {
            element = (JSONObject) parser.parse(reader);
            singleELement = (JSONObject) element.get(citta);
            return (String) singleELement.get("cap");
        }
        catch (Exception e) 
        {
            System.err.println(e.getLocalizedMessage());
        }
        return "";       
    }
    private String doPrefisso() 
    {
        try (Reader reader = new FileReader("src\\dati.json"))
        {
            element = (JSONObject) parser.parse(reader);
            singleELement = (JSONObject) element.get(citta);
            return (String) singleELement.get("prefisso");
        }
        catch (Exception e)
        {
            System.err.println(e.getLocalizedMessage());
        }
        return "";       
    }
    private String doProvincia() 
    {
        try (Reader reader = new FileReader("src\\dati.json"))
        {
            element = (JSONObject) parser.parse(reader);
            singleELement = (JSONObject) element.get(citta);
            return (String) singleELement.get("provincia");
        }
        catch (Exception e) 
        {
            System.err.println(e.getLocalizedMessage());
        }
        return "";       
    }
    private String doRegione() 
    {
        try (Reader reader = new FileReader("src\\dati.json"))
        {
            element = (JSONObject) parser.parse(reader);
            singleELement = (JSONObject) element.get(citta);
            return (String) singleELement.get("regione");
        }
        catch (Exception e) 
        {
            System.err.println(e.getLocalizedMessage());
        }
        return "";       
    }
    private String doBelfiore() 
    {
        try (Reader reader = new FileReader("src\\dati.json"))
        {
            element = (JSONObject) parser.parse(reader);
            singleELement = (JSONObject) element.get(citta);
            return (String) singleELement.get("codfisco");
        }
        catch (Exception e) 
        {
            System.err.println(e.getLocalizedMessage());
        }
        return "";       
    }
    private String doAbitanti() 
    {
        try (Reader reader = new FileReader("src\\dati.json"))
        {
            element = (JSONObject) parser.parse(reader);
            singleELement = (JSONObject) element.get(citta);
            return (String) singleELement.get("abitanti");
        }
        catch (Exception e) 
        {
            System.err.println(e.getLocalizedMessage());
        }
        return "";       
    }
    
    public String getIstat(){return instat;}
    public String getCap(){return cap;}
    public String getPrefisso(){return prefisso;}
    public String getProvincia(){return provincia;}
    public String getRegione(){return regione;}
    public String getBelfiore(){return belfiore;}
    public String getAbitanti(){return abitanti;}


}