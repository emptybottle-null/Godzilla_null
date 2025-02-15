/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import org.fife.io.UnicodeReader;
import org.fife.io.UnicodeWriter;
import org.fife.ui.rsyntaxtextarea.FileLocation;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class TextEditorPane
extends RSyntaxTextArea
implements DocumentListener {
    private static final long serialVersionUID = 1L;
    public static final String FULL_PATH_PROPERTY = "TextEditorPane.fileFullPath";
    public static final String DIRTY_PROPERTY = "TextEditorPane.dirty";
    public static final String READ_ONLY_PROPERTY = "TextEditorPane.readOnly";
    public static final String ENCODING_PROPERTY = "TextEditorPane.encoding";
    private FileLocation loc;
    private String charSet;
    private boolean readOnly;
    private boolean dirty;
    private long lastSaveOrLoadTime;
    public static final long LAST_MODIFIED_UNKNOWN = 0L;
    private static final String DEFAULT_FILE_NAME = "Untitled.txt";

    public TextEditorPane() {
        this(0);
    }

    public TextEditorPane(int textMode) {
        this(textMode, false);
    }

    public TextEditorPane(int textMode, boolean wordWrapEnabled) {
        super(textMode);
        this.setLineWrap(wordWrapEnabled);
        try {
            this.init(null, null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public TextEditorPane(int textMode, boolean wordWrapEnabled, FileLocation loc) throws IOException {
        this(textMode, wordWrapEnabled, loc, null);
    }

    public TextEditorPane(int textMode, boolean wordWrapEnabled, FileLocation loc, String defaultEnc) throws IOException {
        super(textMode);
        this.setLineWrap(wordWrapEnabled);
        this.init(loc, defaultEnc);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }

    private static String getDefaultEncoding() {
        return Charset.defaultCharset().name();
    }

    public String getEncoding() {
        return this.charSet;
    }

    public String getFileFullPath() {
        return this.loc == null ? null : this.loc.getFileFullPath();
    }

    public String getFileName() {
        return this.loc == null ? null : this.loc.getFileName();
    }

    public long getLastSaveOrLoadTime() {
        return this.lastSaveOrLoadTime;
    }

    public Object getLineSeparator() {
        return this.getDocument().getProperty("__EndOfLine__");
    }

    private void init(FileLocation loc, String defaultEnc) throws IOException {
        if (loc == null) {
            this.loc = FileLocation.create(DEFAULT_FILE_NAME);
            this.charSet = defaultEnc == null ? TextEditorPane.getDefaultEncoding() : defaultEnc;
            this.setLineSeparator(System.getProperty("line.separator"));
        } else {
            this.load(loc, defaultEnc);
        }
        if (this.loc.isLocalAndExists()) {
            File file = new File(this.loc.getFileFullPath());
            this.lastSaveOrLoadTime = file.lastModified();
            this.setReadOnly(!file.canWrite());
        } else {
            this.lastSaveOrLoadTime = 0L;
            this.setReadOnly(false);
        }
        this.setDirty(false);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (!this.dirty) {
            this.setDirty(true);
        }
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public boolean isLocal() {
        return this.loc.isLocal();
    }

    public boolean isLocalAndExists() {
        return this.loc.isLocalAndExists();
    }

    public boolean isModifiedOutsideEditor() {
        return this.loc.getActualLastModified() > this.getLastSaveOrLoadTime();
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void load(FileLocation loc) throws IOException {
        this.load(loc, (String)null);
    }

    public void load(FileLocation loc, Charset defaultEnc) throws IOException {
        this.load(loc, defaultEnc == null ? null : defaultEnc.name());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void load(FileLocation loc, String defaultEnc) throws IOException {
        if (loc.isLocal() && !loc.isLocalAndExists()) {
            this.charSet = defaultEnc != null ? defaultEnc : TextEditorPane.getDefaultEncoding();
            this.loc = loc;
            this.setText(null);
            this.discardAllEdits();
            this.setDirty(false);
            return;
        }
        UnicodeReader ur = new UnicodeReader(loc.getInputStream(), defaultEnc);
        Document doc = this.getDocument();
        doc.removeDocumentListener(this);
        try (BufferedReader r = new BufferedReader(ur);){
            this.read(r, null);
        } finally {
            doc.addDocumentListener(this);
        }
        this.charSet = ur.getEncoding();
        String old = this.getFileFullPath();
        this.loc = loc;
        this.setDirty(false);
        this.setCaretPosition(0);
        this.discardAllEdits();
        this.firePropertyChange(FULL_PATH_PROPERTY, old, this.getFileFullPath());
    }

    public void reload() throws IOException {
        String oldEncoding = this.getEncoding();
        UnicodeReader ur = new UnicodeReader(this.loc.getInputStream(), oldEncoding);
        String encoding = ur.getEncoding();
        try (BufferedReader r = new BufferedReader(ur);){
            this.read(r, null);
        }
        this.setEncoding(encoding);
        this.setDirty(false);
        this.syncLastSaveOrLoadTimeToActualFile();
        this.discardAllEdits();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (!this.dirty) {
            this.setDirty(true);
        }
    }

    public void save() throws IOException {
        this.saveImpl(this.loc);
        this.setDirty(false);
        this.syncLastSaveOrLoadTimeToActualFile();
    }

    public void saveAs(FileLocation loc) throws IOException {
        this.saveImpl(loc);
        String old = this.getFileFullPath();
        this.loc = loc;
        this.setDirty(false);
        this.lastSaveOrLoadTime = loc.getActualLastModified();
        this.firePropertyChange(FULL_PATH_PROPERTY, old, this.getFileFullPath());
    }

    private void saveImpl(FileLocation loc) throws IOException {
        OutputStream out = loc.getOutputStream();
        try (BufferedWriter w = new BufferedWriter(new UnicodeWriter(out, this.getEncoding()));){
            this.write(w);
        }
    }

    public void setDirty(boolean dirty) {
        if (this.dirty != dirty) {
            this.dirty = dirty;
            this.firePropertyChange(DIRTY_PROPERTY, !dirty, dirty);
        }
    }

    @Override
    public void setDocument(Document doc) {
        Document old = this.getDocument();
        if (old != null) {
            old.removeDocumentListener(this);
        }
        super.setDocument(doc);
        doc.addDocumentListener(this);
    }

    public void setEncoding(String encoding) {
        if (encoding == null) {
            throw new NullPointerException("encoding cannot be null");
        }
        if (!Charset.isSupported(encoding)) {
            throw new UnsupportedCharsetException(encoding);
        }
        if (this.charSet == null || !this.charSet.equals(encoding)) {
            String oldEncoding = this.charSet;
            this.charSet = encoding;
            this.firePropertyChange(ENCODING_PROPERTY, oldEncoding, this.charSet);
            this.setDirty(true);
        }
    }

    public void setLineSeparator(String separator) {
        this.setLineSeparator(separator, true);
    }

    public void setLineSeparator(String separator, boolean setDirty) {
        if (separator == null) {
            throw new NullPointerException("terminator cannot be null");
        }
        if (!("\r\n".equals(separator) || "\n".equals(separator) || "\r".equals(separator))) {
            throw new IllegalArgumentException("Invalid line terminator");
        }
        Document doc = this.getDocument();
        Object old = doc.getProperty("__EndOfLine__");
        if (!separator.equals(old)) {
            doc.putProperty("__EndOfLine__", separator);
            if (setDirty) {
                this.setDirty(true);
            }
        }
    }

    public void setReadOnly(boolean readOnly) {
        if (this.readOnly != readOnly) {
            this.readOnly = readOnly;
            this.firePropertyChange(READ_ONLY_PROPERTY, !readOnly, readOnly);
        }
    }

    public void syncLastSaveOrLoadTimeToActualFile() {
        if (this.loc.isLocalAndExists()) {
            this.lastSaveOrLoadTime = this.loc.getActualLastModified();
        }
    }
}

