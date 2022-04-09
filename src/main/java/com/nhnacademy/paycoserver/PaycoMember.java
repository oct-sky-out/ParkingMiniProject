package com.nhnacademy.paycoserver;

import com.nhnacademy.discountable.Discountable;

public class PaycoMember implements Discountable {
    @Override
    public long discount(long fee) {
        return (long) (fee - (fee * 0.1));
    }

    @Override
    public String toString() {
        return "PaycoMember";
    }
}
