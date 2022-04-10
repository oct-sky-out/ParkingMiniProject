package com.nhnacademy.discountable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.car.cartype.CarType;
import com.nhnacademy.exceptions.NotMatchCarNumberException;
import com.nhnacademy.paycoserver.PaycoMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountableTest {

    @Test
    @DisplayName("Discountable을 구현하는 CarType의 LARGE는 예외를 던진다.")
    void discount_CarType_LARGE_throw_error() {
        Discountable large = CarType.LARGE;

        assertThatThrownBy(() -> large.discount(1000))
            .isInstanceOf(NotMatchCarNumberException.class)
            .hasMessageContaining("대형차", "지불 할 수 없습니다.");
    }

    @Test
    @DisplayName("Discountabled을 구현하는 CarType의 NORMAL은 할인하지않는다.")
    void discountable_CarType_NORMAL() {
        Discountable normal = CarType.NORMAL;

        assertThat(normal.discount(1000)).isEqualTo(1000);
    }

    @Test
    @DisplayName("Discountabled을 구현하는 CarType의 SMALL은 50% 할인한다.")
    void discountable_CarType_SMALL() {
        Discountable normal = CarType.SMALL;

        assertThat(normal.discount(1000)).isEqualTo(500);
    }

    @Test
    @DisplayName("Discountabled을 구현하는 Payco회원은 10% 할인한다.")
    void discountable_PaycoMember() {
        Discountable paycoMember = new PaycoMember();

        assertThat(paycoMember.discount(1000)).isEqualTo(900);
    }
}
