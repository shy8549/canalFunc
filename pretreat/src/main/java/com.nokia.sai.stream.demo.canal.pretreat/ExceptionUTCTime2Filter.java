package com.nokia.sai.stream.demo.canal.pretreat;

import com.nokia.sai.stream.access.abs.PretreatServiceAbs;
import com.nokia.sai.stream.access.entity.QueueEntity;
import com.nokia.sai.stream.core.exception.AppException;
import com.nokia.sai.stream.core.interfaces.AppLogger;
import com.nokia.sai.stream.core.logger.AppLoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: FILTER THE EXCEPTION UTCTIME
 * @className: ExceptionUTCTime2Filter
 * @date:2021/9/24 9:23
 */
public class ExceptionUTCTime2Filter extends PretreatServiceAbs {

    private static final AppLogger logger = AppLoggerFactory.getLogger(HttpPretreatService.class);
    private static final long serialVersionUID = 1760056881004188046L;


    public Boolean GetTimeFormatBoolean(String inputcolumn) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss-SSSSSS");

        if (inputcolumn == null && "".equals(inputcolumn)) {
            logger.error("sliceTime column is null ,filtered!!! ");
            return false;
        }
        try {
            Date date = sdf.parse(inputcolumn);
            return inputcolumn.equals(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public <T> Object pretreat(T t) {

        QueueEntity queueE = (QueueEntity) t;

        if (GetTimeFormatBoolean(queueE.getSliceTime())) {
            return t;
        } else {
            logger.error("slicetime column format is not 'yyyy-MM-dd HH:mm:ss.SSSSSS',filtered!!! ");
            logger.error("ServiceName" + queueE.getServiceName() + " file name :" + queueE.getPath() + queueE.getFileName() + " ; line number is " + queueE.getLineNum() + " ; sliceTime is : " + queueE.getSliceTime());
            return null;
        }
    }
}
