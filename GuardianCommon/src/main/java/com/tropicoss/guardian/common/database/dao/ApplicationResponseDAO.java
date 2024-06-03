package com.tropicoss.guardian.common.database.dao;

import com.tropicoss.guardian.common.database.model.ApplicationResponse;

import java.util.List;

public interface ApplicationResponseDAO {
    void addApplicationResponse(ApplicationResponse applicationResponse);
    ApplicationResponse getApplicationResponseById(int applicationResponseId);
    List<ApplicationResponse> getAllApplicationResponses();
    void updateApplicationResponse(ApplicationResponse applicationResponse);
    void deleteApplicationResponse(int applicationResponseId);
}
