package com.suushiemaniac.cubing.wca.result;

import com.suushiemaniac.cubing.wca.comp.Event;

public class Solve {
    public static Solve fromFormatString() {
        // TODO
        return null;
    }

    public static String formatTime(long elapsed) {
        return String.valueOf(elapsed);
    }

    private long elapsed;

    private Event event;

    public Solve(long elapsed, Event event) {
        this.elapsed = elapsed;
        this.event = event;
    }

    public long getElapsed() {
        return elapsed;
    }

    public Event getEvent() {
        return event;
    }

    public void setElapsed(long elapsed) {
        this.elapsed = elapsed;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String toFormatString() {
        String timeFormat = this.event.getTimeFormat();

        // TODO
        switch (timeFormat) {
            case "time":
                return formatTime(this.elapsed);
            case "number":
                return String.valueOf(this.elapsed);
            case "multi":
                switch (this.event.getId()) {
                    case "333mbo":
                        return "1SSAATTTTT";
                    case "333mbf":
                        return "0DDTTTTTMM";
                    default:
                        return "Invalid multi-blind discipline!";
                }
            default:
                return "Invalid time format!";
        }
    }
}
