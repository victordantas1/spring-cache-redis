package com.vct.springbasicauth.repository;

import com.vct.springbasicauth.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Produto findByNomeIgnoreCase(String nome);

}
