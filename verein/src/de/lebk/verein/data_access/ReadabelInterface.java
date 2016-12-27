package de.lebk.verein.data_access;

import java.io.IOException;
import java.util.List;

/**
 * Created by marcel on 26.12.16.
 */
public interface ReadabelInterface {
	List<String> fetch() throws IOException;
}
