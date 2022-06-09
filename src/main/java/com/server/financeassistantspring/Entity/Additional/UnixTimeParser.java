package com.server.financeassistantspring.Entity.Additional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UnixTimeParser {

    public static final long oneWeek =  604800;
    public static final long oneDay = 86400;
    public static final long oneHour = 3600;
    static public long timeParse(String timestamp)
    {
        if (timestamp != null)
        {
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(timestamp);
                return (long)date.getTime()/1000;
            } catch(ParseException ex) {
                System.out.println("Неверный формат даты");
                return 0;
            }
        }
        else {
            System.out.println("Не введена дата");
            return 0;
        }
    }
    static public String timeUnparse(long unixTime) {
        if (unixTime != 0)
        {
            Date date = new Date ();
            date.setTime((long)unixTime*1000);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //System.out.println(sdf.format(date));
            return sdf.format(date);
        }
        else {
            System.out.println("Не введена дата");
            return null;
        }
    }
}
