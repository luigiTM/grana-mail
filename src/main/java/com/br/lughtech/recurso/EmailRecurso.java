package com.br.lughtech.recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.lughtech.dominio.ObjetoEmail;
import com.br.lughtech.dto.EmailDto;
import com.br.lughtech.servico.EmailServico;

@RestController
@RequestMapping(value = "/email")
public class EmailRecurso {

	@Autowired
	private EmailServico emailServico;

	@PostMapping(value = "enviar")
	public ResponseEntity<Void> enviarEmail(@RequestBody EmailDto email) {
		emailServico.enviarEmail(new ObjetoEmail(email));
		return ResponseEntity.noContent().build();
	}

}
