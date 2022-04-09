package com.nhnacademy.car.cartype;

import com.nhnacademy.discountable.Discountable;
import com.nhnacademy.exceptions.NotMatchCarNumberException;

public enum CarType implements Discountable {
    LARGE {
        @Override
        public long discount(long fee) {
            throw new NotMatchCarNumberException("대형차는 지불 할 수 없습니다.");
        }
    },
    SMALL {
        @Override
        public long discount(long fee) {
            return fee / 2;
        }
    },
    NORMAL {
        @Override
        public long discount(long fee) {
            return fee;
        }
    }
}
