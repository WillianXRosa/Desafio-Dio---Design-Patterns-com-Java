package com.designerpattern_dio.cadastro_de_produtos_e_descontos.model;

import java.io.Serializable;

import com.designerpattern_dio.cadastro_de_produtos_e_descontos.interfaces.DescontoStrategy;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("FIXO")
public class DescontoFixo extends Desconto implements Serializable, DescontoStrategy {
   
	private static final long serialVersionUID = 1L;

	private double valorDesconto;

    @Override
    public double calcular(double preco) {
        return preco - valorDesconto;
    }

    // Getters e setters      
    public double getValorDesconto() {
		return valorDesconto;
	}
    
    public void setValorDesconto(double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
}

