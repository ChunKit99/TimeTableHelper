package model;

import java.time.LocalTime;

public class Day {

    public String name;
    public int numOfSlot = 10;
    public LocalTime timeStartLearning = LocalTime.of(8, 00, 00);
    public LocalTime durationLearning = LocalTime.of(0, 50, 00);
    public TimePeriod[] timePeriod = new TimePeriod[numOfSlot];

    public Day(String name) {
        this.name = name;
        timePeriod = makeTimePeriod();
    }

    public TimePeriod[] makeTimePeriod() {
        TimePeriod[] timePeriod = new TimePeriod[numOfSlot];
        for (int i = 0; i < numOfSlot; i++) {
            timePeriod[i] = new TimePeriod();
            if (i == 0) {
                timePeriod[i].timeStart = LocalTime.of(8, 0, 0);
                timePeriod[i].timeEnd = timePeriod[i].timeStart.plusMinutes(durationLearning.getMinute());

            } else {
                timePeriod[i].timeStart = timePeriod[i - 1].timeStart.plusHours(1);
                timePeriod[i].timeEnd = timePeriod[i].timeStart.plusMinutes(durationLearning.getMinute());
            }
        }
        return timePeriod;

    }

}
