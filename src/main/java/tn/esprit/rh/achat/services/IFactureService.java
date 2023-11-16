package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.dto.FactureRequestModel;

import java.util.Date;
import java.util.List;



public interface IFactureService {
	List<Facture> retrieveAllFactures();

	List<Facture> getFacturesByFournisseur(Long idFournisseur);

	Facture addFacture(FactureRequestModel f);

	void cancelFacture(Long id);

	Facture retrieveFacture(Long id);
	
	void assignOperateurToFacture(Long idOperateur, Long idFacture);

	float pourcentageRecouvrement(Date startDate, Date endDate);

}
