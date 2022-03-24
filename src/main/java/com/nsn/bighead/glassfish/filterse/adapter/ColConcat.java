package com.nsn.bighead.glassfish.filterse.adapter;

import com.nsn.bighead.glassfish.filterse.handler.DataFilterAdapter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Arrays;


/**
 * @author: create by suhy
 * @version: v1.0
 * @description: colconcat
 * @className: ColConcat
 * @date:2021/9/7 10:10
 */
public class ColConcat extends DataFilterAdapter {

    private static final long serialVersionUID = -3540598866586729319L;
    private Logger log = Logger.getLogger(ColConcat.class);
    int i = -1;
    int j = -1;

    @Override
    public String handleBuffer(String[] data, String todo, int columnX, String service, String extPars) {

        String result ;

        if (data[i] != null && !"".equals(data[i])) {
            result = data[i].concat("#").concat(data[j]);
            return result;
        } else {
            return data[j];
        }
    }

    @Override
    public String handleBuffer(String[] data, String todo, int columnX, String service) {
        return this.handleBuffer(data, todo, columnX, service, ",");
    }

    @Override
    public void init(String extPars) {
        String[] pars = StringUtils.splitByWholeSeparatorPreserveAllTokens(extPars, ",");
        if (pars.length == 2) {
            i = Integer.parseInt(pars[0]);
            j = Integer.parseInt(pars[1]);
        } else {
            log.error("Wrong InPut parameter");
        }
    }

}
