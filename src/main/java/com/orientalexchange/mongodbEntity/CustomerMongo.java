package com.orientalexchange.mongodbEntity;




import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Getter
@Setter
@Document(collection = "customer")
public class CustomerMongo {
    @Id
    @Field
    private String id;

    @Field
    private String customerName;

    @Field
    private String customerAddress;
}
