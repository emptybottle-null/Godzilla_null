/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlTokenSource;

public interface XmlCursor
extends XmlTokenSource {
    public void dispose();

    public boolean toCursor(XmlCursor var1);

    public void push();

    public boolean pop();

    public void selectPath(String var1);

    public void selectPath(String var1, XmlOptions var2);

    public boolean hasNextSelection();

    public boolean toNextSelection();

    public boolean toSelection(int var1);

    public int getSelectionCount();

    public void addToSelection();

    public void clearSelections();

    public boolean toBookmark(XmlBookmark var1);

    public XmlBookmark toNextBookmark(Object var1);

    public XmlBookmark toPrevBookmark(Object var1);

    public QName getName();

    public void setName(QName var1);

    public String namespaceForPrefix(String var1);

    public String prefixForNamespace(String var1);

    public void getAllNamespaces(Map var1);

    public XmlObject getObject();

    public TokenType currentTokenType();

    public boolean isStartdoc();

    public boolean isEnddoc();

    public boolean isStart();

    public boolean isEnd();

    public boolean isText();

    public boolean isAttr();

    public boolean isNamespace();

    public boolean isComment();

    public boolean isProcinst();

    public boolean isContainer();

    public boolean isFinish();

    public boolean isAnyAttr();

    public TokenType prevTokenType();

    public boolean hasNextToken();

    public boolean hasPrevToken();

    public TokenType toNextToken();

    public TokenType toPrevToken();

    public TokenType toFirstContentToken();

    public TokenType toEndToken();

    public int toNextChar(int var1);

    public int toPrevChar(int var1);

    public boolean toNextSibling();

    public boolean toPrevSibling();

    public boolean toParent();

    public boolean toFirstChild();

    public boolean toLastChild();

    public boolean toChild(String var1);

    public boolean toChild(String var1, String var2);

    public boolean toChild(QName var1);

    public boolean toChild(int var1);

    public boolean toChild(QName var1, int var2);

    public boolean toNextSibling(String var1);

    public boolean toNextSibling(String var1, String var2);

    public boolean toNextSibling(QName var1);

    public boolean toFirstAttribute();

    public boolean toLastAttribute();

    public boolean toNextAttribute();

    public boolean toPrevAttribute();

    public String getAttributeText(QName var1);

    public boolean setAttributeText(QName var1, String var2);

    public boolean removeAttribute(QName var1);

    public String getTextValue();

    public int getTextValue(char[] var1, int var2, int var3);

    public void setTextValue(String var1);

    public void setTextValue(char[] var1, int var2, int var3);

    public String getChars();

    public int getChars(char[] var1, int var2, int var3);

    public void toStartDoc();

    public void toEndDoc();

    public boolean isInSameDocument(XmlCursor var1);

    public int comparePosition(XmlCursor var1);

    public boolean isLeftOf(XmlCursor var1);

    public boolean isAtSamePositionAs(XmlCursor var1);

    public boolean isRightOf(XmlCursor var1);

    public XmlCursor execQuery(String var1);

    public XmlCursor execQuery(String var1, XmlOptions var2);

    public ChangeStamp getDocChangeStamp();

    public void setBookmark(XmlBookmark var1);

    public XmlBookmark getBookmark(Object var1);

    public void clearBookmark(Object var1);

    public void getAllBookmarkRefs(Collection var1);

    public boolean removeXml();

    public boolean moveXml(XmlCursor var1);

    public boolean copyXml(XmlCursor var1);

    public boolean removeXmlContents();

    public boolean moveXmlContents(XmlCursor var1);

    public boolean copyXmlContents(XmlCursor var1);

    public int removeChars(int var1);

    public int moveChars(int var1, XmlCursor var2);

    public int copyChars(int var1, XmlCursor var2);

    public void insertChars(String var1);

    public void insertElement(QName var1);

    public void insertElement(String var1);

    public void insertElement(String var1, String var2);

    public void beginElement(QName var1);

    public void beginElement(String var1);

    public void beginElement(String var1, String var2);

    public void insertElementWithText(QName var1, String var2);

    public void insertElementWithText(String var1, String var2);

    public void insertElementWithText(String var1, String var2, String var3);

    public void insertAttribute(String var1);

    public void insertAttribute(String var1, String var2);

    public void insertAttribute(QName var1);

    public void insertAttributeWithValue(String var1, String var2);

    public void insertAttributeWithValue(String var1, String var2, String var3);

    public void insertAttributeWithValue(QName var1, String var2);

    public void insertNamespace(String var1, String var2);

    public void insertComment(String var1);

    public void insertProcInst(String var1, String var2);

    public static interface XmlMark {
        public XmlCursor createCursor();
    }

    public static abstract class XmlBookmark {
        public XmlMark _currentMark;
        public final Reference _ref;

        public XmlBookmark() {
            this(false);
        }

        public XmlBookmark(boolean weak) {
            this._ref = weak ? new WeakReference<XmlBookmark>(this) : null;
        }

        public final XmlCursor createCursor() {
            return this._currentMark == null ? null : this._currentMark.createCursor();
        }

        public final XmlCursor toBookmark(XmlCursor c) {
            return c == null || !c.toBookmark(this) ? this.createCursor() : c;
        }

        public Object getKey() {
            return this.getClass();
        }
    }

    public static interface ChangeStamp {
        public boolean hasChanged();
    }

    public static final class TokenType {
        public static final int INT_NONE = 0;
        public static final int INT_STARTDOC = 1;
        public static final int INT_ENDDOC = 2;
        public static final int INT_START = 3;
        public static final int INT_END = 4;
        public static final int INT_TEXT = 5;
        public static final int INT_ATTR = 6;
        public static final int INT_NAMESPACE = 7;
        public static final int INT_COMMENT = 8;
        public static final int INT_PROCINST = 9;
        public static final TokenType NONE = new TokenType("NONE", 0);
        public static final TokenType STARTDOC = new TokenType("STARTDOC", 1);
        public static final TokenType ENDDOC = new TokenType("ENDDOC", 2);
        public static final TokenType START = new TokenType("START", 3);
        public static final TokenType END = new TokenType("END", 4);
        public static final TokenType TEXT = new TokenType("TEXT", 5);
        public static final TokenType ATTR = new TokenType("ATTR", 6);
        public static final TokenType NAMESPACE = new TokenType("NAMESPACE", 7);
        public static final TokenType COMMENT = new TokenType("COMMENT", 8);
        public static final TokenType PROCINST = new TokenType("PROCINST", 9);
        private String _name;
        private int _value;

        public String toString() {
            return this._name;
        }

        public int intValue() {
            return this._value;
        }

        public boolean isNone() {
            return this == NONE;
        }

        public boolean isStartdoc() {
            return this == STARTDOC;
        }

        public boolean isEnddoc() {
            return this == ENDDOC;
        }

        public boolean isStart() {
            return this == START;
        }

        public boolean isEnd() {
            return this == END;
        }

        public boolean isText() {
            return this == TEXT;
        }

        public boolean isAttr() {
            return this == ATTR;
        }

        public boolean isNamespace() {
            return this == NAMESPACE;
        }

        public boolean isComment() {
            return this == COMMENT;
        }

        public boolean isProcinst() {
            return this == PROCINST;
        }

        public boolean isContainer() {
            return this == STARTDOC || this == START;
        }

        public boolean isFinish() {
            return this == ENDDOC || this == END;
        }

        public boolean isAnyAttr() {
            return this == NAMESPACE || this == ATTR;
        }

        private TokenType(String name, int value) {
            this._name = name;
            this._value = value;
        }
    }
}

