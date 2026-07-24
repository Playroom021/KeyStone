package com.KeyStone.DeliveryService.Security;

import java.util.Map;
import java.util.Set;
import com.KeyStone.DeliveryService.ENUM.Permission;
import com.KeyStone.DeliveryService.ENUM.Role;

public class RoleBasedPermissions {

    public static Map<Role,Set<Permission>>getRolebasedPermissions(){
        Map<Role,Set<Permission>>Permissions = new HashMap<>();
        Permissions.put(Role.MANAGER, new HashSet<>(Array.asList(Permission.CREATE_USER,
            Permission.UPDATE_USER,
            Permission.DELETE_USER,
            Permission.VIEW_USER,

            Permission.CREATE_CUSTOMER,
            Permission.UPDATE_CUSTOMER,
            Permission.DELETE_CUSTOMER,
            Permission.VIEW_CUSTOMER,

            Permission.CREATE_SITE,
            Permission.UPDATE_SITE,
            Permission.DELETE_SITE,
            Permission.VIEW_SITE,

            Permission.CREATE_WORK_ORDER,
            Permission.UPDATE_WORK_ORDER,
            Permission.DELETE_WORK_ORDER,
            Permission.VIEW_WORK_ORDER,
            Permission.ASSIGN_WORK_ORDER,
            Permission.CLOSE_WORK_ORDER,
            Permission.CANCEL_WORK_ORDER,

            Permission.START_WORK,
            Permission.HOLD_WORK,
            Permission.RESUME_WORK,
            Permission.COMPLETE_WORK,

            Permission.RISE_REQUEST,
            Permission.VIEW_REQUEST,

            Permission.ADD_PARTS,
            Permission.UPDATE_PARTS,
            Permission.DELETE_PARTS,
            Permission.VIEW_PARTS,
            Permission.USE_PARTS,

            Permission.ADD_TIME_LOGS,
            Permission.VIEW_TIME_LOGS,

            Permission.SEND_NOTIFICATION,

            Permission.VIEW_DASHBOARD,

            Permission.VIEW_REPORTS
        )));

        Permissions.put(Role.DISPATCHER, 
            new HashSet<>(Arrays.asList(
                Permission.CREATE_CUSTOMER,
                Permission.UPDATE_CUSTOMER,
                Permission.VIEW_CUSTOMER,

                Permission.CREATE_SITE,
                Permission.UPDATE_SITE,
                Permission.VIEW_SITE,

                Permission.CREATE_WORK_ORDER,
                Permission.UPDATE_WORK_ORDER,
                Permission.VIEW_WORK_ORDER,
                Permission.ASSIGN_WORK_ORDER,
                Permission.CANCEL_WORK_ORDER,

                Permission.VIEW_DASHBOARD

            )));
        Permissions.put(Role.TECHNICIAN,
            new HashSet<>(Arrays.asList(
                Permission.VIEW_WORK_ORDER,
                Permission.START_WORK,
                Permission.HOLD_WORK,
                Permission.RESUME_WORK,
                Permission.COMPLETE_WORK,
                Permission.USE_PARTS,
                Permission.VIEW_PARTS,
                Permission.ADD_TIME_LOGS,
                Permission.VIEW_TIME_LOGS
            )));

        Permissions.put(Role.CUSTOMER,
            new HashSet<>(Arrays.asList(
                    // Permission.CREATE_WORK_ORDER,
                    // Permission.VIEW_WORK_ORDER,
                    Permission.RISE_REQUEST,
                    Permission.VIEW_REQUEST
                )));

        return Permissions;

    }
}
