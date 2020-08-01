package com.br.lughtech.recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.lughtech.EmailDto;
import com.br.lughtech.dominio.ObjetoEmail;
import com.br.lughtech.servico.EmailServico;

@RestController
@RequestMapping(value = "/email")
public class EmailRecurso {

	@Autowired
	private EmailServico emailServico;

	@RequestMapping(value = "enviar", method = RequestMethod.POST)
	public ResponseEntity<Void> enviarEmail(@RequestBody EmailDto email) {
		ObjetoEmail objetoEmail = new ObjetoEmail(email);
		emailServico.enviarEmail(objetoEmail);
		return ResponseEntity.noContent().build();
	}

}
