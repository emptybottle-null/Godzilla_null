/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package shells.encoder.java;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.ArrayAccessExpr;
import com.github.javaparser.ast.expr.ArrayInitializerExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.EnclosedExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import sun.misc.BASE64Encoder;

public class another_encode {
    public static String encryption(String str, int offset) {
        StringBuilder str1 = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char)((c - 97 + offset) % 26 + 97);
            } else if (c >= 'A' && c <= 'Z') {
                c = (char)((c - 65 + offset) % 26 + 65);
            } else if (c >= '0' && c <= '9') {
                c = (char)((c - 48 + offset) % 10 + 48);
            } else {
                str1 = new StringBuilder(str);
                break;
            }
            str1.append(c);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(str1.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static int encodeString(MethodDeclaration method) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int offset = random.nextInt(10);
        List<StringLiteralExpr> stringList = method.findAll(StringLiteralExpr.class);
        for (StringLiteralExpr s : stringList) {
            if (!s.getParentNode().isPresent() || !(s.getParentNode().get() instanceof ArrayInitializerExpr)) continue;
            s.setValue(another_encode.encryption(s.getValue(), offset));
        }
        return offset;
    }

    public static void changeRef(MethodDeclaration method, int offset) {
        List<ArrayAccessExpr> arrayExpr = method.findAll(ArrayAccessExpr.class);
        for (ArrayAccessExpr expr : arrayExpr) {
            if (!expr.getName().asNameExpr().getNameAsString().equals("globalArr")) continue;
            MethodCallExpr methodCallExpr = new MethodCallExpr();
            methodCallExpr.setName("dec");
            methodCallExpr.setScope(null);
            NodeList<Expression> nodeList = new NodeList<Expression>();
            ArrayAccessExpr a = new ArrayAccessExpr();
            a.setName(expr.getName());
            a.setIndex(expr.getIndex());
            nodeList.add(a);
            IntegerLiteralExpr intValue = new IntegerLiteralExpr();
            intValue.setValue(String.valueOf(offset));
            nodeList.add(intValue);
            methodCallExpr.setArguments((NodeList)nodeList);
            expr.replace(methodCallExpr);
        }
    }

    public static String string_do_Identify_XOR(String code) {
        String final_code = code;
        if (code.contains("defineClass")) {
            final_code = "public class test1{" + final_code + "}";
            CompilationUnit compilationUnit1 = StaticJavaParser.parse(final_code);
            List<MethodDeclaration> methods1 = compilationUnit1.findAll(MethodDeclaration.class);
            for (MethodDeclaration method2 : methods1) {
                int offset = another_encode.encodeString(method2);
                another_encode.changeRef(method2, offset);
                another_encode.doIdentify(method2);
                another_encode.doXOR(method2);
                another_encode.doXOR(method2);
            }
            String modifiedCode = compilationUnit1.toString();
            int lastIndex = modifiedCode.lastIndexOf("}");
            final_code = modifiedCode.substring(24, lastIndex);
        } else {
            final_code = "public class test1{public void test2(){" + final_code + "}}";
            CompilationUnit compilationUnit = StaticJavaParser.parse(final_code);
            MethodDeclaration test1_method = compilationUnit.findFirst(MethodDeclaration.class, method -> method.getName().getIdentifier().equals("test2")).orElse(null);
            if (test1_method != null) {
                int offset1 = another_encode.encodeString(test1_method);
                another_encode.changeRef(test1_method, offset1);
                another_encode.doIdentify(test1_method);
                another_encode.doXOR(test1_method);
                another_encode.doXOR(test1_method);
                final_code = test1_method.toString();
            }
            int lastIndex = final_code.lastIndexOf("}");
            final_code = final_code.substring(25, lastIndex);
        }
        return final_code;
    }

    public static void doIdentify(MethodDeclaration method) {
        HashMap<String, String> vas = new HashMap<String, String>();
        List<VariableDeclarator> vaList = method.findAll(VariableDeclarator.class);
        for (VariableDeclarator va : vaList) {
            String newName = another_encode.getRandomString(20);
            vas.put(va.getNameAsString(), newName);
            va.setName(newName);
        }
        method.findAll(NameExpr.class).forEach(n -> {
            if (vas.containsKey(n.getNameAsString())) {
                n.setName((String)vas.get(n.getNameAsString()));
            }
        });
    }

    public static String getRandomString(int length) {
        try {
            String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            Random random = new Random();
            random.setSeed(System.currentTimeMillis());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; ++i) {
                int number = random.nextInt(52);
                sb.append(str.charAt(number));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void doXOR(MethodDeclaration method) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        List<IntegerLiteralExpr> integers = method.findAll(IntegerLiteralExpr.class);
        for (IntegerLiteralExpr i : integers) {
            int value = Integer.parseInt(i.getValue());
            int key = random.nextInt(1000000) + 1000000;
            int cipherNum = value ^ key;
            EnclosedExpr enclosedExpr = new EnclosedExpr();
            BinaryExpr binaryExpr = new BinaryExpr();
            binaryExpr.setLeft(new IntegerLiteralExpr(String.valueOf(cipherNum)));
            binaryExpr.setRight(new IntegerLiteralExpr(String.valueOf(key)));
            binaryExpr.setOperator(BinaryExpr.Operator.XOR);
            enclosedExpr.setInner(binaryExpr);
            i.replace(enclosedExpr);
        }
    }
}

