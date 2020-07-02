package com.avito.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String text;

    @NonNull
    private LocalDateTime date;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User author;

    @NonNull
    private ReadStatus readStatus = ReadStatus.NOT_READ;

    public enum ReadStatus {
        READ,
        NOT_READ
    }

}
