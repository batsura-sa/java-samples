/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.util.Random;
import java.util.concurrent.Callable;
import lombok.Builder;

public class CountTask implements Callable<CountTask.Info> {
    private static final ThreadLocal<Info> threadInfo = new ThreadLocal<>();
    private static final int MAX_COUNT = 50_000_000;

    private final Info info;

    public CountTask(Integer threadInfo) {
        info = new Info(Thread.currentThread().getName(), threadInfo, null, null, 0F);
    }

    @Override
    public Info call() {
        double result = 0;

        threadInfo.set(
                info.toBuilder()
                        .callThreadName(Thread.currentThread().getName())
                        .isVirtual(Thread.currentThread().isVirtual())
                        .result(result)
                        .build()
        );

        Random random = new Random();
        for (int i = 0; i < MAX_COUNT; i++) {
            result += Math.sqrt(random.nextDouble());
        }

        return threadInfo.get();
    }

    @Builder(toBuilder = true)
    public record Info(
            String constructorThreadName,
            Integer number,
            String callThreadName,
            Boolean isVirtual,
            double result
    ) {
    }
}