package com.board_of_ads.models.kladr;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 60)
    private String name;

    @Column(name = "socr", length = 10)
    private String shortType;

    @Column(name = "indx", length = 6)
    private String index;

    @Column(name = "gninmb", length = 4)
    private String gninmb;

    @Column(name = "uno", length = 4)
    private String uno;

    @Column(name = "ocatd", length = 11, unique = true)
    private String ocatd;

    @Column(name = "code", length = 13)
    private String code;

    @ManyToOne
    private Region region;
}
