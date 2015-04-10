/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group20.antgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class Utils {
    public static String fileToString(String pathname) throws FileNotFoundException{
        File file = new File(pathname);
//        StringBuilder fileContents = new StringBuilder((int)file.length());
//        Scanner scanner = new Scanner(file);
//        String lineSeparator = System.getProperty("line.separator");
//
//        try {
//            while(scanner.hasNextLine()) {        
//                fileContents.append(scanner.nextLine()).append("\n");
//            }
//            return fileContents.toString();
//        } finally {
//            scanner.close();
//        }
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String readLine = "";
        String map = "";
        try {
            while ((readLine = buffer.readLine()) != null) {
                if(!map.equals("")){
                    map += "\n";
                }
                map += (readLine); 
            }
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

    public static String[] fileToStringArray(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        List<String> lines;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = new ArrayList<>();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines.toArray(new String[lines.size()]);
    }
    
    public static void writeToFile(String fileString, String filePath){

        try {
            Writer output = null;
            File file = new File(filePath);
            output = new BufferedWriter(new FileWriter(file));
            output.write(fileString);
            output.close();
            System.out.println("file written");       
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could not write file");
        }
    }
}
