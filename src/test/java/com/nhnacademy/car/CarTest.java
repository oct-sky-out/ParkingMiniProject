package com.nhnacademy.car;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.exceptions.NotMatchCarNumberException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CarTest {
    @Test
    @DisplayName("자동차의 번호판의 올바른 형식이 들어오지않으면 \"NotMatchCarNumberException\" 예외를 던지는가?")
    @Description("자동차의 올바른 형식은 12A1234 혹은 123A3456만이 허용된다.")
    void not_exacted_car_number_then_throw_NotMatchCarNumberException() {
        String errorCase1 = "12A26"; // 뒷자리 1자리 부족
        String errorCase2 = "12A26122"; // 뒷자리 숫자 1자리 많음
        String errorCase3 = "1A2611"; // 앞자리 1자리 부족
        String errorCase4 = "1123A2122"; // 앞자리 1자리 많음
        String errorCase5 = "13a2122"; // 중간 문자가 무조건 대문자여야함.
        String errorCase6 = "13가2122"; // 중간 문자가는 영어 대문자 이외면 예외를 일으킴.

        InputStream in1 = new ByteArrayInputStream(errorCase1.getBytes());
        InputStream in2 = new ByteArrayInputStream(errorCase2.getBytes());
        InputStream in3 = new ByteArrayInputStream(errorCase3.getBytes());
        InputStream in4 = new ByteArrayInputStream(errorCase4.getBytes());
        InputStream in5 = new ByteArrayInputStream(errorCase5.getBytes());
        InputStream in6 = new ByteArrayInputStream(errorCase6.getBytes());

        System.setIn(in1);
        assertThatThrownBy(() -> new Car())
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호", "올바르지않은");

        System.setIn(in2);
        assertThatThrownBy(() -> new Car())
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호", "올바르지않은");

        System.setIn(in3);
        assertThatThrownBy(() -> new Car())
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호", "올바르지않은");

        System.setIn(in4);
        assertThatThrownBy(() -> new Car())
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호", "올바르지않은");

        System.setIn(in5);
        assertThatThrownBy(() -> new Car())
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호", "올바르지않은");

        System.setIn(in6);
        assertThatThrownBy(() -> new Car())
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("번호", "올바르지않은");
    }

}
