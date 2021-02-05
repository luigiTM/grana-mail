package com.br.lughtech.configuracao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.StreamMessageListenerContainer.StreamMessageListenerContainerOptions;
import org.springframework.data.redis.stream.Subscription;

@Configuration
public class RedisConfig {

	@Autowired
	private StreamListener<String, ObjectRecord<String, String>> streamListener;

	private static final String STREAM_KEY = "emails";

	@Bean
	LettuceConnectionFactory lettuceConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName("172.23.188.133");
		config.setPort(6379);
		return new LettuceConnectionFactory(config);
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		final RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(lettuceConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public Subscription subscription() throws UnknownHostException {
		StreamMessageListenerContainerOptions<String, ObjectRecord<String, String>> options = StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder().serializer(new StringRedisSerializer()).keySerializer(new StringRedisSerializer()).hashKeySerializer(new StringRedisSerializer()).hashValueSerializer(new StringRedisSerializer()).pollTimeout(Duration.ofSeconds(1)).targetType(String.class).build();
		StreamMessageListenerContainer<String, ObjectRecord<String, String>> listenerContainer = StreamMessageListenerContainer.create(redisTemplate().getConnectionFactory(), options);
		Subscription subscription = listenerContainer.receiveAutoAck(Consumer.from(STREAM_KEY, InetAddress.getLocalHost().getHostName()), StreamOffset.create(STREAM_KEY, ReadOffset.lastConsumed()), streamListener);
		listenerContainer.start();
		return subscription;
	}

}
