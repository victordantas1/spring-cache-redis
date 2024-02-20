package com.vct.springbasicauth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Long id;
    @Column(name = "produto_nome", nullable = false)
    private String nome;
    @Column(name = "produto_preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    public void converte(ProdutoDTO produtoDTO) {
        this.setNome(produtoDTO.nome());
        this.setPreco(produtoDTO.preco());
    }

}
