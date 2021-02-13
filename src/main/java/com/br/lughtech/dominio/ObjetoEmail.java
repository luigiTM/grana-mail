package com.br.lughtech.dominio;

import java.io.Serializable;

import com.br.lughtech.dto.EmailDto;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class ObjetoEmail implements Serializable {

	private static final long serialVersionUID = 1L;

	Email de;
	Email para;
	String assunto;
	Content conteudo;
	Mail mail;

	public ObjetoEmail() {
	}

	public ObjetoEmail(Email de, Email para, String assunto, Content conteudo) {
		this.de = de;
		this.para = para;
		this.assunto = assunto;
		this.conteudo = conteudo;
		this.mail = new Mail(this.de, this.assunto, this.para, this.conteudo);
	}

	public ObjetoEmail(EmailDto emailDto) {
		this.de = new Email(emailDto.getDe());
		this.para = new Email(emailDto.getPara());
		this.assunto = emailDto.getAssunto();
		this.conteudo = new Content("text/plain", emailDto.getConteudo());
		this.mail = new Mail(this.de, this.assunto, this.para, this.conteudo);
	}

	public Email getDe() {
		return de;
	}

	public void setDe(Email de) {
		this.de = de;
	}

	public Email getPara() {
		return para;
	}

	public void setPara(Email para) {
		this.para = para;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Content getConteudo() {
		return conteudo;
	}

	public void setConteudo(Content conteudo) {
		this.conteudo = conteudo;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjetoEmail other = (ObjetoEmail) obj;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ObjetoEmail [de=" + de.getEmail() + ", para=" + para.getEmail() + ", assunto=" + assunto + ", conteudo=" + conteudo.getValue() + "]";
	}

}
