package com.avito.models.kladr;



import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Data
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
