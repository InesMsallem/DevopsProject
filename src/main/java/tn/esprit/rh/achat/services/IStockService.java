package tn.esprit.rh.achat.services;

import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.entities.dto.StockRequestModel;

import java.util.List;



public interface IStockService {

	List<Stock> retrieveAllStocks();

	Stock addStock(StockRequestModel s);

	void deleteStock(Long id);

	Stock updateStock(StockRequestModel u);

	Stock retrieveStock(Long id);

	String retrieveStatusStock();
}
