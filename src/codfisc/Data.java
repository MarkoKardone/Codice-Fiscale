package codfisc;

import java.text.SimpleDateFormat;
import java.util.*;

public class Data {
    private String data;
    
    public Data(Date data,boolean f) 
    {
        this.data = doData(data, f);
    }

    private String doData(Date data, boolean f) 
    {
        {
            String code = new String();
            String char_mese ="ABCDEHLMPRST";
            
            SimpleDateFormat data_format = new SimpleDateFormat("yy");
            Calendar calendar = new GregorianCalendar();
            int mese;
            
            calendar.setTime(data);
            mese = calendar.get(Calendar.MONTH);
            int day = (calendar.get(Calendar.DAY_OF_MONTH)+((f) ?40:0));
    
            code  = data_format.format(data);
            code += char_mese.substring(mese,mese+1);
            code += String.format("%02d",day);
            
            return code;
        }
    }

    public String getData(){return data;};
}
