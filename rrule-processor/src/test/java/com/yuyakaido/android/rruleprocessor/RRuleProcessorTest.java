package com.yuyakaido.android.rruleprocessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.assertTrue;

/**
 * Created by yuyakaido on 9/6/15.
 */
@RunWith(RobolectricTestRunner.class)
public class RRuleProcessorTest {

    @Test
    public void testAll() {
        testDaily();
        testWeekly();
        testMonthly();
        testYearly();
        testUntil();
        testBeforeByDay();
        testAfterByDay();
        testMultipleByDay();
        testEndOfMonth();
    }

    private void testDaily() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date from = df.parse("2015-09-01T00:00:00.000-0000");
            Date to = df.parse("2015-09-30T23:59:59.999-0000");
            Date dtStart = df.parse("2015-09-20T00:00:00.000-0000");

            List<Date> expect = new ArrayList<>();
            expect.add(df.parse("2015-09-20T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-21T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-22T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-23T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-24T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-25T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-26T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-27T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-28T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-29T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-30T00:00:00.000-0000"));
            String rule = "FREQ=DAILY";
            List<Date> actual = RRuleProcessor.getDates(rule, dtStart, from, to);

            String message = "RRule = " + rule + ", dtStart = " + df.format(dtStart);
            println(message);
            message = "expect.size() = " + expect.size() + ", actual.size() = " + actual.size();
            println(message);
            assertTrue(message, expect.size() == actual.size());

