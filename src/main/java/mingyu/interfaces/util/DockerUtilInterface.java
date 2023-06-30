package mingyu.interfaces.util;

import java.util.ArrayList;

public interface DockerUtilInterface {

    public abstract String run(ArrayList<String> options, String dockerImage, String command, ArrayList<String> argument);

    public abstract String push(ArrayList<String> options, String dockerImage);

    public abstract String pull(ArrayList<String> options, String dockerImage);

    public abstract String exec(ArrayList<String> options, String dockerContainerName, String command, ArrayList<String> arguments);

    public abstract String rm(ArrayList<String> options, String dockerContainerName);

    public abstract String stop(ArrayList<String> options, String dockerContainerName);

    public abstract String ps(ArrayList<String> options);

    public abstract String rmi(ArrayList<String> options, String dockerImage);

    public abstract String images(ArrayList<String> options);

}
