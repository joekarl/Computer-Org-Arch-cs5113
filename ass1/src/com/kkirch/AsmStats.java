/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch;

/**
 *
 * @author kkirch
 */
public class AsmStats {

    public int instructionCount;
    public int codeSizeInBytes;
    public int memoryAccessCount;
    public int memoryAccessInBytes;
    public int totalMemoryBandwithInBytes;
    public float averageCpi;
    public int totalCost;

    @Override
    public String toString() {
        return "AsmStats {"
                + "\n\tinstructionCount : " + instructionCount
                + ",\n\tcodeSizeInBytes : " + codeSizeInBytes
                + ",\n\tmemoryAccessCount : " + memoryAccessCount
                + ",\n\taverageCPI : " + averageCpi
                + ",\n\ttotalCost : " + totalCost
                + ",\n\tmemoryAccessInBytes : " + memoryAccessInBytes
                + ",\n\ttotalMemoryBandwidth : " + totalMemoryBandwithInBytes
                + "\n}";
    }
}
