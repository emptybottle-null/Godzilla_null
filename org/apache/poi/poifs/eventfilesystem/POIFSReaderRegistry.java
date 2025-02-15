/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.eventfilesystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.poifs.filesystem.DocumentDescriptor;
import org.apache.poi.poifs.filesystem.POIFSDocumentPath;

class POIFSReaderRegistry {
    private Set<POIFSReaderListener> omnivorousListeners = new HashSet<POIFSReaderListener>();
    private Map<POIFSReaderListener, Set<DocumentDescriptor>> selectiveListeners = new HashMap<POIFSReaderListener, Set<DocumentDescriptor>>();
    private Map<DocumentDescriptor, Set<POIFSReaderListener>> chosenDocumentDescriptors = new HashMap<DocumentDescriptor, Set<POIFSReaderListener>>();

    POIFSReaderRegistry() {
    }

    void registerListener(POIFSReaderListener listener, POIFSDocumentPath path, String documentName) {
        DocumentDescriptor descriptor;
        Set descriptors;
        if (!this.omnivorousListeners.contains(listener) && (descriptors = this.selectiveListeners.computeIfAbsent(listener, k -> new HashSet())).add(descriptor = new DocumentDescriptor(path, documentName))) {
            Set listeners = this.chosenDocumentDescriptors.computeIfAbsent(descriptor, k -> new HashSet());
            listeners.add(listener);
        }
    }

    void registerListener(POIFSReaderListener listener) {
        if (!this.omnivorousListeners.contains(listener)) {
            this.removeSelectiveListener(listener);
            this.omnivorousListeners.add(listener);
        }
    }

    Iterable<POIFSReaderListener> getListeners(POIFSDocumentPath path, String name) {
        HashSet<POIFSReaderListener> rval = new HashSet<POIFSReaderListener>(this.omnivorousListeners);
        Set<POIFSReaderListener> selectiveListenersInner = this.chosenDocumentDescriptors.get(new DocumentDescriptor(path, name));
        if (selectiveListenersInner != null) {
            rval.addAll(selectiveListenersInner);
        }
        return rval;
    }

    private void removeSelectiveListener(POIFSReaderListener listener) {
        Set<DocumentDescriptor> selectedDescriptors = this.selectiveListeners.remove(listener);
        if (selectedDescriptors != null) {
            for (DocumentDescriptor selectedDescriptor : selectedDescriptors) {
                this.dropDocument(listener, selectedDescriptor);
            }
        }
    }

    private void dropDocument(POIFSReaderListener listener, DocumentDescriptor descriptor) {
        Set<POIFSReaderListener> listeners = this.chosenDocumentDescriptors.get(descriptor);
        listeners.remove(listener);
        if (listeners.size() == 0) {
            this.chosenDocumentDescriptors.remove(descriptor);
        }
    }
}

