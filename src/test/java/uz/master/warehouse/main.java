package uz.master.warehouse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.chrono.HijrahChronology;
import java.time.chrono.HijrahDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Locale;

@SpringBootTest
public class main {
    @Test
    public void none(){
        HijrahDate date=HijrahDate.now();
        System.out.println(date);
        String localizedDateTimePattern = DateTimeFormatterBuilder.getLocalizedDateTimePattern(FormatStyle.LONG, FormatStyle.MEDIUM, HijrahChronology.INSTANCE, Locale.ENGLISH);
        LocalDateTime dateTime=LocalDateTime.now();
        System.out.println(localizedDateTimePattern);
        dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(dateTime);

    }
}
