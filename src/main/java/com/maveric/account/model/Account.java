package com.maveric.account.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "account")
@JsonIgnoreProperties(value={ "id" }, allowSetters= true)
public class Account {

    @Id
    private String id;

    @Field(name = "customerId")
    private String customerId;

    @Field(name = "type")
    private String type;

}
