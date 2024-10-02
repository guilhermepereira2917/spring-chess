import PieceSideEnum from "@/enums/pieceSideEnum";
import PieceTypeEnum from "@/enums/pieceTypeEnum";
import GameModel from "@/models/game/gameModel";
import PieceModel from "@/models/game/pieceModel";
import SquareModel from "@/models/game/squareModel";
import gameService from "@/services/gameService";
import { useQuery, UseQueryResult } from "@tanstack/react-query";
import { ReactNode } from "react";

export default function Game(): ReactNode {
  const { isPending, error, data: game }: UseQueryResult<GameModel> = useQuery({
    queryKey: ['game'],
    queryFn: () => gameService.getGame()
  });

  if (isPending) {
    return <p>Loading...</p>
  }

  if (error) {
    return <p>An error has ocurred: {error.message}</p>
  }

  return (
    <div className="grid grid-cols-8 grid-rows-8 w-[800px]">
      {game.board.squares.flat().map((square: SquareModel): ReactNode => {
        const squareColor: string = (square.column - square.row % 2) % 2 == 0 ? "bg-white" : "bg-black";

        const pieceSvgPath: string | null = ((): string | null => {
          if (!square.piece) {
            return null;
          }

          const piece: PieceModel = square.piece;

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
          <div className={`${squareColor} w-[100px] h-[100px]`}>
            {pieceSvgPath && (
              <img src={pieceSvgPath} />
            )}
          </div>
        );
      })}
    </div>
  );
};