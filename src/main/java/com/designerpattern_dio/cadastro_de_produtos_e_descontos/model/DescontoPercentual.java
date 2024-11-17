package com.designerpattern_dio.cadastro_de_produtos_e_descontos.model;

import java.io.Serializable;

import com.designerpattern_dio.cadastro_de_produtos_e_descontos.interfaces.DescontoStrategy;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("PERCENTUAL")
public class DescontoPercentual extends Desconto implements Serializable, DescontoStrategy {
  
	private static final long serialVersionUID = 1L;

	private double percentual;

    @Override
    public double calcular(double preco) {
        return preco * (1 - percentual / 100);
    }
   
    /**Getters and Setters**/
	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

    
}
