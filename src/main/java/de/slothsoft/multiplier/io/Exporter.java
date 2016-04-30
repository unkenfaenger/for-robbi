package de.slothsoft.multiplier.io;

import java.io.OutputStream;
import java.util.Map;

public interface Exporter extends Porter {

	String CONFIG_EXTENSION = "extension";

	default boolean canExport(Class<?> classToBeExported) {
		return true;
	}

	void export(Object source, OutputStream target, Map<String, Object> config);
}
