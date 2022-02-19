package com.jayjav.jaycoin.service;

import com.jayjav.jaycoin.model.Block;
import java.util.List;

/**
 * @author OMONIYI ILESANMI
 */
public interface BlockService {

    List<Block> getChain();

    Block createBlock(Long proof, String previoushash);

    Block getPreviousBlock();

    Long proofOfWork(Long previousProof);

    String hash(Block block);

    boolean isChainValid(List<Block> chain);
}