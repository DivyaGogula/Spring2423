package com.orientalexchange.mongodbEntity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "loan")
public class LoanMongo {
    @Id
    @Indexed
    @Field
    private String id;

    @Field
    private String loanType;

    @Field
    private String loanAmount;
}
