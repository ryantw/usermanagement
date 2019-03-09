package io.lker.webstore.common.model.product;

import io.lker.webstore.common.model.catalogue.Category;
import io.lker.webstore.common.model.preorder.PreOrder;
import io.lker.webstore.common.model.user.BaseEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
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
    public Product(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "color")
    private String color;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    // Display product on webpage
    private boolean displayOnWebpage;

    // Won't display on page if Preorder
    private boolean isPreOrder;

    // Added to DB
    @Column(name = "date_added")
    private Date dateAdded = new Date();

    // Last modified, bring in audit log
    @Column(name = "date_modified")
    private Date dateModified = new Date();

    // Date to display on webpage?

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductDescription> descriptions = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductOption> productOptions = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "category_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @OneToOne(mappedBy = "product")
    private PreOrder preOrder;

    public void addProductOption(ProductOption productOption){
        if(productOptions == null)
            this.productOptions = new HashSet<>();
        productOption.setProduct(this);
        productOptions.add(productOption);
    }

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
