package de.slothsoft.multiplier.io;

public interface Porter {

	String CONFIG_FILE_NAME = "file name";

	String[] getSupportedExtensions();

	default String getDisplayName() {
		return getClass().getSimpleName();
	}
}
