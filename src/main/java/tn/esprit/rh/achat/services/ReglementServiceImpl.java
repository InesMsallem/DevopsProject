package tn.esprit.rh.achat.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.entities.dto.ReglementRequestModel;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ReglementRepository;

@Service
public class ReglementServiceImpl implements IReglementService {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	ReglementRepository reglementRepository;
	ModelMapper modelMapper = new ModelMapper();
	@Override
	public List<Reglement> retrieveAllReglements() {
		return (List<Reglement>) reglementRepository.findAll();
	}

	@Override
	public Reglement addReglement(ReglementRequestModel r) {
		Reglement reg = modelMapper.map(r,Reglement.class);
		return reglementRepository.save(reg);
	}

	@Override
	public Reglement retrieveReglement(Long id) {
		return reglementRepository.findById(id).orElse(null);
	}

	@Override
	public List<Reglement> retrieveReglementByFacture(Long idFacture) {
		return reglementRepository.retrieveReglementByFacture(idFacture);
		

	}

	@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		return reglementRepository.getChiffreAffaireEntreDeuxDate( startDate, endDate);
	}

}
