package io.lker.webstore.common.model.product;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
public class ProductDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

}
