/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.chainsaw;

import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

class EventDetails {
    private final long mTimeStamp;
    private final Priority mPriority;
    private final String mCategoryName;
    private final String mNDC;
    private final String mThreadName;
    private final String mMessage;
    private final String[] mThrowableStrRep;
    private final String mLocationDetails;

    EventDetails(long aTimeStamp, Priority aPriority, String aCategoryName, String aNDC, String aThreadName, String aMessage, String[] aThrowableStrRep, String aLocationDetails) {
        this.mTimeStamp = aTimeStamp;
        this.mPriority = aPriority;
        this.mCategoryName = aCategoryName;
        this.mNDC = aNDC;
        this.mThreadName = aThreadName;
        this.mMessage = aMessage;
        this.mThrowableStrRep = aThrowableStrRep;
        this.mLocationDetails = aLocationDetails;
    }

    EventDetails(LoggingEvent aEvent) {
        this(aEvent.timeStamp, aEvent.getLevel(), aEvent.getLoggerName(), aEvent.getNDC(), aEvent.getThreadName(), aEvent.getRenderedMessage(), aEvent.getThrowableStrRep(), aEvent.getLocationInformation() == null ? null : aEvent.getLocationInformation().fullInfo);
    }

    long getTimeStamp() {
        return this.mTimeStamp;
    }

    Priority getPriority() {
        return this.mPriority;
    }

    String getCategoryName() {
        return this.mCategoryName;
    }

    String getNDC() {
        return this.mNDC;
    }

    String getThreadName() {
        return this.mThreadName;
    }

    String getMessage() {
        return this.mMessage;
    }

    String getLocationDetails() {
        return this.mLocationDetails;
    }

    String[] getThrowableStrRep() {
        return this.mThrowableStrRep;
    }
}

