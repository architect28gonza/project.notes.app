package com.note.dto.cluster;

import java.util.List;

import com.note.dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

public class ResponseClusterDto extends ResponseDto {
    
    @Getter
    @Setter
    private List<Group> group;

}
