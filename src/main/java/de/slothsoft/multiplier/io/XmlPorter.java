package de.slothsoft.multiplier.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

public class XmlPorter implements Exporter, Importer {

	private static final String[] EXTENSIONS = { "xml" };

	@Override
	public String getDisplayName() {
		return "XML File";
	}

	@Override
	public String[] getSupportedExtensions() {
		return EXTENSIONS.clone();
	}

	@Override
	public boolean canExport(Class<?> classToBeExported) {
		return canHandle(classToBeExported);
	}

	@Override
	public boolean canImport(Class<?> classToBeImported) {
		return canHandle(classToBeImported);
	}

	private boolean canHandle(Class<?> classToBeHandled) {
		XmlRootElement annotation = classToBeHandled.getAnnotation(XmlRootElement.class);
		return annotation != null;
	}

	@Override
	public Object importFile(InputStream source, Class<?> importedClass, Map<String, Object> config) {
		try {
			JAXBContext context = JAXBContext.newInstance(importedClass);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return unmarshaller.unmarshal(source);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void export(Object source, OutputStream target, Map<String, Object> config) {
		try {
			JAXBContext context = JAXBContext.newInstance(source.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
