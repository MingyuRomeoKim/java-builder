package mingyu.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuilderManager {

    private Logger logger;

    private String configFilePath;
    DockerManager dockerManager;
    GitManager gitManager;
    ArchiveManager archiveManager;

    public BuilderManager(String gitRepositoryURL, String dockerRepositoryImage, String gitCommitBranch) {
        logger = LoggerFactory.getLogger("BuilderManagerLogger");

        String yourGitId = "";
        String yourGitPassword = "";

        gitManager = new GitManager(yourGitId, yourGitPassword, gitCommitBranch, gitRepositoryURL);
        dockerManager = new DockerManager(dockerRepositoryImage, gitRepositoryURL);
        archiveManager = new ArchiveManager();
    }

    public static void main(String[] args) throws Exception {
        // args[0] : "git repository url" when use git pull command
        // args[1] : "dockerRepo/dockerImage:tag" when use build
        // args[2] : CI_COMMIT_BRANCH

        String gitRepositoryURL = args[0];
        String dockerRepositoryImage = args[1];
        String gitCommitBranch = args[2];

        BuilderManager builderManager = new BuilderManager(gitRepositoryURL, dockerRepositoryImage, gitCommitBranch);

        // 해당 날짜 지정
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateTime = dateFormat.format(new Date());
        String sourceDirectoryPath = "/Users/mingyukim/tmp/build/source/" + dateTime;

        // 최신 source code 다운로드
        builderManager.gitManager.sourceCodeDownload(dateTime,  sourceDirectoryPath);

        // 도커로 다운로드한 최신 source code build 하기;
        builderManager.dockerManager.startBuild(sourceDirectoryPath);

        // build된 프로젝트 폴더 tar.gz 파일로 압축하기
//        String sourceDirectoryPath = "/Users/mingyukim/tmp/build/source/20221228164126";
        builderManager.archiveManager.startArchive(sourceDirectoryPath);

    }
}