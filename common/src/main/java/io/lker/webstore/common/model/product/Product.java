package io.lker.webstore.common.model.product;

import io.lker.webstore.common.model.Description;
import io.lker.webstore.common.model.catalogue.Category;
import io.lker.webstore.common.model.user.BaseEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"categories"})
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

    private String name;

    // Grouping products LIKE products
    private Long groupedProduct;

    private Long productQuantity;

    private boolean isFeatured;

    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    //private Set<ProductDescription> descriptions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<ProductSize> productSizes;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "products")
    private Set<Category> categories = new HashSet<>();

    /*
    public void addDescription(ProductDescription description){
        if(descriptions == null)
            this.descriptions = new HashSet<>();
        description.setProduct(this);
        descriptions.add(description);
    }*/

    public void addCategory(Category category){
        if(categories == null)
            return;

        categories.remove(category);

        categories.add(category);
    }

}
