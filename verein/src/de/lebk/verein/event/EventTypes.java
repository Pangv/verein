package de.lebk.verein.event;

/**
 *
 * @author sopaetzel
 */
public enum EventTypes {
    LAPIDATION, CHILDTOURNAMENT, GENERIC;

    public String returnType(EventTypes type) {
        switch (type) {
            case LAPIDATION:
                return "Steinigung";
            case CHILDTOURNAMENT:
                return "Kindertunier";
            case GENERIC:
                return "Allgemein";
        }
        return "Error";
    }

}
