package com.GitHub.Searcher.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
public class StringToDateParser {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

    public LocalDate parseStringToDate(String stringDate)
    {
        LocalDate date=LocalDate.parse(stringDate, formatter);
        return date;
    }

    public boolean isUpdatedWithin3Months(String stringRepoDate)
    {
        LocalDate repoUpdateDate = parseStringToDate(stringRepoDate);
        LocalDate actualDate = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(repoUpdateDate, actualDate);
        if(months<3) {
            return true;
        }
        else return false;
    }

}
