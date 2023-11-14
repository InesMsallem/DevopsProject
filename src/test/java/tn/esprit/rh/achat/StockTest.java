package tn.esprit.rh.achat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {StockServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class StockTest {

    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockServiceImpl stockServiceImpl;

    @Test
    void testRetrieveAllStocks() {
        // Arrange
        when(stockRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Stock> actualRetrieveAllStocksResult = stockServiceImpl.retrieveAllStocks();

        // Assert
        assertNotNull(actualRetrieveAllStocksResult);
        assertTrue(actualRetrieveAllStocksResult.isEmpty());
        verify(stockRepository).findAll();
    }

    @Test
    void testRetrieveStockById() {
        // Arrange
        Long stockId = 1L;
        Stock expectedStock = new Stock(/* construct stock object here */);
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(expectedStock));

        // Act
        Stock actualStock = stockServiceImpl.retrieveStock(stockId);

        // Assert
        assertNotNull(actualStock);
        assertEquals(expectedStock, actualStock);
        verify(stockRepository).findById(stockId);
    }

    @Test
    void testAddStock() {
        // Arrange
        Stock stockToAdd = new Stock(/* construct stock object here */);
        when(stockRepository.save(stockToAdd)).thenReturn(stockToAdd);

        // Act
        Stock addedStock = stockServiceImpl.addStock(stockToAdd);

        // Assert
        assertNotNull(addedStock);
        assertEquals(stockToAdd, addedStock);
        verify(stockRepository).save(stockToAdd);
    }

    @Test
    void testUpdateStock() {
        // Arrange
        Stock existingStock = new Stock(/* construct existing stock object here */);
        when(stockRepository.save(existingStock)).thenReturn(existingStock);

        // Act
        Stock updatedStock = stockServiceImpl.updateStock(existingStock);

        // Assert
        assertNotNull(updatedStock);
        assertEquals(existingStock, updatedStock);
        verify(stockRepository).save(existingStock);
    }

    @Test
    void testDeleteStock() {
        // Arrange
        Long stockId = 123L;
        doNothing().when(stockRepository).deleteById(stockId);

        // Act
        stockServiceImpl.deleteStock(stockId);

        // Assert
        verify(stockRepository).deleteById(stockId);
    }
}