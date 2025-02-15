/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.ModRM;
import com.kichik.pecoff4j.asm.Register;
import com.kichik.pecoff4j.asm.SIB;

public class MOV
extends AbstractInstruction {
    private ModRM modrm;
    private SIB sib;
    private int disp32;
    private int imm32;

    public MOV(ModRM modrm) {
        this.modrm = modrm;
        this.code = this.toCode(139, modrm);
    }

    public MOV(ModRM modrm, byte imm8) {
        this.modrm = modrm;
        this.imm32 = imm8;
        this.code = this.toCode(139, modrm, imm8);
    }

    public MOV(ModRM modrm, int disp32, int imm32) {
        this.modrm = modrm;
        this.disp32 = disp32;
        this.imm32 = imm32;
        this.code = this.toCode(199, modrm, disp32, imm32);
    }

    public MOV(ModRM modrm, byte disp8, int imm32) {
        this.modrm = modrm;
        this.disp32 = disp8;
        this.imm32 = imm32;
        this.code = this.toCode(199, modrm, disp8, imm32);
    }

    public MOV(ModRM modrm, int imm32) {
        this.modrm = modrm;
        this.imm32 = imm32;
        this.code = this.toCode(137, modrm, imm32);
    }

    public MOV(int opcode, ModRM modrm, byte imm8) {
        this.modrm = modrm;
        this.imm32 = imm8;
        this.code = this.toCode(opcode, modrm, imm8);
    }

    public MOV(int opcode, ModRM modrm, int imm32) {
        this.modrm = modrm;
        this.imm32 = imm32;
        this.code = this.toCode(opcode, modrm, imm32);
    }

    public MOV(int opcode, int imm32) {
        this.imm32 = imm32;
        this.code = this.toCode(opcode, imm32);
    }

    public MOV(ModRM modrm, SIB sib, byte imm8) {
        this.modrm = modrm;
        this.sib = sib;
        this.imm32 = imm8;
        this.code = this.toCode(137, modrm, sib, imm8);
    }

    public MOV(int opcode, ModRM modrm, SIB sib, int imm32) {
        this.modrm = modrm;
        this.sib = sib;
        this.imm32 = imm32;
        this.code = this.toCode(opcode, modrm, sib, imm32);
    }

    @Override
    public String toIntelAssembly() {
        switch (this.code[0] & 0xFF) {
            case 139: {
                switch (this.modrm.mod) {
                    case 0: {
                        return "mov  [" + Register.to32(this.modrm.reg2) + "], " + Register.to32(this.modrm.reg1);
                    }
                    case 1: {
                        return "mov  " + Register.to32(this.modrm.reg2) + ", [" + Register.to32(this.modrm.reg1) + MOV.toHexString((byte)this.imm32, true) + "]";
                    }
                    case 2: {
                        return "mov  " + Register.to32(this.modrm.reg2) + ", [" + Register.to32(this.modrm.reg1) + MOV.toHexString(this.imm32, true) + "]";
                    }
                    case 3: {
                        return "mov  " + Register.to32(this.modrm.reg2) + ", " + Register.to32(this.modrm.reg1);
                    }
                }
            }
            case 137: {
                switch (this.modrm.mod) {
                    default: 
                }
                return "mov  [" + Register.to32(this.modrm.reg1) + MOV.toHexString(this.imm32, true) + "], " + Register.to32(this.modrm.reg2);
            }
            case 198: {
                return "mov  byte ptr [" + Register.to32(this.modrm.reg1) + "], " + MOV.toHexString((byte)this.imm32, false);
            }
            case 199: {
                switch (this.modrm.mod) {
                    case 1: {
                        return "mov  dword ptr [" + Register.to32(this.modrm.reg1) + MOV.toHexString((byte)this.disp32, true) + "], " + MOV.toHexString(this.imm32, false);
                    }
                    case 2: {
                        return "mov  dword ptr [" + Register.to32(this.modrm.reg1) + MOV.toHexString(this.disp32, true) + "], " + MOV.toHexString(this.imm32, false);
                    }
                }
            }
            case 161: {
                return "mov  eax, [" + MOV.toHexString(this.imm32, false) + "]";
            }
            case 163: {
                return "mov  [" + MOV.toHexString(this.imm32, false) + "], eax";
            }
        }
        return "MOV: UNKNOWN";
    }
}

