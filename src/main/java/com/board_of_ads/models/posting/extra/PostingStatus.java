package com.board_of_ads.models.posting.extra;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Posting_Statuses")
public class PostingStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Имя - используется для удобства, уникальное как и id
     */
    @Column(name = "name", unique = true)
    private String name;

    /**
     * Описание (название) в единственном числе
     */
    @Column(name = "description_single")
    private String description_single;

    /**
     * Описание (название) во множественном числе.
     */
    @Column(name = "description_many")
    private String description_many;
}
