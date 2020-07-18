package com.br.lughtech.dominio;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class ObjetoEmail {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((conteudo == null) ? 0 : conteudo.hashCode());
		result = prime * result + ((de == null) ? 0 : de.hashCode());
		result = prime * result + ((para == null) ? 0 : para.hashCode());
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
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		if (conteudo == null) {
			if (other.conteudo != null)
				return false;
		} else if (!conteudo.equals(other.conteudo))
			return false;
		if (de == null) {
			if (other.de != null)
				return false;
		} else if (!de.equals(other.de))
			return false;
		if (para == null) {
			if (other.para != null)
				return false;
		} else if (!para.equals(other.para))
			return false;
		return true;
	}

}
