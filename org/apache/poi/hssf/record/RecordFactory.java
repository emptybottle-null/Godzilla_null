/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.ArrayRecord;
import org.apache.poi.hssf.record.AutoFilterInfoRecord;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BackupRecord;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BookBoolRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.BottomMarginRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.CFHeader12Record;
import org.apache.poi.hssf.record.CFHeaderRecord;
import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.CFRuleRecord;
import org.apache.poi.hssf.record.CRNCountRecord;
import org.apache.poi.hssf.record.CRNRecord;
import org.apache.poi.hssf.record.CalcCountRecord;
import org.apache.poi.hssf.record.CalcModeRecord;
import org.apache.poi.hssf.record.CodepageRecord;
import org.apache.poi.hssf.record.ColumnInfoRecord;
import org.apache.poi.hssf.record.ContinueRecord;
import org.apache.poi.hssf.record.CountryRecord;
import org.apache.poi.hssf.record.DBCellRecord;
import org.apache.poi.hssf.record.DConRefRecord;
import org.apache.poi.hssf.record.DSFRecord;
import org.apache.poi.hssf.record.DVALRecord;
import org.apache.poi.hssf.record.DVRecord;
import org.apache.poi.hssf.record.DateWindow1904Record;
import org.apache.poi.hssf.record.DefaultColWidthRecord;
import org.apache.poi.hssf.record.DefaultRowHeightRecord;
import org.apache.poi.hssf.record.DeltaRecord;
import org.apache.poi.hssf.record.DimensionsRecord;
import org.apache.poi.hssf.record.DrawingGroupRecord;
import org.apache.poi.hssf.record.DrawingRecord;
import org.apache.poi.hssf.record.DrawingSelectionRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.ExtSSTRecord;
import org.apache.poi.hssf.record.ExtendedFormatRecord;
import org.apache.poi.hssf.record.ExternSheetRecord;
import org.apache.poi.hssf.record.ExternalNameRecord;
import org.apache.poi.hssf.record.FeatHdrRecord;
import org.apache.poi.hssf.record.FeatRecord;
import org.apache.poi.hssf.record.FilePassRecord;
import org.apache.poi.hssf.record.FileSharingRecord;
import org.apache.poi.hssf.record.FnGroupCountRecord;
import org.apache.poi.hssf.record.FontRecord;
import org.apache.poi.hssf.record.FooterRecord;
import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.GridsetRecord;
import org.apache.poi.hssf.record.GutsRecord;
import org.apache.poi.hssf.record.HCenterRecord;
import org.apache.poi.hssf.record.HeaderFooterRecord;
import org.apache.poi.hssf.record.HeaderRecord;
import org.apache.poi.hssf.record.HideObjRecord;
import org.apache.poi.hssf.record.HorizontalPageBreakRecord;
import org.apache.poi.hssf.record.HyperlinkRecord;
import org.apache.poi.hssf.record.IndexRecord;
import org.apache.poi.hssf.record.InterfaceEndRecord;
import org.apache.poi.hssf.record.InterfaceHdrRecord;
import org.apache.poi.hssf.record.IterationRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.LeftMarginRecord;
import org.apache.poi.hssf.record.MMSRecord;
import org.apache.poi.hssf.record.MergeCellsRecord;
import org.apache.poi.hssf.record.MulBlankRecord;
import org.apache.poi.hssf.record.MulRKRecord;
import org.apache.poi.hssf.record.NameCommentRecord;
import org.apache.poi.hssf.record.NameRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.ObjectProtectRecord;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.hssf.record.PaneRecord;
import org.apache.poi.hssf.record.PasswordRecord;
import org.apache.poi.hssf.record.PasswordRev4Record;
import org.apache.poi.hssf.record.PrecisionRecord;
import org.apache.poi.hssf.record.PrintGridlinesRecord;
import org.apache.poi.hssf.record.PrintHeadersRecord;
import org.apache.poi.hssf.record.PrintSetupRecord;
import org.apache.poi.hssf.record.ProtectRecord;
import org.apache.poi.hssf.record.ProtectionRev4Record;
import org.apache.poi.hssf.record.RKRecord;
import org.apache.poi.hssf.record.RecalcIdRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.RecordFactoryInputStream;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.RefModeRecord;
import org.apache.poi.hssf.record.RefreshAllRecord;
import org.apache.poi.hssf.record.RightMarginRecord;
import org.apache.poi.hssf.record.RowRecord;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.SaveRecalcRecord;
import org.apache.poi.hssf.record.ScenarioProtectRecord;
import org.apache.poi.hssf.record.SelectionRecord;
import org.apache.poi.hssf.record.SharedFormulaRecord;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.hssf.record.StyleRecord;
import org.apache.poi.hssf.record.SupBookRecord;
import org.apache.poi.hssf.record.TabIdRecord;
import org.apache.poi.hssf.record.TableRecord;
import org.apache.poi.hssf.record.TableStylesRecord;
import org.apache.poi.hssf.record.TextObjectRecord;
import org.apache.poi.hssf.record.TopMarginRecord;
import org.apache.poi.hssf.record.UncalcedRecord;
import org.apache.poi.hssf.record.UnknownRecord;
import org.apache.poi.hssf.record.UseSelFSRecord;
import org.apache.poi.hssf.record.UserSViewBegin;
import org.apache.poi.hssf.record.UserSViewEnd;
import org.apache.poi.hssf.record.VCenterRecord;
import org.apache.poi.hssf.record.VerticalPageBreakRecord;
import org.apache.poi.hssf.record.WSBoolRecord;
import org.apache.poi.hssf.record.WindowOneRecord;
import org.apache.poi.hssf.record.WindowProtectRecord;
import org.apache.poi.hssf.record.WindowTwoRecord;
import org.apache.poi.hssf.record.WriteAccessRecord;
import org.apache.poi.hssf.record.WriteProtectRecord;
import org.apache.poi.hssf.record.chart.BeginRecord;
import org.apache.poi.hssf.record.chart.CatLabRecord;
import org.apache.poi.hssf.record.chart.ChartEndBlockRecord;
import org.apache.poi.hssf.record.chart.ChartEndObjectRecord;
import org.apache.poi.hssf.record.chart.ChartFRTInfoRecord;
import org.apache.poi.hssf.record.chart.ChartRecord;
import org.apache.poi.hssf.record.chart.ChartStartBlockRecord;
import org.apache.poi.hssf.record.chart.ChartStartObjectRecord;
import org.apache.poi.hssf.record.chart.ChartTitleFormatRecord;
import org.apache.poi.hssf.record.chart.DataFormatRecord;
import org.apache.poi.hssf.record.chart.EndRecord;
import org.apache.poi.hssf.record.chart.LegendRecord;
import org.apache.poi.hssf.record.chart.LinkedDataRecord;
import org.apache.poi.hssf.record.chart.SeriesRecord;
import org.apache.poi.hssf.record.chart.SeriesTextRecord;
import org.apache.poi.hssf.record.chart.SeriesToChartGroupRecord;
import org.apache.poi.hssf.record.chart.ValueRangeRecord;
import org.apache.poi.hssf.record.pivottable.DataItemRecord;
import org.apache.poi.hssf.record.pivottable.ExtendedPivotTableViewFieldsRecord;
import org.apache.poi.hssf.record.pivottable.PageItemRecord;
import org.apache.poi.hssf.record.pivottable.StreamIDRecord;
import org.apache.poi.hssf.record.pivottable.ViewDefinitionRecord;
import org.apache.poi.hssf.record.pivottable.ViewFieldsRecord;
import org.apache.poi.hssf.record.pivottable.ViewSourceRecord;
import org.apache.poi.util.RecordFormatException;

