/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.util;

import com.kichik.pecoff4j.PE;
import com.kichik.pecoff4j.ResourceDirectory;
import com.kichik.pecoff4j.io.PEParser;
import com.kichik.pecoff4j.util.IO;
import com.kichik.pecoff4j.util.IconFile;
import java.io.File;
import java.io.IOException;

public class RCEdit {
    public static void main(String[] args) throws Exception {
        RCEdit.launch(new String[0]);
        RCEdit.launch(new String[]{"/I", "test/WinRun4J.exe", "test/eclipse.ico"});
    }

    public static void launch(String[] args) throws Exception {
        RCEdit.assertArgCount(args, 2, 3);
        String option = args[0].toUpperCase();
        if ("/I".equals(option)) {
            RCEdit.assertArgCount(args, 3, 3);
            RCEdit.addIcon(args[1], args[2]);
        } else if ("/N".equals(option)) {
            RCEdit.assertArgCount(args, 3, 3);
            RCEdit.setIni(args[1], args[2]);
        } else if ("/S".equals(option)) {
            RCEdit.assertArgCount(args, 3, 3);
            RCEdit.setSplash(args[1], args[2]);
        }
    }

    private static void addIcon(String exe, String icon) throws IOException {
        PE pe = PEParser.parse(exe);
        IconFile ic = IconFile.parse(icon);
    }

    private static void setIni(String exe, String ini) throws IOException {
        PE pe = PEParser.parse(exe);
        byte[] inib = IO.toBytes(new File(ini));
        ResourceDirectory rd = pe.getImageData().getResourceTable();
        if (rd != null) {
            // empty if block
        }
    }

    private static void setSplash(String exe, String splash) throws IOException {
        PE pe = PEParser.parse(exe);
        byte[] spb = IO.toBytes(new File(splash));
        ResourceDirectory rd = pe.getImageData().getResourceTable();
    }

    private static void assertArgCount(String[] args, int min, int max) {
        if (args.length < min || args.length > max) {
            RCEdit.printUsage();
            System.exit(1);
        }
    }

    private static void printUsage() {
        RCEdit.printf("WinRun4J Resource Editor v2.0 (winrun4j.sf.net)\n\n");
        RCEdit.printf("Edits resources in executables (EXE) and dynamic link-libraries (DLL).\n\n");
        RCEdit.printf("RCEDIT <option> <exe/dll> [resource]\n\n");
        RCEdit.printf("  filename\tSpecifies the filename of the EXE/DLL.\n");
        RCEdit.printf("  resource\tSpecifies the name of the resource to add to the EXE/DLL.\n");
        RCEdit.printf("  /I\t\tSet the icon as the default icon for the executable.\n");
        RCEdit.printf("  /A\t\tAdds an icon to the EXE/DLL.\n");
        RCEdit.printf("  /N\t\tSets the INI file.\n");
        RCEdit.printf("  /J\t\tAdds a JAR file.\n");
        RCEdit.printf("  /E\t\tExtracts a JAR file from the EXE/DLL.\n");
        RCEdit.printf("  /S\t\tSets the splash image.\n");
        RCEdit.printf("  /C\t\tClears all resources from the EXE/DLL.\n");
        RCEdit.printf("  /L\t\tLists the resources in the EXE/DLL.\n");
        RCEdit.printf("  /P\t\tOutputs the contents of the INI file in the EXE.\n");
    }

    private static void printf(String s) {
        System.out.print(s);
    }
}

