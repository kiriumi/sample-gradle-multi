package sample.gradle.multi.shared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Library {

    private final Logger logger = LogManager.getLogger(Library.class);

    public boolean someLibraryMethod() {

        String hoge = null;

        if (hoge == null) {

            if (hoge == null) {
                logger.info("SpotBugs検知箇所");
            }
        }

        logger.info("Shared開始");

        return true;
    }
}
