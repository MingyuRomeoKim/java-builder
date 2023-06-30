package mingyu.classes;

import mingyu.libraries.CompressLib;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ArchiveManager {

    private Logger logger;
    private CompressLib compressLib;
    public ArchiveManager() {
        logger = LoggerFactory.getLogger("ArchiveManagerLogger");
        compressLib = CompressLib.getInstance();
    }

    public void startArchive(String path) {
        try {
            Path source = Paths.get(path);
            compressLib.compress(source);
        } catch (Exception exception) {
            logger.info("startArchive Error ::\n",exception.getMessage(),exception.getStackTrace());
        }

    }

}
