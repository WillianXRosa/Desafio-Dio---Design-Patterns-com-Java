package com.designerpattern_dio.cadastro_de_produtos_e_descontos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designerpattern_dio.cadastro_de_produtos_e_descontos.interfaces.DescontoStrategy;
import com.designerpattern_dio.cadastro_de_produtos_e_descontos.model.Desconto;

@Repository
public interface DescontoRepository extends JpaRepository<Desconto,Long>{

	

}
