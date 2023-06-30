package mingyu.classes;

import mingyu.utiles.DockerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DockerManager {

    private Logger logger;
    private String dockerImage = null;
    private String gitRepositoryURL = null;
    private DockerUtil dockerUtil;

    public String getDockerImage() {
        return dockerImage;
    }

    public void setDockerImage(String dockerImage) {
        this.dockerImage = dockerImage;
    }

    public String getGitRepositoryURL() {
        return gitRepositoryURL;
    }

    public void setGitRepositoryURL(String gitRepositoryURL) {
        this.gitRepositoryURL = gitRepositoryURL;
    }

    public DockerManager(String dockerImage, String gitRepositoryURL) {
        logger = LoggerFactory.getLogger("BuilderManagerLogger");
        dockerUtil = DockerUtil.getInstance();
        setDockerImage(dockerImage);
        setGitRepositoryURL(gitRepositoryURL);
    }

    public void startBuild(String sourceDirectoryPath) {
        logger.info("docker Build Start !! \n");
        ArrayList<String> options = new ArrayList<String>(List.of(""));
        ArrayList<String> arguments = new ArrayList<String>(List.of(""));

        // docker pull images
        String dockerPullCommand = dockerUtil.pull(options, getDockerImage());
        String result = dockerUtil.excuteShellScript(dockerPullCommand);
        logger.info("docker pull message :: \n", dockerPullCommand, result);
        System.out.println("docker pull message ::");
        System.out.println(dockerPullCommand);
        System.out.println(result);

        // docker run image when using docker build && remove after build
        String[] commandArray = new String[]{
                "/bin/bash",
                "-c",
                "'cd /app && " +
                "rm -rf .git && " +
                "cp .env.example .env && " +
                "composer install && " +
                "php artisan config:clear && " +
                "php artisan cache:clear && " +
                "php artisan route:clear && " +
                "php artisan config:cache'"
        };

        options = new ArrayList<String>(List.of("-w", "/app", "-v", sourceDirectoryPath + ":/app", "--name", "builder", "--rm"));
        String dockerRunCommand = dockerUtil.run(options, getDockerImage(), String.join(" ", commandArray), arguments);
        result = dockerUtil.excuteShellScript(dockerRunCommand);
        logger.info("docker run message :: \n", dockerRunCommand, result);

        System.out.println("docker run message ::");
        System.out.println(dockerRunCommand);
        System.out.println(result);

        // build source using docker container
        // PHP Laravel Project가 Test Source이기 때문에 아래 스크립트로 작성함.
        // 추후 별도의 config.properties 등의 파일로 만들어 각 docker image명에 있는
        // Language 구분하여 별도로 관리 예정.
//        String[] commandArray = new String[]{
//                "/bin/bash",
//                "-c",
//                "'cd /app && " +
//                "rm -rf .git && " +
//                "cp .env.example .env && " +
//                "composer install && " +
//                "php artisan config:clear && " +
//                "php artisan cache:clear && " +
//                "php artisan route:clear && " +
//                "php artisan config:cache'"
//        };
//        String dockerExecCommand = dockerUtil.exec(options, "builder", String.join(" ", commandArray), arguments);
//        result = dockerUtil.excuteShellScript(dockerExecCommand);
//        logger.info("docker exec message :: \n", dockerExecCommand, result);

        // docker container stop
//        String dockerStopCommand = dockerUtil.stop(options,"builder");
//        result = dockerUtil.excuteShellScript(dockerStopCommand);
//        logger.info("docker stop result :: \n",dockerStopCommand,result);

        // docker container remove
//        options = new ArrayList<String>(List.of(""));
//        String dockerRmCommand = dockerUtil.rm(options, "builder");
//        result = dockerUtil.excuteShellScript(dockerRmCommand);
//        logger.info("docker rm message :: \n", dockerRmCommand, result);

    }
}
