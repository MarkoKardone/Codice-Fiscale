import javax.swing.*;
import javax.swing.JSpinner.*;
import java.awt.*;
import java.util.*;
public class Finestra
{
    JFrame f = new JFrame("CodiceFiscale");   
    
    HashMap<String,JPanel> paneMap = new HashMap<String,JPanel>(); 
    
    JLabel[] labels = new JLabel[5];


    JTextField[] texts = new JTextField[4];
    JRadioButton[] sex = new JRadioButton[2];
    JButton run = new JButton("Run");
    ButtonGroup bg = new ButtonGroup();
    JSpinner data = new JSpinner();

    Logica log = new Logica(texts,data); 
    
    public Finestra() 
    {       
        initPane();
        initLabel();
        initTexts();

        initName();
        initSurname();
        initSex();
        initData();
        initCity();
        initRun();
                
        
        paneMap.get("Paste").add(paneMap.get("Grid"),"North");
        paneMap.get("Paste").add(paneMap.get("City"),"Center");
        paneMap.get("Paste").add(paneMap.get("Run"),"South");
        
        paneMap.get("Main").add(paneMap.get("Paste"));
        f.add(paneMap.get("Main"));

        f.pack();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setVisible(true);
    }

    private void initRun() 
    {
        texts[3].setEditable(false);
        run.addActionListener(log);
        paneMap.get("Run").add(run,"North");
        paneMap.get("Run").add(texts[3],"South");

    }

    private void initCity() 
    {
        paneMap.get("City").add(labels[4],"North");
        paneMap.get("City").add(texts[2],"South");
    }

    private void initData() 
    {
        SpinnerDateModel model = new SpinnerDateModel();
        model.setCalendarField(Calendar.SHORT);
        data.setModel(model);
        data.setEditor(new DateEditor(data,"dd/MM/yyyy"));
        paneMap.get("Data").add(labels[3],"North");
        paneMap.get("Data").add(data,"South");
        paneMap.get("Grid").add(paneMap.get("Data"));
    }

    private void initSex() 
    {
        String[] gener = {"Maschio","Femmina"}; 
        for (int i = 0; i < sex.length; i++)
        {
            sex[i] = new JRadioButton(gener[i]);
            sex[i].addActionListener(log);
            bg.add(sex[i]);
        }
        paneMap.get("Radio").add(sex[0]);
        paneMap.get("Radio").add(sex[1]);
        paneMap.get("Sex").add(labels[2],"North");
        paneMap.get("Sex").add(paneMap.get("Radio"),"South");
        paneMap.get("Grid").add(paneMap.get("Sex"));
    }

    private void initSurname() 
    {
        paneMap.get("Surname").add(labels[1],"North");
        paneMap.get("Surname").add(texts[1],"South");
        paneMap.get("Grid").add(paneMap.get("Surname"));
    }

    private void initName() 
    {
        paneMap.get("Name").add(labels[0],"North");
        paneMap.get("Name").add(texts[0],"South");
        paneMap.get("Grid").add(paneMap.get("Name"));
    }

    private void initPane() 
    {
        String[] name = 
        {
            "Main",
            "Paste",
            "Grid",
            "Name",
            "Surname",
            "Sex",
            "Radio",
            "Data",
            "City",
            "Run"
        };
        for (String string : name) 
        {
            if(string.equals("Main"))
            {
                paneMap.put(string,new JPanel());
                continue;   
            } 
            if(string.equals("Grid"))
            {
                paneMap.put(string,new JPanel(new GridLayout(0,2)));
                continue;    
            }
            if(string.equals("Radio"))
            {
                paneMap.put(string,new JPanel(new FlowLayout()));
                continue;    
            }
            paneMap.put(string,new JPanel(new BorderLayout()));
        }
    }

    private void initTexts() 
    {
        for (int i = 0; i < texts.length; i++) 
        {
            texts[i] = new JTextField(5);
        }
    }
    
    private void initLabel() 
    {
        String[] etic = {"Nome","Cognome","Sesso","Data di Nascita","Luogo di Nascita"};
        for (int i = 0; i < etic.length; i++) 
        {
            labels[i] = new JLabel(etic[i]);
        }
    }
}
