/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

public class Register {
    public static String to32(int register) {
        switch (register) {
            case 0: {
                return "eax";
            }
            case 1: {
                return "ecx";
            }
            case 2: {
                return "edx";
            }
            case 3: {
                return "ebx";
            }
            case 4: {
                return "esp";
            }
            case 5: {
                return "ebp";
            }
            case 6: {
                return "esi";
            }
            case 7: {
                return "edi";
            }
        }
        return null;
    }
}

