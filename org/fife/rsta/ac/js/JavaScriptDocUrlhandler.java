/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import org.fife.rsta.ac.java.classreader.ClassFile;
import org.fife.rsta.ac.java.classreader.FieldInfo;
import org.fife.rsta.ac.java.classreader.MethodInfo;
import org.fife.rsta.ac.js.JavaScriptLanguageSupport;
import org.fife.rsta.ac.js.Logger;
import org.fife.rsta.ac.js.completion.JSClassCompletion;
import org.fife.rsta.ac.js.completion.JSCompletion;
import org.fife.rsta.ac.js.completion.JSFieldCompletion;
import org.fife.rsta.ac.js.completion.JSFunctionCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.DescWindowCallback;
import org.fife.ui.autocomplete.ExternalURLHandler;
import org.fife.ui.autocomplete.Util;
import org.fife.ui.autocomplete.VariableCompletion;

public class JavaScriptDocUrlhandler
implements ExternalURLHandler {
    private JavaScriptLanguageSupport languageSupport;

    public JavaScriptDocUrlhandler(JavaScriptLanguageSupport languageSupport) {
        this.languageSupport = languageSupport;
    }

    private String getClass(Completion c, String desc) {
        String clazz = null;
        if (c instanceof JSClassCompletion) {
            clazz = ((JSClassCompletion)c).getClassName(true);
        } else if (c instanceof JSCompletion) {
            JSCompletion jsc = (JSCompletion)c;
            clazz = jsc.getEnclosingClassName(true);
        } else {
            Logger.logError("Can't determine class from completion type: " + c.getClass() + " (" + c.toString() + ") - href: " + desc);
        }
        return clazz;
    }

    private String getPackage(Completion c, String desc) {
        String pkg = null;
        if (c instanceof JSClassCompletion) {
            pkg = ((JSClassCompletion)c).getPackageName();
        }
        if (c instanceof JSCompletion) {
            int lastDot;
            String definedIn = ((JSCompletion)c).getEnclosingClassName(true);
            if (definedIn != null && (lastDot = definedIn.lastIndexOf(46)) > -1) {
                pkg = definedIn.substring(0, lastDot);
            }
        } else {
            Logger.logError("Can't determine package from completion type: " + c.getClass() + " (" + c.toString() + ") - href: " + desc);
        }
        return pkg;
    }

    private boolean isRelativeUrl(String text) {
        String[] EXTS = new String[]{".html", ".htm"};
        for (int i = 0; i < EXTS.length; ++i) {
            if (!text.endsWith(EXTS[i]) && !text.contains(EXTS[i] + "#") && !text.contains(EXTS[i] + "?")) continue;
            return true;
        }
        return false;
    }

    private String doBackups(String pkg, int backupCount) {
        int lastDot = pkg.length();
        while (lastDot > -1 && backupCount > 0) {
            lastDot = pkg.lastIndexOf(46, lastDot);
            --backupCount;
        }
        return lastDot > -1 ? pkg.substring(0, lastDot) : "";
    }

    private JavaScriptLanguageSupport getJavaScriptLanguageSupport() {
        return this.languageSupport;
    }

    private static final String getAnchor(String url) {
        int pound = url.indexOf(35);
        return pound > -1 ? url.substring(pound + 1) : null;
    }

    private static final String[] getArgs(String methodSignature) {
        int rparen;
        String[] args = null;
        int lparen = methodSignature.indexOf(40);
        if (lparen > -1 && (rparen = methodSignature.indexOf(41, lparen)) > -1 && rparen > lparen + 1) {
            String temp = methodSignature.substring(lparen, rparen);
            args = temp.split("\\s*,\\s*");
        }
        if (args == null) {
            args = new String[]{};
        }
        return args;
    }

    @Override
    public void urlClicked(HyperlinkEvent e, Completion c, DescWindowCallback callback) {
        URL url = e.getURL();
        if (url != null) {
            try {
                Util.browse(new URI(url.toString()));
            } catch (URISyntaxException ioe) {
                UIManager.getLookAndFeel().provideErrorFeedback(null);
                ioe.printStackTrace();
            }
            return;
        }
        String desc = e.getDescription();
        Logger.log(desc);
        if (desc != null) {
            if (this.isRelativeUrl(desc)) {
                int ext = desc.indexOf(".htm");
                if (ext > -1) {
                    String anchor = JavaScriptDocUrlhandler.getAnchor(desc);
                    String clazz = desc.substring(0, ext);
                    int backups = 0;
                    while (clazz.startsWith("../")) {
                        ++backups;
                        clazz = clazz.substring(3);
                    }
                    clazz = clazz.replace('/', '.');
                    String pkg = this.getPackage(c, desc);
                    if (pkg != null) {
                        clazz = this.doBackups(pkg, backups) + "." + clazz;
                        JavaScriptLanguageSupport jls = this.getJavaScriptLanguageSupport();
                        ClassFile cf = jls.getJarManager().getClassEntry(clazz);
                        if (cf != null) {
                            JSClassCompletion cc = new JSClassCompletion(c.getProvider(), cf, true);
                            callback.showSummaryFor(cc, anchor);
                        }
                    }
                }
            } else {
                JavaScriptLanguageSupport jls = this.getJavaScriptLanguageSupport();
                String clazz = desc;
                String member = null;
                int pound = desc.indexOf(35);
                if (pound > -1) {
                    member = clazz.substring(pound + 1);
                    clazz = clazz.substring(0, pound);
                }
                if (member == null) {
                    ClassFile cf;
                    boolean guessedPackage = false;
                    if (clazz.indexOf(46) == -1) {
                        String pkg = this.getPackage(c, desc);
                        if (pkg != null) {
                            clazz = pkg + "." + clazz;
                        }
                        guessedPackage = true;
                    }
                    if ((cf = jls.getJarManager().getClassEntry(clazz)) == null && guessedPackage) {
                        int lastDot = clazz.lastIndexOf(46);
                        clazz = "java.lang." + clazz.substring(lastDot + 1);
                        cf = jls.getJarManager().getClassEntry(clazz);
                    }
                    if (cf != null) {
                        JSClassCompletion cc = new JSClassCompletion(c.getProvider(), cf, true);
                        callback.showSummaryFor(cc, null);
                    } else {
                        UIManager.getLookAndFeel().provideErrorFeedback(null);
                        Logger.log("Unknown class: " + clazz);
                    }
                } else {
                    ClassFile cf;
                    boolean guessedPackage = false;
                    if (pound == 0) {
                        clazz = this.getClass(c, desc);
                    } else if (clazz.indexOf(46) == -1) {
                        String pkg = this.getPackage(c, desc);
                        if (pkg != null) {
                            clazz = pkg + "." + clazz;
                        }
                        guessedPackage = true;
                    }
                    ClassFile classFile = cf = clazz != null ? jls.getJarManager().getClassEntry(clazz) : null;
                    if (cf == null && guessedPackage) {
                        int lastDot = clazz.lastIndexOf(46);
                        clazz = "java.lang." + clazz.substring(lastDot + 1);
                        cf = jls.getJarManager().getClassEntry(clazz);
                    }
                    if (cf != null) {
                        VariableCompletion memberCompletion = null;
                        int lparen = member.indexOf(40);
                        if (lparen == -1) {
                            FieldInfo fi = cf.getFieldInfoByName(member);
                            if (fi != null) {
                                memberCompletion = new JSFieldCompletion(c.getProvider(), fi);
                            } else {
                                List<MethodInfo> miList = cf.getMethodInfoByName(member, -1);
                                if (miList != null && miList.size() > 0) {
                                    MethodInfo mi = miList.get(0);
                                    memberCompletion = new JSFunctionCompletion(c.getProvider(), mi);
                                }
                            }
                        } else {
                            String[] args = JavaScriptDocUrlhandler.getArgs(member);
                            String methodName = member.substring(0, lparen);
                            List<MethodInfo> miList = cf.getMethodInfoByName(methodName, args.length);
                            if (miList != null && miList.size() > 0) {
                                if (miList.size() > 1) {
                                    Logger.log("Multiple overload support not yet implemented");
                                } else {
                                    MethodInfo mi = miList.get(0);
                                    memberCompletion = new JSFunctionCompletion(c.getProvider(), mi);
                                }
                            }
                        }
                        if (memberCompletion != null) {
                            callback.showSummaryFor(memberCompletion, null);
                        }
                    } else {
                        UIManager.getLookAndFeel().provideErrorFeedback(null);
                        Logger.logError("Unknown class: " + clazz + " (href: " + desc + ")");
                    }
                }
            }
        }
    }
}