public final class RecordFactory {
    private static final int NUM_RECORDS = 512;
    private static final Class<?>[] CONSTRUCTOR_ARGS = new Class[]{RecordInputStream.class};
    private static final Class<? extends Record>[] recordClasses = new Class[]{ArrayRecord.class, AutoFilterInfoRecord.class, BackupRecord.class, BlankRecord.class, BOFRecord.class, BookBoolRecord.class, BoolErrRecord.class, BottomMarginRecord.class, BoundSheetRecord.class, CalcCountRecord.class, CalcModeRecord.class, CFHeaderRecord.class, CFHeader12Record.class, CFRuleRecord.class, CFRule12Record.class, ChartRecord.class, ChartTitleFormatRecord.class, CodepageRecord.class, ColumnInfoRecord.class, ContinueRecord.class, CountryRecord.class, CRNCountRecord.class, CRNRecord.class, DateWindow1904Record.class, DBCellRecord.class, DConRefRecord.class, DefaultColWidthRecord.class, DefaultRowHeightRecord.class, DeltaRecord.class, DimensionsRecord.class, DrawingGroupRecord.class, DrawingRecord.class, DrawingSelectionRecord.class, DSFRecord.class, DVALRecord.class, DVRecord.class, EOFRecord.class, ExtendedFormatRecord.class, ExternalNameRecord.class, ExternSheetRecord.class, ExtSSTRecord.class, FeatRecord.class, FeatHdrRecord.class, FilePassRecord.class, FileSharingRecord.class, FnGroupCountRecord.class, FontRecord.class, FooterRecord.class, FormatRecord.class, FormulaRecord.class, GridsetRecord.class, GutsRecord.class, HCenterRecord.class, HeaderRecord.class, HeaderFooterRecord.class, HideObjRecord.class, HorizontalPageBreakRecord.class, HyperlinkRecord.class, IndexRecord.class, InterfaceEndRecord.class, InterfaceHdrRecord.class, IterationRecord.class, LabelRecord.class, LabelSSTRecord.class, LeftMarginRecord.class, LegendRecord.class, MergeCellsRecord.class, MMSRecord.class, MulBlankRecord.class, MulRKRecord.class, NameRecord.class, NameCommentRecord.class, NoteRecord.class, NumberRecord.class, ObjectProtectRecord.class, ObjRecord.class, PaletteRecord.class, PaneRecord.class, PasswordRecord.class, PasswordRev4Record.class, PrecisionRecord.class, PrintGridlinesRecord.class, PrintHeadersRecord.class, PrintSetupRecord.class, ProtectionRev4Record.class, ProtectRecord.class, RecalcIdRecord.class, RefModeRecord.class, RefreshAllRecord.class, RightMarginRecord.class, RKRecord.class, RowRecord.class, SaveRecalcRecord.class, ScenarioProtectRecord.class, SelectionRecord.class, SeriesRecord.class, SeriesTextRecord.class, SharedFormulaRecord.class, SSTRecord.class, StringRecord.class, StyleRecord.class, SupBookRecord.class, TabIdRecord.class, TableRecord.class, TableStylesRecord.class, TextObjectRecord.class, TopMarginRecord.class, UncalcedRecord.class, UseSelFSRecord.class, UserSViewBegin.class, UserSViewEnd.class, ValueRangeRecord.class, VCenterRecord.class, VerticalPageBreakRecord.class, WindowOneRecord.class, WindowProtectRecord.class, WindowTwoRecord.class, WriteAccessRecord.class, WriteProtectRecord.class, WSBoolRecord.class, BeginRecord.class, ChartFRTInfoRecord.class, ChartStartBlockRecord.class, ChartEndBlockRecord.class, ChartStartObjectRecord.class, ChartEndObjectRecord.class, CatLabRecord.class, DataFormatRecord.class, EndRecord.class, LinkedDataRecord.class, SeriesToChartGroupRecord.class, DataItemRecord.class, ExtendedPivotTableViewFieldsRecord.class, PageItemRecord.class, StreamIDRecord.class, ViewDefinitionRecord.class, ViewFieldsRecord.class, ViewSourceRecord.class};
    private static final Map<Integer, I_RecordCreator> _recordCreatorsById = RecordFactory.recordsToMap(recordClasses);
    private static short[] _allKnownRecordSIDs;

