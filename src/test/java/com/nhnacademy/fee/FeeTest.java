package com.nhnacademy.fee;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.time.Time;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeeTest {
    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("입 출차시간을 계산한다. 계산 - 30분 무료")
    void calculate_from_30min() {
        Fee fee = new Fee();
        LocalDateTime now = LocalDateTime.now();
        Time enterTime = new Time(now);
        Time outTIme = new Time(now.plusMinutes(30));

        assertThat(fee.calculate(enterTime, outTIme)).isZero();
    }

    @Test
    @DisplayName("입 출차시간을 계산한다. 계산 - 30 ~ 1시간까지 1천원")
    void calculate_from_1hour() {
        Fee fee = new Fee();
        LocalDateTime now = LocalDateTime.now();
        Time enterTime = new Time(now);
        Time outTIme = new Time(now.plusMinutes(60));

        assertThat(fee.calculate(enterTime, outTIme)).isEqualTo(1000);
    }

    @Test
    @DisplayName("입 출차시간을 계산한다. 계산 - 1시간 10분 -> 1500원")
    void calculate_from_1hour_10min() {
        Fee fee = new Fee();
        LocalDateTime now = LocalDateTime.now();
        Time enterTime = new Time(now);
        Time outTIme = new Time(now.plusMinutes(70));

        assertThat(fee.calculate(enterTime, outTIme)).isEqualTo(1500);
    }

    @Test
    @DisplayName("입 출차시간을 계산한다. 계산 - 1시간 1초 -> 1500원")
    void calculate_from_1hour_1sec() {
        Fee fee = new Fee();
        LocalDateTime now = LocalDateTime.now();
        Time enterTime = new Time(now);
        Time outTIme = new Time(now.plusMinutes(60).plusSeconds(1));

        assertThat(fee.calculate(enterTime, outTIme)).isEqualTo(1500);
    }

    @Test
    @DisplayName("입 출차시간을 계산한다. 계산 - 6시간 -> 15000원")
    void calculate_from_6hour() {
        Fee fee = new Fee();
        LocalDateTime now = LocalDateTime.now();
        Time enterTime = new Time(now);
        Time outTIme = new Time(now.plusHours(6));

        assertThat(fee.calculate(enterTime, outTIme)).isEqualTo(15000);
    }

    @Test
    @DisplayName("입 출차시간을 계산한다. 계산 - 1일 6시간 -> 30000원")
    void calculate_from_1day_6hour() {
        Fee fee = new Fee();
        LocalDateTime now = LocalDateTime.now();
        Time enterTime = new Time(now);
        Time outTIme = new Time(now.plusDays(1).plusHours(6));

        assertThat(fee.calculate(enterTime, outTIme)).isEqualTo(30000);
    }

    @Test
    @DisplayName("입 출차시간을 계산한다. 계산 - 2일 -> 30000원")
    void calculate_from_2day() {
        Fee fee = new Fee();
        LocalDateTime now = LocalDateTime.now();
        Time enterTime = new Time(now);
        Time outTIme = new Time(now.plusDays(2));

        assertThat(fee.calculate(enterTime, outTIme)).isEqualTo(30000);
    }

}
