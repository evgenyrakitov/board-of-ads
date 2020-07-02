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

        List<Category> categories = categoryService.getRootCategories().stream().sorted((Comparator.comparing(Category::getName))).collect(Collectors.toList());
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

    private void initCategories() {

        if (categoryService.getAllCategories().size() > 0) {
            return;
        }

        //1 вкладка Категории создание

        Category categoryTransport = new Category("Транспорт", null);
        Category categoryProperty = new Category("Недвижимость", null);
        Category categoryJob = new Category("Работа", null);
        Category categoryServices = new Category("Услуги", null);
        Category categoryForHomeAndGarden = new Category("Для дома и дачи", null);
        Category categoryPersonalItems = new Category("Личные вещи", null);
        Category categoryConsumerElectronics = new Category("Бытовая электроника", null);
        Category categoryHobbiesAndLeisure = new Category("Хобби и отдых", null);
        Category cateroryAnimals = new Category("Животные", null);
        Category categoryBusiness = new Category("Для бизнеса", null);

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

        Category categoryCars = new Category("Автомобили", null);
        Category categoryMotorcycles = new Category("Мотоциклы и мототехника", null);
        Category categoryTrucksAndSpecialEquipment = new Category("Грузовики и спецтехника", null);
        Category categoryWaterTransport = new Category("Водный транспорт", null);
        Category categoryPartsAndAccessories = new Category("Запчасти и аксессуары", null);

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

        Category oldCars = new Category("С пробегом", null);
        Category newCars = new Category("Новый", null);

        //3 вкладка Категория автомобили добавление в БД

        categoryService.addCategory(oldCars);
        categoryService.addCategory(newCars);


        //3 вкладка Категория автомобили назначение родителей

        oldCars.setParentCategory(categoryCars);
        newCars.setParentCategory(categoryCars);
        categoryService.updateCategory(oldCars);
        categoryService.updateCategory(newCars);

        //3 вкладка Категория водный транспорт создание

        Category rowboats = new Category("Вёсельные лодки", null);
        Category jetSkis = new Category("Гидроциклы", null);
        Category boatsAndYachts = new Category("Катера и яхты", null);
        Category kayaksAndCanoes = new Category("Каяки и каноэ", null);
        Category motorBoats = new Category("Моторные лодки", null);
        Category inflatableBoat = new Category("Надувные лодки", null);

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

        Category buses = new Category("Автобусы", null);
        Category motorhomes = new Category("Автодома", null);
        Category truckCranes = new Category("Автокраны", null);
        Category bulldozers = new Category("Бульдозеры", null);
        Category trucks = new Category("Грузовики", null);
        Category utilityEquipment = new Category("Коммунальная техника", null);
        Category easyTransport = new Category("Лёгкий транспорт", null);
        Category loaders = new Category("Погрузчики", null);
        Category trailers = new Category("Прицепы", null);
        Category agriculturalMachinery = new Category("Сельхозтехника", null);
        Category constructionMachinery = new Category("Строительная техника", null);
        Category loggingEquipment = new Category("Техника для лесозаготовки", null);
        Category tractorUnits = new Category("Тягачи", null);
        Category excavators = new Category("Экскаваторы", null);

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

        Category buggy = new Category("Багги", null);
        Category allTerrainVehicles = new Category("Вездеходы", null);
        Category karting = new Category("Картинг", null);
        Category atv = new Category("Квадроциклы", null);
        Category mopedsAndScooters = new Category("Мопеды и скутеры", null);
        Category motorcycles = new Category("Мотоциклы", null);
        Category snowmobiles = new Category("Снегоходы", null);

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

        Category categorySummary = new Category("Резюме", null);
        Category categoryVacancies = new Category("Вакансии", null);

        //2 вкладка   Категория работа добавление в БД

        categoryService.addCategory(categorySummary);
        categoryService.addCategory(categoryVacancies);

        //2 вкладка   Категория работа назначение родителей

        categorySummary.setParentCategory(categoryJob);
        categoryVacancies.setParentCategory(categoryJob);
        categoryService.updateCategory(categorySummary);
        categoryService.updateCategory(categoryVacancies);

        //3 вкладка   Категория  Резюме и Вакансии создание

        Category itRes = new Category("IT, интернет, телеком", null);
        Category itVac = new Category("IT, интернет, телеком", null);
        Category carBusinessRes = new Category("Автомобильный бизнес", null);
        Category carBusinessVac = new Category("Автомобильный бизнес", null);
        Category administrativeWorkRes = new Category("Административная работа", null);
        Category administrativeWorkVac = new Category("Административная работа", null);
        Category bankRes = new Category("Банки, инвестиции", null);
        Category bankVac = new Category("Банки, инвестиции", null);
        Category withoutRes = new Category("Без опыта, студенты", null);
        Category withoutVac = new Category("Без опыта, студенты", null);
        Category accRes = new Category("Бухгалтерия, финансы", null);
        Category accVac = new Category("Бухгалтерия, финансы", null);
        Category hiManagRes = new Category("Высший менеджмент", null);
        Category hiManagVac = new Category("Высший менеджмент", null);
        Category nkoRes = new Category("Госслужба, НКО", null);
        Category nkoVac = new Category("Госслужба, НКО", null);
        Category homeStaffRes = new Category("Домашний персонал", null);
        Category homeStaffVac = new Category("Домашний персонал", null);
        Category ghkRes = new Category("ЖКХ, эксплуатация", null);
        Category ghkVac = new Category("ЖКХ, эксплуатация", null);
        Category artRes = new Category("Искусство, развлечения", null);
        Category artVac = new Category("Искусство, развлечения", null);
        Category consRes = new Category("Консультирование", null);
        Category consVac = new Category("Консультирование", null);
        Category prRes = new Category("Маркетинг, реклама, PR", null);
        Category prVac = new Category("Маркетинг, реклама, PR", null);
        Category medRes = new Category("Медицина, фармацевтика", null);
        Category medVac = new Category("Медицина, фармацевтика", null);
        Category scienceRes = new Category("Образование, наука", null);
        Category scienceVac = new Category("Образование, наука", null);
        Category safetyRes = new Category("Охрана, безопасность", null);
        Category safetyVac = new Category("Охрана, безопасность", null);
        Category salesRes = new Category("Продажи", null);
        Category salesVac = new Category("Продажи", null);
        Category agriculturalRes = new Category("Производство, сырьё, с/х", null);
        Category agriculturalVac = new Category("Производство, сырьё, с/х", null);
        Category insuranceRes = new Category("Страхование", null);
        Category insuranceVac = new Category("Страхование", null);
        Category buildRes = new Category("Строительство", null);
        Category buildVac = new Category("Строительство", null);
        Category transpRes = new Category("Транспорт, логистика", null);
        Category transpVac = new Category("Транспорт, логистика", null);
        Category tourismRes = new Category("Туризм, рестораны", null);
        Category tourismVac = new Category("Туризм, рестораны", null);
        Category fitnessRes = new Category("Фитнес, салоны красоты", null);
        Category fitnessVac = new Category("Фитнес, салоны красоты", null);
        Category lawRes = new Category("Юриспруденция", null);
        Category lawVac = new Category("Юриспруденция", null);

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

        Category categoryApartments = new Category("Квартиры", null);
        Category categoryRooms = new Category("Комнаты", null);
        Category categoryHousesSummerResidencesCottages = new Category("Дома, дачи, коттеджи", null);
        Category categoryLand = new Category("Земельные участки", null);
        Category categoryGaragesAndParkingSpaces = new Category("Гаражи и машиноместа", null);
        Category categoryCommercialProperty = new Category("Коммерческая недвижимость", null);
        Category nullAbroad = new Category("Недвижимость за рубежом", null);

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

        Category sellApart = new Category("Продам", null);
        Category sellRooms = new Category("Продам", null);
        Category sellHouses = new Category("Продам", null);
        Category sellLand = new Category("Продам", null);
        Category sellGarage = new Category("Продам", null);
        Category sellCommerc = new Category("Продам", null);
        Category sellAbroad = new Category("Продам", null);

        Category rentLand = new Category("Сдам", null);
        Category rentApart = new Category("Сдам", null);
        Category rentRooms = new Category("Сдам", null);
        Category rentHouses = new Category("Сдам", null);
        Category rentGarage = new Category("Сдам", null);
        Category rentAbroad = new Category("Сдам", null);
        Category rentCommerc = new Category("Сдам", null);

        Category buyLand = new Category("Куплю", null);
        Category buyApart = new Category("Куплю", null);
        Category buyRooms = new Category("Куплю", null);
        Category buyHouses = new Category("Куплю", null);
        Category buyGarage = new Category("Куплю", null);
        Category buyAbroad = new Category("Куплю", null);
        Category buyCommerc = new Category("Куплю", null);

        Category snimLand = new Category("Сниму", null);
        Category snimOutApart = new Category("Сниму", null);
        Category snimOutRooms = new Category("Сниму", null);
        Category snimHouses = new Category("Сниму", null);
        Category snimGarage = new Category("Сниму", null);
        Category snimAbroad = new Category("Сниму", null);
        Category snimCommerc = new Category("Сниму", null);

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
