package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OperateurTest {

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Mock
    private OperateurRepository operateurRepository;

    @BeforeEach
    void setUp() {
        // Setup any initialization or common behavior here.
    }

    @Test
    void testRetrieveAllOperateurs() {
        // Arrange
        List<Operateur> operateurList = new ArrayList<>();
        operateurList.add(new Operateur(1L, "John", "Doe", "password", new HashSet<>()));
        when(operateurRepository.findAll()).thenReturn(operateurList);

        // Act
        List<Operateur> result = operateurService.retrieveAllOperateurs();

        // Assert
        assertEquals(1, result.size());
        verify(operateurRepository, times(1)).findAll();
    }

    @Test
    void testRetrieveOperateur() {
        // Arrange
        Long operateurId = 1L;
        Operateur operateur = new Operateur(operateurId, "John", "Doe", "password", new HashSet<>());
        when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(operateur));

        // Act
        Operateur result = operateurService.retrieveOperateur(operateurId);

        // Assert
        assertEquals(operateur, result);
    }

    @Test
    void testRetrieveOperateur_NotFound() {
        // Arrange
        Long operateurId = 1L;
        when(operateurRepository.findById(operateurId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> operateurService.retrieveOperateur(operateurId));
    }

    @Test
    void testAddOperateur() {
        // Arrange
        Operateur newOperateur = new Operateur(null, "Alice", "Smith", "newPassword", new HashSet<>());
        when(operateurRepository.save(newOperateur)).thenReturn(new Operateur(1L, "Alice", "Smith", "newPassword", new HashSet<>()));

        // Act
        Operateur result = operateurService.addOperateur(newOperateur);

        // Assert
        assertEquals(1L, result.getIdOperateur());
    }

    @Test
    void testUpdateOperateur() {
        // Arrange
        Operateur existingOperateur = new Operateur(1L, "John", "Doe", "password", new HashSet<>());
        when(operateurRepository.save(existingOperateur)).thenReturn(existingOperateur);

        // Act
        Operateur updatedOperateur = operateurService.updateOperateur(existingOperateur);

        // Assert
        assertEquals("John", updatedOperateur.getNom());
        assertEquals("Doe", updatedOperateur.getPrenom());
        assertEquals("password", updatedOperateur.getPassword());
    }

    @Test
    void testDeleteOperateur() {
        // Arrange
        Long operateurId = 1L;

        // Act
        operateurService.deleteOperateur(operateurId);

        // Assert
        verify(operateurRepository, times(1)).deleteById(operateurId);
    }
}
