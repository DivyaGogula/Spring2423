package com.orientalexchange.mongodbEntity;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString
@Document(collection = "bank")
public class BankMongo {

        @Id
        private String id;

        @Field(name= "bankCode")
        private String bankCode;

        @Field(name= "bankName")
        private String bankName;

        @Field(name= "bankAddress")
        private String bankAddress;

}

