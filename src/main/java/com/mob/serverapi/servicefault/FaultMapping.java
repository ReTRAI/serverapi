package com.mob.serverapi.servicefault;

public class FaultMapping {

    public enum FaultType {
        error("ERROR"),
        warning("WARNING");

        public final String label;

        private FaultType(String label) {
            this.label = label;
        }
    }

    public enum RepoFault {
        userNotExist("USER_DONT_EXIST"),
        userInactive("USER_INACTIVE"),
        userBlocked("USER_BLOCKED"),
        wrongCredentials("WRONG_CREDENTIALS"),
        invalidActionUser("INVALID_ACTION_USER"),
        emailExist("EMAIL_ALREADY_EXISTS"),
        usernameExist("USERNAME_ALREADY_EXISTS"),
        userNotBlocked("USER_NOT_BLOCKED"),
        userIsAdmin("USER_IS_ALREADY_ADMIN"),
        roleNotExist("ROLE_DONT_EXIST"),
        supportNotExist("SUPPORT_DONT_EXIST"),
        userAlreadyHasRole("USER_ALREADY_HAS_ROLE"),
        hasAssoc("HAS_ASSOCIATIONS"),
        circularAssoc("CIRCULAR_ASSOCIATION"),
        childHasParent("CHILD_ALREADY_ASSOCIATED"),
        assocNotExist("ASSOCIATION_DONT_EXIST"),
        statusNotExist("STATUS_DONT_EXIST"),
        ticketNotExist("TICKET_DONT_EXIST"),
        resellerNotExist("RESELLER_DONT_EXIST"),
        deviceNotExist("DEVICE_DONT_EXIST"),
        userDeviceNotExist("USERDEVICE_DONT_EXIST"),
        resellerHasDevices("RESELLER_HAS_DEVICES"),
        inconsistentDeviceStatus("INCONSISTENT_DEVICE_STATUS"),
        androidIdExist("ANDROID_ID_EXIST"),
        simNumberExist("SIM_NUMBER_EXIST"),
        imeiExist("IMEI_EXIST"),
        serialNumberExist("SERIAL_NUMBER_EXIST");

        public final String label;

        private RepoFault(String label) {
            this.label = label;
        }
    }

    public enum UserGeneralRepoFault {
        getUserById("GET_USER_BY_ID"),
        loginFailed("LOGIN_FAILED"),
        setUser("SET_USER"),
        unblockUser("UNBLOCK_USER"),
        blockUser("BLOCK_USER"),
        changeUserPassword("CHANGE_USER_PW"),
        inactivateUser("INACTIVATE_USER"),
        activateUser("ACTIVATE_USER"),
        changeLang("CHANGE_LANG"),
        changeTheme("CHANGE_THEME"),
        existUserName("EXIST_USER_NAME"),
        existUserEmail("EXIST_USER_EMAIL"),
        getUserRoleByUser("GET_USER_ROLE_BY_USER_ID"),
        getUserFiltered("GET_USER_FILTERED"),
        getCountUserFiltered("GET_COUNT_USERS_FILTERED"),
        setAdminRole("SET_ADMIN_ROLE"),
        removeAdminRole("REMOVE_ADMIN_ROLE");

        public final String label;

        private UserGeneralRepoFault(String label) {
            this.label = label;
        }
        }

    public enum SupportGeneralRepoFault {
        getSupportById("GET_SUPPORT_BY_ID"),
        getSupportByUserId("GET_SUPPORT_BY_USER_ID"),
        setSupport("SET_SUPPORT"),
        removeSupport("REMOVE_SUPPORT"),
        getSupportFiltered("GET_SUPPORT_FILTERED"),
        getCountSupportFiltered("GET_COUNT_SUPPORT_FILTERED"),
        getTicketFiltered("GET_TICKET_FILTERED"),
        getCountTicketFiltered("GET_COUNT_TICKET_FILTERED"),
        getTicketDetailFiltered("GET_TICKET_DETAIL_FILTERED"),
        getCountTicketDetailFiltered("GET_COUNT_TICKET_DETAIL_FILTERED"),
        setSupportAssoc("SET_SUPPORT_ASSOCIATION"),
        removeSupportAssoc("REMOVE_SUPPORT_ASSOCIATION"),
        getSupportAssoc("GET_SUPPORT_ASSOCIATION"),
        getSupportParentByChildId("GET_SUPPORT_PARENT_BY_CHILD"),
        setTicket("SET_TICKET"),
        updateTicket("UPDATE_TICKET"),
        setTicketDetail("SET_TICKET_DETAIL"),
        getAvailableSupportTicket("GET_AVAILABLE_SUPPORT_PARENTS"),
        getCountAvailableSupportTicket("GET_COUNT_AVAILABLE_SUPPORT_PARENTS"),
        isHierarchyValid("IS_HIERARCHY_VALID");


        public final String label;

        private SupportGeneralRepoFault(String label) {
            this.label = label;
        }
    }

    public enum ResellerGeneralRepoFault {
        getResellerById("GET_RESELLER_BY_ID"),
        getResellerByUserId("GET_RESELLER_BY_USER_ID"),
        getResellerByUserDeviceName("GET_RESELLER_BY_USER_DEVICE_NAME"),
        getResellerFiltered("GET_RESELLER_FILTERED"),
        getCountResellerFiltered("GET_COUNT_RESELLER_FILTERED"),
        setReseller("SET_RESELLER"),
        setResellerBalanceMovement("ADD_RESELLER_BALANCE_MOVEMENT"),
        getResellerBalanceMovement("GET_RESELLER_BALANCE_MOVEMENTS"),
        getCountResellerBalanceMovement("GET_COUNT_RESELLER_BALANCE_MOVEMENTS"),
        removeReseller("REMOVE_RESELLER"),
        setResellerAssoc("SET_RESELLER_ASSOCIATION"),
        removeResellerAssoc("REMOVE_RESELLER_ASSOCIATION"),
        getResellerAssoc("GET_RESELLER_ASSOCIATION"),
        getResellerParentByChildId("GET_RESELLER_PARENT_BY_CHILD"),
        getAvailableResellerParent("GET_AVAILABLE_RESELLER_PARENTS"),
        getCountAvailableResellerParent("GET_COUNT_AVAILABLE_RESELLER_PARENTS"),
        isHierarchyValid("IS_HIERARCHY_VALID");

        public final String label;

        private ResellerGeneralRepoFault(String label) {
            this.label = label;
        }
    }

    public enum DashboardGeneralRepoFault {
        getActiveDashboard("GET_ACTIVE_DASHBOARD"),
        getInactiveDashboard("GET_INACTIVE_DASHBOARD"),
        getGlobalDashboard("GET_GLOBAL_DASHBOARD"),
        getExpiringDashboard("GET_EXPIRING_DASHBOARD");

        public final String label;

        private DashboardGeneralRepoFault(String label) {
            this.label = label;
        }
    }

    public enum DeviceGeneralRepoFault {
        suspendDevice("SUSPEND_DEVICE"),
        wipeDevice("WIPE_DEVICE"),
        blockDevice("BLOCK_DEVICE"),
        activateDevice("ACTIVATE_DEVICE"),
        assignDevice("ASSIGN_DEVICE"),
        getDeviceFiltered("GET_DEVICES_FILTERED"),
        getCountDeviceFiltered("GET_COUNT_DEVICES_FILTERED"),
        setDevice("SET_DEVICE"),
        setDeviceList("SET_DEVICE_LIST"),
        getDeviceById("GET_DEVICE_BY_ID");

        public final String label;

        private DeviceGeneralRepoFault(String label) {
            this.label = label;
        }
    }
}
