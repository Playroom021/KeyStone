package com.KeyStone.DeliveryService.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstrctor
@NoArgsConstructor
@Builder

public class EmailLogDTO {

    public String recipientEmail;
    public String subject;
    public String Body;

}
