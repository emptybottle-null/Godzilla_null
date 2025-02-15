/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.usermodel;

import org.apache.poi.hpsf.ClassID;
import org.apache.poi.hpsf.ClassIDPredefined;

public interface ObjectMetaData {
    public String getObjectName();

    public String getProgId();

    public ClassID getClassID();

    public String getOleEntry();

    public static enum Application {
        EXCEL_V8("Worksheet", "Excel.Sheet.8", "Package", ClassIDPredefined.EXCEL_V8),
        EXCEL_V12("Worksheet", "Excel.Sheet.12", "Package", ClassIDPredefined.EXCEL_V12),
        WORD_V8("Document", "Word.Document.8", "Package", ClassIDPredefined.WORD_V8),
        WORD_V12("Document", "Word.Document.12", "Package", ClassIDPredefined.WORD_V12),
        PDF("PDF", "AcroExch.Document", "Contents", ClassIDPredefined.PDF),
        CUSTOM(null, null, null, null);

        String objectName;
        String progId;
        String oleEntry;
        ClassID classId;

        private Application(String objectName, String progId, String oleEntry, ClassIDPredefined classId) {
            this.objectName = objectName;
            this.progId = progId;
            this.classId = classId == null ? null : classId.getClassID();
            this.oleEntry = oleEntry;
        }

        public static Application lookup(String progId) {
            for (Application a : Application.values()) {
                if (a.progId == null || !a.progId.equals(progId)) continue;
                return a;
            }
            return null;
        }

        public ObjectMetaData getMetaData() {
            return new ObjectMetaData(){

                @Override
                public String getObjectName() {
                    return objectName;
                }

                @Override
                public String getProgId() {
                    return progId;
                }

                @Override
                public String getOleEntry() {
                    return oleEntry;
                }

                @Override
                public ClassID getClassID() {
                    return classId;
                }
            };
        }
    }
}

