import java.util.Vector;
/**
 * @author: create by suhy
 * @version: v1.0
 * @description:
 * @className: ResouceManageService
 * @date:2022/8/4 15:19
 */

/**
 * 消耗cpu
 * java -XX:InitialHeapSize=64m -XX:MaxHeapSize=64m -XX:NewSize=32m -XX:MaxNewSize=32m -XX:+UseParNewG
 * 消耗内存
 * java -XX:InitialHeapSize=4096m -XX:MaxHeapSize=4096m -XX:NewSize=3072m -XX:MaxNewSize=3072m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:PretenureSizeThreshold=500m -cp "." ResouceManageService -m 1 &
 */

public class ResouceManageService {
    //是否跑消耗内存代码的标记,默认否，即跑消耗Cpu的代码
    private static boolean memConsume = false;

    public static void main(String[] args) throws Exception {

        final ResouceManageService service = new ResouceManageService();
        int num = 1;

        for (int i = 0; i < args.length; i++) {
            //指定-m表明跑消耗内存，指定-c或不指定为消耗Cpu，
            if ("-c".equals(args[i])) {

            } else if ("-m".equals(args[i])) {
                memConsume = true;
                num = Integer.parseInt(args[i + 1]);
                i++;
            }
        }
        if(memConsume){
            service.memConsume(num);
        }else {
            service.cpuConsume();
        }
    }

    //只内存消耗调用这个方法
    @SuppressWarnings("unchecked")
    public void memConsume(int num){

        //执行一个for循环进行新生代内存的申请，共消耗num数量GB
        for(int i=0;i<num * 10;i++){

            @SuppressWarnings("rawtypes")
            Vector v = new Vector();
            byte b1[] = new byte[104857600]; //100M
            v.add(b1);
			/*System.out.println(v);
			Runtime rt = Runtime.getRuntime();
			System.out.println("free memory"+ rt.freeMemory() );*/
        }
        //内存消耗申请完后，执行死循环休眠，让JVM一直占用申请到的内存，达到一直占用num数量GB的效果
        while(true){
            try {
                Thread.sleep(3600000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //cpu消耗方法
    public void cpuConsume(){
        // 角度的分割
        final double SPLIT = 0.01;
        //
        // 2PI分割的次数，也就是2/0.01个，正好是一周
        final int COUNT = (int) (2 / SPLIT);
        final double PI = Math.PI;
        // 时间间隔
        final int INTERVAL = 200;
        long[] busySpan = new long[COUNT];
        long[] idleSpan = new long[COUNT];
        int half = INTERVAL / 2;
        double radian = 0.0;
        for (int i = 0; i < COUNT; i++) {
            busySpan[i] = (long) (half + (Math.sin(PI * radian) * half));
            idleSpan[i] = INTERVAL - busySpan[i];
            radian += SPLIT;
        }
        long startTime = 0;
        int j = 0;
        while (true) {
            j = j % COUNT;
            startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < busySpan[j])
                ;
            try {
                //这里的if控制可以注解掉，让Thread.sleep(idleSpan[j])一直执行。
                //我这里加了if控制是因为希望Cpu一直保存在70%以上工作的效果(小于70不sleep)，If注解掉将以正弦曲线的趋势使用Cpu
                if(idleSpan[j]<70){
                    Thread.sleep(idleSpan[j]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            j++;
        }
    }
}
