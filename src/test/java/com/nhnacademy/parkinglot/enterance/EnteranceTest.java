package com.nhnacademy.parkinglot.enterance;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.car.Car;
import com.nhnacademy.exceptions.NotMatchCarNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnteranceTest {
    Car car;

    @BeforeEach
    void setUp() {
        car = mock(Car.class);
    }

    @Test
    @DisplayName("차량을 스캔한다.")
    void scan() {
        String carNumber = "12A 3456";

        when(car.getCarNumber()).thenReturn(carNumber);
        Enterance.scan(car);

        verify(car).getCarNumber();
    }

    @Test
    @DisplayName("차량 번호판의 규격에 맞지않으면 예외를 던진다.")
    void scan_car_number_not_exact_then_throw_error() {
        String errCase1 = "1A 1234"; // 앞자리가 2자리 미만일때
        String errCase2 = "1234A 1234"; // 앞자리가 3자리 초과일때
        String errCase3 = "12A 123"; // 뒷자리가 4자리 미만일때
        String errCase4 = "12A 12345"; // 뒷자리가 4자리 초과일때
        String errCase5 = "12a 1234"; // 중간 문자가 영 소문자일때
        String errCase6 = "12가 1234"; // 중간 문자가 영 대문자 이외일때
        String errCase7 = "12가1234"; // 앞 뒤자리 사이 공백이 없을 때

        when(car.getCarNumber()).thenReturn(errCase1);
        throwErrorCarNumber();

        when(car.getCarNumber()).thenReturn(errCase2);
        throwErrorCarNumber();

        when(car.getCarNumber()).thenReturn(errCase3);
        throwErrorCarNumber();

        when(car.getCarNumber()).thenReturn(errCase4);
        throwErrorCarNumber();

        when(car.getCarNumber()).thenReturn(errCase4);
        throwErrorCarNumber();

        when(car.getCarNumber()).thenReturn(errCase5);
        throwErrorCarNumber();

        when(car.getCarNumber()).thenReturn(errCase6);
        throwErrorCarNumber();

        when(car.getCarNumber()).thenReturn(errCase7);
        throwErrorCarNumber();
    }

    private void throwErrorCarNumber() {
        assertThatThrownBy(() -> Enterance.scan(car))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호판", "형식", "올바르지않습니다.");
    }
}
