package com.sfw.repositories;

import com.sfw.entities.CustomerLoyalty;
import org.springframework.data.jpa.repository.JpaRepository;


//modified Integer as Long

public interface CustomerLoyaltyRepository extends JpaRepository<CustomerLoyalty,Integer> {
}
