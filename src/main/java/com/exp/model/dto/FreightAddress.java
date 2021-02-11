package com.exp.model.dto;


import com.exp.model.type.AddressType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreightAddress implements Serializable {

    private Long id;

    private AddressType type;

    private String name;

    private String company;

    private String contactName;

    private String country;

    private String province;

    private String city;

    private String addressLine1;

    private String addressLine2;

    private String zipcode;

    private String phone;

    private String email;

    private String phoneExtension;


}
