package com.vct.springbasicauth.service;

import com.vct.springbasicauth.entities.Produto;
import com.vct.springbasicauth.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Cacheable(value = "products")
    public List<Produto> getAll() throws InterruptedException {
        Thread.sleep(2000);
        return repository.findAll();
    }

    @Cacheable(value = "product", key = "#produtoId")
    public Optional<Produto> getOne(Long produtoId) throws InterruptedException {
        Thread.sleep(2000);
        return repository.findById(produtoId);
    }

}
