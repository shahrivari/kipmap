package io.github.shahrivari.kipmap;

public class Preconditions {
    public static final void checkArg(boolean bool, String message) {
        if (!bool)
            throw new IllegalArgumentException(message);
    }
}
