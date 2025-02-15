/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.chainsaw;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.chainsaw.EventDetails;

class MyTableModel
extends AbstractTableModel {
    private static final Logger LOG = Logger.getLogger(MyTableModel.class);
    private static final Comparator MY_COMP = new Comparator(){

        public int compare(Object aObj1, Object aObj2) {
            if (aObj1 == null && aObj2 == null) {
                return 0;
            }
            if (aObj1 == null) {
                return -1;
            }
            if (aObj2 == null) {
                return 1;
            }
            EventDetails le1 = (EventDetails)aObj1;
            EventDetails le2 = (EventDetails)aObj2;
            if (le1.getTimeStamp() < le2.getTimeStamp()) {
                return 1;
            }
            return -1;
        }
    };
    private static final String[] COL_NAMES = new String[]{"Time", "Priority", "Trace", "Category", "NDC", "Message"};
    private static final EventDetails[] EMPTY_LIST = new EventDetails[0];
    private static final DateFormat DATE_FORMATTER = DateFormat.getDateTimeInstance(3, 2);
    private final Object mLock = new Object();
    private final SortedSet mAllEvents = new TreeSet(MY_COMP);
    private EventDetails[] mFilteredEvents = EMPTY_LIST;
    private final List mPendingEvents = new ArrayList();
    private boolean mPaused = false;
    private String mThreadFilter = "";
    private String mMessageFilter = "";
    private String mNDCFilter = "";
    private String mCategoryFilter = "";
    private Priority mPriorityFilter = Priority.DEBUG;

    MyTableModel() {
        Thread t = new Thread(new Processor());
        t.setDaemon(true);
        t.start();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getRowCount() {
        Object object = this.mLock;
        synchronized (object) {
            return this.mFilteredEvents.length;
        }
    }

    public int getColumnCount() {
        return COL_NAMES.length;
    }

    public String getColumnName(int aCol) {
        return COL_NAMES[aCol];
    }

    public Class getColumnClass(int aCol) {
        Class clazz = aCol == 2 ? Boolean.class : Object.class;
        return clazz;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object getValueAt(int aRow, int aCol) {
        Object object = this.mLock;
        synchronized (object) {
            EventDetails event = this.mFilteredEvents[aRow];
            if (aCol == 0) {
                return DATE_FORMATTER.format(new Date(event.getTimeStamp()));
            }
            if (aCol == 1) {
                return event.getPriority();
            }
            if (aCol == 2) {
                return event.getThrowableStrRep() == null ? Boolean.FALSE : Boolean.TRUE;
            }
            if (aCol == 3) {
                return event.getCategoryName();
            }
            if (aCol == 4) {
                return event.getNDC();
            }
            return event.getMessage();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setPriorityFilter(Priority aPriority) {
        Object object = this.mLock;
        synchronized (object) {
            this.mPriorityFilter = aPriority;
            this.updateFilteredEvents(false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setThreadFilter(String aStr) {
        Object object = this.mLock;
        synchronized (object) {
            this.mThreadFilter = aStr.trim();
            this.updateFilteredEvents(false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setMessageFilter(String aStr) {
        Object object = this.mLock;
        synchronized (object) {
            this.mMessageFilter = aStr.trim();
            this.updateFilteredEvents(false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setNDCFilter(String aStr) {
        Object object = this.mLock;
        synchronized (object) {
            this.mNDCFilter = aStr.trim();
            this.updateFilteredEvents(false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setCategoryFilter(String aStr) {
        Object object = this.mLock;
        synchronized (object) {
            this.mCategoryFilter = aStr.trim();
            this.updateFilteredEvents(false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void addEvent(EventDetails aEvent) {
        Object object = this.mLock;
        synchronized (object) {
            this.mPendingEvents.add(aEvent);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clear() {
        Object object = this.mLock;
        synchronized (object) {
            this.mAllEvents.clear();
            this.mFilteredEvents = new EventDetails[0];
            this.mPendingEvents.clear();
            this.fireTableDataChanged();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void toggle() {
        Object object = this.mLock;
        synchronized (object) {
            this.mPaused = !this.mPaused;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isPaused() {
        Object object = this.mLock;
        synchronized (object) {
            return this.mPaused;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public EventDetails getEventDetails(int aRow) {
        Object object = this.mLock;
        synchronized (object) {
            return this.mFilteredEvents[aRow];
        }
    }

    private void updateFilteredEvents(boolean aInsertedToFront) {
        long start = System.currentTimeMillis();
        ArrayList<EventDetails> filtered = new ArrayList<EventDetails>();
        int size = this.mAllEvents.size();
        Iterator it = this.mAllEvents.iterator();
        while (it.hasNext()) {
            EventDetails event = (EventDetails)it.next();
            if (!this.matchFilter(event)) continue;
            filtered.add(event);
        }
        EventDetails lastFirst = this.mFilteredEvents.length == 0 ? null : this.mFilteredEvents[0];
        this.mFilteredEvents = filtered.toArray(EMPTY_LIST);
        if (aInsertedToFront && lastFirst != null) {
            int index = filtered.indexOf(lastFirst);
            if (index < 1) {
                LOG.warn("In strange state");
                this.fireTableDataChanged();
            } else {
                this.fireTableRowsInserted(0, index - 1);
            }
        } else {
            this.fireTableDataChanged();
        }
        long end = System.currentTimeMillis();
        LOG.debug("Total time [ms]: " + (end - start) + " in update, size: " + size);
    }

    private boolean matchFilter(EventDetails aEvent) {
        if (aEvent.getPriority().isGreaterOrEqual(this.mPriorityFilter) && aEvent.getThreadName().indexOf(this.mThreadFilter) >= 0 && aEvent.getCategoryName().indexOf(this.mCategoryFilter) >= 0 && (this.mNDCFilter.length() == 0 || aEvent.getNDC() != null && aEvent.getNDC().indexOf(this.mNDCFilter) >= 0)) {
            String rm = aEvent.getMessage();
            if (rm == null) {
                return this.mMessageFilter.length() == 0;
            }
            return rm.indexOf(this.mMessageFilter) >= 0;
        }
        return false;
    }

    private class Processor
    implements Runnable {
        private Processor() {
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException interruptedException) {
                    // empty catch block
                }
                Object object = MyTableModel.this.mLock;
                synchronized (object) {
                    if (MyTableModel.this.mPaused) {
                        continue;
                    }
                    boolean toHead = true;
                    boolean needUpdate = false;
                    Iterator it = MyTableModel.this.mPendingEvents.iterator();
                    while (it.hasNext()) {
                        EventDetails event = (EventDetails)it.next();
                        MyTableModel.this.mAllEvents.add(event);
                        toHead = toHead && event == MyTableModel.this.mAllEvents.first();
                        needUpdate = needUpdate || MyTableModel.this.matchFilter(event);
                    }
                    MyTableModel.this.mPendingEvents.clear();
                    if (needUpdate) {
                        MyTableModel.this.updateFilteredEvents(toHead);
                    }
                }
            }
        }
    }
}

