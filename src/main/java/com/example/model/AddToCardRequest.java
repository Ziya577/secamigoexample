package com.example.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCardRequest {

    @NotNull(message = "Hesab nömrəsi boş ola bilməz")
    private String accountNumber;
    @NotNull(message = "Məbləğ boş ola bilməz")
    private Double amount;
}
