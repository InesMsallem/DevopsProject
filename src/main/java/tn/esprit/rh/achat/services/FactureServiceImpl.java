package tn.esprit.rh.achat.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import lombok.extern.slf4j.Slf4j;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.entities.dto.FactureRequestModel;
import tn.esprit.rh.achat.repositories.*;
import tn.esprit.rh.achat.services.IFactureService;

@Service
@Slf4j
@Transactional
public class FactureServiceImpl implements IFactureService {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	OperateurRepository operateurRepository;
	@Autowired
	DetailFactureRepository detailFactureRepository;
	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	ProduitRepository produitRepository;
    @Autowired
	ReglementServiceImpl reglementService;
    @Autowired
	ReglementRepository reglementRepository;
    
    ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<Facture> retrieveAllFactures() {
		List<Facture> factures =  factureRepository.findAll();
		for (Facture facture : factures) {
			log.info(" facture : " + facture);
		}
		return factures;
	}

	
	public Facture addFacture(FactureRequestModel f) {
		Facture facture = modelMapper.map(f,Facture.class);
		return factureRepository.save(facture);
	}

	
	public Facture addDetailsFacture(Facture f, Set<DetailFacture> detailsFacture) {
		float montantFacture = 0;
		float montantRemise = 0;
		for (DetailFacture detail : detailsFacture) {
			
			Optional<Produit> produit = produitRepository.findById(detail.getProduit().getIdProduit());
		if(produit.isPresent()) {
			
			float prixTotalDetail = detail.getQteCommandee() * produit.get().getPrix();
		
			float montantRemiseDetail = (prixTotalDetail * detail.getPourcentageRemise()) / 100;
			float prixTotalDetailRemise = prixTotalDetail - montantRemiseDetail;
			detail.setMontantRemise(montantRemiseDetail);
			detail.setPrixTotalDetail(prixTotalDetailRemise);
			
			montantFacture = montantFacture + prixTotalDetailRemise;
			
			montantRemise = montantRemise + montantRemiseDetail;
			detailFactureRepository.save(detail);
		}
		f.setMontantFacture(montantFacture);
		f.setMontantRemise(montantRemise);
		}
		return f;
	}

	@Override
	public void cancelFacture(Long factureId) {
	
		Facture facture = factureRepository.findById(factureId).orElse(new Facture());
		facture.setArchivee(true);
		factureRepository.save(facture);
	
		factureRepository.updateFacture(factureId);
	}

	@Override
	public Facture retrieveFacture(Long factureId) {
		Facture facture = factureRepository.findById(factureId).orElse(null);
		log.info("facture :" + facture);
		return facture;
	}

	@Override
	public List<Facture> getFacturesByFournisseur(Long idFournisseur) {
		List<Facture> f = new ArrayList<>();
		Optional <Fournisseur> fournisseur = fournisseurRepository.findById(idFournisseur);
		if(fournisseur.isPresent()) {
			 f=(List<Facture>) fournisseur.get().getFactures() ;
		}
		return f;
	}

	@Override
	public void assignOperateurToFacture(Long idOperateur, Long idFacture) {
		
		Optional <Operateur> operateur = operateurRepository.findById(idOperateur);
		if (operateur.isPresent()) {
			Optional <Facture> facture = factureRepository.findById(idFacture);
			if (facture.isPresent())
			{
				operateur.get().getFactures().add(facture.get());
				operateurRepository.save(operateur.get());	
			}
		
		}
		
		
	}
	
	@Override
	public float pourcentageRecouvrement(Date startDate, Date endDate) {
		float totalFacturesEntreDeuxDates = factureRepository.getTotalFacturesEntreDeuxDates(startDate,endDate);
		float totalRecouvrementEntreDeuxDates =reglementRepository.getChiffreAffaireEntreDeuxDate(startDate,endDate);
		return (totalRecouvrementEntreDeuxDates/totalFacturesEntreDeuxDates)*100;
	}


	

}