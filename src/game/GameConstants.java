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
    public static final Color  COLOR_ENEMY       = new Color(200, 40, 40); // kırmızı
    public static final double ENEMY_SPAWN_GAP   = 250;
    public static final double FUEL_MAX          = 100;
    public static final double FUEL_CONSUMPTION  = 5;
    public static final double FUEL_REFILL_RATE  = 40;
    public static final int    HELICOPTER_POINTS = 100;
    public static final int   FUEL_DEPOT_WIDTH  = 34;
    public static final int   FUEL_DEPOT_HEIGHT = 40;
    public static final Color COLOR_FUEL_DEPOT  = new Color(240, 180, 40); // sarımsı
    public static final double FUEL_DEPOT_SPAWN_GAP = 400;
    public static final int STARTING_LIVES = 3;
    public static final double INVINCIBLE_TIME = 2.0;
    public static final double RESPAWN_FUEL = 50;
    public static final int    SHIP_WIDTH   = 40;
    public static final int    SHIP_HEIGHT  = 22;
    public static final double SHIP_SPEED   = 40;
    public static final Color COLOR_SHIP = new Color(255, 140, 0); // turuncu
    public static final int    SHIP_POINTS  = 50;

    public static final int    JET_WIDTH    = 30;
    public static final int    JET_HEIGHT   = 16;
    public static final double JET_SPEED    = 200;
    public static final Color COLOR_JET = new Color(200, 60, 220); // mor
    public static final int    JET_POINTS   = 150;
    
    public static final int    BRIDGE_HEIGHT   = 30;
    public static final Color  COLOR_BRIDGE    = new Color(140, 90, 40); // kahverengi
    public static final double BRIDGE_DISTANCE = 3000;
    public static final int    BRIDGE_POINTS   = 500;
    
    private GameConstants() {}
}
