import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: 多核cpu控制
 * @className: CpuControl2
 * @date:2021/9/3 9:58
 */
public class CpuControl2 {
    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        ExecutorService pool = Executors.newFixedThreadPool(rt.availableProcessors());
        System.out.println(rt.availableProcessors());
        for (int i = 0 ;i < rt.availableProcessors(); i++) {
            pool.execute(new Loop());
        }
        pool.shutdown();
    }
}

class Loop implements Runnable {
    @Override
    public void run() {
        //busyTime CPU忙时占用35%
        int busyTime = new Double(100 * 0.35).intValue();
        int idleTime = new Double(100 * (1 - 0.35)).intValue();
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