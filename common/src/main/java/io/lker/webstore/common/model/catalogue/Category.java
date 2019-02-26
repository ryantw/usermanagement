package io.lker.webstore.common.model.catalogue;

import io.lker.webstore.common.model.user.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "category")
public class Category extends BaseEntity {

}
