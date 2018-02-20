package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import javax.swing.text.MutableAttributeSet;

/**
 * Created by pv on 19/2/18.
 */
@Configuration
public class MyConfiguration {

	@Bean
	public JedisConnectionFactory connectionFactory(){
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("localhost");
		connectionFactory.setPort(6379);
		return connectionFactory;
	}
}
