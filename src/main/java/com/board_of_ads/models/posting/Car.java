package com.board_of_ads.models.posting;

import com.board_of_ads.models.posting.Posting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Car extends Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Внешний вид

    private int color;

    private String urlYoutube;

    //Регистрационные данные

    private String vin;

    //Технические характеристики

    private String brand;

    private String model;

    private int year;

    private String typeBody;

    private String rudder;

    //История


    private boolean thereIsAServiceBook;

    private boolean servedByADealer;

    private boolean onGuarantee;

    //Дополнительные опции

    private String powerRudder;

    private String climateControl;

    private boolean controlsOnTheRudder;

    private boolean athermalGlazing;

    private String salon;

    private boolean leatherSteering;

    private boolean hatch;

    //Обогрев

    private boolean frontSeatHeated;

    private boolean backSeatHeated;

    private boolean mirrorsHeated;

    private boolean backGlazingHeated;

    private boolean rudderHeated;


    private String electricWindowLifter; //Электростеклоподъемники

    //Электропривод

    private boolean frontSeatElectricDrive;

    private boolean backSeatElectricDrive;

    private boolean mirrorsElectricDrive;

    private boolean steeringColumnElectricDrive;

    //Память настроек

    private boolean fontSeatMemorySettings;

    private boolean backSeatMemorySettings;

    private boolean mirrorsMemorySettings;

    private boolean steeringColumnSettings;

    //Помощь при вождении

    private boolean automaticValet;

    private boolean rainSensor;

    private boolean lightSensor;

    private boolean rearParkingSensor;

    private boolean parktronicFront;

    private boolean blindSpotMonitoringSystem;

    private boolean rearViewCamera;

    private boolean cruiseControl;

    private boolean onBoardComputer;

    //Противоугонная система

    private boolean signaling;

    private boolean centralLocking;

    private boolean immobilizer;

    private boolean satellite;

    //Подушка безопасности

    private boolean frontalAirbags;

    private boolean kneeAirbags;

    private boolean blindsAirbags;

    private boolean sideFrontAirbags;

    private boolean sideRearAirbags;

    //Активная безопасность

    private boolean antiLockBrakes;

    private boolean transactionControl;

    private boolean exchangeRateStability;

    private boolean distributionBrakingEfforts;

    private boolean emergencyBraking;

    private boolean blockDifferential;

    private boolean pedestrianDetection;

    //Проигрыватели и навигация

    private boolean cdOrDvdOrBluRay;

    private boolean mp3;

    private boolean radio;

    private boolean tv;

    private boolean video;

    private boolean steeringWheelControl;

    private boolean usb;

    private boolean aux;

    private boolean bluetooth;

    private boolean gps;

    private String audioSystem;

    private boolean subWoofer;

    //Фары

    private String headlights;

    private boolean antiFog;

    private boolean headlightsWashers;

    private boolean adaptiveLighting;

    //Шины и диски

    private String tiresAndWheels;

    private boolean winterTiresIncluded;

}
