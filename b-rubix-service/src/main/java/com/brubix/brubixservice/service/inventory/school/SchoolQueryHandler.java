package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.school.SchoolData;

public interface SchoolQueryHandler {

    SchoolData findSchoolByCode(String code);
}