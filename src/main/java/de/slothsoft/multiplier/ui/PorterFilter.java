package de.slothsoft.multiplier.ui;

import java.io.File;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.slothsoft.multiplier.io.Porter;

public class PorterFilter extends FileFilter {

	private final Porter porter;
	private final FileNameExtensionFilter extensionFilter;

	private Pattern pattern;

	public PorterFilter(Porter porter) {
		this.porter = porter;
		this.extensionFilter = new FileNameExtensionFilter(porter.getDisplayName(), porter.getSupportedExtensions());
	}

	@Override
	public boolean accept(File f) {
		return this.extensionFilter.accept(f) && acceptPattern(f);
	}

	private boolean acceptPattern(File f) {
		return (this.pattern == null) || this.pattern.matcher(f.toString()).matches();
	}

	@Override
	public String getDescription() {
		return this.extensionFilter.getDescription();
	}

	public Porter getPorter() {
		return this.porter;
	}

	public Pattern getPattern() {
		return this.pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

}
