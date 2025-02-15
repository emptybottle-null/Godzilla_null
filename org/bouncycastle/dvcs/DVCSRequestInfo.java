/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.dvcs;

import java.math.BigInteger;
import java.util.Date;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformation;
import org.bouncycastle.asn1.dvcs.DVCSTime;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.dvcs.DVCSParsingException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.Arrays;

public class DVCSRequestInfo {
    private DVCSRequestInformation data;

    public DVCSRequestInfo(byte[] byArray) {
        this(DVCSRequestInformation.getInstance(byArray));
    }

    public DVCSRequestInfo(DVCSRequestInformation dVCSRequestInformation) {
        this.data = dVCSRequestInformation;
    }

    public DVCSRequestInformation toASN1Structure() {
        return this.data;
    }

    public int getVersion() {
        return this.data.getVersion();
    }

    public int getServiceType() {
        return this.data.getService().getValue().intValue();
    }

    public BigInteger getNonce() {
        return this.data.getNonce();
    }

    public Date getRequestTime() throws DVCSParsingException {
        DVCSTime dVCSTime = this.data.getRequestTime();
        if (dVCSTime == null) {
            return null;
        }
        try {
            if (dVCSTime.getGenTime() != null) {
                return dVCSTime.getGenTime().getDate();
            }
            TimeStampToken timeStampToken = new TimeStampToken(dVCSTime.getTimeStampToken());
            return timeStampToken.getTimeStampInfo().getGenTime();
        } catch (Exception exception) {
            throw new DVCSParsingException("unable to extract time: " + exception.getMessage(), exception);
        }
    }

    public GeneralNames getRequester() {
        return this.data.getRequester();
    }

    public PolicyInformation getRequestPolicy() {
        if (this.data.getRequestPolicy() != null) {
            return this.data.getRequestPolicy();
        }
        return null;
    }

    public GeneralNames getDVCSNames() {
        return this.data.getDVCS();
    }

    public GeneralNames getDataLocations() {
        return this.data.getDataLocations();
    }

    public static boolean validate(DVCSRequestInfo dVCSRequestInfo, DVCSRequestInfo dVCSRequestInfo2) {
        DVCSRequestInformation dVCSRequestInformation = dVCSRequestInfo.data;
        DVCSRequestInformation dVCSRequestInformation2 = dVCSRequestInfo2.data;
        if (dVCSRequestInformation.getVersion() != dVCSRequestInformation2.getVersion()) {
            return false;
        }
        if (!DVCSRequestInfo.clientEqualsServer(dVCSRequestInformation.getService(), dVCSRequestInformation2.getService())) {
            return false;
        }
        if (!DVCSRequestInfo.clientEqualsServer(dVCSRequestInformation.getRequestTime(), dVCSRequestInformation2.getRequestTime())) {
            return false;
        }
        if (!DVCSRequestInfo.clientEqualsServer(dVCSRequestInformation.getRequestPolicy(), dVCSRequestInformation2.getRequestPolicy())) {
            return false;
        }
        if (!DVCSRequestInfo.clientEqualsServer(dVCSRequestInformation.getExtensions(), dVCSRequestInformation2.getExtensions())) {
            return false;
        }
        if (dVCSRequestInformation.getNonce() != null) {
            if (dVCSRequestInformation2.getNonce() == null) {
                return false;
            }
            byte[] byArray = dVCSRequestInformation.getNonce().toByteArray();
            byte[] byArray2 = dVCSRequestInformation2.getNonce().toByteArray();
            if (byArray2.length < byArray.length) {
                return false;
            }
            if (!Arrays.areEqual(byArray, Arrays.copyOfRange(byArray2, 0, byArray.length))) {
                return false;
            }
        }
        return true;
    }

    private static boolean clientEqualsServer(Object object, Object object2) {
        return object == null && object2 == null || object != null && object.equals(object2);
    }
}

