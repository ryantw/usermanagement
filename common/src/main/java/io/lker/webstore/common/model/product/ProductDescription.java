package io.lker.webstore.common.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.lker.webstore.common.model.Description;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"product"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
@Table(name="product_description")
public class ProductDescription extends Description {

    @Builder
    public ProductDescription(Long id, String language, String name,
                              String title, String description, String rteDescription,
                              Product product) {
        super(id, language, name, title, description);
        this.rteDescription = rteDescription;
        this.product = product;
    }

    @Column(name = "rte_description")
    @Lob
    private String rteDescription;

    @JsonIgnore
    @ManyToOne
    private Product product;

}
