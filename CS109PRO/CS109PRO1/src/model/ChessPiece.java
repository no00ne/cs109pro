package model;


public class ChessPiece {
    // the owner of the chess
    private PlayerColor owner;

    // Elephant? Cat? Dog? ...
    private String name;
    private int rank;
    private boolean trapped;
    public ChessPiece(PlayerColor owner, String name, int rank, boolean trapped) {
        this.owner = owner;
        this.name = name;
        this.rank = rank;
        this.trapped = trapped;
    }

    public void setTrapped(boolean trapped) {
        this.trapped = trapped;
    }

    public boolean isTrapped() {
        return trapped;
    }

    public boolean canCapture(ChessPiece target) {
        boolean CanDo = false;
        if (target != null ){
            if(!this.owner.equals(target.getOwner())){
              if(target.isTrapped()){
                  CanDo = true;
              }
               else if (this.rank == 8){
                  if (target.getRank() != 1){
                  CanDo = true;
            }
        }
               else if (this.rank == 1){
                  if (target.getRank() == 8 | target.getRank() == 1){
                  CanDo = true;
            }
        }
               else if (this.rank < 8 & this.rank > 1){
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
