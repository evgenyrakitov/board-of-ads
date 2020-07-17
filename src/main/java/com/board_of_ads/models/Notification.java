package com.board_of_ads.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification { //этот класс - базовые нотификации. если делать их прям как в овите, там их несколько типов.
    //и потом, расширяя этот класс, можно получить их все, я полагаю. пока тип нотификаций будет просто Enum.
    //в тот момент, когда будут заведены типы, этот коммент нужно будет удалить, мой далёкий потомок.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime timeCreated;

//    private LocalDateTime timeRead;

    private boolean isRead = false;

    //нижеприведённые типы - это то, что я вижу в овцито. возможно, они различны структурой. возможно, овцито что-то
    //добавит
    public enum Type {
        PayService, PersonalSelection, Stocks, News, TipsOfAvito, MessagesOfMessenger,
        PartnershipOfResearch, SelectedSeller, Recall
    }

    private Type type;
}