            for (int i = 0; i < expect.size(); i++) {
                message = "expect = " + df.format(expect.get(i)) + ", actual = " + df.format(actual.get(i));
                println(message);
                assertTrue(expect.get(i).equals(actual.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void testWeekly() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date from = df.parse("2015-09-01T00:00:00.000-0000");
            Date to = df.parse("2015-09-30T23:59:59.999-0000");
            Date dtStart = df.parse("2015-09-06T00:00:00.000-0000");

            List<Date> expect = new ArrayList<>();
            expect.add(df.parse("2015-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-13T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-20T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-27T00:00:00.000-0000"));
            String rule = "FREQ=WEEKLY";
            List<Date> actual = RRuleProcessor.getDates(rule, dtStart, from, to);

            String message = "RRule = " + rule + ", dtStart = " + df.format(dtStart);
            println(message);
            message = "expect.size() = " + expect.size() + ", actual.size() = " + actual.size();
            println(message);
            assertTrue(message, expect.size() == actual.size());

            for (int i = 0; i < expect.size(); i++) {
                message = "expect = " + df.format(expect.get(i)) + ", actual = " + df.format(actual.get(i));
                println(message);
                assertTrue(expect.get(i).equals(actual.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void testMonthly() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date from = df.parse("2015-01-01T00:00:00.000-0000");
            Date to = df.parse("2015-12-31T23:59:59.999-0000");
            Date dtStart = df.parse("2015-09-06T00:00:00.000-0000");

            List<Date> expect = new ArrayList<>();
            expect.add(df.parse("2015-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2015-10-06T00:00:00.000-0000"));
            expect.add(df.parse("2015-11-06T00:00:00.000-0000"));
            expect.add(df.parse("2015-12-06T00:00:00.000-0000"));
            String rule = "FREQ=MONTHLY";
            List<Date> actual = RRuleProcessor.getDates(rule, dtStart, from, to);

            String message = "RRule = " + rule + ", dtStart = " + df.format(dtStart);
            println(message);
            message = "expect.size() = " + expect.size() + ", actual.size() = " + actual.size();
            println(message);
            assertTrue(message, expect.size() == actual.size());

            for (int i = 0; i < expect.size(); i++) {
                message = "expect = " + df.format(expect.get(i)) + ", actual = " + df.format(actual.get(i));
                println(message);
                assertTrue(expect.get(i).equals(actual.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void testYearly() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date from = df.parse("2010-01-01T00:00:00.000-0000");
            Date to = df.parse("2020-12-31T23:59:59.999-0000");
            Date dtStart = df.parse("2015-09-06T00:00:00.000-0000");

            List<Date> expect = new ArrayList<>();
            expect.add(df.parse("2015-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2016-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2017-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2018-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2019-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2020-09-06T00:00:00.000-0000"));
            String rule = "FREQ=YEARLY";
            List<Date> actual = RRuleProcessor.getDates(rule, dtStart, from, to);

            String message = "RRule = " + rule + ", dtStart = " + df.format(dtStart);
            println(message);
            message = "expect.size() = " + expect.size() + ", actual.size() = " + actual.size();
            println(message);
            assertTrue(message, expect.size() == actual.size());

            for (int i = 0; i < expect.size(); i++) {
                message = "expect = " + df.format(expect.get(i)) + ", actual = " + df.format(actual.get(i));
                println(message);
                assertTrue(expect.get(i).equals(actual.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void testUntil() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date from = df.parse("2015-09-01T00:00:00.000-0000");
            Date to = df.parse("2015-09-30T23:59:59.999-0000");
            Date dtStart = df.parse("2015-09-06T00:00:00.000-0000");

            List<Date> expect = new ArrayList<>();
            expect.add(df.parse("2015-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-13T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-20T00:00:00.000-0000"));
            String rule = "FREQ=WEEKLY;UNTIL=20150925T000000Z";
            List<Date> actual = RRuleProcessor.getDates(rule, dtStart, from, to);

            String message = "RRule = " + rule + ", dtStart = " + df.format(dtStart);
            println(message);
            message = "expect.size() = " + expect.size() + ", actual.size() = " + actual.size();
            println(message);
            assertTrue(message, expect.size() == actual.size());

            for (int i = 0; i < expect.size(); i++) {
                message = "expect = " + df.format(expect.get(i)) + ", actual = " + df.format(actual.get(i));
                println(message);
                assertTrue(expect.get(i).equals(actual.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void testBeforeByDay() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date from = df.parse("2015-09-01T00:00:00.000-0000");
            Date to = df.parse("2015-09-30T23:59:59.999-0000");
            Date dtStart = df.parse("2015-09-06T00:00:00.000-0000");

            List<Date> expect = new ArrayList<>();
            expect.add(df.parse("2015-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-12T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-19T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-26T00:00:00.000-0000"));
            String rule = "FREQ=WEEKLY;BYDAY=SA";
            List<Date> actual = RRuleProcessor.getDates(rule, dtStart, from, to);

            String message = "RRule = " + rule + ", dtStart = " + df.format(dtStart);
            println(message);
            message = "expect.size() = " + expect.size() + ", actual.size() = " + actual.size();
            println(message);
            assertTrue(message, expect.size() == actual.size());

            for (int i = 0; i < expect.size(); i++) {
                message = "expect = " + df.format(expect.get(i)) + ", actual = " + df.format(actual.get(i));
                println(message);
                assertTrue(expect.get(i).equals(actual.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void testMultipleByDay() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date from = df.parse("2015-09-01T00:00:00.000-0000");
            Date to = df.parse("2015-09-30T23:59:59.999-0000");
            Date dtStart = df.parse("2015-09-06T00:00:00.000-0000");

            List<Date> expect = new ArrayList<>();
            expect.add(df.parse("2015-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-07T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-12T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-13T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-14T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-19T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-20T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-21T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-26T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-27T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-28T00:00:00.000-0000"));
            String rule = "FREQ=WEEKLY;BYDAY=SA,SU,MO";
            List<Date> actual = RRuleProcessor.getDates(rule, dtStart, from, to);

            String message = "RRule = " + rule + ", dtStart = " + df.format(dtStart);
            println(message);
            message = "expect.size() = " + expect.size() + ", actual.size() = " + actual.size();
            println(message);
            assertTrue(message, expect.size() == actual.size());

            for (int i = 0; i < expect.size(); i++) {
                message = "expect = " + df.format(expect.get(i)) + ", actual = " + df.format(actual.get(i));
                println(message);
                assertTrue(expect.get(i).equals(actual.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void testAfterByDay() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date from = df.parse("2015-09-01T00:00:00.000-0000");
            Date to = df.parse("2015-09-30T23:59:59.999-0000");
            Date dtStart = df.parse("2015-09-06T00:00:00.000-0000");

            List<Date> expect = new ArrayList<>();
            expect.add(df.parse("2015-09-06T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-07T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-14T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-21T00:00:00.000-0000"));
            expect.add(df.parse("2015-09-28T00:00:00.000-0000"));
            String rule = "FREQ=WEEKLY;BYDAY=MO";
            List<Date> actual = RRuleProcessor.getDates(rule, dtStart, from, to);

            String message = "RRule = " + rule + ", dtStart = " + df.format(dtStart);
            println(message);
            message = "expect.size() = " + expect.size() + ", actual.size() = " + actual.size();
            println(message);
            assertTrue(message, expect.size() == actual.size());

            for (int i = 0; i < expect.size(); i++) {
                message = "expect = " + df.format(expect.get(i)) + ", actual = " + df.format(actual.get(i));
                println(message);
                assertTrue(expect.get(i).equals(actual.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void testEndOfMonth() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            df.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date from = df.parse("2015-01-01T00:00:00.000-0000");
            Date to = df.parse("2015-12-31T23:59:59.999-0000");
            Date dtStart = df.parse("2015-10-31T00:00:00.000-0000");

            List<Date> expect = new ArrayList<>();
            expect.add(df.parse("2015-10-31T00:00:00.000-0000"));
            expect.add(df.parse("2015-12-31T00:00:00.000-0000"));
            String rule = "FREQ=MONTHLY";
            List<Date> actual = RRuleProcessor.getDates(rule, dtStart, from, to);

            String message = "RRule = " + rule + ", dtStart = " + df.format(dtStart);
            println(message);
            message = "expect.size() = " + expect.size() + ", actual.size() = " + actual.size();
            println(message);
            assertTrue(message, expect.size() == actual.size());

            for (int i = 0; i < expect.size(); i++) {
                message = "expect = " + df.format(expect.get(i)) + ", actual = " + df.format(actual.get(i));
                println(message);
                assertTrue(expect.get(i).equals(actual.get(i)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void println(String message) {
        System.out.println(message);
    }

}