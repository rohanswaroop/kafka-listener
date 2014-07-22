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
				if (System.getProperty("os.name").equals("Mac OS X")) {
					try {
						Runtime.getRuntime().exec("pmset sleepnow");
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
				
				else {
					try {
						Runtime.getRuntime().exec("psshutdown -d -t 0");
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			else if (msg.equals("screenoff")) {
				if (System.getProperty("os.name").equals("Mac OS X")) {
					try {
						Runtime.getRuntime().exec("pmset displaysleepnow");
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
				
				else {
					try {
						Runtime.getRuntime().exec("nircmd monitor off"); //FIX NIRCMD PATH
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			else if (msg.equals("lock")) {
				if (System.getProperty("os.name").equals("Mac OS X")) {
					try {
						Runtime.getRuntime().exec("pmset displaysleepnow");
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
				
				else {
					try {
						Runtime.getRuntime().exec("psshutdown -l -t 0");
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			else if (msg.equals("shutdown")) {
				if (System.getProperty("os.name").equals("Mac OS X")) {
					try {
						Runtime.getRuntime().exec("pmset sleepnow");
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
				
				else {
					try {
						Runtime.getRuntime().exec("psshutdown -k -t 0");
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			messageNum++;
		}
	}
}