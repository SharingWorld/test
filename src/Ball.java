import processing.core.PApplet;

public class Ball extends MovableView implements Constants{

    private float radius;

    Ball(PApplet pApplet) {
        super(pApplet, CIRCLE);
        SpriteManager.putImage(pApplet, BALL,
                "./images/BALL.png");

        position.x = DataHelper.getUsageVaus().position.getX() - BALL_SIZE / 2;
        position.y = DataHelper.getUsageVaus().position.getY() - VAUS_SIZE_Y;


        size.x = BALL_SIZE;
        size.y = BALL_SIZE;
        radius = size.x / 2;
        speed = 7f;

        CollisionManager.allocate(CIRCLE, this);
        Arkanoid3.keyEventManager.setOnReleaseListener(' ', new KeyEventManager.OnReleaseListener() {
            @Override
            public void onRelease(float duration) {
                direction.x = (float) Math.random() * 2 - 1f;
                direction.y = (float) Math.random() * -1;
                direction = direction.nomalize();
            }
        });

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {
        pApplet.image(SpriteManager.getImage(BALL),
                position.x, position.y, size.x, size.y);
    }

    @Override
    public void onCollision(View view) {

        if(view instanceof Block){
            Block block = (Block)view;

            if(block.getVisible()) {

            position.subtract(getVelocity());

                if (block.position.x - block.size.x / 2 <= position.x + radius
                        && block.position.x - block.size.x / 2
                        + BLOCK_SIZE_X >= position.x - radius) {
                    direction.y = -direction.y;
                    System.out.println("a");
//                    block.setVisible(INVISIBLE);
                } else {
                    direction.x = -direction.x;
                    System.out.println("b");
//                    block.setVisible(INVISIBLE);


                }
            }

        }
        else if(view instanceof Rect){

            Rect wall = (Rect)view;
            position.subtract(getVelocity());

            if(wall.position.x - wall.size.x / 2 < position.x - radius
                    && wall.position.x - wall.size.x / 2 + wall.size.x > position.x + radius){
                direction.y = -direction.y;
            }
            else {
                direction.x = -direction.x;
            }


        }
        else if(view instanceof Vaus){

            Vaus vaus = (Vaus)view;
            position.subtract(getVelocity());

            float gap = position.x - vaus.position.x;
            if(gap > 0){
                direction.x = gap / 60;
                direction.y = -direction.y;

            }
            else if(gap == 0){
                direction.y = -direction.y;
            }
            else if(gap < 0){
                direction.x = gap / 60;
                direction.y = -direction.y;
            }
            direction = direction.nomalize();
        }
    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
