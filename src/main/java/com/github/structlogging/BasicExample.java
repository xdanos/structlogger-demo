package com.github.structlogging;

import com.github.structlogging.context.AnotherContext;
import com.github.structlogging.context.BlockCacheContext;
import com.github.structlogging.slf4j.Slf4jLoggingCallback;
import com.github.structlogging.annotation.LoggerContext;
import org.slf4j.LoggerFactory;

@SuppressWarnings("ConstantConditions")
public class BasicExample {

    @LoggerContext(context = DefaultContext.class)
    private static StructLogger<DefaultContext> defaultLog = new StructLogger<>(
            new Slf4jLoggingCallback(
                    LoggerFactory.getLogger("com.github.structlogging.Default")
            )
    );

    @LoggerContext(context = BlockCacheContext.class)
    private static StructLogger<BlockCacheContext> structLog = new StructLogger<>(
            new Slf4jLoggingCallback(
                    LoggerFactory.getLogger("com.github.structlogging.Structured")
            )
    );

    @LoggerContext(context = AnotherContext.class)
    private static StructLogger<AnotherContext> anotherContextStructLog = new StructLogger<>(
            new Slf4jLoggingCallback(
                    LoggerFactory.getLogger("com.github.structlogging.Another")
            )
    );

    public static void main(String[] args) {

        int numCached = 0;
        int neededCached = 0;
        long blockId = 0;
        long datanodeUuid = 0;
        String reason = "reason";

        defaultLog.info("Event with double={} and aa  a boolean={}")
                .varDouble(1.2)
                .varBoolean(false)
                .log("edu.TestEvent");

        defaultLog.info("Event with double={} and boolean={} and double={} and double={}")
                .varDouble(1.2)
                .varBoolean(true)
                .varDouble(5.6)
                .varDouble(1.0)
                .log();

        structLog.warn("Block removal for dataNode from PENDING_UNCACHED - it was uncached by the dataNode")
                .blockId(blockId)
                .dataNodeUuid(datanodeUuid)
                .log();

        structLog.warn("Block removal for dataNode from PENDING_UNCACHED - it was uncached by the dataNode")
                .blockId(blockId)
                .dataNodeUuid(datanodeUuid)
                .log();

        structLog.info("Cannot cache block")
                .blockId(blockId)
                .reason(reason)
                .log("CannotCache");

        structLog.trace("Block removal for dataNode from PENDING_CACHED - we already have enough cached replicas")
                .blockId(blockId)
                .dataNodeUuid(datanodeUuid)
                .numCached(numCached)
                .neededCached(neededCached)
                .log("com.github.BlockRemoval");

        structLog.info("Block removal for dataNode from PENDING_UNCACHED - we do not have enough cached replicas")
                .blockId(blockId)
                .dataNodeUuid(datanodeUuid)
                .numCached(numCached)
                .neededCached(neededCached)
                .log("com.BlockRemoval");

        structLog.info("Block removal for dataNode from cachedBlocks - neededCached == 0, and pendingUncached and pendingCached are empty.")
                .blockId(blockId)
                .log();

        structLog.info("Block removal for dataNode from PENDING_UNCACHED - it was uncached by the dataNode")
                .blockId(new Object().hashCode())
                .dataNodeUuid(someMethod())
                .log();

        structLog.error("Event with blockId")
                .blockId(blockId)
                .log();

        anotherContextStructLog.info("Event with context")
                .context("ahoj")
                .log();

        structLog.info("Block removal for dataNode from PENDING_UNCACHED - it was uncached by the dataNode")
                .blockId(new Object().hashCode())
                .dataNodeUuid(someMethod())
                .log();

        structLog.audit("Audit event with blockId and dataNodeUuid")
                .blockId(1)
                .dataNodeUuid(2)
                .log();
     }

    private static int someMethod() {
        return 0;
    }
}