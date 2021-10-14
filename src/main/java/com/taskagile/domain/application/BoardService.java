package com.taskagile.domain.application;

import com.taskagile.domain.application.commands.CreateBoardCommand;
import com.taskagile.domain.model.board.Board;
import com.taskagile.domain.model.user.UserId;

import java.util.List;

public interface BoardService {

    List<Board> findBoardsByMembership(UserId userId);
    Board createBoard(CreateBoardCommand command);
}
