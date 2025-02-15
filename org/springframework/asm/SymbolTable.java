/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.ByteVector;
import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.ConstantDynamic;
import org.springframework.asm.Handle;
import org.springframework.asm.Symbol;
import org.springframework.asm.Type;

final class SymbolTable {
    final ClassWriter classWriter;
    private final ClassReader sourceClassReader;
    private int majorVersion;
    private String className;
    private int entryCount;
    private Entry[] entries;
    private int constantPoolCount;
    private ByteVector constantPool;
    private int bootstrapMethodCount;
    private ByteVector bootstrapMethods;
    private int typeCount;
    private Entry[] typeTable;

    SymbolTable(ClassWriter classWriter) {
        this.classWriter = classWriter;
        this.sourceClassReader = null;
        this.entries = new Entry[256];
        this.constantPoolCount = 1;
        this.constantPool = new ByteVector();
    }

    SymbolTable(ClassWriter classWriter, ClassReader classReader) {
        byte itemTag;
        this.classWriter = classWriter;
        this.sourceClassReader = classReader;
        byte[] inputBytes = classReader.classFileBuffer;
        int constantPoolOffset = classReader.getItem(1) - 1;
        int constantPoolLength = classReader.header - constantPoolOffset;
        this.constantPoolCount = classReader.getItemCount();
        this.constantPool = new ByteVector(constantPoolLength);
        this.constantPool.putByteArray(inputBytes, constantPoolOffset, constantPoolLength);
        this.entries = new Entry[this.constantPoolCount * 2];
        char[] charBuffer = new char[classReader.getMaxStringLength()];
        boolean hasBootstrapMethods = false;
        block10: for (int itemIndex = 1; itemIndex < this.constantPoolCount; itemIndex += itemTag == 5 || itemTag == 6 ? 2 : 1) {
            int itemOffset = classReader.getItem(itemIndex);
            itemTag = inputBytes[itemOffset - 1];
            switch (itemTag) {
                case 9: 
                case 10: 
                case 11: {
                    int nameAndTypeItemOffset = classReader.getItem(classReader.readUnsignedShort(itemOffset + 2));
                    this.addConstantMemberReference(itemIndex, itemTag, classReader.readClass(itemOffset, charBuffer), classReader.readUTF8(nameAndTypeItemOffset, charBuffer), classReader.readUTF8(nameAndTypeItemOffset + 2, charBuffer));
                    continue block10;
                }
                case 3: 
                case 4: {
                    this.addConstantIntegerOrFloat(itemIndex, itemTag, classReader.readInt(itemOffset));
                    continue block10;
                }
                case 12: {
                    this.addConstantNameAndType(itemIndex, classReader.readUTF8(itemOffset, charBuffer), classReader.readUTF8(itemOffset + 2, charBuffer));
                    continue block10;
                }
                case 5: 
                case 6: {
                    this.addConstantLongOrDouble(itemIndex, itemTag, classReader.readLong(itemOffset));
                    continue block10;
                }
                case 1: {
                    this.addConstantUtf8(itemIndex, classReader.readUtf(itemIndex, charBuffer));
                    continue block10;
                }
                case 15: {
                    int memberRefItemOffset = classReader.getItem(classReader.readUnsignedShort(itemOffset + 1));
                    int nameAndTypeItemOffset = classReader.getItem(classReader.readUnsignedShort(memberRefItemOffset + 2));
                    this.addConstantMethodHandle(itemIndex, classReader.readByte(itemOffset), classReader.readClass(memberRefItemOffset, charBuffer), classReader.readUTF8(nameAndTypeItemOffset, charBuffer), classReader.readUTF8(nameAndTypeItemOffset + 2, charBuffer));
                    continue block10;
                }
                case 17: 
                case 18: {
                    hasBootstrapMethods = true;
                    int nameAndTypeItemOffset = classReader.getItem(classReader.readUnsignedShort(itemOffset + 2));
                    this.addConstantDynamicOrInvokeDynamicReference(itemTag, itemIndex, classReader.readUTF8(nameAndTypeItemOffset, charBuffer), classReader.readUTF8(nameAndTypeItemOffset + 2, charBuffer), classReader.readUnsignedShort(itemOffset));
                    continue block10;
                }
                case 7: 
                case 8: 
                case 16: 
                case 19: 
                case 20: {
                    this.addConstantUtf8Reference(itemIndex, itemTag, classReader.readUTF8(itemOffset, charBuffer));
                    continue block10;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        if (hasBootstrapMethods) {
            this.copyBootstrapMethods(classReader, charBuffer);
        }
    }

    private void copyBootstrapMethods(ClassReader classReader, char[] charBuffer) {
        byte[] inputBytes = classReader.classFileBuffer;
        int currentAttributeOffset = classReader.getFirstAttributeOffset();
        for (int i = classReader.readUnsignedShort(currentAttributeOffset - 2); i > 0; --i) {
            String attributeName = classReader.readUTF8(currentAttributeOffset, charBuffer);
            if ("BootstrapMethods".equals(attributeName)) {
                this.bootstrapMethodCount = classReader.readUnsignedShort(currentAttributeOffset + 6);
                break;
            }
            currentAttributeOffset += 6 + classReader.readInt(currentAttributeOffset + 2);
        }
        if (this.bootstrapMethodCount > 0) {
            int bootstrapMethodsOffset = currentAttributeOffset + 8;
            int bootstrapMethodsLength = classReader.readInt(currentAttributeOffset + 2) - 2;
            this.bootstrapMethods = new ByteVector(bootstrapMethodsLength);
            this.bootstrapMethods.putByteArray(inputBytes, bootstrapMethodsOffset, bootstrapMethodsLength);
            int currentOffset = bootstrapMethodsOffset;
            for (int i = 0; i < this.bootstrapMethodCount; ++i) {
                int offset = currentOffset - bootstrapMethodsOffset;
                int bootstrapMethodRef = classReader.readUnsignedShort(currentOffset);
                int numBootstrapArguments = classReader.readUnsignedShort(currentOffset += 2);
                currentOffset += 2;
                int hashCode = classReader.readConst(bootstrapMethodRef, charBuffer).hashCode();
                while (numBootstrapArguments-- > 0) {
                    int bootstrapArgument = classReader.readUnsignedShort(currentOffset);
                    currentOffset += 2;
                    hashCode ^= classReader.readConst(bootstrapArgument, charBuffer).hashCode();
                }
                this.add(new Entry(i, 64, offset, hashCode & Integer.MAX_VALUE));
            }
        }
    }

    ClassReader getSource() {
        return this.sourceClassReader;
    }

    int getMajorVersion() {
        return this.majorVersion;
    }

    String getClassName() {
        return this.className;
    }

    int setMajorVersionAndClassName(int majorVersion, String className) {
        this.majorVersion = majorVersion;
        this.className = className;
        return this.addConstantClass((String)className).index;
    }

    int getConstantPoolCount() {
        return this.constantPoolCount;
    }

    int getConstantPoolLength() {
        return this.constantPool.length;
    }

    void putConstantPool(ByteVector output) {
        output.putShort(this.constantPoolCount).putByteArray(this.constantPool.data, 0, this.constantPool.length);
    }

    int computeBootstrapMethodsSize() {
        if (this.bootstrapMethods != null) {
            this.addConstantUtf8("BootstrapMethods");
            return 8 + this.bootstrapMethods.length;
        }
        return 0;
    }

    void putBootstrapMethods(ByteVector output) {
        if (this.bootstrapMethods != null) {
            output.putShort(this.addConstantUtf8("BootstrapMethods")).putInt(this.bootstrapMethods.length + 2).putShort(this.bootstrapMethodCount).putByteArray(this.bootstrapMethods.data, 0, this.bootstrapMethods.length);
        }
    }

    private Entry get(int hashCode) {
        return this.entries[hashCode % this.entries.length];
    }

    private Entry put(Entry entry) {
        if (this.entryCount > this.entries.length * 3 / 4) {
            int currentCapacity = this.entries.length;
            int newCapacity = currentCapacity * 2 + 1;
            Entry[] newEntries = new Entry[newCapacity];
            for (int i = currentCapacity - 1; i >= 0; --i) {
                Entry currentEntry = this.entries[i];
                while (currentEntry != null) {
                    int newCurrentEntryIndex = currentEntry.hashCode % newCapacity;
                    Entry nextEntry = currentEntry.next;
                    currentEntry.next = newEntries[newCurrentEntryIndex];
                    newEntries[newCurrentEntryIndex] = currentEntry;
                    currentEntry = nextEntry;
                }
            }
            this.entries = newEntries;
        }
        ++this.entryCount;
        int index = entry.hashCode % this.entries.length;
        entry.next = this.entries[index];
        this.entries[index] = entry;
        return this.entries[index];
    }

    private void add(Entry entry) {
        ++this.entryCount;
        int index = entry.hashCode % this.entries.length;
        entry.next = this.entries[index];
        this.entries[index] = entry;
    }

    Symbol addConstant(Object value) {
        if (value instanceof Integer) {
            return this.addConstantInteger((Integer)value);
        }
        if (value instanceof Byte) {
            return this.addConstantInteger(((Byte)value).intValue());
        }
        if (value instanceof Character) {
            return this.addConstantInteger(((Character)value).charValue());
        }
        if (value instanceof Short) {
            return this.addConstantInteger(((Short)value).intValue());
        }
        if (value instanceof Boolean) {
            return this.addConstantInteger((Boolean)value != false ? 1 : 0);
        }
        if (value instanceof Float) {
            return this.addConstantFloat(((Float)value).floatValue());
        }
        if (value instanceof Long) {
            return this.addConstantLong((Long)value);
        }
        if (value instanceof Double) {
            return this.addConstantDouble((Double)value);
        }
        if (value instanceof String) {
            return this.addConstantString((String)value);
        }
        if (value instanceof Type) {
            Type type = (Type)value;
            int typeSort = type.getSort();
            if (typeSort == 10) {
                return this.addConstantClass(type.getInternalName());
            }
            if (typeSort == 11) {
                return this.addConstantMethodType(type.getDescriptor());
            }
            return this.addConstantClass(type.getDescriptor());
        }
        if (value instanceof Handle) {
            Handle handle = (Handle)value;
            return this.addConstantMethodHandle(handle.getTag(), handle.getOwner(), handle.getName(), handle.getDesc(), handle.isInterface());
        }
        if (value instanceof ConstantDynamic) {
            ConstantDynamic constantDynamic = (ConstantDynamic)value;
            return this.addConstantDynamic(constantDynamic.getName(), constantDynamic.getDescriptor(), constantDynamic.getBootstrapMethod(), constantDynamic.getBootstrapMethodArgumentsUnsafe());
        }
        throw new IllegalArgumentException("value " + value);
    }

    Symbol addConstantClass(String value) {
        return this.addConstantUtf8Reference(7, value);
    }

    Symbol addConstantFieldref(String owner, String name, String descriptor) {
        return this.addConstantMemberReference(9, owner, name, descriptor);
    }

    Symbol addConstantMethodref(String owner, String name, String descriptor, boolean isInterface) {
        int tag = isInterface ? 11 : 10;
        return this.addConstantMemberReference(tag, owner, name, descriptor);
    }

    private Entry addConstantMemberReference(int tag, String owner, String name, String descriptor) {
        int hashCode = SymbolTable.hash(tag, owner, name, descriptor);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == tag && entry.hashCode == hashCode && entry.owner.equals(owner) && entry.name.equals(name) && entry.value.equals(descriptor)) {
                return entry;
            }
            entry = entry.next;
        }
        this.constantPool.put122(tag, this.addConstantClass((String)owner).index, this.addConstantNameAndType(name, descriptor));
        return this.put(new Entry(this.constantPoolCount++, tag, owner, name, descriptor, 0L, hashCode));
    }

    private void addConstantMemberReference(int index, int tag, String owner, String name, String descriptor) {
        this.add(new Entry(index, tag, owner, name, descriptor, 0L, SymbolTable.hash(tag, owner, name, descriptor)));
    }

    Symbol addConstantString(String value) {
        return this.addConstantUtf8Reference(8, value);
    }

    Symbol addConstantInteger(int value) {
        return this.addConstantIntegerOrFloat(3, value);
    }

    Symbol addConstantFloat(float value) {
        return this.addConstantIntegerOrFloat(4, Float.floatToRawIntBits(value));
    }

    private Symbol addConstantIntegerOrFloat(int tag, int value) {
        int hashCode = SymbolTable.hash(tag, value);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == tag && entry.hashCode == hashCode && entry.data == (long)value) {
                return entry;
            }
            entry = entry.next;
        }
        this.constantPool.putByte(tag).putInt(value);
        return this.put(new Entry(this.constantPoolCount++, tag, value, hashCode));
    }

    private void addConstantIntegerOrFloat(int index, int tag, int value) {
        this.add(new Entry(index, tag, value, SymbolTable.hash(tag, value)));
    }

    Symbol addConstantLong(long value) {
        return this.addConstantLongOrDouble(5, value);
    }

    Symbol addConstantDouble(double value) {
        return this.addConstantLongOrDouble(6, Double.doubleToRawLongBits(value));
    }

    private Symbol addConstantLongOrDouble(int tag, long value) {
        int hashCode = SymbolTable.hash(tag, value);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == tag && entry.hashCode == hashCode && entry.data == value) {
                return entry;
            }
            entry = entry.next;
        }
        int index = this.constantPoolCount;
        this.constantPool.putByte(tag).putLong(value);
        this.constantPoolCount += 2;
        return this.put(new Entry(index, tag, value, hashCode));
    }

    private void addConstantLongOrDouble(int index, int tag, long value) {
        this.add(new Entry(index, tag, value, SymbolTable.hash(tag, value)));
    }

    int addConstantNameAndType(String name, String descriptor) {
        int tag = 12;
        int hashCode = SymbolTable.hash(12, name, descriptor);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == 12 && entry.hashCode == hashCode && entry.name.equals(name) && entry.value.equals(descriptor)) {
                return entry.index;
            }
            entry = entry.next;
        }
        this.constantPool.put122(12, this.addConstantUtf8(name), this.addConstantUtf8(descriptor));
        return this.put((Entry)new Entry((int)this.constantPoolCount++, (int)12, (String)name, (String)descriptor, (int)hashCode)).index;
    }

    private void addConstantNameAndType(int index, String name, String descriptor) {
        int tag = 12;
        this.add(new Entry(index, 12, name, descriptor, SymbolTable.hash(12, name, descriptor)));
    }

    int addConstantUtf8(String value) {
        int hashCode = SymbolTable.hash(1, value);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == 1 && entry.hashCode == hashCode && entry.value.equals(value)) {
                return entry.index;
            }
            entry = entry.next;
        }
        this.constantPool.putByte(1).putUTF8(value);
        return this.put((Entry)new Entry((int)this.constantPoolCount++, (int)1, (String)value, (int)hashCode)).index;
    }

    private void addConstantUtf8(int index, String value) {
        this.add(new Entry(index, 1, value, SymbolTable.hash(1, value)));
    }

    Symbol addConstantMethodHandle(int referenceKind, String owner, String name, String descriptor, boolean isInterface) {
        int tag = 15;
        int hashCode = SymbolTable.hash(15, owner, name, descriptor, referenceKind);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == 15 && entry.hashCode == hashCode && entry.data == (long)referenceKind && entry.owner.equals(owner) && entry.name.equals(name) && entry.value.equals(descriptor)) {
                return entry;
            }
            entry = entry.next;
        }
        if (referenceKind <= 4) {
            this.constantPool.put112(15, referenceKind, this.addConstantFieldref((String)owner, (String)name, (String)descriptor).index);
        } else {
            this.constantPool.put112(15, referenceKind, this.addConstantMethodref((String)owner, (String)name, (String)descriptor, (boolean)isInterface).index);
        }
        return this.put(new Entry(this.constantPoolCount++, 15, owner, name, descriptor, referenceKind, hashCode));
    }

    private void addConstantMethodHandle(int index, int referenceKind, String owner, String name, String descriptor) {
        int tag = 15;
        int hashCode = SymbolTable.hash(15, owner, name, descriptor, referenceKind);
        this.add(new Entry(index, 15, owner, name, descriptor, referenceKind, hashCode));
    }

    Symbol addConstantMethodType(String methodDescriptor) {
        return this.addConstantUtf8Reference(16, methodDescriptor);
    }

    Symbol addConstantDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object ... bootstrapMethodArguments) {
        Symbol bootstrapMethod = this.addBootstrapMethod(bootstrapMethodHandle, bootstrapMethodArguments);
        return this.addConstantDynamicOrInvokeDynamicReference(17, name, descriptor, bootstrapMethod.index);
    }

    Symbol addConstantInvokeDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object ... bootstrapMethodArguments) {
        Symbol bootstrapMethod = this.addBootstrapMethod(bootstrapMethodHandle, bootstrapMethodArguments);
        return this.addConstantDynamicOrInvokeDynamicReference(18, name, descriptor, bootstrapMethod.index);
    }

    private Symbol addConstantDynamicOrInvokeDynamicReference(int tag, String name, String descriptor, int bootstrapMethodIndex) {
        int hashCode = SymbolTable.hash(tag, name, descriptor, bootstrapMethodIndex);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == tag && entry.hashCode == hashCode && entry.data == (long)bootstrapMethodIndex && entry.name.equals(name) && entry.value.equals(descriptor)) {
                return entry;
            }
            entry = entry.next;
        }
        this.constantPool.put122(tag, bootstrapMethodIndex, this.addConstantNameAndType(name, descriptor));
        return this.put(new Entry(this.constantPoolCount++, tag, null, name, descriptor, bootstrapMethodIndex, hashCode));
    }

    private void addConstantDynamicOrInvokeDynamicReference(int tag, int index, String name, String descriptor, int bootstrapMethodIndex) {
        int hashCode = SymbolTable.hash(tag, name, descriptor, bootstrapMethodIndex);
        this.add(new Entry(index, tag, null, name, descriptor, bootstrapMethodIndex, hashCode));
    }

    Symbol addConstantModule(String moduleName) {
        return this.addConstantUtf8Reference(19, moduleName);
    }

    Symbol addConstantPackage(String packageName) {
        return this.addConstantUtf8Reference(20, packageName);
    }

    private Symbol addConstantUtf8Reference(int tag, String value) {
        int hashCode = SymbolTable.hash(tag, value);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == tag && entry.hashCode == hashCode && entry.value.equals(value)) {
                return entry;
            }
            entry = entry.next;
        }
        this.constantPool.put12(tag, this.addConstantUtf8(value));
        return this.put(new Entry(this.constantPoolCount++, tag, value, hashCode));
    }

    private void addConstantUtf8Reference(int index, int tag, String value) {
        this.add(new Entry(index, tag, value, SymbolTable.hash(tag, value)));
    }

    Symbol addBootstrapMethod(Handle bootstrapMethodHandle, Object ... bootstrapMethodArguments) {
        ByteVector bootstrapMethodsAttribute = this.bootstrapMethods;
        if (bootstrapMethodsAttribute == null) {
            bootstrapMethodsAttribute = this.bootstrapMethods = new ByteVector();
        }
        int numBootstrapArguments = bootstrapMethodArguments.length;
        int[] bootstrapMethodArgumentIndexes = new int[numBootstrapArguments];
        for (int i = 0; i < numBootstrapArguments; ++i) {
            bootstrapMethodArgumentIndexes[i] = this.addConstant((Object)bootstrapMethodArguments[i]).index;
        }
        int bootstrapMethodOffset = bootstrapMethodsAttribute.length;
        bootstrapMethodsAttribute.putShort(this.addConstantMethodHandle((int)bootstrapMethodHandle.getTag(), (String)bootstrapMethodHandle.getOwner(), (String)bootstrapMethodHandle.getName(), (String)bootstrapMethodHandle.getDesc(), (boolean)bootstrapMethodHandle.isInterface()).index);
        bootstrapMethodsAttribute.putShort(numBootstrapArguments);
        for (int i = 0; i < numBootstrapArguments; ++i) {
            bootstrapMethodsAttribute.putShort(bootstrapMethodArgumentIndexes[i]);
        }
        int bootstrapMethodlength = bootstrapMethodsAttribute.length - bootstrapMethodOffset;
        int hashCode = bootstrapMethodHandle.hashCode();
        for (Object bootstrapMethodArgument : bootstrapMethodArguments) {
            hashCode ^= bootstrapMethodArgument.hashCode();
        }
        return this.addBootstrapMethod(bootstrapMethodOffset, bootstrapMethodlength, hashCode &= Integer.MAX_VALUE);
    }

    private Symbol addBootstrapMethod(int offset, int length, int hashCode) {
        byte[] bootstrapMethodsData = this.bootstrapMethods.data;
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == 64 && entry.hashCode == hashCode) {
                int otherOffset = (int)entry.data;
                boolean isSameBootstrapMethod = true;
                for (int i = 0; i < length; ++i) {
                    if (bootstrapMethodsData[offset + i] == bootstrapMethodsData[otherOffset + i]) continue;
                    isSameBootstrapMethod = false;
                    break;
                }
                if (isSameBootstrapMethod) {
                    this.bootstrapMethods.length = offset;
                    return entry;
                }
            }
            entry = entry.next;
        }
        return this.put(new Entry(this.bootstrapMethodCount++, 64, offset, hashCode));
    }

    Symbol getType(int typeIndex) {
        return this.typeTable[typeIndex];
    }

    int addType(String value) {
        int hashCode = SymbolTable.hash(128, value);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == 128 && entry.hashCode == hashCode && entry.value.equals(value)) {
                return entry.index;
            }
            entry = entry.next;
        }
        return this.addTypeInternal(new Entry(this.typeCount, 128, value, hashCode));
    }

    int addUninitializedType(String value, int bytecodeOffset) {
        int hashCode = SymbolTable.hash(129, value, bytecodeOffset);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == 129 && entry.hashCode == hashCode && entry.data == (long)bytecodeOffset && entry.value.equals(value)) {
                return entry.index;
            }
            entry = entry.next;
        }
        return this.addTypeInternal(new Entry(this.typeCount, 129, value, bytecodeOffset, hashCode));
    }

    int addMergedType(int typeTableIndex1, int typeTableIndex2) {
        int commonSuperTypeIndex;
        long data = typeTableIndex1 < typeTableIndex2 ? (long)typeTableIndex1 | (long)typeTableIndex2 << 32 : (long)typeTableIndex2 | (long)typeTableIndex1 << 32;
        int hashCode = SymbolTable.hash(130, typeTableIndex1 + typeTableIndex2);
        Entry entry = this.get(hashCode);
        while (entry != null) {
            if (entry.tag == 130 && entry.hashCode == hashCode && entry.data == data) {
                return entry.info;
            }
            entry = entry.next;
        }
        String type1 = this.typeTable[typeTableIndex1].value;
        String type2 = this.typeTable[typeTableIndex2].value;
        this.put((Entry)new Entry((int)this.typeCount, (int)130, (long)data, (int)hashCode)).info = commonSuperTypeIndex = this.addType(this.classWriter.getCommonSuperClass(type1, type2));
        return commonSuperTypeIndex;
    }

    private int addTypeInternal(Entry entry) {
        if (this.typeTable == null) {
            this.typeTable = new Entry[16];
        }
        if (this.typeCount == this.typeTable.length) {
            Entry[] newTypeTable = new Entry[2 * this.typeTable.length];
            System.arraycopy(this.typeTable, 0, newTypeTable, 0, this.typeTable.length);
            this.typeTable = newTypeTable;
        }
        this.typeTable[this.typeCount++] = entry;
        return this.put((Entry)entry).index;
    }

    private static int hash(int tag, int value) {
        return Integer.MAX_VALUE & tag + value;
    }

    private static int hash(int tag, long value) {
        return Integer.MAX_VALUE & tag + (int)value + (int)(value >>> 32);
    }

    private static int hash(int tag, String value) {
        return Integer.MAX_VALUE & tag + value.hashCode();
    }

    private static int hash(int tag, String value1, int value2) {
        return Integer.MAX_VALUE & tag + value1.hashCode() + value2;
    }

    private static int hash(int tag, String value1, String value2) {
        return Integer.MAX_VALUE & tag + value1.hashCode() * value2.hashCode();
    }

    private static int hash(int tag, String value1, String value2, int value3) {
        return Integer.MAX_VALUE & tag + value1.hashCode() * value2.hashCode() * (value3 + 1);
    }

    private static int hash(int tag, String value1, String value2, String value3) {
        return Integer.MAX_VALUE & tag + value1.hashCode() * value2.hashCode() * value3.hashCode();
    }

    private static int hash(int tag, String value1, String value2, String value3, int value4) {
        return Integer.MAX_VALUE & tag + value1.hashCode() * value2.hashCode() * value3.hashCode() * value4;
    }

    private static class Entry
    extends Symbol {
        final int hashCode;
        Entry next;

        Entry(int index, int tag, String owner, String name, String value, long data, int hashCode) {
            super(index, tag, owner, name, value, data);
            this.hashCode = hashCode;
        }

        Entry(int index, int tag, String value, int hashCode) {
            super(index, tag, null, null, value, 0L);
            this.hashCode = hashCode;
        }

        Entry(int index, int tag, String value, long data, int hashCode) {
            super(index, tag, null, null, value, data);
            this.hashCode = hashCode;
        }

        Entry(int index, int tag, String name, String value, int hashCode) {
            super(index, tag, null, name, value, 0L);
            this.hashCode = hashCode;
        }

        Entry(int index, int tag, long data, int hashCode) {
            super(index, tag, null, null, null, data);
            this.hashCode = hashCode;
        }
    }
}

