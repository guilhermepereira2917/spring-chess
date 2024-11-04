enum GameResultEnum {
  WHITE_WINS = 'WHITE_WINS',
  BLACK_WINS = 'BLACK_WINS',
  DRAW = 'DRAW',
};

export const GameResultEnumLabel = new Map<GameResultEnum, string>([
  [GameResultEnum.WHITE_WINS, 'White wins!'],
  [GameResultEnum.BLACK_WINS, 'Black wins!'],
  [GameResultEnum.DRAW, 'Draw.'],
]);

export default GameResultEnum;