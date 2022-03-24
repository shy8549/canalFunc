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
 * @description: filter the exception time
 * @className: ExceptionTimeFilter
 * @date:2021/9/23 15:03
 */
public class ExceptionUTCTime1Filter extends PretreatServiceAbs {

    private static final Long serialVersionUID = 1L;
    private static final AppLogger logger = AppLoggerFactory.getLogger(HttpPretreatService.class);


    public Boolean GetTimeFormatBoolean(String inputcolumn) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");

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
    public <T> Object pretreat(T t) throws AppException {

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
