package com.humanoo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author R.fatthi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotNull
    private String isbn;

    @NotNull
    private String bookName;

    @NotNull
    private String author;

    @NotNull
    private String categories;

}
