package de.lebk.verein.member;

/**
 * @author mraddatz
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
