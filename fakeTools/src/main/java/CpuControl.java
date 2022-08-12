import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: CpuControl
 * @className: CpuControl
 * @date:2021/9/3 14:28
 */
public class CpuControl {

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        ExecutorService pool = Executors.newFixedThreadPool(rt.availableProcessors());
        for (int i = 0; i < rt.availableProcessors(); i++) {
            pool.execute(new Loop());
        }
        pool.shutdown();
    }
// 使用args[] 来使用变量来控制cpu
/*    public static void main(String[] args)
    {
        long startTime = 0;// 开始时间
        int busyTime = Integer.parseInt(args[0]);// 繁忙时间
        int idleTime = Integer.parseInt(args[1]);// 空闲时间
        while (true)
        {
            startTime = System.currentTimeMillis();     // CPU繁忙
            while (System.currentTimeMillis() - startTime <= busyTime);   // CPU空闲
            try
            {
                Thread.sleep(idleTime);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }*/
}

class Loop implements Runnable {
    @Override
    public void run() {

        int busyTime = 35;
        int idleTime = 65;

        while (true) {
            long startTime = System.currentTimeMillis();
            while ((System.currentTimeMillis() - startTime) <= busyTime) ;
            try {
                Thread.sleep(idleTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
