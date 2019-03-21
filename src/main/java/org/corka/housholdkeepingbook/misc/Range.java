package org.corka.housholdkeepingbook.misc;

import java.util.ArrayList;
import java.util.List;

public class Range {

    public static List<Integer> getIntegerRange(int startInclusive, int endExclusive) {
        if (endExclusive < startInclusive) throw new IllegalArgumentException("endExclusive cannot be lower then startInclusive");

        int len = endExclusive - startInclusive;
        List<Integer> range = new ArrayList<>(len);

        for (int i = startInclusive; i < endExclusive; i++) {
            range.add(i);
        }

        return range;
    }

}
