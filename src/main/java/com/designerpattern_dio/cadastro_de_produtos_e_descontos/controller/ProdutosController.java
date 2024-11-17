package com.designerpattern_dio.cadastro_de_produtos_e_descontos.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designerpattern_dio.cadastro_de_produtos_e_descontos.model.Produtos;
import com.designerpattern_dio.cadastro_de_produtos_e_descontos.repository.ProdutoRepository;
import com.designerpattern_dio.cadastro_de_produtos_e_descontos.util.MensagemErro;

@RestController
@RequestMapping("/produtos")
public class ProdutosController implements Serializable {
  
	private static final long serialVersionUID = 1L;
	
	@Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public Produtos criarProduto(@RequestBody Produtos produto) {
        return produtoRepository.save(produto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produtos> atualizarProduto(@PathVariable Long id, @RequestBody Produtos produtoAtualizado) {
        Optional<Produtos> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produtos produto = produtoExistente.get();            
            
            produto.setNomeProduto(produtoAtualizado.getNomeProduto());
            produto.setPreco(produtoAtualizado.getPreco());           
            
            produtoRepository.save(produto);

            return ResponseEntity.ok(produto); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id) {
        Optional<Produtos> produto = produtoRepository.findById(id);
        
        if (produto.isPresent()) {
            produtoRepository.delete(produto.get());
            return ResponseEntity.ok("Produto deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Produto não encontrado");
        }
    }
        
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
        Optional<Produtos> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        } else {
            MensagemErro erro = new MensagemErro("Produto não cadastrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
        }
    }
    
    @GetMapping("/produtos")
    public ResponseEntity<List<Produtos>> listarProdutos() {
        List<Produtos> listaProdutos = produtoRepository.findAll();
        if (listaProdutos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaProdutos);
        }
    }

    
    
}
