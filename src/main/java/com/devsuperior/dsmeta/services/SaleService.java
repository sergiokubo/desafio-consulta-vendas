package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerSalesDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}


	public List<SellerSalesDTO> getSalesByPeriod(LocalDate minDate, LocalDate maxDate) {
		// Verificar se maxDate foi informado, caso contrário usar a data atual
		if (maxDate == null) {
			maxDate = LocalDate.now();
		}

		// Verificar se minDate foi informado, caso contrário usar 1 ano antes do maxDate
		if (minDate == null) {
			minDate = maxDate.minusYears(1L);
		}

		return repository.findSalesByPeriod(minDate, maxDate);
	}
}
