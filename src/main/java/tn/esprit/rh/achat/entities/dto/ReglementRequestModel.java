package tn.esprit.rh.achat.entities.dto;



import java.util.Date;

import tn.esprit.rh.achat.entities.Facture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReglementRequestModel {
	
	private Long idReglement;
	private float montantPaye;
	private float montantRestant;
	private Boolean payee;
	private Date dateReglement;
	private Facture facture;


}
