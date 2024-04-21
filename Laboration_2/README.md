# Laboration 2

## Environments & Tools

* MSI GE72MVR 7RG Apache Pro
* Windows 11 Pro 22H2 22621.1413
* Ubuntu Desktop 23.04
* Visual Studio Code 1.77.0 (on Windows 11 Pro)
* Visual Studio Code 1.78.2 (on Ubuntu Desktop 23.04)
* OpenJDK 17.0.6 LTS
* Apache Maven 3.9.1 (on Windows 11 Pro)
* Apache Maven 3.8.7 (on Ubuntu Desktop 23.04)
* Windows Terminal 1.16.10262.0
* Microsoft PowerShell 7.3.3
* Google Chrome 111.0.5563.147

## Purpose

The aim of this lab is to demonstrate the application of the **Decorator** Design Pattern in a secure message transmission scenario, where messages need to be encrypted and decrypted while being transferred between spy handlers through field agents. The provided design needs to be implemented, ensuring that the encryption and decryption processes work as intended, and the solution adheres to the **Decorator** Design Pattern principles.

**Concrete Goals:**

* Implement the `Content` class as the abstract base component, in compliance to its specification, and implement the `Operative` class as the abstract base decorator, from which concrete decorators will derive.
* Implement the `Container` class as the concrete component, responsible for the base storage of the encrypted message content and initial encryption.
* Implement the `Spy` class as a concrete decorator, responsible for increasing the encryption level each time a new `Spy` is added to the decoration chain.
* Implement the `SpyMaster` class as a concrete decorator, responsible for decrypting the encrypted message and applying the decryption key based on the actual encryption depth.
* Ensuring that the solution complies with design specifications, the provided client code, and passes the provided unit tests.

## Procedures

For this assignment, in order to get a working solution, new classes were to be implemented without modyifying any existing classes. The classes to be implemented were `Content`, `Container`, `Operative`, `Spy`, `SpyMaster` and `InvalidAuthorizationException`. The provided unit tests were to be used to verify that the solution works as intended. The following steps were taken to complete the assignment:

```java
public abstract class Content implements MessageInterface
```

The `Content` class represents the basic functionality for encrypting and decrypting messages using the Caesar cipher. The class is declared as abstract, which means it cannot be instantiated directly but can be used as a base class for other classes.

The class has a private static variable called `encryptionLevel`, which represents the global encryption level. This variable is shared among all instances of `Content` and can be accessed by subclasses.

The `increaseEncryptionLevel(int level)` method is used to increase the global encryption level by a specified amount (`level`). It is a protected method, meaning it can only be accessed within the class or its subclasses. The current encryption level is stored in a variable named `encryptionLevel`. The method adds the level parameter to the current value of `encryptionLevel`, effectively increasing the encryption level.

The `setEncryptionLevel(int level)` method sets the global encryption level to a specific value (`level`). It is also a protected method, accessible only within the class or its subclasses. The encryption level is stored in the `encryptionLevel` variable, and the method assigns the value of the level parameter to `encryptionLevel`, effectively updating the encryption level to the desired value.

The `getEncryptionLevel(Content content)` method retrieves the encryption level of a given `Content` object. It takes a `Content` object as a parameter and returns an integer representing the encryption level. The method checks if the provided `Content` object is an instance of `SpyMaster`. If it is, the method returns the current value of `encryptionLevel`.

``` java
if (content instanceof SpyMaster) {
    return encryptionLevel;
}
```

However, if the `Content` object is not an instance of `SpyMaster`, an `InvalidAuthorizationException` is thrown, indicating that only spy masters are allowed to access the encryption depth.

``` java
else {
    throw new InvalidAuthorizationException("Only spy masters may access encryption depth.");
}
```

The `cipher(String message, int shift)` method is used to encrypt a message using the Caesar cipher. It takes a message string and an integer shift as parameters. The `message` parameter represents the message to be encrypted, while the `shift` parameter determines the amount to shift each character in the message by. The method begins by checking if the message is `null`. If it is, an `IllegalArgumentException` is thrown, indicating that the message cannot be `null`. Next, a `StringBuilder` named `encryptedMessage` is initialized. This `StringBuilder` will be used to store the encrypted message.

The method then iterates through each character in the input message using a `foreach` loop. For each character `ch` in the `message`, the following operations are performed:

