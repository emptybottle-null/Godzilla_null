/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import java.io.IOException;
import java.io.PrintStream;

public class AssemblyFormatter {
    public static void format(AbstractInstruction[] instructions, PrintStream out) throws IOException {
        for (AbstractInstruction ai : instructions) {
            out.print(AbstractInstruction.toHexString(ai.getOffset(), false));
            out.print("   ");
            out.print(AssemblyFormatter.toHexString(ai.toCode(), 30));
            out.println(ai.toIntelAssembly());
        }
    }

    public static String toHexString(byte[] bytes, int pad) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(AssemblyFormatter.toHexString(b));
        }
        for (int i = pad - bytes.length * 2; i > 0; --i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    private static String toHexString(byte b) {
        String s = Integer.toHexString(b & 0xFF);
        if (s.length() == 1) {
            return "0" + s;
        }
        return s;
    }
}

