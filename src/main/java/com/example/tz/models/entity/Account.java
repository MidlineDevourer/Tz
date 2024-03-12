package com.example.tz.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Schema(description = "Информация аккаунта")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Schema(description = "id аккаунта")
    private Long id;

    @Schema(description = "имя аккаунта")
    private String nameAccount;

    @Schema(description = "PIN-code аккаунта")
    @Pattern(regexp = "[0-9]{4}", message = "Некорректный Pin-code")
    private String pinCode;

    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @Schema(description = "счета аккаунта")
    private List<AccountNumber> accountNumbers;

}
