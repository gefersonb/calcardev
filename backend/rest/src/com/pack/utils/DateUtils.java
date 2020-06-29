package com.pack.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.jboss.logging.Logger;

public class DateUtils {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final Logger LOGGER = Logger.getLogger(DateUtils.class);

    private DateUtils() {
    }

    public static Date convert(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date convert(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String dateAsString(Date data, String format) {
        return new SimpleDateFormat(format).format(data);
    }

    public static String dateAsString(Date data) {
        return dateAsString(data, DATE_FORMAT);
    }

    public static Optional<Date> stringAsDate(String data) {
        return stringAsDate(data, DATE_FORMAT);
    }

    public static Optional<Date> stringAsDate(String data, String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(data);
        } catch (ParseException e) {
            LOGGER.error(e);
        }
        return Optional.ofNullable(date);
    }

    public static LocalDate convert(Date date) {
        return convert(date, ZoneId.systemDefault());
    }

    public static LocalDate convert(Date date, ZoneId zone) {
        if (date == null) {
            return null;
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
    }

}