    public static Class<? extends Record> getRecordClass(int sid) {
        I_RecordCreator rc = _recordCreatorsById.get(sid);
        if (rc == null) {
            return null;
        }
        return rc.getRecordClass();
    }

    public static Record[] createRecord(RecordInputStream in) {
        Record record = RecordFactory.createSingleRecord(in);
        if (record instanceof DBCellRecord) {
            return new Record[]{null};
        }
        if (record instanceof RKRecord) {
            return new Record[]{RecordFactory.convertToNumberRecord((RKRecord)record)};
        }
        if (record instanceof MulRKRecord) {
            return RecordFactory.convertRKRecords((MulRKRecord)record);
        }
        return new Record[]{record};
    }

    public static Record createSingleRecord(RecordInputStream in) {
        I_RecordCreator constructor = _recordCreatorsById.get(in.getSid());
        if (constructor == null) {
            return new UnknownRecord(in);
        }
        return constructor.create(in);
    }

    public static NumberRecord convertToNumberRecord(RKRecord rk) {
        NumberRecord num = new NumberRecord();
        num.setColumn(rk.getColumn());
        num.setRow(rk.getRow());
        num.setXFIndex(rk.getXFIndex());
        num.setValue(rk.getRKNumber());
        return num;
    }

    public static NumberRecord[] convertRKRecords(MulRKRecord mrk) {
        NumberRecord[] mulRecs = new NumberRecord[mrk.getNumColumns()];
        for (int k = 0; k < mrk.getNumColumns(); ++k) {
            NumberRecord nr = new NumberRecord();
            nr.setColumn((short)(k + mrk.getFirstColumn()));
            nr.setRow(mrk.getRow());
            nr.setXFIndex(mrk.getXFAt(k));
            nr.setValue(mrk.getRKNumberAt(k));
            mulRecs[k] = nr;
        }
        return mulRecs;
    }

