import PieceSideEnum from "@/enums/pieceSideEnum";
import PieceTypeEnum from "@/enums/pieceTypeEnum";
import PieceModel from "@/models/game/pieceModel";
import SquareModel from "@/models/game/squareModel";
import React, { ReactNode } from "react";

interface Props {
  square: SquareModel,
  handleSquareClicked: (square: SquareModel) => void,
}

export default function Square(props: Props): ReactNode {
  const squareColor: string = (props.square.column - props.square.row % 2) % 2 == 0 ? "bg-white" : "bg-black";

  const pieceSvgPath: string | null = ((): string | null => {
    if (!props.square.piece) {
      return null;
    }

    const piece: PieceModel = props.square.piece;

    const pieceColor: string = piece.side == PieceSideEnum.WHITE ? 'w' : 'b';
    let pieceAbreviationLetter: string;

    switch (piece.type) {
      case PieceTypeEnum.PAWN:
        pieceAbreviationLetter = 'P';
        break;
      case PieceTypeEnum.KNIGHT:
        pieceAbreviationLetter = 'N';
        break;
      case PieceTypeEnum.BISHOP:
        pieceAbreviationLetter = 'B';
        break;
      case PieceTypeEnum.ROOK:
        pieceAbreviationLetter = 'R';
        break;
      case PieceTypeEnum.QUEEN:
        pieceAbreviationLetter = 'Q';
        break;
      case PieceTypeEnum.KING:
        pieceAbreviationLetter = 'K';
        break;
    }

    return `pieces/horsey/${pieceColor}${pieceAbreviationLetter}.svg`;
  })();

  return (
    <div className={`${squareColor} w-[100px] h-[100px] relative`} onClick={() => { props.handleSquareClicked(props.square) }}>
      <p className="text-red-600 font-bold">{`${props.square.row}, ${props.square.column}`}</p>
      {pieceSvgPath && (
        <img className="absolute left-1/2 top-1/2 -translate-x-1/2 -translate-y-1/2" src={pieceSvgPath} />
      )}
    </div>
  );
}