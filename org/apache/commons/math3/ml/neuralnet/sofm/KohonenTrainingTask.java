/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ml.neuralnet.sofm;

import java.util.Iterator;
import org.apache.commons.math3.ml.neuralnet.Network;
import org.apache.commons.math3.ml.neuralnet.sofm.KohonenUpdateAction;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class KohonenTrainingTask
implements Runnable {
    private final Network net;
    private final Iterator<double[]> featuresIterator;
    private final KohonenUpdateAction updateAction;

    public KohonenTrainingTask(Network net, Iterator<double[]> featuresIterator, KohonenUpdateAction updateAction) {
        this.net = net;
        this.featuresIterator = featuresIterator;
        this.updateAction = updateAction;
    }

    @Override
    public void run() {
        while (this.featuresIterator.hasNext()) {
            this.updateAction.update(this.net, this.featuresIterator.next());
        }
    }
}

