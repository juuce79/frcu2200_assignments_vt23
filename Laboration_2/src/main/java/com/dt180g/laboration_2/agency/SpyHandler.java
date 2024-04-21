package com.dt180g.laboration_2.agency;

import com.dt180g.laboration_2.components.Container;
import com.dt180g.laboration_2.components.Content;
import com.dt180g.laboration_2.decorators.Spy;
import com.dt180g.laboration_2.decorators.SpyMaster;
import com.dt180g.laboration_2.support.Constants;
import com.dt180g.laboration_2.support.InvalidAuthorizationException;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Spy handler coordinates spy activities, serving as both receiver and transmitter
 * of messages.
 * @author Erik Ström
 */
public class SpyHandler {
    private final Random rand = new Random();
    private final String name;

    /**
     * Construction initializes member data, and determines boundary
     * for randomization.
     * @param name the name of handler.
     */
    public SpyHandler(final String name) {
        this.name = name;
    }

    /**
     * Procedure responsible for encryption of message.
     * @param message text contents to be encrypted.
     * @return Message object with encrypted contents.
     */
    private Content encryptMessage(final String message) {
        // Randomize both initial encryption and amount of spies, using defined interval
        int upperBound = Constants.UPPER_BOUNDARY;
        int lowerBound = Constants.LOWER_BOUNDARY;
        int baseEncryption = this.rand.nextInt(upperBound - lowerBound) + lowerBound;
        int spiesAmount = this.rand.nextInt(upperBound - lowerBound) + lowerBound;

        System.out.printf("%n\tCreate message container using base encryption %d.", baseEncryption);
        Content content = new Container(message, baseEncryption);

        System.out.printf("%n\tFilter message through %d spies.", spiesAmount);
        return IntStream.range(0, spiesAmount + 1)
                .boxed() // convert the IntStream to a Stream<Integer>
                .reduce(content, (currentContent, i) -> new Spy(currentContent), (a, b) -> b);
    }

    /**
     * Procedure responsible for sending message to target handler.
     * Message will be built by devoted method.
     * @param content original message content to be sent to target.
     * @param target Spy handler at the receiving end of message.
     */
    public void sendMessage(final String content, final SpyHandler target) {
        System.out.printf("Spy Handler %s building message... ", this.name);
        Content message = encryptMessage(content);

        System.out.printf("%n\tSending message to target handler!");
        target.decryptMessage(message);
    }

    /**
     * Procedure responsible for adding master spy to message chain, for decryption.
     * @param message encrypted message.
     */
    private void decryptMessage(final Content message) {
        System.out.printf("%n%nSpy Handler %s receives message...", this.name);
        System.out.printf("%n\tDecrypting message using master spy.");

        System.out.printf("%n\tDeciphered message: ");
        try {
            System.out.println(new SpyMaster(message).getMessage());
        } catch (InvalidAuthorizationException e) {
            System.out.println(e.getMessage());
        }
    }
}
