import PieceSideEnum from "@/enums/pieceSideEnum";
import PieceTypeEnum from "@/enums/pieceTypeEnum";
import PieceModel from "@/models/game/pieceModel";
import SquareModel from "@/models/game/squareModel";
import classNames from "classnames";
import React, { ReactNode } from "react";
import { Tooltip } from "./ui/tooltip";
import { TooltipContent, TooltipProvider, TooltipTrigger } from "@radix-ui/react-tooltip";

interface Props {
  square: SquareModel,
  isSquareSelected: boolean,
  handleSquareClicked: (square: SquareModel) => void,
}

export default function Square(props: Props): ReactNode {
  const squareColor: string = (props.square.column - props.square.row % 2) % 2 == 0 ? "bg-white" : "bg-black";

  const divClassNames = classNames(
    squareColor,
    'w-[100px]',
    'h-[100px]',
    'relative',
    { 'cursor-pointer': props.square.piece },
  );

  const imgClassNames = classNames(
    'absolute',
    'left-1/2',
    'top-1/2',
    '-translate-x-1/2',
    '-translate-y-1/2',
    { 'scale-110': props.isSquareSelected },
    'pointer-events-none'
  );

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

  const squareCoordinate: string = `${String.fromCharCode(props.square.column + 97)}${8 - props.square.row} {${props.square.row}, ${props.square.column}}`;

  return (
    <TooltipProvider>
      <Tooltip>
        <TooltipTrigger>
          <div className={divClassNames} onClick={() => { props.handleSquareClicked(props.square) }}>
            {pieceSvgPath && (
              <img className={imgClassNames} src={pieceSvgPath} />
            )}
          </div>
        </TooltipTrigger>
        <TooltipContent side="bottom" sideOffset={-50}>
          <p className="text-red-400 font-bold m-1 z-50 bg-white p-1 rounded outline">{squareCoordinate}</p>
        </TooltipContent>
      </Tooltip>
    </TooltipProvider>
  );
}