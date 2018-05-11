import processing.core.PApplet;

public class Item extends MovableView implements Constants {

    private int item;

    Item(PApplet pApplet, int item, float posX, float posY) {
        super(pApplet, RECT, posX, posY, ITEM_SIZE_X, ITEM_SIZE_Y);

        direction.y = 1;
        direction = direction.nomalize();

        this.item = item;

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {

        pApplet.image(SpriteManager.getImages(item).get(0), position.x, position.y,
                size.x, size.y);
    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
