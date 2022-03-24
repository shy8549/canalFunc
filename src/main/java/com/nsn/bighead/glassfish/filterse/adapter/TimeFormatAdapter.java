package com.nsn.bighead.glassfish.filterse.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.nsn.bighead.glassfish.filterse.handler.DataFilterAdapter;

public class TimeFormatAdapter extends DataFilterAdapter {

    private Logger log = Logger.getLogger(TimeFormatAdapter.class);

    private String pattern = "";
    private String format = "MS";
    private String zone = "";

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String handleBuffer(String[] data, String todo, int columnX,
                               String service, String extPars) {
        String result = "";
		/*try
		{*/

        try {
            if ("".equals(pattern)) {
                log.error("ERROR PARS " + extPars);
                throw new Exception();
            } else {
                long formatTime = 0L;
                long formatTimeOther = 0L;

                if ("SS".equals(pattern)) {
                    long todoDate = Long.parseLong(todo);
                    formatTime = todoDate * 1000;
                } else if ("MS".equals(pattern)) {
                    long todoDate = Long.parseLong(todo);
                    formatTime = todoDate;
                } else if ("WS".equals(pattern)) {
                    long todoDate = Long.parseLong(todo);
                    formatTime = todoDate / 1000;
                } else if ("NS".equals(pattern)) {
                    long todoDate = Long.parseLong(todo);
                    formatTime = todoDate / 1000000;
                } else if ("yyyy-MM-dd HH:mm:ss.SSSSSS".equals(pattern)) {
                    SimpleDateFormat sdf = MyDateUtil.getDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    if (!"".equals(zone)) {
                        sdf.setTimeZone(TimeZone.getTimeZone(zone));
                    }
                    formatTime = sdf.parse(todo.substring(0, 23)).getTime();
                    formatTimeOther = Long.parseLong(todo.substring(23, 26));
                } else if ("yyyy-MM-dd HH:mm:ss.SSSSSSSSS".equals(pattern)) {
                    SimpleDateFormat sdf = MyDateUtil.getDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    if (!"".equals(zone)) {
                        sdf.setTimeZone(TimeZone.getTimeZone(zone));
                    }
                    formatTime = sdf.parse(todo.substring(0, 23)).getTime();
                    formatTimeOther = Long.parseLong(todo.substring(23, 29));
                } else if ("yyyyMMdd HH:mm:ss:SSS".equals(pattern)) {
                    SimpleDateFormat sdf = MyDateUtil.getDateFormat("yyyyMMdd HH:mm:ss");
                    if (!"".equals(zone)) {
                        sdf.setTimeZone(TimeZone.getTimeZone(zone));
                    }

                    formatTime = sdf.parse(todo.substring(0, 17)).getTime();
                } else if ("yyyyMMddHHmmss".equals(pattern)) {
                    SimpleDateFormat sdf = MyDateUtil.getDateFormat("yyyyMMddHHmmss");
                    if (!"".equals(zone)) {
                        sdf.setTimeZone(TimeZone.getTimeZone(zone));
                    }
                    formatTime = sdf.parse(todo).getTime();
                } else if ("NOW".equals(pattern)) {
                    formatTime = System.currentTimeMillis();
                } else if ("SUB".equals(pattern)) {
                    todo = todo.substring(0, (todo.length() - Integer.parseInt(format)));
                    return todo;
                } else {
                    SimpleDateFormat sdf = MyDateUtil.getDateFormat(pattern);
                    if (!"".equals(zone)) {
                        sdf.setTimeZone(TimeZone.getTimeZone(zone));
                    }
                    formatTime = sdf.parse(todo).getTime();
                }

                if ("SS".equals(format)) {
                    result = String.valueOf(formatTime / 1000);
                } else if ("MS".equals(format)) {
                    result = String.valueOf(formatTime);
                    //log.info("alter transform reporttime is : "+result);
                } else if ("WS".equals(format)) {
                    result = String.valueOf(formatTime * 1000 + formatTimeOther);
                } else if ("NS".equals(format)) {
                    result = String.valueOf(formatTime * 1000000 + formatTimeOther);
                } else if ("yyyy-MM-dd HH:mm:ss".equals(format)) {
                    SimpleDateFormat sdf = MyDateUtil.getDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (!"".equals(zone)) {
                        sdf.setTimeZone(TimeZone.getTimeZone(zone));
                    }
                    result = sdf.format(new Date(formatTime));
                } else if ("yyyy-MM-dd HH:mm:ss.SSSSSS".equals(format)) {
                    SimpleDateFormat sdf = MyDateUtil.getDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    if (!"".equals(zone)) {
                        sdf.setTimeZone(TimeZone.getTimeZone(zone));
                    }
                    String formatTimeOtherStr = String.valueOf(formatTimeOther);
                    for (int i = formatTimeOtherStr.length(); i < 3; i++) {
                        formatTimeOtherStr = formatTimeOtherStr + "0";
                    }
                    result = sdf.format(new Date(formatTime)) + formatTimeOtherStr;
                } else if ("yyyy-MM-dd HH:mm:ss.SSSSSSSSS".equals(format)) {
                    SimpleDateFormat sdf = MyDateUtil.getDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    if (!"".equals(zone)) {
                        sdf.setTimeZone(TimeZone.getTimeZone(zone));
                    }
                    String formatTimeOtherStr = String.valueOf(formatTimeOther);
                    for (int i = formatTimeOtherStr.length(); i < 6; i++) {
                        formatTimeOtherStr = formatTimeOtherStr + "0";
                    }
                    result = sdf.format(new Date(formatTime)) + formatTimeOtherStr;
                } else {
                    SimpleDateFormat sdf = MyDateUtil.getDateFormat(format);
                    if (!"".equals(zone)) {
                        sdf.setTimeZone(TimeZone.getTimeZone(zone));
                    }
                    result = sdf.format(new Date(formatTime));
                }

            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

		/*}
		catch (Exception e) {
			//e.printStackTrace();
			//log.error(e.getMessage(),e);
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return result;  */
    }

    @Override
    public String handleBuffer(String[] data, String todo, int columnX,
                               String service) {
        try {
            return handleBuffer(data, todo, columnX, service, ",MS,");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void init(String extPars) {

        String[] pars = StringUtils.splitByWholeSeparatorPreserveAllTokens(extPars, ",");
        if (pars.length == 1) {
            pattern = pars[0];
        } else if (pars.length == 2) {
            pattern = pars[0];
            if (!"".equals(pars[1])) {
                format = pars[1];
            }
        } else if (pars.length == 3) {
            pattern = pars[0];
            if (!"".equals(pars[1])) {
                format = pars[1];
            }
            if (!"".equals(pars[2])) {
                zone = pars[2];
            }
        } else {

        }

    }

    public static void main(String[] args) {
        String[] data = new String[2];
        data[0] = "0";
        data[1] = "1";

        try {
            DataFilterAdapter adapter = (DataFilterAdapter) Class.forName("com.nsn.bighead.glassfish.filterse.adapter.TimeFormatAdapter").newInstance();

            //adapter.init("yyyyMMddHHmmss,yyyy-MM-dd HH:mm:ss");
            //adapter.init("yyyyMMddHHmmss,MS");
            //adapter.init("yyyyMMdd,yyyy-MM-dd HH:mm:ss");
//			adapter.init("SUB,2");
//			adapter.init("yyyyMMddHHmmss,WS");
            //adapter.init("yyyyMMddHHmmss,NS");
            adapter.init("yyyyMMddHHmmss,yyyy-MM-dd HH:mm:ss");

//			long start=System.currentTimeMillis();

            try {
                String re = adapter.handleBuffer(data, "20220322142538", 0, "MLTE_S1U_HTTP", "NOW,MS");

                System.out.println(re);
            } catch (Exception ex43) {
                System.out.println("ssss");
                ex43.printStackTrace();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
