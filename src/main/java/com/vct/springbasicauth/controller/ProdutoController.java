package com.vct.springbasicauth.controller;

import com.vct.springbasicauth.entities.Produto;
import com.vct.springbasicauth.entities.ProdutoDTO;
import com.vct.springbasicauth.repository.ProdutoRepository;
import com.vct.springbasicauth.service.ProdutoService;
import org.aspectj.runtime.internal.cflowstack.ThreadStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoService service;

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> findAll() throws InterruptedException {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> findOne(@PathVariable Long id) throws InterruptedException {
        return ResponseEntity.ok().body(service.getOne(id).get());
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> addProduct(@RequestBody ProdutoDTO produto) {
        Produto prod = new Produto();
        prod.converte(produto);
        repository.save(prod);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(prod.getId()).toUri();
        return ResponseEntity.created(uri).body(prod);
    }

}
