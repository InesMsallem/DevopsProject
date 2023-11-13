package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.entities.dto.ReglementRequestModel;

import java.util.Date;
import java.util.List;



public interface IReglementService {

	List<Reglement> retrieveAllReglements();
	Reglement addReglement(ReglementRequestModel r);
	Reglement retrieveReglement(Long id);
	List<Reglement> retrieveReglementByFacture(Long idFacture);
	float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate); 

}
