package com.beetech.mvcspringboot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiErrorVO {
    @NonNull
    private String errorMessage;

    private List<String> validationList;
}
