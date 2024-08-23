package com.orientalexchange.mongodbEntity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Getter
@Setter
@Document(collection = "branch")
public class BranchMongo {
    @Id
    @Indexed
    @Field
    private  String id;

    @Field
    private String branchName;

    @Field
    private String branchAddress;
}
