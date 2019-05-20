package jakyungyoon.jacalendar.calendar.jaycalendar;

public class WeeklyItem {
     String dayOfWeek;
     String dayNum;
     String weekcontent;
     String month;
     String dayOfWeekInMonth;

    public WeeklyItem(String dayOfWeek, String dayNum, String month, String dayOfWeekInMonth, String weekcontent) {
        this.dayOfWeek = dayOfWeek;
        this.dayNum = dayNum;
        this.weekcontent = weekcontent;
        this.month = month;
        this.dayOfWeekInMonth = dayOfWeekInMonth;
    }


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayOfWeekInMonth() {
        return dayOfWeekInMonth;
    }

    public void setDayOfWeekInMonth(String dayOfWeekInMonth) {
        this.dayOfWeekInMonth = dayOfWeekInMonth;
    }


    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayNum() {
        return dayNum;
    }

    public void setDayNum(String dayNum) {
        this.dayNum = dayNum;
    }

    public String getWeekcontent() {
        return weekcontent;
    }

    public void setWeekcontent(String weekcontent) {
        this.weekcontent = weekcontent;
    }
}
