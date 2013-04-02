/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kkirch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Reads in comma delimited file of cpi information
 * 
 * Format is 
 * opcode,cpi
 * opcode,cpi
 * opcode,cpi
 * 
 * @author kkirch
 */
public class CpiReader {
    public Map<String,Integer> readCpi(InputStream input) throws IOException {
        HashMap<String, Integer> cpiMap = new HashMap<String,Integer>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String line;
        while((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            cpiMap.put(parts[0], Integer.parseInt(parts[1]));
        }
        
        return cpiMap;
    }
}
