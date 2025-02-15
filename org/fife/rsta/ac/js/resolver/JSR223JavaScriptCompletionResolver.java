/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.resolver;

import java.io.IOException;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.js.JavaScriptHelper;
import org.fife.rsta.ac.js.Logger;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.completion.JSMethodData;
import org.fife.rsta.ac.js.resolver.JavaScriptCompletionResolver;
import org.fife.rsta.ac.js.resolver.JavaScriptResolver;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.FunctionCall;
import org.mozilla.javascript.ast.PropertyGet;

public class JSR223JavaScriptCompletionResolver
extends JavaScriptCompletionResolver {
    public JSR223JavaScriptCompletionResolver(SourceCompletionProvider provider) {
        super(provider);
    }

    @Override
    protected TypeDeclaration resolveNativeType(AstNode node) {
        TypeDeclaration dec = super.resolveNativeType(node);
        if (dec == null) {
            dec = this.testJavaStaticType(node);
        }
        return dec;
    }

    @Override
    public String getLookupText(JSMethodData methodData, String name) {
        StringBuilder sb = new StringBuilder(name);
        sb.append('(');
        int count = methodData.getParameterCount();
        String[] parameterTypes = methodData.getMethodInfo().getParameterTypes();
        for (int i = 0; i < count; ++i) {
            String paramName = methodData.getParameterType(parameterTypes, i, this.provider);
            sb.append(paramName);
            if (i >= count - 1) continue;
            sb.append(",");
        }
        sb.append(')');
        return sb.toString();
    }

    @Override
    public String getFunctionNameLookup(FunctionCall call, SourceCompletionProvider provider) {
        if (call != null) {
            StringBuilder sb = new StringBuilder();
            if (call.getTarget() instanceof PropertyGet) {
                PropertyGet get = (PropertyGet)call.getTarget();
                sb.append(get.getProperty().getIdentifier());
            }
            sb.append("(");
            int count = call.getArguments().size();
            for (int i = 0; i < count; ++i) {
                AstNode paramNode = call.getArguments().get(i);
                JavaScriptResolver resolver = provider.getJavaScriptEngine().getJavaScriptResolver(provider);
                Logger.log("PARAM: " + JavaScriptHelper.convertNodeToSource(paramNode));
                try {
                    TypeDeclaration type = resolver.resolveParamNode(JavaScriptHelper.convertNodeToSource(paramNode));
                    String resolved = type != null ? type.getQualifiedName() : "any";
                    sb.append(resolved);
                    if (i >= count - 1) continue;
                    sb.append(",");
                    continue;
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
            sb.append(")");
            return sb.toString();
        }
        return null;
    }

    @Override
    protected TypeDeclaration findJavaStaticType(AstNode node) {
        String testName = null;
        if (node.getParent() != null && node.getParent().getType() == 33) {
            String name = node.toSource();
            try {
                int index;
                String longName = node.getParent().toSource();
                if (longName.indexOf(91) == -1 && longName.indexOf(93) == -1 && longName.indexOf(40) == -1 && longName.indexOf(41) == -1 && (index = longName.lastIndexOf(name)) > -1) {
                    testName = longName.substring(0, index + name.length());
                }
            } catch (Exception e) {
                Logger.log(e.getMessage());
            }
        } else {
            testName = node.toSource();
        }
        if (testName != null) {
            ClassFile cf;
            TypeDeclaration dec = JavaScriptHelper.getTypeDeclaration(testName, this.provider);
            if (dec == null) {
                dec = JavaScriptHelper.createNewTypeDeclaration(testName);
            }
            if ((cf = this.provider.getJavaScriptTypesFactory().getClassFile(this.provider.getJarManager(), dec)) != null) {
                TypeDeclaration returnDec = this.provider.getJavaScriptTypesFactory().createNewTypeDeclaration(cf, true, false);
                return returnDec;
            }
        }
        return null;
    }
}

