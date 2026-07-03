package game;

import java.awt.Color;

public final class GameConstants {
	public static final String TITLE = "Rivers Raid";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public static final int FPS = 60;
	public static final long FRAME_TIME_NS = 1_000_000_000L / FPS;
	public static final Color COLOR_BACKGROUND = new Color(20, 20, 20);
    public static final Color COLOR_WATER      = new Color(40, 60, 180);
    public static final Color COLOR_LAND       = new Color(80, 130, 30);
    public static final Color COLOR_HUD_TEXT   = Color.WHITE;
    public static final int   PLAYER_WIDTH  = 30;
    public static final int   PLAYER_HEIGHT = 30;
    public static final double PLAYER_SPEED = 250;
    public static final Color COLOR_PLAYER  = Color.YELLOW;
    public static final double SCROLL_SPEED = 150;
    public static final int    BULLET_WIDTH    = 4;
    public static final int    BULLET_HEIGHT   = 12;
    public static final double BULLET_SPEED    = 500;
    public static final Color  COLOR_BULLET    = Color.YELLOW;
    public static final double SHOOT_COOLDOWN  = 0.4;
    public static final int RIVER_START_WIDTH = 200;
    public static final int SEGMENT_HEIGHT  = 10;
    public static final int RIVER_MIN_WIDTH = 120;
    public static final int RIVER_MAX_WIDTH = 350;
    public static final int BANK_MARGIN     = 20;
    public static final int RIVER_DRIFT     = 3;
    public static final int    ENEMY_WIDTH       = 34;
    public static final int    ENEMY_HEIGHT      = 20;
    public static final double HELICOPTER_SPEED  = 80;
    public static final Color  COLOR_ENEMY       = new Color(200, 40, 40);
    public static final double ENEMY_SPAWN_GAP   = 250;
    
    private GameConstants() {}
}
