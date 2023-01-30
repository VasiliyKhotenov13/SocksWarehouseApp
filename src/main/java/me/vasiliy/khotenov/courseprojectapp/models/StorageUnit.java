package me.vasiliy.khotenov.courseprojectapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StorageUnit {

    private int cottonPart;
    private SocksColor socksColor;
    private SocksSize socksSize;
    private int quantity;

}
