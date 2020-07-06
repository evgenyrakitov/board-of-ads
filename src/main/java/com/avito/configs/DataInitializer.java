package com.avito.configs;

import com.avito.models.Category;
import com.avito.models.Images;
import com.avito.models.Role;
import com.avito.models.User;
import com.avito.models.posting.Posting;
import com.avito.service.interfaces.CategoryService;
import com.avito.service.interfaces.PostingService;
import com.avito.service.interfaces.RoleService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final RoleService roleService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final PostingService postingService;

    @PostConstruct
    private void init() {
        initRoles();
        initUsers();
        initCategories();
        initPostings();
    }

    private void initRoles() {
        if (roleService.getAllRoles().size() != 0) {
            return;
        }

        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        roleService.addRole(adminRole);
        roleService.addRole(userRole);
    }

    private void initUsers() {
        if (userService.getAllUsers().size() != 0) {
            return;
        }

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleService.findRoleByName("ADMIN"));
        adminRoles.add(roleService.findRoleByName("USER"));

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleService.findRoleByName("USER"));

        User userAdmin = new User("admin@gmail.com", "Test Admin name", "admin", "admin", "89185552623", adminRoles);
        User userUser = new User("test.email.1@gmail.com", "test 1 public name", "qwerty1", "qwerty1", "89526321452", userRoles);

        userService.addUser(userAdmin);
        userService.addUser(userUser);
    }

    private void initPostings() {
        if (postingService.getAllPostings().size() != 0) {
            return;
        }

        User userUser = userService.findUserByEmail("test.email.1@gmail.com");
        User adminUser = userService.findUserByEmail("admin@gmail.com");


        List<Category> categories = categoryService.getAllCategories().stream().sorted(Comparator.comparing(Category::getNameRu)).collect(Collectors.toList());

        /////// Постинг категории Недвижимость

        Images images1 = new Images();
        images1.setImagePath("/images/posting_img/realty/1/1.png");
        Images images2 = new Images();
        images2.setImagePath("/images/posting_img/realty/1/2.png");
        Images images3 = new Images();
        images3.setImagePath("/images/posting_img/realty/1/3.png");
        Images images4 = new Images();
        images4.setImagePath("/images/posting_img/realty/2/1.png");
        Images images5= new Images();
        images5.setImagePath("/images/posting_img/realty/2/2.png");
        Images images6 = new Images();
        images6.setImagePath("/images/posting_img/realty/2/3.png");
        Images images7 = new Images();
        images7.setImagePath("/images/posting_img/realty/3/1.png");
        Images images8 = new Images();
        images8.setImagePath("/images/posting_img/realty/3/2.png");
        Images images9 = new Images();
        images9.setImagePath("/images/posting_img/realty/3/3.png");

        Set<Images> set1 = Stream.of(images1, images2, images3).collect(Collectors.toSet());
        Set<Images> set2 = Stream.of(images4, images5, images6).collect(Collectors.toSet());
        Set<Images> set3 = Stream.of(images7, images8, images9).collect(Collectors.toSet());

        assert categories.size() >= 3;

        Posting posting = new Posting();
        posting.setTitle("Коттедж 400 м² на участке 3 сот.");
        posting.setCategory(categories.get(13));
        posting.setUser(adminUser);
        posting.setFullDescription("Коттедж на два хозяина. На первом этаже кут с ванной, третий этаж-две комнаты. Цокольный этаж .");
        posting.setShortDescription("Коттедж");
        posting.setImagePath(set1);
        posting.setPrice(3_500_000);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("Коттедж 266 м² на участке 4.8 сот.");
        posting.setCategory(categories.get(41));
        posting.setUser(adminUser);
        posting.setFullDescription("Для тех кто хочет жить в элиземли рядом с Воронежским водохранилищем! - на первом этаже: п...");
        posting.setShortDescription("Коттедж 266 м² на участке 4.8 сот.");
        posting.setPrice(300_000_000);
        posting.setImagePath(set2);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("Дом 240 м² на участке 6.5 сот.");
        posting.setCategory(categories.get(22));
        posting.setUser(userUser);
        posting.setFullDescription("Продаю без посредников половину 3-х этажного дома (без подвал Вторая половина дома 280 кв.м.");
        posting.setShortDescription("Дом 240 м² на участке 6.5 сот.");
        posting.setPrice(8_004_500);
        posting.setImagePath(set3);
        postingService.addPosting(posting);


    //////////////// Постинг категории Автомобили

        Images imagesa1 = new Images();
        imagesa1.setImagePath("/images/posting_img/avto/1/1.png");
        Images imagesa2 = new Images();
        imagesa2.setImagePath("/images/posting_img/avto/1/2.png");
        Images imagesa3 = new Images();
        imagesa3.setImagePath("/images/posting_img/avto/1/3.png");
        Images imagesa4 = new Images();
        imagesa4.setImagePath("/images/posting_img/avto/2/1.png");
        Images imagesa5= new Images();
        imagesa5.setImagePath("/images/posting_img/avto/2/2.png");
        Images imagesa6 = new Images();
        imagesa6.setImagePath("/images/posting_img/avto/2/3.png");
        Images imagesa7 = new Images();
        imagesa7.setImagePath("/images/posting_img/avto/3/1.png");
        Images imagesa8 = new Images();
        imagesa8.setImagePath("/images/posting_img/avto/3/2.png");
        Images imagesa9 = new Images();
        imagesa9.setImagePath("/images/posting_img/avto/3/3.png");

        Set<Images> seta1 = Stream.of(imagesa1, imagesa2, imagesa3).collect(Collectors.toSet());
        Set<Images> seta2 = Stream.of(imagesa4, imagesa5, imagesa6).collect(Collectors.toSet());
        Set<Images> seta3 = Stream.of(imagesa7, imagesa8, imagesa9).collect(Collectors.toSet());

        posting = new Posting();
        posting.setTitle("BMW 5 серия, 2006");
        posting.setCategory(categories.get(10));
        posting.setUser(adminUser);
        posting.setFullDescription("Учет Армения! BMW 530I M-пакет с завода,объемт р ,за наличку! на обмен 740т.р");
        posting.setShortDescription("BMW 5 серия, 2006");
        posting.setImagePath(seta1);
        posting.setPrice(680_000);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("Mercedes-Benz Sprinter, 1996");
        posting.setCategory(categories.get(11));
        posting.setUser(adminUser);
        posting.setFullDescription("Продам микроавтобус . Состояние хорошее вложение не требует. Категория В. Установлен газ.");
        posting.setShortDescription("Mercedes-Benz Sprinter, 1996");
        posting.setPrice(390_000);
        posting.setImagePath(seta2);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("Renault Logan, 2014");
        posting.setCategory(categories.get(140));
        posting.setUser(userUser);
        posting.setFullDescription("В хорошем состоянии, юридичиски чистасло не есть , коробка ходовая на отлично.");
        posting.setShortDescription("Renault Logan, 2014");
        posting.setPrice(422_500);
        posting.setImagePath(seta3);
        postingService.addPosting(posting);

    //////////////// Бытовая техника

        Images imagesb1 = new Images();
        imagesb1.setImagePath("/images/posting_img/appliances/1/1.png");
        Images imagesb2 = new Images();
        imagesb2.setImagePath("/images/posting_img/appliances/1/2.png");
        Images imagesb3 = new Images();
        imagesb3.setImagePath("/images/posting_img/appliances/1/3.png");
        Images imagesb4 = new Images();
        imagesb4.setImagePath("/images/posting_img/appliances/2/1.png");
        Images imagesb5= new Images();
        imagesb5.setImagePath("/images/posting_img/appliances/2/2.png");
        Images imagesb6 = new Images();
        imagesb6.setImagePath("/images/posting_img/appliances/2/3.png");
        Images imagesb7 = new Images();
        imagesb7.setImagePath("/images/posting_img/appliances/3/1.png");
        Images imagesb8 = new Images();
        imagesb8.setImagePath("/images/posting_img/appliances/3/2.png");
        Images imagesb9 = new Images();
        imagesb9.setImagePath("/images/posting_img/appliances/3/3.png");

        Set<Images> setb1 = Stream.of(imagesb1, imagesb2, imagesb3).collect(Collectors.toSet());
        Set<Images> setb2 = Stream.of(imagesb4, imagesb5, imagesb6).collect(Collectors.toSet());
        Set<Images> setb3 = Stream.of(imagesb7, imagesb8, imagesb9).collect(Collectors.toSet());

        posting = new Posting();
        posting.setTitle("Пылесос Zelmer");
        posting.setCategory(categories.get(11));
        posting.setUser(adminUser);
        posting.setFullDescription("Пылесос в хорошем состоянии, все исправно, все насадки как новые, сменные мешки имеются, 2100w");
        posting.setShortDescription("Пылесос Zelmer");
        posting.setImagePath(setb2);
        posting.setPrice(20_000);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("Стиральная машина");
        posting.setCategory(categories.get(120));
        posting.setUser(adminUser);
        posting.setFullDescription("Стиральная машина Samsung S803J. Загрузка 3.5кг. Состояние отличное. Гарантия. Доставка. Семафорная 271 стр.7.");
        posting.setShortDescription("Стиральная машина");
        posting.setPrice(3_000);
        posting.setImagePath(setb1);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("электро котел Zota lux 12");
        posting.setCategory(categories.get(10));
        posting.setUser(userUser);
        posting.setFullDescription("Продаю из за покупки более мощного. Так как купил дом больше в 2 раза.");
        posting.setShortDescription("электро котел Zota lux 12");
        posting.setPrice(6_500);
        posting.setImagePath(setb3);
        postingService.addPosting(posting);

 //////////////// Бытовая техника

        Images imagesc1 = new Images();
        imagesc1.setImagePath("/images/posting_img/clothes/1/1.png");
        Images imagesc2 = new Images();
        imagesc2.setImagePath("/images/posting_img/clothes/1/2.png");
        Images imagesc3 = new Images();
        imagesc3.setImagePath("/images/posting_img/clothes/1/3.png");
        Images imagesc4 = new Images();
        imagesc4.setImagePath("/images/posting_img/clothes/2/1.png");
        Images imagesc5= new Images();
        imagesc5.setImagePath("/images/posting_img/clothes/2/2.png");
        Images imagesc6 = new Images();
        imagesc6.setImagePath("/images/posting_img/clothes/2/3.png");
        Images imagesc7 = new Images();
        imagesc7.setImagePath("/images/posting_img/clothes/3/1.png");
        Images imagesc8 = new Images();
        imagesc8.setImagePath("/images/posting_img/clothes/3/2.png");
        Images imagesc9 = new Images();
        imagesc9.setImagePath("/images/posting_img/clothes/3/3.png");

        Set<Images> setc1 = Stream.of(imagesc1, imagesc2, imagesc3).collect(Collectors.toSet());
        Set<Images> setc2 = Stream.of(imagesc4, imagesc5, imagesc6).collect(Collectors.toSet());
        Set<Images> setc3 = Stream.of(imagesc7, imagesc8, imagesc9).collect(Collectors.toSet());

        posting = new Posting();
        posting.setTitle("Свадебное платье");
        posting.setCategory(categories.get(10));
        posting.setUser(adminUser);
        posting.setFullDescription("Продам Новые свадебные платья, размер 38—40. 4 модели. Торг уместен");
        posting.setShortDescription("Свадебное платье");
        posting.setImagePath(setc1);
        posting.setPrice(3_000);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("Кеды");
        posting.setCategory(categories.get(10));
        posting.setUser(adminUser);
        posting.setFullDescription("Товар новый. Причина продажи: не подошел размер.");
        posting.setShortDescription("Кеды");
        posting.setPrice(300);
        posting.setImagePath(setc2);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("Комбинезон и шапке зима");
        posting.setCategory(categories.get(10));
        posting.setUser(userUser);
        posting.setFullDescription("Комбинезон Guliver... б/у один сезон идеальном состоянии 6т окончательно... покупала за 12т.");
        posting.setShortDescription("Комбинезон и шапке зима");
        posting.setPrice(6_500);
        posting.setImagePath(setc3);
        postingService.addPosting(posting);


    }

    private void initCategories() {

        if (categoryService.getAllCategories().size() != 0) {
            return;
        }

        Set<Posting> postings = new HashSet<>();

        //1 вкладка Категории создание

        Category categoryTransport = new Category("Транспорт", "Transport", null, postings);
        Category categoryProperty = new Category("Недвижимость", "Property", null, postings);
        Category categoryJob = new Category("Работа", "Job", null, postings);
        Category categoryServices = new Category("Услуги", "Services", null, postings);
        Category categoryForHomeAndGarden = new Category("Для дома и дачи", "For home and garden", null, postings);
        Category categoryPersonalItems = new Category("Личные вещи", "Personal items", null, postings);
        Category categoryConsumerElectronics = new Category("Бытовая электроника", "Consumer electronics", null, postings);
        Category categoryHobbiesAndLeisure = new Category("Хобби и отдых", "Hobbies and leisure", null, postings);
        Category cateroryAnimals = new Category("Животные", "Animals", null, postings);
        Category categoryBusiness = new Category("Для бизнеса", "Business", null, postings);

        //1 вкладка Категории добавление в БД

        categoryService.addCategory(categoryTransport);
        categoryService.addCategory(categoryProperty);
        categoryService.addCategory(categoryJob);
        categoryService.addCategory(categoryServices);
        categoryService.addCategory(categoryForHomeAndGarden);
        categoryService.addCategory(categoryPersonalItems);
        categoryService.addCategory(categoryConsumerElectronics);
        categoryService.addCategory(categoryHobbiesAndLeisure);
        categoryService.addCategory(cateroryAnimals);
        categoryService.addCategory(categoryBusiness);

        //2 вкладка Категория транспорт создание

        Category categoryCars = new Category("Автомобили", "Cars", null, postings);
        Category categoryMotorcycles = new Category("Мотоциклы и мототехника", "Motorcycles", null, postings);
        Category categoryTrucksAndSpecialEquipment = new Category("Грузовики и спецтехника", "Trucks and special equipment", null, postings);
        Category categoryWaterTransport = new Category("Водный транспорт", "Water transport", null, postings);
        Category categoryPartsAndAccessories = new Category("Запчасти и аксессуары", "Parts and accessories", null, postings);

        //2 вкладка Категория транспорт добавление в БД

        categoryService.addCategory(categoryCars);
        categoryService.addCategory(categoryMotorcycles);
        categoryService.addCategory(categoryTrucksAndSpecialEquipment);
        categoryService.addCategory(categoryWaterTransport);
        categoryService.addCategory(categoryPartsAndAccessories);

        //2 вкладка Категория транспорт назначение родителей

        categoryCars.setParentCategory(categoryTransport);
        categoryMotorcycles.setParentCategory(categoryTransport);
        categoryTrucksAndSpecialEquipment.setParentCategory(categoryTransport);
        categoryWaterTransport.setParentCategory(categoryTransport);
        categoryPartsAndAccessories.setParentCategory(categoryTransport);
        categoryService.updateCategory(categoryCars);
        categoryService.updateCategory(categoryMotorcycles);
        categoryService.updateCategory(categoryTrucksAndSpecialEquipment);
        categoryService.updateCategory(categoryWaterTransport);
        categoryService.updateCategory(categoryPartsAndAccessories);

        //3 вкладка Категория автомобили создание

        Category oldCars = new Category("С пробегом", "With mileage", null, postings);
        Category newCars = new Category("Новый", "New", null, postings);

        //3 вкладка Категория автомобили добавление в БД

        categoryService.addCategory(oldCars);
        categoryService.addCategory(newCars);

        //3 вкладка Категория автомобили назначение родителей

        oldCars.setParentCategory(categoryCars);
        newCars.setParentCategory(categoryCars);
        categoryService.updateCategory(oldCars);
        categoryService.updateCategory(newCars);

        //3 вкладка Категория водный транспорт создание

        Category rowboats = new Category("Вёсельные лодки", "Rowing boats", null, postings);
        Category jetSkis = new Category("Гидроциклы", "Jet skis", null, postings);
        Category boatsAndYachts = new Category("Катера и яхты", "Boats and yachts", null, postings);
        Category kayaksAndCanoes = new Category("Каяки и каноэ", "Kayaks and canoes", null, postings);
        Category motorBoats = new Category("Моторные лодки", "Motor boats", null, postings);
        Category inflatableBoat = new Category("Надувные лодки", "Inflatable boat", null, postings);

        //3 вкладка Категория водный транспорт  добавление в БД

        categoryService.addCategory(rowboats);
        categoryService.addCategory(jetSkis);
        categoryService.addCategory(boatsAndYachts);
        categoryService.addCategory(kayaksAndCanoes);
        categoryService.addCategory(motorBoats);
        categoryService.addCategory(inflatableBoat);

        //3 вкладка Категория водный транспорт назначение родителей

        rowboats.setParentCategory(categoryWaterTransport);
        jetSkis.setParentCategory(categoryWaterTransport);
        boatsAndYachts.setParentCategory(categoryWaterTransport);
        kayaksAndCanoes.setParentCategory(categoryWaterTransport);
        motorBoats.setParentCategory(categoryWaterTransport);
        inflatableBoat.setParentCategory(categoryWaterTransport);
        categoryService.updateCategory(rowboats);
        categoryService.updateCategory(jetSkis);
        categoryService.updateCategory(boatsAndYachts);
        categoryService.updateCategory(kayaksAndCanoes);
        categoryService.updateCategory(motorBoats);
        categoryService.updateCategory(inflatableBoat);

        //3 вкладка Категория спец транспорт  создание

        Category buses = new Category("Автобусы", "Buses", null, postings);
        Category motorhomes = new Category("Автодома", "Auto houses", null, postings);
        Category truckCranes = new Category("Автокраны", "Truck cranes", null, postings);
        Category bulldozers = new Category("Бульдозеры", "Bulldozers", null, postings);
        Category trucks = new Category("Грузовики", "Trucks", null, postings);
        Category utilityEquipment = new Category("Коммунальная техника", "Utility equipment", null, postings);
        Category easyTransport = new Category("Лёгкий транспорт", "Light transport", null, postings);
        Category loaders = new Category("Погрузчики", "Loaders", null, postings);
        Category trailers = new Category("Прицепы", "Trailers", null, postings);
        Category agriculturalMachinery = new Category("Сельхозтехника", "Agricultural machinery", null, postings);
        Category constructionMachinery = new Category("Строительная техника", "Construction equipment", null, postings);
        Category loggingEquipment = new Category("Техника для лесозаготовки", "Logging equipment", null, postings);
        Category tractorUnits = new Category("Тягачи", "Tractors", null, postings);
        Category excavators = new Category("Экскаваторы", "Excavators", null, postings);

        //3 вкладка Категория спец транспорт  добавление в БД

        categoryService.addCategory(buses);
        categoryService.addCategory(motorhomes);
        categoryService.addCategory(truckCranes);
        categoryService.addCategory(bulldozers);
        categoryService.addCategory(trucks);
        categoryService.addCategory(utilityEquipment);
        categoryService.addCategory(easyTransport);
        categoryService.addCategory(loaders);
        categoryService.addCategory(trailers);
        categoryService.addCategory(agriculturalMachinery);
        categoryService.addCategory(constructionMachinery);
        categoryService.addCategory(loggingEquipment);
        categoryService.addCategory(tractorUnits);
        categoryService.addCategory(excavators);

        //3 вкладка Категория спец транспорт назначение родителей

        buses.setParentCategory(categoryTrucksAndSpecialEquipment);
        motorhomes.setParentCategory(categoryTrucksAndSpecialEquipment);
        truckCranes.setParentCategory(categoryTrucksAndSpecialEquipment);
        bulldozers.setParentCategory(categoryTrucksAndSpecialEquipment);
        trucks.setParentCategory(categoryTrucksAndSpecialEquipment);
        utilityEquipment.setParentCategory(categoryTrucksAndSpecialEquipment);
        easyTransport.setParentCategory(categoryTrucksAndSpecialEquipment);
        loaders.setParentCategory(categoryTrucksAndSpecialEquipment);
        trailers.setParentCategory(categoryTrucksAndSpecialEquipment);
        agriculturalMachinery.setParentCategory(categoryTrucksAndSpecialEquipment);
        constructionMachinery.setParentCategory(categoryTrucksAndSpecialEquipment);
        loggingEquipment.setParentCategory(categoryTrucksAndSpecialEquipment);
        tractorUnits.setParentCategory(categoryTrucksAndSpecialEquipment);
        excavators.setParentCategory(categoryTrucksAndSpecialEquipment);
        categoryService.updateCategory(buses);
        categoryService.updateCategory(motorhomes);
        categoryService.updateCategory(truckCranes);
        categoryService.updateCategory(bulldozers);
        categoryService.updateCategory(trucks);
        categoryService.updateCategory(utilityEquipment);
        categoryService.updateCategory(easyTransport);
        categoryService.updateCategory(loaders);
        categoryService.updateCategory(trailers);
        categoryService.updateCategory(agriculturalMachinery);
        categoryService.updateCategory(constructionMachinery);
        categoryService.updateCategory(loggingEquipment);
        categoryService.updateCategory(tractorUnits);
        categoryService.updateCategory(excavators);

        //3 вкладка Категория  мотоциклы создание

        Category buggy = new Category("Багги", "Buggy", null, postings);
        Category allTerrainVehicles = new Category("Вездеходы", "Rovers", null, postings);
        Category karting = new Category("Картинг", "Karting", null, postings);
        Category atv = new Category("Квадроциклы", "ATVs", null, postings);
        Category mopedsAndScooters = new Category("Мопеды и скутеры", "Mopeds and scooters", null, postings);
        Category motorcycles = new Category("Мотоциклы", "Motorcycles", null, postings);
        Category snowmobiles = new Category("Снегоходы", "Snowmobiles", null, postings);

        //3 вкладка Категория  мотоциклы  добавление в БД

        categoryService.addCategory(buggy);
        categoryService.addCategory(allTerrainVehicles);
        categoryService.addCategory(karting);
        categoryService.addCategory(atv);
        categoryService.addCategory(mopedsAndScooters);
        categoryService.addCategory(motorcycles);
        categoryService.addCategory(snowmobiles);

        //3 вкладка Категория  мотоциклы  назначение родителей

        buggy.setParentCategory(categoryMotorcycles);
        allTerrainVehicles.setParentCategory(categoryMotorcycles);
        karting.setParentCategory(categoryMotorcycles);
        atv.setParentCategory(categoryMotorcycles);
        mopedsAndScooters.setParentCategory(categoryMotorcycles);
        motorcycles.setParentCategory(categoryMotorcycles);
        snowmobiles.setParentCategory(categoryMotorcycles);
        categoryService.updateCategory(buggy);
        categoryService.updateCategory(allTerrainVehicles);
        categoryService.updateCategory(karting);
        categoryService.updateCategory(atv);
        categoryService.updateCategory(mopedsAndScooters);
        categoryService.updateCategory(motorcycles);
        categoryService.updateCategory(snowmobiles);

        // 2 вкладка   Категория работа создание

        Category categorySummary = new Category("Резюме", "Summary", null, postings);
        Category categoryVacancies = new Category("Вакансии", "Job openings", null, postings);

        //2 вкладка   Категория работа добавление в БД

        categoryService.addCategory(categorySummary);
        categoryService.addCategory(categoryVacancies);

        //2 вкладка   Категория работа назначение родителей

        categorySummary.setParentCategory(categoryJob);
        categoryVacancies.setParentCategory(categoryJob);
        categoryService.updateCategory(categorySummary);
        categoryService.updateCategory(categoryVacancies);

        //3 вкладка   Категория  Резюме и Вакансии создание

        Category itRes = new Category("IT, интернет, телеком", "IT, Internet, Telecom", null, postings);
        Category itVac = new Category("IT, интернет, телеком", "IT, Internet, Telecom", null, postings);
        Category carBusinessRes = new Category("Автомобильный бизнес", "Automobile business", null, postings);
        Category carBusinessVac = new Category("Автомобильный бизнес", "Automobile business", null, postings);
        Category administrativeWorkRes = new Category("Административная работа", "Administrative work", null, postings);
        Category administrativeWorkVac = new Category("Административная работа", "Administrative work", null, postings);
        Category bankRes = new Category("Банки, инвестиции", "Banks, investments", null, postings);
        Category bankVac = new Category("Банки, инвестиции", "Banks, investments", null, postings);
        Category withoutRes = new Category("Без опыта, студенты", "Without experience, students", null, postings);
        Category withoutVac = new Category("Без опыта, студенты", "Without experience, students", null, postings);
        Category accRes = new Category("Бухгалтерия, финансы", "Accounting, Finance", null, postings);
        Category accVac = new Category("Бухгалтерия, финансы", "Accounting, Finance", null, postings);
        Category hiManagRes = new Category("Высший менеджмент", "Top management", null, postings);
        Category hiManagVac = new Category("Высший менеджмент", "Top management", null, postings);
        Category nkoRes = new Category("Госслужба, НКО", "Civil service, NPO", null, postings);
        Category nkoVac = new Category("Госслужба, НКО", "Civil service, NPO", null, postings);
        Category homeStaffRes = new Category("Домашний персонал", "Domestic staff", null, postings);
        Category homeStaffVac = new Category("Домашний персонал", "Domestic staff", null, postings);
        Category ghkRes = new Category("ЖКХ, эксплуатация", "Housing and utilities, operation", null, postings);
        Category ghkVac = new Category("ЖКХ, эксплуатация", "Housing and utilities, operation", null, postings);
        Category artRes = new Category("Искусство, развлечения", "Art, entertainment", null, postings);
        Category artVac = new Category("Искусство, развлечения", "Art, entertainment", null, postings);
        Category consRes = new Category("Консультирование", "Consultancy", null, postings);
        Category consVac = new Category("Консультирование", "Consultancy", null, postings);
        Category prRes = new Category("Маркетинг, реклама, PR", "Marketing, advertising, PR", null, postings);
        Category prVac = new Category("Маркетинг, реклама, PR", "Marketing, advertising, PR", null, postings);
        Category medRes = new Category("Медицина, фармацевтика", "Medicine, pharmaceuticals", null, postings);
        Category medVac = new Category("Медицина, фармацевтика", "Medicine, pharmaceuticals", null, postings);
        Category scienceRes = new Category("Образование, наука", "Education, science", null, postings);
        Category scienceVac = new Category("Образование, наука", "Education, science", null, postings);
        Category safetyRes = new Category("Охрана, безопасность", "Security and safety", null, postings);
        Category safetyVac = new Category("Охрана, безопасность", "Security and safety", null, postings);
        Category salesRes = new Category("Продажи", "Sales", null, postings);
        Category salesVac = new Category("Продажи", "Sales", null, postings);
        Category agriculturalRes = new Category("Производство, сырьё, с/х", "Production, raw materials, agricultural", null, postings);
        Category agriculturalVac = new Category("Производство, сырьё, с/х", "Production, raw materials, agricultural", null, postings);
        Category insuranceRes = new Category("Страхование", "Insurance", null, postings);
        Category insuranceVac = new Category("Страхование", "Insurance", null, postings);
        Category buildRes = new Category("Строительство", "Construction", null, postings);
        Category buildVac = new Category("Строительство", "Construction", null, postings);
        Category transpRes = new Category("Транспорт, логистика", "Transport, logistics", null, postings);
        Category transpVac = new Category("Транспорт, логистика", "Transport, logistics", null, postings);
        Category tourismRes = new Category("Туризм, рестораны", "Tourism, restaurants", null, postings);
        Category tourismVac = new Category("Туризм, рестораны", "Tourism, restaurants", null, postings);
        Category fitnessRes = new Category("Фитнес, салоны красоты", "Fitness, beauty salons", null, postings);
        Category fitnessVac = new Category("Фитнес, салоны красоты", "Fitness, beauty salons", null, postings);
        Category lawRes = new Category("Юриспруденция", "Jurisprudence", null, postings);
        Category lawVac = new Category("Юриспруденция", "Jurisprudence", null, postings);

        //3 вкладка Категория спец транспорт  добавление в БД

        categoryService.addCategory(itRes);
        categoryService.addCategory(itVac);
        categoryService.addCategory(carBusinessRes);
        categoryService.addCategory(carBusinessVac);
        categoryService.addCategory(administrativeWorkRes);
        categoryService.addCategory(administrativeWorkVac);
        categoryService.addCategory(bankRes);
        categoryService.addCategory(bankVac);
        categoryService.addCategory(withoutRes);
        categoryService.addCategory(withoutVac);
        categoryService.addCategory(accRes);
        categoryService.addCategory(accVac);
        categoryService.addCategory(hiManagRes);
        categoryService.addCategory(hiManagVac);
        categoryService.addCategory(nkoRes);
        categoryService.addCategory(nkoVac);
        categoryService.addCategory(homeStaffRes);
        categoryService.addCategory(homeStaffVac);
        categoryService.addCategory(ghkRes);
        categoryService.addCategory(ghkVac);
        categoryService.addCategory(artRes);
        categoryService.addCategory(artVac);
        categoryService.addCategory(consRes);
        categoryService.addCategory(consVac);
        categoryService.addCategory(prRes);
        categoryService.addCategory(prVac);
        categoryService.addCategory(medRes);
        categoryService.addCategory(medVac);
        categoryService.addCategory(scienceRes);
        categoryService.addCategory(scienceVac);
        categoryService.addCategory(safetyRes);
        categoryService.addCategory(safetyVac);
        categoryService.addCategory(salesRes);
        categoryService.addCategory(salesVac);
        categoryService.addCategory(agriculturalRes);
        categoryService.addCategory(agriculturalVac);
        categoryService.addCategory(insuranceRes);
        categoryService.addCategory(insuranceVac);
        categoryService.addCategory(buildRes);
        categoryService.addCategory(buildVac);
        categoryService.addCategory(transpRes);
        categoryService.addCategory(transpVac);
        categoryService.addCategory(tourismRes);
        categoryService.addCategory(tourismVac);
        categoryService.addCategory(fitnessRes);
        categoryService.addCategory(fitnessVac);
        categoryService.addCategory(lawRes);
        categoryService.addCategory(lawVac);

        //3 вкладка Категория спец транспорт  назначение родителей

        itRes.setParentCategory(categorySummary);
        itVac.setParentCategory(categoryVacancies);
        carBusinessRes.setParentCategory(categorySummary);
        carBusinessVac.setParentCategory(categoryVacancies);
        administrativeWorkRes.setParentCategory(categorySummary);
        administrativeWorkVac.setParentCategory(categoryVacancies);
        bankRes.setParentCategory(categorySummary);
        bankVac.setParentCategory(categoryVacancies);
        withoutRes.setParentCategory(categorySummary);
        withoutVac.setParentCategory(categoryVacancies);
        accRes.setParentCategory(categorySummary);
        accVac.setParentCategory(categoryVacancies);
        hiManagRes.setParentCategory(categorySummary);
        hiManagVac.setParentCategory(categoryVacancies);
        nkoRes.setParentCategory(categorySummary);
        nkoVac.setParentCategory(categoryVacancies);
        homeStaffRes.setParentCategory(categorySummary);
        homeStaffVac.setParentCategory(categoryVacancies);
        ghkRes.setParentCategory(categorySummary);
        ghkVac.setParentCategory(categoryVacancies);
        artRes.setParentCategory(categorySummary);
        artVac.setParentCategory(categoryVacancies);
        consRes.setParentCategory(categorySummary);
        consVac.setParentCategory(categoryVacancies);
        prRes.setParentCategory(categorySummary);
        prVac.setParentCategory(categoryVacancies);
        medRes.setParentCategory(categorySummary);
        medVac.setParentCategory(categoryVacancies);
        scienceRes.setParentCategory(categorySummary);
        scienceVac.setParentCategory(categoryVacancies);
        safetyRes.setParentCategory(categorySummary);
        safetyVac.setParentCategory(categoryVacancies);
        salesRes.setParentCategory(categorySummary);
        salesVac.setParentCategory(categoryVacancies);
        agriculturalRes.setParentCategory(categorySummary);
        agriculturalVac.setParentCategory(categoryVacancies);
        insuranceRes.setParentCategory(categorySummary);
        insuranceVac.setParentCategory(categoryVacancies);
        buildRes.setParentCategory(categorySummary);
        buildVac.setParentCategory(categoryVacancies);
        transpRes.setParentCategory(categorySummary);
        transpVac.setParentCategory(categoryVacancies);
        tourismRes.setParentCategory(categorySummary);
        tourismVac.setParentCategory(categoryVacancies);
        fitnessRes.setParentCategory(categorySummary);
        fitnessVac.setParentCategory(categoryVacancies);
        lawRes.setParentCategory(categorySummary);
        lawVac.setParentCategory(categoryVacancies);
        categoryService.updateCategory(itRes);
        categoryService.updateCategory(itVac);
        categoryService.updateCategory(carBusinessRes);
        categoryService.updateCategory(carBusinessVac);
        categoryService.updateCategory(administrativeWorkRes);
        categoryService.updateCategory(administrativeWorkVac);
        categoryService.updateCategory(bankRes);
        categoryService.updateCategory(bankVac);
        categoryService.updateCategory(withoutRes);
        categoryService.updateCategory(withoutVac);
        categoryService.updateCategory(accRes);
        categoryService.updateCategory(accVac);
        categoryService.updateCategory(hiManagRes);
        categoryService.updateCategory(hiManagVac);
        categoryService.updateCategory(nkoRes);
        categoryService.updateCategory(nkoVac);
        categoryService.updateCategory(homeStaffRes);
        categoryService.updateCategory(homeStaffVac);
        categoryService.updateCategory(ghkRes);
        categoryService.updateCategory(ghkVac);
        categoryService.updateCategory(artRes);
        categoryService.updateCategory(artVac);
        categoryService.updateCategory(consRes);
        categoryService.updateCategory(consVac);
        categoryService.updateCategory(prRes);
        categoryService.updateCategory(prVac);
        categoryService.updateCategory(medRes);
        categoryService.updateCategory(medVac);
        categoryService.updateCategory(scienceRes);
        categoryService.updateCategory(scienceVac);
        categoryService.updateCategory(safetyRes);
        categoryService.updateCategory(safetyVac);
        categoryService.updateCategory(salesRes);
        categoryService.updateCategory(salesVac);
        categoryService.updateCategory(agriculturalRes);
        categoryService.updateCategory(agriculturalVac);
        categoryService.updateCategory(insuranceRes);
        categoryService.updateCategory(insuranceVac);
        categoryService.updateCategory(buildRes);
        categoryService.updateCategory(buildVac);
        categoryService.updateCategory(transpRes);
        categoryService.updateCategory(transpVac);
        categoryService.updateCategory(tourismRes);
        categoryService.updateCategory(tourismVac);
        categoryService.updateCategory(fitnessRes);
        categoryService.updateCategory(fitnessVac);
        categoryService.updateCategory(lawRes);
        categoryService.updateCategory(lawVac);

        //2 вкладка   Категория  Недвижимость создание

        Category categoryApartments = new Category("Квартиры", "Apartments", null, postings);
        Category categoryRooms = new Category("Комнаты", "Rooms", null, postings);
        Category categoryHousesSummerResidencesCottages = new Category("Дома, дачи, коттеджи", "Houses, villas, cottages", null, postings);
        Category categoryLand = new Category("Земельные участки", "Land plot", null, postings);
        Category categoryGaragesAndParkingSpaces = new Category("Гаражи и машиноместа", "Garages and Parking spaces", null, postings);
        Category categoryCommercialProperty = new Category("Коммерческая недвижимость", "Commercial real estate", null, postings);
        Category nullAbroad = new Category("Недвижимость за рубежом", "International property", null, postings);

        //2 вкладка   Категория  Недвижимость  добавление в БД

        categoryService.addCategory(categoryApartments);
        categoryService.addCategory(categoryRooms);
        categoryService.addCategory(categoryHousesSummerResidencesCottages);
        categoryService.addCategory(categoryLand);
        categoryService.addCategory(categoryGaragesAndParkingSpaces);
        categoryService.addCategory(categoryCommercialProperty);
        categoryService.addCategory(nullAbroad);

        //2 вкладка   Категория  Недвижимость назначение родителей

        categoryApartments.setParentCategory(categoryProperty);
        categoryRooms.setParentCategory(categoryProperty);
        categoryHousesSummerResidencesCottages.setParentCategory(categoryProperty);
        categoryLand.setParentCategory(categoryProperty);
        categoryGaragesAndParkingSpaces.setParentCategory(categoryProperty);
        categoryCommercialProperty.setParentCategory(categoryProperty);
        nullAbroad.setParentCategory(categoryProperty);
        categoryService.updateCategory(categoryApartments);
        categoryService.updateCategory(categoryRooms);
        categoryService.updateCategory(categoryHousesSummerResidencesCottages);
        categoryService.updateCategory(categoryLand);
        categoryService.updateCategory(categoryGaragesAndParkingSpaces);
        categoryService.updateCategory(categoryCommercialProperty);
        categoryService.updateCategory(nullAbroad);

        //3 вкладка   Категории  КуплюПродамСдамСниму создание

        Category sellApart = new Category("Продам", "Sell", null, postings);
        Category sellRooms = new Category("Продам", "Sell", null, postings);
        Category sellHouses = new Category("Продам", "Sell", null, postings);
        Category sellLand = new Category("Продам", "Sell", null, postings);
        Category sellGarage = new Category("Продам", "Sell", null, postings);
        Category sellAbroad = new Category("Продам", "Sell", null, postings);
        Category sellCommerc = new Category("Продам", "Sell", null, postings);

        Category rentApart = new Category("Сдам", "Pass", null, postings);
        Category rentRooms = new Category("Сдам", "Pass", null, postings);
        Category rentHouses = new Category("Сдам", "Pass", null, postings);
        Category rentLand = new Category("Сдам", "Pass", null, postings);
        Category rentGarage = new Category("Сдам", "Pass", null, postings);
        Category rentAbroad = new Category("Сдам", "Pass", null, postings);
        Category rentCommerc = new Category("Сдам", "Pass", null, postings);

        Category buyApart = new Category("Куплю", "Buy", null, postings);
        Category buyRooms = new Category("Куплю", "Buy", null, postings);
        Category buyHouses = new Category("Куплю", "Buy", null, postings);
        Category buyLand = new Category("Куплю", "Buy", null, postings);
        Category buyGarage = new Category("Куплю", "Buy", null, postings);
        Category buyAbroad = new Category("Куплю", "Buy", null, postings);
        Category buyCommerc = new Category("Куплю", "Buy", null, postings);

        Category snimApart = new Category("Сниму", "Rent", null, postings);
        Category snimRooms = new Category("Сниму", "Rent", null, postings);
        Category snimHouses = new Category("Сниму", "Rent", null, postings);
        Category snimLand = new Category("Сниму", "Rent", null, postings);
        Category snimGarage = new Category("Сниму", "Rent", null, postings);
        Category snimAbroad = new Category("Сниму", "Rent", null, postings);
        Category snimCommerc = new Category("Сниму", "Rent", null, postings);

        //3 вкладка   Категории  КуплюПродамСдамСниму  добавление в БД

        categoryService.addCategory(sellApart);
        categoryService.addCategory(sellRooms);
        categoryService.addCategory(sellHouses);
        categoryService.addCategory(sellLand);
        categoryService.addCategory(sellGarage);
        categoryService.addCategory(sellCommerc);
        categoryService.addCategory(sellAbroad);

        categoryService.addCategory(rentLand);
        categoryService.addCategory(rentApart);
        categoryService.addCategory(rentRooms);
        categoryService.addCategory(rentHouses);
        categoryService.addCategory(rentGarage);
        categoryService.addCategory(rentAbroad);
        categoryService.addCategory(rentCommerc);

        categoryService.addCategory(buyLand);
        categoryService.addCategory(buyApart);
        categoryService.addCategory(buyRooms);
        categoryService.addCategory(buyHouses);
        categoryService.addCategory(buyGarage);
        categoryService.addCategory(buyAbroad);
        categoryService.addCategory(buyCommerc);

        categoryService.addCategory(snimLand);
        categoryService.addCategory(snimApart);
        categoryService.addCategory(snimRooms);
        categoryService.addCategory(snimHouses);
        categoryService.addCategory(snimGarage);
        categoryService.addCategory(snimAbroad);
        categoryService.addCategory(snimCommerc);

        //3 вкладка   Категории  КуплюПродамСдамСниму назначение родителей

        sellApart.setParentCategory(categoryApartments);
        sellRooms.setParentCategory(categoryRooms);
        sellHouses.setParentCategory(categoryHousesSummerResidencesCottages);
        sellLand.setParentCategory(categoryLand);
        sellGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        sellCommerc.setParentCategory(categoryCommercialProperty);
        sellAbroad.setParentCategory(nullAbroad);
        categoryService.updateCategory(sellApart);
        categoryService.updateCategory(sellRooms);
        categoryService.updateCategory(sellHouses);
        categoryService.updateCategory(sellLand);
        categoryService.updateCategory(sellGarage);
        categoryService.updateCategory(sellCommerc);
        categoryService.updateCategory(sellAbroad);

        rentApart.setParentCategory(categoryApartments);
        rentRooms.setParentCategory(categoryRooms);
        rentHouses.setParentCategory(categoryHousesSummerResidencesCottages);
        rentLand.setParentCategory(categoryLand);
        rentGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        rentCommerc.setParentCategory(categoryCommercialProperty);
        rentAbroad.setParentCategory(nullAbroad);
        categoryService.updateCategory(rentLand);
        categoryService.updateCategory(rentApart);
        categoryService.updateCategory(rentRooms);
        categoryService.updateCategory(rentHouses);
        categoryService.updateCategory(rentGarage);
        categoryService.updateCategory(rentAbroad);
        categoryService.updateCategory(rentCommerc);

        buyLand.setParentCategory(categoryLand);
        buyApart.setParentCategory(categoryApartments);
        buyRooms.setParentCategory(categoryRooms);
        buyHouses.setParentCategory(categoryHousesSummerResidencesCottages);
        buyGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        buyAbroad.setParentCategory(nullAbroad);
        buyCommerc.setParentCategory(categoryCommercialProperty);
        categoryService.updateCategory(buyLand);
        categoryService.updateCategory(buyApart);
        categoryService.updateCategory(buyRooms);
        categoryService.updateCategory(buyHouses);
        categoryService.updateCategory(buyGarage);
        categoryService.updateCategory(buyAbroad);
        categoryService.updateCategory(buyCommerc);

        snimLand.setParentCategory(categoryLand);
        snimApart.setParentCategory(categoryApartments);
        snimRooms.setParentCategory(categoryRooms);
        snimHouses.setParentCategory(categoryHousesSummerResidencesCottages);
        snimGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        snimAbroad.setParentCategory(nullAbroad);
        snimCommerc.setParentCategory(categoryCommercialProperty);
        categoryService.updateCategory(snimLand);
        categoryService.updateCategory(snimApart);
        categoryService.updateCategory(snimRooms);
        categoryService.updateCategory(snimHouses);
        categoryService.updateCategory(snimGarage);
        categoryService.updateCategory(snimAbroad);
        categoryService.updateCategory(snimCommerc);

        //4 вкладка   Категории  квартиры создание

        Category secondaryApart = new Category("Вторичка", "Secondary", null, postings);
        Category newBuildingApart = new Category("Новостройка", "New building", null, postings);

        Category forLongTermRentApart = new Category("На длительный срок", "For long term", null, postings);
        Category forLongTermSnimApart = new Category("На длительный срок", "For long term", null, postings);

        Category byTheDayRentApart = new Category("Посуточно", "By the day", null, postings);
        Category byTheDaySnimApart = new Category("Посуточно", "By the day", null, postings);

        //4 вкладка   Категории  квартиры  добавление в БД

        categoryService.addCategory(secondaryApart);
        categoryService.addCategory(newBuildingApart);

        categoryService.addCategory(forLongTermRentApart);
        categoryService.addCategory(forLongTermSnimApart);

        categoryService.addCategory(byTheDayRentApart);
        categoryService.addCategory(byTheDaySnimApart);

        //4 вкладка   Категории  квартиры назначение родителей

        secondaryApart.setParentCategory(sellApart);
        newBuildingApart.setParentCategory(sellApart);

        forLongTermRentApart.setParentCategory(rentApart);
        forLongTermSnimApart.setParentCategory(snimApart);

        byTheDayRentApart.setParentCategory(rentApart);
        byTheDaySnimApart.setParentCategory(snimApart);

        categoryService.updateCategory(secondaryApart);
        categoryService.updateCategory(newBuildingApart);
        categoryService.updateCategory(forLongTermRentApart);
        categoryService.updateCategory(forLongTermSnimApart);
        categoryService.updateCategory(byTheDayRentApart);
        categoryService.updateCategory(byTheDaySnimApart);

        //4 вкладка   Категории  комнаты создание

        Category forLongTermRentRoom = new Category("На длительный срок", "For long term", null, postings);
        Category forLongTermSnimRoom = new Category("На длительный срок", "For long term", null, postings);
        Category byTheDayRentRoom = new Category("Посуточно", "By the day", null, postings);
        Category byTheDaySnimRoom = new Category("Посуточно", "By the day", null, postings);

        //4 вкладка   Категории  комнаты  добавление в БД

        categoryService.addCategory(forLongTermRentRoom);
        categoryService.addCategory(forLongTermSnimRoom);
        categoryService.addCategory(byTheDayRentRoom);
        categoryService.addCategory(byTheDaySnimRoom);

        //4 вкладка   Категории  комнаты назначение родителей

        forLongTermRentRoom.setParentCategory(rentRooms);
        forLongTermSnimRoom.setParentCategory(snimRooms);
        byTheDayRentRoom.setParentCategory(rentRooms);
        byTheDaySnimRoom.setParentCategory(snimRooms);
        categoryService.updateCategory(forLongTermRentRoom);
        categoryService.updateCategory(forLongTermSnimRoom);
        categoryService.updateCategory(byTheDayRentRoom);
        categoryService.updateCategory(byTheDaySnimRoom);

        //4 вкладка   Категории  комнаты создание

        Category forLongTermRentHouse = new Category("На длительный срок", "For long term", null, postings);
        Category forLongTermSnimHouse = new Category("На длительный срок", "For long term", null, postings);
        Category byTheDayRentHouse = new Category("Посуточно", "By the day", null, postings);
        Category byTheDaySnimHouse = new Category("Посуточно", "By the day", null, postings);

        //4 вкладка   Категории  комнаты  добавление в БД

        categoryService.addCategory(forLongTermRentHouse);
        categoryService.addCategory(forLongTermSnimHouse);
        categoryService.addCategory(byTheDayRentHouse);
        categoryService.addCategory(byTheDaySnimHouse);

        //4 вкладка   Категории  комнаты назначение родителей

        forLongTermRentHouse.setParentCategory(rentHouses);
        forLongTermSnimHouse.setParentCategory(snimHouses);
        byTheDayRentHouse.setParentCategory(rentHouses);
        byTheDaySnimHouse.setParentCategory(snimHouses);
        categoryService.updateCategory(forLongTermRentHouse);
        categoryService.updateCategory(forLongTermSnimHouse);
        categoryService.updateCategory(byTheDayRentHouse);
        categoryService.updateCategory(byTheDaySnimHouse);

        //4 вкладка   Категории  земля создание

        Category settlementsSellLand = new Category("Поселений (ИЖС)", "Settlements (IHC)", null, postings);
        Category agriculturalPurposesSellLand = new Category("Сельхозназначения (СНТ, ДНП)", "Agricultural purposes (HNPP,DNPP)", null, postings);
        Category industrialPurposesSellLand = new Category("Промназначения", "Industrial purposes", null, postings);

        Category settlementsRentLand = new Category("Поселений (ИЖС)", "Settlements (IHC)", null, postings);
        Category agriculturalPurposesRentLand = new Category("Сельхозназначения (СНТ, ДНП)", "Agricultural purposes (HNPP,DNPP)", null, postings);
        Category industrialPurposesRentLand = new Category("Промназначения", "Industrial purposes", null, postings);

        Category settlementsBuyLand = new Category("Поселений (ИЖС)", "Settlements (IHC)", null, postings);
        Category agriculturalPurposesBuyLand = new Category("Сельхозназначения (СНТ, ДНП)", "Agricultural purposes (HNPP,DNPP)", null, postings);
        Category industrialPurposesBuyLand = new Category("Промназначения", "Industrial purposes", null, postings);

        Category settlementsSnimLand = new Category("Поселений (ИЖС)", "Settlements (IHC)", null, postings);
        Category agriculturalPurposesSnimLand = new Category("Сельхозназначения (СНТ, ДНП)", "Agricultural purposes (HNPP,DNPP)", null, postings);
        Category industrialPurposesSnimLand = new Category("Промназначения", "Industrial purposes", null, postings);

        //4 вкладка   Категории  земля  добавление в БД

        categoryService.addCategory(settlementsSellLand);
        categoryService.addCategory(agriculturalPurposesSellLand);
        categoryService.addCategory(industrialPurposesSellLand);

        categoryService.addCategory(settlementsRentLand);
        categoryService.addCategory(agriculturalPurposesRentLand);
        categoryService.addCategory(industrialPurposesRentLand);

        categoryService.addCategory(settlementsBuyLand);
        categoryService.addCategory(agriculturalPurposesBuyLand);
        categoryService.addCategory(industrialPurposesBuyLand);

        categoryService.addCategory(settlementsSnimLand);
        categoryService.addCategory(agriculturalPurposesSnimLand);
        categoryService.addCategory(industrialPurposesSnimLand);

        //4 вкладка   Категории  земля назначение родителей
        settlementsSellLand.setParentCategory(sellLand);
        agriculturalPurposesSellLand.setParentCategory(sellLand);
        industrialPurposesSellLand.setParentCategory(sellLand);

        settlementsRentLand.setParentCategory(rentLand);
        agriculturalPurposesRentLand.setParentCategory(rentLand);
        industrialPurposesRentLand.setParentCategory(rentLand);

        settlementsBuyLand.setParentCategory(buyLand);
        agriculturalPurposesBuyLand.setParentCategory(buyLand);
        industrialPurposesBuyLand.setParentCategory(buyLand);

        settlementsSnimLand.setParentCategory(snimLand);
        agriculturalPurposesSnimLand.setParentCategory(snimLand);
        industrialPurposesSnimLand.setParentCategory(snimLand);

        categoryService.updateCategory(settlementsSellLand);
        categoryService.updateCategory(agriculturalPurposesSellLand);
        categoryService.updateCategory(industrialPurposesSellLand);

        categoryService.updateCategory(settlementsRentLand);
        categoryService.updateCategory(agriculturalPurposesRentLand);
        categoryService.updateCategory(industrialPurposesRentLand);

        categoryService.updateCategory(settlementsBuyLand);
        categoryService.updateCategory(agriculturalPurposesBuyLand);
        categoryService.updateCategory(industrialPurposesBuyLand);

        categoryService.updateCategory(settlementsSnimLand);
        categoryService.updateCategory(agriculturalPurposesSnimLand);
        categoryService.updateCategory(industrialPurposesSnimLand);


        //4 вкладка   Категории  гараж создание

        Category garageSellGarage = new Category("Гараж", "Garage", null, postings);
        Category parkingSpaceSellGarage = new Category("Машиноместо", "Parking space", null, postings);

        Category garageRentGarage = new Category("Гараж", "Garage", null, postings);
        Category parkingSpaceRentGarage = new Category("Машиноместо", "Parking space", null, postings);

        Category garageBuyGarage = new Category("Гараж", "Garage", null, postings);
        Category parkingSpaceBuyGarage = new Category("Машиноместо", "Parking space", null, postings);

        Category garageSnimGarage = new Category("Гараж", "Garage", null, postings);
        Category parkingSpaceSnimGarage = new Category("Машиноместо", "Parking space", null, postings);

        //4 вкладка   Категории  гараж  добавление в БД

        categoryService.addCategory(garageSellGarage);
        categoryService.addCategory(parkingSpaceSellGarage);

        categoryService.addCategory(garageRentGarage);
        categoryService.addCategory(parkingSpaceRentGarage);

        categoryService.addCategory(garageBuyGarage);
        categoryService.addCategory(parkingSpaceBuyGarage);

        categoryService.addCategory(garageSnimGarage);
        categoryService.addCategory(parkingSpaceSnimGarage);

        //4 вкладка   Категории  гараж назначение родителей

        garageSellGarage.setParentCategory(sellGarage);
        parkingSpaceSellGarage.setParentCategory(sellGarage);

        garageRentGarage.setParentCategory(rentGarage);
        parkingSpaceRentGarage.setParentCategory(rentGarage);

        garageBuyGarage.setParentCategory(buyGarage);
        parkingSpaceBuyGarage.setParentCategory(buyGarage);

        garageSnimGarage.setParentCategory(snimGarage);
        parkingSpaceSnimGarage.setParentCategory(snimGarage);

        categoryService.updateCategory(garageSellGarage);
        categoryService.updateCategory(parkingSpaceSellGarage);

        categoryService.updateCategory(garageRentGarage);
        categoryService.updateCategory(parkingSpaceRentGarage);

        categoryService.updateCategory(garageBuyGarage);
        categoryService.updateCategory(parkingSpaceBuyGarage);

        categoryService.updateCategory(garageSnimGarage);
        categoryService.updateCategory(parkingSpaceSnimGarage);

        //4 вкладка   Категории  загран создание

        Category apartmentsSellAbroad = new Category("Квартира, апартаменты", "Apartment, apartments", null, postings);
        Category villaSellAbroad = new Category("Дом, вилла", "House, villa", null, postings);
        Category plotSellAbroad = new Category("Земельный участок", "Land plot", null, postings);
        Category parkingSpaceGarageSellAbroad = new Category("Гараж, машиноместо", "Garage, parking space", null, postings);
        Category estateSellAbroad = new Category("Комерческая недвижимость", "Commercial real estate", null, postings);

        Category apartmentsRentAbroad = new Category("Квартира, апартаменты", "Apartment, apartments", null, postings);
        Category villaRentAbroad = new Category("Дом, вилла", "House, villa", null, postings);
        Category plotRentAbroad = new Category("Земельный участок", "Land plot", null, postings);
        Category parkingSpaceGarageRentAbroad = new Category("Гараж, машиноместо", "Garage, parking space", null, postings);
        Category estateRentAbroad = new Category("Комерческая недвижимость", "Commercial real estate", null, postings);

        Category apartmentsBuyAbroad = new Category("Квартира, апартаменты", "Apartment, apartments", null, postings);
        Category villaBuyAbroad = new Category("Дом, вилла", "House, villa", null, postings);
        Category plotBuyAbroad = new Category("Земельный участок", "Land plot", null, postings);
        Category parkingSpaceGarageBuyAbroad = new Category("Гараж, машиноместо", "Garage, parking space", null, postings);
        Category estateBuyAbroad = new Category("Комерческая недвижимость", "Commercial real estate", null, postings);

        Category apartmentsSnimAbroad = new Category("Квартира, апартаменты", "Apartment, apartments", null, postings);
        Category villaSnimAbroad = new Category("Дом, вилла", "House, villa", null, postings);
        Category plotSnimAbroad = new Category("Земельный участок", "Land plot", null, postings);
        Category parkingSpaceGarageSnimAbroad = new Category("Гараж, машиноместо", "Garage, parking space", null, postings);
        Category estateSnimAbroad = new Category("Комерческая недвижимость", "Commercial real estate", null, postings);


        //4 вкладка   Категории  загран  добавление в БД

        categoryService.addCategory(apartmentsSellAbroad);
        categoryService.addCategory(villaSellAbroad);
        categoryService.addCategory(plotSellAbroad);
        categoryService.addCategory(parkingSpaceGarageSellAbroad);
        categoryService.addCategory(estateSellAbroad);

        categoryService.addCategory(apartmentsRentAbroad);
        categoryService.addCategory(villaRentAbroad);
        categoryService.addCategory(plotRentAbroad);
        categoryService.addCategory(parkingSpaceGarageRentAbroad);
        categoryService.addCategory(estateRentAbroad);

        categoryService.addCategory(apartmentsBuyAbroad);
        categoryService.addCategory(villaBuyAbroad);
        categoryService.addCategory(plotBuyAbroad);
        categoryService.addCategory(parkingSpaceGarageBuyAbroad);
        categoryService.addCategory(estateBuyAbroad);

        categoryService.addCategory(apartmentsSnimAbroad);
        categoryService.addCategory(villaSnimAbroad);
        categoryService.addCategory(plotSnimAbroad);
        categoryService.addCategory(parkingSpaceGarageSnimAbroad);
        categoryService.addCategory(estateSnimAbroad);

        //4 вкладка   Категории  загран назначение родителей

        apartmentsSellAbroad.setParentCategory(sellAbroad);
        villaSellAbroad.setParentCategory(sellAbroad);
        plotSellAbroad.setParentCategory(sellAbroad);
        parkingSpaceGarageSellAbroad.setParentCategory(sellAbroad);
        estateSellAbroad.setParentCategory(sellAbroad);

        apartmentsRentAbroad.setParentCategory(rentAbroad);
        villaRentAbroad.setParentCategory(rentAbroad);
        plotRentAbroad.setParentCategory(rentAbroad);
        parkingSpaceGarageRentAbroad.setParentCategory(rentAbroad);
        estateRentAbroad.setParentCategory(rentAbroad);

        apartmentsBuyAbroad.setParentCategory(buyAbroad);
        villaBuyAbroad.setParentCategory(buyAbroad);
        plotBuyAbroad.setParentCategory(buyAbroad);
        parkingSpaceGarageBuyAbroad.setParentCategory(buyAbroad);
        estateBuyAbroad.setParentCategory(buyAbroad);

        apartmentsSnimAbroad.setParentCategory(snimAbroad);
        villaSnimAbroad.setParentCategory(snimAbroad);
        plotSnimAbroad.setParentCategory(snimAbroad);
        parkingSpaceGarageSnimAbroad.setParentCategory(snimAbroad);
        estateSnimAbroad.setParentCategory(snimAbroad);

        categoryService.updateCategory(apartmentsSellAbroad);
        categoryService.updateCategory(villaSellAbroad);
        categoryService.updateCategory(plotSellAbroad);
        categoryService.updateCategory(parkingSpaceGarageSellAbroad);
        categoryService.updateCategory(estateSellAbroad);

        categoryService.updateCategory(apartmentsRentAbroad);
        categoryService.updateCategory(villaRentAbroad);
        categoryService.updateCategory(plotRentAbroad);
        categoryService.updateCategory(parkingSpaceGarageRentAbroad);
        categoryService.updateCategory(estateRentAbroad);

        categoryService.updateCategory(apartmentsBuyAbroad);
        categoryService.updateCategory(villaBuyAbroad);
        categoryService.updateCategory(plotBuyAbroad);
        categoryService.updateCategory(parkingSpaceGarageBuyAbroad);
        categoryService.updateCategory(estateBuyAbroad);

        categoryService.updateCategory(apartmentsSnimAbroad);
        categoryService.updateCategory(villaSnimAbroad);
        categoryService.updateCategory(plotSnimAbroad);
        categoryService.updateCategory(parkingSpaceGarageSnimAbroad);
        categoryService.updateCategory(estateSnimAbroad);

//4 вкладка   Категории  комерческое создание

        Category officeSellCommerc = new Category("Офисное помещение", "Office space", null, postings);
        Category placementRoomSellCommerc = new Category("Помещение сввободного размещения", "Free placement room", null, postings);
        Category premiseSellCommerc = new Category("Торговое помещение", "Commercial premise", null, postings);
        Category warehouseSellCommerc = new Category("Складское помещение", "Warehouse", null, postings);
        Category facilitySellCommerc = new Category("Производственное помещение", "Production facility", null, postings);
        Category cateringSellCommerc = new Category("Помещение общественного питания", "Public catering premises", null, postings);
        Category hotelSellCommerc = new Category("Гостинница", "Hotel", null, postings);

        Category officeRentCommerc = new Category("Офисное помещение", "Office space", null, postings);
        Category placementRoomRentCommerc = new Category("Помещение сввободного размещения", "Free placement room", null, postings);
        Category premiseRentCommerc = new Category("Торговое помещение", "Commercial premise", null, postings);
        Category warehouseRentCommerc = new Category("Складское помещение", "Warehouse", null, postings);
        Category facilityRentCommerc = new Category("Производственное помещение", "Production facility", null, postings);
        Category cateringRentCommerc = new Category("Помещение общественного питания", "Public catering premises", null, postings);
        Category hotelRentCommerc = new Category("Гостинница", "Hotel", null, postings);

        Category officeBuyCommerc = new Category("Офисное помещение", "Office space", null, postings);
        Category placementRoomBuyCommerc = new Category("Помещение сввободного размещения", "Free placement room", null, postings);
        Category premiseBuyCommerc = new Category("Торговое помещение", "Commercial premise", null, postings);
        Category warehouseBuyCommerc = new Category("Складское помещение", "Warehouse", null, postings);
        Category facilityBuyCommerc = new Category("Производственное помещение", "Production facility", null, postings);
        Category cateringBuyCommerc = new Category("Помещение общественного питания", "Public catering premises", null, postings);
        Category hotelBuyCommerc = new Category("Гостинница", "Hotel", null, postings);

        Category officeSnimCommerc = new Category("Офисное помещение", "Office space", null, postings);
        Category placementRoomSnimCommerc = new Category("Помещение сввободного размещения", "Free placement room", null, postings);
        Category premiseSnimCommerc = new Category("Торговое помещение", "Commercial premise", null, postings);
        Category warehouseSnimCommerc = new Category("Складское помещение", "Warehouse", null, postings);
        Category facilitySnimCommerc = new Category("Производственное помещение", "Production facility", null, postings);
        Category cateringSnimCommerc = new Category("Помещение общественного питания", "Public catering premises", null, postings);
        Category hotelSnimCommerc = new Category("Гостинница", "Hotel", null, postings);

        //4 вкладка   Категории  комерческое  добавление в БД

        categoryService.addCategory(officeSellCommerc);
        categoryService.addCategory(placementRoomSellCommerc);
        categoryService.addCategory(premiseSellCommerc);
        categoryService.addCategory(warehouseSellCommerc);
        categoryService.addCategory(facilitySellCommerc);
        categoryService.addCategory(cateringSellCommerc);
        categoryService.addCategory(hotelSellCommerc);

        categoryService.addCategory(officeRentCommerc);
        categoryService.addCategory(placementRoomRentCommerc);
        categoryService.addCategory(premiseRentCommerc);
        categoryService.addCategory(warehouseRentCommerc);
        categoryService.addCategory(facilityRentCommerc);
        categoryService.addCategory(cateringRentCommerc);
        categoryService.addCategory(hotelRentCommerc);

        categoryService.addCategory(officeBuyCommerc);
        categoryService.addCategory(placementRoomBuyCommerc);
        categoryService.addCategory(premiseBuyCommerc);
        categoryService.addCategory(warehouseBuyCommerc);
        categoryService.addCategory(facilityBuyCommerc);
        categoryService.addCategory(cateringBuyCommerc);
        categoryService.addCategory(hotelBuyCommerc);

        categoryService.addCategory(officeSnimCommerc);
        categoryService.addCategory(placementRoomSnimCommerc);
        categoryService.addCategory(premiseSnimCommerc);
        categoryService.addCategory(warehouseSnimCommerc);
        categoryService.addCategory(facilitySnimCommerc);
        categoryService.addCategory(cateringSnimCommerc);
        categoryService.addCategory(hotelSnimCommerc);

        //4 вкладка   Категории  комерческое назначение родителей

        officeSellCommerc.setParentCategory(sellCommerc);
        placementRoomSellCommerc.setParentCategory(sellCommerc);
        premiseSellCommerc.setParentCategory(sellCommerc);
        warehouseSellCommerc.setParentCategory(sellCommerc);
        facilitySellCommerc.setParentCategory(sellCommerc);
        cateringSellCommerc.setParentCategory(sellCommerc);
        hotelSellCommerc.setParentCategory(sellCommerc);

        officeRentCommerc.setParentCategory(rentCommerc);
        placementRoomRentCommerc.setParentCategory(rentCommerc);
        premiseRentCommerc.setParentCategory(rentCommerc);
        warehouseRentCommerc.setParentCategory(rentCommerc);
        facilityRentCommerc.setParentCategory(rentCommerc);
        cateringRentCommerc.setParentCategory(rentCommerc);
        hotelRentCommerc.setParentCategory(rentCommerc);

        officeBuyCommerc.setParentCategory(buyCommerc);
        placementRoomBuyCommerc.setParentCategory(buyCommerc);
        premiseBuyCommerc.setParentCategory(buyCommerc);
        warehouseBuyCommerc.setParentCategory(buyCommerc);
        facilityBuyCommerc.setParentCategory(buyCommerc);
        cateringBuyCommerc.setParentCategory(buyCommerc);
        hotelBuyCommerc.setParentCategory(buyCommerc);

        officeSnimCommerc.setParentCategory(snimCommerc);
        placementRoomSnimCommerc.setParentCategory(snimCommerc);
        premiseSnimCommerc.setParentCategory(snimCommerc);
        warehouseSnimCommerc.setParentCategory(snimCommerc);
        facilitySnimCommerc.setParentCategory(snimCommerc);
        cateringSnimCommerc.setParentCategory(snimCommerc);
        hotelSnimCommerc.setParentCategory(snimCommerc);

        categoryService.updateCategory(officeSellCommerc);
        categoryService.updateCategory(placementRoomSellCommerc);
        categoryService.updateCategory(premiseSellCommerc);
        categoryService.updateCategory(warehouseSellCommerc);
        categoryService.updateCategory(facilitySellCommerc);
        categoryService.updateCategory(cateringSellCommerc);
        categoryService.updateCategory(hotelSellCommerc);

        categoryService.updateCategory(officeRentCommerc);
        categoryService.updateCategory(placementRoomRentCommerc);
        categoryService.updateCategory(premiseRentCommerc);
        categoryService.updateCategory(warehouseRentCommerc);
        categoryService.updateCategory(facilityRentCommerc);
        categoryService.updateCategory(cateringRentCommerc);
        categoryService.updateCategory(hotelRentCommerc);

        categoryService.updateCategory(officeBuyCommerc);
        categoryService.updateCategory(placementRoomBuyCommerc);
        categoryService.updateCategory(premiseBuyCommerc);
        categoryService.updateCategory(warehouseBuyCommerc);
        categoryService.updateCategory(facilityBuyCommerc);
        categoryService.updateCategory(cateringBuyCommerc);
        categoryService.updateCategory(hotelBuyCommerc);

        categoryService.updateCategory(officeSnimCommerc);
        categoryService.updateCategory(placementRoomSnimCommerc);
        categoryService.updateCategory(premiseSnimCommerc);
        categoryService.updateCategory(warehouseSnimCommerc);
        categoryService.updateCategory(facilitySnimCommerc);
        categoryService.updateCategory(cateringSnimCommerc);
        categoryService.updateCategory(hotelSnimCommerc);
    }
}
