package com.KeyStone.DeliveryService.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeyStone.DeliveryService.DTO.WorkOrderDTO;
import com.KeyStone.DeliveryService.ENUM.WorkOrderStatus;
import com.KeyStone.DeliveryService.Entity.Customer;
import com.KeyStone.DeliveryService.Entity.UserAuth;
import com.KeyStone.DeliveryService.Entity.WorkOrder;
import com.KeyStone.DeliveryService.Exception.ResourceNotFoundException;
import com.KeyStone.DeliveryService.Repository.CustomerRepository;
import com.KeyStone.DeliveryService.Repository.UserAuthRepository;
import com.KeyStone.DeliveryService.Repository.WorkOrderRepository;
@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserAuthRepository userRepository;

    @Override
    public WorkOrderDTO createWorkOrder(WorkOrderDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        UserAuth user = null;

        if (dto.getAssignedToId() != null) {
            user = userRepository.findById(dto.getAssignedToId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));
        }

        WorkOrder workOrder = WorkOrder.builder()
                .workOrderCode(generateWorkOrderCode())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .priority(dto.getPriority())
                .status(WorkOrderStatus.NEW)
                .customer(customer)
                .assignedTo(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        workOrder = workOrderRepository.save(workOrder);

        return convertToDTO(workOrder);
    }

    @Override
    public List<WorkOrderDTO> getAllWorkOrders() {

        return workOrderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public WorkOrderDTO getWorkOrderById(Long id) {

        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work Order not found"));

        return convertToDTO(workOrder);

    }

    @Override
    public WorkOrderDTO updateWorkOrder(Long id, WorkOrderDTO dto) {

        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work Order not found"));

        workOrder.setTitle(dto.getTitle());
        workOrder.setDescription(dto.getDescription());
        workOrder.setPriority(dto.getPriority());
        workOrder.setUpdatedAt(LocalDateTime.now());

        if (dto.getStatus() != null) {
            workOrder.setStatus(dto.getStatus());
        }

        if (dto.getAssignedToId() != null) {

            UserAuth user = userRepository.findById(dto.getAssignedToId())
                    .orElseThrow(() -> new ResourceNotFoundException("Assigned user not found"));

            workOrder.setAssignedTo(user);

        }

        workOrderRepository.save(workOrder);

        return convertToDTO(workOrder);

    }

    @Override
    public void deleteWorkOrder(Long id) {

        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Work Order not found"));

        workOrderRepository.delete(workOrder);

    }

    private WorkOrderDTO convertToDTO(WorkOrder workOrder) {

        return WorkOrderDTO.builder()
                .id(workOrder.getId())
                .workOrderCode(workOrder.getWorkOrderCode())
                .title(workOrder.getTitle())
                .description(workOrder.getDescription())
                .priority(workOrder.getPriority())
                .status(workOrder.getStatus())
                .customerId(workOrder.getCustomer().getId())
                .assignedToId(
                        workOrder.getAssignedTo() != null
                                ? workOrder.getAssignedTo().getId()
                                : null)
                .build();

    }

    private String generateWorkOrderCode() {

        long count = workOrderRepository.count() + 1;

        return "WO-" + String.format("%04d", count);

    }

}