package io.lker.webstore.common.model.product;

import io.lker.webstore.common.model.user.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "products")
public class Product extends BaseEntity {

    @Builder
    public Product(Long id, String name, Long groupedProduct, Long productQuantity,
                   Set<ProductDescription> descriptions, Set<ProductSize> productSizes) {
        super(id);
        this.name = name;
        this.groupedProduct = groupedProduct;
        this.productQuantity = productQuantity;
        this.descriptions = descriptions;
        this.productSizes = productSizes;
    }

    private String name;

    // Grouping products LIKE products
    private Long groupedProduct;

    private Long productQuantity;

    private boolean isFeatured;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductDescription> descriptions = new HashSet<ProductDescription>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductSize> productSizes = new HashSet<>();

}
