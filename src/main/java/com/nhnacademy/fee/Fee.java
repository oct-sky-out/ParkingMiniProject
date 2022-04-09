package com.nhnacademy.fee;

import com.nhnacademy.Time;
import java.time.LocalDateTime;

public class Fee implements Calculatable {
    private static final long THIRTY_MINUTE_MILLISECONDS = 1_800_000L; // 30분 초단위 변환값
    private static final long ONE_HOUR_MILLISECONDS = THIRTY_MINUTE_MILLISECONDS * 2; // 1시간
    private static final long TEN_MINUTE_MILLI_SECONDS = THIRTY_MINUTE_MILLISECONDS / 3; // 10분
    private static final long DAY_OF_MILLI_SECONDS = ONE_HOUR_MILLISECONDS * 24; // 하루
    private static final long MAX_PARK_MILLI_SECOND_PER_DAY = 16_800_000;
    // 4시간 40 (일 최대요금 주차시간 5시간 40분)
    private static final long OVERTIME_BASE_FEE = 1_000; // 기본요금
    private static final long OVER_TIME_FEE = 500; // 초과 요금 - 10분당
    private static final long MAX_FEE_PER_DAY = 15_000L; // 24시간 당 최대 요금

    @Override
    public long calculate(Time enterTime, Time outTime) {
        long usingParkingLotMilliSeconds = outTime.getMilliSeconds() - enterTime.getMilliSeconds();

        if (usingParkingLotMilliSeconds >= DAY_OF_MILLI_SECONDS) {
            return this.overDayCalculate(enterTime, outTime);
        }

        if (usingParkingLotMilliSeconds <= THIRTY_MINUTE_MILLISECONDS) {
            return 0;
        }

        if (usingParkingLotMilliSeconds <= ONE_HOUR_MILLISECONDS) {
            return OVERTIME_BASE_FEE;
        }

        long overSeconds = usingParkingLotMilliSeconds - ONE_HOUR_MILLISECONDS;
        if (overSeconds >= MAX_PARK_MILLI_SECOND_PER_DAY) {
            return MAX_FEE_PER_DAY;
        }

        double overMinute = overSeconds / TEN_MINUTE_MILLI_SECONDS; // 이용 초과 분
        return OVERTIME_BASE_FEE + ((long) overMinute + 1) * OVER_TIME_FEE;
    }

    private long overDayCalculate(Time enterTime, Time outTime) {
        long overDay =
            (outTime.getMilliSeconds() - enterTime.getMilliSeconds()) / DAY_OF_MILLI_SECONDS;
        LocalDateTime subOutDateTime = outTime.getDateTime().minusDays(overDay);
        Time subOutTime = new Time(subOutDateTime);

        return (overDay * MAX_FEE_PER_DAY) + this.calculate(enterTime, subOutTime);
    }

    private int getDayOfNumber(Time dateTime) {
        return dateTime.getDateTime().getDayOfMonth();
    }
}
