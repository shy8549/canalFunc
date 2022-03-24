import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: cpu control
 * @className: CpuControl
 * @date:2021/9/3 9:08
 */
public class CpuControl {

    public static void CpuControl(double rate){
        int busyTime = new Double(100 * rate).intValue();
        int idleTime = new Double(100 * (1 - rate)).intValue();

//        long startTime = 0;
//        while (true) {
//            startTime = System.currentTimeMillis();     // busy loop
//            while ((System.currentTimeMillis() - startTime) <= busyTime) {};     // idle
//            // loop
//            try {
//                Thread.sleep(idleTime);
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//        }
        Runtime rt = Runtime.getRuntime();
        ExecutorService pool = Executors.newFixedThreadPool(rt.availableProcessors());
        System.out.println(rt.availableProcessors());
        for (int i = 0 ;i < rt.availableProcessors(); i++) {
            pool.execute(new Loop());
        }
        pool.shutdown();

    }
    public static void main(String[] args) throws InterruptedException{
        CpuControl cp  =  new CpuControl();
        cp.CpuControl(0.35);

    }
}
class Loop2 implements Runnable {
    @Override
    public void run() {
        int busyTime = 20;
        int idleTime = busyTime;
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