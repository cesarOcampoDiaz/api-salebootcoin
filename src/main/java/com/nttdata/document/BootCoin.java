package com.nttdata.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BootCoin {
	@Id
	private String id;
    private Integer clientId;
    private Integer paymentMethod;
    private Integer currencyID;
    private Double amount;
    private LocalDateTime dateTransaction;

    
}
