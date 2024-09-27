//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.date;

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
    public SimpleDate(int m, int d) throws BadDateException {
        if (m < 1 || m > 12) {
            throw new BadDateException("Month must be between 1 and 12");
        }
        if (d < 1 || d > 31) {
            throw new BadDateException("Day must be between 1 and 31");
        }
        this.day = d;
        this.month = m;
    }

    public SimpleDate copy() throws BadDateException {
        return new SimpleDate(this.month, this.day);
    }

    /**
     * Return the day of the
     * SimpleDate this
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Return the month of the
     * SimpleDate this
     */
    public int getMonth() {
        return this.month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object d) {
        if(this == d){
            return true;
        }
        if(!(d instanceof SimpleDate)){
            return false;
        }
        SimpleDate other = (SimpleDate) d;
        return this.month == other.month && this.day == other.day;
    }

    @Override
    public int compareTo(SimpleDate o) {
        if (this.month < o.month) {
            return -1;
        } else if (this.month > o.month) {
            return 1;
        } else {
            if (this.day < o.day) {
                return -1;
            } else if (this.day > o.day) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    /**
     * Return the number of days in  the
     * month of the date we apply the method to
     */
    public int daysInMonth() {
        int[] daysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysPerMonth[this.month - 1];
    }

    /**
     * Change the SimpleDate this
     * to the SimpleDate of the next day
     * of that date
     */
    public void nextDay() {
        this.day += 1;
        if (this.day > this.daysInMonth()) {
            this.day = 1;
            this.month += 1;
            if (this.month > 12) {
                this.month = 1;
            }
        }
    }

    /**
     * Return the number of days
     * between the date 'this' and 'to'
     */
    public int daysToDate(SimpleDate to) throws BadDateException {
        if(this.compareTo(to)<0){
            int count = 0;
            SimpleDate temp = this.copy();
            while (!temp.equals(to)) {
                temp.nextDay();
                count += 1;
            }
            return count;
        }
        else if(this.compareTo(to)>0){
            int count = 0;
            SimpleDate temp = to.copy();
            while (!this.equals(temp)) {
                temp.nextDay();
                count += 1;
            }
            return count;
        }
        return 0;
    }

    /*
     * Return a String representation
     * of the SimpleDate this
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day;
    }
}
