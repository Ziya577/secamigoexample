package com.example.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCardPayload {

    @NotNull(message = "Hesab nömrəsi boş ola bilməz")
    private String accountNumber;
    @NotNull(message = "Məbləğ boş ola bilməz")
    private Double amount;
}
