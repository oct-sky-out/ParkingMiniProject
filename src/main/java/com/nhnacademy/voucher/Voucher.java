package com.nhnacademy.voucher;

import com.nhnacademy.discountable.Discountable;

public enum Voucher implements Discountable {
    TWO_HOUR(4000),
    ONE_HOUR(1000),
    ONE_DAY(15000);

    private final long amount;

    Voucher(long amount) {
        this.amount = amount;
    }

    @Override
    public long discount(long fee) {
        if (fee < this.amount) {
            return 0;
        }
        return fee - this.amount;
    }
}
