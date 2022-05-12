package com.nttdata.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Unwrapped.Nullable;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountYanki {
	
	@Id
	private String accountId;
	private Integer clientId;
	private String phone;
	private Integer typeAccountId;
	private Integer currencyId;
	@Nullable
	private LocalDateTime membershipDate;
	@Nullable
	private Boolean main;
	private double balance;
	private String cardNumber;

}
