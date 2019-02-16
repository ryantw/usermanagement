package io.lker.usermanagement.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Authority")
public class Authority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private AuthorityName name;
}
