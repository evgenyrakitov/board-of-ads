package com.board_of_ads.models.posting.extra;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс нужен для получения информации по статусам объявления у пользователя.
 * Какого статуса сколько штук. Эта информация нужна для страницы профиля пользователя вкладки "Мои объявления"
 */

@Data
@AllArgsConstructor
public class PostingStatusStatistics {

    PostingStatus status;
    Long count;
}
