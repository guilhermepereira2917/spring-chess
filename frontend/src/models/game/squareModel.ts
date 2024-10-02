import PieceModel from "./pieceModel";

export default interface SquareModel {
  row: number,
  column: number,
  piece: PieceModel | null,
}