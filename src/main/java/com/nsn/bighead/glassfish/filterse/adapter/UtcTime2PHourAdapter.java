package com.nsn.bighead.glassfish.filterse.adapter;

import com.nsn.bighead.glassfish.filterse.handler.DataFilterAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * @author: create by suhy
 * @version: v1.0
 * @description:
 * @className: UtcTime2PHourAdapter
 * @date:2022/3/24 9:12
 */
public class UtcTime2PHourAdapter extends DataFilterAdapter {

    private Logger log = Logger.getLogger(UtcTime2PHourAdapter.class);

    @Override
    public String handleBuffer(String[] arg0, String arg1, int columnX, String service){
        if ((arg1 != null) && (!"".equals(arg1))) {
//            log.info("get input string : "+arg1);
//            log.info("get transform result : "+getHourStart(arg1));
            return getHourStart(arg1);
        }
        return "";
    }

    public String getHourStart(String utcTime) {
        Date sourceDate = string2Date(utcTime);
        if (sourceDate == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(sourceDate.getTime());
        calendar.set(12, 0);
        calendar.set(13, 0);
        Date date = calendar.getTime();
        SimpleDateFormat phourFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String sdate = phourFormat.format(date);
        return "p_hour=" + sdate;
    }

    public Date string2Date(String utcTime){
        if (utcTime.length()>=19){
            utcTime=utcTime.substring(0,19);
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(utcTime);
        }catch (Exception e){
            this.log.error("string to date error, utcTime: "+utcTime,e);
        }
        return null;
    }
    public static void main(String[] args) {
        String arg1 = "2022-03-22 14:25:38";
        UtcTime2PHourAdapter adapter = new UtcTime2PHourAdapter();
        System.out.println("data:" + adapter.handleBuffer(args, arg1, 2, ""));
    }
}