1. If the character `ch` is not alphabetic, it is appended to the `encryptedMessage` without modification. This ensures that non-alphabetic characters, such as spaces or punctuation, remain unchanged in the encrypted message.
2. If the character `ch` is alphabetic, the method determines the `offset` value based on whether the character is uppercase or lowercase. If `ch` is uppercase, the `offset` is set to the Unicode value of 'A'. Otherwise, if `ch` is lowercase, the `offset` is set to the Unicode value of 'a'.
3. The method calculates the new position of the character in the alphabet after applying the `shift`. This is done by subtracting the `offset` from the character `ch`, adding the shift, and taking the modulus of `Constants.ALPHABET_LENGTH`, which represents the number of letters in the alphabet.
4. If the calculated `alphaPos` is negative (i.e., the `shift` wraps around to the beginning of the alphabet), the alphabet length is added to make it positive and wrap it around to the end of the alphabet.
5. The method calculates the encrypted character by adding the `offset` and the new position. This gives the shifted character in the alphabet.
6. The encrypted character is appended to the `encryptedMessage` using the `append` method of the `StringBuilder`.
7. After processing all the characters in the message, the `encryptedMessage` is returned as a string representation of the encrypted message.

```java
public class Container extends Content
```

The `Container` class is a subclass of the abstract `Content` class. The `Container` class is designed to hold and manage encrypted messages.

In the `Container` constructor, an incoming message is encrypted using the Caesar cipher method from the `Content` class and the provided encryption level (or a default level of 10 if a negative level is provided).

The constructor for the `Container` class does the following:

1. If the `baseEncryption` level is less than 0, it defaults it to 10. This is a simple way to prevent negative encryption levels. This turned out to be a tricky part of the code to get right and it held up progress for a while.
2. It then sets the encryption level in the `Content` class using the `setEncryptionLevel` method. Since `encryptionLevel` is a static variable in `Content`, it affects all instances of classes extending `Content`.
3. The constructor encrypts the incoming message using the `cipher` method from `Content`, which applies a Caesar cipher to the message. This encrypted message is then stored in the message field of the `Container` class.
4. The `Container` class also has a `getMessage` method. This method simply returns the encrypted message that's stored in the message field. This method is an implementation of the `getMessage` method declared in the `MessageInterface`.

```java
public abstract class Operative extends Content
```

`Operative` is an abstract class, with its subclasses being `Spy` and `SpyMaster`. It extends `Content`, which means it inherits all the methods and fields from the `Content` class and as this is a Decorator pattern implementation, it is designed to "decorate" or augment instances of `Content`.

The constructor for the `Operative` class takes one argument of type `Content`. This `Content` object is the object that this `Operative` is going to "decorate" or augment. The received `Content` object is assigned to the `content` field of the `Operative` object.

The `getMessage` method is overriding the `getMessage` method from the `MessageInterface` class, via the `Content` class. It returns the message from the `Content` object that this `Operative` is decorating. It first casts the `Content` object to a `MessageInterface` and then calls its `getMessage` method to get the message. Casting was suggested by the IDE as there was a compile error beforehand when using `content.getMessage()`.

```java
public final class Spy extends Operative
```

`Spy` is a concrete class that extends `Operative` and it is also declared as a final class, meaning it cannot be extended by any other class. It has a private integer field, `encryptionLevel`, which is used to store the encryption level that will be used by the `Spy`.

The constructor takes a `Content` object as an argument, which it passes to the `Operative` constructor using the `super` keyword. It then enters a do-while loop to generate a random encryption level between `Constants.LOWER_BOUNDARY` and `Constants.UPPER_BOUNDARY`. If the generated encryption level is 26, it will loop again until it generates a number that isn't 26. This is done because an encryption level of 26 in a Caesar cipher would result in no change to the original message (since there are 26 letters in the English alphabet). This part of the code was difficult and it took a lot of debugging (using the debugging tools of the IDE) to finally figure out that the problem was with the 26 letters of the alphabet.

After a valid encryption level is generated, it calls the `increaseEncryptionLevel` static method from the `Content` class, increasing the global encryption level by the encryption level of the `Spy`.

The `getMessage` method overrides the `getMessage` method from the `Operative` class. It first calls the `getMessage` method from `Operative` (using `super.getMessage()`) to get the encrypted message from the `Content` object that the `Spy` is decorating. It then calls the cipher method (inherited from `Content`) to further encrypt this message using the Spy's own `encryptionLevel`.

```java
public final class SpyMaster extends Operative
```

`SpyMaster` is also a concrete class that extends `Operative` and it is also declared as a final class. It has a private integer field, `decryptionKey`, which is used to store the decryption key that will be used by the `SpyMaster`.

The constructor takes a `Content` object as an argument, which it passes to the `Operative` constructor using the super keyword. It then calls the static `getEncryptionLevel` method from the `Content` class, passing in this as the argument (which refers to the current `SpyMaster` object) to get the current encryption depth.

