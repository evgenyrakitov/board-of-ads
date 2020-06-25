package com.avito.models.kladr;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "region")
public class Region {

    @Id
    private Long id;

    @Column(name = "_NAME", length = 40)
    private String name;

    @Column(name = "SOCR", length = 10)
    private String shortType;

    @Column(name = "_INDEX", length = 6)
    private String index;

    @Column(name = "_GNIMB", length = 4)
    private String gnimb;

    @Column(name = "_UNO", length = 4)
    private String uno;

    @Column(name = "_OCATD", length = 11)
    private String ocatd;

    @Column(name = "_CODE", length = 13)
    private String code;

    /*@OneToMany
    private List<City> cities;*/


}
