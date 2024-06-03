package com.tropicoss.guardian.common.database.dao;

import com.tropicoss.guardian.common.database.model.Application;

import java.util.List;

public interface ApplicationDAO {
    void addApplication(Application application);
    Application getApplicationById(int applicationId);
    List<Application> getAllApplications();
    void updateApplication(Application application);
    void deleteApplication(int applicationId);
}
