package com.taskagile.domain.model.board.events;

import com.taskagile.domain.common.event.DomainEvent;
import com.taskagile.domain.model.board.Board;

public class BoardCreatedEvent extends DomainEvent {

    private Board board;

    public BoardCreatedEvent(Object source, Board board) {
        super(source);
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
