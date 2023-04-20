package com.sportslightadmin.adminz.service;

import com.sportslightadmin.adminz.dto.AdminzDTO;
import com.sportslightadmin.adminz.dto.AuthInfo;
import com.sportslightadmin.adminz.dto.ChangePwdCommand;

public interface AdminzService {
	public AuthInfo addAdminProcess(AdminzDTO dto);
	public AuthInfo loginProcess(AdminzDTO dto);
	
	public AdminzDTO updateAdminProcess(String adminEmail);
	public AuthInfo updateAdminProcess(AdminzDTO dto);
	public void updatePassProcess(String email, ChangePwdCommand changePwd);
}
