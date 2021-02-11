package com.exp.model.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePackageData {

    private String weight;

    private String length;

    private String width;

    private String height;

    private String labelFileData;

    private String labelFileName;
}
