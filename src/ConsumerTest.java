import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.*;
import java.util.*;

public class ConsumerTest implements Runnable {
	@SuppressWarnings("rawtypes")
	private KafkaStream m_stream;
	@SuppressWarnings("rawtypes")
	public ConsumerTest(KafkaStream a_stream, int a_threadNumber) {
		m_stream = a_stream;
	}

	int messageNum = 1;

	@SuppressWarnings("unchecked")
	public void run() {
		ConsumerIterator<byte[], byte[]> it = m_stream.iterator();

		while (it.hasNext()) {
			String msg = new String(it.next().message());
			System.out.println("Message " + messageNum + ": " + msg);

			if (msg.equals("sleep")) {
				try {
					Process proc=Runtime.getRuntime().exec("pmset sleepnow");
				}
				catch(IOException e) {
				}
			}
			messageNum++;
		}
	}
}