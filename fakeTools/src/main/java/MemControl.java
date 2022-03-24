import java.util.Vector;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: mem control
 * @className: MemControl
 * @date:2021/9/3 16:36
 */


/**
 * @param
 * @Description: java -Xmx50g -cp fakeTools-1.0-SNAPSHOT.jar MemControl  调整JVM大小
 * @return
 * @date
 */

public class MemControl {

    public boolean memLeft() {
        Long tm = RuntimeUtils.getFreePhysicalMemorySize();
        if (tm < RuntimeUtils.getTotalPhysicalMemorySize() / 2) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        while (true) {
            Vector v = new Vector();
            MemControl mem = new MemControl();
            while (mem.memLeft()) {
                byte b[] = new byte[1048576];
                v.add(b);
            }
            if (mem.memLeft()) {
                v.clear();
            }
        }

    }

}
