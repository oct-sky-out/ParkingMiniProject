package com.nhnacademy.parkingsystem;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.car.Car;
import com.nhnacademy.exceptions.NotMatchCarNumberException;
import com.nhnacademy.parkinglot.ParkingLot;
import com.nhnacademy.parkinglot.parkingsystem.ParkingSystem;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkingSystemTest {
    ParkingSystem parkingSystem; // SUT
    ParkingLot parkingLot; // DOC

    @BeforeEach
    void setUp() {
        parkingLot = mock(ParkingLot.class);
        parkingSystem = new ParkingSystem(parkingLot);
    }

    @Test
    @DisplayName("차량이 들어오면 차량번호를 스캔한다.")
    void scan_car_number() {
        String carNumber = "12A 1234";
        Car car = new Car(carNumber);

        when(parkingLot.enter(car)).thenReturn(1);
        parkingSystem.enterParkingLot(car);


        verify(parkingLot).enter(car);
    }

    @Test
    @DisplayName("스캔한 차량번호가 올바른 형식인가?")
    @Description("차량의 번호 형식은 다음과 같다. 123A 1234 또는 12A 1234 두가지 형식에 맞지않으면 예외를 던진다.")
    void scanned_car_number_not_suitable_then_throw_exception() {
        String errCase1 = "1A 1234"; // 앞자리가 2자리 미만일때
        String errCase2 = "1234A 1234"; // 앞자리가 3자리 초과일때
        String errCase3 = "12A 123"; // 뒷자리가 4자리 미만일때
        String errCase4 = "12A 12345"; // 뒷자리가 4자리 초과일때
        String errCase5 = "12a 1234"; // 중간 문자가 영 소문자일때
        String errCase6 = "12가 1234"; // 중간 문자가 영 대문자 이외일때
        String errCase7 = "12가1234"; // 앞 뒤자리 사이 공백이 없을 때
        String suitableCase1 = "12A 1234"; // 올바른 케이스 1
        String suitableCase2 = "123A 1234"; // 올바른 케이스 2

        Car errCar1 = new Car(errCase1);
        when(parkingLot.enter(errCar1)).thenReturn(0);

        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errCar1))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar2 = new Car(errCase2);
        when(parkingLot.enter(errCar2)).thenReturn(0);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errCar2))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar3 = new Car(errCase3);
        when(parkingLot.enter(errCar3)).thenReturn(0);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errCar3))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar4 = new Car(errCase4);
        when(parkingLot.enter(errCar4)).thenReturn(0);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errCar1))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar5 = new Car(errCase5);
        when(parkingLot.enter(errCar5)).thenReturn(0);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errCar5))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar6 = new Car(errCase6);
        when(parkingLot.enter(errCar6)).thenReturn(0);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errCar6))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar7 = new Car(errCase7);
        when(parkingLot.enter(errCar7)).thenReturn(0);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errCar7))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");


        Car suitableCar1 = new Car(suitableCase1);
        when(parkingLot.enter(suitableCar1)).thenReturn(1);
        parkingSystem.enterParkingLot(suitableCar1);

        Car suitableCar2 = new Car(suitableCase2);
        when(parkingLot.enter(suitableCar2)).thenReturn(2);
        parkingSystem.enterParkingLot(suitableCar2);

        verify(parkingLot, times(2)).enter(any());
    }

    @Test
    @DisplayName("특정구역에 주차한다. A-1 2번째 A-2 10번째 B-1")
    void park_car() {
        fail("다음 구현 대기중");
//        for (int i = 0; i < 10; i++) {
//            parkingSystem.enterParkingLot(new Car("12A" + (1230 + i)));
//        }
//        assertThat(parkingSystem.findParkingLotByCarNumber("12A 1230").lotCode).isEqualTo("A-1");
//        assertThat(parkingSystem.findParkingLotByCarNumber("12A 1231").lotCode).isEqualTo("A-2");
//        assertThat(parkingSystem.findParkingLotByCarNumber("12A 1239").lotCode).isEqualTo("B-1");
    }

}
