/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.function;

import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataReader;

public final class FunctionMetadataRegistry {
    public static final String FUNCTION_NAME_IF = "IF";
    public static final int FUNCTION_INDEX_IF = 1;
    public static final short FUNCTION_INDEX_SUM = 4;
    public static final int FUNCTION_INDEX_CHOOSE = 100;
    public static final short FUNCTION_INDEX_INDIRECT = 148;
    public static final short FUNCTION_INDEX_EXTERNAL = 255;
    private static FunctionMetadataRegistry _instance;
    private static FunctionMetadataRegistry _instanceCetab;
    private final FunctionMetadata[] _functionDataByIndex;
    private final Map<String, FunctionMetadata> _functionDataByName;

    private static FunctionMetadataRegistry getInstance() {
        if (_instance == null) {
            _instance = FunctionMetadataReader.createRegistry();
        }
        return _instance;
    }

    private static FunctionMetadataRegistry getInstanceCetab() {
        if (_instanceCetab == null) {
            _instanceCetab = FunctionMetadataReader.createRegistryCetab();
        }
        return _instanceCetab;
    }

    FunctionMetadataRegistry(FunctionMetadata[] functionDataByIndex, Map<String, FunctionMetadata> functionDataByName) {
        this._functionDataByIndex = functionDataByIndex == null ? null : (FunctionMetadata[])functionDataByIndex.clone();
        this._functionDataByName = functionDataByName;
    }

    Set<String> getAllFunctionNames() {
        return this._functionDataByName.keySet();
    }

    public static FunctionMetadata getFunctionByIndex(int index) {
        return FunctionMetadataRegistry.getInstance().getFunctionByIndexInternal(index);
    }

    public static FunctionMetadata getCetabFunctionByIndex(int index) {
        return FunctionMetadataRegistry.getInstanceCetab().getFunctionByIndexInternal(index);
    }

    private FunctionMetadata getFunctionByIndexInternal(int index) {
        return this._functionDataByIndex[index];
    }

    public static short lookupIndexByName(String name) {
        FunctionMetadata fd = FunctionMetadataRegistry.getInstance().getFunctionByNameInternal(name);
        if (fd == null && (fd = FunctionMetadataRegistry.getInstanceCetab().getFunctionByNameInternal(name)) == null) {
            return -1;
        }
        return (short)fd.getIndex();
    }

    private FunctionMetadata getFunctionByNameInternal(String name) {
        return this._functionDataByName.get(name);
    }

    public static FunctionMetadata getFunctionByName(String name) {
        FunctionMetadata fm = FunctionMetadataRegistry.getInstance().getFunctionByNameInternal(name);
        if (fm == null) {
            return FunctionMetadataRegistry.getInstanceCetab().getFunctionByNameInternal(name);
        }
        return fm;
    }
}

