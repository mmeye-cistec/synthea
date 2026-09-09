package org.mitre.synthea.export;

import org.hl7.fhir.instance.model.api.IBaseBundle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BundleExporter {

	private static List<BiConsumer<IBaseBundle, Exporter.SupportedFhirVersion>> consumers = new ArrayList<>();

	public static void addConsumer(BiConsumer<IBaseBundle, Exporter.SupportedFhirVersion> consumer) {
		consumers.add(consumer);
	}

	public static void setConsumer(BiConsumer<IBaseBundle, Exporter.SupportedFhirVersion> consumer) {
		consumers = new ArrayList<>();
		consumers.add(consumer);
	}

	public static void export(IBaseBundle fhirBundle, Exporter.SupportedFhirVersion fhirVersion) {
		for (BiConsumer<IBaseBundle, Exporter.SupportedFhirVersion> consumer : consumers) {
			consumer.accept(fhirBundle, fhirVersion);
		}
	}
}
