package com.example.payload;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardToCardPayload {
    @NotNull(message="Hesab nömrəsi boş ola bilməz")
    private String fromAccountNumber;
    @NotBlank(message = "Hədəf hesabın nömrəsi boş ola bilməz")
    private String toAccountNumber;
    @DecimalMin(value = "1.00", message = "Məbləğ minimum 1 AZN olmalıdır")
    private Double amount;
}