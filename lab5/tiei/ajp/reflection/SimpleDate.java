//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.reflection;

import java.lang.reflect.*;

/**
 * A class for simple date (only month and day). The year of
 * a SimpleDate is not a leap year (i.e. February has 28 days)
 */
public class SimpleDate implements Comparable<SimpleDate> {
    // the day in the month
    private int day;
    // the month in the year
    private int month;

    /*
     * Return the new SimpleDate m/d
     * where m is the month and d the day
     */
    public SimpleDate(int m, int d) {
        day = d;
        month = m;
    }

 
    public SimpleDate(SimpleDate date) {
    	this(date.month, date.day);
    }

    /**
     * Return a copy of this
     */
//    public SimpleDate copy() {
//    	return new SimpleDate(month, day);
//    }

    /**
     * Return the day of the
     * SimpleDate this
     */
    public int getDay() {
        return day;
    }

    /**
     * Return the month of the
     * SimpleDate this
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Check if the SimpleDate this
     * is equal to the SimpleDate d
     */
    @Override
    public boolean equals(Object d) {
    	if (d == this) {
            return true;
        }
        if (!(d instanceof SimpleDate)) {
            return false;
        }
        SimpleDate sd = (SimpleDate) d;
        return this.day == sd.day && this.month == sd.month;
    }

    @Override
    public int compareTo(SimpleDate o) {
        if ( month == o.month )
            return day - o.day;
        return month - o.month;
    }
    
    /**
     * Return the number of days in  the
     * month of the date we apply the method to
     */
    public int daysInMonth() {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return  daysInMonth[month - 1];
    }

    /**
     * Change the SimpleDate this
     * to the SimpleDate of the next day
     * of that date
     */
    public void nextDay() {
        if ( day == daysInMonth() ) {
            day = 1;
            if ( month == 12 )
                month = 1;
            else
                month++;
        }
        else
            day++;
    }

    /**
     * Return the number of days 
     * between the date 'this' and 'to'
     */
    /*public int daysToDate(Object to) {
        if (to instanceof SimpleDate) {
            return daysToDate((SimpleDate) to);
        } else if (to instanceof FullDate) {
            return daysToDate((FullDate) to);
        } else {
            throw new IllegalArgumentException("Unsupported date type");
        }
    }*/

    public int daysToDate(Object to) throws ClassNotFoundException {
        if(this instanceof FullDate){
            FullDate tmp = new FullDate((FullDate) this);
            int nod = 0;
            while (!tmp.equals((FullDate)to)) {
                tmp.nextDay();
                nod++;
            }
            return nod;

        }else {
            SimpleDate tmp = new SimpleDate(this);
            int nod = 0;
            while (!tmp.equals(to)) {
                tmp.nextDay();
                nod++;
            }
            return nod;
        }
    }

    /*
     * Return a String representation
     * of the SimpleDate this
     */
    @Override
    public String toString() {
        return month + "/" + day;
    }
}
