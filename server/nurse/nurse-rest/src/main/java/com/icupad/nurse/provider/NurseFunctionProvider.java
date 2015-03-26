package com.icupad.nurse.provider;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.icupad.nurse.model.Activity;
import com.icupad.nurse.model.NurseFunction;
import com.icupad.nurse.repository.NurseFunctionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class NurseFunctionProvider {
	
	private static final Multimap<String, String> rawData = LinkedListMultimap.create(10);

	private final NurseFunctionRepository functionRepository;
	
	static {
		String function;
		
		function = "Stan psychospołeczny, rozwój psychoruchowy";
		rawData.put(function, "Komunikacja werbalna / niewerbalna");
		rawData.put(function, "Zastępcze metody komunikacji");
		rawData.put(function, "Zapewnienie bezpieczeństwa");
		rawData.put(function, "Ograniczenie ruchów");
		rawData.put(function, "Włączenie rodziny do pielęgnacji");
		rawData.put(function, "Wizyta rodziny");
		rawData.put(function, "Wizyta osoby duchownej");
		rawData.put(function, "Zabawa / edukacja");
		rawData.put(function, "Samoopieka");
		
		function = "Oddychanie";
		rawData.put(function, "Ocena odsłuchowa");
		rawData.put(function, "Odsysanie wydzieliny + rozprężanie");
		rawData.put(function, "Ćwiczenia oddechowe");
		rawData.put(function, "Oklepywanie klatki piersiowej");
		rawData.put(function, "Drenaż ułożeniowy");
		rawData.put(function, "Nabulizacja / tlenoterapia");
		
		function = "Żywienie";
		rawData.put(function, "Karmienie smoczek / łużeczka");
		rawData.put(function, "Karmienie sonda / gastrostomia");
		rawData.put(function, "Karmienie piersią");
		
		function = "Wydalanie";
		rawData.put(function, "Stolec / Enema");
		rawData.put(function, "Masaż brzucha / czopek");
		rawData.put(function, "Cewnik urologiczny założenie / usunięcie");
		rawData.put(function, "Dobowa / godzinowa zbiórka moczu");
		rawData.put(function, "");
		
		function = "Skóra i błony śluzowe";
		rawData.put(function, "Toaleta całego ciała");
		rawData.put(function, "Toaleta częściowa");
		rawData.put(function, "Toaleta przeciwodleżynowa");
		rawData.put(function, "Toaleta jamy ustnej");
		rawData.put(function, "Pielęgnacja oczu");
		rawData.put(function, "Zmiana bielizny pościelowej");
		rawData.put(function, "Zmiana bielizny osobistej");
		rawData.put(function, "Toaleta pępka");
		rawData.put(function, "Kąpiel lecznicza");
		
		function = "Narząd ruchu";
		rawData.put(function, "Ułożenie na plecach");
		rawData.put(function, "Ułożenie na boku P / L");
		rawData.put(function, "Ułożenie na brzuchu");
		rawData.put(function, "Ćwiczenie bierne");
		rawData.put(function, "Ćwiczenie czynne");
		rawData.put(function, "Masaż");
		
		function = "Termoregulacja";
		rawData.put(function, "Chłodzenie fizyczne");
		rawData.put(function, "Ogrzewanie");
		rawData.put(function, "Inkubator");
		
		function = "Kaniule";
		rawData.put(function, "Płukanie kaniul tętniczych");
		rawData.put(function, "Płukanie kaniul obwodowych");
		rawData.put(function, "Płukanie kaniul centralnych / heparynizacja");
		rawData.put(function, "Zmiana oklejania kaniul");
		rawData.put(function, "Wymiana igły do portu");
		rawData.put(function, "Płukanie portu");
		
		function = "Diagnostyka";
		rawData.put(function, "RTG");
		rawData.put(function, "USG");
		rawData.put(function, "ECHO / EKG / EEG");
		rawData.put(function, "Transport poza oddział");
		
		function = "Zabiegi leczniczo-pielęgnacyjne";
		rawData.put(function, "Zmiana opatruniku na ranie");
		rawData.put(function, "Udrażanianie drenów");
		rawData.put(function, "Drenaż");
		rawData.put(function, "Fototerapia");
		rawData.put(function, "Kompresy / okłady");
		rawData.put(function, "Asystowanie przy zabiegu");
	}
	
	@Autowired
	public NurseFunctionProvider(NurseFunctionRepository nurseFunctionRepository) {
		this.functionRepository = nurseFunctionRepository;
	}
	
	public void provide() {
		List<NurseFunction> functions = new ArrayList<>();
		
		for (String functionName : rawData.keySet()) {
			NurseFunction function = new NurseFunction();
			function.setName(functionName);
			functions.add(function);
			for (String activityName : rawData.get(functionName)) {
				Activity activity = new Activity();
				activity.setName(activityName);
				function.addActivity(activity);
			}
		}
		
		functionRepository.save(functions);
	}

}
