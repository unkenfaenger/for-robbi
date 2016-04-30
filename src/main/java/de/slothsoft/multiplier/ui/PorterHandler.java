package de.slothsoft.multiplier.ui;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import de.slothsoft.multiplier.Multiplier;
import de.slothsoft.multiplier.io.Exporter;
import de.slothsoft.multiplier.io.Importer;
import de.slothsoft.multiplier.io.Porter;

public class PorterHandler {

	private static final String PREF_LAST_PATH = "lastPath.";

	@SuppressWarnings("unchecked")
	public <T> T performImport(Component parent, Class<T> importedClass) {
		JFileChooser fileChooser = createFileChooser(importedClass);
		addChoosableImageFilter(fileChooser, Importer.class, p -> p.canImport(importedClass));
		if (fileChooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
			approvedFileChooser(fileChooser, importedClass);

			PorterFilter filter = (PorterFilter) fileChooser.getFileFilter();
			Map<String, Object> config = createConfig(fileChooser);

			try (FileInputStream source = new FileInputStream(fileChooser.getSelectedFile())) {
				return (T) ((Importer) filter.getPorter()).importFile(source, importedClass, config);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(parent, e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return null;
	}

	public <T> void performExport(Component parent, T source) {
		performExport(parent, source.getClass(), source);
	}

	public <T> void performExport(Component parent, Class<? extends T> exportedClass, T source) {
		JFileChooser fileChooser = createFileChooser(exportedClass);
		addChoosableImageFilter(fileChooser, Exporter.class, p -> p.canExport(exportedClass));
		if (fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
			approvedFileChooser(fileChooser, exportedClass);

			PorterFilter filter = (PorterFilter) fileChooser.getFileFilter();
			File file = prepareExportFile(fileChooser.getSelectedFile(), filter.getPorter().getSupportedExtensions());
			Map<String, Object> config = createConfig(fileChooser);
			config.put(Exporter.CONFIG_EXTENSION, getExtension(file.toString()));

			try (FileOutputStream target = new FileOutputStream(file)) {
				((Exporter) filter.getPorter()).export(source, target, config);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(parent, e.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private File prepareExportFile(File selectedFile, String[] possibleExtensions) {
		String extension = getExtension(selectedFile.toString());
		return extension != null ? selectedFile : new File(selectedFile.toString() + '.' + possibleExtensions[0]);
	}

	private String getExtension(String file) {
		int dotIndex = file.lastIndexOf('.');
		return dotIndex >= 0 ? file.substring(dotIndex + 1, file.length()) : null;
	}

	private JFileChooser createFileChooser(Class<?> classToBeHandled) {
		String lastPath = Multiplier.getPreferences().get(PREF_LAST_PATH + classToBeHandled, null);

		JFileChooser fileChooser = new JFileChooser() {

			private static final long serialVersionUID = 5560186093725519288L;

			private FileFilter lastFilter;

			@Override
			public void setFileFilter(FileFilter filter) {
				if (filter != null && filter.getClass().getSimpleName().equals("GlobFilter") && this.lastFilter != null) {
					try {
						Field patternField = filter.getClass().getDeclaredField("pattern");
						patternField.setAccessible(true);
						Pattern pattern = (Pattern) patternField.get(filter);
						for (FileFilter porterFilter : getChoosableFileFilters())
							((PorterFilter) porterFilter).setPattern(pattern);
						super.setFileFilter(this.lastFilter);
						return;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				super.setFileFilter(filter);
				if (filter != null) this.lastFilter = filter;
			}

		};
		fileChooser.setCurrentDirectory(lastPath == null ? null : new File(lastPath));
		fileChooser.setAcceptAllFileFilterUsed(false);
		return fileChooser;
	}

	private <P extends Porter> void addChoosableImageFilter(JFileChooser fileChooser, Class<P> porterClass,
			Predicate<P> shouldInclude) {
		for (P porter : ServiceLoader.load(porterClass)) {
			if (shouldInclude.test(porter)) fileChooser.addChoosableFileFilter(new PorterFilter(porter));
		}
	}

	private void approvedFileChooser(JFileChooser fileChooser, Class<?> classToBehandled) {
		Multiplier.getPreferences()
				.put(PREF_LAST_PATH + classToBehandled, fileChooser.getCurrentDirectory().toString());
	}

	private Map<String, Object> createConfig(JFileChooser fileChooser) {
		Map<String, Object> config = new HashMap<>();
		config.put(Porter.CONFIG_FILE_NAME, fileChooser.getSelectedFile().toString());
		return config;
	}
}
