package springbatchxml;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBatchApplication {
    private static String[] springConfigLocation = {
            "spring-config.xml"
    };

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext(springConfigLocation);

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("person-converter-job");

        JobExecution execution = jobLauncher.run(job, new JobParameters());

        System.out.println("Exit Status : " + execution.getStatus());


    }
}
