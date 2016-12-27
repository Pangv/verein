package de.lebk.verein.data_access;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcel on 26.12.16.
 */
public abstract class FileService<T> implements ReadabelInterface, WriteabelInterface<T> {
	protected List<String> rawEntities = new ArrayList();
	protected List<T> entities = new ArrayList();
	protected Path path = Paths.get(WriteabelInterface.PATH + "/default.txt");

	protected FileService(Path path) {
		this.path = path;
	}


	public List<String> fetch() throws IOException {
		if (entities.isEmpty()) {
			rawEntities.addAll(Files.readAllLines(path, Charset.defaultCharset()));
			return this.rawEntities;
		} else {
			return this.rawEntities;
		}
	}

	public void save(List<T> entities) throws IOException {
		this.rawEntities.clear();
		for (T entity : entities) {
			this.rawEntities.add(entity.toString());
		}

		Files.write(path, this.rawEntities, Charset.defaultCharset(), StandardOpenOption.CREATE);
	}

	;

	public void refresh() throws IOException {
		this.entities.clear();
		this.fetch();
	}
}
