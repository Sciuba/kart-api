package br.com.sciuba;

import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.sciuba.model.Lap;
import br.com.sciuba.service.KartService;
import br.com.sciuba.to.KartResultTO;
import br.com.sciuba.utils.FileReader;

public class KartMain {
	
	public static void main(String[] args) {
		
		FileReader fileReader = new FileReader();
		KartService kartService = new KartService();
		try {
			List<Lap> listLap = fileReader.read("logRace");
			List<KartResultTO> result = kartService.finalResults(listLap);
			Lap bestLap = result.get(0).getBetterLapOfRace();
			System.out.println("Resultado da Corrida:");
			
			result.forEach(r -> {
				System.out.println(r.getPosition() + "° - " + (r.getPilot().getId() > 10 ? r.getPilot().getId() : "0"+ r.getPilot().getId()) + " - " + String.format("%1$-" + 13 + "s", r.getPilot().getName()) + "   Qtd Voltas Completas:" + r.getNumberOfCompleteLaps()
						+ "  Tempo Total da Prova:" + r.getTotalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")) + "  Melhor Volta:" + r.getBestLap().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
						+ "  Velocidade Média:" + String.format("%.2f",r.getAverageSpeed()) + (r.getPosition() == 1 ? "  Tempo de Corrida:" + r.getPositionDiff() : "  Diferença para o primeiro colocado:" + r.getPositionDiff()));
			});
			
			System.out.println("\nMelhor Volta da Corrida:");
			System.out.println(bestLap.getPilot().getId() + " - " + bestLap.getPilot().getName() + " Volta:" + bestLap.getTimeLap().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
