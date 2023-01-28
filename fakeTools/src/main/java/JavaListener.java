import java.util.Vector;
/**
 * @author: create by suhy
 * @version: v1.0
 * @description: JavaListener
 * @className: JavaListener
 * @date:2023/1/28 8:45
 */
public class JavaListener {
    // 定义时间片大小（毫秒）
    public static final double TIME = 1000;
    // 100兆
    public static final double MB100 = 104857600;

    /**
     * 使用方式：
     * java JavaListener -c:80,40 -m:600
     * -c:80,40 表示占用两个核心，使用率分别为80%、40%左右，共计120%左右。
     * -m:600 表示内存占用600MB左右
     *
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            exit();
        }
        String rates = null, memory = null;
        for (String param : args) {
            if (param.startsWith("-c:")) {
                // cpu参数
                param = param.substring(3, param.length());
                if (param != null && param.length() > 0) {
                    rates = param;
                }
            }
            if (param.startsWith("-m:")) {
                // 内存参数
                param = param.substring(3, param.length());
                if (param != null && param.length() > 0) {
                    memory = param;
                }
            }
        }

        if ((rates == null || rates.length() < 1) && (memory == null || memory.length() < 1)) {
            exit();
        }

        if (rates != null && rates.length() > 0) {
            final String[] rateArr = rates.split(",");
            for (String rate : rateArr) {
                // 初始化线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        double r = Double.valueOf(rate) / 100;
                        lineGraph(r);
                    }
                }).start();
            }
        }

        if (memory != null && memory.length() > 0) {
            // 初始化线程
            Integer finalMemory = Integer.valueOf(memory);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    memory(finalMemory);
                }
            }).start();
        }
    }

    private static void exit() {
        System.out.println("请输入参数");
        System.out.println("例如: java xxxxx -c:80,40 -m:600");
        System.out.println("-c表示控制核心，-m表示控制内存");
        System.out.println("-c:80,40 表示占用两个核心，使用率分别为80%、40%左右");
        System.out.println("-m:600 表示内存占用600MB左右（单位兆）");
        System.exit(0);
    }

    private static double random() {
        return (7 + Math.random() * (3)) / 10;
    }

    @SuppressWarnings("unchecked")
    private static void memory(int mb) {
        @SuppressWarnings("rawtypes")
        Vector v = new Vector();
        for (int i = 0; i < ((int) mb / 100); i++) {
            int size = (int) (MB100 * random());
            byte b1[] = new byte[size]; // 100M
            v.add(b1);
        }
        // 内存放入成功后，定时清空队首，再放入
        while (true) {
            try {
                Thread.sleep(1000);
                System.gc();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (v.size() > 0) {
                v.remove(0);
                int size = (int) (MB100 * random());
                byte b1[] = new byte[size]; // 100M
                v.add(b1);
            }
        }
    }

    /**
     * 占用固定比例CPU
     * @param rate 比例
     */
    private static void lineGraph(double rate) {
        while (true) {
            // 1. 调用做点简单工作的方法
            doSomeSimpleWork(rate * TIME);
            // 2. 线程休眠
            try {
                long sleep = (long) (TIME - rate * TIME);
                if (sleep < 1) {
                    sleep = 1L;
                }
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 占用CPU方法
     * @param time
     */
    private static void doSomeSimpleWork(double time) {
        // 计算当前时间和开始时间的差值是否小于时间片的比例
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < time) {
            // do nothing
        }
    }
}
