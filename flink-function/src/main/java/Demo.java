import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Properties;


/**
 * @author: create by suhy
 * @version: v1.0
 * @description: demo
 * @className: Demo
 * @date:2021/9/2 10:44
 */
public class Demo {

    public static void main(String[] args) throws Exception {

        // 创建Flink执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> stream = env.socketTextStream("10.10.1.97",9999,"\n");

        // Transformations
        // 使用Flink算子对输入流的文本进行操作
        // 按空格切词、计数、分区、设置时间窗口、聚合
        DataStream<Tuple2<String, Integer>> wordCount = stream
                .flatMap((String line, Collector<Tuple2<String, Integer>> collector) -> {
                    String[] tokens = line.split("\\s");
                    // 输出结果 (word, 1)
                    for (String token : tokens) {
                        if (token.length() > 0) {
                            collector.collect(new Tuple2<>(token, 1));
                        }
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                        .keyBy(0).countWindow(5).sum(1);
        // Sink
        wordCount.print();

        // execute
        env.execute("kafka streaming word count");

    }
}

