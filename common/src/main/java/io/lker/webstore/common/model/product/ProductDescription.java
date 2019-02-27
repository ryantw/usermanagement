package io.lker.webstore.common.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.lker.webstore.common.model.Description;
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
@Table(name="product_description")
public class ProductDescription extends Description {

    @Builder
    public ProductDescription(Long id, String language, String name,
                              String title, String description, Product product) {
        super(id, language, name, title, description);
        this.product = product;
    }

    @JsonIgnore
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

}
