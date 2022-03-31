package com.example.paoche.constants;

public class Constant {

    //标记用户已经被删除
    public static final String DELETE_USER_KEY = "delete_user_key_";

    //用户名称key
    public static final String JWT_USER_NAME = "jwt_user_name_key";

    //权限key
    public static final String JWT_PERMISSIONS_KEY = "jwt_permissions_key_";

    //角色key
    public static final String JWT_ROLES_KEY = "jwt_roles_key_";

    //access_token 主动退出后加入黑名单key
    public static final String JWT_ACCESS_TOKEN_BLACKLIST = "jwt_access_token_blacklist_";

    //refresh_token 主动退出后加入黑名单key
    public static final String JWT_REFRESH_TOKEN_BLACKLIST = "jwt_refresh_token_blacklist_";

    //正常token
    public static final String ACCESS_TOKEN ="authorization";

    //刷新token
    public static final String REFRESH_TOKEN = "refresh_token";

    //标记用户是否已经被锁定
    public static final String ACCOUNT_LOCK_KEY = "account_lock_key_";

    //主动去刷新 token key (适用场景，比如修改角色，权限去刷新token)
    public static final String JWT_REFRESH_KEY = "jwt_refresh_key_";

    //标记新的access_token
    public static final String JWT_REFRESH_IDENTIFICATION = "jwt_refresh_identification_";

    //部门编码 key
    public static final String DEPT_CODE_KEY = "dept_code_key_";

    //用户权限缓存 key
    public static final String  IDENTIFY_CACHE_KEY = "shiro-cache:com.example..lesson.shiro.CustomRealm.authorizationCache:";

}
