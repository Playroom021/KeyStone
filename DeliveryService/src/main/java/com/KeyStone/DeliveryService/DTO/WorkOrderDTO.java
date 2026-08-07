package com.KeyStone.DeliveryService.DTO;

import com.KeyStone.DeliveryService.ENUM.Priority;
import com.KeyStone.DeliveryService.ENUM.WorkOrderStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkOrderDTO {

    private Long id;

    private String workOrderCode;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Priority is required")
    private Priority priority;

    private WorkOrderStatus status;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    private Long assignedToId;

}