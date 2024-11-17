package com.designerpattern_dio.cadastro_de_produtos_e_descontos.util;

import java.time.LocalDateTime;

public class MensagemErro {
	 private String mensagem;
	    private LocalDateTime timestamp;

	    public MensagemErro(String mensagemErro) {
			
		}

		public void MensagemErro(String mensagem) {
	        this.mensagem = mensagem;
	        this.timestamp = LocalDateTime.now();
	    }

	    // Getters
	    public String getMensagem() {
	        return mensagem;
	    }

	    public LocalDateTime getTimestamp() {
	        return timestamp;
	    }
}
