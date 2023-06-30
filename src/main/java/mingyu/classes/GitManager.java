package mingyu.classes;

import mingyu.interfaces.GitManagerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import mingyu.utiles.JgitUtil;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import java.io.File;

public class GitManager implements GitManagerInterface {

    private Logger logger;
    JgitUtil jgitUtil;

    public GitManager(String gitUserId, String gitUserPassword,
                      String gitBranchName, String gitRepositoryURL) {

        logger = LoggerFactory.getLogger("GitManagerLogger");

        jgitUtil = JgitUtil.getInstance();
        jgitUtil.setUrl(gitRepositoryURL);
        jgitUtil.setUserId(gitUserId);
        jgitUtil.setUserPass(gitUserPassword);
        jgitUtil.setCp(new UsernamePasswordCredentialsProvider(jgitUtil.getUserId(), jgitUtil.getUserPass()));
        jgitUtil.setHash("origin/" + gitBranchName);
        jgitUtil.setBranchName(gitBranchName);
    }

    @Override
    public void sourceCodeDownload(String dateTime, String sourceDirectoryPath) {
        try {
            jgitUtil.setLocalPath(sourceDirectoryPath);
            File dir = new File(sourceDirectoryPath);

            jgitUtil.checkOut(dir);
            Git git = jgitUtil.open(dir);
            git.checkout();
            jgitUtil.remoteAdd(git);
            jgitUtil.pull(git);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            logger.error("sourceCodeDownloadError :: ", exception.getMessage());
        }
    }

}
