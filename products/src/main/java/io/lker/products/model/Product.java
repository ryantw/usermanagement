package io.lker.products.model;

import io.lker.usermanagement.model.user.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductDescription> descriptions = new HashSet<ProductDescription>();

}
