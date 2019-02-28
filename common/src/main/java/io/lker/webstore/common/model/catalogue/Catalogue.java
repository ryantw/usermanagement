package io.lker.webstore.common.model.catalogue;

import io.lker.webstore.common.model.user.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@Table(name = "catalogue")
public class Catalogue extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "catalogue")
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category){
        if(categories == null)
            return;

        categories.remove(category);

        categories.add(category);
    }

}
