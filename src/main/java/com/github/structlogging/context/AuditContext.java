package com.github.structlogging.context;

import com.github.structlogging.VariableContext;
import com.github.structlogging.annotation.Var;
import com.github.structlogging.annotation.VarContextProvider;

@VarContextProvider
public interface AuditContext extends VariableContext {

	@Var
	AuditContext id(long id);
}
