/**
 * @projectName train
 * @package tech.songjian.batch.config
 * @className tech.songjian.batch.config.BatchApplication
 */
package tech.songjian.batch.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * BatchApplication
 * @description
 * @author SongJian
 * @date 2023/6/4 21:44
 * @version
 */
@SpringBootApplication
@ComponentScan("tech.songjian")
@MapperScan("tech.songjian.train.*.mapper")
public class BatchApplication {

    private static final Logger LOG = LoggerFactory.getLogger(BatchApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BatchApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("测试地址: \thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }
}


