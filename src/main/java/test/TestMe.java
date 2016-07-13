package test;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.parameter.TzId;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

/**
 * Created by meduel on 13-Jul-16.
 */
public class TestMe {
    public static void main(String[] args) throws ParseException, IOException, ParserException {
        testSetProperty();
        testBuilder();
    }

    /**
     * Here we show that we fail on invalid time
     * @throws IOException
     * @throws ParserException
     */
    public static void testBuilder() throws IOException, ParserException {
        StringReader sin = new StringReader("BEGIN:VCALENDAR\n" +
                "BEGIN:VEVENT\n" +
                "DTSTART;TZID=Asia/Jerusalem:20160325T000000\n" +
                "DTEND;TZID=Asia/Jerusalem:20160325T020000\n" +
                "SUMMARY:Untitled rule 1\n" +
                "RRULE:FREQ=WEEKLY;UNTIL=20170713T205959Z;INTERVAL=1;BYDAY=WE\n" +
                "END:VEVENT\n" +
                "END:VCALENDAR\n");
        CalendarBuilder builder = new CalendarBuilder();
        builder.build(sin);
    }

    /**
     * problem is that we set  `20160325T030000`
     *
     * but the library modifies it to : 20160325T020000 unexpectedly..
     * later, we fail since this time does not exist
     *
     * @throws ParseException
     */
    public static void testSetProperty() throws ParseException{
        DtEnd dtEnd = new DtEnd();
//        TzId tzId = new TzId("Israel");
//        dtEnd.getParameters().add(tzId);
        dtEnd.setValue("20160325T030000");
        System.out.println("dtEnd = " + dtEnd);
    }
}
