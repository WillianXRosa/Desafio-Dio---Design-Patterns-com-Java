package com.designerpattern_dio.cadastro_de_produtos_e_descontos.service;

import com.designerpattern_dio.cadastro_de_produtos_e_descontos.interfaces.DescontoStrategy;

public class DescontoService {

	/**Attributes**/
	private DescontoStrategy descontoStrategy;
	
	
	public void setDescontoStrategy(DescontoStrategy descontoStrategy) {
		this.descontoStrategy = descontoStrategy;
	}
	
	public double aplicarDesconto(double preco) {
		return descontoStrategy.calcular(preco);
	}
	
	
}
