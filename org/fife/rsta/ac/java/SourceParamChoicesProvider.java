/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.Icon;
import javax.swing.text.JTextComponent;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.FieldCompletion;
import org.fife.rsta.ac.java.JarManager;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.rsta.ac.java.JavaParser;
import org.fife.rsta.ac.java.JavaSourceCompletion;
import org.fife.rsta.ac.java.LocalVariableCompletion;
import org.fife.rsta.ac.java.MethodCompletion;
import org.fife.rsta.ac.java.rjc.ast.ASTNode;
import org.fife.rsta.ac.java.rjc.ast.CodeBlock;
import org.fife.rsta.ac.java.rjc.ast.CompilationUnit;
import org.fife.rsta.ac.java.rjc.ast.Field;
import org.fife.rsta.ac.java.rjc.ast.FormalParameter;
import org.fife.rsta.ac.java.rjc.ast.LocalVariable;
import org.fife.rsta.ac.java.rjc.ast.Member;
import org.fife.rsta.ac.java.rjc.ast.Method;
import org.fife.rsta.ac.java.rjc.ast.NormalClassDeclaration;
import org.fife.rsta.ac.java.rjc.ast.NormalInterfaceDeclaration;
import org.fife.rsta.ac.java.rjc.ast.Package;
import org.fife.rsta.ac.java.rjc.ast.TypeDeclaration;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.EmptyIcon;
import org.fife.ui.autocomplete.ParameterChoicesProvider;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

class SourceParamChoicesProvider
implements ParameterChoicesProvider {
    private CompletionProvider provider;

    SourceParamChoicesProvider() {
    }

    private void addPublicAndProtectedFieldsAndGetters(Type type, JarManager jm, Package pkg, List<Completion> list) {
    }

    public List<Completion> getLocalVarsFieldsAndGetters(NormalClassDeclaration ncd, String type, int offs) {
        Iterator<ASTNode> i;
        ArrayList<Completion> members = new ArrayList<Completion>();
        if (!ncd.getBodyContainsOffset(offs)) {
            return members;
        }
        Method method = ncd.getMethodContainingOffset(offs);
        if (method != null) {
            i = method.getParameterIterator();
            while (i.hasNext()) {
                FormalParameter param = (FormalParameter)i.next();
                Type paramType = param.getType();
                if (!this.isTypeCompatible(paramType, type)) continue;
                members.add(new LocalVariableCompletion(this.provider, param));
            }
            CodeBlock body = method.getBody();
            if (body != null) {
                CodeBlock block = body.getDeepestCodeBlockContaining(offs);
                List<LocalVariable> vars = block.getLocalVarsBefore(offs);
                for (LocalVariable var : vars) {
                    Type varType = var.getType();
                    if (!this.isTypeCompatible(varType, type)) continue;
                    members.add(new LocalVariableCompletion(this.provider, var));
                }
            }
        }
        i = ncd.getMemberIterator();
        while (i.hasNext()) {
            Member member = (Member)i.next();
            if (member instanceof Field) {
                Type fieldType = member.getType();
                if (!this.isTypeCompatible(fieldType, type)) continue;
                members.add(new FieldCompletion(this.provider, (Field)member));
                continue;
            }
            method = (Method)member;
            if (!this.isSimpleGetter(method) || !this.isTypeCompatible(method.getType(), type)) continue;
            members.add(new MethodCompletion(this.provider, method));
        }
        return members;
    }

    @Override
    public List<Completion> getParameterChoices(JTextComponent tc, ParameterizedCompletion.Parameter param) {
        Object typeObj;
        LanguageSupportFactory lsf = LanguageSupportFactory.get();
        LanguageSupport support = lsf.getSupportFor("text/java");
        JavaLanguageSupport jls = (JavaLanguageSupport)support;
        JarManager jm = jls.getJarManager();
        RSyntaxTextArea textArea = (RSyntaxTextArea)tc;
        JavaParser parser = jls.getParser(textArea);
        if (parser == null) {
            return null;
        }
        CompilationUnit cu = parser.getCompilationUnit();
        if (cu == null) {
            return null;
        }
        int dot = tc.getCaretPosition();
        TypeDeclaration typeDec = cu.getDeepestTypeDeclarationAtOffset(dot);
        if (typeDec == null) {
            return null;
        }
        List<Completion> list = null;
        Package pkg = typeDec.getPackage();
        this.provider = jls.getCompletionProvider(textArea);
        if (typeDec instanceof NormalClassDeclaration) {
            NormalClassDeclaration ncd = (NormalClassDeclaration)typeDec;
            list = this.getLocalVarsFieldsAndGetters(ncd, param.getType(), dot);
            Type extended = ncd.getExtendedType();
            if (extended != null) {
                this.addPublicAndProtectedFieldsAndGetters(extended, jm, pkg, list);
            }
            Iterator<Type> i = ncd.getImplementedIterator();
            while (i.hasNext()) {
                Type implemented = i.next();
                this.addPublicAndProtectedFieldsAndGetters(implemented, jm, pkg, list);
            }
        } else if (typeDec instanceof NormalInterfaceDeclaration) {
            // empty if block
        }
        if (!typeDec.isStatic()) {
            // empty if block
        }
        if ((typeObj = param.getTypeObject()) instanceof Type) {
            Type type = (Type)typeObj;
            if (type.isBasicType()) {
                if (this.isPrimitiveNumericType(type)) {
                    list.add(new SimpleCompletion(this.provider, "0"));
                } else {
                    list.add(new SimpleCompletion(this.provider, "false"));
                    list.add(new SimpleCompletion(this.provider, "true"));
                }
            } else {
                list.add(new SimpleCompletion(this.provider, "null"));
            }
        }
        return list;
    }

    private boolean isPrimitiveNumericType(Type type) {
        String str = type.getName(true);
        return "byte".equals(str) || "float".equals(str) || "double".equals(str) || "int".equals(str) || "short".equals(str) || "long".equals(str);
    }

    private boolean isSimpleGetter(Method method) {
        return method.getParameterCount() == 0 && method.getName().startsWith("get");
    }

    private boolean isTypeCompatible(Type type, String typeName) {
        String typeName2 = type.getName(false);
        int lt = typeName2.indexOf(60);
        if (lt > -1) {
            String arrayDepth = null;
            int brackets = typeName2.indexOf(91, lt);
            if (brackets > -1) {
                arrayDepth = typeName2.substring(brackets);
            }
            typeName2 = typeName2.substring(lt);
            if (arrayDepth != null) {
                typeName2 = typeName2 + arrayDepth;
            }
        }
        return typeName2.equalsIgnoreCase(typeName);
    }

    private static class SimpleCompletion
    extends BasicCompletion
    implements JavaSourceCompletion {
        private Icon ICON = new EmptyIcon(16);

        public SimpleCompletion(CompletionProvider provider, String text) {
            super(provider, text);
            this.setRelevance(-1);
        }

        @Override
        public Icon getIcon() {
            return this.ICON;
        }

        @Override
        public void rendererText(Graphics g, int x, int y, boolean selected) {
        }
    }
}

