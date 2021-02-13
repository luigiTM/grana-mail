package com.br.lughtech.redis;

import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

import com.br.lughtech.dto.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RecebedorDeMensagensRedis implements StreamListener<String, ObjectRecord<String, String>> {

	@Override
	public void onMessage(ObjectRecord<String, String> message) {
		ObjectMapper mapeadorDeObjetos = new ObjectMapper();
		try {
			EmailDto email = mapeadorDeObjetos.readValue(message.getValue(), EmailDto.class);
			System.out.println(email);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
