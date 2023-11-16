package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.entities.dto.OperateurRequestModel;

import java.util.List;




public interface IOperateurService {

	List<Operateur> retrieveAllOperateurs();

	Operateur addOperateur(OperateurRequestModel o);

	void deleteOperateur(Long id);

	Operateur updateOperateur(OperateurRequestModel o);

	Operateur retrieveOperateur(Long id);

}
