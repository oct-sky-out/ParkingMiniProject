package com.nhnacademy.time;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimeTest {
    @Test
    @DisplayName("Time 객체 생성 후 생성 시간의 Milli seconds를 알맞게 가져오는가?")
    void getMilliSeconds() {
        LocalDateTime now = LocalDateTime.now();
        Time time = new Time(now);
        long nowMilli = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        assertThat(time.getMilliSeconds()).isEqualTo(nowMilli);
    }
}
