public class Dice {
  private int face;

  public void roll() {
    face = (int) (Math.random() * 5) + 1;
  }

  public int getFace() {
    return face;
  }
}