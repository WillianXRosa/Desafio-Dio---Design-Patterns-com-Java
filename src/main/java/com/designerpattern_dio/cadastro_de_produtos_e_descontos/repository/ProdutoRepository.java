package com.designerpattern_dio.cadastro_de_produtos_e_descontos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.designerpattern_dio.cadastro_de_produtos_e_descontos.model.Produtos;

public interface ProdutoRepository extends JpaRepository<Produtos,Long>{

}
