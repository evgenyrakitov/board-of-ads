package com.board_of_ads.models.posting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Flat extends Posting{

    @Column
    private int floor;
    @Column
    private Float allSquare;
    @Column
    private Float livingSquare;
    @Column
    private int year;
    @Column
    private int rooms;

}
