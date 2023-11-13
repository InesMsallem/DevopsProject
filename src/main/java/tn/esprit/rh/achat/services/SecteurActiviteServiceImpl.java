package tn.esprit.rh.achat.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.entities.dto.SecteurActiviteRequestModel;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

@Service
public class SecteurActiviteServiceImpl implements ISecteurActiviteService{

	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;
	ModelMapper modelMapper= new ModelMapper();
	@Override
	public List<SecteurActivite> retrieveAllSecteurActivite() {
		return (List<SecteurActivite>) secteurActiviteRepository.findAll();
	}

	@Override
	public SecteurActivite addSecteurActivite(SecteurActiviteRequestModel sa) {
		return secteurActiviteRepository.save(modelMapper.map(sa,SecteurActivite.class));
	}

	@Override
	public void deleteSecteurActivite(Long id) {
		secteurActiviteRepository.deleteById(id);
		
	}

	@Override
	public SecteurActivite updateSecteurActivite(SecteurActiviteRequestModel sa) {
		return  secteurActiviteRepository.save(modelMapper.map(sa,SecteurActivite.class));
	}

	@Override
	public SecteurActivite retrieveSecteurActivite(Long id) {
		return secteurActiviteRepository.findById(id).orElse(null);
	}

}
