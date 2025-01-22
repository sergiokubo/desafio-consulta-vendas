package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SellerSalesDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SellerSalesDTO(s.seller.name, SUM(s.amount)) " +
            "FROM Sale s WHERE s.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY s.seller.name")
    List<SellerSalesDTO> findSalesByPeriod(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(s.id, s.date, s.amount, s.seller.name) " +
            "FROM Sale s " +
            "WHERE (:minDate IS NULL OR s.date >= :minDate) " +
            "AND (:maxDate IS NULL OR s.date <= :maxDate) " +
            "AND (:sellerName IS NULL OR LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :sellerName, '%')))")
    Page<SaleReportDTO> findSales(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate,
            @Param("sellerName") String sellerName,
            Pageable pageable);



}
