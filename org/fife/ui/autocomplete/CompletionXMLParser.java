/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.autocomplete;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import org.fife.ui.autocomplete.AbstractCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.Completion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.autocomplete.MarkupTagCompletion;
import org.fife.ui.autocomplete.ParameterizedCompletion;
import org.fife.ui.autocomplete.VariableCompletion;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class CompletionXMLParser
extends DefaultHandler {
    private List<Completion> completions;
    private CompletionProvider provider;
    private ClassLoader completionCL;
    private String name;
    private String type;
    private String returnType;
    private StringBuilder returnValDesc;
    private StringBuilder desc;
    private String paramName;
    private String paramType;
    private boolean endParam;
    private StringBuilder paramDesc;
    private List<ParameterizedCompletion.Parameter> params;
    private String definedIn;
    private boolean doingKeywords;
    private boolean inKeyword;
    private boolean gettingReturnValDesc;
    private boolean gettingDesc;
    private boolean gettingParams;
    private boolean inParam;
    private boolean gettingParamDesc;
    private boolean inCompletionTypes;
    private char paramStartChar;
    private char paramEndChar;
    private String paramSeparator;
    private String funcCompletionType;
    private static ClassLoader defaultCompletionClassLoader;

    public CompletionXMLParser(CompletionProvider provider) {
        this(provider, null);
    }

    public CompletionXMLParser(CompletionProvider provider, ClassLoader cl) {
        this.provider = provider;
        this.completionCL = cl;
        if (this.completionCL == null) {
            this.completionCL = defaultCompletionClassLoader;
        }
        this.completions = new ArrayList<Completion>();
        this.params = new ArrayList<ParameterizedCompletion.Parameter>(1);
        this.desc = new StringBuilder();
        this.paramDesc = new StringBuilder();
        this.returnValDesc = new StringBuilder();
        this.paramEndChar = '\u0000';
        this.paramStartChar = '\u0000';
        this.paramSeparator = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (this.gettingDesc) {
            this.desc.append(ch, start, length);
        } else if (this.gettingParamDesc) {
            this.paramDesc.append(ch, start, length);
        } else if (this.gettingReturnValDesc) {
            this.returnValDesc.append(ch, start, length);
        }
    }

    private FunctionCompletion createFunctionCompletion() {
        FunctionCompletion fc = null;
        if (this.funcCompletionType != null) {
            try {
                Class<?> clazz = this.completionCL != null ? Class.forName(this.funcCompletionType, true, this.completionCL) : Class.forName(this.funcCompletionType);
                Constructor<?> c = clazz.getDeclaredConstructor(CompletionProvider.class, String.class, String.class);
                fc = (FunctionCompletion)c.newInstance(this.provider, this.name, this.returnType);
            } catch (RuntimeException re) {
                throw re;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fc == null) {
            fc = new FunctionCompletion(this.provider, this.name, this.returnType);
        }
        if (this.desc.length() > 0) {
            fc.setShortDescription(this.desc.toString());
            this.desc.setLength(0);
        }
        fc.setParams(this.params);
        fc.setDefinedIn(this.definedIn);
        if (this.returnValDesc.length() > 0) {
            fc.setReturnValueDescription(this.returnValDesc.toString());
            this.returnValDesc.setLength(0);
        }
        return fc;
    }

    private BasicCompletion createOtherCompletion() {
        BasicCompletion bc = new BasicCompletion(this.provider, this.name);
        if (this.desc.length() > 0) {
            bc.setSummary(this.desc.toString());
            this.desc.setLength(0);
        }
        return bc;
    }

    private MarkupTagCompletion createMarkupTagCompletion() {
        MarkupTagCompletion mc = new MarkupTagCompletion(this.provider, this.name);
        if (this.desc.length() > 0) {
            mc.setDescription(this.desc.toString());
            this.desc.setLength(0);
        }
        mc.setAttributes(this.params);
        mc.setDefinedIn(this.definedIn);
        return mc;
    }

    private VariableCompletion createVariableCompletion() {
        VariableCompletion vc = new VariableCompletion(this.provider, this.name, this.returnType);
        if (this.desc.length() > 0) {
            vc.setShortDescription(this.desc.toString());
            this.desc.setLength(0);
        }
        vc.setDefinedIn(this.definedIn);
        return vc;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("keywords".equals(qName)) {
            this.doingKeywords = false;
        } else if (this.doingKeywords) {
            if ("keyword".equals(qName)) {
                AbstractCompletion c;
                if ("function".equals(this.type)) {
                    c = this.createFunctionCompletion();
                } else if ("constant".equals(this.type)) {
                    c = this.createVariableCompletion();
                } else if ("tag".equals(this.type)) {
                    c = this.createMarkupTagCompletion();
                } else if ("other".equals(this.type)) {
                    c = this.createOtherCompletion();
                } else {
                    throw new InternalError("Unexpected type: " + this.type);
                }
                this.completions.add(c);
                this.inKeyword = false;
            } else if (this.inKeyword) {
                if ("returnValDesc".equals(qName)) {
                    this.gettingReturnValDesc = false;
                } else if (this.gettingParams) {
                    if ("params".equals(qName)) {
                        this.gettingParams = false;
                    } else if ("param".equals(qName)) {
                        ParameterizedCompletion.Parameter param = new ParameterizedCompletion.Parameter(this.paramType, this.paramName, this.endParam);
                        if (this.paramDesc.length() > 0) {
                            param.setDescription(this.paramDesc.toString());
                            this.paramDesc.setLength(0);
                        }
                        this.params.add(param);
                        this.inParam = false;
                    } else if (this.inParam && "desc".equals(qName)) {
                        this.gettingParamDesc = false;
                    }
                } else if ("desc".equals(qName)) {
                    this.gettingDesc = false;
                }
            }
        } else if (this.inCompletionTypes && "completionTypes".equals(qName)) {
            this.inCompletionTypes = false;
        }
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }

    public List<Completion> getCompletions() {
        return this.completions;
    }

    public char getParamEndChar() {
        return this.paramEndChar;
    }

    public String getParamSeparator() {
        return this.paramSeparator;
    }

    public char getParamStartChar() {
        return this.paramStartChar;
    }

    private static char getSingleChar(String str) {
        return str.length() == 1 ? str.charAt(0) : (char)'\u0000';
    }

    public void reset(CompletionProvider provider) {
        this.provider = provider;
        this.completions.clear();
        this.gettingParamDesc = false;
        this.inParam = false;
        this.gettingParams = false;
        this.gettingDesc = false;
        this.inKeyword = false;
        this.doingKeywords = false;
        this.paramEndChar = '\u0000';
        this.paramStartChar = '\u0000';
        this.paramSeparator = null;
    }

    @Override
    public InputSource resolveEntity(String publicID, String systemID) throws SAXException {
        return new InputSource(this.getClass().getResourceAsStream("CompletionXml.dtd"));
    }

    public static void setDefaultCompletionClassLoader(ClassLoader cl) {
        defaultCompletionClassLoader = cl;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("keywords".equals(qName)) {
            this.doingKeywords = true;
        } else if (this.doingKeywords) {
            if ("keyword".equals(qName)) {
                this.name = attrs.getValue("name");
                this.type = attrs.getValue("type");
                this.returnType = attrs.getValue("returnType");
                this.params.clear();
                this.definedIn = attrs.getValue("definedIn");
                this.inKeyword = true;
            } else if (this.inKeyword) {
                if ("returnValDesc".equals(qName)) {
                    this.gettingReturnValDesc = true;
                } else if ("params".equals(qName)) {
                    this.gettingParams = true;
                } else if (this.gettingParams) {
                    if ("param".equals(qName)) {
                        this.paramName = attrs.getValue("name");
                        this.paramType = attrs.getValue("type");
                        this.endParam = Boolean.parseBoolean(attrs.getValue("endParam"));
                        this.inParam = true;
                    }
                    if (this.inParam && "desc".equals(qName)) {
                        this.gettingParamDesc = true;
                    }
                } else if ("desc".equals(qName)) {
                    this.gettingDesc = true;
                }
            }
        } else if ("environment".equals(qName)) {
            this.paramStartChar = CompletionXMLParser.getSingleChar(attrs.getValue("paramStartChar"));
            this.paramEndChar = CompletionXMLParser.getSingleChar(attrs.getValue("paramEndChar"));
            this.paramSeparator = attrs.getValue("paramSeparator");
        } else if ("completionTypes".equals(qName)) {
            this.inCompletionTypes = true;
        } else if (this.inCompletionTypes && "functionCompletionType".equals(qName)) {
            this.funcCompletionType = attrs.getValue("type");
        }
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        throw e;
    }
}

