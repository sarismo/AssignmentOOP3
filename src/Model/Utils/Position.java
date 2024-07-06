package Model.Utils;

public class Position implements Comparable<Position> {

        protected int x;
        protected int y;
        public Position(int x, int y){
            this.x = x;
            this.y = y;
        }
        public double Range(Position q){
            return Math.sqrt(Math.pow((x-q.x),2)+Math.pow((y-q.y),2));
        }

        public int getX(){
            return x;

        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public int compareTo(Position p) {
            if(p.y < this.y)
                return 1;
            else if (p.y > y)
                return -1;
            else {
                if (p.x < this.x)
                    return 1;
                else if (p.x > this.x)
                    return -1;
            }
            return 0;
        }


        public Position Translate(int _x,int _y){
            return new Position(x+_x, y+_y);
        }

    }



