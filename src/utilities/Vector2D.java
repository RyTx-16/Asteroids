package utilities;
// mutable 2D vectors
public final class Vector2D {
    public double x, y;

    // constructor for zero vector
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }
    // constructor for vector with given coordinates
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    // constructor that copies the argument vector
    public Vector2D(Vector2D v){
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2D set(double x, double y){
        this.x = x;
        this.y = y;
        return this;
    }
    // set coordinates
    public Vector2D set(Vector2D v){
        this.x = v.x;
        this.y = v.y;
        return this;
    }
    // set coordinates based on argument vector
    public boolean equals(Object o){
        if (getClass() == o.getClass()){
            Vector2D v = (Vector2D) o;
            return this.x == v.x && this.y == v.y;
        }
        return false;
    }

    // String for displaying vector as text
    public String toString(){
        String s = "( "+this.x+", "+this.y+")";
        return s;
    }


    //  magnitude (= "length") of this vector
    public double mag(){

        return Math.hypot(this.x, this.y);
    }


    // angle between vector and horizontal axis in radians in range [-PI,PI]
    // can be calculated using Math.atan2
    public double angle(){

        return Math.atan2(this.y, this.x);
    }


    // angle between this vector and another vector in range [-PI,PI]
    public double angle(Vector2D other){
        double angle1 = Math.atan2(this.y, this.x);
        double angle2 = Math.atan2(other.y, other.x);
        double differ = angle2 - angle1;
        if (differ > Math.PI){
            differ -= Math.PI * 2;
            //return differ;
        }
        if (differ < -Math.PI){
            differ += 2 * Math.PI;
            //return Math.PI * 2 + differ;
        }
        return differ;
         //return Math.atan2(this.y - other.y, this.x - other.x);
    }
    // add argument vector
    public Vector2D add(Vector2D v){
        this.x += v.x;
        this.y += v.y;
        return this;
    }
    // add values to coordinates
    public Vector2D add(double x, double y){
        this.x += x;
        this.y += y;
        return this;
    }
    // weighted add - surprisingly useful
    public Vector2D addScaled(Vector2D v, double fac){
        this.x += fac * (v.x);
        this.y += fac * (v.y);
        return this;
    }

    public Vector2D subtract(double x, double y){
        this.x -= x;
        this.y -= y;
        return this;
    }
    // subtract argument vector
    public Vector2D subtract(Vector2D v){
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }
    // multiply with factor
    public Vector2D mult(double fac) {
        this.x *= fac;
        this.y *= fac;
        return this;
    }

    // rotate by angle given in radians
    public Vector2D rotate(double angle) {
        double newX = (this.x * Math.cos(angle)) - (this.y * Math.sin(angle));
        this.y = (this.x * Math.sin(angle)) + (this.y * Math.cos(angle));
        this.x = newX;
        return this;
    }

    // "dot product" ("scalar product") with argument vector
    public double dot(Vector2D v) {

        return(this.mag() * v.mag() * Math.cos(angle(v)));
    }

    // distance to argument vector
    public double dist(Vector2D v) {
        return(Math.sqrt(Math.pow((v.x - this.x),2) + Math.pow((v.y - this.y),2)));
    }

    // normalise vector so that magnitude becomes 1
    public Vector2D normalise() {

        double magnitude = this.mag();
        this.x /= magnitude;
        this.y /= magnitude;
        return this;
    }

    // wrap-around operation, assumes w> 0 and h>0
// remember to manage negative values of the coordinates
    public Vector2D wrap(double w, double h) {

        if(this.x > w){
            this.x -= w;
        }
        else if(this.x < 0){
            this.x += w;
        }

        if(this.y > h){
            this.y -=h ;
        }
        else if (this.y < 0){
            this.y += h;
        }
        return this;

    }

    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag) {
        double x = mag * Math.cos(angle);
        double y = mag * Math.sin(angle);
        return new Vector2D(x,y);
    }

}