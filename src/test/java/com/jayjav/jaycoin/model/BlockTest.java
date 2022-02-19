package com.jayjav.jaycoin.model;

import com.jayjav.jaycoin.TestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class BlockTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Block.class);
        Block block1 = new Block();
        block1.setIndex(1L);
        Block block2 = new Block();
        block2.setIndex(block1.getIndex());
        assertEquals(block1, block2);
        block2.setIndex(2L);
        assertNotEquals(block1, block2);
        block1.setIndex(null);
        assertNotEquals(block1, block2);
    }
}
