package com.orientalexchange.mongodbEntity;



import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@Document(collection = "account")
public class AccountMongo {
    @Id
    @Indexed
    private String id;

    @Field
    private String accountType;

    @Field
    private String accountBalance;

}
