package jakyungyoon.jacalendar.calendar.jaycalendar;

public class WeeklyMonthItem {
    String month;
    int monthNum;

    public WeeklyMonthItem(String month, int monthNum) {
        this.month = month;
        this.monthNum = monthNum;
    }

    public int getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
