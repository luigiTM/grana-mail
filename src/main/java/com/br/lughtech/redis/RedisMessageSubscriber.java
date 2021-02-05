package com.br.lughtech.redis;

import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import com.br.lughtech.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisMessageSubscriber implements StreamListener<String, ObjectRecord<String, String>> {

	@Override
	public void onMessage(ObjectRecord<String, String> message) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			EmailDto email = objectMapper.readValue(message.getValue(), EmailDto.class);
			System.out.println(email);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
