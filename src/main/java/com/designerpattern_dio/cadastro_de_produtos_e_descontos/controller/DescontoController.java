package com.designerpattern_dio.cadastro_de_produtos_e_descontos.controller;

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

import com.designerpattern_dio.cadastro_de_produtos_e_descontos.interfaces.DescontoStrategy;
import com.designerpattern_dio.cadastro_de_produtos_e_descontos.model.Desconto;
import com.designerpattern_dio.cadastro_de_produtos_e_descontos.model.DescontoFixo;
import com.designerpattern_dio.cadastro_de_produtos_e_descontos.model.DescontoPercentual;
import com.designerpattern_dio.cadastro_de_produtos_e_descontos.repository.DescontoRepository;

@RestController
@RequestMapping("/descontos")
public class DescontoController {

    @Autowired
    private DescontoRepository descontoRepository;
    
    @GetMapping("/descontos/{id}")
    public ResponseEntity<Desconto> buscarDesconto(@PathVariable Long id) {
        Optional<Desconto> desconto = descontoRepository.findById(id);
        if (desconto.isPresent()) {
            return ResponseEntity.ok(desconto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/descontos")
    public ResponseEntity<List<Desconto>> listarDescontos() {
        List<Desconto> descontos = descontoRepository.findAll();
        if (descontos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(descontos);
    }


    @PostMapping
    public ResponseEntity<Desconto> criarDesconto(@RequestBody Desconto desconto) {
        if (desconto instanceof DescontoFixo || desconto instanceof DescontoPercentual) {
            Desconto novoDesconto = descontoRepository.save(desconto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoDesconto);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Desconto> atualizarDesconto(@PathVariable Long id, @RequestBody Desconto novoDesconto) {
        Optional<Desconto> descontoExistente = descontoRepository.findById(id);

        if (descontoExistente.isPresent()) {
            Desconto descontoAtual = descontoExistente.get();

            
            if (descontoAtual.getClass().equals(novoDesconto.getClass())) {
                if (descontoAtual instanceof DescontoFixo) {
                    ((DescontoFixo) descontoAtual).setValorDesconto(((DescontoFixo) novoDesconto).getValorDesconto());
                } else if (descontoAtual instanceof DescontoPercentual) {
                    ((DescontoPercentual) descontoAtual).setPercentual(((DescontoPercentual) novoDesconto).getPercentual());
                }
                Desconto descontoAtualizado = descontoRepository.save(descontoAtual);
                return ResponseEntity.ok(descontoAtualizado);
            } else {
                
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDesconto(@PathVariable Long id) {
        Optional<Desconto> descontoExistente = descontoRepository.findById(id);
        if (descontoExistente.isPresent()) {
            descontoRepository.delete(descontoExistente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


