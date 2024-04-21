package com.dt180g.laboration_2.support;

/**
 * Support interface where we store constant values, serving as app configuration.
 * @author Erik Ström
 */
public interface Constants {
    /** The length of alphabet to be used for rotation calculation. */
    int ALPHABET_LENGTH = 26;

    /** Lower boundary for interval, used for randomization. */
    int LOWER_BOUNDARY = 10;

    /** Upper boundary for interval, used for randomization. */
    int UPPER_BOUNDARY = 30;
}
