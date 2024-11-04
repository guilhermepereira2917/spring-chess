import GameResultEnum from "@/enums/gameResultEnum";
import SquareModel from "./squareModel";

export default interface BoardModel {
  squares: SquareModel[][],
  gameResult: GameResultEnum | null,
};