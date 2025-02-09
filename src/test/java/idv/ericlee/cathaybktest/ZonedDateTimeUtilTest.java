package idv.ericlee.cathaybktest;

import idv.ericlee.cathaybktest.util.ZonedDateTimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ZonedDateTimeUtilTest {

    @Test
    void formatRFC1123Test()throws Exception{
        String dateStr = "Feb 6, 2025 14:23:41 UTC";
        String dateStr2 = ZonedDateTimeUtil.dateStrToDateStr(dateStr,ZonedDateTimeUtil.RFC1123_PATTERN,ZonedDateTimeUtil.YODA_PATTERN);
        System.out.println(dateStr2);
    }

    @Test
    void formatISO8601Test()throws Exception{
        String dateStr = "2025-02-06T14:23:41+00:00";
        String dateStr2 = ZonedDateTimeUtil.dateStrToDateStr(dateStr,ZonedDateTimeUtil.ISO8601_PATTERN,ZonedDateTimeUtil.YODA_PATTERN);
        System.out.println(dateStr2);
    }

    @Test
    void formatSelfPatternTest()throws Exception{
        String dateStr = "Feb 6, 2025 at 14:23 GMT";
        String dateStr2 = ZonedDateTimeUtil.dateStrToDateStr(dateStr,ZonedDateTimeUtil.SELF_PATTERN,ZonedDateTimeUtil.YODA_PATTERN);
        System.out.println(dateStr2);
    }
}
