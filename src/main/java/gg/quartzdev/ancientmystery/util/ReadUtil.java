package gg.quartzdev.ancientmystery.util;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ReadUtil {

    public static @Nullable EntityType entityType(Object data){
//        If data isn't found
        if(data == null){
            return null;
        }

        String rawData = data.toString();
        try{
           return EntityType.valueOf(rawData);
        } catch(IllegalArgumentException exception){
            return null;
        }
    }


    public static @NotNull Number number(Object data){
//       If data isn't found
        if(data == null){
            return 0;
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
