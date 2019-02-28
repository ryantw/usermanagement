package io.lker.webstore.common.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Slf4j
public class Description implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="language")
    private String language;

    @Column(name="name")
    private String name;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;
}
