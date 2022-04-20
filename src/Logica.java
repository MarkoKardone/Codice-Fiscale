
import java.awt.event.*;
import java.util.Date;

import javax.swing.*;

public class Logica implements ActionListener 
{

    JTextField[] texts;
    JRadioButton sex;
    JSpinner data;

    String nome;
    String cognome;
    Date datan;
    boolean sexF;
    String citta;

    boolean isRun = false;
    
    public Logica(JTextField[] texts,JSpinner data)
    {
        this.texts=texts;
        this.data=data;
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        CodiceFiscale codiceFiscale;

        if(e.getActionCommand().equals("Maschio") && !isRun)
        {
            sexF = false;
            return;
        }
        if(e.getActionCommand().equals("Femmina") && !isRun)
        {
            sexF = true;
            return;
        }
        if(e.getActionCommand().equals("Run") && !isRun)
        {
            cognome = texts[1].getText();
            nome = texts[0].getText();
            datan = (Date) data.getValue();
            citta = texts[2].getText();
            
            isRun = true;
            codiceFiscale = new CodiceFiscale(cognome, nome, sexF, datan, citta);
            texts[3].setText(codiceFiscale.getCodiceFiscale());
            isRun = false;
            return; 
        }
    }
    
}
