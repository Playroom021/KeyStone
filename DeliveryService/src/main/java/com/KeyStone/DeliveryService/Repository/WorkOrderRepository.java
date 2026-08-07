package com.KeyStone.DeliveryService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.KeyStone.DeliveryService.Entity.WorkOrder;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

    Optional<WorkOrder> findByWorkOrderCode(String workOrderCode);

}

