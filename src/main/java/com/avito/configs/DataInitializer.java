package com.avito.configs;

import com.avito.models.Category;
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
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        initCategoriesRU();
        initCategoriesEN();
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

        List<Category> categories = categoryService.getAllCategories().stream().sorted((Comparator.comparing(Category::getName))).collect(Collectors.toList());
        assert categories.size() >= 3;

        Posting posting = new Posting();
        posting.setTitle("Коттедж 400 м² на участке 3 сот.");
        posting.setCategory(categories.get(0));
        posting.setUser(adminUser);
        posting.setFullDescription("Коттедж на два хозяина. На первом этаже кухня, зал и туалет с душем, второй этаж три комнаты и туалет с ванной, третий этаж-две комнаты. Цокольный этаж с гаражом и комнатой. Готов к заселению, возможна долгосрочная аренда.");
        posting.setShortDescription("Коттедж");
        posting.setPrice(3_500_000);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("Posting title");
        posting.setCategory(categories.get(1));
        posting.setUser(adminUser);
        posting.setFullDescription("Full description, long text.");
        posting.setShortDescription("Some posting");
        posting.setPrice(30_000);
        postingService.addPosting(posting);

        posting = new Posting();
        posting.setTitle("Posting title 2");
        posting.setCategory(categories.get(2));
        posting.setUser(userUser);
        posting.setFullDescription("Full description, long text. (owner must be user)");
        posting.setShortDescription("Some posting of user");
        posting.setPrice(4_500);
        postingService.addPosting(posting);
    }

    private void initCategoriesRU() {

        if (categoryService.getAllCategories().size() !=0) {
            return;
        }

        Set<Posting> postings = new HashSet<>();

        //1 вкладка Категории создание

        Category categoryTransport = new Category("Транспорт", null, "ru" ,postings);
        Category categoryProperty = new Category("Недвижимость", null, "ru" ,postings);
        Category categoryJob = new Category("Работа", null, "ru" ,postings);
        Category categoryServices = new Category("Услуги", null, "ru" ,postings);
        Category categoryForHomeAndGarden = new Category("Для дома и дачи", null, "ru" ,postings);
        Category categoryPersonalItems = new Category("Личные вещи", null, "ru" ,postings);
        Category categoryConsumerElectronics = new Category("Бытовая электроника", null, "ru" ,postings);
        Category categoryHobbiesAndLeisure = new Category("Хобби и отдых", null, "ru" ,postings);
        Category cateroryAnimals = new Category("Животные", null, "ru" ,postings);
        Category categoryBusiness = new Category("Для бизнеса", null, "ru" ,postings);

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

        Category categoryCars = new Category("Автомобили", null, "ru" ,postings);
        Category categoryMotorcycles = new Category("Мотоциклы и мототехника", null, "ru" ,postings);
        Category categoryTrucksAndSpecialEquipment = new Category("Грузовики и спецтехника", null, "ru" ,postings);
        Category categoryWaterTransport = new Category("Водный транспорт", null, "ru" ,postings);
        Category categoryPartsAndAccessories = new Category("Запчасти и аксессуары", null, "ru" ,postings);

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

        Category oldCars = new Category("С пробегом", null, "ru" ,postings);
        Category newCars = new Category("Новый", null, "ru" ,postings);

        //3 вкладка Категория автомобили добавление в БД

        categoryService.addCategory(oldCars);
        categoryService.addCategory(newCars);


        //3 вкладка Категория автомобили назначение родителей

        oldCars.setParentCategory(categoryCars);
        newCars.setParentCategory(categoryCars);
        categoryService.updateCategory(oldCars);
        categoryService.updateCategory(newCars);

        //3 вкладка Категория водный транспорт создание

        Category rowboats = new Category("Вёсельные лодки", null, "ru" ,postings);
        Category jetSkis = new Category("Гидроциклы", null, "ru" ,postings);
        Category boatsAndYachts = new Category("Катера и яхты", null, "ru" ,postings);
        Category kayaksAndCanoes = new Category("Каяки и каноэ", null, "ru" ,postings);
        Category motorBoats = new Category("Моторные лодки", null, "ru" ,postings);
        Category inflatableBoat = new Category("Надувные лодки", null, "ru" ,postings);

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

        Category buses = new Category("Автобусы", null, "ru" ,postings);
        Category motorhomes = new Category("Автодома", null, "ru" ,postings);
        Category truckCranes = new Category("Автокраны", null, "ru" ,postings);
        Category bulldozers = new Category("Бульдозеры", null, "ru" ,postings);
        Category trucks = new Category("Грузовики", null, "ru" ,postings);
        Category utilityEquipment = new Category("Коммунальная техника", null, "ru" ,postings);
        Category easyTransport = new Category("Лёгкий транспорт", null, "ru" ,postings);
        Category loaders = new Category("Погрузчики", null, "ru" ,postings);
        Category trailers = new Category("Прицепы", null, "ru" ,postings);
        Category agriculturalMachinery = new Category("Сельхозтехника", null, "ru" ,postings);
        Category constructionMachinery = new Category("Строительная техника", null, "ru" ,postings);
        Category loggingEquipment = new Category("Техника для лесозаготовки", null, "ru" ,postings);
        Category tractorUnits = new Category("Тягачи", null, "ru" ,postings);
        Category excavators = new Category("Экскаваторы", null, "ru" ,postings);

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

        Category buggy = new Category("Багги", null, "ru" ,postings);
        Category allTerrainVehicles = new Category("Вездеходы", null, "ru" ,postings);
        Category karting = new Category("Картинг", null, "ru" ,postings);
        Category atv = new Category("Квадроциклы", null, "ru" ,postings);
        Category mopedsAndScooters = new Category("Мопеды и скутеры", null, "ru" ,postings);
        Category motorcycles = new Category("Мотоциклы", null, "ru" ,postings);
        Category snowmobiles = new Category("Снегоходы", null, "ru" ,postings);

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

        Category categorySummary = new Category("Резюме", null, "ru" ,postings);
        Category categoryVacancies = new Category("Вакансии", null, "ru" ,postings);

        //2 вкладка   Категория работа добавление в БД

        categoryService.addCategory(categorySummary);
        categoryService.addCategory(categoryVacancies);

        //2 вкладка   Категория работа назначение родителей

        categorySummary.setParentCategory(categoryJob);
        categoryVacancies.setParentCategory(categoryJob);
        categoryService.updateCategory(categorySummary);
        categoryService.updateCategory(categoryVacancies);

        //3 вкладка   Категория  Резюме и Вакансии создание

        Category itRes = new Category("IT, интернет, телеком", null, "ru" ,postings);
        Category itVac = new Category("IT, интернет, телеком", null, "ru" ,postings);
        Category carBusinessRes = new Category("Автомобильный бизнес", null, "ru" ,postings);
        Category carBusinessVac = new Category("Автомобильный бизнес", null, "ru" ,postings);
        Category administrativeWorkRes = new Category("Административная работа", null, "ru" ,postings);
        Category administrativeWorkVac = new Category("Административная работа", null, "ru" ,postings);
        Category bankRes = new Category("Банки, инвестиции", null, "ru" ,postings);
        Category bankVac = new Category("Банки, инвестиции", null, "ru" ,postings);
        Category withoutRes = new Category("Без опыта, студенты", null, "ru" ,postings);
        Category withoutVac = new Category("Без опыта, студенты", null, "ru" ,postings);
        Category accRes = new Category("Бухгалтерия, финансы", null, "ru" ,postings);
        Category accVac = new Category("Бухгалтерия, финансы", null, "ru" ,postings);
        Category hiManagRes = new Category("Высший менеджмент", null, "ru" ,postings);
        Category hiManagVac = new Category("Высший менеджмент", null, "ru" ,postings);
        Category nkoRes = new Category("Госслужба, НКО", null, "ru" ,postings);
        Category nkoVac = new Category("Госслужба, НКО", null, "ru" ,postings);
        Category homeStaffRes = new Category("Домашний персонал", null, "ru" ,postings);
        Category homeStaffVac = new Category("Домашний персонал", null, "ru" ,postings);
        Category ghkRes = new Category("ЖКХ, эксплуатация", null, "ru" ,postings);
        Category ghkVac = new Category("ЖКХ, эксплуатация", null, "ru" ,postings);
        Category artRes = new Category("Искусство, развлечения", null, "ru" ,postings);
        Category artVac = new Category("Искусство, развлечения", null, "ru" ,postings);
        Category consRes = new Category("Консультирование", null, "ru" ,postings);
        Category consVac = new Category("Консультирование", null, "ru" ,postings);
        Category prRes = new Category("Маркетинг, реклама, PR", null, "ru" ,postings);
        Category prVac = new Category("Маркетинг, реклама, PR", null, "ru" ,postings);
        Category medRes = new Category("Медицина, фармацевтика", null, "ru" ,postings);
        Category medVac = new Category("Медицина, фармацевтика", null, "ru" ,postings);
        Category scienceRes = new Category("Образование, наука", null, "ru" ,postings);
        Category scienceVac = new Category("Образование, наука", null, "ru" ,postings);
        Category safetyRes = new Category("Охрана, безопасность", null, "ru" ,postings);
        Category safetyVac = new Category("Охрана, безопасность", null, "ru" ,postings);
        Category salesRes = new Category("Продажи", null, "ru" ,postings);
        Category salesVac = new Category("Продажи", null, "ru" ,postings);
        Category agriculturalRes = new Category("Производство, сырьё, с/х", null, "ru" ,postings);
        Category agriculturalVac = new Category("Производство, сырьё, с/х", null, "ru" ,postings);
        Category insuranceRes = new Category("Страхование", null, "ru" ,postings);
        Category insuranceVac = new Category("Страхование", null, "ru" ,postings);
        Category buildRes = new Category("Строительство", null, "ru" ,postings);
        Category buildVac = new Category("Строительство", null, "ru" ,postings);
        Category transpRes = new Category("Транспорт, логистика", null, "ru" ,postings);
        Category transpVac = new Category("Транспорт, логистика", null, "ru" ,postings);
        Category tourismRes = new Category("Туризм, рестораны", null, "ru" ,postings);
        Category tourismVac = new Category("Туризм, рестораны", null, "ru" ,postings);
        Category fitnessRes = new Category("Фитнес, салоны красоты", null, "ru" ,postings);
        Category fitnessVac = new Category("Фитнес, салоны красоты", null, "ru" ,postings);
        Category lawRes = new Category("Юриспруденция", null, "ru" ,postings);
        Category lawVac = new Category("Юриспруденция", null, "ru" ,postings);

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

        Category categoryApartments = new Category("Квартиры", null, "ru" ,postings);
        Category categoryRooms = new Category("Комнаты", null, "ru" ,postings);
        Category categoryHousesSummerResidencesCottages = new Category("Дома, дачи, коттеджи", null, "ru" ,postings);
        Category categoryLand = new Category("Земельные участки", null, "ru" ,postings);
        Category categoryGaragesAndParkingSpaces = new Category("Гаражи и машиноместа", null, "ru" ,postings);
        Category categoryCommercialProperty = new Category("Коммерческая недвижимость", null, "ru" ,postings);
        Category nullAbroad = new Category("Недвижимость за рубежом", null, "ru" ,postings);

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

        Category sellApart = new Category("Продам", null, "ru" ,postings);
        Category sellRooms = new Category("Продам", null, "ru" ,postings);
        Category sellHouses = new Category("Продам", null, "ru" ,postings);
        Category sellLand = new Category("Продам", null, "ru" ,postings);
        Category sellGarage = new Category("Продам", null, "ru" ,postings);
        Category sellCommerc = new Category("Продам", null, "ru" ,postings);
        Category sellAbroad = new Category("Продам", null, "ru" ,postings);

        Category rentLand = new Category("Сдам", null, "ru" ,postings);
        Category rentApart = new Category("Сдам", null, "ru" ,postings);
        Category rentRooms = new Category("Сдам", null, "ru" ,postings);
        Category rentHouses = new Category("Сдам", null, "ru" ,postings);
        Category rentGarage = new Category("Сдам", null, "ru" ,postings);
        Category rentAbroad = new Category("Сдам", null, "ru" ,postings);
        Category rentCommerc = new Category("Сдам", null, "ru" ,postings);

        Category buyLand = new Category("Куплю", null, "ru" ,postings);
        Category buyApart = new Category("Куплю", null, "ru" ,postings);
        Category buyRooms = new Category("Куплю", null, "ru" ,postings);
        Category buyHouses = new Category("Куплю", null, "ru" ,postings);
        Category buyGarage = new Category("Куплю", null, "ru" ,postings);
        Category buyAbroad = new Category("Куплю", null, "ru" ,postings);
        Category buyCommerc = new Category("Куплю", null, "ru" ,postings);

        Category snimLand = new Category("Сниму", null, "ru" ,postings);
        Category snimOutApart = new Category("Сниму", null, "ru" ,postings);
        Category snimOutRooms = new Category("Сниму", null, "ru" ,postings);
        Category snimHouses = new Category("Сниму", null, "ru" ,postings);
        Category snimGarage = new Category("Сниму", null, "ru" ,postings);
        Category snimAbroad = new Category("Сниму", null, "ru" ,postings);
        Category snimCommerc = new Category("Сниму", null, "ru" ,postings);

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
        categoryService.addCategory(snimOutApart);
        categoryService.addCategory(snimOutRooms);
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

        rentLand.setParentCategory(categoryApartments);
        rentApart.setParentCategory(categoryRooms);
        rentRooms.setParentCategory(categoryHousesSummerResidencesCottages);
        rentHouses.setParentCategory(categoryLand);
        rentGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        rentAbroad.setParentCategory(categoryCommercialProperty);
        rentCommerc.setParentCategory(nullAbroad);
        categoryService.updateCategory(rentLand);
        categoryService.updateCategory(rentApart);
        categoryService.updateCategory(rentRooms);
        categoryService.updateCategory(rentHouses);
        categoryService.updateCategory(rentGarage);
        categoryService.updateCategory(rentAbroad);
        categoryService.updateCategory(rentCommerc);

        buyLand.setParentCategory(categoryApartments);
        buyApart.setParentCategory(categoryRooms);
        buyRooms.setParentCategory(categoryHousesSummerResidencesCottages);
        buyHouses.setParentCategory(categoryLand);
        buyGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        buyAbroad.setParentCategory(categoryCommercialProperty);
        buyCommerc.setParentCategory(nullAbroad);
        categoryService.updateCategory(buyLand);
        categoryService.updateCategory(buyApart);
        categoryService.updateCategory(buyRooms);
        categoryService.updateCategory(buyHouses);
        categoryService.updateCategory(buyGarage);
        categoryService.updateCategory(buyAbroad);
        categoryService.updateCategory(buyCommerc);

        snimLand.setParentCategory(categoryApartments);
        snimOutApart.setParentCategory(categoryRooms);
        snimOutRooms.setParentCategory(categoryHousesSummerResidencesCottages);
        snimHouses.setParentCategory(categoryLand);
        snimGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        snimAbroad.setParentCategory(categoryCommercialProperty);
        snimCommerc.setParentCategory(nullAbroad);
        categoryService.updateCategory(snimLand);
        categoryService.updateCategory(snimOutApart);
        categoryService.updateCategory(snimOutRooms);
        categoryService.updateCategory(snimHouses);
        categoryService.updateCategory(snimGarage);
        categoryService.updateCategory(snimAbroad);
        categoryService.updateCategory(snimCommerc);
    }
    private void initCategoriesEN() {

        if (categoryService.getAllCategories().size() == 258) {
            return;
        }
        Set<Posting> postings = null;

        //1 вкладка Категории создание

        Category categoryTransport = new Category("Transport", null, "en" ,postings);
        Category categoryProperty = new Category("Property", null, "en" ,postings);
        Category categoryJob = new Category("Job", null, "en" ,postings);
        Category categoryServices = new Category("Services", null, "en" ,postings);
        Category categoryForHomeAndGarden = new Category("For home and garden", null, "en" ,postings);
        Category categoryPersonalItems = new Category("Personal items", null, "en" ,postings);
        Category categoryConsumerElectronics = new Category("Consumer electronics", null, "en" ,postings);
        Category categoryHobbiesAndLeisure = new Category("Hobbies and leisure", null, "en" ,postings);
        Category cateroryAnimals = new Category("Animals", null, "en" ,postings);
        Category categoryBusiness = new Category("Business", null, "en" ,postings);

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

        Category categoryCars = new Category("Cars", null, "en" ,postings);
        Category categoryMotorcycles = new Category("Motorcycles", null, "en" ,postings);
        Category categoryTrucksAndSpecialEquipment = new Category("Trucks and special equipment", null, "en" ,postings);
        Category categoryWaterTransport = new Category("Water transport", null, "en" ,postings);
        Category categoryPartsAndAccessories = new Category("Parts and accessories", null, "en" ,postings);

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

        Category oldCars = new Category("With mileage", null, "en" ,postings);
        Category newCars = new Category("New", null, "en" ,postings);

        //3 вкладка Категория автомобили добавление в БД

        categoryService.addCategory(oldCars);
        categoryService.addCategory(newCars);


        //3 вкладка Категория автомобили назначение родителей

        oldCars.setParentCategory(categoryCars);
        newCars.setParentCategory(categoryCars);
        categoryService.updateCategory(oldCars);
        categoryService.updateCategory(newCars);

        //3 вкладка Категория водный транспорт создание

        Category rowboats = new Category("Rowing boats", null, "en" ,postings);
        Category jetSkis = new Category("Jet skis", null, "en" ,postings);
        Category boatsAndYachts = new Category("Boats and yachts", null, "en" ,postings);
        Category kayaksAndCanoes = new Category("Kayaks and canoes", null, "en" ,postings);
        Category motorBoats = new Category("Motor boats", null, "en" ,postings);
        Category inflatableBoat = new Category("Inflatable boat", null, "en" ,postings);

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

        Category buses = new Category("Buses", null, "en" ,postings);
        Category motorhomes = new Category("Auto houses", null, "en" ,postings);
        Category truckCranes = new Category("Truck cranes", null, "en" ,postings);
        Category bulldozers = new Category("Bulldozers", null, "en" ,postings);
        Category trucks = new Category("Trucks", null, "en" ,postings);
        Category utilityEquipment = new Category("Utility equipment", null, "en" ,postings);
        Category easyTransport = new Category("Light transport", null, "en" ,postings);
        Category loaders = new Category("Loaders", null, "en" ,postings);
        Category trailers = new Category("Trailers", null, "en" ,postings);
        Category agriculturalMachinery = new Category("Agricultural machinery", null, "en" ,postings);
        Category constructionMachinery = new Category("Construction equipment", null, "en" ,postings);
        Category loggingEquipment = new Category("Logging equipment", null, "en" ,postings);
        Category tractorUnits = new Category("Tractors", null, "en" ,postings);
        Category excavators = new Category("Excavators", null, "en" ,postings);

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

        Category buggy = new Category("Buggy", null, "en" ,postings);
        Category allTerrainVehicles = new Category("Rovers", null, "en" ,postings);
        Category karting = new Category("Karting", null, "en" ,postings);
        Category atv = new Category("ATVs", null, "en" ,postings);
        Category mopedsAndScooters = new Category("Mopeds and scooters", null, "en" ,postings);
        Category motorcycles = new Category("Motorcycles", null, "en" ,postings);
        Category snowmobiles = new Category("Snowmobiles", null, "en" ,postings);

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

        Category categorySummary = new Category("Summary", null, "en" ,postings);
        Category categoryVacancies = new Category("Job openings", null, "en" ,postings);

        //2 вкладка   Категория работа добавление в БД

        categoryService.addCategory(categorySummary);
        categoryService.addCategory(categoryVacancies);

        //2 вкладка   Категория работа назначение родителей

        categorySummary.setParentCategory(categoryJob);
        categoryVacancies.setParentCategory(categoryJob);
        categoryService.updateCategory(categorySummary);
        categoryService.updateCategory(categoryVacancies);

        //3 вкладка   Категория  Резюме и Вакансии создание

        Category itRes = new Category("IT, Internet, Telecom", null, "en" ,postings);
        Category itVac = new Category("IT, Internet, Telecom", null, "en" ,postings);
        Category carBusinessRes = new Category("Automobile business", null, "en" ,postings);
        Category carBusinessVac = new Category("Automobile business", null, "en" ,postings);
        Category administrativeWorkRes = new Category("Administrative work", null, "en" ,postings);
        Category administrativeWorkVac = new Category("Administrative work", null, "en" ,postings);
        Category bankRes = new Category("Banks, investments", null, "en" ,postings);
        Category bankVac = new Category("Banks, investments", null, "en" ,postings);
        Category withoutRes = new Category("Without experience, students", null, "en" ,postings);
        Category withoutVac = new Category("Without experience, students", null, "en" ,postings);
        Category accRes = new Category("Accounting, Finance", null, "en" ,postings);
        Category accVac = new Category("Accounting, Finance", null, "en" ,postings);
        Category hiManagRes = new Category("Top management", null, "en" ,postings);
        Category hiManagVac = new Category("Top management", null, "en" ,postings);
        Category nkoRes = new Category("Civil service, non-Profit organization", null, "en" ,postings);
        Category nkoVac = new Category("Civil service, non-Profit organization", null, "en" ,postings);
        Category homeStaffRes = new Category("Domestic staff", null, "en" ,postings);
        Category homeStaffVac = new Category("Domestic staff", null, "en" ,postings);
        Category ghkRes = new Category("Housing and utilities, operation", null, "en" ,postings);
        Category ghkVac = new Category("Housing and utilities, operation", null, "en" ,postings);
        Category artRes = new Category("Art, entertainment", null, "en" ,postings);
        Category artVac = new Category("Art, entertainment", null, "en" ,postings);
        Category consRes = new Category("Consultancy", null, "en" ,postings);
        Category consVac = new Category("Consultancy", null, "en" ,postings);
        Category prRes = new Category("Marketing, advertising, PR", null, "en" ,postings);
        Category prVac = new Category("Marketing, advertising, PR", null, "en" ,postings);
        Category medRes = new Category("Medicine, pharmaceuticals", null, "en" ,postings);
        Category medVac = new Category("Medicine, pharmaceuticals", null, "en" ,postings);
        Category scienceRes = new Category("Education, science", null, "en" ,postings);
        Category scienceVac = new Category("Education, science", null, "en" ,postings);
        Category safetyRes = new Category("Security and safety", null, "en" ,postings);
        Category safetyVac = new Category("Security and safety", null, "en" ,postings);
        Category salesRes = new Category("Sales", null, "en" ,postings);
        Category salesVac = new Category("Sales", null, "en" ,postings);
        Category agriculturalRes = new Category("Production, raw materials, agricultural", null, "en" ,postings);
        Category agriculturalVac = new Category("Production, raw materials, agricultural", null, "en" ,postings);
        Category insuranceRes = new Category("Insurance", null, "en" ,postings);
        Category insuranceVac = new Category("Insurance", null, "en" ,postings);
        Category buildRes = new Category("Construction", null, "en" ,postings);
        Category buildVac = new Category("Construction", null, "en" ,postings);
        Category transpRes = new Category("Transport, logistics", null, "en" ,postings);
        Category transpVac = new Category("Transport, logistics", null, "en" ,postings);
        Category tourismRes = new Category("Tourism, restaurants", null, "en" ,postings);
        Category tourismVac = new Category("Tourism, restaurants", null, "en" ,postings);
        Category fitnessRes = new Category("Fitness, beauty salons", null, "en" ,postings);
        Category fitnessVac = new Category("Fitness, beauty salons", null, "en" ,postings);
        Category lawRes = new Category("Jurisprudence", null, "en" ,postings);
        Category lawVac = new Category("Jurisprudence", null, "en" ,postings);

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

        Category categoryApartments = new Category("Apartments", null, "en" ,postings);
        Category categoryRooms = new Category("Rooms", null, "en" ,postings);
        Category categoryHousesSummerResidencesCottages = new Category("Houses, villas, cottages", null, "en" ,postings);
        Category categoryLand = new Category("Land plot", null, "en" ,postings);
        Category categoryGaragesAndParkingSpaces = new Category("Garages and Parking spaces", null, "en" ,postings);
        Category categoryCommercialProperty = new Category("Commercial real estate", null, "en" ,postings);
        Category nullAbroad = new Category("International property", null, "en" ,postings);

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

        Category sellApart = new Category("Sell", null, "en" ,postings);
        Category sellRooms = new Category("Sell", null, "en" ,postings);
        Category sellHouses = new Category("Sell", null, "en" ,postings);
        Category sellLand = new Category("Sell", null, "en" ,postings);
        Category sellGarage = new Category("Sell", null, "en" ,postings);
        Category sellCommerc = new Category("Sell", null, "en" ,postings);
        Category sellAbroad = new Category("Sell", null, "en" ,postings);

        Category rentLand = new Category("Pass", null, "en" ,postings);
        Category rentApart = new Category("Pass", null, "en" ,postings);
        Category rentRooms = new Category("Pass", null, "en" ,postings);
        Category rentHouses = new Category("Pass", null, "en" ,postings);
        Category rentGarage = new Category("Pass", null, "en" ,postings);
        Category rentAbroad = new Category("Pass", null, "en" ,postings);
        Category rentCommerc = new Category("Pass", null, "en" ,postings);

        Category buyLand = new Category("Buy", null, "en" ,postings);
        Category buyApart = new Category("Buy", null, "en" ,postings);
        Category buyRooms = new Category("Buy", null, "en" ,postings);
        Category buyHouses = new Category("Buy", null, "en" ,postings);
        Category buyGarage = new Category("Buy", null, "en" ,postings);
        Category buyAbroad = new Category("Buy", null, "en" ,postings);
        Category buyCommerc = new Category("Buy", null, "en" ,postings);

        Category snimLand = new Category("Rent", null, "en" ,postings);
        Category snimOutApart = new Category("Rent", null, "en" ,postings);
        Category snimOutRooms = new Category("Rent", null, "en" ,postings);
        Category snimHouses = new Category("Rent", null, "en" ,postings);
        Category snimGarage = new Category("Rent", null, "en" ,postings);
        Category snimAbroad = new Category("Rent", null, "en" ,postings);
        Category snimCommerc = new Category("Rent", null, "en" ,postings);

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
        categoryService.addCategory(snimOutApart);
        categoryService.addCategory(snimOutRooms);
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

        rentLand.setParentCategory(categoryApartments);
        rentApart.setParentCategory(categoryRooms);
        rentRooms.setParentCategory(categoryHousesSummerResidencesCottages);
        rentHouses.setParentCategory(categoryLand);
        rentGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        rentAbroad.setParentCategory(categoryCommercialProperty);
        rentCommerc.setParentCategory(nullAbroad);
        categoryService.updateCategory(rentLand);
        categoryService.updateCategory(rentApart);
        categoryService.updateCategory(rentRooms);
        categoryService.updateCategory(rentHouses);
        categoryService.updateCategory(rentGarage);
        categoryService.updateCategory(rentAbroad);
        categoryService.updateCategory(rentCommerc);

        buyLand.setParentCategory(categoryApartments);
        buyApart.setParentCategory(categoryRooms);
        buyRooms.setParentCategory(categoryHousesSummerResidencesCottages);
        buyHouses.setParentCategory(categoryLand);
        buyGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        buyAbroad.setParentCategory(categoryCommercialProperty);
        buyCommerc.setParentCategory(nullAbroad);
        categoryService.updateCategory(buyLand);
        categoryService.updateCategory(buyApart);
        categoryService.updateCategory(buyRooms);
        categoryService.updateCategory(buyHouses);
        categoryService.updateCategory(buyGarage);
        categoryService.updateCategory(buyAbroad);
        categoryService.updateCategory(buyCommerc);

        snimLand.setParentCategory(categoryApartments);
        snimOutApart.setParentCategory(categoryRooms);
        snimOutRooms.setParentCategory(categoryHousesSummerResidencesCottages);
        snimHouses.setParentCategory(categoryLand);
        snimGarage.setParentCategory(categoryGaragesAndParkingSpaces);
        snimAbroad.setParentCategory(categoryCommercialProperty);
        snimCommerc.setParentCategory(nullAbroad);
        categoryService.updateCategory(snimLand);
        categoryService.updateCategory(snimOutApart);
        categoryService.updateCategory(snimOutRooms);
        categoryService.updateCategory(snimHouses);
        categoryService.updateCategory(snimGarage);
        categoryService.updateCategory(snimAbroad);
        categoryService.updateCategory(snimCommerc);
    }
}
