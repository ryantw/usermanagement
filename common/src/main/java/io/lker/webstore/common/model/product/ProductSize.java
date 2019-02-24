package io.lker.webstore.common.model.product;

import io.lker.webstore.common.model.user.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"product"})
@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "product_sizes")
public class ProductSize extends BaseEntity {

    @Builder
    public ProductSize(Long id, String name, String description, Product product){
        super(id);
        this.name = name;
        this.description = description;
        this.product = product;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
