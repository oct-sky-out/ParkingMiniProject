package com.nhnacademy.fee;

import com.nhnacademy.Time;

public class Fee implements Calculatable {
    private static final long THIRTY_MINUTE_MILLISECONDS = 1_800_000L; // 30분 초단위 변환값
    private static final long TEN_MINUTE_MILLI_SECONDS = 600_000L; // 10분 초단위 변환값
    private static final long DAY_OF_MILLI_SECONDS = 86_400_000L;
    private static final long MAX_PARK_MILLI_SECOND_PER_DAY = 11_400_000L;
    // 3시간 10분에 9000원 (일 최대요금 주차시간 3시간40분)
    private static final long BASIC_FEE = 1_000; // 기본요금
    private static final long OVER_TIME_FEE = 500; // 초과 요금 - 10분당
    private static final long MAX_FEE_PER_DAY = 10_000L; // 24시간 당 최대 요금

    @Override
    public long calculate(Time enterTime, Time outTime) {
        long usingParkingLotMilliSeconds = outTime.getMilliSeconds() - enterTime.getMilliSeconds();

        if (usingParkingLotMilliSeconds >= DAY_OF_MILLI_SECONDS) {
            return this.overDayCalculate(enterTime, outTime);
        }

        if (usingParkingLotMilliSeconds <= THIRTY_MINUTE_MILLISECONDS) {
            return BASIC_FEE;
        }

        long overSeconds = usingParkingLotMilliSeconds - THIRTY_MINUTE_MILLISECONDS;
        if (overSeconds >= MAX_PARK_MILLI_SECOND_PER_DAY) {
            return MAX_FEE_PER_DAY;
        }

        double overMinute = overSeconds / TEN_MINUTE_MILLI_SECONDS; // 이용 초과 분
        return BASIC_FEE + ((long) overMinute + 1) * OVER_TIME_FEE;
    }

    private int getDayOfNumber(Time dateTime) {
        return dateTime.getDateTime().getDayOfMonth();
    }

    private long overDayCalculate(Time enterTime, Time outTime) {
        int overDay = this.getDayOfNumber(outTime) - this.getDayOfNumber(enterTime);
        return (overDay * MAX_FEE_PER_DAY);
    }
}
