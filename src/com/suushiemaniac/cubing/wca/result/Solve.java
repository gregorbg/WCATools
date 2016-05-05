package com.suushiemaniac.cubing.wca.result;

import com.suushiemaniac.cubing.wca.comp.Event;
import com.suushiemaniac.cubing.wca.util.ArrayUtils;
import com.suushiemaniac.cubing.wca.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solve {
    public static Solve fromFormatString() {
        // TODO
        return null;
    }

    public static String formatTime(long elapsed) {
        //Conversions: centiseconds, seconds, minutes, hours
        int[] convConst = {100, 60, 60, 24};
        int[] formatValues = new int[convConst.length];

        for (int i = 0; i < (convConst.length & formatValues.length); i++) {
            if (i > 0) elapsed /= convConst[i - 1];
            formatValues[i] = (int) (elapsed % convConst[i]);
        }

        int[] trimmed = ArrayUtils.autobox(ArrayUtils.reverse(ArrayUtils.trimBack(ArrayUtils.autobox(formatValues), 0)));
        String[] filled = new String[trimmed.length];

        for (int i = 0; i < (trimmed.length & filled.length); i++)
            filled[i] = i > 0 ? StringUtils.fillLeading("" + trimmed[i], '0', 2) : "" + trimmed[i];

        List<String> filledList = Arrays.asList(filled);

        return String.join(".", String.join(":", filledList.subList(0, filledList.size() - 1)), filledList.get(filledList.size() - 1));
    }

    private long elapsed;

    private boolean isAverage;

    private Event event;

    public Solve(long elapsed, Event event) {
        this(elapsed, event, false);
    }

    public Solve(long elapsed, Event event, boolean isAverage) {
        this.elapsed = elapsed;
        this.event = event;
    }

    public long getElapsed() {
        return elapsed;
    }

    public Event getEvent() {
        return event;
    }

    public boolean isAverage() {
        return isAverage;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setIsAverage(boolean average) {
        isAverage = average;
    }

    public String toFormatString() {
        String timeFormat = this.event.getTimeFormat();

        // TODO
        switch (timeFormat) {
            case "time":
                return formatTime(this.elapsed);

            case "number":
                return this.isAverage ? String.valueOf(((float) this.elapsed) / 100) : String.valueOf(this.elapsed);

            case "multi":

                switch (this.event.getId()) {
                    case "333mbo":
                        String oldValid = StringUtils.fillLeading("" + this.elapsed, '1', 10);
                        Matcher oldMatcher = Pattern.compile("1(\\d{2})(\\d{2})(\\d{5})").matcher(oldValid);

                        if (oldMatcher.find()) {
                            int solved = 99 - Integer.parseInt(oldMatcher.group(1));
                            int attempted = Integer.parseInt(oldMatcher.group(2));
                            int seconds = Integer.parseInt(oldMatcher.group(3));

                            return solved + "/" + attempted + " " + formatTime(seconds * 100);
                        } else return "Invalid old multi-blind format";

                    case "333mbf":
                        String newValid = StringUtils.fillLeading("" + this.elapsed, '0', 10);
                        Matcher newMatcher = Pattern.compile("0(\\d{2})(\\d{5})(\\d{2})").matcher(newValid);

                        if (newMatcher.find()) {
                            int difference = 99 - Integer.parseInt(newMatcher.group(1));
                            int seconds = Integer.parseInt(newMatcher.group(2));
                            int missed = Integer.parseInt(newMatcher.group(3));
                            int solved = difference + missed;
                            int attempted = solved + missed;

                            return solved + "/" + attempted + " " + formatTime(seconds * 100);
                        } else return "Invalid new multi-blind format";

                    default:
                        return "Invalid multi-blind discipline!";
                }

            default:
                return "Invalid time format!";
        }
    }
}
