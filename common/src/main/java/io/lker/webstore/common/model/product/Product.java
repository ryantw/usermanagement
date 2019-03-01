package io.lker.webstore.common.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.lker.webstore.common.model.catalogue.Category;
import io.lker.webstore.common.model.user.BaseEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "product")
public class Product extends BaseEntity {

    @Builder
    public Product(Long id, String name, Long groupedProduct,
                   Long productQuantity, Set<ProductSize> productSizes) {
        super(id);
        this.name = name;
        this.groupedProduct = groupedProduct;
        this.productQuantity = productQuantity;
        this.productSizes = productSizes;
    }

    @Column(name = "name")
    private String name;

    // Grouping products LIKE products
    private Long groupedProduct;

    private Long productQuantity;

    private boolean isFeatured;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductDescription> descriptions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<ProductSize> productSizes;


    @ManyToMany
    @JoinTable(name = "category_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();


    public void addDescription(ProductDescription description){
        if(descriptions == null)
            this.descriptions = new HashSet<>();
        description.setProduct(this);
        descriptions.add(description);
    }

    public void addCategory(Category category){
        if(categories == null)
            return;
        categories.remove(category);
        categories.add(category);
    }

}
