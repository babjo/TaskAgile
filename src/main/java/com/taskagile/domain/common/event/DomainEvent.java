package com.taskagile.domain.common.event;

import org.springframework.context.ApplicationEvent;

public abstract class DomainEvent extends ApplicationEvent {

    public DomainEvent(Object source) {
        super(source);
    }

    public long occurredAt() {
        return getTimestamp();
    }
}
