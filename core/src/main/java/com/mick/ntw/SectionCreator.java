package com.mick.ntw;

import java.util.List;

/**
 * This is responsible for creating a list of {@link Section} objects from the given int value.
 */
public interface SectionCreator {
    /**
     * @param value value to build the {@link Section} objects from
     * @return the ordered list of {@link Section} objects
     */
    List<Section> mapFromNumber(int value);
}
