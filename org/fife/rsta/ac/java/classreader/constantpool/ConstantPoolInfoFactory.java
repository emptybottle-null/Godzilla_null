/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader.constantpool;

import java.io.DataInputStream;
import java.io.IOException;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantClassInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantDoubleInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantFieldrefInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantFloatInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantIntegerInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantInterfaceMethodrefInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantInvokeDynamicInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantLongInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantMethodHandleInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantMethodTypeInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantMethodrefInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantNameAndTypeInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantPoolInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantStringInfo;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantTypes;
import org.fife.rsta.ac.java.classreader.constantpool.ConstantUtf8Info;

public class ConstantPoolInfoFactory
implements ConstantTypes {
    private ConstantPoolInfoFactory() {
    }

    public static ConstantPoolInfo readConstantPoolInfo(ClassFile cf, DataInputStream in) throws IOException {
        ConstantPoolInfo cpi;
        int tag = in.read();
        switch (tag) {
            case 7: {
                int nameIndex = in.readUnsignedShort();
                cpi = new ConstantClassInfo(nameIndex);
                break;
            }
            case 6: {
                int highBytes = in.readInt();
                int lowBytes = in.readInt();
                cpi = new ConstantDoubleInfo(highBytes, lowBytes);
                break;
            }
            case 9: {
                int classIndex = in.readUnsignedShort();
                int nameAndTypeIndex = in.readUnsignedShort();
                cpi = new ConstantFieldrefInfo(classIndex, nameAndTypeIndex);
                break;
            }
            case 4: {
                int bytes = in.readInt();
                cpi = new ConstantFloatInfo(bytes);
                break;
            }
            case 3: {
                int bytes = in.readInt();
                cpi = new ConstantIntegerInfo((long)bytes);
                break;
            }
            case 11: {
                int classIndex = in.readUnsignedShort();
                int nameAndTypeIndex = in.readUnsignedShort();
                cpi = new ConstantInterfaceMethodrefInfo(classIndex, nameAndTypeIndex);
                break;
            }
            case 5: {
                int highBytes = in.readInt();
                int lowBytes = in.readInt();
                cpi = new ConstantLongInfo(highBytes, lowBytes);
                break;
            }
            case 10: {
                int classIndex = in.readUnsignedShort();
                int nameAndTypeIndex = in.readUnsignedShort();
                cpi = new ConstantMethodrefInfo(classIndex, nameAndTypeIndex);
                break;
            }
            case 12: {
                int nameIndex = in.readUnsignedShort();
                int descriptorIndex = in.readUnsignedShort();
                cpi = new ConstantNameAndTypeInfo(nameIndex, descriptorIndex);
                break;
            }
            case 8: {
                int stringIndex = in.readUnsignedShort();
                cpi = new ConstantStringInfo(cf, stringIndex);
                break;
            }
            case 1: {
                int count = in.readUnsignedShort();
                byte[] byteArray = new byte[count];
                in.readFully(byteArray);
                cpi = new ConstantUtf8Info(byteArray);
                break;
            }
            case 15: {
                int referenceKind = in.read();
                int referenceIndex = in.readUnsignedShort();
                cpi = new ConstantMethodHandleInfo(referenceKind, referenceIndex);
                break;
            }
            case 16: {
                int descriptorIndex = in.readUnsignedShort();
                cpi = new ConstantMethodTypeInfo(descriptorIndex);
                break;
            }
            case 18: {
                int bootstrapMethodAttrIndex = in.readUnsignedShort();
                int nameAndTypeIndex = in.readUnsignedShort();
                cpi = new ConstantInvokeDynamicInfo(bootstrapMethodAttrIndex, nameAndTypeIndex);
                break;
            }
            default: {
                throw new IOException("Unknown tag for constant pool info: " + tag);
            }
        }
        return cpi;
    }
}

