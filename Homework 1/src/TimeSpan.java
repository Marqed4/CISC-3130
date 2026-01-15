import java.util.Objects;

public class TimeSpan {
    private final int minutes;

    private TimeSpan(int minutes) {
        this.minutes = minutes;
    }

    private static TimeSpan ofHours(int hours) {
        if (hours < 0) throw new IllegalArgumentException("irrepresentable timespan");
        return new TimeSpan(hours * 60);
    }

    public static TimeSpan ofMinutes(int totalMinutes) {
        if (totalMinutes < 0) throw new IllegalArgumentException("irrepresentable timespan");
        return new TimeSpan(totalMinutes);
    }

    public static TimeSpan ofHoursAndMinutes(int hours, int minutes) {
        if (hours < 0 || minutes < 0)
            throw new IllegalArgumentException("irrepresentable timespan");

//        return ofHours(hours) + ofMinutes(minutes);
        return new TimeSpan(hours * 60 + minutes);
    }

    public int getHours() {
        return minutes / 60;
    }

    public int getMinutes() {
        return minutes % 60;
    }

    public int getTotalMinutes() {
        return minutes;
    }

    public String toString() {
        return String.format("%02d:%02d", getHours(), getMinutes());
    }

    public boolean equals(Object o) {
        return o instanceof TimeSpan other &&
                getTotalMinutes() == other.getTotalMinutes();
    }

    public int hashCode() {
        return Objects.hash(getHours(), getMinutes());
    }

    public TimeSpan plus(TimeSpan other) {
        return new TimeSpan(getTotalMinutes() + other.getTotalMinutes());
    }

    public TimeSpan plusHours(int hours) {
        if (hours < 0) throw new IllegalArgumentException("irrepresentable timespan");
        return new TimeSpan(getTotalMinutes() + (hours * 60));
    }

        public TimeSpan plusMinutes(int minutes) {
            if (minutes < 0) throw new IllegalArgumentException("irrepresentable timespan");
            return new TimeSpan(getTotalMinutes() + minutes);
    }

    public TimeSpan plusHoursAndMinutes(int hours, int minutes) {
        if (hours < 0 || minutes < 0) throw new IllegalArgumentException("irrepresentable timespan");
        return new TimeSpan(getTotalMinutes() + (hours * 60) + minutes);
    }
}