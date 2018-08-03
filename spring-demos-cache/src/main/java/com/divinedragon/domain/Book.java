package com.divinedragon.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private String isbn;

    private String title;

    private Author author;
}
