/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.asm;

import com.kichik.pecoff4j.asm.ADD;
import com.kichik.pecoff4j.asm.AbstractInstruction;
import com.kichik.pecoff4j.asm.CALL;
import com.kichik.pecoff4j.asm.CMP;
import com.kichik.pecoff4j.asm.JGE;
import com.kichik.pecoff4j.asm.JMP;
import com.kichik.pecoff4j.asm.JumpIfInstruction;
import com.kichik.pecoff4j.asm.LEA;
import com.kichik.pecoff4j.asm.MOV;
import com.kichik.pecoff4j.asm.ModRM;
import com.kichik.pecoff4j.asm.POP;
import com.kichik.pecoff4j.asm.PUSH;
import com.kichik.pecoff4j.asm.RET;
import com.kichik.pecoff4j.asm.SHL;
import com.kichik.pecoff4j.asm.SIB;
import com.kichik.pecoff4j.asm.SUB;
import com.kichik.pecoff4j.asm.TEST;
import com.kichik.pecoff4j.util.Reflection;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AssemblyParser {
    public static AbstractInstruction[] parseAll(int offset, InputStream is) throws IOException {
        ArrayList<AbstractInstruction> instructions = new ArrayList<AbstractInstruction>();
        AbstractInstruction ins = null;
        while ((ins = AssemblyParser.parse(is)) != null) {
            ins.setOffset(offset);
            offset += ins.size();
            instructions.add(ins);
        }
        return instructions.toArray(new AbstractInstruction[instructions.size()]);
    }

    public static AbstractInstruction parse(InputStream is) throws IOException {
        int opcode = is.read() & 0xFF;
        int highop = opcode & 0xF0;
        ModRM modrm = null;
        SIB sib = null;
        switch (highop) {
            case 0: {
                switch (opcode) {
                    case 3: {
                        modrm = new ModRM(is.read());
                        int imm32 = AssemblyParser.readDoubleWord(is);
                        return new ADD(opcode, modrm, imm32);
                    }
                    case 15: {
                        return new JumpIfInstruction(is.read(), AssemblyParser.readDoubleWord(is));
                    }
                }
                break;
            }
            case 48: {
                switch (opcode) {
                    case 59: {
                        modrm = new ModRM(is.read());
                        int imm32 = is.read();
                        return new CMP(modrm, (byte)imm32);
                    }
                }
                break;
            }
            case 80: {
                if (opcode < 88) {
                    return new PUSH(opcode & 0xF);
                }
                return new POP(opcode >> 4 & 0xF);
            }
            case 96: {
                switch (opcode) {
                    case 104: {
                        return new PUSH(opcode, AssemblyParser.readDoubleWord(is));
                    }
                    case 106: {
                        return new PUSH((byte)is.read());
                    }
                }
                break;
            }
            case 112: {
                switch (opcode) {
                    case 125: {
                        return new JGE((byte)is.read());
                    }
                }
                break;
            }
            case 128: {
                modrm = new ModRM(is.read());
                switch (opcode) {
                    case 139: {
                        if (modrm.mod < 3 && modrm.reg1 == 4) {
                            sib = new SIB(is.read());
                        }
                        switch (modrm.mod) {
                            case 0: 
                            case 1: {
                                int imm32 = is.read();
                                if (sib != null) {
                                    return new MOV(modrm, sib, (byte)imm32);
                                }
                                return new MOV(modrm, (byte)imm32);
                            }
                            case 2: {
                                int imm32 = AssemblyParser.readDoubleWord(is);
                                if (sib != null) {
                                    return new MOV(opcode, modrm, sib, imm32);
                                }
                                return new MOV(opcode, modrm, imm32);
                            }
                        }
                        return new MOV(modrm);
                    }
                    case 129: {
                        int imm32 = AssemblyParser.readDoubleWord(is);
                        return new SUB(modrm, imm32);
                    }
                    case 131: {
                        int imm32 = is.read();
                        return new ADD(modrm, (byte)imm32);
                    }
                    case 137: {
                        switch (modrm.mod) {
                            case 0: 
                            case 1: {
                                int imm32 = is.read();
                                return new MOV(opcode, modrm, (byte)imm32);
                            }
                            case 2: {
                                int imm32 = AssemblyParser.readDoubleWord(is);
                                return new MOV(modrm, imm32);
                            }
                        }
                    }
                    case 133: {
                        return new TEST(modrm);
                    }
                    case 141: {
                        if (modrm.mod < 3 && modrm.reg1 == 4) {
                            sib = new SIB(is.read());
                            int imm32 = AssemblyParser.readDoubleWord(is);
                            return new LEA(modrm, sib, imm32);
                        }
                        int imm32 = AssemblyParser.readDoubleWord(is);
                        return new LEA(modrm, imm32);
                    }
                }
                AssemblyParser.print(modrm);
                break;
            }
            case 160: {
                switch (opcode) {
                    case 161: 
                    case 163: {
                        return new MOV(opcode, AssemblyParser.readDoubleWord(is));
                    }
                }
                break;
            }
            case 192: {
                switch (opcode) {
                    case 193: {
                        modrm = new ModRM(is.read());
                        int imm32 = is.read();
                        return new SHL(modrm, (byte)imm32);
                    }
                    case 195: {
                        return new RET();
                    }
                    case 198: {
                        modrm = new ModRM(is.read());
                        int imm32 = is.read();
                        return new MOV(opcode, modrm, (byte)imm32);
                    }
                    case 199: {
                        modrm = new ModRM(is.read());
                        switch (modrm.mod) {
                            case 1: {
                                int disp32 = is.read();
                                int imm32 = AssemblyParser.readDoubleWord(is);
                                return new MOV(modrm, (byte)disp32, imm32);
                            }
                        }
                        int disp32 = AssemblyParser.readDoubleWord(is);
                        int imm32 = AssemblyParser.readDoubleWord(is);
                        return new MOV(modrm, disp32, imm32);
                    }
                }
                break;
            }
            case 224: {
                switch (opcode) {
                    case 232: {
                        return new CALL(opcode, AssemblyParser.readDoubleWord(is));
                    }
                    case 233: {
                        return new JMP(AssemblyParser.readDoubleWord(is));
                    }
                    case 235: {
                        return new JMP((byte)is.read());
                    }
                }
                break;
            }
            case 240: {
                switch (opcode) {
                    case 255: {
                        modrm = new ModRM(is.read());
                        int imm32 = AssemblyParser.readDoubleWord(is);
                        return new CALL(modrm, imm32);
                    }
                }
            }
        }
        AssemblyParser.println(opcode);
        return null;
    }

    public static int readDoubleWord(InputStream is) throws IOException {
        return is.read() | is.read() << 8 | is.read() << 16 | is.read() << 24;
    }

    public static void print(Object o) {
        System.out.print(Reflection.toString(o));
    }

    public static void println(Object o) {
        System.out.println(Reflection.toString(o));
    }
}

