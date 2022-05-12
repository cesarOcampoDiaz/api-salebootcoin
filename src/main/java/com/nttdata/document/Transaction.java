package com.nttdata.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped.Nullable;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "transaction_boot_coin")
public class Transaction {
	@Id
	private String id;
    private Integer paymentMethod;
    private String phone;
    private Integer clientId;
    private Integer currencyId;
    @Nullable
    private String originAccount;
    @Nullable
    private String destinationAccount;
    private Double amount;
    private LocalDateTime dateTransaction;

    
}
