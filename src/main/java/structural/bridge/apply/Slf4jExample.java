package structural.bridge.apply;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jExample {

    //Logger -> Abstraction
    //Slf4jExample -> Implementation
    private static Logger logger = LoggerFactory.getLogger(Slf4jExample.class);

    public static void main(String[] args) {
        logger.info("hello logger");
    }
}
