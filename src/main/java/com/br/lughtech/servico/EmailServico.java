package com.br.lughtech.servico;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.br.lughtech.dominio.ObjetoEmail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
public class EmailServico {

	public void enviarEmail(ObjetoEmail email) {
		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(email.getMail().build());
			Response response = sg.api(request);
		} catch (IOException ex) {
			throw new RuntimeException();
		}

	}

}
