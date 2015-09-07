package com.yuyakaido.android.rruleprocessor;

import android.text.format.Time;

import com.android.calendarcommon2.DateException;
import com.android.calendarcommon2.EventRecurrence;
import com.android.calendarcommon2.RecurrenceProcessor;
import com.android.calendarcommon2.RecurrenceSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yuyakaido on 9/4/15.
 */
public final class RRuleProcessor {

    private RRuleProcessor() {}

    public static List<Date> getDates(String rule, Date dtStart, Date from, Date to) {
        Time dtStartTime = date2Time(dtStart);
        Time fromTime = null;
        if (dtStart.after(from)) {
            fromTime = date2Time(dtStart);
        } else {
            fromTime = date2Time(from);
        }
        Time toTime = date2Time(to);
        List<Date> result = new ArrayList<>();
        try {
            RecurrenceSet recurrenceSet = new RecurrenceSet(rule, null, null, null);
            RecurrenceProcessor processor = new RecurrenceProcessor();
            long[] dates = processor.expand(dtStartTime, recurrenceSet, fromTime.toMillis(true), toTime.toMillis(true));
            for (long l : dates) {
                result.add(new Date(l));
            }
        } catch (DateException | EventRecurrence.InvalidFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Time date2Time(Date date) {
        Time time = new Time("UTC");
        time.set(date.getTime());
        return time;
    }

}
