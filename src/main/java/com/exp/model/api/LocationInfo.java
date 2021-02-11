package com.exp.model.api;

import com.exp.model.dto.FreightAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationInfo {

    private FreightAddress sendFrom;

    private FreightAddress sendTo;


}
