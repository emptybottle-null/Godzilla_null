/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast.jsType;

import java.util.HashMap;
import java.util.HashSet;
import org.fife.rsta.ac.js.Logger;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.jsType.JavaScriptFunctionType;
import org.fife.rsta.ac.js.ast.jsType.JavaScriptType;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.completion.JSCompletion;
import org.fife.ui.autocomplete.FunctionCompletion;

public class JSR223Type
extends JavaScriptType {
    public JSR223Type(TypeDeclaration type) {
        super(type);
    }

    @Override
    protected JSCompletion _getCompletion(String completionLookup, SourceCompletionProvider provider) {
        JSCompletion completion = (JSCompletion)this.methodFieldCompletions.get(completionLookup);
        if (completion != null) {
            return completion;
        }
        if (completionLookup.indexOf(40) != -1) {
            boolean isJavaScriptType = provider.getTypesFactory().isJavaScriptType(this.getType());
            Logger.log("Completion Lookup : " + completionLookup);
            JavaScriptFunctionType javaScriptFunctionType = JavaScriptFunctionType.parseFunction(completionLookup, provider);
            JSCompletion[] matches = this.getPotentialLookupList(javaScriptFunctionType.getName());
            int bestFitIndex = -1;
            int bestFitWeight = -1;
            Logger.log("Potential matches : " + matches.length);
            for (int i = 0; i < matches.length; ++i) {
                Logger.log("Potential match : " + matches[i].getLookupName());
                JavaScriptFunctionType matchFunctionType = JavaScriptFunctionType.parseFunction(matches[i].getLookupName(), provider);
                Logger.log("Matching against completion: " + completionLookup);
                int weight = matchFunctionType.compare(javaScriptFunctionType, provider, isJavaScriptType);
                Logger.log("Weight: " + weight);
                if (weight >= JavaScriptFunctionType.CONVERSION_NONE || weight >= bestFitWeight && bestFitIndex != -1) continue;
                bestFitIndex = i;
                bestFitWeight = weight;
            }
            if (bestFitIndex > -1) {
                Logger.log("BEST FIT: " + matches[bestFitIndex].getLookupName());
                return matches[bestFitIndex];
            }
        }
        return null;
    }

    private JSCompletion[] getPotentialLookupList(String name) {
        HashSet<JSCompletion> completionMatches = new HashSet<JSCompletion>();
        this.getPotentialLookupList(name, completionMatches, this);
        return completionMatches.toArray(new JSCompletion[0]);
    }

    private void getPotentialLookupList(String name, HashSet<JSCompletion> completionMatches, JavaScriptType type) {
        HashMap<String, JSCompletion> typeCompletions = type.methodFieldCompletions;
        for (String key : typeCompletions.keySet()) {
            JSCompletion completion;
            if (!key.startsWith(name) || !((completion = (JSCompletion)typeCompletions.get(key)) instanceof FunctionCompletion)) continue;
            completionMatches.add(completion);
        }
        for (JavaScriptType extendedType : type.getExtendedClasses()) {
            this.getPotentialLookupList(name, completionMatches, extendedType);
        }
    }
}

