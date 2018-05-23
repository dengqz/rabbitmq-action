package com.dqz.rabbitmq.self;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author : Cheese
 * @date : 2018/5/21
 * @description : TODO
 */
public class RBmandatoryTest {
    public static final String ip = "192.168.2.57";
    public static final int port = 5672;
    public static final String username = "admin";
    public static final String password = "123456";

    public static final String queueName = "queue.mandatory.test";
    public static final String exchangeName = "exchange.mandatory.test";
    public static final String routingKey = "mandatory";
    public static final Boolean mandatory = true;
    public static final Boolean immediate = false;

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(ip);
            factory.setPort(port);
            factory.setUsername(username);
            factory.setPassword(password);

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.basicQos(1);
            channel.basicPublish(exchangeName, "", mandatory, immediate, MessageProperties.PERSISTENT_TEXT_PLAIN, "===mandatory===".getBytes());
            System.out.println("测试完毕");
//            channel.close();
//            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
