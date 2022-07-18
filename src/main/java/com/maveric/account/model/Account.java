package com.maveric.account.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "account")
@JsonIgnoreProperties(value={ "id" }, allowSetters= true)
public class Account {

    @Id
    @Field(name = "_id")
    private String id;


    @Field(name = "type")
    private String type;

    @Field(name = "customer_id")
    private String customerId;

    @Field(name="created_at")
    private Date createdAt;

    @Field(name="updated_at")
    private Date updatedAt;

}
