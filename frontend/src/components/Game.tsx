import GameModel from "@/models/game/gameModel";
import MoveModel from "@/models/game/moveModel";
import SquareModel from "@/models/game/squareModel";
import gameService from "@/services/gameService";
import { QueryClient, useMutation, UseMutationResult, useQuery, useQueryClient, UseQueryResult } from "@tanstack/react-query";
import { ReactNode, useState } from "react";
import Square from "./Square";
import GameFinishDialog from "./GameFinishDialog";

export default function Game(): ReactNode {
  const [firstSelectedSquare, setFirstSelectedSquare] = useState<SquareModel | null>(null);

  const { isPending, error, data: game }: UseQueryResult<GameModel> = useQuery({
    queryKey: ['game'],
    queryFn: () => gameService.getGame()
  });

  const queryClient: QueryClient = useQueryClient();
  const playMoveMutation: UseMutationResult<GameModel, Error, MoveModel, unknown> = useMutation({
    mutationFn: gameService.playMove,
    onSuccess: (data: GameModel) => {
      queryClient.setQueryData(['game'], data);
    },
  });

  if (isPending) {
    return <p>Loading...</p>
  }

  if (error) {
    return <p>An error has ocurred: {error.message}</p>
  }

  const handleSquareClicked = (square: SquareModel): void => {
    if (game.board.gameResult || playMoveMutation.isPending) {
      return;
    }

    if (!firstSelectedSquare) {
      if (!square.piece) {
        return;
      }

      setFirstSelectedSquare(square);
      return;
    }

    if (square == firstSelectedSquare) {
      setFirstSelectedSquare(null);
      return;
    }

    const move: MoveModel = {
      originalRow: firstSelectedSquare.row,
      originalColumn: firstSelectedSquare.column,
      targetRow: square.row,
      targetColumn: square.column,
    };

    playMoveMutation.mutate(move);
    setFirstSelectedSquare(null);
  }

  return (
    <>
      <div className="grid grid-cols-8 grid-rows-8 w-[800px]">
        {game.board.squares.flat().map((square: SquareModel, index: number): ReactNode => {
          return <Square key={index} isSquareSelected={square == firstSelectedSquare} square={square} handleSquareClicked={handleSquareClicked} />
        })}
      </div>
      <GameFinishDialog game={game} />
    </>
  );
};