package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.KlingAiCreateResponseDto;
import com.virtuwear.rest.dto.KlingAiRequestDto;
import com.virtuwear.rest.dto.KlingAiResponseDto;
import com.virtuwear.rest.dto.KlingAiSingleTaskResponseDto;

public interface KlingAiService {
    KlingAiCreateResponseDto createTryOnTask(KlingAiRequestDto requestDto );

    KlingAiSingleTaskResponseDto getTryOnTaskStatus(String taskId);

    KlingAiResponseDto getTryOnTaskList(int pageNum, int pageSize);

    KlingAiSingleTaskResponseDto pollTryOnTaskStatus(String taskId, int maxRetries, int intervalSeconds);


}
