package com.jayjav.jaycoin.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author OMONIYI ILESANMI
 */
@Data
public class Block {

    private Long index;
    private LocalDateTime timeStamp;
    private Long proof;
    private String previousHash;

    public Block(Long index, Long proof, String previousHash) {
        this.index = index;
        this.timeStamp = LocalDateTime.now();
        this.proof = proof;
        this.previousHash = previousHash;
    }

    public Block() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Block)) {
            return false;
        }
        return index != null && index.equals(((Block) o).index);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "Block{" +
                "index=" + index +
                ", timeStamp=" + timeStamp +
                ", proof=" + proof +
                ", previousHash='" + previousHash + '\'' +
                '}';
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getProof() {
        return proof;
    }

    public void setProof(Long proof) {
        this.proof = proof;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
}