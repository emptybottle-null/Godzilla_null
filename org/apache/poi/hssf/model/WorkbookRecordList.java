/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.record.Record;

public final class WorkbookRecordList {
    private List<Record> records = new ArrayList<Record>();
    private int protpos;
    private int bspos;
    private int tabpos;
    private int fontpos;
    private int xfpos;
    private int backuppos;
    private int namepos;
    private int supbookpos;
    private int externsheetPos;
    private int palettepos = -1;

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public int size() {
        return this.records.size();
    }

    public Record get(int i) {
        return this.records.get(i);
    }

    public void add(int pos, Record r) {
        this.records.add(pos, r);
        this.updateRecordPos(pos, true);
    }

    public List<Record> getRecords() {
        return this.records;
    }

    public void remove(Object record) {
        int i = 0;
        for (Record r : this.records) {
            if (r == record) {
                this.remove(i);
                break;
            }
            ++i;
        }
    }

    public void remove(int pos) {
        this.records.remove(pos);
        this.updateRecordPos(pos, false);
    }

    public int getProtpos() {
        return this.protpos;
    }

    public void setProtpos(int protpos) {
        this.protpos = protpos;
    }

    public int getBspos() {
        return this.bspos;
    }

    public void setBspos(int bspos) {
        this.bspos = bspos;
    }

    public int getTabpos() {
        return this.tabpos;
    }

    public void setTabpos(int tabpos) {
        this.tabpos = tabpos;
    }

    public int getFontpos() {
        return this.fontpos;
    }

    public void setFontpos(int fontpos) {
        this.fontpos = fontpos;
    }

    public int getXfpos() {
        return this.xfpos;
    }

    public void setXfpos(int xfpos) {
        this.xfpos = xfpos;
    }

    public int getBackuppos() {
        return this.backuppos;
    }

    public void setBackuppos(int backuppos) {
        this.backuppos = backuppos;
    }

    public int getPalettepos() {
        return this.palettepos;
    }

    public void setPalettepos(int palettepos) {
        this.palettepos = palettepos;
    }

    public int getNamepos() {
        return this.namepos;
    }

    public int getSupbookpos() {
        return this.supbookpos;
    }

    public void setNamepos(int namepos) {
        this.namepos = namepos;
    }

    public void setSupbookpos(int supbookpos) {
        this.supbookpos = supbookpos;
    }

    public int getExternsheetPos() {
        return this.externsheetPos;
    }

    public void setExternsheetPos(int externsheetPos) {
        this.externsheetPos = externsheetPos;
    }

    private void updateRecordPos(int pos, boolean add) {
        int delta = add ? 1 : -1;
        int p = this.getProtpos();
        if (p >= pos) {
            this.setProtpos(p + delta);
        }
        if ((p = this.getBspos()) >= pos) {
            this.setBspos(p + delta);
        }
        if ((p = this.getTabpos()) >= pos) {
            this.setTabpos(p + delta);
        }
        if ((p = this.getFontpos()) >= pos) {
            this.setFontpos(p + delta);
        }
        if ((p = this.getXfpos()) >= pos) {
            this.setXfpos(p + delta);
        }
        if ((p = this.getBackuppos()) >= pos) {
            this.setBackuppos(p + delta);
        }
        if ((p = this.getNamepos()) >= pos) {
            this.setNamepos(p + delta);
        }
        if ((p = this.getSupbookpos()) >= pos) {
            this.setSupbookpos(p + delta);
        }
        if ((p = this.getPalettepos()) != -1 && p >= pos) {
            this.setPalettepos(p + delta);
        }
        if ((p = this.getExternsheetPos()) >= pos) {
            this.setExternsheetPos(p + delta);
        }
    }
}

