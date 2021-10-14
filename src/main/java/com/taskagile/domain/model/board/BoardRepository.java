package com.taskagile.domain.model.board;

import com.taskagile.domain.model.user.UserId;

import java.util.List;

public interface BoardRepository {

    List<Board> findBoardsByMembership(UserId userId);

    void save(Board board);
}