    public static BlankRecord[] convertBlankRecords(MulBlankRecord mbk) {
        BlankRecord[] mulRecs = new BlankRecord[mbk.getNumColumns()];
        for (int k = 0; k < mbk.getNumColumns(); ++k) {
            BlankRecord br = new BlankRecord();
            br.setColumn((short)(k + mbk.getFirstColumn()));
            br.setRow(mbk.getRow());
            br.setXFIndex(mbk.getXFAt(k));
            mulRecs[k] = br;
        }
        return mulRecs;
    }

    public static short[] getAllKnownRecordSIDs() {
        if (_allKnownRecordSIDs == null) {
            short[] results = new short[_recordCreatorsById.size()];
            int i = 0;
            for (Integer sid : _recordCreatorsById.keySet()) {
                results[i++] = sid.shortValue();
            }
            Arrays.sort(results);
            _allKnownRecordSIDs = results;
        }
        return (short[])_allKnownRecordSIDs.clone();
    }

    private static Map<Integer, I_RecordCreator> recordsToMap(Class<? extends Record>[] records) {
        HashMap<Integer, I_RecordCreator> result = new HashMap<Integer, I_RecordCreator>();
        HashSet<Class<? extends Record>> uniqueRecClasses = new HashSet<Class<? extends Record>>(records.length * 3 / 2);
        for (Class<? extends Record> recClass : records) {
            short sid;
            if (!Record.class.isAssignableFrom(recClass)) {
                throw new RuntimeException("Invalid record sub-class (" + recClass.getName() + ")");
            }
            if (Modifier.isAbstract(recClass.getModifiers())) {
                throw new RuntimeException("Invalid record class (" + recClass.getName() + ") - must not be abstract");
            }
            if (!uniqueRecClasses.add(recClass)) {
                throw new RuntimeException("duplicate record class (" + recClass.getName() + ")");
            }
            try {
                sid = recClass.getField("sid").getShort(null);
            } catch (Exception illegalArgumentException) {
                throw new RecordFormatException("Unable to determine record types");
            }
            Integer key = sid;
            if (result.containsKey(key)) {
                Class<? extends Record> prevClass = ((I_RecordCreator)result.get(key)).getRecordClass();
                throw new RuntimeException("duplicate record sid 0x" + Integer.toHexString(sid).toUpperCase(Locale.ROOT) + " for classes (" + recClass.getName() + ") and (" + prevClass.getName() + ")");
            }
            result.put(key, RecordFactory.getRecordCreator(recClass));
        }
        return result;
    }

    private static I_RecordCreator getRecordCreator(Class<? extends Record> recClass) {
        try {
            Constructor<? extends Record> constructor = recClass.getConstructor(CONSTRUCTOR_ARGS);
            return new ReflectionConstructorRecordCreator(constructor);
        } catch (NoSuchMethodException constructor) {
            try {
                Method m = recClass.getDeclaredMethod("create", CONSTRUCTOR_ARGS);
                return new ReflectionMethodRecordCreator(m);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Failed to find constructor or create method for (" + recClass.getName() + ").");
            }
        }
    }

    public static List<Record> createRecords(InputStream in) throws RecordFormatException {
        Record record;
        ArrayList<Record> records = new ArrayList<Record>(512);
        RecordFactoryInputStream recStream = new RecordFactoryInputStream(in, true);
        while ((record = recStream.nextRecord()) != null) {
            records.add(record);
        }
        return records;
    }

    private static final class ReflectionMethodRecordCreator
    implements I_RecordCreator {
        private final Method _m;

        public ReflectionMethodRecordCreator(Method m) {
            this._m = m;
        }

        @Override
        public Record create(RecordInputStream in) {
            Object[] args = new Object[]{in};
            try {
                return (Record)this._m.invoke(null, args);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RecordFormatException("Unable to construct record instance", e.getTargetException());
            }
        }

        @Override
        public Class<? extends Record> getRecordClass() {
            return this._m.getDeclaringClass();
        }
    }

    private static final class ReflectionConstructorRecordCreator
    implements I_RecordCreator {
        private final Constructor<? extends Record> _c;

        public ReflectionConstructorRecordCreator(Constructor<? extends Record> c) {
            this._c = c;
        }

        @Override
        public Record create(RecordInputStream in) {
            Object[] args = new Object[]{in};
            try {
                return this._c.newInstance(args);
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                Throwable t = e.getTargetException();
                if (t instanceof RecordFormatException) {
                    throw (RecordFormatException)t;
                }
                if (t instanceof EncryptedDocumentException) {
                    throw (EncryptedDocumentException)t;
                }
                throw new RecordFormatException("Unable to construct record instance", t);
            }
        }

        @Override
        public Class<? extends Record> getRecordClass() {
            return this._c.getDeclaringClass();
        }
    }

    private static interface I_RecordCreator {
        public Record create(RecordInputStream var1);

        public Class<? extends Record> getRecordClass();
    }
}

