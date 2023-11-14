package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@ContextConfiguration(classes = {OperateurServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class OperateurTest {



    @MockBean
    private OperateurRepository operateurRepository;

    @Autowired
    private OperateurServiceImpl operateurServiceImpl;
    @Test
    void testRetrieveAllOperateurs() {
        ArrayList<Operateur> operateurList = new ArrayList<>();
        when(operateurRepository.findAll()).thenReturn(operateurList);
        List<Operateur> actualRetrieveAllOperateursResult = operateurServiceImpl.retrieveAllOperateurs();
        assertSame(operateurList, actualRetrieveAllOperateursResult);
        assertTrue(actualRetrieveAllOperateursResult.isEmpty());
        verify(operateurRepository).findAll();
    }

    @Test
    void testDeleteOperateur() {
        doNothing().when(operateurRepository).deleteById((Long) any());
        operateurServiceImpl.deleteOperateur(123L);
        verify(operateurRepository).deleteById((Long) any());
    }
}