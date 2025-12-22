package edu.icet.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Expense {
    private int id;
    private String description;
    private String category;
    private double amount;
    private LocalDate date;
}
