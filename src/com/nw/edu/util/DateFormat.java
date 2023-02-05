
package com.nw.edu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Home
 */
public class DateFormat {
    public static String datetime(final Date date, final String pattern) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
    }

    public static String getCurrentDateFormat(final Date date, final String pattern) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
    }
}
