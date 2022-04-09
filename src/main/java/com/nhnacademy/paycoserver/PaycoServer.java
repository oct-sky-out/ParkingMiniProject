package com.nhnacademy.paycoserver;

public class PaycoServer {
    private PaycoMember paycoMember;

    public void paycoMemberCommand(PaycoMember paycoMember) {
        this.paycoMember = paycoMember;
    }

    public long eventDiscount(long fee) {
        return paycoMember.discount(fee);
    }
}
