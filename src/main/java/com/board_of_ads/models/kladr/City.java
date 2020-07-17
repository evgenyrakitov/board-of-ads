package com.board_of_ads.models.kladr;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "name_en", length = 60)
    private String nameEn;

    @ManyToOne
    private Region region;
}
