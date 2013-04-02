/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kkirch
 */
public class AsmStatisticsGenerator {

    public AsmStats generateStats(InputStream input, Map<String, Integer> cpiMap) throws IOException {
        AsmStats stats = new AsmStats();

        BufferedReader br = new BufferedReader(new InputStreamReader(input));

        int accumulatedCost = 0;

        String line;
        Pattern asmInstructionPattern = Pattern.compile("\\s*([#A-Za-z\\-0-9]+)");
        while ((line = br.readLine()) != null) {
            if (line.matches("L\\d+:.*")) {
                //it does, print label then print other statements
                String[] lineSplit = line.split(":");
                if (lineSplit.length > 1) {
                    line = lineSplit[1];
                } else {
                    continue;
                }
            }
            Matcher matcher = asmInstructionPattern.matcher(line);
            String op = null;
            while (matcher.find()) {
                if (op == null) {
                    //operator 1 byte
                    op = matcher.group().trim();
                    Integer opCost = cpiMap.get(op);
                    if (opCost == null) {
                        throw new IllegalStateException("No Cost provided for operator " + op);
                    }
                    accumulatedCost += opCost;
                    stats.instructionCount++;
                    stats.codeSizeInBytes += 1;
                } else {
                    //operand 2 bytes
                    stats.codeSizeInBytes += 2;
                    if (isMemoryAccess(op, matcher.group().trim())) {
                        stats.memoryAccessCount++;
                    }
                }
            }
        }

        stats.memoryAccessInBytes = stats.memoryAccessCount * 4;
        stats.totalCPI = accumulatedCost / stats.instructionCount;
        stats.totalMemoryBandwithInBytes = stats.memoryAccessInBytes + stats.codeSizeInBytes;

        br.close();

        return stats;
    }

    //memory access if our operand is a memory location
    //and isn't a clear operator
    private boolean isMemoryAccess(String operator, String operand) {
        return !"CLEAR".equals(operator)
                && !operand.startsWith("R");
    }
}
