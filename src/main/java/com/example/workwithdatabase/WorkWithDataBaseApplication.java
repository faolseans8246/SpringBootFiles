package com.example.workwithdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class WorkWithDataBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkWithDataBaseApplication.class, args);
    }


    /**
     *
     * // Har bir ish boshlangandan keyin, ikkinchisini 1000 ms da ishga tushiradi
     *     @Scheduled(fixedRate = 1000L)
     *     public void startReate() {
     *         System.out.println("rate act: " + new Date());
     *     }
     *
     *
     *     // Har bir ish boshlangandan so'ng ikkinchisini bajarailishini 1000 ms oralig'ida kutib turadi
     *     @Scheduled(fixedDelay = 1000L )
     *     public void startDelay() {
     *         System.out.println("deley act: " + new Date());
     *     }
     *
     *     // Berilgan vaqtda ishga tushadi (Secund : Minut : Hour : Day : month : Weekly)
     *     @Scheduled(cron = "0 25 15 * * *")
     *     public void startCron() {
     *         System.out.println("Dastur ishga tushdi: " + new Date());
     *     }
     *
     *
     *     // Funksiya ishga tushishini 1000 ms kutadi va har 3000 ms da takrorlaydi
     *     @Scheduled(initialDelay = 1000L, fixedDelay = 3000L)
     *     public void startAndStop() {
     *         System.out.println("Start And wait: " + new Date());
     *     }
     *
     */

}
