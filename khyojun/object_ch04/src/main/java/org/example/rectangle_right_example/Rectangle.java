package org.example.rectangle_right_example;

public class Rectangle {
    private int left;
    private int right;
    private int top;
    private int bottom;

    public Rectangle(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public void enlarge(int multiple){  // wrong_example과는 달리 객체의 상태에 대한 처리를 자기 스스로 할 수 있도록 스스로 책임지게 만듦 (객체의 상태와 행동을 하나로 묶은 이유)
        right*=multiple;
        bottom*=multiple;
    }


    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }
}
