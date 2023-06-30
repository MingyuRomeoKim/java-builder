package mingyu.utiles;

import mingyu.abstracties.DockerUtilAbstract;
import mingyu.interfaces.util.DockerUtilInterface;
import mingyu.libraries.ShellScriptLib;

import java.util.ArrayList;

public class DockerUtil implements DockerUtilInterface {

    private static DockerUtil instance;

    private String dockerCommand = null;

    public static DockerUtil getInstance() {
        if (instance == null) {
            synchronized (DockerUtil.class) {
                instance = new DockerUtil();
            }
        }
        return instance;
    }

    public String excuteShellScript(String execCommand) {
        ShellScriptLib shellScriptLib = ShellScriptLib.getInstance();
        return shellScriptLib.exec(execCommand);
    }

    public String makeArrListToString(ArrayList<String> arrayList) {
        String string = "";
        if (!arrayList.isEmpty()) {
            for (String line : arrayList) {
                string += String.format("%s ",line);
            }
        }
        return string;
    }

    private String makeDockerCommand(String first, String second, String third, String fourth) {
        if (!first.isEmpty() && !first.isBlank()) {
            dockerCommand = String.format(dockerCommand+"%s ",first);
        }
        if (!second.isEmpty() && !second.isBlank()) {
            dockerCommand = String.format(dockerCommand+"%s ",second);
        }
        if (!third.isEmpty() && !third.isBlank()) {
            dockerCommand = String.format(dockerCommand+"%s ",third);
        }
        if (!fourth.isEmpty() && !fourth.isBlank()) {
            dockerCommand = String.format(dockerCommand+"%s ",fourth);
        }
        return dockerCommand;
    }

    @Override
    public String run(ArrayList<String> options, String dockerImage, String command, ArrayList<String> arguments) {
        String option = makeArrListToString(options);
        String argument = makeArrListToString(arguments);
        dockerCommand = "docker run ";
        return makeDockerCommand(option, dockerImage, command, argument);
    }

    @Override
    public String push(ArrayList<String> options, String dockerImage) {
        String option = makeArrListToString(options);
        dockerCommand = "docker push ";
        return makeDockerCommand(option, dockerImage, "", "");
    }

    @Override
    public String pull(ArrayList<String> options, String dockerImage) {
        String option = makeArrListToString(options);
        dockerCommand = "docker pull ";
        return makeDockerCommand(option, dockerImage, "", "");
    }

    @Override
    public String exec(ArrayList<String> options, String dockerContainerName, String command, ArrayList<String> arguments) {
        String option = makeArrListToString(options);
        String argument = makeArrListToString(arguments);
        dockerCommand = "docker exec ";
        return makeDockerCommand(dockerContainerName, command, option, argument);
    }

    @Override
    public String rm(ArrayList<String> options, String dockerContainerName) {
        String option = makeArrListToString(options);
        dockerCommand = "docker rm ";
        return makeDockerCommand(option,dockerContainerName,"","");
    }

    @Override
    public String stop(ArrayList<String> options, String dockerContainerName) {
        String option = makeArrListToString(options);
        dockerCommand = "docker stop ";
        return makeDockerCommand(option,dockerContainerName,"","");
    }

    @Override
    public String ps(ArrayList<String> options) {
        String option = makeArrListToString(options);
        dockerCommand = "docker ps ";
        return makeDockerCommand(option,"","","");
    }

    @Override
    public String rmi(ArrayList<String> options, String dockerImage) {
        String option = makeArrListToString(options);
        dockerCommand = "docker rmi ";
        return makeDockerCommand(option,dockerImage,"","");
    }

    @Override
    public String images(ArrayList<String> options) {
        String option = makeArrListToString(options);
        dockerCommand = "docker images ";
        return makeDockerCommand(option,"","","");
    }
}


