package com.virtuwear.rest.service;

import com.virtuwear.rest.dto.*;

public interface KlingAiService {
    KlingAiCreateResponseDto createTryOnTask(KlingAiRequestDto requestDto );

    KlingAiSingleTaskResponseDto getTryOnTaskStatus(String taskId);

    KlingAiListTaskResponseDto getTryOnTaskList(int pageNum, int pageSize);

    KlingAiSingleTaskResponseDto pollTryOnTaskStatus(String taskId, int maxRetries, int intervalSeconds);


}
