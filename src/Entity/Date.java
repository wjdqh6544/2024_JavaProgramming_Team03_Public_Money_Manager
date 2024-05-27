package Entity;

import java.io.Serializable;

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
        return getYear() + ". " + getMonth() + ". " + getDay();
    }
    public int getYear() { return this.year; }
    public int getMonth() { return this.month; }
    public int getDay() { return this.day; }
    public void setYear(int year) { this.year = year; }
    public void setMonth(int month) { this.month = month; }
    public void setDay (int day) { this.day = day; }
}
