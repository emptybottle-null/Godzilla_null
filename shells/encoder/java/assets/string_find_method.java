/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.encoder.java.assets;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.util.ArrayList;
import java.util.List;

public class string_find_method {
    public static List<MethodDeclaration> parseMethods(String code) {
        ArrayList<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
        CompilationUnit compilationUnit = StaticJavaParser.parse(code);
        List<MethodDeclaration> methodDeclarations = compilationUnit.findAll(MethodDeclaration.class);
        methods.addAll(methodDeclarations);
        return methods;
    }
}

