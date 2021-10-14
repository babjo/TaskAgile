package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.CreateTeamCommand;
import com.taskagile.domain.model.user.UserId;
import com.taskagile.domain.model.team.Team;

import java.util.List;

public interface TeamService {

    List<Team> findTeamsByUserId(UserId userId);

    Team createTeam(CreateTeamCommand command);
}
