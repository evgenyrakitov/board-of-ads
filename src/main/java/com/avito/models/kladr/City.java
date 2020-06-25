package com.avito.models.kladr;



import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Data
@Entity
@Table(name = "city")
public class City {
    private static final Logger logger = LoggerFactory.getLogger(City.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "_NAME", length = 60)
    private String name;

    @Column(name = "_SOCR", length = 10)
    private String shortType;

    @Column(name = "_INDEX", length = 6)
    private String index;

    @Column(name = "_GNIMB", length = 4)
    private String gnimb;

    @Column(name = "_UNO", length = 4)
    private String uno;

    @Column(name = "_OCATD", length = 11, unique = true)
    private String ocatd;

    @Column(name = "_code", length = 13)
    private String code;

    @ManyToOne
    private Region region;


}
