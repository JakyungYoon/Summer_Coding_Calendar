package jakyungyoon.jacalendar.calendar.jaycalendar;

public class WeeklyWeekItem {
    int month;
    int date;
    int dayOfWeekInMonth;
    int month6;
    int date6;

    public int getMonth6() {
        return month6;
    }

    public void setMonth6(int month6) {
        this.month6 = month6;
    }

    public int getDate6() {
        return date6;
    }

    public void setDate6(int date6) {
        this.date6 = date6;
    }

    public WeeklyWeekItem(int month, int date, int dayOfWeekInMonth, int month6, int date6) {
        this.month = month;
        this.date = date;
        this.dayOfWeekInMonth = dayOfWeekInMonth;
        this.month6 = month6;
        this.date6 = date6;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfWeekInMonth() {
        return dayOfWeekInMonth;
    }

    public void setDayOfWeekInMonth(int dayOfWeekInMonth) {
        this.dayOfWeekInMonth = dayOfWeekInMonth;
    }
}
