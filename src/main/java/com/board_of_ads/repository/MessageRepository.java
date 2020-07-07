package com.board_of_ads.repository;

import com.board_of_ads.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT count (m) FROM Message m WHERE m.readStatus = :readStatus AND m.posting.user.id = :id")
    long countMessagesByReadStatusAndRecipientId(@Param("readStatus") Message.ReadStatus readStatus, @Param("id") long id);

}