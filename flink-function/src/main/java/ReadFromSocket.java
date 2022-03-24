import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author: create by suhy
 * @version: v1.0
 * @description: read from kafka
 * @className: ReadFromKafka
 * @date:2021/8/31 10:44
 */
public class ReadFromSocket {
    public static void main(String[] args) throws Exception {

        // 创建Flink执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> dataStreamSource = env.socketTextStream("10.10.1.97",9999);
        dataStreamSource.print();

        env.execute();

    }
}




