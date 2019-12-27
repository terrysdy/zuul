/**
 * Copyright 2018 Netflix, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.zuul.netty.server;

import com.netflix.config.DynamicIntProperty;

import javax.inject.Singleton;

@Singleton
public class DefaultEventLoopConfig implements EventLoopConfig {

    private static final DynamicIntProperty ACCEPTOR_THREADS =
            new DynamicIntProperty("zuul.server.netty.threads.acceptor", 1);
    private static final DynamicIntProperty WORKER_THREADS =
            new DynamicIntProperty("zuul.server.netty.threads.worker", -1);
    private static final int PROCESSOR_COUNT = Runtime.getRuntime().availableProcessors();

    private final int eventLoopCount;
    private final int acceptorCount;

    public DefaultEventLoopConfig() {
        // worker 线程数默认为 cpu 个数
        eventLoopCount = WORKER_THREADS.get() > 0 ? WORKER_THREADS.get()
                : PROCESSOR_COUNT;
        // acceptor 线程默认为1
        acceptorCount = ACCEPTOR_THREADS.get();
    }

    public DefaultEventLoopConfig(int eventLoopCount, int acceptorCount) {
        this.eventLoopCount = eventLoopCount;
        this.acceptorCount = acceptorCount;
    }

    @Override
    public int eventLoopCount() {
        return eventLoopCount;
    }

    @Override
    public int acceptorCount() {
        return acceptorCount;
    }
}
