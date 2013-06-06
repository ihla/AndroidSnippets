import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

import com.example.android.activityrecognition.ActivityUtils;
import com.example.android.activityrecognition.R;

// Formats the timestamp in the log
private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSZ";

// A date formatter
private SimpleDateFormat dateFormat;

// Get a date formatter, and catch errors in the returned timestamp
try {
    dateFormat = (SimpleDateFormat) DateFormat.getDateTimeInstance();
} catch (Exception e) {
    Log.e(ActivityUtils.APPTAG, getString(R.string.date_format_error));
}

// Format the timestamp according to the pattern, then localize the pattern
dateFormat.applyPattern(DATE_FORMAT_PATTERN);
dateFormat.applyLocalizedPattern(dateFormat.toLocalizedPattern());

// Make a timestamp
String timeStamp = dateFormat.format(new Date());
