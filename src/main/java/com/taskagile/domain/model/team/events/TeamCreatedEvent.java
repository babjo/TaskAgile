package com.taskagile.domain.model.team.events;

import com.taskagile.domain.common.event.DomainEvent;
import com.taskagile.domain.model.team.Team;

public class TeamCreatedEvent extends DomainEvent {

    private Team team;

    public TeamCreatedEvent(Object source, Team team) {
        super(source);
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
