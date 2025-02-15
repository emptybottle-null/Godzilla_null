/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.Instruction;
import com.kichik.pecoff4j.asm.ModRM;
import com.kichik.pecoff4j.asm.SIB;

public abstract class AbstractInstruction
implements Instruction {
    protected byte[] code;
    protected int offset;
    protected String label;

    @Override
    public int size() {
        return this.code.length;
    }

    @Override
    public byte[] toCode() {
        return this.code;
    }

    public int getOpCode() {
        return this.code[0] & 0xFF;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    protected byte[] toCode(int opcode) {
        return new byte[]{(byte)opcode};
    }

    protected byte[] toCode(int opcode, ModRM modrm) {
        return new byte[]{(byte)opcode, modrm.encode()};
    }

    protected byte[] toCode(int opcode, byte imm8) {
        return new byte[]{(byte)opcode, imm8};
    }

    protected byte[] toCode(int opcode, int imm32) {
        return new byte[]{(byte)opcode, this.b1(imm32), this.b2(imm32), this.b3(imm32), this.b4(imm32)};
    }

    protected byte[] toCode(int opcode, ModRM modrm, byte imm8) {
        return new byte[]{(byte)opcode, modrm.encode(), imm8};
    }

    protected byte[] toCode(int opcode, ModRM modrm, int imm32) {
        return new byte[]{(byte)opcode, modrm.encode(), this.b1(imm32), this.b2(imm32), this.b3(imm32), this.b4(imm32)};
    }

    protected byte[] toCode(int opcode, ModRM modrm, SIB sib, byte imm8) {
        return new byte[]{(byte)opcode, modrm.encode(), sib.encode(), imm8};
    }

    protected byte[] toCode(int opcode, ModRM modrm, SIB sib, int imm32) {
        return new byte[]{(byte)opcode, modrm.encode(), sib.encode(), this.b1(imm32), this.b2(imm32), this.b3(imm32), this.b4(imm32)};
    }

    protected byte[] toCode(int opcode, ModRM modrm, int disp32, int imm32) {
        return new byte[]{(byte)opcode, modrm.encode(), this.b1(disp32), this.b2(disp32), this.b3(disp32), this.b4(disp32), this.b1(imm32), this.b2(imm32), this.b3(imm32), this.b4(imm32)};
    }

    protected byte[] toCode(int opcode, ModRM modrm, byte disp8, int imm32) {
        return new byte[]{(byte)opcode, modrm.encode(), disp8, this.b1(imm32), this.b2(imm32), this.b3(imm32), this.b4(imm32)};
    }

    protected byte b1(int value) {
        return (byte)(value & 0xFF);
    }

    protected byte b2(int value) {
        return (byte)(value >> 8 & 0xFF);
    }

    protected byte b3(int value) {
        return (byte)(value >> 16 & 0xFF);
    }

    protected byte b4(int value) {
        return (byte)(value >> 24 & 0xFF);
    }

    public static String toHexString(int value, boolean showSign) {
        StringBuilder sb = new StringBuilder();
        if (showSign) {
            if (value < 0) {
                value *= -1;
                sb.append('-');
            } else {
                sb.append('+');
            }
        }
        String s = Integer.toHexString(value);
        int pad = 8 - s.length();
        for (int i = 0; i < pad; ++i) {
            sb.append('0');
        }
        sb.append(s);
        return sb.toString();
    }

    public static String toHexString(byte value, boolean showSign) {
        StringBuilder sb = new StringBuilder();
        if (showSign) {
            if (value < 0) {
                value = (byte)(value * -1);
                sb.append('-');
            } else {
                sb.append('+');
            }
        }
        String s = Integer.toHexString(value & 0xFF);
        int pad = 2 - s.length();
        for (int i = 0; i < pad; ++i) {
            sb.append('0');
        }
        sb.append(s);
        return sb.toString();
    }
}

