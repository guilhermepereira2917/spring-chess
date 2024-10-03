package com.guilhermepereira.springchess.dtos;

public record MoveDto(int originalRow, int originalColumn, int targetRow, int targetColumn) {
}
