/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.ByteVector;
import org.springframework.asm.ModuleVisitor;
import org.springframework.asm.SymbolTable;

final class ModuleWriter
extends ModuleVisitor {
    private final SymbolTable symbolTable;
    private final int moduleNameIndex;
    private final int moduleFlags;
    private final int moduleVersionIndex;
    private int requiresCount;
    private final ByteVector requires;
    private int exportsCount;
    private final ByteVector exports;
    private int opensCount;
    private final ByteVector opens;
    private int usesCount;
    private final ByteVector usesIndex;
    private int providesCount;
    private final ByteVector provides;
    private int packageCount;
    private final ByteVector packageIndex;
    private int mainClassIndex;

    ModuleWriter(SymbolTable symbolTable, int name, int access, int version) {
        super(589824);
        this.symbolTable = symbolTable;
        this.moduleNameIndex = name;
        this.moduleFlags = access;
        this.moduleVersionIndex = version;
        this.requires = new ByteVector();
        this.exports = new ByteVector();
        this.opens = new ByteVector();
        this.usesIndex = new ByteVector();
        this.provides = new ByteVector();
        this.packageIndex = new ByteVector();
    }

    @Override
    public void visitMainClass(String mainClass) {
        this.mainClassIndex = this.symbolTable.addConstantClass((String)mainClass).index;
    }

    @Override
    public void visitPackage(String packaze) {
        this.packageIndex.putShort(this.symbolTable.addConstantPackage((String)packaze).index);
        ++this.packageCount;
    }

    @Override
    public void visitRequire(String module, int access, String version) {
        this.requires.putShort(this.symbolTable.addConstantModule((String)module).index).putShort(access).putShort(version == null ? 0 : this.symbolTable.addConstantUtf8(version));
        ++this.requiresCount;
    }

    @Override
    public void visitExport(String packaze, int access, String ... modules) {
        this.exports.putShort(this.symbolTable.addConstantPackage((String)packaze).index).putShort(access);
        if (modules == null) {
            this.exports.putShort(0);
        } else {
            this.exports.putShort(modules.length);
            for (String module : modules) {
                this.exports.putShort(this.symbolTable.addConstantModule((String)module).index);
            }
        }
        ++this.exportsCount;
    }

    @Override
    public void visitOpen(String packaze, int access, String ... modules) {
        this.opens.putShort(this.symbolTable.addConstantPackage((String)packaze).index).putShort(access);
        if (modules == null) {
            this.opens.putShort(0);
        } else {
            this.opens.putShort(modules.length);
            for (String module : modules) {
                this.opens.putShort(this.symbolTable.addConstantModule((String)module).index);
            }
        }
        ++this.opensCount;
    }

    @Override
    public void visitUse(String service) {
        this.usesIndex.putShort(this.symbolTable.addConstantClass((String)service).index);
        ++this.usesCount;
    }

    @Override
    public void visitProvide(String service, String ... providers) {
        this.provides.putShort(this.symbolTable.addConstantClass((String)service).index);
        this.provides.putShort(providers.length);
        for (String provider : providers) {
            this.provides.putShort(this.symbolTable.addConstantClass((String)provider).index);
        }
        ++this.providesCount;
    }

    @Override
    public void visitEnd() {
    }

    int getAttributeCount() {
        return 1 + (this.packageCount > 0 ? 1 : 0) + (this.mainClassIndex > 0 ? 1 : 0);
    }

    int computeAttributesSize() {
        this.symbolTable.addConstantUtf8("Module");
        int size = 22 + this.requires.length + this.exports.length + this.opens.length + this.usesIndex.length + this.provides.length;
        if (this.packageCount > 0) {
            this.symbolTable.addConstantUtf8("ModulePackages");
            size += 8 + this.packageIndex.length;
        }
        if (this.mainClassIndex > 0) {
            this.symbolTable.addConstantUtf8("ModuleMainClass");
            size += 8;
        }
        return size;
    }

    void putAttributes(ByteVector output) {
        int moduleAttributeLength = 16 + this.requires.length + this.exports.length + this.opens.length + this.usesIndex.length + this.provides.length;
        output.putShort(this.symbolTable.addConstantUtf8("Module")).putInt(moduleAttributeLength).putShort(this.moduleNameIndex).putShort(this.moduleFlags).putShort(this.moduleVersionIndex).putShort(this.requiresCount).putByteArray(this.requires.data, 0, this.requires.length).putShort(this.exportsCount).putByteArray(this.exports.data, 0, this.exports.length).putShort(this.opensCount).putByteArray(this.opens.data, 0, this.opens.length).putShort(this.usesCount).putByteArray(this.usesIndex.data, 0, this.usesIndex.length).putShort(this.providesCount).putByteArray(this.provides.data, 0, this.provides.length);
        if (this.packageCount > 0) {
            output.putShort(this.symbolTable.addConstantUtf8("ModulePackages")).putInt(2 + this.packageIndex.length).putShort(this.packageCount).putByteArray(this.packageIndex.data, 0, this.packageIndex.length);
        }
        if (this.mainClassIndex > 0) {
            output.putShort(this.symbolTable.addConstantUtf8("ModuleMainClass")).putInt(2).putShort(this.mainClassIndex);
        }
    }
}

