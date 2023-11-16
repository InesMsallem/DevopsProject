package tn.esprit.rh.achat.entities.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.rh.achat.entities.Facture;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperateurRequestModel {
    private static final long serialVersionUID = 1L;

    private Long idOperateur;
    private String nom;
    private String prenom;

    private String password;
    private Set<Facture> factures;
}
