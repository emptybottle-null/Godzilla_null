/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.util.List;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ast.ScriptNode;

public interface Evaluator {
    public Object compile(CompilerEnvirons var1, ScriptNode var2, String var3, boolean var4);

    public Function createFunctionObject(Context var1, Scriptable var2, Object var3, Object var4);

    public Script createScriptObject(Object var1, Object var2);

    public void captureStackInfo(RhinoException var1);

    public String getSourcePositionFromStack(Context var1, int[] var2);

    public String getPatchedStack(RhinoException var1, String var2);

    public List<String> getScriptStack(RhinoException var1);

    public void setEvalScriptFlag(Script var1);
}

