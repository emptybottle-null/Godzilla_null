/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.engine;

import java.util.HashMap;
import org.fife.rsta.ac.js.engine.EMCAJavaScriptEngine;
import org.fife.rsta.ac.js.engine.JSR223JavaScriptEngine;
import org.fife.rsta.ac.js.engine.JavaScriptEngine;
import org.fife.rsta.ac.js.engine.RhinoJavaScriptEngine;

public class JavaScriptEngineFactory {
    public static final String DEFAULT = "EMCA";
    private HashMap<String, JavaScriptEngine> supportedEngines = new HashMap();
    private static JavaScriptEngineFactory Instance = new JavaScriptEngineFactory();

    private JavaScriptEngineFactory() {
    }

    public static JavaScriptEngineFactory Instance() {
        return Instance;
    }

    public JavaScriptEngine getEngineFromCache(String name) {
        if (name == null) {
            name = DEFAULT;
        }
        return this.supportedEngines.get(name);
    }

    public void addEngine(String name, JavaScriptEngine engine) {
        this.supportedEngines.put(name, engine);
    }

    public void removeEngine(String name) {
        this.supportedEngines.remove(name);
    }

    static {
        JavaScriptEngineFactory.Instance().addEngine(DEFAULT, new EMCAJavaScriptEngine());
        JavaScriptEngineFactory.Instance().addEngine("JSR223", new JSR223JavaScriptEngine());
        JavaScriptEngineFactory.Instance().addEngine("RHINO", new RhinoJavaScriptEngine());
    }
}

