package Entity;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Date: class For representation of date
 * @author: Seo, HyeongCheol
 */
public class Date implements Serializable {
    private int year;
    private int month;
    private int day;
    public Date(){ // default constructor
        setYear(1900);
        setMonth(1);
        setDay(1);
    }
    public Date(int year, int month, int day) { // constructor using information
        setYear(year);
        setMonth(month);
        setDay(day);
    }
    public Date(Date otherObj) { // copy constructor
        setYear(otherObj.getYear());
        setMonth(otherObj.getMonth());
        setDay(otherObj.getDay());
    }

    @Override
    public String toString(){
        LocalDate date = LocalDate.of(getYear(), getMonth(), getDay());
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String res = getYear() + "년 ";
        if (getMonth() <= 9){
            res += "0" + getMonth() + "월 ";
        }else {
            res += getMonth() + "월 ";
        }
        if (getDay() <= 9){
            res += "0" + getDay() + "일 ";
        }else {
            res += getDay() + "일 ";
        }
        res += dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);
        return res;
    }
    @Override
    public boolean equals(Object otherObj){
        if (otherObj == null){
            return false;
        } else if ((otherObj instanceof Date) == false){
            return false;
        } else {
            Date obj = (Date) otherObj;
            return ((getYear() == obj.getYear()) && (getMonth() == obj.getMonth()) && (getDay() == obj.getDay()));
        }
    }
    public int getYear() { return this.year; }
    public int getMonth() { return this.month; }
    public int getDay() { return this.day; }
    public void setYear(int year) { this.year = year; }
    public void setMonth(int month) { this.month = month; }
    public void setDay (int day) { this.day = day; }
}
