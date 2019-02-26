package io.lker.webstore.common.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ProductDescription extends BaseEntity {

    @Builder
    public ProductDescription(Long id, String description, Product product) {
        super(id);
        this.description = description;
        this.product = product;
    }

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

}
