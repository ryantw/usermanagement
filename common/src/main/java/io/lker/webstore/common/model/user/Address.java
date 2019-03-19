package io.lker.webstore.common.model.user;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user"})
@Entity
@Builder
@Slf4j
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(name = "street_1")
    private String street1;

    @Column(name = "street_2")
    private String street2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private int zip;

    @ManyToOne
    private User user;

}
