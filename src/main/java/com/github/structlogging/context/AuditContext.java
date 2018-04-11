package com.github.structlogging.context;

import com.github.structlogging.VariableContext;
import com.github.structlogging.annotation.VarContextProvider;
import com.github.structlogging.annotation.Var;

@VarContextProvider
public interface AuditContext extends VariableContext {

    @Var
    AuditContext id(long id);
}
