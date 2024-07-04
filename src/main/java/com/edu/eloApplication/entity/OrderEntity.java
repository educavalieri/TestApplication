package com.edu.eloApplication.entity;

import com.edu.eloApplication.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "order_entity")
public class OrderEntity {

    @Id
    private String productName;
    private StatusEnum status;
}
