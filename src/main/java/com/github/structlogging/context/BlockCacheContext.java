package com.github.structlogging.context;

import com.github.structlogging.VariableContext;
import com.github.structlogging.annotation.Var;
import com.github.structlogging.annotation.VarContextProvider;

@VarContextProvider
public interface BlockCacheContext extends VariableContext {

    @Var
    BlockCacheContext blockId(long blockId);

    @Var
    BlockCacheContext dataNodeUuid(long dataNodeUuid);

    @Var
    BlockCacheContext numCached(int numCached);

    @Var
    BlockCacheContext neededCached(int neededCached);

    @Var
    BlockCacheContext reason(String reason);

    @Var
    BlockCacheContext context(int context);

}
