package com.humanoo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * @author R.fatthi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookAuditDTO {

    private Long id;

    private String isbn;

    private String bookName;

    private String author;

    private String categories;

    private ZonedDateTime dateCreated;

    private ZonedDateTime dateUpdate;

}
