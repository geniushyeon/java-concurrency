package com.geniushyeon.stock.service;

import com.geniushyeon.stock.domain.Stock;
import com.geniushyeon.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PessimisticLockStockService {

    private final StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findByWithPessimisticLock(id);

        stock.decrease(quantity);

        stockRepository.save(stock);
    }
}
