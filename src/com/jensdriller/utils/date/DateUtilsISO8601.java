package com.jensdriller.utils.date;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * Utility methods (formatting & parsing) for the most common date format:
 * <b>ISO8601</b>
 */
public class DateUtilsISO8601 {

	private static final String TAG = DateUtilsISO8601.class.getSimpleName();

	private static final String UTC = "UTC";
	private static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone(UTC);
	private static final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());

	/**
	 * {@link JsonDeserializer} to be used as a type adapter for GSON.
	 * <p>
	 * All operations are UTC-based by default.
	 */
	public static class Deserializer implements JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			final TimeZone timeZone = getTimeZone();
			try {
				return deserializeDate(json.getAsJsonPrimitive().getAsString(), timeZone);
			} catch (ParseException e) {
				Log.e(TAG, "Error parsing Json date: " + json + " " + e);
				return null;
			}
		}

		/**
		 * Which time zone to use for deserialization?
		 * 
		 * @return UTC by default, override if necessary
		 */
		public TimeZone getTimeZone() {
			return TimeZone.getTimeZone(UTC);
		}
	}

	/**
	 * Deserializes the given date for the given time zone.
	 */
	public static Date deserializeDate(String date, TimeZone timeZone) throws ParseException {
		mSimpleDateFormat.setTimeZone(timeZone);
		return mSimpleDateFormat.parse(date);
	}

	/**
	 * Deserializes the given date for the default time zone UTC.
	 */
	public static Date deserializeDate(String date) throws ParseException {
		return deserializeDate(date, UTC_TIMEZONE);
	}

	/**
	 * Formats the given date to <b>ISO8601</b> for the default time zone UTC.
	 */
	public static String formatDate(Date date) {
		return formatTime(date.getTime(), UTC_TIMEZONE);
	}

	/**
	 * Formats the given date to <b>ISO8601</b> for the given time zone.
	 */
	public static String formatDate(Date date, TimeZone timeZone) {
		return formatTime(date.getTime(), timeZone);
	}

	/**
	 * Formats the given time to <b>ISO8601</b> for the default time zone UTC.
	 */
	public static String formatTime(long time) {
		return formatTime(time, UTC_TIMEZONE);
	}

	/**
	 * Formats the given time to <b>ISO8601</b> for the given time zone.
	 */
	public static String formatTime(long time, TimeZone timeZone) {
		mSimpleDateFormat.setTimeZone(timeZone);
		return mSimpleDateFormat.format(time);
	}
}
