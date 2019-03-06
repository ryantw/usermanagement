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
@EqualsAndHashCode(exclude = {"productOption"})
@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "product_sizes")
public class ProductSize extends BaseEntity {

    @Builder
    public ProductSize(Long id, String name, String description, ProductOption productOption){
        super(id);
        this.name = name;
        this.description = description;
        this.productOption = productOption;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToOne
    private ProductOption productOption;
}
