package io.silverdev637.sjge;

import java.util.Locale;

public enum VersionTag {

    DEV("dev", 0),
    SNAPSHOT("snapshot", 1),
    ALPHA("alpha", 2),
    BETA("beta", 3),
    RC("rc", 4),
    RELEASE("", 5); // without suffix

    private final String text;
    private final int priority;

    VersionTag(String text, int priority) {
        this.text = text;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
    
    @Override
    public String toString() {
        return text;
    }

    /**
     * Detects the label based on a suffix.
     * 
     * Ex: "-dev", "alpha", "-SNAPSHOT"
     */
    public static VersionTag fromSuffix(String suffix) {
        if (suffix == null || suffix.isBlank()) {
            return RELEASE;
        }

        String s = suffix.toLowerCase(Locale.ROOT);

        for (VersionTag tag : values()) {
            if (!tag.text.isEmpty() && s.contains(tag.text)) {
                return tag;
            }
        }

        // unknown suffix → treat as generic prerelease
        return DEV;
    }
}
