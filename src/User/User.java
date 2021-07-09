package User;

/**
 * User divide user into several parts wishing to set different access.
 * Abstract class.
 * Less static built-in.
 *
 * @author Kong Weirui
 * @since 3.3
 */
public abstract class User {
    private String loginName_MD5;
    private String password_MD5;
    public static final int STUDENT_USER = 0, TEACHER_USER = 1, SUPER_ADMIN_USER = 2;

    public String getLoginName_MD5() {
        return loginName_MD5;
    }

    public String getPassword_MD5() {
        return password_MD5;
    }
    public int getTag(){
        return -1;
    }
}

