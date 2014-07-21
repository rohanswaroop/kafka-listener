import java.util.*;
public class RunConsumer {
    public static String topic;
    public static void main(String[] args) {
    	topic = "test";
        ConsumerGroupExample consumer = new ConsumerGroupExample("localhost:2181", "rohan", "test");
        consumer.run(1);
    }
}