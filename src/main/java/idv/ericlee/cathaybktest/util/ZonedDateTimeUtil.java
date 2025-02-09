package idv.ericlee.cathaybktest.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ZonedDateTimeUtil {

    public static final String RFC1123_PATTERN = "MMM d, yyyy HH:mm:ss z";
    public static final String ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String SELF_PATTERN = "MMM d, yyyy 'at' HH:mm z";
    public static final String YODA_PATTERN = "yyyy/MM/dd HH:mm:ss";

    // 字符串转 LocalDateTime
    public static ZonedDateTime stringToZonedDateTime(String dateString, String pattern) {
        DateTimeFormatter formatter = null;
        if (pattern.equals(RFC1123_PATTERN) || pattern.equals(SELF_PATTERN)){
            formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        }else{
            formatter = DateTimeFormatter.ofPattern(pattern);
        }
        return ZonedDateTime.parse(dateString, formatter);
    }

    // LocalDateTime 转字符串
    public static String ZonedDateTimeToString(ZonedDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    //將日期字串 轉換 其他格式字串
    public static String dateStrToDateStr(String dateString , String patternFrom, String patternTo){
        ZonedDateTime time = stringToZonedDateTime(dateString,patternFrom);
        return ZonedDateTimeToString(time,patternTo);
    }
}
