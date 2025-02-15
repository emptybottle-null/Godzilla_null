/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.fife.ui.rsyntaxtextarea.TokenMaker;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;

public abstract class AbstractTokenMakerFactory
extends TokenMakerFactory {
    private Map<String, Object> tokenMakerMap = new HashMap<String, Object>();

    protected AbstractTokenMakerFactory() {
        this.initTokenMakerMap();
    }

    @Override
    protected TokenMaker getTokenMakerImpl(String key) {
        TokenMakerCreator tmc = (TokenMakerCreator)this.tokenMakerMap.get(key);
        if (tmc != null) {
            try {
                return tmc.create();
            } catch (RuntimeException re) {
                throw re;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected abstract void initTokenMakerMap();

    @Override
    public Set<String> keySet() {
        return this.tokenMakerMap.keySet();
    }

    public void putMapping(String key, String className) {
        this.putMapping(key, className, null);
    }

    public void putMapping(String key, String className, ClassLoader cl) {
        this.tokenMakerMap.put(key, new TokenMakerCreator(className, cl));
    }

    private static class TokenMakerCreator {
        private String className;
        private ClassLoader cl;

        public TokenMakerCreator(String className, ClassLoader cl) {
            this.className = className;
            this.cl = cl != null ? cl : this.getClass().getClassLoader();
        }

        public TokenMaker create() throws Exception {
            return (TokenMaker)Class.forName(this.className, true, this.cl).newInstance();
        }
    }
}

