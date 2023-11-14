package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.dto.FournisseurRequestModel;

import java.util.List;



public interface IFournisseurService {

	List<Fournisseur> retrieveAllFournisseurs();

	Fournisseur addFournisseur(FournisseurRequestModel fournisseur);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(FournisseurRequestModel fournisseur);

	Fournisseur retrieveFournisseur(Long id);

	void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur);

}