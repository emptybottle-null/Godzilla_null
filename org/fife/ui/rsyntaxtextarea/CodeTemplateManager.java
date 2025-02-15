/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.ui.rsyntaxtextarea;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Segment;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.templates.CodeTemplate;

public class CodeTemplateManager {
    private int maxTemplateIDLength;
    private List<CodeTemplate> templates;
    private Segment s = new Segment();
    private TemplateComparator comparator = new TemplateComparator();
    private File directory;

    public CodeTemplateManager() {
        this.templates = new ArrayList<CodeTemplate>();
    }

    public synchronized void addTemplate(CodeTemplate template) {
        if (template == null) {
            throw new IllegalArgumentException("template cannot be null");
        }
        this.templates.add(template);
        this.sortTemplates();
    }

    public synchronized CodeTemplate getTemplate(RSyntaxTextArea textArea) {
        int caretPos = textArea.getCaretPosition();
        int charsToGet = Math.min(caretPos, this.maxTemplateIDLength);
        try {
            Document doc = textArea.getDocument();
            doc.getText(caretPos - charsToGet, charsToGet, this.s);
            int index = Collections.binarySearch(this.templates, this.s, this.comparator);
            return index >= 0 ? this.templates.get(index) : null;
        } catch (BadLocationException ble) {
            ble.printStackTrace();
            throw new InternalError("Error in CodeTemplateManager");
        }
    }

    private synchronized int getTemplateCount() {
        return this.templates.size();
    }

    public synchronized CodeTemplate[] getTemplates() {
        CodeTemplate[] temp = new CodeTemplate[this.templates.size()];
        return this.templates.toArray(temp);
    }

    private static boolean isValidChar(char ch) {
        return RSyntaxUtilities.isLetterOrDigit(ch) || ch == '_';
    }

    public synchronized boolean removeTemplate(CodeTemplate template) {
        if (template == null) {
            throw new IllegalArgumentException("template cannot be null");
        }
        return this.templates.remove(template);
    }

    public synchronized CodeTemplate removeTemplate(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        Iterator<CodeTemplate> i = this.templates.iterator();
        while (i.hasNext()) {
            CodeTemplate template = i.next();
            if (!id.equals(template.getID())) continue;
            i.remove();
            return template;
        }
        return null;
    }

    public synchronized void replaceTemplates(CodeTemplate[] newTemplates) {
        this.templates.clear();
        if (newTemplates != null) {
            Collections.addAll(this.templates, newTemplates);
        }
        this.sortTemplates();
    }

    public synchronized boolean saveTemplates() {
        if (this.templates == null) {
            return true;
        }
        if (this.directory == null || !this.directory.isDirectory()) {
            return false;
        }
        File[] oldXMLFiles = this.directory.listFiles(new XMLFileFilter());
        if (oldXMLFiles == null) {
            return false;
        }
        int count = oldXMLFiles.length;
        for (File oldXMLFile : oldXMLFiles) {
            oldXMLFile.delete();
        }
        boolean wasSuccessful = true;
        for (CodeTemplate template : this.templates) {
            File xmlFile = new File(this.directory, template.getID() + ".xml");
            try {
                XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(xmlFile)));
                e.writeObject(template);
                e.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                wasSuccessful = false;
            }
        }
        return wasSuccessful;
    }

    public synchronized int setTemplateDirectory(File dir) {
        if (dir != null && dir.isDirectory()) {
            this.directory = dir;
            File[] files = dir.listFiles(new XMLFileFilter());
            int newCount = files == null ? 0 : files.length;
            int oldCount = this.templates.size();
            ArrayList<CodeTemplate> temp = new ArrayList<CodeTemplate>(oldCount + newCount);
            temp.addAll(this.templates);
            for (int i = 0; i < newCount; ++i) {
                try {
                    XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(files[i])));
                    Object obj = d.readObject();
                    if (!(obj instanceof CodeTemplate)) {
                        d.close();
                        throw new IOException("Not a CodeTemplate: " + files[i].getAbsolutePath());
                    }
                    temp.add((CodeTemplate)obj);
                    d.close();
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.templates = temp;
            this.sortTemplates();
            return this.getTemplateCount();
        }
        return -1;
    }

    private synchronized void sortTemplates() {
        this.maxTemplateIDLength = 0;
        Iterator<CodeTemplate> i = this.templates.iterator();
        while (i.hasNext()) {
            CodeTemplate temp = i.next();
            if (temp == null || temp.getID() == null) {
                i.remove();
                continue;
            }
            this.maxTemplateIDLength = Math.max(this.maxTemplateIDLength, temp.getID().length());
        }
        Collections.sort(this.templates);
    }

    private static class XMLFileFilter
    implements FileFilter {
        private XMLFileFilter() {
        }

        @Override
        public boolean accept(File f) {
            return f.getName().toLowerCase().endsWith(".xml");
        }
    }

    private static class TemplateComparator
    implements Comparator<Object>,
    Serializable {
        private TemplateComparator() {
        }

        @Override
        public int compare(Object template, Object segment) {
            int j;
            CodeTemplate t = (CodeTemplate)template;
            char[] templateArray = t.getID().toCharArray();
            int i = 0;
            int len1 = templateArray.length;
            Segment s = (Segment)segment;
            char[] segArray = s.array;
            int len2 = s.count;
            for (j = s.offset + len2 - 1; j >= s.offset && CodeTemplateManager.isValidChar(segArray[j]); --j) {
            }
            int segShift = ++j - s.offset;
            int n = Math.min(len1, len2 -= segShift);
            while (n-- != 0) {
                char c2;
                char c1;
                if ((c1 = templateArray[i++]) == (c2 = segArray[j++])) continue;
                return c1 - c2;
            }
            return len1 - len2;
        }
    }
}

