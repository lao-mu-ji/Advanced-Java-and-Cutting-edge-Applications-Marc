//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.date;

/**
 * A class for full date (tear, month and day). The year of
 * a SimpleDate could be a leap year (i.e. February has 29 days
 * for those years)
 */
public class FullDate extends SimpleDate {
    private int year;


    /*
     * Return the new FullDate y/m/d
     * where y is the year, m is the month
     * and d the day
     */
    public FullDate(int y, int m, int d) throws BadDateException {

        super(m,d);
        this.year = y;
        if(y < 1 || m < 1 || m > 12 || d < 1 || d > 31){
            throw new BadDateException("Invalid date");
        }
    }

    @Override
    public FullDate copy() throws BadDateException {
        return new FullDate(this.year, getMonth(), getDay());
    }

    /**
     * Return the year of the date
     */
    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int daysInMonth() {
        int[] daysPerMonth = {31,isLeapYear(this.year)?29:28 , 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysPerMonth[this.getMonth() - 1];
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    @Override
    public void nextDay() {
        setDay(getDay() + 1);
        if (getDay() > daysInMonth()) {
            setDay(1);
            setMonth(getMonth() + 1);
            if (getMonth() > 12) {
                setMonth(1);
                setYear(getYear() + 1);
            }
        }
    }
    
    /**
     * Return the number of days
     * between the date 'this' and 'to'
     */
    public int daysToDate(FullDate date) throws BadDateException {
        if(this.compareTo(date)<0){
            int count = 0;
            FullDate temp = this.copy();
            while (!temp.equals(date)) {
                temp.nextDay();
                count += 1;
            }
            return count;
        }
        else if(this.compareTo(date)>0){
            int count = 0;
            FullDate temp = date.copy();
            while (!this.equals(temp)) {
                temp.nextDay();
                count += 1;
            }
            return count;
        }
        return 0;
    }
    
    
    @Override
    public boolean equals(Object date) {
        if (this == date) {
            return true;
        }
        if (date == null || getClass() != date.getClass()) {
            return false;
        }
        FullDate otherDate = (FullDate) date;
        return this.getDay() == otherDate.getDay() &&
                this.getMonth() == otherDate.getMonth() &&
                this.getYear() == otherDate.getYear();
    }

    /**
     * Check if the FullDate this
     * is less than or equal to the FullDate date
     */
    public int compareTo(FullDate date) {
        if(this.getYear() < date.getYear()){
            return -1;
        } else if(this.getYear() > date.getYear()){
            return 1;
        } else {
            if (this.getMonth() < date.getMonth()) {
                return -1;
            } else if (this.getMonth() > date.getMonth()) {
                return 1;
            } else {
                if (this.getDay() < date.getDay()) {
                    return -1;
                } else if (this.getDay() > date.getDay()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * Return a String representation
     * of the FullDate this
     */
    @Override
    public String toString() {
        return String.format("%d/%02d/%02d", year, getMonth(), getDay());
    }
}
