package com.taskagile.domain.common.model;

import java.io.Serializable;

public abstract class AbstractBaseEntity implements Serializable {
    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract String toString();
}
