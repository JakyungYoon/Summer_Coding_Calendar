package jakyungyoon.jacalendar.calendar.jaycalendar;

public class DailyItem {
    String Content;
    String DayDate;

    public DailyItem(String content, String dayDate) {
        Content = content;
        DayDate = dayDate;
    }

    public String getDayDate() {
        return DayDate;
    }

    public void setDayDate(String dayDate) {
        DayDate = dayDate;
    }


    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
