package com.nhnacademy.ParkingZoneServiceTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.car.Car;
import com.nhnacademy.parkingzone.ParkingZoneRepository;
import com.nhnacademy.parkingzone.ParkingZoneService;
import com.nhnacademy.parkingzone.parkingspace.ParkingSpace;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class ParkingZoneServiceTest {
    ParkingZoneRepository repository;
    ParkingZoneService service;

    @BeforeEach
    void setUp() {
        repository = new ParkingZoneRepository();
        service = new ParkingZoneService(repository);
    }

    @Test
    @DisplayName("주차장에 차가 들어온다.(번호판을 인식함 - 인식은 스캐너가 담당.)")
    @Description("주차시 주차 구역번호가 지정됨")
    public void scan_car_number() {
        String expectedValue = "12A2612";
        InputStream in = new ByteArrayInputStream(expectedValue.getBytes());
        System.setIn(in);

        ParkingSpace parkedSpace = ParkingSpace.parking(new Car());
        service.enterCar(parkedSpace);

        assertThat(service.parkedCarCount()).isEqualTo(1);
    }
}
