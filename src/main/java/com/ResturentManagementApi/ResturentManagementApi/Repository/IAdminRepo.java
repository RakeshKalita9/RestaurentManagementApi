package com.ResturentManagementApi.ResturentManagementApi.Repository;

import com.ResturentManagementApi.ResturentManagementApi.Model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<AdminUser,Long> {

    AdminUser findFirstByAdminEmail(String adminEmail);
}
