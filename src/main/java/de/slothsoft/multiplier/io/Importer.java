package de.slothsoft.multiplier.io;

import java.io.InputStream;
import java.util.Map;

public interface Importer extends Porter {

	default boolean canImport(Class<?> classToBeImported) {
		return true;
	}

	Object importFile(InputStream source, Class<?> importedClass, Map<String, Object> config);
}
