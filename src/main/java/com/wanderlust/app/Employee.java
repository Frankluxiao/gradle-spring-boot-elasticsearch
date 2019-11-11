package com.wanderlust.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private UUID employeeId;
    private String employName;
    private String position;
    private LocalDateTime creationTime;
    private Address address;
}
