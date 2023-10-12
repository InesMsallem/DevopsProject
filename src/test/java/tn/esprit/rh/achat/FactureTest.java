package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.esprit.rh.achat.controllers.FactureRestController;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.IFactureService;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FactureTest {

    @InjectMocks
    private FactureRestController factureRestController;

    @Mock
    private IFactureService factureService;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FactureRepository factureRepository;

    @Autowired
    private FactureServiceImpl factureServiceImpl;


    @Test
    //retrieve facture
    void testRetrieveAllFactures() {
        ArrayList<Facture> factureList = new ArrayList<>();
        when(factureRepository.findAll()).thenReturn(factureList);
        List<Facture> actualRetrieveAllProduitsResult = factureServiceImpl.retrieveAllFactures();
        assertSame(factureList, actualRetrieveAllProduitsResult);
        assertTrue(actualRetrieveAllProduitsResult.isEmpty());
        verify(factureRepository).findAll();
    }

    @Test
        //add facture
    void testAddFacture() {
        Facture facture = new Facture();
        facture.setIdFacture(1L);
        facture.setMontantRemise(100.0f);
        facture.setMontantFacture(500.0f);
        facture.setDateCreationFacture(new Date());
        facture.setDateDerniereModificationFacture(new Date());
        facture.setArchivee(true);

        when(factureRepository.save(any(Facture.class))).thenReturn(facture);

        Facture newFacture = factureServiceImpl.addFacture(facture);

        assertNotNull(newFacture);
        assertEquals(1L, newFacture.getIdFacture());
        assertEquals(100.0f, newFacture.getMontantRemise());
        assertEquals(500.0f, newFacture.getMontantFacture());
        assertTrue(newFacture.getArchivee());
        verify(factureRepository).save(any(Facture.class));
    }

    @Test
    //cancel facture
    public void testCancelFacture() throws Exception {
        Long factureId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.put("/facture/cancel-facture/{facture-id}", factureId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
