import GameModel from "@/models/game/gameModel";

import { GameResultEnumLabel } from "@/enums/gameResultEnum";
import { ReactNode, useState } from "react";
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from "./ui/dialog";
import { Button } from "./ui/button";

interface GameFinishDialogProps {
  game: GameModel,
};

export default function GameFinishDialog(props: GameFinishDialogProps): ReactNode {
  const [open, setOpen] = useState<boolean>(!!props.game.board.gameResult);

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Game Finished</DialogTitle>
          <DialogDescription>
            {props.game.board.gameResult && GameResultEnumLabel.get(props.game.board.gameResult)}
          </DialogDescription>
          <DialogFooter>
            <Button variant={"secondary"}>New game</Button>
          </DialogFooter>
        </DialogHeader>
      </DialogContent>
    </Dialog>
  );
}