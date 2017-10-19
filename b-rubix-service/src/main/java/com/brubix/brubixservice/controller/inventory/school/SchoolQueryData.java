package com.brubix.brubixservice.controller.inventory.school;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.brubix.brubixservice.controller.inventory.SocialData;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SchoolQueryData {

    private String name;

    private String code;

    private List<AddressData> addresses;

    private SocialData social;

    private byte[] logo;
}
