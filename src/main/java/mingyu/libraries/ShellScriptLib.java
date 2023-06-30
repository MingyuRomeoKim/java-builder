package mingyu.libraries;


import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellScriptLib {
    private static ShellScriptLib instance;
    private boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");

    public static ShellScriptLib getInstance() {
        if (instance == null) {
            synchronized (ShellScriptLib.class) {
                instance = new ShellScriptLib();
            }
        }
        return instance;
    }

    public String exec(String execCommand) {
        String[] input = null;
        StringBuilder output = new StringBuilder();
        JSONObject returnData = new JSONObject();

        if (isWindows) {
            input = new String[]{"cmd.exe", "/c", execCommand};
        } else {
            input = new String[]{"/bin/bash", "-c", execCommand};
        }

        try {
            // Shell Script 실행
            Process process = Runtime.getRuntime().exec(input);

            // 실행 결과 읽어오기
            BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = outputReader.readLine()) != null) {
                output.append(line);
            }

            // return Data 작성
            returnData.put("code",200);
        } catch (Exception exception) {
            output.append(exception.getStackTrace());
            output.append(exception.getMessage());

            // return Data 작성
            returnData.put("code",-400);
        }
        returnData.put("message",output.toString());

        return returnData.toJSONString();
    }
}
