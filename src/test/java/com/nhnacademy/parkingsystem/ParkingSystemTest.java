package com.nhnacademy.parkingsystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.car.Car;
import com.nhnacademy.car.cartype.CarType;
import com.nhnacademy.exceptions.LackMoneyException;
import com.nhnacademy.exceptions.LargeCarDoseNotParkException;
import com.nhnacademy.exceptions.NotMatchCarNumberException;
import com.nhnacademy.exceptions.ParkingSpaceOverflowException;
import com.nhnacademy.parkinglot.ParkingLot;
import com.nhnacademy.parkinglot.parkingspace.ParkingSpace;
import com.nhnacademy.parkinglot.parkingsystem.ParkingSystem;
import com.nhnacademy.paycoserver.PaycoServer;
import com.nhnacademy.user.User;
import java.time.LocalDateTime;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkingSystemTest {
    ParkingSystem parkingSystem; // SUT
    ParkingLot parkingLot; // DOC
    PaycoServer paycoServer; // DOC

    @BeforeEach
    void setUp() {
        parkingLot = mock(ParkingLot.class);
        paycoServer = mock(PaycoServer.class);
        parkingSystem = new ParkingSystem(parkingLot, paycoServer);
    }

    @Test
    @DisplayName("차량이 들어오면 차량번호를 스캔한다.")
    void scan_car_number() {
        String carNumber = "12A 1234";
        String lotCode = "A-1";
        Car car = new Car(carNumber, CarType.NORMAL);
        long amount = 30_000;
        User user = User.normalUser(amount, car, LocalDateTime.of(2022, 4, 9, 17, 45));
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        parkingSystem.enterParkingLot(user);

        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("스캔한 차량번호가 올바른 형식인가?")
    @Description("차량의 번호 형식은 다음과 같다. 123A 1234 또는 12A 1234 두가지 형식에 맞지않으면 예외를 던진다.")
    void scanned_car_number_not_suitable_then_throw_exception() {
        LocalDateTime dateTime = LocalDateTime.now();
        String errCase1 = "1A 1234"; // 앞자리가 2자리 미만일때
        String errCase2 = "1234A 1234"; // 앞자리가 3자리 초과일때
        String errCase3 = "12A 123"; // 뒷자리가 4자리 미만일때
        String errCase4 = "12A 12345"; // 뒷자리가 4자리 초과일때
        String errCase5 = "12a 1234"; // 중간 문자가 영 소문자일때
        String errCase6 = "12가 1234"; // 중간 문자가 영 대문자 이외일때
        String errCase7 = "12가1234"; // 앞 뒤자리 사이 공백이 없을 때
        String suitableCase1 = "12A 1234"; // 올바른 케이스 1
        String suitableCase2 = "123A 1234"; // 올바른 케이스 2
        String lotCode = "A-1";
        String lotCode2 = "A-2";

        Car errCar1 = new Car(errCase1, CarType.NORMAL);
        User errUser1 = User.normalUser(30000, errCar1, dateTime);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errUser1))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar2 = new Car(errCase2, CarType.NORMAL);
        User errUser2 = User.normalUser(30000, errCar2, dateTime);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errUser2))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar3 = new Car(errCase3, CarType.NORMAL);
        User errUser3 = User.normalUser(30000, errCar3, dateTime);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errUser3))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar4 = new Car(errCase4, CarType.NORMAL);
        User errUser4 = User.normalUser(30000, errCar4, dateTime);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errUser4))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar5 = new Car(errCase5, CarType.NORMAL);
        User errUser5 = User.normalUser(30000, errCar5, dateTime);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errUser5))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar6 = new Car(errCase6, CarType.NORMAL);
        User errUser6 = User.normalUser(30000, errCar6, dateTime);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errUser6))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");

        Car errCar7 = new Car(errCase7, CarType.NORMAL);
        User errUser7 = User.normalUser(30000, errCar7, dateTime);
        assertThatThrownBy(() -> parkingSystem.enterParkingLot(errUser7))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");


        Car suitableCar1 = new Car(suitableCase1, CarType.NORMAL);
        User user1 = User.normalUser(30000, suitableCar1, dateTime);
        ParkingSpace space1 = new ParkingSpace(suitableCar1, lotCode);
        when(parkingLot.enter(suitableCar1, lotCode)).thenReturn(space1);
        parkingSystem.enterParkingLot(user1);

        Car suitableCar2 = new Car(suitableCase2, CarType.NORMAL);
        User user2 = User.normalUser(30000, suitableCar2, dateTime);
        ParkingSpace space2 = new ParkingSpace(suitableCar2, lotCode);
        when(parkingLot.enter(suitableCar2, lotCode2)).thenReturn(space2);
        parkingSystem.enterParkingLot(user2);

        verify(parkingLot, times(2)).enter(any(), any());
    }

    @Test
    @DisplayName("특정구역에 주차한다. A-1 2번째 A-2 10번째 B-1")
    void park_car() {
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < 11; i++) {
            String carNumber = "12A " + (1230 + i);
            Car car = new Car(carNumber, CarType.NORMAL);
            User user = User.normalUser(30000, car, now);
            String lotCode = "A-" + (i + 1);
            ParkingSpace space = new ParkingSpace(car, lotCode);

            if (i == 10) {
                space = new ParkingSpace(car, "B-1");
                when(parkingLot.enter(car, "B-1")).thenReturn(space);
            } else {
                when(parkingLot.enter(car, lotCode)).thenReturn(space);
            }
            when(parkingLot.getParkedSpaceCount()).thenReturn(i);

            if (i == 0) {
                assertThat(parkingSystem.enterParkingLot(user).getLotCode()).isEqualTo("A-1");
            }
            if (i == 1) {
                assertThat(
                    parkingSystem.enterParkingLot(user).getLotCode()).isEqualTo("A-2");
            }
            if (i == 10) {
                assertThat(
                    parkingSystem.enterParkingLot(user).getLotCode()).isEqualTo("B-1");
            }
        }
    }

    @Test
    @DisplayName("최대 주차공간 Z-10까지 주차 후, 다음 주차시 예외를 던짐")
    void if_over_maximum_lot_throw_ParkingSpaceOverflowException() {
        for (int i = 0; i < 261; i++) {
            LocalDateTime now = LocalDateTime.now();
            String carNumber = "12A " + (1230 + i);
            Car car = new Car(carNumber, CarType.NORMAL);
            String lotCode = "A-" + (i + 1);
            ParkingSpace space = new ParkingSpace(car, lotCode);

            User user = User.normalUser(30000, car, now);

            when(parkingLot.enter(car, lotCode)).thenReturn(space);
            when(parkingLot.getParkedSpaceCount()).thenReturn(i);

            if (i == 260) {
                assertThatThrownBy(() -> parkingSystem.enterParkingLot(user))
                    .isInstanceOf(ParkingSpaceOverflowException.class)
                    .hasMessageContaining("주차 공간", "없습니다.");
            }
        }
    }

    @Test
    @DisplayName("주차장에서 차가 나간다. 차가 나갈려면 주차 시간만큼 결제를 해야한다. - 30분이하")
    void exit_user_car_() {
        String carNumber = "12A 1234";
        long amount = 30_000L;
        LocalDateTime outTime = LocalDateTime.now().plusMinutes(30); // 30분
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isEqualTo(30_000L);
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("주차장에서 차가 나간다. 차가 나갈려면 주차 시간만큼 결제를 해야한다. - 30분 1초 (초과요금 적용)")
    void exit_user_car_over_time_price_case1() {
        String carNumber = "12A 1234";
        long amount = 30_000L;
        LocalDateTime outTime = LocalDateTime.now().plusMinutes(30).plusSeconds(1); // 30분 1초
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isEqualTo(29_000L);
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("주차장에서 차가 나간다. 차가 나갈려면 주차 시간만큼 결제를 해야한다. - 50분 (초과요금 적용)")
    void exit_user_car_over_time_price_case2() {
        String carNumber = "12A 1234";
        long amount = 30_000L;
        LocalDateTime outTime = LocalDateTime.now().plusMinutes(50); //50분
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isEqualTo(29_000L);
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("주차장에서 차가 나간다. 차가 나갈려면 주차 시간만큼 결제를 해야한다. - 61분 (초과요금 적용)")
    void exit_user_car_over_time_price_case3() {
        String carNumber = "12A 1234";
        long amount = 30_000L;
        LocalDateTime outTime = LocalDateTime.now().plusMinutes(61); // 61분
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isEqualTo(28_500L);
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("주차장에서 차가 나간다. 차가 나갈려면 주차 시간만큼 결제를 해야한다. - 6시간 (초과요금 적용)")
    void exit_user_car_over_time_price_case4() {
        String carNumber = "12A 1234";
        long amount = 30_000L;
        LocalDateTime outTime = LocalDateTime.now().plusHours(6); // 6시간
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isEqualTo(15_000L);
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("주차장에서 차가 나간다. 차가 나갈려면 주차 시간만큼 결제를 해야한다. - 1일 (초과요금 적용)")
    void exit_user_car_over_time_price_case5() {
        String carNumber = "12A 1234";
        long amount = 30_000L;
        LocalDateTime outTime = LocalDateTime.now().plusDays(1); // 하루
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isEqualTo(15_000L);
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("주차장에서 차가 나간다. 차가 나갈려면 주차 시간만큼 결제를 해야한다. - 1일 1시간(초과요금 적용)")
    void exit_user_car_over_time_price_case6() {
        String carNumber = "12A 1234";
        long amount = 30_000L;
        LocalDateTime outTime = LocalDateTime.now().plusDays(1).plusHours(1); // 하루 + 1시간
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isEqualTo(14_000L);
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("주차장에서 차가 나간다. 차가 나갈려면 주차 시간만큼 결제를 해야한다. - 2일 (초과요금 적용)")
    void exit_user_car_over_time_price_case7() {
        String carNumber = "12A 1234";
        long amount = 30_000L;
        LocalDateTime outTime = LocalDateTime.now().plusDays(2); // 이틀
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isZero();
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("주차장에서 차가 나간다. 요금이 부족하면 예외를 던진다.")
    void if_lack_money_then_throw_LackMoneyException() {
        String carNumber = "12A 1234";
        long amount = 1000L;
        LocalDateTime outTime = LocalDateTime.now().plusDays(2); // 하루
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);

        assertThatThrownBy(() -> parkingSystem.exitUserCar(user))
            .isInstanceOf(LackMoneyException.class)
            .hasMessageContaining("잔액", "부족");

        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("자동차가 소형일때는 50% 할인이 가능하다.")
    void small_car_is_discount_50_percent() {
        String carNumber = "12A 1234";
        long amount = 1000L;
        LocalDateTime outTime = LocalDateTime.now().plusHours(1); // 하루
        Car car = new Car(carNumber, CarType.SMALL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isEqualTo(500);
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("자동차가 일반 차량일때는 할인이 없다.")
    void normal_car_is_no_discount() {
        String carNumber = "12A 1234";
        long amount = 1000L;
        LocalDateTime outTime = LocalDateTime.now().plusHours(1); // 하루
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.normalUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isZero();
        verify(parkingLot).enter(car, lotCode);
    }

    @Test
    @DisplayName("자동차가 대형일때는 주차가 불가능하다. 입차시 예외를 던진다.")
    void large_car_dose_not_park() {
        String carNumber = "12A 1234";
        long amount = 1000L;
        LocalDateTime outTime = LocalDateTime.now().plusDays(2); // 하루
        Car car = new Car(carNumber, CarType.LARGE);
        User user = User.normalUser(amount, car, outTime);

        assertThatThrownBy(() -> parkingSystem.enterParkingLot(user))
            .isInstanceOf(LargeCarDoseNotParkException.class)
            .hasMessageContaining("대형차", "주차");
    }

    @Test
    @DisplayName("페이코 회원은 10퍼센트 할인혜택을 받는다.")
    void payco_user_10_percent_discount() {
        String carNumber = "12A 1234";
        long amount = 1000L;
        LocalDateTime outTime = LocalDateTime.now().plusHours(1); // 하루
        Car car = new Car(carNumber, CarType.NORMAL);
        User user = User.paycoUser(amount, car, outTime);
        String lotCode = "A-1";
        ParkingSpace space = new ParkingSpace(car, lotCode);

        when(parkingLot.enter(car, lotCode)).thenReturn(space);
        when(parkingLot.findByCarNumber(carNumber)).thenReturn(space);
        when(paycoServer.eventDiscount(amount)).thenReturn(900L);

        parkingSystem.enterParkingLot(user);
        parkingSystem.exitUserCar(user);

        assertThat(user.getAmount()).isEqualTo(100);
        verify(parkingLot).enter(car, lotCode);
    }
}
