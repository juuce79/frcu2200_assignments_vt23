package com.dt180g.laboration_2;

import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.decorators.Spy;

import java.util.stream.IntStream;

/**
 * An abstract base, defining commonalities for concrete test cases.
 * @author Erik Ström
 */
public abstract class TestBase {
    protected final String baseMsg = "Original Message";

    /**
     * Use the reduce method to create Spy objects and wrap the content in them for
     * each element in the range.
     * @param content our base component.
     * @param spiesAmount number of spies to be used for decoration.
     * @return a chain of spy decorators.
     */
    protected Content addSpiesToContent(Content content, int spiesAmount) {
        return IntStream.range(0, spiesAmount + 1)
                .boxed() // convert the IntStream to a Stream<Integer>
                .reduce(content, (currentContent, i) -> new Spy(currentContent), (a, b) -> b);
    }
}
