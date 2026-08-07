package com.KeyStone.DeliveryService.Service;

import java.util.List;

import com.KeyStone.DeliveryService.DTO.WorkOrderDTO;

public interface WorkOrderService {

    WorkOrderDTO createWorkOrder(WorkOrderDTO dto);

    List<WorkOrderDTO> getAllWorkOrders();

    WorkOrderDTO getWorkOrderById(Long id);

    WorkOrderDTO updateWorkOrder(Long id, WorkOrderDTO dto);

    void deleteWorkOrder(Long id);

}