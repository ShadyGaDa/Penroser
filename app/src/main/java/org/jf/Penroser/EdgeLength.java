/*
 * [The "BSD licence"]
 * Copyright (c) 2011 Ben Gruver
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jf.Penroser;

import java.util.ArrayList;
import java.util.List;

public class EdgeLength {
    private static List<EdgeLength> edgeLengths_pos = new ArrayList<EdgeLength>();
    private static List<EdgeLength> edgeLengths_neg = new ArrayList<EdgeLength>();

    private float[] xLengths;
    private float[] yLengths;

    private EdgeLength(float scale) {
        xLengths = new float[20];
        yLengths = new float[20];
        for (int i=0; i<20; i++) {
            xLengths[i] = (float)(scale * Math.sin(i*Math.PI/10));
            yLengths[i] = (float)(scale * Math.cos(i*Math.PI/10));
        }
    }

    private static void initToLevel(int level) {
        if (level < 0) {
            for (int i=edgeLengths_neg.size(); i<=-level; i++) {
                edgeLengths_neg.add(new EdgeLength((float)Math.pow(Constants.goldenRatio, i)));
            }
        } else {
            for (int i=edgeLengths_pos.size(); i<=level; i++) {
                edgeLengths_pos.add(new EdgeLength((float)Math.pow(Constants.goldenRatio, -i)));
            }
        }
    }

    public static EdgeLength getEdgeLength(int level) {
        initToLevel(level);
        if (level < 0) {
            return edgeLengths_neg.get(-level);
        }
        return edgeLengths_pos.get(level);
    }

    public float x(int rotation) {
        return xLengths[MathUtil.positiveMod(rotation, 20)];
    }

    public float y(int rotation) {
        return yLengths[MathUtil.positiveMod(rotation, 20)];
    }
}
