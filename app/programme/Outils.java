/*---------------------------------------------------*/
/*                     IMPORT                        */
/*---------------------------------------------------*/



//Import des classes pour manipuler les dates
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;



/*---------------------------------------------------*/
/*                       CODE                        */
/*---------------------------------------------------*/



public class Outils
{

    public static String Decimal2HeureMinute(double heure)
    {
        int heureEntier, minuteEntier;

        heureEntier  = (int)(heure);
        minuteEntier = (int) arrondir((heure - heureEntier) * 60);

        if (minuteEntier == 60)
        {
            minuteEntier = 0;
            heureEntier++;
        }

        return String.format("%2d", heureEntier) + "h" + String.format("%0" + 2 + "d", minuteEntier);
    }

    public static double HeureMinute2Decimal(int heure, int minute)
    {
        return heure + arrondir(minute)/60.0;
    }

    
    
    public static String AfficherLaDate(LocalDate date) 
    {
        
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfMonth = date.getDayOfMonth();
        Month month = date.getMonth();
        int year = date.getYear();
        
        String dayOfWeekName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
        String monthName = month.getDisplayName(TextStyle.FULL, Locale.getDefault());
        String yearName = Integer.toString(year);
        
        String dayOfWeekName1 = dayOfWeekName.substring(0, 1).toUpperCase();
        dayOfWeekName = dayOfWeekName1 + dayOfWeekName.substring(1); 
        
        String monthName1 = monthName.substring(0, 1).toUpperCase();
        monthName = monthName1 + monthName.substring(1);


        return dayOfWeekName + "  " + dayOfMonth + "  " + monthName + "  " + yearName ;
    }

    public static int arrondir(double nb)
    {
        if (nb%1 >= 0.5)
            nb++;
        return (int) nb;
    }

}