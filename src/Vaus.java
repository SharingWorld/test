import processing.core.PApplet;

import java.security.Key;

public class Vaus extends MovableView implements Constants {

    private boolean controlR = true;
    private boolean controlL = true;
    private boolean controlTotal = true;

    Vaus(PApplet pApplet) {
        super(pApplet, RECT);
        SpriteManager.putImages(pApplet, VAUS,
                "./images/Vaus.png",
                0,0,0,0,
                152,56, 6);

        position.x = WIDTH / 2;
        position.y = HEIGHT - 100;
        size.x = VAUS_SIZE_X;
        size.y = VAUS_SIZE_Y;

        CollisionManager.allocate(VAUS, this);

        Arkanoid3.keyEventManager.setOnPressListener(pApplet.LEFT, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if(controlL)
                    position.x -= 10;
                controlR = true;
            }
        });

        Arkanoid3.keyEventManager.setOnPressListener(pApplet.RIGHT, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                if(controlR)
                    position.x += 10;
                controlL = true;
            }
        });


    }



    @Override
    public void onUpdate() {

        if(controlTotal){
            controlR = true;
            controlL = true;
        }

    }

    @Override
    public void render() {

        pApplet.image(SpriteManager.getImages(VAUS).get(0),
                position.x - size.x / 2, position.y - size.y / 2,
                size.x, size.y);

    }

    @Override
    public void onCollision(View view) {

        controlTotal = false;

        if(view instanceof Rect){
            Rect rect = (Rect)view;
            if(rect.position.x < position.x) {
                controlL = false;
//                controlR = true;
            }
            else if(rect.position.x > position.x+ size.x / 2) {
                controlR = false;
//                controlR = true;
            }

        }

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }


}
