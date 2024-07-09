package com.example.springsecurity_authority.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class GroupSearchCriteria {

    private String groupname;
    private String tag;
    private String ownerId;

}
