package com.example.myrok.service;


import com.example.myrok.dto.DashBoardDTO;

import java.util.List;

public interface DashBoardService {

    DashBoardDTO.TagResponseDTO getTagInfo(Long projectId);
}
