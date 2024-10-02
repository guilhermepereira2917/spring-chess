import api from "./api";
import GameModel from "@/models/game/gameModel";

class GameService {
  async getGame(): Promise<GameModel> {
    return (await api.get<GameModel>(`/game`)).data;
  }
}

const gameService = new GameService();

export default gameService;