/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.xml.cpx;

import com.kitfox.svg.xml.cpx.CPXInputStream;
import com.kitfox.svg.xml.cpx.CPXOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CPXTest {
    public CPXTest() {
        this.writeTest();
        this.readTest();
    }

    public void writeTest() {
        try {
            int numBytes;
            InputStream is = CPXTest.class.getResourceAsStream("/data/readme.txt");
            FileOutputStream fout = new FileOutputStream("C:\\tmp\\cpxFile.cpx");
            CPXOutputStream cout = new CPXOutputStream(fout);
            byte[] buffer = new byte[1024];
            while ((numBytes = is.read(buffer)) != -1) {
                cout.write(buffer, 0, numBytes);
            }
            cout.close();
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
        }
    }

    public void readTest() {
        try {
            String line;
            FileInputStream is = new FileInputStream("C:\\tmp\\cpxFile.cpx");
            CPXInputStream cin = new CPXInputStream(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(cin));
            while ((line = br.readLine()) != null) {
                System.err.println(line);
            }
        } catch (Exception e) {
            Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
        }
    }

    public static void main(String[] args) {
        new CPXTest();
    }
}

