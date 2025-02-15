/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.classreader;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.MemberInfo;
import org.fife.rsta.ac.java.classreader.attributes.AttributeInfo;
import org.fife.rsta.ac.java.classreader.attributes.ConstantValue;

public class FieldInfo
extends MemberInfo {
    private int nameIndex;
    private int descriptorIndex;
    private List<AttributeInfo> attributes;
    public static final String CONSTANT_VALUE = "ConstantValue";

    public FieldInfo(ClassFile cf, int accessFlags, int nameIndex, int descriptorIndex) {
        super(cf, accessFlags);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = new ArrayList<AttributeInfo>(1);
    }

    public void addAttribute(AttributeInfo info) {
        this.attributes.add(info);
    }

    public AttributeInfo getAttribute(int index) {
        return this.attributes.get(index);
    }

    public int getAttributeCount() {
        return this.attributes.size();
    }

    public String getConstantValueAsString() {
        ConstantValue cv = this.getConstantValueAttributeInfo();
        return cv == null ? null : cv.getConstantValueAsString();
    }

    private ConstantValue getConstantValueAttributeInfo() {
        for (int i = 0; i < this.getAttributeCount(); ++i) {
            AttributeInfo ai = this.attributes.get(i);
            if (!(ai instanceof ConstantValue)) continue;
            return (ConstantValue)ai;
        }
        return null;
    }

    @Override
    public String getDescriptor() {
        return this.cf.getUtf8ValueFromConstantPool(this.descriptorIndex);
    }

    @Override
    public String getName() {
        return this.cf.getUtf8ValueFromConstantPool(this.nameIndex);
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public String getTypeString(boolean qualified) {
        StringBuilder sb = new StringBuilder();
        String descriptor = this.getDescriptor();
        int braceCount = descriptor.lastIndexOf(91) + 1;
        switch (descriptor.charAt(braceCount)) {
            case 'B': {
                sb.append("byte");
                break;
            }
            case 'C': {
                sb.append("char");
                break;
            }
            case 'D': {
                sb.append("double");
                break;
            }
            case 'F': {
                sb.append("float");
                break;
            }
            case 'I': {
                sb.append("int");
                break;
            }
            case 'J': {
                sb.append("long");
                break;
            }
            case 'S': {
                sb.append("short");
                break;
            }
            case 'Z': {
                sb.append("boolean");
                break;
            }
            case 'L': {
                String clazz = descriptor.substring(braceCount + 1, descriptor.length() - 1);
                clazz = qualified ? clazz.replace('/', '.') : clazz.substring(clazz.lastIndexOf(47) + 1);
                sb.append(clazz);
                break;
            }
            default: {
                sb.append("UNSUPPORTED_TYPE_").append(descriptor);
            }
        }
        for (int i = 0; i < braceCount; ++i) {
            sb.append("[]");
        }
        return sb.toString();
    }

    public boolean isConstant() {
        return this.getConstantValueAttributeInfo() != null;
    }

    public static FieldInfo read(ClassFile cf, DataInputStream in) throws IOException {
        FieldInfo info = new FieldInfo(cf, in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        int attrCount = in.readUnsignedShort();
        for (int i = 0; i < attrCount; ++i) {
            AttributeInfo ai = info.readAttribute(in);
            if (ai == null) continue;
            info.addAttribute(ai);
        }
        return info;
    }

    private AttributeInfo readAttribute(DataInputStream in) throws IOException {
        AttributeInfo ai;
        int attributeNameIndex = in.readUnsignedShort();
        int attributeLength = in.readInt();
        String attrName = this.cf.getUtf8ValueFromConstantPool(attributeNameIndex);
        if (CONSTANT_VALUE.equals(attrName)) {
            int constantValueIndex = in.readUnsignedShort();
            ConstantValue cv = new ConstantValue(this.cf, constantValueIndex);
            ai = cv;
        } else {
            ai = super.readAttribute(in, attrName, attributeLength);
        }
        return ai;
    }
}

