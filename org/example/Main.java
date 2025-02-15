/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.example;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import shells.encoder.java.jspencounter;

public class Main {
    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\iUVNw4398nIF4328jvdw\\Downloads\\09051.jsp";
        String jspContent = Main.readJSPFile(filePath);
        String filePath2 = "C:\\Users\\iUVNw4398nIF4328jvdw\\Downloads\\090519.jsp";
        byte[] file1_byte = jspencounter.test2(jspContent.getBytes());
        System.out.println(Integer.toHexString(0xFF & file1_byte[0]));
        System.out.println(Integer.toHexString(0xFF & file1_byte[1]));
        Main.writeBytesToJSPFile(filePath2, file1_byte);
    }

    public static void writeBytesToJSPFile(String filePath, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath);){
            fos.write(data);
        }
    }

    public static String readJSPFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));){
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}

