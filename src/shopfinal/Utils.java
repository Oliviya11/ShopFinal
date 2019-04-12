/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopfinal;

import java.sql.Date;
import java.util.Calendar;
import shopfinal.res.StringResources;

public final class Utils {
    private static String getWeekDayNumber(int day) {
        // Saturday = 1, Monday = 2, ... Sunday = 7
        switch (day) {
            case 0:
                return StringResources.Saturday;
            case 1:
                return StringResources.Monday;
            case 2:
                return StringResources.Tuesday;
            case 3:
                return StringResources.Wednesday;
            case 4:
                return StringResources.Thursday;
            case 5:
                return StringResources.Friday;
            case 6:
                return StringResources.Sunday;
            default:
                return "None";
        }
    }
    
     public static String getDayOfTheWeek(Date date) {
        return getWeekDayNumber(date.getDay());
    }
}
