package com.github.structlogging;

import com.github.structlogging.annotation.LoggerContext;
import com.github.structlogging.context.AnotherContext;
import com.github.structlogging.context.BlockCacheContext;
import com.github.structlogging.slf4j.Slf4jLoggingCallback;
import org.slf4j.LoggerFactory;

@SuppressWarnings("ConstantConditions")
public class BasicExample {

	@LoggerContext(context = DefaultContext.class)
	private static StructLogger<DefaultContext> DEFAULT_LOG = new StructLogger<>(
			new Slf4jLoggingCallback(
					LoggerFactory.getLogger("com.github.structlogging.Default")
			)
	);

	@LoggerContext(context = BlockCacheContext.class)
	private static StructLogger<BlockCacheContext> STRUCT_LOG = new StructLogger<>(
			new Slf4jLoggingCallback(
					LoggerFactory.getLogger("com.github.structlogging.Structured")
			)
	);

	@LoggerContext(context = AnotherContext.class)
	private static StructLogger<AnotherContext> ANOTHER_LOG = new StructLogger<>(
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

		DEFAULT_LOG.info("Event with double={} and aa  a boolean={}")
				.varDouble(1.2)
				.varBoolean(false)
				.log("edu.TestEvent");

		/*
		 *
		 *
		 *
		 *
		 * */

		DEFAULT_LOG.info("Event with double={} and boolean={} and double={} and double={}")
				.varDouble(1.2)
				.varBoolean(true)
				.varDouble(5.6)
				.varDouble(1.0)
				.log();

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.warn("Block removal for dataNode from PENDING_UNCACHED - it was uncached by the dataNode")
				.blockId(blockId)
				.dataNodeUuid(datanodeUuid)
				.log();

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.warn("Block removal for dataNode from PENDING_UNCACHED - it was uncached by the dataNode")
				.blockId(blockId)
				.dataNodeUuid(datanodeUuid)
				.log();

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.info("Cannot cache block")
				.blockId(blockId)
				.reason(reason)
				.log("CannotCache");

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.trace("Block removal for dataNode from PENDING_CACHED - we already have enough cached replicas")
				.blockId(blockId)
				.dataNodeUuid(datanodeUuid)
				.numCached(numCached)
				.neededCached(neededCached)
				.log("com.github.BlockRemoval");

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.info("Block removal for dataNode from PENDING_UNCACHED - we do not have enough cached replicas")
				.blockId(blockId)
				.dataNodeUuid(datanodeUuid)
				.numCached(numCached)
				.neededCached(neededCached)
				.log("com.BlockRemoval");

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.info("Block removal for dataNode from cachedBlocks - neededCached == 0, and pendingUncached and pendingCached are empty.")
				.blockId(blockId)
				.log();

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.info("Block removal for dataNode from PENDING_UNCACHED - it was uncached by the dataNode")
				.blockId(new Object().hashCode())
				.dataNodeUuid(someMethod())
				.log();

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.error("Event with blockId")
				.blockId(blockId)
				.log();

		/*
		 *
		 *
		 *
		 *
		 * */

		ANOTHER_LOG.info("Event with context")
				.context("ahoj")
				.log();

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.info("Block removal for dataNode from PENDING_UNCACHED - it was uncached by the dataNode")
				.blockId(new Object().hashCode())
				.dataNodeUuid(someMethod())
				.log();

		/*
		 *
		 *
		 *
		 *
		 * */

		STRUCT_LOG.audit("Audit event with blockId and dataNodeUuid")
				.blockId(1)
				.dataNodeUuid(2)
				.log();
	}

	private static int someMethod() {
		return 0;
	}
}