package com.example.hassannaqvi.dr_registration.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static int monthsBetweenDates(Date startDate, Date endDate) {

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        int monthsBetween = 0;
        int dateDiff = end.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);

        if (dateDiff < 0) {
            int borrrow = end.getActualMaximum(Calendar.DAY_OF_MONTH);
            dateDiff = (end.get(Calendar.DAY_OF_MONTH) + borrrow) - start.get(Calendar.DAY_OF_MONTH);
            monthsBetween--;

            if (dateDiff > 0) {
                monthsBetween++;
            }
        }

        monthsBetween += end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        monthsBetween += (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
        return monthsBetween;
    }

    public static long ageInMonths(String year, String month) {
        long ageInMonths = (Integer.valueOf(year) * 12) + Integer.valueOf(month);
        return ageInMonths;
    }

    public static String convertDateFormat(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date d = sdf.parse(dateStr);
            return new SimpleDateFormat("dd/MM/yyyy").format(d);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return "";
    }
    public static String getYearsBack(String format, int year){
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        cal.add(Calendar.YEAR, year);
        return new SimpleDateFormat(format).format(cal.getTime()).toString(); //"dd-MM-yyyy HH:mm"
    }

    public static Calendar getCalendarDate(String value) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(value);
            calendar.setTime(date);
            return calendar;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public static long ageInYearByDOB(String dateStr) {
        Calendar cal = getCalendarDate(dateStr);
        Date dob = cal.getTime();
        Date today = new Date();
        Long diff = today.getTime() - dob.getTime();
        long ageInYears = (diff / (24 * 60 * 60 * 1000)) / 365;
        return ageInYears;
    }
    public static String getYearsAndMonthsBack(String format, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(cal.getTime());
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        return new SimpleDateFormat(format).format(cal.getTime()).toString(); //"dd-MM-yyyy HH:mm"
    }
}
