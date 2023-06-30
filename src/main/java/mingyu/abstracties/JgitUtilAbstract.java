package mingyu.abstracties;


import org.eclipse.jgit.transport.CredentialsProvider;

public abstract class JgitUtilAbstract {
    private String userId = null; // 예) "test@github.com"
    private String userPass = null; // 예) "git Password"
    private String userName = null; // 예) "mingyu"
    private String userEmail = null; // 예) "rlaalsrb0466@naver.com"
    private String hash = null;  // 예) "origin/main"
    private String url = null; // 예) "https://gitlab.com/mingyukim/project1"
    private String localPath = null; // 예) /app/builder/javaBuilder/tmp/build/source/202212271342/
    private String branchName = null; // 예) master , main , dev , staging 등
    private CredentialsProvider cp = null; // new UsernamePasswordCredentialsProvider(userId, userPass)

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public CredentialsProvider getCp() {
        return cp;
    }

    public void setCp(CredentialsProvider cp) {
        this.cp = cp;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
