import PieceSideEnum from "@/enums/pieceSideEnum";
import PieceTypeEnum from "@/enums/pieceTypeEnum";

export default interface PieceModel {
  type: PieceTypeEnum,
  side: PieceSideEnum,
};