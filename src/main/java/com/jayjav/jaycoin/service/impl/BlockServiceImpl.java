package com.jayjav.jaycoin.service.impl;

import com.google.gson.Gson;
import com.jayjav.jaycoin.model.Block;
import com.jayjav.jaycoin.service.BlockService;
import com.jayjav.jaycoin.util.EncyptionUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OMONIYI ILESANMI
 */

@Service
public class BlockServiceImpl implements BlockService {

    private List<Block> chain = new ArrayList<>();

    public BlockServiceImpl() {
        Block block = new Block((long) (chain.size() + 1), 1L, "0");
        chain.add(block);
    }

    @Override
    public List<Block> getChain() {
        return this.chain;
    }

    @Override
    public Block createBlock(Long proof, String previoushash) {
        Block block = new Block((long) (chain.size() + 1), proof, previoushash);
        chain.add(block);
        return block;
    }

    @Override
    public Block getPreviousBlock() {
        return chain.get(chain.size() - 1);
    }

    @Override
    public Long proofOfWork(Long previousProof) {
        Long newProof = 1L;
        boolean checkProof = false;

        while (!checkProof) {
            String hashOperation = EncyptionUtil.applySha256(String.valueOf(Math.pow(newProof, 2) - Math.pow(previousProof, 2)));
            if (hashOperation.startsWith("0000")) {
                checkProof = true;
            } else {
                newProof += 1;
            }
        }
        return newProof;
    }

    @Override
    public String hash(Block block) {
        String blockInString = new Gson().toJson(block);
        return EncyptionUtil.applySha256(blockInString);
    }

    @Override
    public boolean isChainValid(List<Block> chain) {
        Block previousBlock = chain.get(0);
        int blockIndex = 1;

        while (blockIndex < chain.size()) {
            Block block = chain.get(blockIndex);
            if (!block.getPreviousHash().equals(hash(previousBlock))) {
                return false;
            }
            Long previousProof = previousBlock.getProof();
            Long proof = block.getProof();
            String hashOperation = EncyptionUtil.applySha256(String.valueOf(Math.pow(proof, 2) - Math.pow(previousProof, 2)));
            if (!hashOperation.startsWith("0000")) {
                return true;
            }
            previousBlock = block;
            blockIndex += 1;
        }
        return true;
    }
}