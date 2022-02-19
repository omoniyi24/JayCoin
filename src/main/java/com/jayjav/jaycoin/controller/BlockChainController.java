package com.jayjav.jaycoin.controller;

import com.jayjav.jaycoin.model.Block;
import com.jayjav.jaycoin.service.BlockService;
import com.jayjav.jaycoin.util.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author OMONIYI ILESANMI
 */

@RestController
@RequestMapping("/api/v1")
public class BlockChainController {

    @Autowired
    private BlockService blockService;

    @GetMapping("getChain")
    public List<Block> getChain(){
        return blockService.getChain();
    }

    @GetMapping("mineBlock")
    public Map<String, Object> mineBlock(){
        Block previousBlock = blockService.getPreviousBlock();
        Long previousProof = previousBlock.getProof();
        Long proof = blockService.proofOfWork(previousProof);
        String previousHash = blockService.hash(previousBlock);
        Block block = blockService.createBlock(proof, previousHash);
        Map<String, Object> response = new HashMap<>();
        response.put("message", ApplicationConstants.CONGRATULATIONS_MESSAGE);
        response.put("index", block.getIndex());
        response.put("timestamp", block.getTimeStamp());
        response.put("proof", block.getProof());
        response.put("previous_hash", block.getPreviousHash());
        return response;
    }

    @GetMapping("validateChain")
    public Map<String, Object> validateChain(){
        List<Block> chain = blockService.getChain();
        boolean chainValid = blockService.isChainValid(chain);
        Map<String, Object> response = new HashMap<>();
        if (chainValid){
            response.put("message", ApplicationConstants.VALID_BLOCK_MESSAGE);
        } else {
            response.put("message", ApplicationConstants.NOT_VALID_BLOCK_MESSAGE);
        }
        response.put("size", chain.size());
        return response;
    }
}
