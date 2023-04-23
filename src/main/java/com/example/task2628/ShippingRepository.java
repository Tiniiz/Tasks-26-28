package com.example.task2628;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShippingRepository extends JpaRepository<Cargo, Long> {
    @Query("SELECT c FROM Cargo c WHERE CONCAT(LOWER(c.name), ' ', LOWER(c.content), ' ', LOWER(c.citySend), ' ', LOWER(c.dateSend),  ' ', LOWER(c.cityArrive), ' ', LOWER(c.dateArrive)) LIKE %?1%")
    List<Cargo> search(String keyword);

    @Query("SELECT c FROM Cargo c WHERE CONCAT(LOWER(c.name), ' ', LOWER(c.content), ' ', LOWER(c.citySend), ' ', LOWER(c.dateSend),  ' ', LOWER(c.cityArrive), ' ', LOWER(c.dateArrive)) LIKE %?1% ORDER BY ?2")
    List<Cargo> sortedSearchASC(String keyword, int order_by);

    @Query("SELECT c FROM Cargo c WHERE CONCAT(LOWER(c.name), ' ', LOWER(c.content), ' ', LOWER(c.citySend), ' ', LOWER(c.dateSend),  ' ', LOWER(c.cityArrive), ' ', LOWER(c.dateArrive)) LIKE %?1% ORDER BY ?2 DESC")
    List<Cargo> sortedSearchDESC(String keyword, int order_by);
}

