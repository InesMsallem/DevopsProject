package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.entities.dto.CategorieProduitRequestModel;

import java.util.List;




public interface ICategorieProduitService {

	List<CategorieProduit> retrieveAllCategorieProduits();

	CategorieProduit addCategorieProduit(CategorieProduitRequestModel cp);

	void deleteCategorieProduit(Long id);

	CategorieProduit updateCategorieProduit(CategorieProduitRequestModel cp);

	CategorieProduit retrieveCategorieProduit(Long id);
}
