package com.example.employee.Dto.Respond;

import com.example.employee.model.Product;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RespProducts {
    private List<Product> products;
    private int totalInPage ;
    private long totalProduct;
    private int totalPage;
}
