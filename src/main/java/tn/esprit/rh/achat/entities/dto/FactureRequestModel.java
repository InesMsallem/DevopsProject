package tn.esprit.rh.achat.entities.dto;

import java.util.Date;
import java.util.Set;

import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.Reglement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactureRequestModel {

	private Long idFacture;
	private float montantRemise;
	private float montantFacture;
	private Date dateCreationFacture;
	private Date dateDerniereModificationFacture;
	private Boolean archivee;
	private Set<DetailFacture> detailsFacture;
	private Fournisseur fournisseur;
	private Set<Reglement> reglements;
}
