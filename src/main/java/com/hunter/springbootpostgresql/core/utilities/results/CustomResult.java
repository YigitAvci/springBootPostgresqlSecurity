package com.hunter.springbootpostgresql.core.utilities.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomResult {
    private boolean success;
    private String message;


}
