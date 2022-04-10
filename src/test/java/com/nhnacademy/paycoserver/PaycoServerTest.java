package com.nhnacademy.paycoserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaycoServerTest {
    PaycoServer server; // SUT
    PaycoMember member; // DOC

    @BeforeEach
    void setUp() {
        member = mock(PaycoMember.class);
        server = new PaycoServer();
        server.paycoMemberCommand(member);
    }

    @Test
    void eventDiscount() {
        long fee = 1000;

        when(member.discount(fee)).thenReturn(900L);
        assertThat(server.eventDiscount(fee)).isEqualTo(900);

        verify(member).discount(fee);
    }
}
