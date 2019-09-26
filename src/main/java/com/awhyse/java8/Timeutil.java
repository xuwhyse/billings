package com.awhyse.java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author xumin
 * @description
 * @date 2018/10/17 下午4:16
 */
public class Timeutil {
    public static void main(String[] args) {
        getTimeStr();
    }

    /**
     * 线程安全的java8 LocalDateTime + DateTimeFormatter
     */
    private static void getTimeStr() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalTime time2 = time.plusMinutes(5);//日期相加

        System.err.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hhmm")));
        System.err.println(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.err.println(time.format(DateTimeFormatter.ofPattern("HHmm")));
        System.err.println(time2.format(DateTimeFormatter.ofPattern("hhmm")));




        date2Local();
    }

    private static void date2Local() {
        Date aa = new Date();
        Instant instant = aa.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
    }
}
