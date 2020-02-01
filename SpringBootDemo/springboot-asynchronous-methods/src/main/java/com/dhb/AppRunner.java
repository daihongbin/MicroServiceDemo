package com.dhb;

import com.dhb.entity.User;
import com.dhb.service.GitHubLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final GitHubLookupService gitHubLookupService;

    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    public void run(String... args)throws Exception{
        long start = System.currentTimeMillis();

        Future<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
        Future<User> page2 = gitHubLookupService.findUser("CloudFoundry");
        Future<User> page3 = gitHubLookupService.findUser("Spring-Projects");

        while (!page1.isDone() && page2.isDone() && page3.isDone()){
            Thread.sleep(10);
        }

        logger.info("Elapsed time:" + (System.currentTimeMillis() - start));
        logger.info("-->" + page1.get());
        logger.info("-->" + page2.get());
        logger.info("-->" + page3.get());
    }
}
