//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.reflection;

/**
 * A class for full date (tear, month and day). The year of
 * a SimpleDate could be a leap year (i.e. February has 29 days
 * for those years)
 */
public class FullDate extends SimpleDate {

    // the year
    private int year;

    /*
     * Return the new FullDate y/m/d
     * where y is the year, m is the month
     * and d the day
     */
    public FullDate(int y, int m, int d) throws BadDateException {
        super(m,d);
    	year = y;
    	if ( y < 1 || m < 1 || m > 12 || d < 1 || d > daysInMonth() )
            throw new BadDateException("bad date: " + this);
    }

    public FullDate(FullDate date) {
    	super(date.getMonth(),date.getDay());
    	year = date.year;
    }


    public FullDate copy() {
        try {
            return new FullDate(year, this.getMonth(), this.getDay());
        }
        catch ( BadDateException e ) {
            return this;
        }
    }
    
    /**
     * Return the year of the date
     */
    public int getYear() {
        return year;
    }

    /**
     * Return the number of days in  the
     * month of the date we apply the method to
     * (works for leap year as well)
     */
    @Override
    public int daysInMonth() {
        if ( getMonth() == 2 && leapYear(year) )
            return 29;
        return super.daysInMonth();
    }

    /**
     * Change the FullDate this
     * to the FullDate of the next day
     * of that date
     */
    @Override
    public void nextDay() {
        super.nextDay();
        if ( getDay() == 1 && getMonth() == 1 )
            year++;
    }
    
    /**
     * Return the number of days 
     * between the date 'this' and 'to'
     */
    public int daysToDate(FullDate date) throws ClassNotFoundException {
    	if ( compareTo(date) <= 0 )
    		return super.daysToDate(date);
    	return date.daysToDate(this);
    }
    
    
    @Override
    public boolean equals(Object date) {
    	if (date == this) {
            return true;
        }
        if (!(date instanceof FullDate)) {
            return false;
        }
        FullDate sd = (FullDate) date;
        return super.equals(sd) && year == sd.year;
    }

    /**
     * Check if the FullDate this
     * is less than or equal to the FullDate date
     */
    public int compareTo(FullDate date) {
        if ( year == date.year ) {
            return super.compareTo(date);
        }
        return this.year - date.year;
    }

    /**
     * Return a String representation
     * of the FullDate this
     */
    @Override
    public String toString() {
        return year + "/" + super.toString();
    }

    /**
     * Return true if year is a leap year, false otherwise
     */
    private static boolean leapYear(int year) {
        return year % 4 == 0 && ( year % 100 != 0 || year % 400 == 0 );
    }
}
