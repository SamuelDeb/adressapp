package fr.sd.addressapp.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Fonctions pour aider à gérer les dates
 */
public class DateUtil {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Retourne la date donnée au format String
     *
     * @param date Date a retourné en String
     * @return date sous format String
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Convertie un String dans le format defini par DATE_PATTERN en un objet LocalDate
     *
     * Retourne null si le String ne peut être converti.
     * @param dateString la date en String
     * @return la date en objet ou null si elle n'a pu être convertie.
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeException e) {
            return null;
        }
    }

    /**
     * Verifie si le String et une date valide
     * @param dateString
     * @return true si le String est une date valide
     */
    public static boolean isValidDate(String dateString){
        return DateUtil.parse(dateString) != null;
    }
}
