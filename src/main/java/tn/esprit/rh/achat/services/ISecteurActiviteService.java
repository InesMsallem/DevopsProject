package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.entities.dto.SecteurActiviteRequestModel;

import java.util.List;



public interface ISecteurActiviteService {

	List<SecteurActivite> retrieveAllSecteurActivite();

	SecteurActivite addSecteurActivite(SecteurActiviteRequestModel sa);

	void deleteSecteurActivite(Long id);

	SecteurActivite updateSecteurActivite(SecteurActiviteRequestModel sa);

	SecteurActivite retrieveSecteurActivite(Long id);

}
