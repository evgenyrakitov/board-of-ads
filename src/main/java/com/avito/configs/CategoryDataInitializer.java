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

        categories3levelJobVacanc.add(new Category("IT, интернет, телеком", emptySet));
        categories3levelJobResume.add(new Category("IT, интернет, телеком", emptySet));
        categories3levelJobVacanc.add(new Category("Автомобильный бизнес", emptySet));
        categories3levelJobResume.add(new Category("Автомобильный бизнес", emptySet));
        categories3levelJobVacanc.add(new Category("Административная работа", emptySet));
        categories3levelJobResume.add(new Category("Административная работа", emptySet));
        categories3levelJobVacanc.add(new Category("Банки, инвестиции", emptySet));
        categories3levelJobResume.add(new Category("Банки, инвестиции", emptySet));
        categories3levelJobVacanc.add(new Category("Без опыта, студенты", emptySet));
        categories3levelJobResume.add(new Category("Без опыта, студенты", emptySet));
        categories3levelJobVacanc.add(new Category("Бухгалтерия, финансы", emptySet));
        categories3levelJobResume.add(new Category("Бухгалтерия, финансы", emptySet));
        categories3levelJobVacanc.add(new Category("Высший менеджмент", emptySet));
        categories3levelJobResume.add(new Category("Высший менеджмент", emptySet));
        categories3levelJobVacanc.add(new Category("Госслужба, НКО", emptySet));
        categories3levelJobResume.add(new Category("Госслужба, НКО", emptySet));
        categories3levelJobVacanc.add(new Category("Домашний персонал", emptySet));
        categories3levelJobResume.add(new Category("Домашний персонал", emptySet));
        categories3levelJobVacanc.add(new Category("ЖКХ, эксплуатация", emptySet));
        categories3levelJobResume.add(new Category("ЖКХ, эксплуатация", emptySet));
        categories3levelJobVacanc.add(new Category("Искусство, развлечения", emptySet));
        categories3levelJobResume.add(new Category("Искусство, развлечения", emptySet));
        categories3levelJobVacanc.add(new Category("Консультирование", emptySet));
        categories3levelJobResume.add(new Category("Консультирование", emptySet));
        categories3levelJobVacanc.add(new Category("Маркетинг, реклама, PR", emptySet));
        categories3levelJobResume.add(new Category("Маркетинг, реклама, PR", emptySet));
        categories3levelJobVacanc.add(new Category("Медицина, фармацевтика", emptySet));
        categories3levelJobResume.add(new Category("Медицина, фармацевтика", emptySet));
        categories3levelJobVacanc.add(new Category("Образование, наука", emptySet));
        categories3levelJobResume.add(new Category("Образование, наука", emptySet));
        categories3levelJobVacanc.add(new Category("Охрана, безопасность", emptySet));
        categories3levelJobResume.add(new Category("Охрана, безопасность", emptySet));
        categories3levelJobVacanc.add(new Category("Продажи", emptySet));
        categories3levelJobResume.add(new Category("Продажи", emptySet));
        categories3levelJobVacanc.add(new Category("Производство, сырьё, с/х", emptySet));
        categories3levelJobResume.add(new Category("Производство, сырьё, с/х", emptySet));
        categories3levelJobVacanc.add(new Category("Страхование", emptySet));
        categories3levelJobResume.add(new Category("Страхование", emptySet));
        categories3levelJobVacanc.add(new Category("Строительство", emptySet));
        categories3levelJobResume.add(new Category("Строительство", emptySet));
        categories3levelJobVacanc.add(new Category("Транспорт, логистика", emptySet));
        categories3levelJobResume.add(new Category("Транспорт, логистика", emptySet));
        categories3levelJobVacanc.add(new Category("Туризм, рестораны", emptySet));
        categories3levelJobResume.add(new Category("Туризм, рестораны", emptySet));
        categories3levelJobVacanc.add(new Category("Фитнес, салоны красоты", emptySet));
        categories3levelJobResume.add(new Category("Фитнес, салоны красоты", emptySet));
        categories3levelJobVacanc.add(new Category("Юриспруденция", emptySet));
        categories3levelJobResume.add(new Category("Юриспруденция", emptySet));

        //2 вкладка Работа
        HashSet<Category> categories2levelJob = new HashSet<>();
        categories2levelJob.add(new Category("Резюме", categories3levelJobResume));
        categories2levelJob.add(new Category("Вакансии", categories3levelJobVacanc));

        //3 вкладка Недвижимость
        HashSet<Category> categories3levelDomFlat = new HashSet<>();
        HashSet<Category> categories3levelDomRoom = new HashSet<>();
        HashSet<Category> categories3levelDomRancho = new HashSet<>();
        HashSet<Category> categories3levelDomPlace = new HashSet<>();
        HashSet<Category> categories3levelDomAuto = new HashSet<>();
        HashSet<Category> categories3levelDomComerc = new HashSet<>();
        HashSet<Category> categories3levelDomAbroad = new HashSet<>();
        categories3levelDomFlat.add(new Category("Продам", emptySet));
        categories3levelDomRoom.add(new Category("Продам", emptySet));
        categories3levelDomAuto.add(new Category("Продам", emptySet));
        categories3levelDomPlace.add(new Category("Продам", emptySet));
        categories3levelDomRancho.add(new Category("Продам", emptySet));
        categories3levelDomComerc.add(new Category("Продам", emptySet));
        categories3levelDomAbroad.add(new Category("Продам", emptySet));
        categories3levelDomFlat.add(new Category("Сдам", emptySet));
        categories3levelDomRoom.add(new Category("Сдам", emptySet));
        categories3levelDomAuto.add(new Category("Сдам", emptySet));
        categories3levelDomPlace.add(new Category("Сдам", emptySet));
        categories3levelDomRancho.add(new Category("Сдам", emptySet));
        categories3levelDomComerc.add(new Category("Сдам", emptySet));
        categories3levelDomAbroad.add(new Category("Сдам", emptySet));
        categories3levelDomFlat.add(new Category("Куплю", emptySet));
        categories3levelDomRoom.add(new Category("Куплю", emptySet));
        categories3levelDomAuto.add(new Category("Куплю", emptySet));
        categories3levelDomPlace.add(new Category("Куплю", emptySet));
        categories3levelDomRancho.add(new Category("Куплю", emptySet));
        categories3levelDomComerc.add(new Category("Куплю", emptySet));
        categories3levelDomAbroad.add(new Category("Куплю", emptySet));
        categories3levelDomFlat.add(new Category("Сниму", emptySet));
        categories3levelDomRoom.add(new Category("Сниму", emptySet));
        categories3levelDomAuto.add(new Category("Сниму", emptySet));
        categories3levelDomPlace.add(new Category("Сниму", emptySet));
        categories3levelDomRancho.add(new Category("Сниму", emptySet));
        categories3levelDomComerc.add(new Category("Сниму", emptySet));
        categories3levelDomAbroad.add(new Category("Сниму", emptySet));

        //2 вкладка Недвижимость
        HashSet<Category> categories2levelDom = new HashSet<>();
        categories2levelDom.add(new Category("Квартиры", categories3levelDomFlat));
        categories2levelDom.add(new Category("Комнаты", categories3levelDomRoom));
        categories2levelDom.add(new Category("Дома, дачи, коттеджи", categories3levelDomRancho));
        categories2levelDom.add(new Category("Земельные участки", categories3levelDomPlace));
        categories2levelDom.add(new Category("Гаражи и машиноместа", categories3levelDomAuto));
        categories2levelDom.add(new Category("Коммерческая недвижимость", categories3levelDomComerc));
        categories2levelDom.add(new Category("Недвижимость за рубежом", categories3levelDomAbroad));


        //3 вкладка водный транспорт
        HashSet<Category> categories3levelWater = new HashSet<>();
        categories3levelWater.add(new Category("Вёсельные лодки", emptySet));
        categories3levelWater.add(new Category("Гидроциклы", emptySet));
        categories3levelWater.add(new Category("Катера и яхты", emptySet));
        categories3levelWater.add(new Category("Каяки и каноэ", emptySet));
        categories3levelWater.add(new Category("Моторные лодки", emptySet));
        categories3levelWater.add(new Category("Надувные лодки", emptySet));

        //3 вкладка спец транспорт
        HashSet<Category> categories3levelSpec = new HashSet<>();
        categories3levelSpec.add(new Category("Автобусы", emptySet));
        categories3levelSpec.add(new Category("Автодома", emptySet));
        categories3levelSpec.add(new Category("Автокраны", emptySet));
        categories3levelSpec.add(new Category("Бульдозеры", emptySet));
        categories3levelSpec.add(new Category("Грузовики", emptySet));
        categories3levelSpec.add(new Category("Коммунальная техника", emptySet));
        categories3levelSpec.add(new Category("Лёгкий транспорт", emptySet));
        categories3levelSpec.add(new Category("Погрузчики", emptySet));
        categories3levelSpec.add(new Category("Прицепы", emptySet));
        categories3levelSpec.add(new Category("Сельхозтехника", emptySet));
        categories3levelSpec.add(new Category("Строительная техника", emptySet));
        categories3levelSpec.add(new Category("Техника для лесозаготовки", emptySet));
        categories3levelSpec.add(new Category("Тягачи", emptySet));
        categories3levelSpec.add(new Category("Экскаваторы", emptySet));

        //3 вкладка  мотоциклы
        HashSet<Category> categories3levelMoto = new HashSet<>();
        categories3levelMoto.add(new Category("Багги", emptySet));
        categories3levelMoto.add(new Category("Вездеходы", emptySet));
        categories3levelMoto.add(new Category("Картинг", emptySet));
        categories3levelMoto.add(new Category("Квадроциклы", emptySet));
        categories3levelMoto.add(new Category("Мопеды и скутеры", emptySet));
        categories3levelMoto.add(new Category("Мотоциклы", emptySet));
        categories3levelMoto.add(new Category("Снегоходы", emptySet));

        //3 вкладка  автомобили
        HashSet<Category> categories3levelAutomob = new HashSet<>();
        categories3levelAutomob.add(new Category("С пробегом", emptySet));
        categories3levelAutomob.add(new Category("Новый", emptySet));

        //2 вкладка транспорт
        HashSet<Category> categories2levelTransp = new HashSet<>();
        categories2levelTransp.add(new Category("Автомобили", categories3levelAutomob));
        categories2levelTransp.add(new Category("Мотоциклы и мототехника", categories3levelMoto));
        categories2levelTransp.add(new Category("Грузовики и спецтехника", categories3levelSpec));
        categories2levelTransp.add(new Category("Водный транспорт", categories3levelWater));
        categories2levelTransp.add(new Category("Запчасти и аксессуары", emptySet));

        //1 вкладка
        addCategory(new Category("Транспорт", categories2levelTransp));
        addCategory(new Category("Недвижимость", categories2levelDom));
        addCategory(new Category("Работа", categories2levelJob));
        addCategory(new Category("Услуги", emptySet));
        addCategory(new Category("Для дома и дачи", emptySet));
        addCategory(new Category("Бытовая электроника", emptySet));
        addCategory(new Category("Хобби и отдых", emptySet));
        addCategory(new Category("Животные", emptySet));
        addCategory(new Category("Для бизнеса", emptySet));
    }


    private void addCategory(Category name) {
        categoryService.addCategory(name);
    }

}
