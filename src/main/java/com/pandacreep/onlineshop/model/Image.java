package com.pandacreep.onlineshop.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "images")
@Builder
@Data
public class Image {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @Builder.Default
    private Binary image = new Binary(new byte[0]);

    public static final Image NO_IMAGE = Image.builder().id("-NO-IMAGE-").build();
}
