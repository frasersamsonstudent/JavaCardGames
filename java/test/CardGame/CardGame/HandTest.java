package CardGame;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    Hand hand = new Hand("C5,D3,S7");

    @org.junit.jupiter.api.Test
    void getFirstCard() {
        assertEquals("C5", this.hand.getFirstCard().toString());
    }
}