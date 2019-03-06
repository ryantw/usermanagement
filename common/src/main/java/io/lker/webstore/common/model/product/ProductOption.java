package io.lker.webstore.common.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.lker.webstore.common.model.user.BaseEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"product"})
@Entity
@Builder
@Slf4j
@Table(name = "product_option")
public class ProductOption extends BaseEntity {

    @Column(name = "color")
    private String color;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "web_quantity")
    private int webQuantity;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "productOption")
    private ProductSize productSize;

    private String image;

    private boolean displayWebpage;

    @JsonIgnore
    @ManyToOne
    private Product product;

}
