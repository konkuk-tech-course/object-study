package org.example.rectangle_wrong_example;

public class AnyClass {

    void anyMethod(Rectangle rectangle, int multiple){
        rectangle.setRight(rectangle.getRight() * multiple);
        rectangle.setBottom(rectangle.getBottom() * multiple); // 이렇게 처리해버리면 캡슐화가 제대로 되고 있지 않는거임. -> 인터페이스 자체가 내부 구현을 보여주고 있기 때문에
    }

}
