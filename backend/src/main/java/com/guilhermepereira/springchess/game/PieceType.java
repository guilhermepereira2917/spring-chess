package com.guilhermepereira.springchess.game;

public enum PieceType {
	PAWN {
		@Override
		public char getSymbol() {
			return 'P';
		}
	},
	KNIGHT {
		@Override
		public char getSymbol() {
			return 'N';
		}
	},
	BISHOP {
		@Override
		public char getSymbol() {
			return 'B';
		}
	},
	ROOK {
		@Override
		public char getSymbol() {
			return 'R';
		}
	},
	QUEEN {
		@Override
		public char getSymbol() {
			return 'Q';
		}
	},
	KING {
		@Override
		public char getSymbol() {
			return 'K';
		}
	};

	public abstract char getSymbol();

	public static PieceType getPieceTypeFromSymbol(char symbol) {
		for (final PieceType pieceType : PieceType.values()) {
			if (pieceType.getSymbol() == symbol) {
				return pieceType;
			}
		}

		return null;
	}
}
