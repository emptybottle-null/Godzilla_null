/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ss.formula.function;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.poi.ss.formula.function.FunctionMetadata;
import org.apache.poi.ss.formula.function.FunctionMetadataRegistry;

final class FunctionDataBuilder {
    private int _maxFunctionIndex = -1;
    private final Map<String, FunctionMetadata> _functionDataByName;
    private final Map<Integer, FunctionMetadata> _functionDataByIndex;
    private final Set<Integer> _mutatingFunctionIndexes;

    public FunctionDataBuilder(int sizeEstimate) {
        this._functionDataByName = new HashMap<String, FunctionMetadata>(sizeEstimate * 3 / 2);
        this._functionDataByIndex = new HashMap<Integer, FunctionMetadata>(sizeEstimate * 3 / 2);
        this._mutatingFunctionIndexes = new HashSet<Integer>();
    }

    public void add(int functionIndex, String functionName, int minParams, int maxParams, byte returnClassCode, byte[] parameterClassCodes, boolean hasFootnote) {
        FunctionMetadata prevFM;
        FunctionMetadata fm = new FunctionMetadata(functionIndex, functionName, minParams, maxParams, returnClassCode, parameterClassCodes);
        Integer indexKey = functionIndex;
        if (functionIndex > this._maxFunctionIndex) {
            this._maxFunctionIndex = functionIndex;
        }
        if ((prevFM = this._functionDataByName.get(functionName)) != null) {
            if (!hasFootnote || !this._mutatingFunctionIndexes.contains(indexKey)) {
                throw new RuntimeException("Multiple entries for function name '" + functionName + "'");
            }
            this._functionDataByIndex.remove(prevFM.getIndex());
        }
        if ((prevFM = this._functionDataByIndex.get(indexKey)) != null) {
            if (!hasFootnote || !this._mutatingFunctionIndexes.contains(indexKey)) {
                throw new RuntimeException("Multiple entries for function index (" + functionIndex + ")");
            }
            this._functionDataByName.remove(prevFM.getName());
        }
        if (hasFootnote) {
            this._mutatingFunctionIndexes.add(indexKey);
        }
        this._functionDataByIndex.put(indexKey, fm);
        this._functionDataByName.put(functionName, fm);
    }

    public FunctionMetadataRegistry build() {
        FunctionMetadata[] jumbledArray = new FunctionMetadata[this._functionDataByName.size()];
        this._functionDataByName.values().toArray(jumbledArray);
        FunctionMetadata[] fdIndexArray = new FunctionMetadata[this._maxFunctionIndex + 1];
        FunctionMetadata[] functionMetadataArray = jumbledArray;
        int n = functionMetadataArray.length;
        for (int i = 0; i < n; ++i) {
            FunctionMetadata fd;
            fdIndexArray[fd.getIndex()] = fd = functionMetadataArray[i];
        }
        return new FunctionMetadataRegistry(fdIndexArray, this._functionDataByName);
    }
}