After that, it calculates the decryption key by subtracting the encryption depth (modulo the alphabet length) from the alphabet length. This is because the Caesar cipher is cyclic with a cycle length equal to the alphabet length, so subtracting the encryption depth from the alphabet length gives the correct shift for decryption.

```java
Constants.ALPHABET_LENGTH - (encryptionDepth % Constants.ALPHABET_LENGTH)
```

Just as in the `Spy` class, the `getMessage` method from `SpyMaster` also overrides the `getMessage` method from the `Operative` class. It first calls the `getMessage` method from `Operative` (using `super.getMessage()`) to get the encrypted message from the `Content` object that the `SpyMaster` is decorating. It then calls the `cipher` method (inherited from `Content`) to decrypt this message using the SpyMaster's own `decryptionKey`.

```java
public class InvalidAuthorizationException extends RuntimeException
```

The `InvalidAuthorizationException` class is a custom exception that is used to signal an invalid authorization scenario in the code. It extends `RuntimeException`, which is a built-in class in Java that represents exceptions which occur during the execution of a program, and they are unchecked, meaning they don't need to be declared in a method or constructor's throws clause.

The constructor of `InvalidAuthorizationException` takes a string parameter message, which is the detail message for the exception. This message is then passed to the constructor of the superclass (`RuntimeException`) using the super keyword. An exception is thrown when an `Operative` other than a `SpyMaster` tries to access the encryption depth.

## Discussion

The purpose of this lab was to modify the existing Spies’R Us app by adding the suggested classes to make the app function. The classes were to be added by following the instruction PDF and the Strategy Design Pattern contained therein. No modifications were to be made to the existing code.

Following the order of the Assignment PDF, firstly, the `Content` class was added. This was the biggest class and maybe the most important, at least of the classes to be implemented. The self-explanatory `increaseEncryptionLevel`, `setEncryptionLevel` and `getEncryptionLevel` methods were all added. These methods were all to be called from other classes. The `cipher` method, maybe the most important method in the `Content` class, was also added. This method was a Caesar cipher implementation which was responsible for both the encryption and decryption of the passwords used by the spies.

Next, the `Container` class was implemented. This class holds and manages the encrypted messages (passwords) and is a subclass of the `Content` class. This class has to take the `message` that comes from the `MessageInterface` and pass it on to the `cipher::Content` method in order to be encrypted. Checks were also put in place to ensure the stability and security of this class.

Once the `Container` class had been implemented, next up was the `Operative` class. This class, an abstract class which, also as the `Container` class, extends the `Content` class. This being a decorator is responsible, through itself and its sub-classes, `Spy` and `SpyMaster`, for decorating instances of the `Content` class.

The `Spy` and `SpyMaster` classes were implemented next. The `Spy` class was implemented to take care of the encryption level. This is because spies are not allowed to have access to the encrypted messages although they can send them, meaning that the `Spy` class needed to randomly increase the encryption level with each added spy. The `SpyMaster` class on the other hand is for spy masters, who have access to encrypted messages, and therefore this class is responsible for the decryption of the messages. This class was implemented by adding code that was able to decrypt the originally encrypted message by calling and using the same method, `cipher::Content`, that initially encrypted the message.

The methodology was effective in achieving the lab's goals. The classes were added successfully and the application functioned as described in the assignment PDF. The success of the lab was evaluated by testing the code thoroughly, by using the built in testing functionality provided by VS Code, by compiling the app using `mvn clean verify` and by running the java app itself to test the functionality and all the options. No issues or bugs were found.

However, there are some potential limitations to the methodology used in this lab. One limitation of the methodology used in this lab is that the code was not tested on a large scale. This means that it is possible that there may be issues that would only arise when the application has been used by a large enough number of users. For example, people use applications in different ways and only after prolonged exposure to different methods of usage and/or repeated useage on a large scale, are bugs finally found.

Another limitation of the methodology is that the code was not tested in a security-focused environment. This means that it is possible that there may be security vulnerabilities that could be exploited by malicious users. For example, the application may not be able to prevent unauthorized users from accessing parts of the code that are not meant to be accessed.

To address the limitations of the methodology used in this lab, the following measures can be taken to limit possible future problems:

* The code should be tested on a large scale to identify any issues that may arise when the application is used by a large number of users.
* The code should be tested in a security-focused environment to identify any security vulnerabilities that could be exploited by malicious users.

Overall, the methodology used in this lab was effective in achieving the lab's goals. However, there are some potential limitations that should be considered when using the code in a production environment.
