package model;


public class ChessPiece {
    // the owner of the chess
    private PlayerColor owner;

    // Elephant? Cat? Dog? ...
    private String name;
    private int rank;

    public ChessPiece(PlayerColor owner, String name, int rank) {
        this.owner = owner;
        this.name = name;
        this.rank = rank;
    }

    public boolean canCapture(ChessPiece target) {
        boolean CanDo = false;
        if (target != null ){
            if(!this.owner.equals(target.getOwner())){
               if (this.rank == 8){
                  if (target.getRank() != 1){
                  CanDo = true;
            }
        }
               if (this.rank == 1){
                  if (target.getRank() == 8 | target.getRank() == 1){
                  CanDo = true;
            }
        }
               if (this.rank < 8 & this.rank > 1){
                  if (target.getRank() <= this.rank){
                  CanDo = true;
            }
          }
            }
          }
        return CanDo;
    }

    public String getName() {
        return name;
    }

    public PlayerColor getOwner() {
        return owner;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
