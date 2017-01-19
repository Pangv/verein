package de.lebk.verein.member;

/**
 * Created by marcel on 28.12.16.
 */
public enum Role {
    OFFICER("Officer"),
    MEMBER("Member");

    private String displayName;

    Role(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
