package com.example.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = "Ad bölməsi boş ola bilməz")
    private String firstname;
    @NotNull(message = "Soyad bölməsi boş ola bilməz")
    private String lastname;
    @Email(message = "Düzgün email formatı daxil edin")
    private String email;
    @Size(min = 8,max = 16,message = " Şifrə minimum 8 maxmimum 16 simvoldan ibarət olmalıdır")
    private String password;
    @Pattern(regexp = "\\d{7}",message = "Hesab nömrəsi 7 rəqəmdən ibarət olmalıdır")
    private String accountNumber;
    @DecimalMin(value="0.0",message = "Balans kəsrli ədəd olmalıdır")
    private Double balance;
}
