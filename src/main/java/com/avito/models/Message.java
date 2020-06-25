package com.avito.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String text;

    @NonNull
    private LocalDate date;

    @NonNull
    private LocalTime time;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User sender;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User recipient;

}
