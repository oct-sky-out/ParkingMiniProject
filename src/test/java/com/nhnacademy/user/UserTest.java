package com.nhnacademy.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.car.Car;
import com.nhnacademy.car.cartype.CarType;
import com.nhnacademy.exceptions.LackMoneyException;
import com.nhnacademy.voucher.Voucher;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
    User user;

    @Test
    @DisplayName("유저가 출차 요금을 계산한다.")
    void user_pay_parking_lot_fee() {
        Car car = new Car("12A 1111", CarType.NORMAL);
        long fee = 1000;
        user = User.normalUser(1000, car, LocalDateTime.now());

        user.payParkingLotFee(fee);
        assertThat(user.getAmount()).isZero();
    }

    @Test
    @DisplayName("유저가 출차 시 요금이 부족하면 예외를 던진다.")
    void lack_user_money_then_throw_error() {
        Car car = new Car("12A 1111", CarType.NORMAL);
        long fee = 1000;
        user = User.normalUser(560, car, LocalDateTime.now());

        assertThatThrownBy(() -> user.payParkingLotFee(fee))
            .isInstanceOf(LackMoneyException.class)
            .hasMessageContaining("잔액", "부족");
    }

    @Test
    @DisplayName("유저가 출차 시 주차권을 얻은 후 한장 사용 후 정산한다.")
    void user_use_voucher_and_pay() {
        Car car = new Car("12A 1111", CarType.NORMAL);
        long fee = 1500;
        user = User.normalUser(1500, car, LocalDateTime.now());
        user.takeVoucher(Voucher.ONE_HOUR);
        user.payParkingLotFee(fee);

        assertThat(user.getAmount()).isEqualTo(1000);
    }
}
