package io.lker.webstore.common.model.catalogue;

import io.lker.webstore.common.model.product.Product;
import io.lker.webstore.common.model.user.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"catalogue"})
@Entity
@Slf4j
@Table(name = "category")
public class Category extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne
    private Catalogue catalogue;

    @ManyToMany
    @JoinTable(name = "category_products",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product){
        if(products == null)
            return;

        products.remove(product);

        products.add(product);
    }

}
