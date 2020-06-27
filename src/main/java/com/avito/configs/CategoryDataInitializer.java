package com.avito.configs;

import com.avito.models.Category;
import com.avito.service.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class CategoryDataInitializer {

    private final CategoryService categoryService;

    @PostConstruct
    private void init() {

        if (categoryService.getAllCategories().size() > 8) {
            return;
        }

        Set<Category> emptySet = Collections.EMPTY_SET;

        //3 вкладка Работа
        HashSet<Category> categories3levelJobVacanc = new HashSet<>();
        HashSet<Category> categories3levelJobResume = new HashSet<>();

        categories3levelJobVacanc.add(new Category("IT, интернет, телеком", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("IT, интернет, телеком", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Автомобильный бизнес", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Автомобильный бизнес", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Административная работа", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Административная работа", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Банки, инвестиции", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Банки, инвестиции", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Без опыта, студенты", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Без опыта, студенты", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Бухгалтерия, финансы", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Бухгалтерия, финансы", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Высший менеджмент", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Высший менеджмент", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Госслужба, НКО", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Госслужба, НКО", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Домашний персонал", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Домашний персонал", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("ЖКХ, эксплуатация", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("ЖКХ, эксплуатация", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Искусство, развлечения", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Искусство, развлечения", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Консультирование", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Консультирование", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Маркетинг, реклама, PR", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Маркетинг, реклама, PR", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Медицина, фармацевтика", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Медицина, фармацевтика", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Образование, наука", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Образование, наука", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Охрана, безопасность", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Охрана, безопасность", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Продажи", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Продажи", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Производство, сырьё, с/х", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Производство, сырьё, с/х", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Страхование", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Страхование", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Строительство", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Строительство", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Транспорт, логистика", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Транспорт, логистика", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Туризм, рестораны", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Туризм, рестораны", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Фитнес, салоны красоты", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Фитнес, салоны красоты", "Вакансии", emptySet));
        categories3levelJobVacanc.add(new Category("Юриспруденция", "Резюме", emptySet));
        categories3levelJobResume.add(new Category("Юриспруденция", "Вакансии", emptySet));

        //2 вкладка Работа
        HashSet<Category> categories2levelJob = new HashSet<>();
        categories2levelJob.add(new Category("Резюме", "Работа", categories3levelJobResume));
        categories2levelJob.add(new Category("Вакансии", "Работа", categories3levelJobVacanc));

        //3 вкладка Недвижимость
        HashSet<Category> categories3levelDomFlat = new HashSet<>();
        HashSet<Category> categories3levelDomRoom = new HashSet<>();
        HashSet<Category> categories3levelDomRancho = new HashSet<>();
        HashSet<Category> categories3levelDomPlace = new HashSet<>();
        HashSet<Category> categories3levelDomAuto = new HashSet<>();
        HashSet<Category> categories3levelDomComerc = new HashSet<>();
        HashSet<Category> categories3levelDomAbroad = new HashSet<>();
        categories3levelDomFlat.add(new Category("Продам", "Квартиры", emptySet));
        categories3levelDomRoom.add(new Category("Продам", "Комнаты", emptySet));
        categories3levelDomAuto.add(new Category("Продам", "Дома, дачи, коттеджи", emptySet));
        categories3levelDomPlace.add(new Category("Продам", "Земельные участки", emptySet));
        categories3levelDomRancho.add(new Category("Продам", "Гаражи и машиноместа", emptySet));
        categories3levelDomComerc.add(new Category("Продам", "Коммерческая недвижимость", emptySet));
        categories3levelDomAbroad.add(new Category("Продам", "Недвижимость за рубежом", emptySet));
        categories3levelDomFlat.add(new Category("Сдам", "Квартиры", emptySet));
        categories3levelDomRoom.add(new Category("Сдам", "Комнаты", emptySet));
        categories3levelDomAuto.add(new Category("Сдам", "Дома, дачи, коттеджи", emptySet));
        categories3levelDomPlace.add(new Category("Сдам", "Земельные участки", emptySet));
        categories3levelDomRancho.add(new Category("Сдам", "Гаражи и машиноместа", emptySet));
        categories3levelDomComerc.add(new Category("Сдам", "Коммерческая недвижимость", emptySet));
        categories3levelDomAbroad.add(new Category("Сдам", "Недвижимость за рубежом", emptySet));
        categories3levelDomFlat.add(new Category("Куплю", "Квартиры", emptySet));
        categories3levelDomRoom.add(new Category("Куплю", "Комнаты", emptySet));
        categories3levelDomAuto.add(new Category("Куплю", "Дома, дачи, коттеджи", emptySet));
        categories3levelDomPlace.add(new Category("Куплю", "Земельные участки", emptySet));
        categories3levelDomRancho.add(new Category("Куплю", "Гаражи и машиноместа", emptySet));
        categories3levelDomComerc.add(new Category("Куплю", "Коммерческая недвижимость", emptySet));
        categories3levelDomAbroad.add(new Category("Куплю", "Недвижимость за рубежом", emptySet));
        categories3levelDomFlat.add(new Category("Сниму", "Квартиры", emptySet));
        categories3levelDomRoom.add(new Category("Сниму", "Комнаты", emptySet));
        categories3levelDomAuto.add(new Category("Сниму", "Дома, дачи, коттеджи", emptySet));
        categories3levelDomPlace.add(new Category("Сниму", "Земельные участки", emptySet));
        categories3levelDomRancho.add(new Category("Сниму", "Гаражи и машиноместа", emptySet));
        categories3levelDomComerc.add(new Category("Сниму", "Коммерческая недвижимость", emptySet));
        categories3levelDomAbroad.add(new Category("Сниму", "Недвижимость за рубежом", emptySet));

        //2 вкладка Недвижимость
        HashSet<Category> categories2levelDom = new HashSet<>();
        categories2levelDom.add(new Category("Квартиры", "Недвижимость", categories3levelDomFlat));
        categories2levelDom.add(new Category("Комнаты", "Недвижимость", categories3levelDomRoom));
        categories2levelDom.add(new Category("Дома, дачи, коттеджи", "Недвижимость", categories3levelDomRancho));
        categories2levelDom.add(new Category("Земельные участки", "Недвижимость", categories3levelDomPlace));
        categories2levelDom.add(new Category("Гаражи и машиноместа", "Недвижимость", categories3levelDomAuto));
        categories2levelDom.add(new Category("Коммерческая недвижимость", "Недвижимость", categories3levelDomComerc));
        categories2levelDom.add(new Category("Недвижимость за рубежом", "Недвижимость", categories3levelDomAbroad));


        //3 вкладка водный транспорт
        HashSet<Category> categories3levelWater = new HashSet<>();
        categories3levelWater.add(new Category("Вёсельные лодки", "Водный транспорт", emptySet));
        categories3levelWater.add(new Category("Гидроциклы", "Водный транспорт", emptySet));
        categories3levelWater.add(new Category("Катера и яхты", "Водный транспорт", emptySet));
        categories3levelWater.add(new Category("Каяки и каноэ", "Водный транспорт", emptySet));
        categories3levelWater.add(new Category("Моторные лодки", "Водный транспорт", emptySet));
        categories3levelWater.add(new Category("Надувные лодки", "Водный транспорт", emptySet));

        //3 вкладка спец транспорт
        HashSet<Category> categories3levelSpec = new HashSet<>();
        categories3levelSpec.add(new Category("Автобусы", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Автодома", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Автокраны", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Бульдозеры", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Грузовики", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Коммунальная техника", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Лёгкий транспорт", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Погрузчики", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Прицепы", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Сельхозтехника", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Строительная техника", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Техника для лесозаготовки", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Тягачи", "Грузовики и спецтехника", emptySet));
        categories3levelSpec.add(new Category("Экскаваторы", "Грузовики и спецтехника", emptySet));

        //3 вкладка  мотоциклы
        HashSet<Category> categories3levelMoto = new HashSet<>();
        categories3levelMoto.add(new Category("Багги", "Мотоциклы и мототехника", emptySet));
        categories3levelMoto.add(new Category("Вездеходы", "Мотоциклы и мототехника", emptySet));
        categories3levelMoto.add(new Category("Картинг", "Мотоциклы и мототехника", emptySet));
        categories3levelMoto.add(new Category("Квадроциклы", "Мотоциклы и мототехника", emptySet));
        categories3levelMoto.add(new Category("Мопеды и скутеры", "Мотоциклы и мототехника", emptySet));
        categories3levelMoto.add(new Category("Мотоциклы", "Мотоциклы и мототехника", emptySet));
        categories3levelMoto.add(new Category("Снегоходы", "Мотоциклы и мототехника", emptySet));

        //3 вкладка  автомобили
        HashSet<Category> categories3levelAutomob = new HashSet<>();
        categories3levelAutomob.add(new Category("С пробегом", "Автомобили", emptySet));
        categories3levelAutomob.add(new Category("Новый", "Автомобили", emptySet));

        //2 вкладка транспорт
        HashSet<Category> categories2levelTransp = new HashSet<>();
        categories2levelTransp.add(new Category("Автомобили", "Транспорт", categories3levelAutomob));
        categories2levelTransp.add(new Category("Мотоциклы и мототехника", "Транспорт", categories3levelMoto));
        categories2levelTransp.add(new Category("Грузовики и спецтехника", "Транспорт", categories3levelSpec));
        categories2levelTransp.add(new Category("Водный транспорт", "Транспорт", categories3levelWater));
        categories2levelTransp.add(new Category("Запчасти и аксессуары", "Транспорт", emptySet));

        //1 вкладка
        addCategory(new Category("Транспорт", "Категория", categories2levelTransp));
        addCategory(new Category("Недвижимость", "Категория", categories2levelDom));
        addCategory(new Category("Работа", "Категория", categories2levelJob));
        addCategory(new Category("Услуги", "Категория", emptySet));
        addCategory(new Category("Для дома и дачи", "Категория", emptySet));
        addCategory(new Category("Личные вещи", "Категория", emptySet));
        addCategory(new Category("Бытовая электроника", "Категория", emptySet));
        addCategory(new Category("Хобби и отдых", "Категория", emptySet));
        addCategory(new Category("Животные", "Категория", emptySet));
        addCategory(new Category("Для бизнеса", "Категория", emptySet));
    }


    private void addCategory(Category name) {
        categoryService.addCategory(name);
    }

}
