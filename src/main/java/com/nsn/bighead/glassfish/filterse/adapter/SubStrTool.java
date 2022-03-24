package com.nsn.bighead.glassfish.filterse.adapter;

import com.nsn.bighead.glassfish.filterse.handler.DataFilterAdapter;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;


/**
 * @author: create by suhy
 * @version: v1.0
 * @description: com.nsn.bighead.glassfish.filterse.adapter
 * @date:2021/8/17
 */
public class SubStrTool extends DataFilterAdapter {
    private Logger log = Logger.getLogger(SubStrTool.class);
    private int sub_start = -1;
    private int sub_end = -1;

    /**
     *
     * @param data  原始数据
     * @param todo  要操作的字段
     * @param columnX  要操作的字段所属的列号
     * @param service  业务名称
     * @param extPars   扩展参数
     * @return      返回操作后的值
     */
    @Override
    public String handleBuffer(String[] data, String todo, int columnX, String service, String extPars) {
        String result ;
        if (todo != null && !"".equals(todo)) {
            if(todo.length()<8){
                result = todo;
//                log.info("the length of column "+columnX+" is less than 8,return the original data : "+todo);
            }else {
                result = todo.substring(sub_start, sub_end);
            }

            return result;
        } else {
//            log.info("serviceName : " + service + " substr column "+columnX+" is 'null' ,please check data!!! ");
//            log.info("serviceName : " + service + " substr column is 'null'  : " + Arrays.toString(data));
            return "";
        }
    }

    @Override
    public String handleBuffer(String[] data, String todo, int columnX, String service) {
        return this.handleBuffer(data, todo, columnX, service, ",");
    }

    /**
     * @param extPars: eg:"3,20"，从第四位开始截取到第21位
     * @return void
     * @Description: 初始化第五个参数，定义截取位置
     * @author
     * @date
     */
    @Override
    public void init(String extPars) {
        String[] pars = StringUtils.splitByWholeSeparatorPreserveAllTokens(extPars, ",");
        if (pars.length == 2) {
            sub_start = Integer.parseInt(pars[0]);
            sub_end = Integer.parseInt(pars[1]);
        } else {
            log.error("Wrong InPut parameter");
        }
    }

    public static void main(String[] args) {
        String[] data = new String[2];
        data[0] = "0";
        data[1] = "1";
        try {
            DataFilterAdapter adapter = (DataFilterAdapter) Class.forName("com.nsn.bighead.glassfish.filterse.adapter.SubStrTool").newInstance();
            adapter.init("1,8");
            String re = adapter.handleBuffer(data, "01234567890", 0, "MLTE_S1U_HTTP", "1,5");

            System.out.println(re);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
