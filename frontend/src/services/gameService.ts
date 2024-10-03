import GameModel from "@/models/game/gameModel";
import MoveModel from "@/models/game/moveModel";
import api from "./api";

class GameService {
  async getGame(): Promise<GameModel> {
    return (await api.get<GameModel>(`/game`)).data;
  }

  async playMove(move: MoveModel): Promise<GameModel> {
    return (await api.post<GameModel>(`/game`, move)).data;
  }
}

const gameService = new GameService();

export default gameService;