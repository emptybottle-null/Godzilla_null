/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.animation.Animate;
import com.kitfox.svg.animation.AnimateColor;
import com.kitfox.svg.animation.AnimateMotion;
import com.kitfox.svg.animation.AnimateTransform;
import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.animation.TrackBase;
import com.kitfox.svg.animation.TrackColor;
import com.kitfox.svg.animation.TrackDouble;
import com.kitfox.svg.animation.TrackPath;
import com.kitfox.svg.animation.TrackTransform;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

public class TrackManager
implements Serializable {
    public static final long serialVersionUID = 0L;
    HashMap<TrackKey, TrackBase> tracks = new HashMap();

    public void addTrackElement(AnimationElement element) throws SVGElementException {
        TrackBase track;
        block8: {
            TrackKey key;
            block10: {
                block9: {
                    key = new TrackKey(element);
                    track = this.tracks.get(key);
                    if (track != null) break block8;
                    if (!(element instanceof Animate)) break block9;
                    switch (((Animate)element).getDataType()) {
                        case 0: {
                            track = new TrackDouble(element);
                            break block10;
                        }
                        case 1: {
                            track = new TrackColor(element);
                            break block10;
                        }
                        case 2: {
                            track = new TrackPath(element);
                            break block10;
                        }
                        default: {
                            throw new RuntimeException("");
                        }
                    }
                }
                if (element instanceof AnimateColor) {
                    track = new TrackColor(element);
                } else if (element instanceof AnimateTransform || element instanceof AnimateMotion) {
                    track = new TrackTransform(element);
                }
            }
            this.tracks.put(key, track);
        }
        track.addElement(element);
    }

    public TrackBase getTrack(String name, int type) {
        if (type == 2) {
            TrackBase t = this.getTrack(name, 0);
            if (t != null) {
                return t;
            }
            t = this.getTrack(name, 1);
            if (t != null) {
                return t;
            }
            return null;
        }
        TrackKey key = new TrackKey(name, type);
        TrackBase t = this.tracks.get(key);
        if (t != null) {
            return t;
        }
        key = new TrackKey(name, 2);
        return this.tracks.get(key);
    }

    public int getNumTracks() {
        return this.tracks.size();
    }

    public Iterator<TrackBase> iterator() {
        return this.tracks.values().iterator();
    }

    static class TrackKey {
        String name;
        int type;

        TrackKey(AnimationElement base) {
            this(base.getAttribName(), base.getAttribType());
        }

        TrackKey(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public int hashCode() {
            int hash = this.name == null ? 0 : this.name.hashCode();
            hash = hash * 97 + this.type;
            return hash;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof TrackKey)) {
                return false;
            }
            TrackKey key = (TrackKey)obj;
            return key.type == this.type && key.name.equals(this.name);
        }
    }
}

