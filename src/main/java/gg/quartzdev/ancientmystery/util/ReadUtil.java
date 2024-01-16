package gg.quartzdev.ancientmystery.util;

import org.checkerframework.checker.units.qual.K;

public class ReadUtil {
    public static Number number(Object data){
//       If data isn't found
        if(data == null){
            return 0;
        }

//        Try returning it as a double
        if(data instanceof Double){
            return (double) data;
        }

//        Convert to string and try parsing
        String rawData = data.toString();
        Number number = null;
        try {
            number = Float.parseFloat(rawData);
        } catch(NumberFormatException e) {
            try {
                number = Double.parseDouble(rawData);
            } catch(NumberFormatException e1) {
                try {
                    number = Integer.parseInt(rawData);
                } catch(NumberFormatException e2) {
                    try {
                        number = Long.parseLong(rawData);
                    } catch(NumberFormatException e3) {
                        return 0;
                    }
                }
            }
        }
        return number;
    }
}
