package com.icupad.nurse.provider;

import com.icupad.nurse.model.Activity;
import com.icupad.nurse.model.NurseFunction;
import com.icupad.nurse.repository.NurseFunctionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class NurseFunctionProvider {
	
	private final List<NurseFunction> functions = new ArrayList<>();

	private final NurseFunctionRepository functionRepository;
	
	@Autowired
	public NurseFunctionProvider(NurseFunctionRepository nurseFunctionRepository) {
		this.functionRepository = nurseFunctionRepository;
	}
	
	public void provide() {
		NurseFunction function;
		
		function = new NurseFunction("01_SPS", "Stan psychospołeczny, rozwój psychoruchowy");
		functions.add(function);
		function.addActivity(new Activity("01_SPS_KWN", "Komunikacja werbalna / niewerbalna"));
		function.addActivity(new Activity("02_SPS_ZMK", "Zastępcze metody komunikacji"));
		function.addActivity(new Activity("02_SPS_ZMK", "Zastępcze metody komunikacji"));
		function.addActivity(new Activity("03_SPS_ZBE", "Zapewnienie bezpieczeństwa"));
		function.addActivity(new Activity("04_SPS_ORU", "Ograniczenie ruchów"));
		function.addActivity(new Activity("05_SPS_WRO", "Włączenie rodziny do pielęgnacji"));
		function.addActivity(new Activity("06_SPS_WIR", "Wizyta rodziny"));
		function.addActivity(new Activity("07_SPS_WOD", "Wizyta osoby duchownej"));
		function.addActivity(new Activity("08_SPS_ZED", "Zabawa / edukacja"));
		function.addActivity(new Activity("09_SPS_SAM", "Samoopieka"));
		
		function = new NurseFunction("02_ODD", "Oddychanie");
		functions.add(function);
		function.addActivity(new Activity("10_ODD_OOD", "Ocena odsłuchowa"));
		function.addActivity(new Activity("11_ODD_OWR", "Odsysanie wydzieliny + rozprężanie"));
		function.addActivity(new Activity("12_ODD_COD", "Ćwiczenia oddechowe"));
		function.addActivity(new Activity("13_ODD_OKP", "Oklepywanie klatki piersiowej"));
		function.addActivity(new Activity("14_ODD_DRU", "Drenaż ułożeniowy"));
		function.addActivity(new Activity("15_ODD_NTL", "Nabulizacja / tlenoterapia"));
		
		function = new NurseFunction("03_ZYW", "Żywienie");
		functions.add(function);
		function.addActivity(new Activity("16_ZYW_SMO", "Karmienie smoczek / łyżeczka"));
		function.addActivity(new Activity("17_ZYW_SON", "Karmienie sonda / gastrostomia"));
		function.addActivity(new Activity("18_ZYW_PIE", "Karmienie piersią"));
		
		function = new NurseFunction("04_WYD", "Wydalanie");
		functions.add(function);
		function.addActivity(new Activity("19_WYD_STO", "Stolec / Enema"));
		function.addActivity(new Activity("20_WYD_MAS", "Masaż brzucha / czopek"));
		function.addActivity(new Activity("21_WYD_SEW", "Cewnik urologiczny założenie / usunięcie"));
		function.addActivity(new Activity("22_WYD_MOC", "Dobowa / godzinowa zbiórka moczu"));
		function.addActivity(new Activity("23_WYD_RUR", "Sucha rurka"));
		
		function = new NurseFunction("05_SBS", "Skóra i błony śluzowe");
		functions.add(function);
		function.addActivity(new Activity("24_SBS_TCI", "Toaleta całego ciała"));
		function.addActivity(new Activity("25_SBS_TCZ", "Toaleta częściowa"));
		function.addActivity(new Activity("26_SBS_TPR", "Toaleta przeciwodleżynowa"));
		function.addActivity(new Activity("27_SBS_TJB", "Toaleta jamy ustnej"));
		function.addActivity(new Activity("28_SBS_OCZ", "Pielęgnacja oczu"));
		function.addActivity(new Activity("29_SBS_BPO", "Zmiana bielizny pościelowej"));
		function.addActivity(new Activity("30_SBS_BOS", "Zmiana bielizny osobistej"));
		function.addActivity(new Activity("31_SBS_TPE", "Toaleta pępka"));
		function.addActivity(new Activity("32_SBS_KAP", "Kąpiel lecznicza"));
		
		function = new NurseFunction("06_NRU", "Narząd ruchu");
		functions.add(function);
		function.addActivity(new Activity("33_NRU_PLE", "Ułożenie na plecach"));
		function.addActivity(new Activity("34_NRU_BOK", "Ułożenie na boku P / L"));
		function.addActivity(new Activity("35_NRU_BRZ", "Ułożenie na brzuchu"));
		function.addActivity(new Activity("36_NRU_CWB", "Ćwiczenie bierne"));
		function.addActivity(new Activity("37_NRU_CWC", "Ćwiczenie czynne"));
		function.addActivity(new Activity("38_NRU_MAS", "Masaż"));
		
		function = new NurseFunction("07_TER", "Termoregulacja");
		functions.add(function);
		function.addActivity(new Activity("39_TER_CHL", "Chłodzenie fizyczne"));
		function.addActivity(new Activity("40_TER_OGR", "Ogrzewanie"));
		function.addActivity(new Activity("41_TER_INK", "Inkubator"));
		
		function = new NurseFunction("08_KAN", "Kaniule");
		functions.add(function);
		function.addActivity(new Activity("42_KAN_PTE", "Płukanie kaniul tętniczych"));
		function.addActivity(new Activity("43_KAN_POB", "Płukanie kaniul obwodowych"));
		function.addActivity(new Activity("44_KAN_PCH", "Płukanie kaniul centralnych / heparynizacja"));
		function.addActivity(new Activity("45_KAN_ZMO", "Zmiana oklejania kaniul"));
		function.addActivity(new Activity("46_KAN_WYI", "Wymiana igły do portu"));
		function.addActivity(new Activity("47_KAN_WPO", "Płukanie portu"));
		
		function = new NurseFunction("09_DIA", "Diagnostyka");
		functions.add(function);
		function.addActivity(new Activity("48_DIA_RTG", "RTG"));
		function.addActivity(new Activity("49_DIA_USG", "USG"));
		function.addActivity(new Activity("50_DIA_EEE", "ECHO / EKG / EEG"));
		function.addActivity(new Activity("51_DIA_TRA", "Transport poza oddział"));
		
		function = new NurseFunction("10_ZLP", "Zabiegi leczniczo-pielęgnacyjne");
		functions.add(function);
		function.addActivity(new Activity("52_ZLP_OPA", "Zmiana opatruniku na ranie"));
		function.addActivity(new Activity("53_ZLP_UDR", "Udrażanianie drenów"));
		function.addActivity(new Activity("54_ZLP_DRE", "Drenaż"));
		function.addActivity(new Activity("55_ZLP_FOT", "Fototerapia"));
		function.addActivity(new Activity("56_ZLP_KOM", "Kompresy / okłady"));
		function.addActivity(new Activity("57_ZLP_ASY", "Asystowanie przy zabiegu"));
		
		functionRepository.save(functions);
	}

}
