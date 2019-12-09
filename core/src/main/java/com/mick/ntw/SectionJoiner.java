package com.mick.ntw;

import java.util.List;

/**
 * This is responsible for joining the ordered {@link Section} objects into a human readable string.
 */
public interface SectionJoiner {
    /**
     * @param sections {@link Section} objects to join
     * @return Human readable string
     */
    String join(List<Section> sections);
}
