package Entity;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Date: class For representation of date
 * @author: Seo, HyeongCheol
 */
public class Date {
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
    public int getYear() { return this.year; }
    public int getMonth() { return this.month; }
    public int getDay() { return this.day; }
    private void setYear(int year) { this.year = year; }
    private void setMonth(int month) { this.month = month; }
    private void setDay (int day) { this.day = day; }
}
