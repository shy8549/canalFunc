import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: Calendar learnning
 * @date:2021/8/16
 */
public class DateUtilTemp {
//    private static Object MicroTimestamp;

//    private static String[] parsePatterns = {"yyyy-MM-dd HH:mm:ss-SSS"};
    private static String[] parsePatterns = {"yyyy-MM-dd HH:mm:ss.SSSSSS", "yyyy-MM-dd HH:mm:ss-SSSSSS",
            "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss-SSS", "yyyy-MM-dd HH:mm:ss"};
    public enum MicroTimestamp {
        INSTANCE;

        private long startDate;
        private long startNanoseconds;
        private SimpleDateFormat dateFormat;

        private MicroTimestamp() {
            this.startDate = System.currentTimeMillis();
            this.startNanoseconds = System.nanoTime();
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        }

        public String get() {
            long microSeconds = (System.nanoTime() - this.startNanoseconds) / 1000;
            System.out.println(microSeconds);
            long date = this.startDate + (microSeconds / 1000);
            System.out.println(date);
            return this.dateFormat.format(date) + String.format("%03d", microSeconds % 1000);
        }
    }
/*
* java8 UTC time只支持到毫秒，会将"2021-08-09 01:58:00.420023"秒后面的6位都作为毫秒计算
* */
    public static void main(String[] args) throws Exception{

//        Calendar cl = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
//        System.out.println(cl.getTime());
//        System.out.println(cl.getTimeZone());
//        System.out.println(cl.getTimeInMillis());
//        System.out.println(cl.get(Calendar.DATE));
//        System.out.println(cl.get(Calendar.MONTH));
//        System.out.println(cl.get(Calendar.YEAR));

        SimpleDateFormat sdt = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");
//
//        System.out.println(sdt.parse("2021-08-09 01:58:00.420"));
//        System.out.println(sdt.parse("2021-08-09 01:58:00.420023"));
        System.out.println(String.valueOf(sdt.parse("20210809 01:58:00.420").getTime()));
//        System.out.println(String.valueOf(sdt.parse("2021-08-09 01:58:00.420023").getTime()));
//        System.out.println(MicroTimestamp.INSTANCE.get());
//        System.out.println(DateUtils.parseDate("2021-08-09 01:58:00.111222", parsePatterns));
    }
}
