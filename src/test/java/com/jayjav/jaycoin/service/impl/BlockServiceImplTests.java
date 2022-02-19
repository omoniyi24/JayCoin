package com.jayjav.jaycoin.service.impl;

import com.jayjav.jaycoin.model.Block;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author OMONIYI ILESANMI
 */

@SpringBootTest
public class BlockServiceImplTests {

    @InjectMocks
    private BlockServiceImpl blockService;

    private String previousHash;
    private long proof;

    @BeforeEach
    public void setup() {
        previousHash = "28b755ce9a84fb9a59b3469ee19bd07f9cbb09e534ab14999309cc48d4dfd9d5";
        proof = 38561L;
    }

    @Test
    void returnChainWhenGetChainIsCalled() throws Exception {
        List<Block> returnedChain = blockService.getChain();
        assertNotNull(returnedChain);
        assertTrue(returnedChain.size() > 0);
    }

    @Test
    void returnBlockWhenBlockIsCreated() throws Exception {
        Block returnedBlock = blockService.createBlock(proof, previousHash);
        assertNotNull(returnedBlock);
        assertTrue(returnedBlock.getIndex() > 0);
    }

    @Test
    void returnBlockWhenGetPreviousIsCalled() throws Exception {
        Block returnedBlock = blockService.getPreviousBlock();
        assertNotNull(returnedBlock);
        System.out.println(">>>> " + returnedBlock);
        assertTrue(returnedBlock.getIndex() == 1);
    }

    @Test
    void returnNewProofOfWorkWhenProofOfWorkIsCalled() throws Exception {
        List<Block> returnedChain = blockService.getChain();
        Block block = returnedChain.get(0);
        Long newProofOfWork = blockService.proofOfWork(proof);
        assertNotNull(newProofOfWork);
        assertNotEquals(newProofOfWork,  block.getProof());
    }

    @Test
    void returnHashStringWhenHashIsCalled() throws Exception {
        List<Block> returnedChain = blockService.getChain();
        Block block = returnedChain.get(0);
        String newHashString = blockService.hash(block);
        assertNotNull(newHashString);
        assertTrue(newHashString.length() > 0);
    }

    @Test
    void testIfChainIsValid() throws Exception {
        List<Block> returnedChain = blockService.getChain();
        boolean chainValid = blockService.isChainValid(returnedChain);
        assertNotNull(chainValid);
        assertTrue(chainValid);
    }
}
