package com.KeyStone.DeliveryService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KeyStone.DeliveryService.DTO.WorkOrderDTO;
import com.KeyStone.DeliveryService.Service.WorkOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
    name = "Work Order Management",
    description = "APIs for creating, updating, viewing and deleting work orders"
)
@RestController
@RequestMapping("/api/workorders")
@CrossOrigin(origins = "*")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @Operation(summary = "Create a new work order")
    @PostMapping
    public ResponseEntity<WorkOrderDTO> createWorkOrder(
            @Valid @RequestBody WorkOrderDTO dto) {

        return new ResponseEntity<>(
                workOrderService.createWorkOrder(dto),
                HttpStatus.CREATED);

    }

    @Operation(summary = "Get all work orders")
    @GetMapping
    public ResponseEntity<List<WorkOrderDTO>> getAllWorkOrders() {

        return ResponseEntity.ok(
                workOrderService.getAllWorkOrders());

    }


    @Operation(summary = "Get work order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<WorkOrderDTO> getWorkOrderById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                workOrderService.getWorkOrderById(id));

    }

    @Operation(summary = "Update work order")
    @PutMapping("/{id}")
    public ResponseEntity<WorkOrderDTO> updateWorkOrder(
            @PathVariable Long id,
            @Valid @RequestBody WorkOrderDTO dto) {

        return ResponseEntity.ok(
                workOrderService.updateWorkOrder(id, dto));

    }

    @Operation(summary = "Delete work order")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorkOrder(
            @PathVariable Long id) {

        workOrderService.deleteWorkOrder(id);

        return ResponseEntity.ok("Work Order deleted successfully.");

    }

}